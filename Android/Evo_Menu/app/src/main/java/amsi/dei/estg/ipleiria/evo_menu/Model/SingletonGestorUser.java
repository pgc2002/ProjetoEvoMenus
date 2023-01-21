package amsi.dei.estg.ipleiria.evo_menu.Model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//Class unica, nao se repete mas pode ser acedida.
/*import amsi.dei.estg.ipleiria.evo_menu.Listeners.LivroListener;
import amsi.dei.estg.ipleiria.evo_menu.Listeners.LivrosListener;
import amsi.dei.estg.ipleiria.evo_menu.Listeners.LoginListener;*/

import amsi.dei.estg.ipleiria.evo_menu.R;
import amsi.dei.estg.ipleiria.evo_menu.Utils.UserJsonParser;

//import amsi.dei.estg.ipleiria.evo_menu.Views.DetalhesLivroActivity;

public class SingletonGestorUser {
    private final static String ip = "192.168.1.87";
    private final static String mUrlAPIuser = "http://"+ ip +"/ProjetoEvoMenus/projetofinal/backend/web/api/user";
    private UserBdHelper usersBD = null;
    private static SingletonGestorUser instancia = null;
    private ArrayList<Users> users;
    private static RequestQueue volleyQueue = null;
    private String validacao;
    /*private LivrosListener livrosListener;
    private LivroListener livroListener;
    private LoginListener loginListener;*/

    //Verificar se ja existe ou nao
    public static synchronized SingletonGestorUser getInstance(Context contexto) {
        if (instancia == null) {
            instancia = new SingletonGestorUser(contexto);
            volleyQueue = Volley.newRequestQueue(contexto);
        }
        return instancia;
    }

    private SingletonGestorUser(Context contexto) {
        users = new ArrayList<>();
        usersBD = new UserBdHelper(contexto);
        validacao = "";
    }

    public ArrayList<Users> getUsersBD() {
        return users = usersBD.getAllUsersBD();
    }

    //Buscar os users do ficheiro criado para a lista
    public void setUsers(ArrayList<Users> users) {
        this.users = users;
    }

    public Users getUser(long id) {
        for (Users user : users) {
            return user;
        }
        return null;
    }

    //adicionarlivrosapi
    public void adicionarUsersBD(ArrayList<Users> users) {
        usersBD.removerAllUsersBD();
        for (Users user : users) {
            adicionarUserBD(user);
        }
    }

    //CRUD
    public void adicionarUserBD(Users user) {
        /*Livro livrobd = livrosBD.adicionarLivroBD(livro);

        if(livrobd!=null)
        {
            livros.add(livrobd);
        }*/
        //Adicionar atravez da api
        usersBD.adicionarUserBD(user);
    }

    public void removerUserBD(long id) {
        Users user = getUser(id);
        if (user != null) {
            usersBD.removerUserBD(user.getId());
            /*if(livrosBD.removerLivroBD(livro.getId()))
                livros.remove(livro);*/
        }

    }

    public void editarUserBD(Users dadosUser) {
        Users user = getUser(dadosUser.getId());
        if (user != null) {
            /*(livrosBD.editarLivroBD(dadosLivro)) {
                livro.setTitulo(dadosLivro.getTitulo());
                livro.setAutor(dadosLivro.getAutor());
                livro.setAno(dadosLivro.getAno());
                livro.setSerie(dadosLivro.getSerie());
                livro.setCapa(dadosLivro.getCapa());*/
            usersBD.editarUserBD(dadosUser);

        }
    }


    //pedidos a api
    public void adicionarUserAPI(final Users user, final String pais, final String cidade, final String rua, final String codpost,  final Context contexto)
    {
        if (!UserJsonParser.isConnectionInternet(contexto)) {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT);
            return;
        }
        StringRequest request = new StringRequest(Request.Method.POST, mUrlAPIuser + "/criar?username=" + user.getUsername() + "&nome=" + user.getNome() + "&password=" + user.getPass() + "&email=" + user.getEmail() + "&telemovel=" + user.getTelemovel() + "&nif=" + user.getNif() + "&pais=" + pais + "&cidade=" + cidade + "&rua=" + rua + "&codpost=" + codpost, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                adicionarUserBD(UserJsonParser.parserJsonUser(response));
                //ativar o listener...
        /*if(livroListener != null)
        {
            livroListener.onRefreshDetalhes(DetalhesLivroActivity.OP_CODE_ADICIONAR);
        }*/

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });
        volleyQueue.add(request);
    }

    public void getAllUsersAPI(final Context contexto)
    {
        if(!UserJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIuser, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                users = UserJsonParser.parserJsonUsers(response);
                adicionarUsersBD(users);
                //Ativar o listener
                /*if(livroListener!=null)
                {
                    livrosListener.onRefreshListaLivros(livros);
                }*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });
        volleyQueue.add(req);
    }

    public void removerUserAPI(final Users user, final Context contexto)
    {
        if(!UserJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest req = new StringRequest(Request.Method.DELETE, mUrlAPIuser + '/' + user.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                removerUserBD(user.getId());
                //ativar listener
                /*if(livroListener != null)
                {
                    livroListener.onRefreshDetalhes(DetalhesLivroActivity.OP_CODE_APAGAR);
                }*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });
        volleyQueue.add(req);
    }

    public void editarUserAPI(final Users user, Context contexto)
    {
        if(!UserJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT);
            return;
        }
        StringRequest request = new StringRequest(Request.Method.PUT, mUrlAPIuser + '/' + user.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                editarUserBD(user);
                //ativar o listener...
                /*if(livroListener != null)
                {
                    livroListener.onRefreshDetalhes(DetalhesLivroActivity.OP_CODE_EDITAR);
                }*/

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", "" + user.getId());
                params.put("username", user.getUsername());
                params.put("nome", user.getNome());
                params.put("password", user.getPass());
                params.put("email", user.getEmail());
                params.put("telemovel", user.getTelemovel());
                params.put("nif", user.getNif());
                return params;

            };
        };
        volleyQueue.add(request);
    }

    public void validacaoPassAPI(final String username, final String pass, Context contexto){
        if(!UserJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }
        StringRequest req = new StringRequest(Request.Method.GET, mUrlAPIuser  + "/validar?username="+ username +"&password="+pass, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    validacao = UserJsonParser.parserJsonValidacao(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Ativar o listener
                /*if(livroListener!=null)
                {
                    livrosListener.onRefreshListaLivros(livros);
                }*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("testeValidacao", error.getMessage());
                return;
            }
        });
        volleyQueue.add(req);
    }

    public String getValidacao(){
        return validacao;
    }

    /*
    public void setLivrosListener(LivrosListener livrosListener) {
        this.livrosListener = livrosListener;
    }

    public void setLivroListener(LivroListener livroListener) {
        this.livroListener = livroListener;
    }
    */

    /*
    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }
    */
}

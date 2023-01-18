package amsi.dei.estg.ipleiria.evo_menu.Model;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

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
    private final static String mUrlAPIuser = "http://localhost/ProjetoEvoMenus/projetofinal/backend/web/api/user";
    private UserBdHelper usersBD = null;
    private static SingletonGestorUser instancia = null;
    private ArrayList<Users> users;
    private static RequestQueue volleyQueue = null;
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
    public void adicionarLivrosBD(ArrayList<Users> users) {
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
    public void adicionarUserAPI(final Users user, final Context contexto, String token)
    {
        if(!UserJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT);
            return;
        }
        StringRequest request = new StringRequest(Request.Method.POST, mUrlAPIuser, new Response.Listener<String>() {
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
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", "" + user.getId());
                params.put("username", user.getUsername());
                params.put("auth_key", user.getAuth_key());
                params.put("password_hash", user.getPass_hash());
                params.put("email", user.getEmail());
                params.put("status", "" + user.getStatus());
                params.put("created_at", "" + user.getData_criacao());
                params.put("updated_at", "" + user.getData_update());
                params.put("telemovel", user.getTelemovel());
                params.put("nif", user.getNif());
                params.put("tipo", user.getTipo());
                params.put("nome", user.getNome());
                params.put("idRestaurante", "" + user.getId_restaurante());
                params.put("idMorada", "" + user.getId_morada());
                params.put("idMesa", "" + user.getId_mesa());
                //params.put("capa", livro.getCapa() == null? DetalhesLivroActivity.IMG_DEFAULT : livro.getCapa());

                return params;
            };
        };
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
                adicionarLivrosBD(users);
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

    public void editarLivroAPI(final Users user, Context contexto, final String token)
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
                params.put("auth_key", user.getAuth_key());
                params.put("password_hash", user.getPass_hash());
                params.put("email", user.getEmail());
                params.put("status", "" + user.getStatus());
                params.put("created_at", "" + user.getData_criacao());
                params.put("updated_at", "" + user.getData_update());
                params.put("telemovel", user.getTelemovel());
                params.put("nif", user.getNif());
                params.put("tipo", user.getTipo());
                params.put("nome", user.getNome());
                params.put("idRestaurante", "" + user.getId_restaurante());
                params.put("idMorada", "" + user.getId_morada());
                params.put("idMesa", "" + user.getId_mesa());

                return params;

            };
        };
        volleyQueue.add(request);
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

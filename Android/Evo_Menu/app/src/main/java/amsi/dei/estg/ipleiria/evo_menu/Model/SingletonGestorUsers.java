package amsi.dei.estg.ipleiria.evo_menu.Model;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import amsi.dei.estg.ipleiria.evo_menu.Model.Listeners.UserListener;
import amsi.dei.estg.ipleiria.evo_menu.Model.Listeners.UsersListener;
import amsi.dei.estg.ipleiria.evo_menu.R;
import amsi.dei.estg.ipleiria.evo_menu.UrlApi;
import amsi.dei.estg.ipleiria.evo_menu.Utils.UserJsonParser;

public class SingletonGestorUsers {
    private final static String mUrlAPIuser = new UrlApi().getUrl() + "user";
    private UserBdHelper usersBD = null;
    private static SingletonGestorUsers instancia = null;
    private ArrayList<User> users;
    private static RequestQueue volleyQueue = null;
    private UsersListener usersListener;
    private UserListener userListener;

    //Verificar se ja existe ou nao
    public static synchronized SingletonGestorUsers getInstance(Context contexto) {
        if (instancia == null) {
            instancia = new SingletonGestorUsers(contexto);
            volleyQueue = Volley.newRequestQueue(contexto);
        }
        return instancia;
    }

    private SingletonGestorUsers(Context contexto) {
        users = new ArrayList<>();
        usersBD = new UserBdHelper(contexto);
    }

    public ArrayList<User> getUsersBD() {
        return users = usersBD.getAllUsersBD();
    }

    //Buscar os users do ficheiro criado para a lista
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public User getUser(long id) {
        for (User user : users) {
            return user;
        }
        return null;
    }

    public void adicionarUsersBD(ArrayList<User> users) {
        usersBD.removerAllUsersBD();
        for (User user : users) {
            adicionarUserBD(user);
        }
    }

    //CRUD
    public void adicionarUserBD(User user) {
        /*Livro livrobd = livrosBD.adicionarLivroBD(livro);

        if(livrobd!=null)
        {
            livros.add(livrobd);
        }*/
        //Adicionar atravez da api
        usersBD.adicionarUserBD(user);
    }

    public void removerUserBD(long id) {
        User user = getUser(id);
        if (user != null) {
            usersBD.removerUserBD(user.getId());
            /*if(livrosBD.removerLivroBD(livro.getId()))
                livros.remove(livro);*/
        }

    }

    public void editarUserBD(User dadosUser) {
        User user = getUser(dadosUser.getId());
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
    public void adicionarUserAPI(final User user, final Context contexto)
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
                adicionarUsersBD(users);
                //Ativar o listener
                if(userListener!=null)
                {
                    usersListener.onRefreshListaUsers(users);
                }
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

    public void removerUserAPI(final User user, final Context contexto)
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

    public void editarUserAPI(final User user, Context contexto)
    {
        if(!UserJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT);
            return;
        }
        StringRequest request = new StringRequest(Request.Method.PUT, mUrlAPIuser + "/alterarperfil" +
                "?idUser=" + user.getId() + "&username=" + user.getUsername() + "&nome=" + user.getNome() +
                "&password=" + user.getPass() + "&email=" + user.getEmail() + "&telemovel=" + user.getTelemovel() +
                "&nif=" + user.getNif()
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //editarUserBD(user);
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
        });
        volleyQueue.add(request);
    }

    public void editarMoradaAPI(final Morada morada, Context contexto)
    {
        if(!UserJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT);
            return;
        }
        StringRequest request = new StringRequest(Request.Method.PUT, mUrlAPIuser + "/alterarmorada"+
        "?idUser=" + morada.getIdUser() + "&pais=" + morada.getPais() + "&cidade=" + morada.getCidade() + "&rua=" + morada.getRua() + "&codpost=" + morada.getCodpost()
        , new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                //editarUserBD(morada);
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
        });
        volleyQueue.add(request);
    }

    public void setUsersListener(UsersListener usersListener) {
        this.usersListener = usersListener;
    }

    public void setUserListener(UserListener userListener) {
        this.userListener = userListener;
    }
}

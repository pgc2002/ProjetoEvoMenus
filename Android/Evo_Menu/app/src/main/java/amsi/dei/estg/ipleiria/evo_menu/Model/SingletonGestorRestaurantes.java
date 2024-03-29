package amsi.dei.estg.ipleiria.evo_menu.Model;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
//Class unica, nao se repete mas pode ser acedida.
import amsi.dei.estg.ipleiria.evo_menu.Listeners.RestauranteListener;
import amsi.dei.estg.ipleiria.evo_menu.Listeners.RestaurantesListener;
import amsi.dei.estg.ipleiria.evo_menu.R;
import amsi.dei.estg.ipleiria.evo_menu.UrlApi;
import amsi.dei.estg.ipleiria.evo_menu.Utils.CategoriaJsonParser;
import amsi.dei.estg.ipleiria.evo_menu.Utils.RestauranteJsonParser;
import amsi.dei.estg.ipleiria.evo_menu.Utils.UserJsonParser;

public class SingletonGestorRestaurantes
{
    private final static String mUrlAPIrestaurantes =  new UrlApi().getUrl() + "restaurante"; //link da api
    private static final long REQUEST_TIMEOUT = 15;
    private RestauranteDBHelper restaurantesDB = null;

    private HorarioDBHelper horarioDB = null;
    private static SingletonGestorRestaurantes instancia = null;
    private ArrayList<Restaurante> restaurantes = null;
    private ArrayList<Categoria> categorias;
    private Restaurante restaurante;

    public Restaurante getRestaurante() {
        return restaurante;
    }

    private static RequestQueue volleyQueue = null;
    private RestaurantesListener restaurantesListener;
    private RestauranteListener restauranteListener;

    //Verificar se ja existe ou nao
    public static synchronized SingletonGestorRestaurantes getInstance(Context contexto) {
        if (instancia == null) {
            instancia = new SingletonGestorRestaurantes(contexto);
            volleyQueue = Volley.newRequestQueue(contexto);
        }
        return instancia;
    }

    private SingletonGestorRestaurantes(Context contexto) {
        restaurantes = new ArrayList<>();
        restaurantesDB = new RestauranteDBHelper(contexto);
        horarioDB = new HorarioDBHelper(contexto);
    }

    public ArrayList<Restaurante> getRestaurantesDB() {
        return restaurantes = restaurantesDB.getAllRestaurantesBD();
    }

    //Buscar os livros do ficheiro criado para a lista
    public void setRestaurantes(ArrayList<Restaurante> lista) {
        this.restaurantes = lista;
    }

    public Restaurante getRestaurante(int id) {
        for (Restaurante restaurante : restaurantes) {
            if(restaurante.getId() == id)
                return restaurante;
        }
        return null;
    }

    public ArrayList<Restaurante> getRestaurantes(){
        return restaurantes;
    }

    public void adicionarRestaurantesBD(ArrayList<Restaurante> lista) {
        restaurantesDB.removerAllRestaurantesBD();
        for (Restaurante restaurante : lista) {
            adicionarRestauranteBD(restaurante);
        }
    }

    //CRUD
    public void adicionarRestauranteBD(Restaurante restaurante) {
    /*Livro livrobd = livrosBD.adicionarLivroBD(livro);

    if(livrobd!=null)
    {
        livros.add(livrobd);
    }*/
        //Adicionar atravez da api
        restaurantesDB.adicionarRestauranteBD(restaurante);
    }

    public void getAllRestaurantesAPI(final Context contexto)
    {
        if(!RestauranteJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIrestaurantes, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                restaurantes = RestauranteJsonParser.parserJsonRestaurante(response);
                adicionarRestaurantesBD(restaurantes);

                for (Restaurante restaurante: restaurantes) {
                    SingletonGestorRestaurantes.getInstance(contexto).getHorarioAPI(contexto, restaurante);
                }

                //Ativar o listener
                if(restauranteListener!=null)
                {
                    restaurantesListener.onRefreshListaRestaurantes(restaurantes);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{
                    Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                }catch (Exception ignored){
                    //Toast.makeText(contexto, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        });
        volleyQueue.add(req);
    }

    public void getAllRestaurantesSincronizadosAPI(final Context contexto) {
        if (!RestauranteJsonParser.isConnectionInternet(contexto)) {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }
        RequestFuture<JSONArray> requestFuture = RequestFuture.newFuture();

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIrestaurantes, new JSONArray(), requestFuture, requestFuture);

        volleyQueue.add(req);

        try {

            JSONArray restaurantesJSON = requestFuture.get(REQUEST_TIMEOUT, TimeUnit.SECONDS);
            restaurantes = RestauranteJsonParser.parserJsonRestaurante(restaurantesJSON);
        } catch (InterruptedException e) {
            // handle the error
        } catch (ExecutionException e) {
            // handle the error
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }


    public Restaurante getRestauranteDB(int id){
        Restaurante restaurante;

        Cursor cursor = restaurantesDB.getReadableDatabase().rawQuery("select 1 from restaurantes WHERE id = "+id, null);

        if (cursor.moveToFirst()) {
                // Assume every column is int
                restaurante = new Restaurante(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3),
                        cursor.getString(4), cursor.getInt(5), cursor.getInt(6));
        }
        else
            restaurante=null;
        return restaurante;
    }

    public void getRestauranteAPI(final Context contexto, final int id)
    {

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, mUrlAPIrestaurantes + "/" + id,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                restaurante = RestauranteJsonParser.parserJsonRestaurante(response);
                //adicionarUserBD(user);
            }
        } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        volleyQueue.add(req);
    }

    public void getCategoriasAPI(final Context contexto, final int id)
    {
        if(!RestauranteJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIrestaurantes + "/" + id + "/categorias",null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                categorias = CategoriaJsonParser.parserJsonCategorias(response);
                //adicionarRestaurantesBD(restaurantes);

                //Ativar o listener
                /*if(restauranteListener!=null)
                {
                    restaurantesListener.onRefreshListaRestaurantes(restaurantes);
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

    public void getHorarioAPI(final Context contexto, final int id)
    {
        if(!UserJsonParser.isConnectionInternet(contexto)){
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }
        Restaurante restaurante = getRestaurante(id);

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, mUrlAPIrestaurantes + "/" + restaurante.getId_horario() + "/horario",null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                restaurante.setHorario(RestauranteJsonParser.parserJsonHorario(response));
                //adicionarUserBD(user);
            }
        } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        volleyQueue.add(req);
    }

    public void getHorarioAPI(final Context contexto, final Restaurante restaurante)
    {
        if(!UserJsonParser.isConnectionInternet(contexto)){
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, mUrlAPIrestaurantes + "/" + restaurante.getId_horario() + "/horario",null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                HorarioFuncionamento horario = RestauranteJsonParser.parserJsonHorario(response);
                restaurante.setHorario(horario);
                //horarioDB.adicionarHorariosBD(horario);
            }
        } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        volleyQueue.add(req);
    }

    public ArrayList<Categoria> getCategorias(){
        return categorias;
    }



    public void setRestaurantesListener(RestaurantesListener restaurantesListener) {
        this.restaurantesListener = restaurantesListener;
    }

    public void setRestauranteListener(RestauranteListener restauranteListener) {
        this.restauranteListener = restauranteListener;
    }
};

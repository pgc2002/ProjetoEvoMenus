package amsi.dei.estg.ipleiria.evo_menu.Model;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;
//Class unica, nao se repete mas pode ser acedida.
import amsi.dei.estg.ipleiria.evo_menu.Model.Listeners.RestauranteListener;
import amsi.dei.estg.ipleiria.evo_menu.Model.Listeners.RestaurantesListener;
import amsi.dei.estg.ipleiria.evo_menu.R;
import amsi.dei.estg.ipleiria.evo_menu.Utils.RestauranteJsonParser;

public class SingletonGestorRestaurantes
{
        private final static String mUrlAPIrestaurantes = "http://192.168.1.65/ProjetoEvoMenus/projetofinal/backend/web/api/restaurante"; //link da api
        private RestauranteDBHelper restaurantesDB = null;
        private static SingletonGestorRestaurantes instancia = null;
        private ArrayList<Restaurante> restaurantes;
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
        }

        public ArrayList<Restaurante> getRestaurantesDB() {
            return restaurantes = restaurantesDB.getAllRestaurantesBD();
        }

        //Buscar os livros do ficheiro criado para a lista
        public void setLivros(ArrayList<Restaurante> lista) {
            this.restaurantes = lista;
        }

        public Restaurante getRestaurante(int id) {
            for (Restaurante restaurante : restaurantes) {
                return restaurante;
            }
            return null;
        }

        //adicionarlivrosapi
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
                    //Ativar o listener
                    if(restauranteListener!=null)
                    {
                        restaurantesListener.onRefreshListaRestaurantes(restaurantes);
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

        public void setRestaurantesListener(RestaurantesListener restaurantesListener) {
            this.restaurantesListener = restaurantesListener;
        }

        public void setRestauranteListener(RestauranteListener restauranteListener) {
            this.restauranteListener = restauranteListener;
        }
};

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
//import amsi.dei.estg.ipleiria.evo_menu.Listeners.MesaListener;
//import amsi.dei.estg.ipleiria.evo_menu.Listeners.MesasListener;
import amsi.dei.estg.ipleiria.evo_menu.Listeners.MesaListener;
import amsi.dei.estg.ipleiria.evo_menu.Listeners.MesasListener;
import amsi.dei.estg.ipleiria.evo_menu.R;
import amsi.dei.estg.ipleiria.evo_menu.UrlApi;
import amsi.dei.estg.ipleiria.evo_menu.Utils.MesasJsonParser;
import amsi.dei.estg.ipleiria.evo_menu.Utils.RestauranteJsonParser;


public class SingletonGestorMesas
{
    private final static String mUrlAPImesas = new UrlApi().getUrl() + "mesa"; //link da api
    private MesasDBHelper mesasDB = null;
    private static SingletonGestorMesas instancia = null;
    private ArrayList<Mesa> mesas;
    private static RequestQueue volleyQueue = null;
    private MesasListener mesasListener;
    private MesaListener mesaListener;

    //Verificar se ja existe ou nao
    public static synchronized SingletonGestorMesas getInstance(Context contexto) {
        if (instancia == null) {
            instancia = new SingletonGestorMesas(contexto);
            volleyQueue = Volley.newRequestQueue(contexto);
        }
        return instancia;
    }

    private SingletonGestorMesas(Context contexto) {
        mesas = new ArrayList<>();
        mesasDB = new MesasDBHelper(contexto);
    }

    public ArrayList<Mesa> getMesasDB() {
        return mesas = mesasDB.getAllMesasDB();
    }

    public void setMesas(ArrayList<Mesa> lista) {
        this.mesas = lista;
    }

    public Mesa getMesa(int id) {
        for (Mesa mesa : mesas) {
            return mesa;
        }
        return null;
    }

    //adicionarlivrosapi
    public void adicionarMesasDB(ArrayList<Mesa> lista) {
        mesasDB.removerAllMesasDB();
        for (Mesa mesa : lista) {
            adicionarMesaBD(mesa);
        }
    }

    //CRUD
    public void adicionarMesaBD(Mesa mesa) {
        /*Livro livrobd = livrosBD.adicionarLivroBD(livro);

        if(livrobd!=null)
        {
            livros.add(livrobd);
        }*/
        //Adicionar atravez da api
        mesasDB.adicionarMesasDB(mesa);
    }

    public void getAllRestaurantesAPI(final Context contexto)
    {
        if(!RestauranteJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPImesas, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                mesas = MesasJsonParser.parserJsonMesa(response);
                //adicionarMesasDB(mesas);
                //Ativar o listener
                if(mesaListener!=null)
                {
                    mesasListener.onRefreshListaMesas(mesas);
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

    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

    public void setMesasDB(MesasListener mesasListener) {
        this.mesasListener = mesasListener;
    }

    public void setMesaDB(MesaListener mesaListener) {
        this.mesaListener = mesaListener;
    }
}

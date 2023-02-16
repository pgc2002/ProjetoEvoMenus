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

import amsi.dei.estg.ipleiria.evo_menu.Listeners.ItemListener;
import amsi.dei.estg.ipleiria.evo_menu.Listeners.ItemsListener;
import amsi.dei.estg.ipleiria.evo_menu.R;
import amsi.dei.estg.ipleiria.evo_menu.UrlApi;
import amsi.dei.estg.ipleiria.evo_menu.Utils.ItemJsonParser;

public class SingletonGestorItems {
    private final static String urlAPI = new UrlApi().getUrl() + "item";
    private ItemDBHelper itemsDB = null;
    private static SingletonGestorItems instancia = null;

    private ArrayList<Item> items;
    private static RequestQueue volleyQueue = null;
    private ItemsListener itemsListener;
    private ItemListener itemListener;

    //Verificar se ja existe ou nao
    public static synchronized SingletonGestorItems getInstance(Context contexto) {
        if (instancia == null) {
            instancia = new SingletonGestorItems(contexto);
            volleyQueue = Volley.newRequestQueue(contexto);
        }
        return instancia;
    }

    private SingletonGestorItems(Context contexto) {
        items = new ArrayList<>();
        itemsDB = new ItemDBHelper(contexto);
    }

    public ArrayList<Item> getItemsDB() {
        return items = itemsDB.getAllItemsDB();
    }

    public Item getItem(int id) {
        for (Item item : items) {
            if(id == item.getId())
                return item;
        }
        return null;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    private void adicionarItemsDB(ArrayList<Item> lista) {
        itemsDB.removerAllItemsDB();
        for (Item item : lista) {
            adicionarItemDB(item);
        }
    }

    private void adicionarItemDB(Item item) {
        itemsDB.adicionarItemDB(item);
    }

    public void getAllItemsAPI(final Context contexto)
    {
        if(!ItemJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, urlAPI, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                items = ItemJsonParser.parserJsonItems(response);
                adicionarItemsDB(items);
                //Ativar o listener
                if(itemListener !=null)
                {
                    itemsListener.onRefreshListaItems(items);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{
                    Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                }catch (Exception ignored){
                    Toast.makeText(contexto, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        });
        volleyQueue.add(req);
    }

    public void setItemsListener(ItemsListener itemsListener) {
        this.itemsListener = itemsListener;
    }

    public void setItemListener(ItemListener itemsListener) {
        this.itemListener = itemsListener;
    }
}

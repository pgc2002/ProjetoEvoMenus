package amsi.dei.estg.ipleiria.evo_menu.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Model.Item;

public class ItemJsonParser {

    public static ArrayList<Item> parserJsonItems(JSONArray resposta)
    {
        ArrayList<Item> lista = new ArrayList<>();
        try {
            for(int i = 0; i < resposta.length(); i++)
            {
                JSONObject jsonItem = (JSONObject) resposta.get(i);
                int id = jsonItem.getInt("id");
                String nome = jsonItem.getString("nome");
                String fotografia = jsonItem.getString("fotografia");
                double preco = jsonItem.getDouble("preco");
                int idCategoria = jsonItem.getInt("idCategoria");

                Item Item = new Item(id, nome, fotografia, preco, idCategoria);
                lista.add(Item);
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return lista;
    }

    public static Item parserJsonItem(String resposta)
    {
        Item item = null;
        try {
            JSONObject jsonItem = new JSONObject(resposta);
            int id = jsonItem.getInt("id");
            String nome = jsonItem.getString("nome");
            String fotografia = jsonItem.getString("fotografia");
            double preco = jsonItem.getDouble("preco");
            int idCategoria = jsonItem.getInt("idCategoria");

            item = new Item(id, nome, fotografia, preco, idCategoria);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return item;
    }

    public static boolean isConnectionInternet(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}

package amsi.dei.estg.ipleiria.evo_menu.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Model.Menu;

public class MenuJsonParser {

    public static ArrayList<Menu> parserJsonMenus(JSONArray resposta)
    {
        ArrayList<Menu> lista = new ArrayList<>();
        try {
            for(int i = 0; i < resposta.length(); i++)
            {
                JSONObject jsonItem = (JSONObject) resposta.get(i);
                int id = jsonItem.getInt("id");
                String nome = jsonItem.getString("nome");
                String fotografia = jsonItem.getString("fotografia");
                double desconto = jsonItem.getDouble("desconto");
                int idCategoria = jsonItem.getInt("idCategoria");

                Menu menu = new Menu(id, nome, fotografia, desconto, idCategoria);
                lista.add(menu);
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return lista;
    }

    public static Menu parserJsonMenu(String resposta)
    {
        Menu menu = null;
        try {
            JSONObject jsonItem = new JSONObject(resposta);
            int id = jsonItem.getInt("id");
            String nome = jsonItem.getString("nome");
            String fotografia = jsonItem.getString("fotografia");
            double desconto = jsonItem.getDouble("desconto");
            int idCategoria = jsonItem.getInt("idCategoria");

            menu = new Menu(id, nome, fotografia, desconto, idCategoria);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return menu;
    }

    public static boolean isConnectionInternet(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}

package amsi.dei.estg.ipleiria.evo_menu.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Model.Categoria;

public class CategoriaJsonParser {

    public static ArrayList<Categoria> parserJsonCategorias(JSONArray resposta)
    {
        ArrayList<Categoria> lista = new ArrayList<>();
        try {
        for(int i = 0; i < resposta.length(); i++)
        {
            JSONObject jsonCategoria = (JSONObject) resposta.get(i);
            int id = jsonCategoria.getInt("id");
            String nome = jsonCategoria.getString("nome");
            int idRestaurante = jsonCategoria.getInt("idRestaurante");

            Categoria categoria = new Categoria(id, nome, idRestaurante);
            lista.add(categoria);
        }

        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return lista;
    }

    public static Categoria parserJsonCategoria(String resposta)
    {
        Categoria categoria = null;
        try {
            JSONObject jsonCategoria = new JSONObject(resposta);
            int id = jsonCategoria.getInt("id");
            String nome = jsonCategoria.getString("nome");
            int idRestaurante = jsonCategoria.getInt("idRestaurante");

            categoria = new Categoria(id, nome, idRestaurante);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return categoria;
    }

    public static boolean isConnectionInternet(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}

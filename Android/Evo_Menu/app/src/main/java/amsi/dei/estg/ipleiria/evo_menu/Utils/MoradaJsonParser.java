package amsi.dei.estg.ipleiria.evo_menu.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Model.Morada;

public class MoradaJsonParser {
    public static ArrayList<Morada> parserJsonMoradas(JSONArray resposta)
    {
        ArrayList<Morada> lista = new ArrayList<>();
        try {
            for(int i = 0; i < resposta.length(); i++)
            {
                JSONObject jsonMorada = (JSONObject) resposta.get(i);
                int id = jsonMorada.getInt("id");
                String pais = jsonMorada.getString("pais");
                String cidade = jsonMorada.getString("cidade");
                String rua = jsonMorada.getString("rua");
                String codpost = jsonMorada.getString("codpost");

                Morada morada = new Morada(id, pais, cidade, rua, codpost);
                lista.add(morada);
            }


        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return lista;
    }

    public static Morada parserJsonMorada(String resposta)
    {
        Morada morada = null;
        try {
            JSONObject jsonMorada = new JSONObject(resposta);
            int id = jsonMorada.getInt("id");
            String pais = jsonMorada.getString("pais");
            String cidade = jsonMorada.getString("cidade");
            String rua = jsonMorada.getString("rua");
            String codpost = jsonMorada.getString("codpost");

            morada = new Morada(id, pais, cidade, rua, codpost);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return morada;

    }

    public static boolean isConnectionInternet(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}

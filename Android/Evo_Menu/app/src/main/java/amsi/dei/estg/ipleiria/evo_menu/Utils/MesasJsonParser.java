package amsi.dei.estg.ipleiria.evo_menu.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Model.Mesa;
import amsi.dei.estg.ipleiria.evo_menu.Model.Restaurante;

public class MesasJsonParser
{
    public static ArrayList<Mesa> parserJsonMesa(JSONArray resposta)
    {
        ArrayList<Mesa> lista = new ArrayList<>();
        try {
            for(int i = 0; i < resposta.length(); i++)
            {
                JSONObject jsonMesa = (JSONObject) resposta.get(i);
                int id = jsonMesa.getInt("id");
                int numero = jsonMesa.getInt("numero");
                int capacidade = jsonMesa.getInt("capacidade");
                String estado = jsonMesa.getString("estado");
                int id_restaurante = jsonMesa.getInt("id_restaurante");
                Mesa mesa = new Mesa(id, numero, capacidade, estado, id_restaurante);
                lista.add(mesa);
            }


        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return lista;

    }

    public static Mesa parserJsonMesa(String resposta)
    {
        Mesa mesa = null;
        try {
            JSONObject jsonMesa = new JSONObject(resposta);
            int id = jsonMesa.getInt("id");
            int numero = jsonMesa.getInt("numero");
            int capacidade = jsonMesa.getInt("capacidade");
            String estado = jsonMesa.getString("estado");
            int id_restaurante = jsonMesa.getInt("id_restaurante");
            mesa = new Mesa(id, numero, capacidade, estado, id_restaurante);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mesa;

    }


    public static boolean isConnectionInternet(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}

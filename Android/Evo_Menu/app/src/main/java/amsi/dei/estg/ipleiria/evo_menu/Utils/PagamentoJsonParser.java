package amsi.dei.estg.ipleiria.evo_menu.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Model.Pagamento;

public class PagamentoJsonParser {
    public static ArrayList<Pagamento> parserJsonPagamentos(JSONArray resposta)
    {
        ArrayList<Pagamento> lista = new ArrayList<>();
        try {
            for(int i = 0; i < resposta.length(); i++)
            {
                JSONObject jsonPagamento = (JSONObject) resposta.get(i);
                int id = jsonPagamento.getInt("id");
                int idPedido = jsonPagamento.getInt("idPedido");
                long valor = jsonPagamento.getLong("valor");
                String metodo = jsonPagamento.getString("metodo");

                Pagamento user = new Pagamento(id, idPedido, valor, metodo);
                lista.add(user);
            }


        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return lista;
    }

    //POR FAZER

    public static Pagamento parserJsonPagamento(String resposta)
    {
        Pagamento pagamento = null;
        try {
            JSONObject jsonPagamento = new JSONObject(resposta);
            int id = jsonPagamento.getInt("id");
            int idPedido = jsonPagamento.getInt("idPedido");
            long valor = jsonPagamento.getLong("valor");
            String metodo = jsonPagamento.getString("metodo");

            pagamento = new Pagamento(id, idPedido, valor, metodo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pagamento;

    }

    public static String parserJsonLogin(String resposta)
    {
        String token = null;
        try {
            JSONObject jsonLogin = new JSONObject(resposta);
            if(jsonLogin.getBoolean("success"))
            {
                token = jsonLogin.getString("token");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return token;

    }

    public static boolean isConnectionInternet(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}

package amsi.dei.estg.ipleiria.evo_menu.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Model.Pedidos;

public class PedidoJsonParser {
    public static ArrayList<Pedidos> parserJsonPedidos(JSONArray resposta)
    {
        ArrayList<Pedidos> lista = new ArrayList<>();
        try {
            for(int i = 0; i < resposta.length(); i++)
            {
                JSONObject jsonPagamento = (JSONObject) resposta.get(i);
                int id = jsonPagamento.getInt("id");
                double valorTotal = jsonPagamento.getDouble("valorTotal");
                String estado = jsonPagamento.getString("estado");
                int id_cliente = jsonPagamento.getInt("idCliente");
                int id_restaurante = jsonPagamento.getInt("idRestaurante");

                Pedidos pedido = new Pedidos(id, valorTotal, estado, id_cliente, id_restaurante);
                lista.add(pedido);
            }


        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return lista;
    }

    //POR FAZER

    public static Pedidos parserJsonPedido(String resposta)
    {
        Pedidos pedido = null;
        try {
            JSONObject jsonPagamento = new JSONObject(resposta);
            int id = jsonPagamento.getInt("id");
            double valorTotal = jsonPagamento.getDouble("valorTotal");
            String estado = jsonPagamento.getString("estado");
            int id_cliente = jsonPagamento.getInt("idCliente");
            int id_restaurante = jsonPagamento.getInt("idRestaurante");

            pedido = new Pedidos(id, valorTotal, estado, id_cliente, id_restaurante);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pedido;

    }

    public static boolean isConnectionInternet(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}

package amsi.dei.estg.ipleiria.evo_menu.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Model.Users;

public class UserJsonParser {
    public static ArrayList<Users> parserJsonUsers(JSONArray resposta)
    {
        ArrayList<Users> lista = new ArrayList<>();
        try {
            for(int i = 0; i < resposta.length(); i++)
            {
                JSONObject jsonUser = (JSONObject) resposta.get(i);
                int id = jsonUser.getInt("id");
                String username = jsonUser.getString("username");
                String auth_key = jsonUser.getString("auth_key");
                String password_hash = jsonUser.getString("password_hash");
                String email = jsonUser.getString("email");
                String status = jsonUser.getString("status");
                long created_at = jsonUser.getLong("created_at");
                long updated_at = jsonUser.getLong("updated_at");
                String telemovel = jsonUser.getString("telemovel");
                String nif = jsonUser.getString("nif");
                String tipo = jsonUser.getString("tipo");
                String nome = jsonUser.getString("nome");
                int idRestaurante = jsonUser.getInt("idRestaurante");
                int idMorada = jsonUser.getInt("idMorada");
                int idMesa = jsonUser.getInt("idMesa");

                Users user = new Users(id, username, auth_key, password_hash, email, telemovel, nif, nome, idMorada, created_at, updated_at);
                lista.add(user);
            }


        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return lista;
    }

    //POR FAZER

    public static Users parserJsonUser(String resposta)
    {
        Users user = null;
        try {
            JSONObject jsonUser = new JSONObject(resposta);
            int id = jsonUser.getInt("id");
            String username = jsonUser.getString("username");
            String auth_key = jsonUser.getString("auth_key");
            String password_hash = jsonUser.getString("password_hash");
            String email = jsonUser.getString("email");
            String status = jsonUser.getString("status");
            long created_at = jsonUser.getLong("created_at");
            long updated_at = jsonUser.getLong("updated_at");
            String telemovel = jsonUser.getString("telemovel");
            String nif = jsonUser.getString("nif");
            String tipo = jsonUser.getString("tipo");
            String nome = jsonUser.getString("nome");
            int idRestaurante = jsonUser.getInt("idRestaurante");
            int idMorada = jsonUser.getInt("idMorada");
            int idMesa = jsonUser.getInt("idMesa");
            int ano = jsonUser.getInt("ano");
            String capa = jsonUser.getString("capa");

            user = new Users(id, username, auth_key, password_hash, email, telemovel, nif, nome, idMorada, created_at, updated_at);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;

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

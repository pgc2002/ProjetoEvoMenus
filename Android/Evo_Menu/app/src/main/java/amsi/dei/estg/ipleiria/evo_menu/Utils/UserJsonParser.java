package amsi.dei.estg.ipleiria.evo_menu.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Model.User;

public class UserJsonParser {
    public static ArrayList<User> parserJsonUsers(JSONArray resposta)
    {
        ArrayList<User> lista = new ArrayList<>();
        try {
            for(int i = 0; i < resposta.length(); i++)
            {
                JSONObject jsonUser = (JSONObject) resposta.get(i);
                int id = jsonUser.getInt("id");
                String username = jsonUser.getString("username");
                String password = jsonUser.getString("password_hash");
                String email = jsonUser.getString("email");
                String telemovel = jsonUser.getString("telemovel");
                String nif = jsonUser.getString("nif");
                String nome = jsonUser.getString("nome");
                int idMorada = jsonUser.getInt("idMorada");

                User user = new User(id, username, nome, password, email, telemovel, nif, idMorada);
                lista.add(user);
            }


        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return lista;
    }

    //POR FAZER

    public static User parserJsonUser(String resposta)
    {
        User user = null;
        try {
            JSONObject jsonUser = new JSONObject(resposta);
            int id = jsonUser.getInt("id");
            String username = jsonUser.getString("username");
            String password = jsonUser.getString("password_hash");
            String email = jsonUser.getString("email");
            String telemovel = jsonUser.getString("telemovel");
            String nif = jsonUser.getString("nif");
            String nome = jsonUser.getString("nome");
            int idMorada = jsonUser.getInt("idMorada");

            user = new User(id, username, nome, password, email, telemovel, nif, idMorada);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;

    }

    public static String parserJsonValidacao (String resposta) throws JSONException {
        return resposta;
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

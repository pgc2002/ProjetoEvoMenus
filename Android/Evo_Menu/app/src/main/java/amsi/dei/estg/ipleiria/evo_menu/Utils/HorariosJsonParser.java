package amsi.dei.estg.ipleiria.evo_menu.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Model.HorarioFuncionamento;

public class HorariosJsonParser
{
    public static ArrayList<HorarioFuncionamento> parserJsonHorarios(JSONArray resposta)
    {
        ArrayList<HorarioFuncionamento> lista = new ArrayList<>();
        try {
            for(int i = 0; i < resposta.length(); i++)
            {
                JSONObject jsonHorarios = (JSONObject) resposta.get(i);
                int id = jsonHorarios.getInt("id");
                String segunda_feira = jsonHorarios.getString("segunda_feira");
                String terca_feira = jsonHorarios.getString("terca_feira");
                String quarta_feira = jsonHorarios.getString("quarta_feira");
                String quinta_feira = jsonHorarios.getString("quinta_feira");
                String sexta_feira = jsonHorarios.getString("sexta_feira");
                String sabado = jsonHorarios.getString("sabado");
                String domingo = jsonHorarios.getString("domingo");

                HorarioFuncionamento horario = new HorarioFuncionamento(id, segunda_feira, terca_feira, quarta_feira, quinta_feira, sexta_feira, sabado, domingo);
                lista.add(horario);
            }


        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return lista;

    }

    public static HorarioFuncionamento pareserJsonHorario(String resposta)
    {
        HorarioFuncionamento horarioFuncionamento = null;
        try {
            JSONObject jsonHorarios = new JSONObject(resposta);
            int id = jsonHorarios.getInt("id");
            String segunda_feira = jsonHorarios.getString("segunda_feira");
            String terca_feira = jsonHorarios.getString("terca_feira");
            String quarta_feira = jsonHorarios.getString("quarta_feira");
            String quinta_feira = jsonHorarios.getString("quinta_feira");
            String sexta_feira = jsonHorarios.getString("sexta_feira");
            String sabado = jsonHorarios.getString("sabado");
            String domingo = jsonHorarios.getString("domingo");

            horarioFuncionamento = new HorarioFuncionamento(id, segunda_feira, terca_feira, quarta_feira, quinta_feira, sexta_feira, sabado, domingo);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return horarioFuncionamento;

    }


    public static boolean isConnectionInternet(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
package amsi.dei.estg.ipleiria.evo_menu.Model;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;
//Class unica, nao se repete mas pode ser acedida.
//import amsi.dei.estg.ipleiria.evo_menu.Listeners.HorarioListener;
//import amsi.dei.estg.ipleiria.evo_menu.Listeners.HorariosListener;
import amsi.dei.estg.ipleiria.evo_menu.Listeners.HorarioListener;
import amsi.dei.estg.ipleiria.evo_menu.Listeners.HorariosListener;
import amsi.dei.estg.ipleiria.evo_menu.R;
import amsi.dei.estg.ipleiria.evo_menu.Utils.HorariosJsonParser;

public class SingletonGestorHorarios
{
    private final static String mUrlAPIhorarios = "http://localhost/ProjetoEvoMenus/projetofinal/backend/web/api/horarios"; //link da api
    private HorarioDBHelper horariosDB = null;
    private static SingletonGestorHorarios instancia = null;
    private ArrayList<HorarioFuncionamento> horarios;
    private static RequestQueue volleyQueue = null;
    private HorariosListener horariosListener;
    private HorarioListener horarioListener;

    //Verificar se ja existe ou nao
    public static synchronized SingletonGestorHorarios getInstance(Context contexto) {
        if (instancia == null) {
            instancia = new SingletonGestorHorarios(contexto);
            volleyQueue = Volley.newRequestQueue(contexto);
        }
        return instancia;
    }

    private SingletonGestorHorarios(Context contexto) {
        horarios = new ArrayList<>();
        horariosDB = new HorarioDBHelper(contexto);
    }

    public ArrayList<HorarioFuncionamento> getHorariosDB() {
        return horarios = horariosDB.getAllHorariosDB();
    }

    //Buscar os livros do ficheiro criado para a lista
    public void setHorarios(ArrayList<HorarioFuncionamento> lista) {
        this.horarios = lista;
    }

    public HorarioFuncionamento getRestaurante(int id) {
        for (HorarioFuncionamento horarioFuncionamento : horarios) {
            return horarioFuncionamento;
        }
        return null;
    }

    //adicionarlivrosapi
    public void adicionarHorariosBD(ArrayList<HorarioFuncionamento> lista) {
        horariosDB.removerAllHorariosDB();
        for (HorarioFuncionamento horarioFuncionamento : lista) {
            adicionarHorariosBD(horarioFuncionamento);
        }
    }

    //CRUD
    public void adicionarHorariosBD(HorarioFuncionamento horarioFuncionamento) {
        /*Livro livrobd = livrosBD.adicionarLivroBD(livro);

        if(livrobd!=null)
        {
            livros.add(livrobd);
        }*/
        //Adicionar atravez da api
        horariosDB.adicionarHorariosBD(horarioFuncionamento);
    }




    public void getAllHorariosAPI(final Context contexto)
    {
        if(!HorariosJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIhorarios, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                horarios = HorariosJsonParser.parserJsonHorarios(response);
                adicionarHorariosBD(horarios);
                //Ativar o listener
                if(horarioListener!=null)
                {
                    horariosListener.onRefreshListaHorarios(horarios);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });
        volleyQueue.add(req);
    }





    public void setHorariosListener(HorariosListener horariosListener) {
        this.horarioListener = horarioListener;
    }

    public void setHorarioListeneer(HorarioListener horarioListener) {
        this.horarioListener = horarioListener;
    }



}

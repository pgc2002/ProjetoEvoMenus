package amsi.dei.estg.ipleiria.evo_menu.Model;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import amsi.dei.estg.ipleiria.evo_menu.Listeners.MoradaListener;
import amsi.dei.estg.ipleiria.evo_menu.Listeners.MoradasListener;
import amsi.dei.estg.ipleiria.evo_menu.R;
import amsi.dei.estg.ipleiria.evo_menu.UrlApi;
import amsi.dei.estg.ipleiria.evo_menu.Utils.MoradaJsonParser;

public class SingletonGestorMorada {
    private final static String mUrlAPImorada = new UrlApi().getUrl() + "morada";
    private MoradaBdHelper moradasBD = null;
    private static SingletonGestorMorada instancia = null;

    private ArrayList<Morada> moradas;

    public ArrayList<Morada> getMoradas() {
        return moradas;
    }
    private static RequestQueue volleyQueue = null;
    private MoradasListener moradasListener;
    private MoradaListener moradaListener;

    //Verificar se ja existe ou nao
    public static synchronized SingletonGestorMorada getInstance(Context contexto) {
        if (instancia == null) {
            instancia = new SingletonGestorMorada(contexto);
            volleyQueue = Volley.newRequestQueue(contexto);
        }
        return instancia;
    }

    private SingletonGestorMorada(Context contexto) {
        moradas = new ArrayList<>();
        moradasBD = new MoradaBdHelper(contexto);
    }

    public ArrayList<Morada> getMoradasBD() {
        return moradas = moradasBD.getAllMoradasBD();
    }

    //Buscar os livros do ficheiro criado para a lista
    public void setMoradas(ArrayList<Morada> lista) {
        this.moradas = lista;
    }

    public Morada getMorada(long id) {
        for (Morada morada : moradas) {
            return morada;
        }
        return null;
    }

    //adicionarlivrosapi
    public void adicionarMoradasBD(ArrayList<Morada> lista) {
        moradasBD.removerAllMoradasBD();
        for (Morada morada : lista) {
            adicionarMoradaBD(morada);
        }
    }

    //CRUD
    public void adicionarMoradaBD(Morada morada) {

        //Adicionar atravez da api
        moradasBD.adicionarMoradaBD(morada);
    }

    public void removerMoradaBD(long id) {
        Morada morada = getMorada(id);
        if (morada != null) {
            moradasBD.removerMoradaBD(morada.getId());
            /*if(livrosBD.removerLivroBD(livro.getId()))
                livros.remove(livro);*/
        }

    }

    public void editarMoradaBD(Morada dadosMorada) {
        Morada morada = getMorada(dadosMorada.getId());
        if (morada != null) {
            /*(livrosBD.editarLivroBD(dadosLivro)) {
                livro.setTitulo(dadosLivro.getTitulo());
                livro.setAutor(dadosLivro.getAutor());
                livro.setAno(dadosLivro.getAno());
                livro.setSerie(dadosLivro.getSerie());
                livro.setCapa(dadosLivro.getCapa());*/
            moradasBD.editarMoradaBD(dadosMorada);

        }
    }


    //pedidos a api
    public void adicionarMoradaAPI(final Morada morada, final Context contexto, String token)
    {
        if(!MoradaJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT);
            return;
        }
        StringRequest request = new StringRequest(Request.Method.POST, mUrlAPImorada, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                adicionarMoradaBD(MoradaJsonParser.parserJsonMorada(response));
                //ativar o listener...
                /*
                if(moradaListener != null)
                {
                    moradaListener.onRefreshDetalhes(DetalhesLivroActivity.OP_CODE_ADICIONAR);
                }*/

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("pais", morada.getPais());
                params.put("cidade", morada.getCidade());
                params.put("rua", morada.getRua());
                params.put("codpost", morada.getCodpost());

                return params;

            };
        };
        volleyQueue.add(request);
    }

    public void getAllMoradasAPI(final Context contexto)
    {
        if(!MoradaJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPImorada, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                moradas = MoradaJsonParser.parserJsonMoradas(response);
                //adicionarMoradasBD(moradas);
                //Ativar o listener
                /*if(moradaListener!=null)
                {
                    moradasListener.onRefreshListaMoradas(moradas);
                }*/
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

    public void removerMoradaAPI(final Morada morada, final Context contexto)
    {
        if(!MoradaJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest req = new StringRequest(Request.Method.DELETE, mUrlAPImorada + '/' + morada.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                removerMoradaBD(morada.getId());
                //ativar listener
                /*if(moradaListener != null)
                {
                    moradaListener.onRefreshDetalhes(DetalhesLivroActivity.OP_CODE_APAGAR);
                }*/
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

    public void editarMoradaAPI(final Morada morada, Context contexto)
    {
        if(!MoradaJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT);
            return;
        }
        StringRequest request = new StringRequest(Request.Method.PUT, mUrlAPImorada + '/' + morada.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                editarMoradaBD(morada);
                //ativar o listener...
                /*
                if(moradaListener != null)
                {
                    moradaListener.onRefreshDetalhes(DetalhesLivroActivity.OP_CODE_EDITAR);
                }*/

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("pais", morada.getPais());
                params.put("cidade", morada.getCidade());
                params.put("rua", morada.getRua());
                params.put("codpost", morada.getCodpost());

                return params;

            };
        };
        volleyQueue.add(request);
    }

    public void setMoradasListener(MoradasListener moradasListener) {
        this.moradasListener = moradasListener;
    }

    public void setMoradaListener(MoradaListener moradaListener) {
        this.moradaListener = moradaListener;
    }
}

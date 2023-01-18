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

import amsi.dei.estg.ipleiria.evo_menu.Listeners.CategoriaListener;
import amsi.dei.estg.ipleiria.evo_menu.Listeners.CategoriasListener;
import amsi.dei.estg.ipleiria.evo_menu.R;
import amsi.dei.estg.ipleiria.evo_menu.Utils.CategoriaJsonParser;

public class SingletonGestorCategorias {
    private final static String urlAPI = "http://localhost/ProjetoEvoMenus/projetofinal/backend/web/api/categoria";
    private CategoriaDBHelper categoriasDB = null;
    private static SingletonGestorCategorias instancia = null;

    private ArrayList<Categoria> categorias;
    private static RequestQueue volleyQueue = null;
    private CategoriasListener categoriasListener;
    private CategoriaListener categoriaListener;

    //Verificar se ja existe ou nao
    public static synchronized SingletonGestorCategorias getInstance(Context contexto) {
        if (instancia == null) {
            instancia = new SingletonGestorCategorias(contexto);
            volleyQueue = Volley.newRequestQueue(contexto);
        }
        return instancia;
    }

    private SingletonGestorCategorias(Context contexto) {
        categorias = new ArrayList<>();
        categoriasDB = new CategoriaDBHelper(contexto);
    }

    public ArrayList<Categoria> getCategoriasDB() {
        return categorias = categoriasDB.getAllCategoriasDB();
    }

    public Categoria getCategoria(int id) {
        for (Categoria categoria : categorias) {
            if(id == categoria.getId())
                return categoria;
        }
        return null;
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    private void adicionarCategoriasDB(ArrayList<Categoria> lista) {
        categoriasDB.removerAllCategoriasDB();
        for (Categoria categoria : lista) {
            adicionarCategoriaDB(categoria);
        }
    }

    private void adicionarCategoriaDB(Categoria categoria) {
        categoriasDB.adicionarCategoriaDB(categoria);
    }

    public void getAllCategoriasAPI(final Context contexto)
    {
        if(!CategoriaJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, urlAPI, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                categorias = CategoriaJsonParser.parserJsonCategorias(response);
                adicionarCategoriasDB(categorias);
                //Ativar o listener
                if(categoriaListener !=null)
                {
                    categoriasListener.onRefreshListaCategorias(categorias);
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

    public void setCategoriasListener(CategoriasListener categoriasListener) {
        this.categoriasListener = categoriasListener;
    }

    public void setCategoriaListener(CategoriaListener categoriaListener) {
        this.categoriaListener = categoriaListener;
    }

}

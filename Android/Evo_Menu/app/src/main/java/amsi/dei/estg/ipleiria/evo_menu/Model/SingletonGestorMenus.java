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

import amsi.dei.estg.ipleiria.evo_menu.Listeners.MenuListener;
import amsi.dei.estg.ipleiria.evo_menu.Listeners.MenusListener;
import amsi.dei.estg.ipleiria.evo_menu.R;
import amsi.dei.estg.ipleiria.evo_menu.Utils.MenuJsonParser;

public class SingletonGestorMenus {
    private final static String urlAPI = "http://localhost/ProjetoEvoMenus/projetofinal/backend/web/api/menu";
    private MenuDBHelper menusDB = null;
    private static SingletonGestorMenus instancia = null;

    private ArrayList<Menu> menus;
    private static RequestQueue volleyQueue = null;
    private MenusListener menusListener;
    private MenuListener menuListener;
    
    //Verificar se ja existe ou nao
    public static synchronized SingletonGestorMenus getInstance(Context contexto) {
        if (instancia == null) {
            instancia = new SingletonGestorMenus(contexto);
            volleyQueue = Volley.newRequestQueue(contexto);
        }
        return instancia;
    }

    private SingletonGestorMenus(Context contexto) {
        menus = new ArrayList<>();
        menusDB = new MenuDBHelper(contexto);
    }
    
    public ArrayList<Menu> getMenusDB() {
        return menus = menusDB.getAllMenusDB();
    }

    public Menu getMenu(int id) {
        for (Menu menu : menus) {
            if(id == menu.getId())
                return menu;
        }
        return null;
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    private void adicionarMenusDB(ArrayList<Menu> lista) {
        menusDB.removerAllMenusDB();
        for (Menu menu : lista) {
            adicionarMenuDB(menu);
        }
    }

    private void adicionarMenuDB(Menu menu) {menusDB.adicionarMenuDB(menu);}

    public void getAllMenusAPI(final Context contexto)
    {
        if(!MenuJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, urlAPI, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                menus = MenuJsonParser.parserJsonMenus(response);
                adicionarMenusDB(menus);
                //Ativar o listener
                if(menuListener !=null)
                {
                    menusListener.onRefreshListaCategorias(menus);
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

    public void setMenusListener(MenusListener menusListener) {
        this.menusListener = menusListener;
    }

    public void setMenuListener(MenuListener menuListener) {
        this.menuListener = menuListener;
    }
}
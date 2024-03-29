package amsi.dei.estg.ipleiria.evo_menu.Model;

import android.content.Context;
import android.util.Log;
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
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import amsi.dei.estg.ipleiria.evo_menu.Listeners.PedidoListener;
import amsi.dei.estg.ipleiria.evo_menu.Listeners.PedidosListener;
import amsi.dei.estg.ipleiria.evo_menu.R;
import amsi.dei.estg.ipleiria.evo_menu.UrlApi;
import amsi.dei.estg.ipleiria.evo_menu.Utils.PedidoJsonParser;
import amsi.dei.estg.ipleiria.evo_menu.Utils.UserJsonParser;

public class SingletonGestorPedidos {
    private final static String mUrlAPIpedido = new UrlApi().getUrl() + "pedido";
    private PedidoBdHelper pedidoBD = null;
    private static SingletonGestorPedidos instancia = null;
    private ArrayList<Pedido> pedidos;
    private int numItems;
    private int numMenus;

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    private static RequestQueue volleyQueue = null;
    private PedidosListener pedidosListener;
    private PedidoListener pedidoListener;

    private ArrayList<Integer> idItensPedido;
    private ArrayList<Integer> idMenusPedido;
    private float valorTotal;
    private int idPedido;

    private boolean isPedidoSent;

    //Verificar se ja existe ou nao
    public static synchronized SingletonGestorPedidos getInstance(Context contexto) {
        if (instancia == null) {
            instancia = new SingletonGestorPedidos(contexto);
            volleyQueue = Volley.newRequestQueue(contexto);
        }
        return instancia;
    }

    private SingletonGestorPedidos(Context contexto) {
        pedidos = new ArrayList<>();
        pedidoBD = new PedidoBdHelper(contexto);
        valorTotal = 0;
        isPedidoSent = false;
    }

    public ArrayList<Pedido> getPedidosBD() {
        return pedidos = pedidoBD.getAllPedidosBD();
    }

    //Buscar os users do ficheiro criado para a lista
    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Pedido getPedido(long id) {
        for (Pedido pedido : pedidos) {
            return pedido;
        }
        return null;
    }

    public void adicionarPedidosBD(ArrayList<Pedido> pedidos) {
        pedidoBD.removerAllPedidosBD();
        for (Pedido pedido : pedidos) {
            adicionarPedidoBD(pedido);
        }
    }

    //CRUD
    public void adicionarPedidoBD(Pedido pedido) {
        /*Livro livrobd = livrosBD.adicionarLivroBD(livro);

        if(livrobd!=null)
        {
            livros.add(livrobd);
        }*/
        //Adicionar atravez da api
        pedidoBD.adicionarPedidoBD(pedido);
    }

    /*public void removerPedidoBD(long id) {
        Pedido pedido = getPedido(id);
        if (pedido != null) {
            pedidoBD.removerPedidoBD(pedido.getId());
            if(livrosBD.removerLivroBD(livro.getId()))
                livros.remove(livro);
        }

    }

    public void editarPedidoBD(Pedido dadosPedido) {
        Pedido pedido = getPedido(dadosPedido.getId());
        if (pedido != null) {
            (livrosBD.editarLivroBD(dadosLivro)) {
                livro.setTitulo(dadosLivro.getTitulo());
                livro.setAutor(dadosLivro.getAutor());
                livro.setAno(dadosLivro.getAno());
                livro.setSerie(dadosLivro.getSerie());
                livro.setCapa(dadosLivro.getCapa());
            pedidoBD.editarPedidoBD(dadosPedido);
        }
    }*/


    //pedidos a api
    public void adicionarPedidoAPI(final Pedido pedido, final Context contexto)
    {
        if(!PedidoJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT);
            return;
        }
        StringRequest request = new StringRequest(Request.Method.POST, mUrlAPIpedido +
                "/criar?valorTotal=" + pedido.getValor_total() + "&estado=" + pedido.getEstado() + "&idCliente=" + pedido.getId_cliente() + "&idRestaurante=" + pedido.getId_restaurante(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //adicionarPedidoBD(PedidoJsonParser.parserJsonPedido(response));
                //ativar o listener...
                /*if(livroListener != null)
                {
                    livroListener.onRefreshDetalhes(DetalhesLivroActivity.OP_CODE_ADICIONAR);
                }*/

                idPedido = Integer.parseInt(PedidoJsonParser.parserJsonIdPedido(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });
        volleyQueue.add(request);
    }

    //pedidos a api
    public void adicionarItemPedidoAPI(final int idItem, final Context contexto)
    {
        if(!PedidoJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT);
            return;
        }
        StringRequest request = new StringRequest(Request.Method.POST, mUrlAPIpedido +
                "/inserir_item?idPedido=" + idPedido + "&idItem=" + idItem, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //adicionarPedidoBD(PedidoJsonParser.parserJsonPedido(response));
                //ativar o listener...
                /*if(livroListener != null)
                {
                    livroListener.onRefreshDetalhes(DetalhesLivroActivity.OP_CODE_ADICIONAR);
                }*/

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });
        volleyQueue.add(request);
    }

    public void adicionarMenuPedidoAPI(final int idMenu, final Context contexto)
    {
        if(!PedidoJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT);
            return;
        }
        StringRequest request = new StringRequest(Request.Method.POST, mUrlAPIpedido +
                "/inserir_menu?idPedido=" + idPedido + "&idMenu=" + idMenu, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //adicionarPedidoBD(PedidoJsonParser.parserJsonPedido(response));
                //ativar o listener...
                /*if(livroListener != null)
                {
                    livroListener.onRefreshDetalhes(DetalhesLivroActivity.OP_CODE_ADICIONAR);
                }*/

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });
        volleyQueue.add(request);
    }

    /*public void getAllPedidosAPI(final Context contexto)
    {
        if(!PedidoJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIpedido + "/user/"+SingletonGestorUsers.getInstance(contexto).getUserLogado().getId(), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                pedidos = PedidoJsonParser.parserJsonPedidos(response);

                //adicionarPedidosBD(pedidos);

                //Ativar o listener
                if(pedidoListener!=null)
                {
                    pedidosListener.onRefreshListaPedidos(pedidos);
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
    }*/

    public void getAllItensPedidoAPI(final Context contexto, final int i)
    {
        if(!PedidoJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIpedido + "/" + idPedido + "/items", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null) {
                    for (Pedido pedido : pedidos) {
                        pedido.setIdItensPedido(PedidoJsonParser.parserJsonItemIds(response));
                    }
                }
                //adicionarPedidosBD(pedidos);
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

    public void getAllMenusPedidoAPI(final Context contexto, final int i)
    {
        if(!PedidoJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIpedido + "/" + idPedido + "/menus", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null) {
                    for (Pedido pedido : pedidos) {
                        if (response != null)
                            pedido.setIdMenusPedido(PedidoJsonParser.parserJsonItemIds(response));
                    }
                }
                //adicionarPedidosBD(pedidos);
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

    public void getCountItemsAPI(int id, final Context contexto)
    {
        if(!PedidoJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest req = new StringRequest(Request.Method.DELETE, mUrlAPIpedido + '/' + id + "/countitems", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                numItems = Integer.parseInt(response);
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

    public void getCountMenusAPI(int id, final Context contexto)
    {
        if(!PedidoJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest req = new StringRequest(Request.Method.DELETE, mUrlAPIpedido + '/' + id + "/countmenus", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                numMenus = Integer.parseInt(response);
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

    public void getAllPedidosAPI(final Context contexto)
    {
        if(!PedidoJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIpedido + "/user/"+SingletonGestorUsers.getInstance(contexto).getUserLogado().getId(), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                pedidos = PedidoJsonParser.parserJsonPedidos(response);

                adicionarPedidosBD(pedidos);

                //Ativar o listener
                if(pedidoListener!=null)
                {
                    pedidosListener.onRefreshListaPedidos(pedidos);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{
                    Toast.makeText(contexto, error.getMessage(), Toast.LENGTH_SHORT).show();
                }catch (Exception ignored){}
                return;
            }
        });
        volleyQueue.add(req);
    }

    /*public void getAllPedidosAPI(final Context contexto, int idUser)
    {
        if(!PedidoJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIpedido, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Pedido> pedidosPorFiltrar = PedidoJsonParser.parserJsonPedidos(response);

                for (Pedido pedido : pedidosPorFiltrar) {
                    if(pedido.getId_cliente() == idUser)
                        pedidos.add(pedido);
                }

                //adicionarPedidosBD(pedidosFiltrados);

                //Ativar o listener
                if(pedidoListener!=null)
                {
                    pedidosListener.onRefreshListaPedidos(pedidos);
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
    }*/

    /*public void removerPedidoAPI(final Pedido pedido, final Context contexto)
    {
        if(!PedidoJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest req = new StringRequest(Request.Method.DELETE, mUrlAPIpedido + '/' + pedido.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                removerPedidoBD(pedido.getId());
                //ativar listener
                if(livroListener != null)
                {
                    livroListener.onRefreshDetalhes(DetalhesLivroActivity.OP_CODE_APAGAR);
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

    public void editarPedidoAPI(final Pedido pedido, Context contexto, final String token)
    {
        if(!PedidoJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT);
            return;
        }
        StringRequest request = new StringRequest(Request.Method.PUT, mUrlAPIpedido + '/' + pedido.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                editarPedidoBD(pedido);
                //ativar o listener...
                if(livroListener != null)
                {
                    livroListener.onRefreshDetalhes(DetalhesLivroActivity.OP_CODE_EDITAR);
                }

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
                params.put("id", "" + pedido.getId());
                params.put("valorTotal", "" + pedido.getValor_total());
                params.put("estado", pedido.getEstado());
                params.put("idCliente", "" + pedido.getId_cliente());
                params.put("idRestaurante", "" + pedido.getId_restaurante());

                return params;

            };
        };
        volleyQueue.add(request);
    }*/

    public void setPedidosListener(PedidosListener pedidosListener) {
        this.pedidosListener = pedidosListener;
    }

    public void setPedidoListener(PedidoListener pedidoListener) {
        this.pedidoListener = pedidoListener;
    }

    public ArrayList<Integer> getIdItensPedido() {
        return idItensPedido;
    }

    public void setIdItensPedido(ArrayList<Integer> idItensPedido) {
        this.idItensPedido = idItensPedido;
    }

    public ArrayList<Integer> getIdMenusPedido() {
        return idMenusPedido;
    }

    public void setIdMenusPedido(ArrayList<Integer> idMenusPedido) {
        this.idMenusPedido = idMenusPedido;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public boolean getIsPedidoSent() {
        return isPedidoSent;
    }

    public void setIsPedidoSent(boolean b) {
        this.isPedidoSent = b;
    }

    public int getNumItems() {
        return numItems;
    }

    public int getNumMenus() {
        return numMenus;
    }
}

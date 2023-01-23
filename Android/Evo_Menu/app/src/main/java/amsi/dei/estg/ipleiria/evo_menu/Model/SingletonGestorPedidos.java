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

import amsi.dei.estg.ipleiria.evo_menu.Listeners.PedidoListener;
import amsi.dei.estg.ipleiria.evo_menu.Listeners.PedidosListener;
import amsi.dei.estg.ipleiria.evo_menu.R;
import amsi.dei.estg.ipleiria.evo_menu.UrlApi;
import amsi.dei.estg.ipleiria.evo_menu.Utils.PedidoJsonParser;

public class SingletonGestorPedidos {
    private final static String mUrlAPIpedido = new UrlApi().getUrl() + "pedido";
    private PedidoBdHelper pedidoBD = null;
    private static SingletonGestorPedidos instancia = null;
    private ArrayList<Pedido> pedidos;

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    private static RequestQueue volleyQueue = null;
    private PedidosListener pedidosListener;
    private PedidoListener pedidoListener;

    private ArrayList<Integer> idItensPedido;
    private ArrayList<Integer> idMenusPedido;

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
        StringRequest request = new StringRequest(Request.Method.POST, mUrlAPIpedido, new Response.Listener<String>() {
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
                //params.put("capa", livro.getCapa() == null? DetalhesLivroActivity.IMG_DEFAULT : livro.getCapa());

                return params;
            };
        };
        volleyQueue.add(request);


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
    }

    /*public void getAllPedidosUserAPI(final Context contexto, int idUser)
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
}

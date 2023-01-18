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

import amsi.dei.estg.ipleiria.evo_menu.R;
import amsi.dei.estg.ipleiria.evo_menu.Utils.PedidoJsonParser;

public class SingletonGestorPedido {
    private final static String mUrlAPIpedido = "http://localhost/ProjetoEvoMenus/projetofinal/backend/web/api/pedido";
    private PedidoBdHelper pedidoBD = null;
    private static SingletonGestorPedido instancia = null;
    private ArrayList<Pedidos> pedidos;
    private static RequestQueue volleyQueue = null;
    /*private LivrosListener livrosListener;
    private LivroListener livroListener;
    private LoginListener loginListener;*/


    //Verificar se ja existe ou nao
    public static synchronized SingletonGestorPedido getInstance(Context contexto) {
        if (instancia == null) {
            instancia = new SingletonGestorPedido(contexto);
            volleyQueue = Volley.newRequestQueue(contexto);
        }
        return instancia;
    }

    private SingletonGestorPedido(Context contexto) {
        pedidos = new ArrayList<>();
        pedidoBD = new PedidoBdHelper(contexto);
    }

    public ArrayList<Pedidos> getPedidosBD() {
        return pedidos = pedidoBD.getAllPedidosBD();
    }

    //Buscar os users do ficheiro criado para a lista
    public void setPagamentos(ArrayList<Pedidos> pedidos) {
        this.pedidos = pedidos;
    }

    public Pedidos getPedido(long id) {
        for (Pedidos pedido : pedidos) {
            return pedido;
        }
        return null;
    }

    //adicionarlivrosapi
    public void adicionarPedidosBD(ArrayList<Pedidos> pedidos) {
        pedidoBD.removerAllPedidosBD();
        for (Pedidos pedido : pedidos) {
            adicionarPedidoBD(pedido);
        }
    }

    //CRUD
    public void adicionarPedidoBD(Pedidos pedido) {
        /*Livro livrobd = livrosBD.adicionarLivroBD(livro);

        if(livrobd!=null)
        {
            livros.add(livrobd);
        }*/
        //Adicionar atravez da api
        pedidoBD.adicionarPedidoBD(pedido);
    }

    public void removerPedidoBD(long id) {
        Pedidos pedido = getPedido(id);
        if (pedido != null) {
            pedidoBD.removerPedidoBD(pedido.getId());
            /*if(livrosBD.removerLivroBD(livro.getId()))
                livros.remove(livro);*/
        }

    }

    public void editarPedidoBD(Pedidos dadosPedido) {
        Pedidos pedido = getPedido(dadosPedido.getId());
        if (pedido != null) {
            /*(livrosBD.editarLivroBD(dadosLivro)) {
                livro.setTitulo(dadosLivro.getTitulo());
                livro.setAutor(dadosLivro.getAutor());
                livro.setAno(dadosLivro.getAno());
                livro.setSerie(dadosLivro.getSerie());
                livro.setCapa(dadosLivro.getCapa());*/
            pedidoBD.editarPedidoBD(dadosPedido);

        }
    }


    //pedidos a api
    public void adicionarPedidoAPI(final Pedidos pedido, final Context contexto, String token)
    {
        if(!PedidoJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT);
            return;
        }
        StringRequest request = new StringRequest(Request.Method.POST, mUrlAPIpedido, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                adicionarPedidoBD(PedidoJsonParser.parserJsonPedido(response));
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
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIpedido, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                pedidos = PedidoJsonParser.parserJsonPedidos(response);
                adicionarPedidosBD(pedidos);
                //Ativar o listener
                /*if(livroListener!=null)
                {
                    livrosListener.onRefreshListaLivros(livros);
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

    public void removerPedidoAPI(final Pedidos pedido, final Context contexto)
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
                /*if(livroListener != null)
                {
                    livroListener.onRefreshDetalhes(DetalhesLivroActivity.OP_CODE_APAGAR);
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

    public void editarPedidoAPI(final Pedidos pedido, Context contexto, final String token)
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
                /*if(livroListener != null)
                {
                    livroListener.onRefreshDetalhes(DetalhesLivroActivity.OP_CODE_EDITAR);
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

                return params;

            };
        };
        volleyQueue.add(request);
    }

    /*
    public void setLivrosListener(LivrosListener livrosListener) {
        this.livrosListener = livrosListener;
    }

    public void setLivroListener(LivroListener livroListener) {
        this.livroListener = livroListener;
    }
    */

    /*
    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }
    */
}
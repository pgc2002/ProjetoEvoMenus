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

import amsi.dei.estg.ipleiria.evo_menu.Model.Listeners.PagamentoListener;
import amsi.dei.estg.ipleiria.evo_menu.Model.Listeners.PagamentosListener;
import amsi.dei.estg.ipleiria.evo_menu.R;
import amsi.dei.estg.ipleiria.evo_menu.Utils.PagamentoJsonParser;

public class SingletonGestorPagamentos {
    private final static String mUrlAPIpagamento = "http://localhost/ProjetoEvoMenus/projetofinal/backend/web/api/pagamento";
    private PagamentoBdHelper pagamentoBD = null;
    private static SingletonGestorPagamentos instancia = null;
    private ArrayList<Pagamento> pagamentos;
    private static RequestQueue volleyQueue = null;
    private PagamentosListener pagamentosListener;
    private PagamentoListener pagamentoListener;


    //Verificar se ja existe ou nao
    public static synchronized SingletonGestorPagamentos getInstance(Context contexto) {
        if (instancia == null) {
            instancia = new SingletonGestorPagamentos(contexto);
            volleyQueue = Volley.newRequestQueue(contexto);
        }
        return instancia;
    }

    private SingletonGestorPagamentos(Context contexto) {
        pagamentos = new ArrayList<>();
        pagamentoBD = new PagamentoBdHelper(contexto);
    }

    public ArrayList<Pagamento> getPagamentosBD() {
        return pagamentos = pagamentoBD.getAllPagamentosBD();
    }

    //Buscar os users do ficheiro criado para a lista
    public void setPagamentos(ArrayList<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public Pagamento getPagamento(long id) {
        for (Pagamento pagamento : pagamentos) {
            return pagamento;
        }
        return null;
    }

    //adicionarlivrosapi
    public void adicionarPagamentosBD(ArrayList<Pagamento> pagamentos) {
        pagamentoBD.removerAllPagamentosBD();
        for (Pagamento pagamento : pagamentos) {
            adicionarPagamentoBD(pagamento);
        }
    }

    //CRUD
    public void adicionarPagamentoBD(Pagamento pagamento) {
        /*Livro livrobd = livrosBD.adicionarLivroBD(livro);

        if(livrobd!=null)
        {
            livros.add(livrobd);
        }*/
        //Adicionar atravez da api
        pagamentoBD.adicionarPagamentoBD(pagamento);
    }

    /*public void removerPagamentoBD(long id) {
        Pagamento pagamento = getPagamento(id);
        if (pagamento != null) {
            pagamentoBD.removerPagamentoBD(pagamento.getId());
            /f(livrosBD.removerLivroBD(livro.getId()))
                livros.remove(livro);
        }

    }

    public void editarPagamentoBD(Pagamento dadosPagamento) {
        Pagamento pagamento = getPagamento(dadosPagamento.getId());
        if (pagamento != null) {
            (livrosBD.editarLivroBD(dadosLivro)) {
                livro.setTitulo(dadosLivro.getTitulo());
                livro.setAutor(dadosLivro.getAutor());
                livro.setAno(dadosLivro.getAno());
                livro.setSerie(dadosLivro.getSerie());
                livro.setCapa(dadosLivro.getCapa());
            pagamentoBD.editarPagamentoBD(dadosPagamento);
        }
    }*/

    //pedidos a api
    public void adicionarPagamentoAPI(final Pagamento pagamento, final Context contexto, String token)
    {
        if(!PagamentoJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT);
            return;
        }
        StringRequest request = new StringRequest(Request.Method.POST, mUrlAPIpagamento, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                adicionarPagamentoBD(PagamentoJsonParser.parserJsonPagamento(response));
                //ativar o listener...
                /*if(pagamentoListener != null)
                {
                    pagamentoListener.onRefreshDetalhes(DetalhesLivroActivity.OP_CODE_ADICIONAR);
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
                params.put("id", "" + pagamento.getId());
                params.put("idPedido", "" + pagamento.getIdPedido());
                params.put("valor", "" + pagamento.getValor());
                params.put("metodo", pagamento.getMetodo());
                //params.put("capa", livro.getCapa() == null? DetalhesLivroActivity.IMG_DEFAULT : livro.getCapa());

                return params;
            };
        };
        volleyQueue.add(request);
    }

    public void getAllPagamentosAPI(final Context contexto)
    {
        if(!PagamentoJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIpagamento, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                pagamentos = PagamentoJsonParser.parserJsonPagamentos(response);
                adicionarPagamentosBD(pagamentos);
                //Ativar o listener
                if(pagamentoListener!=null)
                {
                    pagamentosListener.onRefreshListaPagamentos(pagamentos);
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

    /*public void removerPagamentoAPI(final Pagamento pagamento, final Context contexto)
    {
        if(!PagamentoJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest req = new StringRequest(Request.Method.DELETE, mUrlAPIpagamento + '/' + pagamento.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                removerPagamentoBD(pagamento.getId());
                //ativar listener
                if(pagamentoListener != null)
                {
                    pagamentoListener.onRefreshDetalhes(DetalhesLivroActivity.OP_CODE_APAGAR);
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

    /*public void editarPagamentoAPI(final Pagamento pagamento, Context contexto, final String token)
    {
        if(!PagamentoJsonParser.isConnectionInternet(contexto))
        {
            Toast.makeText(contexto, R.string.no_internet, Toast.LENGTH_SHORT);
            return;
        }
        StringRequest request = new StringRequest(Request.Method.PUT, mUrlAPIpagamento + '/' + pagamento.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                editarPagamentoBD(pagamento);
                //ativar o listener...
                if(pagamentoListener != null)
                {
                    pagamentoListener.onRefreshDetalhes(DetalhesLivroActivity.OP_CODE_EDITAR);
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
                params.put("id", "" + pagamento.getId());
                params.put("idPedido", "" + pagamento.getIdPedido());
                params.put("valor", "" + pagamento.getValor());
                params.put("metodo", pagamento.getMetodo());

                return params;

            };
        };
        volleyQueue.add(request);
    }*/

    public void setPagamentosListener(PagamentosListener pagamentosListener) {
        this.pagamentosListener = pagamentosListener;
    }

    public void setPagamentoListener(PagamentoListener pagamentoListener) {
        this.pagamentoListener = pagamentoListener;
    }
}

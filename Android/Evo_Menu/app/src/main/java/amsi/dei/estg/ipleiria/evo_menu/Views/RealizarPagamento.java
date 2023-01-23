package amsi.dei.estg.ipleiria.evo_menu.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Adaptadores.ListaItemsAdaptador;
import amsi.dei.estg.ipleiria.evo_menu.Adaptadores.ListaMenusAdaptador;
import amsi.dei.estg.ipleiria.evo_menu.Adaptadores.ListaPedidoPagamentoAdaptador;
import amsi.dei.estg.ipleiria.evo_menu.Model.Categoria;
import amsi.dei.estg.ipleiria.evo_menu.Model.Item;
import amsi.dei.estg.ipleiria.evo_menu.Model.Menu;
import amsi.dei.estg.ipleiria.evo_menu.Model.Pedido;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorCategorias;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorItems;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorMenus;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorPedidos;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorRestaurantes;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorUsers;
import amsi.dei.estg.ipleiria.evo_menu.R;

public class RealizarPagamento extends AppCompatActivity {

    Spinner spMetodosPagamento;
    ListView lvItensPedido;
    TextView tvItensTotaisPagamento, tvValorTotalPagamentos;
    Button btnConcluirPagamento, btnCancelarPedidoPagamento;
    ArrayList<Item> itens;
    ArrayList<Menu> menus;
    int idRestaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_pagamento);

        itens = new ArrayList<>();
        menus = new ArrayList<>();
        idRestaurante = getIntent().getExtras().getInt("idRestaurante");

        spMetodosPagamento = findViewById(R.id.spMetodosPagamento);
        lvItensPedido = findViewById(R.id.lvItensPedido);
        tvItensTotaisPagamento = findViewById(R.id.tvItensTotaisPagamento);
        tvValorTotalPagamentos = findViewById(R.id.tvValorTotalPagamentos);
        btnConcluirPagamento = findViewById(R.id.btnConcluirPagamento);
        btnCancelarPedidoPagamento = findViewById(R.id.btnCancelarPedidoPagamento);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.metodosPagamento,
                android.R.layout.simple_spinner_item);
        spMetodosPagamento.setAdapter(adapter);

        preencherLV();
        tvValorTotalPagamentos.setText(SingletonGestorPedidos.getInstance(this).getValorTotal() + "");

        btnConcluirPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                Pedido pedido = new Pedido(SingletonGestorPedidos.getInstance(context).getValorTotal(), "Recebido",
                        SingletonGestorUsers.getInstance(context).getUserLogado().getId(), idRestaurante);

                SingletonGestorPedidos.getInstance(getApplicationContext()).adicionarPedidoAPI(pedido, getApplicationContext());

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (SingletonGestorPedidos.getInstance(getApplicationContext()).getIdItensPedido() != null)
                    for (int idItem: SingletonGestorPedidos.getInstance(getApplicationContext()).getIdItensPedido())
                        SingletonGestorPedidos.getInstance(getApplicationContext()).adicionarItemPedidoAPI(idItem, getApplicationContext());

                if (SingletonGestorPedidos.getInstance(getApplicationContext()).getIdMenusPedido() != null)
                    for (int idMenu: SingletonGestorPedidos.getInstance(getApplicationContext()).getIdItensPedido())
                        SingletonGestorPedidos.getInstance(getApplicationContext()).adicionarMenuPedidoAPI(idMenu, getApplicationContext());

            }
        });

        btnCancelarPedidoPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void preencherLV() {
        //carregar menus e items a partir de ids guardados nos singletons
        if (SingletonGestorPedidos.getInstance(this).getIdItensPedido() != null)
            for (int id: SingletonGestorPedidos.getInstance(this).getIdItensPedido())
                itens.add(SingletonGestorItems.getInstance(this).getItem(id));

        if (SingletonGestorPedidos.getInstance(this).getIdMenusPedido() != null)
            for (int id: SingletonGestorPedidos.getInstance(this).getIdMenusPedido())
                menus.add(SingletonGestorMenus.getInstance(this).getMenu(id));

        lvItensPedido.setAdapter(new ListaPedidoPagamentoAdaptador(this, itens, menus));
    }
}
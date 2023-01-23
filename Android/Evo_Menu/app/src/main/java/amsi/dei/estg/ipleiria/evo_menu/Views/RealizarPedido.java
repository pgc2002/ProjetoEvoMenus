package amsi.dei.estg.ipleiria.evo_menu.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Adaptadores.ListaItemsAdaptador;
import amsi.dei.estg.ipleiria.evo_menu.Adaptadores.ListaMenusAdaptador;
import amsi.dei.estg.ipleiria.evo_menu.Model.Categoria;
import amsi.dei.estg.ipleiria.evo_menu.Model.Item;
import amsi.dei.estg.ipleiria.evo_menu.Model.Menu;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorCategorias;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorItems;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorMenus;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorPedidos;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorUsers;
import amsi.dei.estg.ipleiria.evo_menu.Model.User;
import amsi.dei.estg.ipleiria.evo_menu.R;

public class RealizarPedido extends AppCompatActivity {

    Spinner spCategorias;
    Button btnItem, btnMenu, btnCancelar, btnFazerPagamento;
    TextView tvItensTotais, tvValorTotal, tvCategoria;
    ListView lvItens;
    ArrayList<Categoria> categorias;
    ArrayList<Item> items;
    ArrayList<Menu> menus;
    int idRestaurante;
    boolean isItem;
    ArrayList<Integer> idItems;
    ArrayList<Integer> idMenus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_pedido);

        spCategorias = findViewById(R.id.spCategoria);
        btnItem = findViewById(R.id.btnItem);
        btnMenu = findViewById(R.id.btnMenu);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnFazerPagamento = findViewById(R.id.btnFazerPagamento);
        tvItensTotais = findViewById(R.id.tvItensTotais);
        tvValorTotal = findViewById(R.id.tvValorTotalPedido);
        tvCategoria = findViewById(R.id.tvCategoria);
        lvItens = findViewById(R.id.lvConteudoMenu);

        Bundle extras = getIntent().getExtras();
        idRestaurante = extras.getInt("idRestaurante");

        items = new ArrayList<Item>();
        menus = new ArrayList<Menu>();

        isItem = true;

        idItems = new ArrayList<Integer>();
        idMenus = new ArrayList<Integer>();

        categorias = new ArrayList<Categoria>();
        for (Categoria categoria: SingletonGestorCategorias.getInstance(this).getCategorias()) {
            if(categoria.getIdRestaurante() == idRestaurante)
                categorias.add(categoria);
        }

        if(!categorias.isEmpty()) {
            ArrayAdapter<Categoria> adapter = new ArrayAdapter<Categoria>(this, android.R.layout.simple_spinner_dropdown_item, categorias);
            spCategorias.setAdapter(adapter);
            spCategorias.setSelection(0);
        }

        SingletonGestorPedidos.getInstance(this).setValorTotal(0);

        final Handler handler = new Handler();
        final int delay = 1500;
        Runnable runnable = new Runnable() {
            public void run() {
                handler.postDelayed(new Runnable() {
                    public void run() {
                        atualizarPedido();
                        Log.d("idsItens", SingletonGestorPedidos.getInstance(getApplicationContext()).getIdItensPedido() + "");
                        Log.d("idsMenus", SingletonGestorPedidos.getInstance(getApplicationContext()).getIdMenusPedido() + "");
                        handler.postDelayed(this, delay);
                    }
                }, delay);
            }
        };
        new Thread(runnable).start();

        preencherLV();

        spCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                preencherLV();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isItem = true;
                preencherLV();
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isItem = false;
                preencherLV();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnFazerPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RealizarPagamento.class);
                intent.putExtra("idRestaurante", idRestaurante);
                if (SingletonGestorPedidos.getInstance(getApplicationContext()).getIdMenusPedido().isEmpty() && SingletonGestorPedidos.getInstance(getApplicationContext()).getIdItensPedido().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Precisa de selcionar pelo menos um item ou menu.", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(intent);
            }
        });
    }

    private void atualizarPedido() {
        SingletonGestorPedidos.getInstance(this).setIdItensPedido(idItems);
        SingletonGestorPedidos.getInstance(this).setIdMenusPedido(idMenus);
        int contadorItens = 0;
        if(SingletonGestorPedidos.getInstance(this).getIdItensPedido() != null)
            contadorItens += SingletonGestorPedidos.getInstance(this).getIdItensPedido().size();

        if(SingletonGestorPedidos.getInstance(this).getIdMenusPedido() != null)
            contadorItens += SingletonGestorPedidos.getInstance(this).getIdMenusPedido().size();

        tvItensTotais.setText(contadorItens + "");
        tvValorTotal.setText(SingletonGestorPedidos.getInstance(this).getValorTotal()+"€");
    }

    public void preencherLV() {
        Categoria categoria = (Categoria) spCategorias.getSelectedItem();
        int id = categoria.getId();
        if(isItem){
            tvCategoria.setText("ITENS");
            if (!items.isEmpty())
                items.clear();;
            for (Item item: SingletonGestorItems.getInstance(this).getItems()) {
                if (item.getIdCategoria() == id)
                    items.add(item);
            }
            lvItens.setAdapter(new ListaItemsAdaptador(this, items));
        }else{
            tvCategoria.setText("MENUS");
            if (!menus.isEmpty())
                menus.clear();
            for (Menu menu: SingletonGestorMenus.getInstance(this).getMenus()) {
                if (menu.getIdCategoria() == id)
                    menus.add(menu);
            }
            lvItens.setAdapter(new ListaMenusAdaptador(this, menus));
        }

    }
}
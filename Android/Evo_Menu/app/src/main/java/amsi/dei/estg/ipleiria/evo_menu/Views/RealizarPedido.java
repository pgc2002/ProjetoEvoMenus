package amsi.dei.estg.ipleiria.evo_menu.Views;

import androidx.appcompat.app.AppCompatActivity;

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
import amsi.dei.estg.ipleiria.evo_menu.R;

public class RealizarPedido extends AppCompatActivity {

    Spinner spCategorias;
    Button btnItem, btnMenu, btnCancelar, btnFazerPagamento, btnAdicionar;
    TextView tvItensTotais, tvValorTotal, tvCategoria;
    ListView lvItens;
    ArrayList<Categoria> categorias;
    ArrayList<Item> items;
    ArrayList<Menu> menus;
    int idRestaurante;
    boolean isItem, runThread;
    int[] idItems;
    int[] idMenus;

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
        runThread = true;

        idItems = null;
        idMenus = null;



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

        final Handler handler = new Handler();
        final int delay = 3000;
        new Thread(new Runnable() {
            public void run() {
                handler.postDelayed(new Runnable() {
                    public void run() {
                        while(runThread) {
                            atualizarPedido();
                            Log.d("still works", "i guess");
                        }
                        handler.postDelayed(this, delay);
                    }
                }, delay);
            }
        }).start();

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
                runThread = false;
                finish();
            }
        });

        btnFazerPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void atualizarPedido() {
        SingletonGestorPedidos.getInstance(this).setIdItensPedido(idItems);
        SingletonGestorPedidos.getInstance(this).setIdMenusPedido(idMenus);
        int contadorItens = 0;
        if(SingletonGestorPedidos.getInstance(this).getIdItensPedido() != null)
            contadorItens += SingletonGestorPedidos.getInstance(this).getIdItensPedido().length;

        if(SingletonGestorPedidos.getInstance(this).getIdMenusPedido() != null)
            contadorItens += SingletonGestorPedidos.getInstance(this).getIdMenusPedido().length;

        tvItensTotais.setText(contadorItens + "");
        tvValorTotal.setText("bababooey");
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
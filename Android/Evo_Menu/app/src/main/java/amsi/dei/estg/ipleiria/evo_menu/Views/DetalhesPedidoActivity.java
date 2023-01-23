package amsi.dei.estg.ipleiria.evo_menu.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Adaptadores.ListaPedidoPagamentoAdaptador;
import amsi.dei.estg.ipleiria.evo_menu.Model.Item;
import amsi.dei.estg.ipleiria.evo_menu.Model.Menu;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorItems;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorMenus;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorPedidos;
import amsi.dei.estg.ipleiria.evo_menu.R;

public class DetalhesPedidoActivity extends AppCompatActivity {
    ListView lvItensPedido;

    ArrayList<Item> itens;
    ArrayList<Menu> menus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pedido);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        itens = new ArrayList<>();
        menus = new ArrayList<>();

        lvItensPedido = findViewById(R.id.lvItensPedido);
        preencherLV();
    }

    public void preencherLV() {
        Intent intent = getIntent();

        int idPedido = (int)intent.getIntExtra("idPedido", 0);

        //carregar menus e items a partir de ids guardados nos singletons
        if (SingletonGestorPedidos.getInstance(getApplicationContext()).getPedido(idPedido).getIdItensPedido() != null)
            for (int id: SingletonGestorPedidos.getInstance(getApplicationContext()).getPedido(idPedido).getIdItensPedido())
                itens.add(SingletonGestorItems.getInstance(this).getItem(id));

        if (SingletonGestorPedidos.getInstance(getApplicationContext()).getPedido(idPedido).getIdMenusPedido() != null)
            for (int id: SingletonGestorPedidos.getInstance(getApplicationContext()).getPedido(idPedido).getIdMenusPedido())
                menus.add(SingletonGestorMenus.getInstance(this).getMenu(id));

        lvItensPedido.setAdapter(new ListaPedidoPagamentoAdaptador(this, itens, menus));
    }
}
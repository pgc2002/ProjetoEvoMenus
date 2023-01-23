package amsi.dei.estg.ipleiria.evo_menu.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Listeners.RestauranteListener;
import amsi.dei.estg.ipleiria.evo_menu.Model.Categoria;
import amsi.dei.estg.ipleiria.evo_menu.Model.Restaurante;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorRestaurantes;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorUsers;
import amsi.dei.estg.ipleiria.evo_menu.Model.User;
import amsi.dei.estg.ipleiria.evo_menu.R;

public class DetalhesRestauranteActivity extends AppCompatActivity implements RestauranteListener
{
    public static final String ID_RESTAURANTE = "amsi.dei.estg.ipleiria.evo_menu.Views.ID_RESTAURANTE";

    public static final int OP_CODE_EDITAR = 100;
    public static final String OP_CODE = "OPERATION_CODE";

    public static final int OP_CODE_ADICIONAR = 300;

    public static final int OP_CODE_APAGAR = 200;

    private TextView tvNome, tvLotacao, tvEmail, tvTelemovel;
    private ImageView ivCapaDetalhes;
    private Button btFazerPedido;
    private Restaurante restaurante;
    private String token;

    //public static final String ID_LIVRO = "amsi.dei.ipleiria.books_aula.Vistas.ID_LIVRO";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_restaurante);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ivCapaDetalhes = findViewById(R.id.ivCapaDetalhe);
        tvNome = findViewById(R.id.tvNomeItem);
        tvLotacao = findViewById(R.id.tvLotacaoMax);
        tvEmail = findViewById(R.id.tvEmail);
        tvTelemovel = findViewById(R.id.tvTelemovel);
        btFazerPedido = findViewById(R.id.btFazerPedido);
        //ivCapaDetalhes = findViewById(R.id.ivCapaDetalhe);//adiciona no layout

        Intent intent = getIntent();

        int id = (int)intent.getLongExtra(ID_RESTAURANTE, -1);
        int idRestaurante = (int)intent.getIntExtra("idRestaurante", 0);
        Log.d("idRestaurante", idRestaurante + "");
        Restaurante restaurante = SingletonGestorRestaurantes.getInstance(this).getRestaurante(idRestaurante);

        SingletonGestorRestaurantes.getInstance(this).getCategoriasAPI(this, idRestaurante);
        ArrayList<Categoria> categorias = SingletonGestorRestaurantes.getInstance(this).getCategorias();
        Log.d("idRestaurante", categorias + "");

        mostrarDetalhes(restaurante);

        btFazerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetalhesRestauranteActivity.this, RealizarPedido.class);
                i.putExtra("idRestaurante", idRestaurante);
                startActivity(i);
            }
        });
    }

    private void mostrarDetalhes(Restaurante restaurante)
    {
        //meter dados nos campos...
        //setTitle("Detalhes: " + restaurante.getNome());
        setTitle("Detalhes: " + restaurante.getNome());
        tvNome.setText(restaurante.getNome());
        tvEmail.setText(restaurante.getEmail());
        tvLotacao.setText(String.valueOf(restaurante.getLotacao_max()));
        tvTelemovel.setText(restaurante.getTelemovel());
    }

    @Override
    public void onRefreshDetalhes(int op)
    {
        Intent intent = new Intent();
        intent.putExtra(OP_CODE, op);
        setResult(RESULT_OK, intent);
        finish();
    }
}

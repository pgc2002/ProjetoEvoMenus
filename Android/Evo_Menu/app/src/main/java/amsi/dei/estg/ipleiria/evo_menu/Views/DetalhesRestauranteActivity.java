package amsi.dei.estg.ipleiria.evo_menu.Views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import amsi.dei.estg.ipleiria.evo_menu.Listeners.RestauranteListener;
import amsi.dei.estg.ipleiria.evo_menu.Model.Restaurante;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorRestaurantes;
import amsi.dei.estg.ipleiria.evo_menu.R;

public class DetalhesRestauranteActivity extends AppCompatActivity implements RestauranteListener
{
    public static final String ID_RESTAURANTE = "amsi.dei.estg.ipleiria.evo_menu.Views.ID_RESTAURANTE";

    public static final int OP_CODE_EDITAR = 100;
    public static final String OP_CODE = "OPERATION_CODE";

    public static final int OP_CODE_ADICIONAR = 300;

    public static final int OP_CODE_APAGAR = 200;

    private EditText etNome, etLotacao, etEmail, etTelemovel;
    private ImageView ivCapaDetalhes;
    private FloatingActionButton fabGuardar;
    private Restaurante restaurante;
    private String token;

    //public static final String ID_LIVRO = "amsi.dei.ipleiria.books_aula.Vistas.ID_LIVRO";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_restaurante);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNome = findViewById(R.id.etNomeRestaurante);
        etLotacao = findViewById(R.id.etLotacaoMax);
        etEmail = findViewById(R.id.etEmail);
        etTelemovel = findViewById(R.id.etTelemovel);
        //ivCapaDetalhes = findViewById(R.id.ivCapaDetalhe);//adiciona no layout

        int id = (int)getIntent().getLongExtra(ID_RESTAURANTE, -1);
        restaurante = SingletonGestorRestaurantes.getInstance(this).getRestaurante(id);

        if(restaurante != null)
        {
            mostrarDetalhes(restaurante);
        }
        /*else
        {
            fabGuardar.setImageResource(R.drawable.add_icon);
        }*/

        /*fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registarLivro();
            }
        });
        SingletonGestorLivros.getInstance(this).setLivroListener(this);*/


    }

    private void mostrarDetalhes(Restaurante restaurante)
    {
        //meter dados nos campos...
        setTitle("Detalhes: " + restaurante.getNome());
        etEmail.setText(restaurante.getEmail());
        etLotacao.setText("" + restaurante.getLotacao_max());
        etTelemovel.setText(restaurante.getTelemovel());
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

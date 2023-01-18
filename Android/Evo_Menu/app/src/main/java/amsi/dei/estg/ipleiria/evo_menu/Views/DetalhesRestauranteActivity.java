package amsi.dei.estg.ipleiria.evo_menu.Views;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import amsi.dei.estg.ipleiria.evo_menu.Listeners.RestauranteListener;
import amsi.dei.estg.ipleiria.evo_menu.Model.Restaurante;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorRestaurantes;
import amsi.dei.estg.ipleiria.evo_menu.R;

public class DetalhesRestauranteActivity extends AppCompatActivity implements RestauranteListener
{
    private EditText etNome, etLotacao, etEmail, etTelemovel;
    private ImageView ivCapaDetalhe;
    private FloatingActionButton fabGuardar;
    private Restaurante restaurante;
    private String token;
    public static final String OP_CODE = "OPERATION_CODE";
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
        ivCapaDetalhe = findViewById(R.id.ivCapaDetalhe);//adiciona no layout

        /*int id = (int)getIntent().getLongExtra(ID_LIVRO, -1);
        restaurante = SingletonGestorRestaurantes.getInstance(this).get(id);*/

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



    private void mostrarDetalhes(Restaurante livro)
    {
        //meter dados nos campos...
        setTitle("Detalhes: " + restaurante.getNome());
        etEmail.setText(livro.getEmail());
        etLotacao.setText("" + livro.getLotacaoMaxima());
        etTelemovel.setText(livro.getTelemovel());


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

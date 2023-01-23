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
import amsi.dei.estg.ipleiria.evo_menu.Model.HorarioFuncionamento;
import amsi.dei.estg.ipleiria.evo_menu.Model.Restaurante;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorMorada;
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
    private TextView tvSegundaAlmoco, tvTercaAlmoco, tvQuartaAlmoco, tvQuintaAlmoco, tvSextaAlmoco, tvSabadoAlmoco, tvDomingoAlmoco;
    private TextView tvSegundaJantar, tvTercaJantar, tvQuartaJantar, tvQuintaJantar, tvSextaJantar, tvSabadoJantar, tvDomingoJantar;
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

        btFazerPedido = findViewById(R.id.btFazerPedido);

        tvNome = findViewById(R.id.tvNomeRestaurante);
        tvLotacao = findViewById(R.id.tvLotacaoMax);
        tvEmail = findViewById(R.id.tvEmail);
        tvTelemovel = findViewById(R.id.tvTelemovel);

        tvSegundaAlmoco = findViewById(R.id.tvSegundaAlmoco);
        tvTercaAlmoco = findViewById(R.id.tvTercaAlmoco);
        tvQuartaAlmoco = findViewById(R.id.tvQuartaAlmoco);
        tvQuintaAlmoco = findViewById(R.id.tvQuintaAlmoco);
        tvSextaAlmoco = findViewById(R.id.tvSextaAlmoco);
        tvSabadoAlmoco = findViewById(R.id.tvSabadoAlmoco);
        tvDomingoAlmoco = findViewById(R.id.tvDomingoAlmoco);

        tvSegundaJantar = findViewById(R.id.tvSegundaJantar);
        tvTercaJantar = findViewById(R.id.tvTercaJantar);
        tvQuartaJantar = findViewById(R.id.tvQuartaJantar);
        tvQuintaJantar = findViewById(R.id.tvQuintaJantar);
        tvSextaJantar = findViewById(R.id.tvSextaJantar);
        tvSabadoJantar = findViewById(R.id.tvSabadoJantar);
        tvDomingoJantar = findViewById(R.id.tvDomingoJantar);

        Intent intent = getIntent();

        int id = (int)intent.getLongExtra(ID_RESTAURANTE, -1);
        int idRestaurante = (int)intent.getIntExtra("idRestaurante", 0);
        Log.d("idRestaurante", idRestaurante + "");

        SingletonGestorRestaurantes.getInstance(this).getHorarioAPI(this,idRestaurante);
        Restaurante restaurante = SingletonGestorRestaurantes.getInstance(this).getRestaurante(idRestaurante);

        SingletonGestorRestaurantes.getInstance(this).getCategoriasAPI(this, idRestaurante);
        ArrayList<Categoria> categorias = SingletonGestorRestaurantes.getInstance(this).getCategorias();

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
        setTitle("Detalhes: " + restaurante.getNome());
        tvNome.setText(restaurante.getNome());
        tvEmail.setText(restaurante.getEmail());
        tvLotacao.setText(String.valueOf(restaurante.getLotacao_max()));
        tvTelemovel.setText(restaurante.getTelemovel());

        HorarioFuncionamento horario = restaurante.getHorario();
        if(horario != null){
            String[] segunda = horario.getSegunda_feira().split("-");
            String[] terca = horario.getTerca_feira().split("-");
            String[] quarta = horario.getQuarta_feira().split("-");
            String[] quinta = horario.getQuinta_feira().split("-");
            String[] sexta = horario.getSexta_feira().split("-");
            String[] sabado = horario.getSabado().split("-");
            String[] domingo = horario.getDomingo().split("-");

            // Almoço
            if(segunda.length > 1)
                tvSegundaAlmoco.setText(segunda[0]+"-"+segunda[1]);
            else
                tvSegundaAlmoco.setText("Folga");

            if(terca.length > 1)
                tvTercaAlmoco.setText(terca[0]+"-"+terca[1]);
            else
                tvTercaAlmoco.setText("Folga");

            if(quarta.length > 1)
                tvQuartaAlmoco.setText(quarta[0]+"-"+quarta[1]);
            else
                tvQuartaAlmoco.setText("Folga");

            if(quinta.length > 1)
                tvQuintaAlmoco.setText(quinta[0]+"-"+quinta[1]);
            else
                tvQuintaAlmoco.setText("Folga");

            if(sexta.length > 1)
                tvSextaAlmoco.setText(sexta[0]+"-"+sexta[1]);
            else
                tvSextaAlmoco.setText("Folga");

            if(sabado.length > 1)
                tvSabadoAlmoco.setText(sabado[0]+"-"+sabado[1]);
            else
                tvSabadoAlmoco.setText("Folga");

            if(domingo.length > 1)
                tvDomingoAlmoco.setText(domingo[0]+"-"+domingo[1]);
            else
                tvDomingoAlmoco.setText("Folga");


            // Jantar
            if(segunda.length > 1)
                tvSegundaJantar.setText(segunda[2]+"-"+segunda[3]);
            else
                tvSegundaAlmoco.setText("Folga");

            if(terca.length > 1)
                tvTercaJantar.setText(terca[2]+"-"+terca[3]);
            else
                tvTercaJantar.setText("Folga");

            if(quarta.length > 1)
                tvQuartaJantar.setText(quarta[2]+"-"+quarta[3]);
            else
                tvQuartaJantar.setText("Folga");

            if(quinta.length > 1)
                tvQuintaJantar.setText(quinta[2]+"-"+quinta[3]);
            else
                tvQuintaJantar.setText("Folga");

            if(sexta.length > 1)
                tvSextaJantar.setText(sexta[2]+"-"+sexta[3]);
            else
                tvSextaJantar.setText("Folga");

            if(sabado.length > 1)
                tvSabadoJantar.setText(sabado[2]+"-"+sabado[3]);
            else
                tvSabadoJantar.setText("Folga");

            if(domingo.length > 1)
                tvDomingoJantar.setText(domingo[2]+"-"+domingo[3]);
            else
                tvDomingoJantar.setText("Folga");
        }
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

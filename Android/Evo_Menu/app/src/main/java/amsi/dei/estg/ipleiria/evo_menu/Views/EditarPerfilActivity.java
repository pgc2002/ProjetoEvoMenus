package amsi.dei.estg.ipleiria.evo_menu.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import amsi.dei.estg.ipleiria.evo_menu.Model.Morada;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorUsers;
import amsi.dei.estg.ipleiria.evo_menu.Model.User;
import amsi.dei.estg.ipleiria.evo_menu.R;

public class EditarPerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        EditText etPassword = findViewById(R.id.etPassword);
        EditText etNome = findViewById(R.id.etNome);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etTelemovel = findViewById(R.id.etTelemovel);
        EditText etNif = findViewById(R.id.etNif);

        EditText etRua = findViewById(R.id.etRua);
        EditText etCodPostal = findViewById(R.id.etCodPost);
        EditText etPais = findViewById(R.id.etPais);
        EditText etCidade = findViewById(R.id.etCidade);

        User user = SingletonGestorUsers.getInstance(getApplicationContext()).getUserLogado();
        SingletonGestorUsers.getInstance(getApplicationContext()).getMoradaAPI(getApplicationContext());

        if(user != null) {
            //etPassword.setText(user.getPass());
            etNome.setText(user.getNome());
            etEmail.setText(user.getEmail());
            etTelemovel.setText(user.getTelemovel());
            etNif.setText(user.getNif());
        }

        Morada morada = user.getMorada();

        if(morada != null){
            etPais.setText(morada.getPais());
            etCidade.setText(morada.getCidade());
            etRua.setText(morada.getRua());
            etCodPostal.setText(morada.getCodpost());
        }

        Button btn = findViewById(R.id.btConcluirEdicao);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNome.getText().length() < 1 || etEmail.getText().length() < 1 || etTelemovel.getText().length() < 1 || etNif.getText().length() < 1 ||
                        etRua.getText().length() < 1 || etCodPostal.getText().length() < 1 || etPais.getText().length() < 1 || etCidade.getText().length() < 1)
                {

                    return;
                }

                User user = new User(SingletonGestorUsers.getInstance(getApplicationContext()).getUserLogado().getId(), SingletonGestorUsers.getInstance(getApplicationContext()).getUserLogado().getUsername(), etNome.getText().toString(), etPassword.getText().toString(), etEmail.getText().toString(), etTelemovel.getText().toString(), etNif.getText().toString());

                /*if(SingletonGestorUsers.getInstance(getContext()).getUserLogado().getPass() == etPassword.getText().toString())
                    user = new User(SingletonGestorUsers.getInstance(getContext()).getUserLogado().getId(), etUsername.getText().toString(), etNome.getText().toString(), "", etEmail.getText().toString(), etTelemovel.getText().toString(), etNif.getText().toString());
                else
                    user = new User(SingletonGestorUsers.getInstance(getContext()).getUserLogado().getId(), etUsername.getText().toString(), etNome.getText().toString(), etPassword.getText().toString(), etEmail.getText().toString(), etTelemovel.getText().toString(), etNif.getText().toString());
                */
                Morada morada = new Morada(SingletonGestorUsers.getInstance(getApplicationContext()).getUserLogado().getId_morada(), SingletonGestorUsers.getInstance(getApplicationContext()).getUserLogado().getId(), etPais.getText().toString(), etCidade.getText().toString(), etRua.getText().toString(), etCodPostal.getText().toString());

                SingletonGestorUsers.getInstance(getApplicationContext()).setUserLogado(user);
                SingletonGestorUsers.getInstance(getApplicationContext()).setMoradaUserLogado(morada);

                SingletonGestorUsers.getInstance(getApplicationContext()).editarUserAPI(user, getApplicationContext());
                SingletonGestorUsers.getInstance(getApplicationContext()).editarMoradaAPI(morada, getApplicationContext());
                SingletonGestorUsers.getInstance(getApplicationContext()).editarUserBD(user);


                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                intent.putExtra("refreshPerfil", 1);
                startActivity(intent);
                finish();
            }
        });
    }
}
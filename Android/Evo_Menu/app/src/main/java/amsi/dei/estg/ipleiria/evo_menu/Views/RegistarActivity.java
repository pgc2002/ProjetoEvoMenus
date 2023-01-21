package amsi.dei.estg.ipleiria.evo_menu.Views;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorUser;
import amsi.dei.estg.ipleiria.evo_menu.Model.Users;
import amsi.dei.estg.ipleiria.evo_menu.R;

public class RegistarActivity extends AppCompatActivity
{
    private EditText etUsername, etNomeCompleto, etPassword, etMail, etTelemovel, etNif, etPais, etCidade, etRua, etCodPost;
    private Button btnRegistar, btnCancelar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.registar_activity);

        btnRegistar = findViewById(R.id.btRegistarNovo);
        btnCancelar = findViewById(R.id.btCancelarRegisto);

        etUsername = findViewById(R.id.etRegistarUsername);
        etNomeCompleto = findViewById(R.id.etRegistarNome);
        etPassword = findViewById(R.id.etRegistarPassword);
        etMail = findViewById(R.id.etRegistarEmail);
        etTelemovel = findViewById(R.id.etRegistarTelemovel);
        etNif = findViewById(R.id.etRegistarNif);
        etPais = findViewById(R.id.etRegistarPais);
        etCidade = findViewById(R.id.etRegistarCidade);
        etRua = findViewById(R.id.etRegistarRua);
        etCodPost = findViewById(R.id.etRegistaCodPostal);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registarUser();
            }
        });
    }

    private void registarUser() {
        try {
        Users user = new Users(1, etUsername.getText().toString(), etNomeCompleto.getText().toString(), etPassword.getText().toString(),
                etMail.getText().toString(), etTelemovel.getText().toString(), etNif.getText().toString());

            SingletonGestorUser.getInstance(this).adicionarUserAPI(user, etPais.getText().toString(), etCidade.getText().toString(),
                    etRua.getText().toString(), etCodPost.getText().toString(), this);
        }catch (Exception e){
            Log.d("testeCriar user", e.getMessage());
        }

    }
}

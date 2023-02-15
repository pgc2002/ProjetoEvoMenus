package amsi.dei.estg.ipleiria.evo_menu.Views;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorUsers;
import amsi.dei.estg.ipleiria.evo_menu.Model.User;
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
                if(etUsername.getText().toString().length() <= 0 || etNomeCompleto.getText().toString().length() <= 0
                   || etPassword.getText().toString().length() <= 0 || etMail.getText().toString().length() <= 0
                   || etTelemovel.getText().toString().length() <= 0 || etNif.getText().toString().length() <= 0
                   || etPais.getText().toString().length() <= 0 || etCidade.getText().toString().length() <= 0
                   || etRua.getText().toString().length() <= 0 || etCodPost.getText().toString().length() <= 0
                ){
                    Toast.makeText(getApplicationContext(), "Todos os campos devem estar preenchidos", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!existeUser()){
                    registarUser();
                    finish();
                }
            }
        });
    }

    private void registarUser() {
        try {
            //ArrayList<User> users = SingletonGestorUsers.getInstance(getApplicationContext()).getUsersSingleton();
            User user = new User(etUsername.getText().toString(), etNomeCompleto.getText().toString(), etPassword.getText().toString(),
            etMail.getText().toString(), etTelemovel.getText().toString(), etNif.getText().toString());

            SingletonGestorUsers.getInstance(this).adicionarUserAPI(user, etPais.getText().toString(), etCidade.getText().toString(),
                    etRua.getText().toString(), etCodPost.getText().toString(), this);

            //SingletonGestorUsers.getInstance(this).adicionarUserBD(user);

        }catch (Exception e){
            Log.d("testeCriar user", e.getMessage());
        }
    }


    private boolean existeUser(){

        return true;
    }
}

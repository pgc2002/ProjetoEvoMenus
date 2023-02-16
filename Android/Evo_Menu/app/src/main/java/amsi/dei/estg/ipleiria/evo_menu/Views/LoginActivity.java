package amsi.dei.estg.ipleiria.evo_menu.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Listeners.LoginListener;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorUsers;
import amsi.dei.estg.ipleiria.evo_menu.Model.User;
import amsi.dei.estg.ipleiria.evo_menu.R;

public class LoginActivity extends AppCompatActivity implements LoginListener {
    private EditText etUsername, etPass;
    private boolean existe = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        etUsername = findViewById(R.id.etUsernameLogin);
        etPass = findViewById(R.id.etPasswordLogin);
        Button btnLogin = findViewById(R.id.btLogin);
        Button btnRegistar = findViewById(R.id.btRegistar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etUsername.getText().length() < 1 || etPass.getText().length() < 1){
                    Toast.makeText(getApplicationContext(), "Todos os campos devem estar preenchidos", Toast.LENGTH_SHORT).show();
                    return;
                }

                btnLogin.setClickable(false);
                btnRegistar.setClickable(false);
                btnLogin.setAlpha(.5f);
                btnRegistar.setAlpha(.5f);
                btnLogin.setBackgroundColor(getResources().getColor(R.color.butoesDesativados));
                btnRegistar.setBackgroundColor(getResources().getColor(R.color.butoesDesativados));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnLogin.setClickable(true);
                        btnRegistar.setClickable(true);
                        btnLogin.setAlpha(1);
                        btnRegistar.setAlpha(1);
                        btnLogin.setBackgroundColor(getResources().getColor(R.color.butoes));
                        btnRegistar.setBackgroundColor(getResources().getColor(R.color.butoes));
                    }
                }, 500);

                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);

                if (validarLoginBD()) {
                    startActivity(intent);
                }else {
                    if(existe){
                        Toast.makeText(getApplicationContext(), "Username ou password errados", Toast.LENGTH_SHORT).show();
                    }else{
                        validarLogin();
                    }
                }
            }
        });

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(LoginActivity.this,RegistarActivity.class);
                startActivity(intent);
            }
        });

        SingletonGestorUsers.getInstance(this).setLoginListener(this);
    }

    private boolean validarLoginBD() {
        existe = false;

        ArrayList<User> users = SingletonGestorUsers.getInstance(getApplicationContext()).getUsersBD();

        boolean validacao = false;

        if (!users.isEmpty())
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equals(etUsername.getText().toString().trim()) && users.get(i).getPass().equals(etPass.getText().toString().trim())) {
                    SingletonGestorUsers.getInstance(getApplicationContext()).setUserLogado(users.get(i));
                    validacao = true;
                }else if(users.get(i).getUsername().equals(etUsername.getText().toString().trim())){
                    existe = true;
                }
            }

        return validacao;
    }

    private void validarLogin() {
        String username = etUsername.getText().toString().trim();
        String pass = etPass.getText().toString().trim();

        SingletonGestorUsers.getInstance(this).loginAPI(this, username, pass);
    }

    @Override
    public void onValidadeLogin(Context contexto, String username, String password, String valido) {
        if(valido == null){
            Toast.makeText(this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
            return;
        }

        //Log.d("verificar validacao", valido);

        if(valido.equals("true")){
            SingletonGestorUsers.getInstance(getApplicationContext()).getUserLogadoAPI(this, etUsername.getText().toString().trim(), etPass.getText().toString().trim());
            Intent intent = new Intent(this, MainMenuActivity.class);
            intent.putExtra("username", etUsername.getText().toString().trim());
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Username ou password errados", Toast.LENGTH_SHORT).show();
        }
    }
}
package amsi.dei.estg.ipleiria.evo_menu.Views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorUsers;
import amsi.dei.estg.ipleiria.evo_menu.Model.User;
import amsi.dei.estg.ipleiria.evo_menu.R;

public class LoginActivity extends AppCompatActivity
{
    public static final String MAIL = "amsi.dei.estg.ipleiria.projetofinal.mail";
    private EditText etUsername, etPass;
    private Button btnLogin, btnRegistar;
    SingletonGestorUsers singletonGestorUser;
    int checkLogin;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        singletonGestorUser = SingletonGestorUsers.getInstance(this);
        checkLogin = 0;
        etUsername = findViewById(R.id.etUsernameLogin);
        etPass = findViewById(R.id.etPasswordLogin);
        btnLogin = findViewById(R.id.btLogin);
        btnRegistar = findViewById(R.id.btRegistar);
        SingletonGestorUsers.getInstance(this).getAllUsersAPI(this);
        //bt click event
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                try {
                    validarLogin(view);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("myTag", e.getMessage());
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

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (checkLogin == 1)
                    btnLogin.performClick();

                handler.postDelayed(this, 500); //1000ms = 1seconds * 60
            }
        }, 1);

    }

    private void validarLogin(View view) throws JSONException {
        String username = etUsername.getText().toString();
        String pass = etPass.getText().toString();
        checkLogin++;
        //User user = SingletonGestorUsers.getInstance(this).getUser(username);
        SingletonGestorUsers.getInstance(this).validacaoPassAPI(username, pass, this);
        try {
            String validacao = "";

            validacao = SingletonGestorUsers.getInstance(this).getValidacao();
            int length = validacao.length();
            if(validacao.length() != 0) {
                String validacaoNew = validacao.substring(1, validacao.length() - 1);
                String[] parts = validacaoNew.split("----");
                int val = Integer.parseInt(parts[0]);
                if(val == 1){
                    Intent intentUser = new Intent(this, MainMenuActivity.class);
                    //intentUser.putExtra("id", parts[1]);
                    //intentUser.putExtra("pass", pass);
                    SingletonGestorUsers.getInstance(this).getUserAPI(this, Integer.parseInt(parts[1]), pass);
                    //User user = SingletonGestorUsers.getInstance(this).getUserLogado();
                    //Log.d("wdaaw", user.getUsername());
                    startActivity(intentUser);
                }
            }
        }catch (Exception e){
            Log.d("testeValidacao", e.getMessage());
        }
    }
    private void RegistarUser(View view){
        setContentView(R.layout.registar_activity);
    }

    private boolean isPassValida(String pass) {
        if(pass == null)
            return false;
        return pass.length() >=4;


    }

    private boolean isMailValido(String mail) {
        if(mail == null || mail.isEmpty())
            return false;
        boolean valido = Patterns.EMAIL_ADDRESS.matcher(mail).matches();
        return valido;
    }


}

package amsi.dei.estg.ipleiria.evo_menu.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorCategorias;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorItems;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorMenus;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorMesas;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorPagamentos;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorPedidos;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorRestaurantes;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorUsers;
import amsi.dei.estg.ipleiria.evo_menu.Model.User;
import amsi.dei.estg.ipleiria.evo_menu.R;

public class LoginActivity extends AppCompatActivity
{
    public static Activity login;

    public static final String MAIL = "amsi.dei.estg.ipleiria.projetofinal.mail";
    private EditText etUsername, etPass;
    private Button btnLogin, btnRegistar;
    int checkLogin;

    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login = this;
        setContentView(R.layout.login_activity);
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
                if(etUsername.getText().length() < 1 || etPass.getText().length() < 1)
                    return;

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
                }, 2000);

                if(validarLogin()){
                    /*final int delay = 30000;
                    new Thread(new Runnable() {
                        public void run() {
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    //new Dados().inicializarSingletons(getApplicationContext());
                                    inicializarSingletons();
                                    Log.d("teste", "funciona");
                                    handler.postDelayed(this, delay);
                                }
                            }, delay);
                        }
                    }).start();*/
                    
                    SingletonGestorRestaurantes.getInstance(getApplicationContext()).getAllRestaurantesAPI(getApplicationContext());
                    SingletonGestorPedidos.getInstance(getApplicationContext()).getAllPedidosAPI(getApplicationContext());

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                    startActivity(intent);
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
    }

    private boolean validarLogin() {
        String username = etUsername.getText().toString();
        String password = etPass.getText().toString();

        SingletonGestorUsers.getInstance(this).validarLogin(this, username, password);
        String loginValido = SingletonGestorUsers.getInstance(this).getLoginValido();

        if(loginValido != null){
            Log.d("LoginTeste", loginValido);

            if(loginValido.equals("true")){
                SingletonGestorUsers.getInstance(this).getUserLogadoAPI(this, username, password);
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public void onDestroy() {
        thread.interrupt();
        super.onDestroy();
    }
}

package amsi.dei.estg.ipleiria.evo_menu.Views;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import amsi.dei.estg.ipleiria.evo_menu.R;

public class LoginActivity extends AppCompatActivity
{
    public static final String MAIL = "amsi.dei.estg.ipleiria.projetofinal.mail";
    private EditText etUsername, etPass;
    private Button btnLogin, btnRegistar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        etUsername = findViewById(R.id.etUsernameLogin);
        etPass = findViewById(R.id.etPasswordLogin);
        btnLogin = findViewById(R.id.btLogin);
        btnRegistar = findViewById(R.id.btRegistar);
        //bt click event
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                validarLogin(view);
            }
        });
        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                RegistarUser(view);
            }
        });
        ;
    }

    private void validarLogin(View view) {
        String mail = etUsername.getText().toString();
        String pass = etPass.getText().toString();

        //Validaçao mail
        if(!isMailValido(mail)){
            //Strings escritas no string.xml
            etUsername.setError(getString(R.string.textErrorMail));
            return;
        }

        if(!isPassValida(pass)){
            etPass.setError(getString(R.string.textErrorPass));
            return;
        }
        //Ligação entre atividades!! Envia tambem o email para o proximo.
        Intent intentMail = new Intent(this, MainMenuActivity.class);
        intentMail.putExtra("Mail", mail);
        startActivity(intentMail);

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

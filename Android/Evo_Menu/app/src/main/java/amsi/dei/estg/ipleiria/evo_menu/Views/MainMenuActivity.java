package amsi.dei.estg.ipleiria.evo_menu.Views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import amsi.dei.estg.ipleiria.evo_menu.R;

public class MainMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String DADOS_USER = "DADOS_USER";

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private FragmentManager fragmentManager;
    private String email;
    private static final String MAIL = "email" ;






    @Override
    protected void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main_menu);
        navigationView = findViewById(R.id.navView);
        drawer = findViewById(R.id.drawerLayout);
        fragmentManager = getSupportFragmentManager();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nopen, R.string.nclose);
        toggle.syncState();

        drawer.addDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(this);

        carregarLivrosFicheiro();

        carregarCabecalho();

        carregarFragmentoInicial();


    }

    private void carregarFragmentoInicial()
    {

    }

    private void carregarCabecalho()
    {
        email = getIntent().getStringExtra(LoginActivity.MAIL);

        SharedPreferences sharedPreferences = getSharedPreferences("DADOS_USER", Context.MODE_PRIVATE);
        if(email != null)
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(MAIL, email);
            editor.apply();
        }
        else
        {
            //a ver mais tarde, defvalue em vez de s1
            email = sharedPreferences.getString(MAIL, "Email não definido");
        }

        View headerView = navigationView.getHeaderView(0);
        TextView tvMail = headerView.findViewById(R.id.tvHeaderMail);
        tvMail.setText(email);
    }

    private void carregarLivrosFicheiro() {
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int opcao = item.getItemId();
        Fragment fragment = null;
        switch(opcao)
        {
            case R.id.navRestaurantes:
                /*Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);*/
                fragment = new GrelhaRestaurantesFragment();
                break;

            case R.id.navFavoritos:
                fragment = new ListaRestaurantesFavFragment();
                break;

            case R.id.navEditarPerfil:
                setContentView(R.layout.perfil_useredit_activity);
                break;
        }
        if(fragment != null)
        {
            fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


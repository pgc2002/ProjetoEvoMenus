package amsi.dei.estg.ipleiria.evo_menu.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorCategorias;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorHorarios;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorItems;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorMenus;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorMesas;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorPagamentos;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorPedidos;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorRestaurantes;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorUsers;
import amsi.dei.estg.ipleiria.evo_menu.Model.User;

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
        /*Runnable objRunnable = new Runnable() {
            @Override
            public void run() {
                try{
                    SingletonGestorPedidos.getInstance(getApplicationContext()).getAllPedidosAPI(getApplicationContext(), SingletonGestorUsers.getInstance(getApplicationContext()).getUserLogado().getId());
                    SingletonGestorRestaurantes.getInstance(getApplicationContext()).getAllRestaurantesAPI(getApplicationContext());
                    SingletonGestorMenus.getInstance(getApplicationContext()).getAllMenusAPI(getApplicationContext());
                    SingletonGestorItems.getInstance(getApplicationContext()).getAllItemsAPI(getApplicationContext());
                    SingletonGestorCategorias.getInstance(getApplicationContext()).getAllCategoriasAPI(getApplicationContext());
                    SingletonGestorHorarios.getInstance(getApplicationContext()).getAllHorariosAPI(getApplicationContext());
                    SingletonGestorPagamentos.getInstance(getApplicationContext()).getAllPagamentosAPI(getApplicationContext());
                    SingletonGestorMesas.getInstance(getApplicationContext()).getAllRestaurantesAPI(getApplicationContext());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        Thread objBgThread = new Thread(objRunnable);
        objBgThread.start();*/

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

        carregarCabecalho();

        carregarFragmentoInicial();
    }

    private void carregarFragmentoInicial()
    {
        Fragment fragment = new PaginaInicialFragment();
        fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
    }

    private void carregarCabecalho()
    {

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

        Bundle extras = getIntent().getExtras();

        //SingletonGestorUsers.getInstance(this).getUserAPI(this, extras.getInt("id"), extras.getString("pass"));

        /*User user = SingletonGestorUsers.getInstance(this).getUserLogado();

        Log.d("userLogado", user.getNome());*/

        /*SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MAIL, user.getNome());
        editor.apply();*/

        View headerView = navigationView.getHeaderView(0);
        TextView tvMail = headerView.findViewById(R.id.tvHeaderMail);
        tvMail.setText(email);
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
                fragment = new ListaRestaurantesFragment();
                break;

            case R.id.navFavoritos:
                fragment = new ListaRestaurantesFavFragment();
                break;

            case R.id.navVerPerfil:
                fragment = new VerPerfilFragment();
                /*Intent intent = new Intent(MainMenuActivity.this, VerPerfilActivity.class);
                MainMenuActivity.this.startActivity(intent);*/
                //setContentView(R.layout.activity_ver_perfil);
                break;

            case R.id.navHistoricoPedidos:
                fragment = new HistoricoPedidosFragment();
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

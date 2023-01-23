package amsi.dei.estg.ipleiria.evo_menu.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorItems;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorMenus;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorMesas;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorPagamentos;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorPedidos;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorRestaurantes;

import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorUsers;
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
        //inicializarSingletons();

        new Thread(new Runnable() {
            public void run() {
                try {
                    inicializarSingletons();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();


        final Handler handler = new Handler();
        final int delay = 300000;

        new Thread(new Runnable() {
            public void run() {
                handler.postDelayed(new Runnable() {
                    public void run() {
                        atualizarSingletons();
                        handler.postDelayed(this, delay);
                    }
                }, delay);
            }
        }).start();

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

    // TESTAR DPS !!!!!!!
    private void atualizarSingletons() {
        SingletonGestorRestaurantes.getInstance(getApplicationContext()).getAllRestaurantesAPI(getApplicationContext());
        SingletonGestorUsers.getInstance(getApplicationContext()).getMoradaAPI(getApplicationContext());
        SingletonGestorCategorias.getInstance(getApplicationContext()).getAllCategoriasAPI(getApplicationContext());
        SingletonGestorMenus.getInstance(getApplicationContext()).getAllMenusAPI(getApplicationContext());
        SingletonGestorItems.getInstance(getApplicationContext()).getAllItemsAPI(getApplicationContext());
        SingletonGestorPedidos.getInstance(getApplicationContext()).getAllPedidosAPI(getApplicationContext());
        SingletonGestorPagamentos.getInstance(getApplicationContext()).getAllPagamentosAPI(getApplicationContext());
        SingletonGestorMesas.getInstance(getApplicationContext()).getAllRestaurantesAPI(getApplicationContext());
    }

    private void inicializarSingletons() throws InterruptedException {
        //Thread.sleep(3000);
        SingletonGestorUsers.getInstance(getApplicationContext()).getMoradaAPI(getApplicationContext());
        SingletonGestorCategorias.getInstance(getApplicationContext()).getAllCategoriasAPI(getApplicationContext());
        SingletonGestorMenus.getInstance(getApplicationContext()).getAllMenusAPI(getApplicationContext());
        SingletonGestorItems.getInstance(getApplicationContext()).getAllItemsAPI(getApplicationContext());
        SingletonGestorPagamentos.getInstance(getApplicationContext()).getAllPagamentosAPI(getApplicationContext());
        SingletonGestorMesas.getInstance(getApplicationContext()).getAllRestaurantesAPI(getApplicationContext());
        SingletonGestorPedidos.getInstance(getApplicationContext()).getAllPedidosAPI(getApplicationContext());
    }

    private void carregarFragmentoInicial()
    {
        //Fragment fragment = new PaginaInicialFragment();
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        Fragment fragment = new ListaRestaurantesFragment();
        fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
    }

    private void carregarCabecalho()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("DADOS_USER", Context.MODE_PRIVATE);
        /*if(email != null)
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(MAIL, email);
            editor.apply();
        }
        else
        {
            //a ver mais tarde, defvalue em vez de s1
            email = sharedPreferences.getString(MAIL, "Email não definido");
        }*/

        //Bundle extras = getIntent().getExtras();

        //SingletonGestorUsers.getInstance(this).getUserAPI(this, extras.getInt("id"), extras.getString("pass"));

        /*User user = SingletonGestorUsers.getInstance(this).getUserLogado();

        Log.d("userLogado", user.getNome());*/

        /*SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MAIL, user.getNome());
        editor.apply();*/

        View headerView = navigationView.getHeaderView(0);
        TextView tvUsername = headerView.findViewById(R.id.tvHeaderUsername);
        tvUsername.setText(SingletonGestorUsers.getInstance(this).getUserLogado().getUsername());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int opcao = item.getItemId();
        Fragment fragment = null;
        switch(opcao)
        {
            case R.id.navRestaurantes:
                fragment = new ListaRestaurantesFragment();
                break;

            case R.id.navFavoritos:
                fragment = new ListaRestaurantesFavFragment();
                break;

            case R.id.navVerPerfil:
                fragment = new VerPerfilFragment();
                break;

            case R.id.navHistoricoPedidos:
                fragment = new HistoricoPedidosFragment();
                break;

            case R.id.navLogout:
                LoginActivity.login.finish();
                SingletonGestorUsers.getInstance(getApplicationContext()).logout();
                finish();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
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

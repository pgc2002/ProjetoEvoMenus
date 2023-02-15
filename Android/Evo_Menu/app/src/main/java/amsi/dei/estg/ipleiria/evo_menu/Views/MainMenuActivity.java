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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Model.Restaurante;
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

        /*final Handler handler = new Handler();
        final int delay = 300000;
        //final int delay = 10000;

        new Thread(new Runnable() {
            public void run() {
                handler.postDelayed(new Runnable() {
                    public void run() {
                        atualizarSingletons();
                        handler.postDelayed(this, delay);
                    }
                }, delay);
            }
        }).start();*/

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

        //verificarUserLogadoNaBd();

        //preencherRestaurantesNaBd();

        carregarCabecalho();

        carregarFragmentoInicial();
    }

    private void preencherRestaurantesNaBd() {
        ArrayList<Restaurante> restaurantesApi = SingletonGestorRestaurantes.getInstance(getApplicationContext()).getRestaurantes();
        ArrayList<Restaurante> restaurantesBd = SingletonGestorRestaurantes.getInstance(getApplicationContext()).getRestaurantesDB();

        if (!restaurantesApi.isEmpty()){
            for (int i = 0; i < restaurantesApi.size(); i++){
                if (restaurantesBd.isEmpty())
                    SingletonGestorRestaurantes.getInstance(getApplicationContext()).adicionarRestauranteBD(restaurantesApi.get(i));
                else
                    for (int p = 0; p < restaurantesBd.size(); p++) {
                        boolean naoEstaNaBd = true;
                        if (restaurantesApi.get(i).getId() == restaurantesBd.get(p).getId())
                            naoEstaNaBd = false;

                        if (naoEstaNaBd)
                            SingletonGestorRestaurantes.getInstance(getApplicationContext()).adicionarRestauranteBD(restaurantesApi.get(i));
                    }
            }
        }
    }

    private void verificarUserLogadoNaBd() {
        ArrayList<User> usersbd = SingletonGestorUsers.getInstance(getApplicationContext()).getUsersBD();
        if (!usersbd.isEmpty()){
            boolean usernaoestanabd = true;
            for (int i = 0; i<usersbd.size(); i++)
                if (usersbd.get(i).getId() == SingletonGestorUsers.getInstance(getApplicationContext()).getUserLogado().getId())
                    usernaoestanabd = false;

            if (usernaoestanabd)
                SingletonGestorUsers.getInstance(getApplicationContext()).adicionarUserBD(SingletonGestorUsers.getInstance(getApplicationContext()).getUserLogado());
        }else
            SingletonGestorUsers.getInstance(getApplicationContext()).adicionarUserBD(SingletonGestorUsers.getInstance(getApplicationContext()).getUserLogado());
    }

    private void atualizarSingletons() {
        Toast.makeText(getApplicationContext(), "Dados atualizados", Toast.LENGTH_SHORT).show();
        SingletonGestorRestaurantes.getInstance(getApplicationContext()).getAllRestaurantesAPI(getApplicationContext());
        SingletonGestorCategorias.getInstance(getApplicationContext()).getAllCategoriasAPI(getApplicationContext());
        SingletonGestorMenus.getInstance(getApplicationContext()).getAllMenusAPI(getApplicationContext());
        SingletonGestorItems.getInstance(getApplicationContext()).getAllItemsAPI(getApplicationContext());
        //SingletonGestorPedidos.getInstance(getApplicationContext()).getAllPedidosAPI(getApplicationContext());
        //SingletonGestorPagamentos.getInstance(getApplicationContext()).getAllPagamentosAPI(getApplicationContext());
        SingletonGestorMesas.getInstance(getApplicationContext()).getAllRestaurantesAPI(getApplicationContext());
    }

    private void inicializarSingletons() throws InterruptedException {
        Thread.sleep(1000);
        SingletonGestorRestaurantes.getInstance(getApplicationContext()).getAllRestaurantesAPI(getApplicationContext());
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
        if(getIntent().getExtras().getBoolean("refreshPerfil")){
            Fragment fragment = new VerPerfilFragment();
            fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
            return;
        }

        new Thread(new Runnable() {
            public void run() {
                try {
                    inicializarSingletons();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        Fragment fragment = new ListaRestaurantesFragment();
        fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
    }

    private void carregarCabecalho() {
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

        //SingletonGestorUsers.getInstance(this).getUserAPI(this, extras.getInt("id"), extras.getString("pass"));

        /*User user = SingletonGestorUsers.getInstance(this).getUserLogado();

        Log.d("userLogado", user.getNome());*/

        /*SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MAIL, user.getNome());
        editor.apply();*/

        View headerView = navigationView.getHeaderView(0);
        TextView tvUsername = headerView.findViewById(R.id.tvHeaderUsername);

        if(SingletonGestorUsers.getInstance(this).getUserLogado() == null)
            tvUsername.setText(getIntent().getExtras().getString("username"));
        else
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

            case R.id.navVerPerfil:
                fragment = new VerPerfilFragment();
                break;

            case R.id.navHistoricoPedidos:
                fragment = new HistoricoPedidosFragment();
                break;

            case R.id.navLogout:
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

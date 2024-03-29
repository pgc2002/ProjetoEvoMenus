package amsi.dei.estg.ipleiria.evo_menu.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Adaptadores.ListaRestaurantesAdaptador;
import amsi.dei.estg.ipleiria.evo_menu.Listeners.RestaurantesListener;
import amsi.dei.estg.ipleiria.evo_menu.Model.Restaurante;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorRestaurantes;
import amsi.dei.estg.ipleiria.evo_menu.R;

public class ListaRestaurantesFragment extends Fragment implements RestaurantesListener {

    public static final int CODE_REQUEST_ADICIONAR = 1;
    private static final int CODE_REQUEST_EDITAR = 2;

    private ListView lvRestaurantes;

    private Button fabActionButton;

    private ListaRestaurantesAdaptador adaptador;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListaRestaurantesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_restaurantes, container, false);

        setHasOptionsMenu(true);

        lvRestaurantes = view.findViewById(R.id.lvListaRestaurantes);

        SingletonGestorRestaurantes.getInstance(getContext()).getAllRestaurantesAPI(getContext());
        adaptador = new ListaRestaurantesAdaptador(getContext(), SingletonGestorRestaurantes.getInstance(getContext()).getRestaurantesDB());
        /*ArrayList<Restaurante> restaurantes = SingletonGestorRestaurantes.getInstance(getContext()).getRestaurantes();
        adaptador = new ListaRestaurantesAdaptador(getContext(), restaurantes);*/

        lvRestaurantes.setAdapter(adaptador);

        lvRestaurantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Intent intent = new Intent(getContext(), DetalhesRestauranteActivity.class);
                //intent.putExtra(DetalhesRestauranteActivity.ID_RESTAURANTE, l);
                intent.putExtra("idRestaurante", SingletonGestorRestaurantes.getInstance(getContext()).getRestaurantes().get(position).getId());
                //SingletonGestorRestaurantes.getInstance(getContext()).getHorarioAPI(getContext(),SingletonGestorRestaurantes.getInstance(getContext()).getRestaurantes().get(position).getId());
                startActivity(intent);
            }
        });

        SingletonGestorRestaurantes.getInstance(getContext()).setRestaurantesListener(this);

        return view;
    }

    @Override
    public void onRefreshListaRestaurantes(ArrayList<Restaurante> listaRestaurantes) {
        if(listaRestaurantes != null)
        {
            lvRestaurantes.setAdapter(new ListaRestaurantesAdaptador(getContext(), listaRestaurantes));
        }
    }
}

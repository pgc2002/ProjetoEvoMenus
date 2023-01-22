package amsi.dei.estg.ipleiria.evo_menu.Views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Adaptadores.ListaPedidosAdaptador;
import amsi.dei.estg.ipleiria.evo_menu.Listeners.PedidosListener;
import amsi.dei.estg.ipleiria.evo_menu.Model.Pedido;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorPedidos;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorRestaurantes;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorUsers;
import amsi.dei.estg.ipleiria.evo_menu.R;

public class HistoricoPedidosFragment extends Fragment implements PedidosListener {

    public static final int CODE_REQUEST_ADICIONAR = 1;
    private static final int CODE_REQUEST_EDITAR = 2;

    private ListView lvPedidos;

    private ListaPedidosAdaptador adaptador;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoricoPedidosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historico_pedidos, container, false);

        setHasOptionsMenu(true);

        lvPedidos = view.findViewById(R.id.lvPedidos);

        SingletonGestorRestaurantes.getInstance(getContext()).getAllRestaurantesAPI(getContext());
        //SingletonGestorPedidos.getInstance(getContext()).getAllPedidosAPI(getContext(), SingletonGestorUsers.getInstance(getContext()).getUserLogado().getId());
        SingletonGestorPedidos.getInstance(getContext()).getAllPedidosAPI(getContext());
        ArrayList<Pedido> pedidos = SingletonGestorPedidos.getInstance(getContext()).getPedidos();
        adaptador = new ListaPedidosAdaptador(getContext(), pedidos);

        lvPedidos.setAdapter(adaptador);

        lvPedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Log.d("coisas", "aconteceram");
                /*Intent intent = new Intent(getContext(), DetalhesRestauranteActivity.class);
                intent.putExtra(DetalhesRestauranteActivity.ID_RESTAURANTE, l);
                startActivityForResult(intent, CODE_REQUEST_EDITAR);
                Toast.makeText(getContext(), SingletonGestorRestaurantes.getInstance(getContext()).getRestaurantesDB().get(position).getNome(),
                        Toast.LENGTH_SHORT).show();*/
                //Chamar atividade detalhada
            }
        });

        SingletonGestorPedidos.getInstance(getContext()).setPedidosListener(this);
        //SingletonGestorPedidos.getInstance(getContext()).getAllPedidosAPI(getContext(), 20);

        return view;
    }

    @Override
    public void onRefreshListaPedidos(ArrayList<Pedido> listaPedidos) {
        if(listaPedidos != null)
        {
            lvPedidos.setAdapter(new ListaPedidosAdaptador(getContext(), listaPedidos));
        }
    }
}
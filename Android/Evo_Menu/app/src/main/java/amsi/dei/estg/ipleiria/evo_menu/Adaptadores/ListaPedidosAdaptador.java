package amsi.dei.estg.ipleiria.evo_menu.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Model.Pedido;
import amsi.dei.estg.ipleiria.evo_menu.Model.Restaurante;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorRestaurantes;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorUsers;
import amsi.dei.estg.ipleiria.evo_menu.R;

public class ListaPedidosAdaptador extends BaseAdapter {
    private ArrayList<Pedido> listaPedidos;
    private Context contexto;
    private LayoutInflater inflater;

    public ListaPedidosAdaptador(Context context, ArrayList<Pedido> lista)
    {
        this.contexto = context;

        ArrayList<Pedido> listaFiltrada = new ArrayList<>();

        for (Pedido pedido: lista) {
            if(SingletonGestorUsers.getInstance(contexto).getUserLogado().getId() == pedido.getId_cliente())
                listaFiltrada.add(pedido);
        }

        this.listaPedidos = listaFiltrada;
    }

    @Override
    public int getCount()
    {
        return listaPedidos.size();
    }

    @Override
    public Object getItem(int position)
    {
        return listaPedidos.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return listaPedidos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //preenche cada quadrado
        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_historico_pedidos, null);

        //Preenchimento do view
        ListaPedidosAdaptador.ViewHolderLista viewHolder = (ListaPedidosAdaptador.ViewHolderLista) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ListaPedidosAdaptador.ViewHolderLista(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.update(listaPedidos.get(position));
        return convertView;
    }

    private class ViewHolderLista {
        private TextView tvNomeRestaurante, tvValorTotal;
        //private ImageView ivRestauranteBackground;

        public ViewHolderLista(View view) {
            tvNomeRestaurante = view.findViewById(R.id.tvNomeItemPagamento);
            tvValorTotal = view.findViewById(R.id.tvValorTotal);
            //ivRestauranteBackground = view.findViewById(R.id.ivRestauranteBackground);
        }

        public void update(Pedido pedido) {
            Restaurante restaurante = SingletonGestorRestaurantes.getInstance(contexto).getRestaurante(pedido.getId_restaurante());
            tvNomeRestaurante.setText(restaurante.getNome());
            tvValorTotal.setText(pedido.getValor_total()+" €");
            //tvData.setText();

            //Glide.with(contexto).load(restaurante.getCapa()).placeholder(R.drawable.logoipl).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivCapa);
        }
    }
}

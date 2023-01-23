package amsi.dei.estg.ipleiria.evo_menu.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import amsi.dei.estg.ipleiria.evo_menu.Model.Morada;
import amsi.dei.estg.ipleiria.evo_menu.Model.SingletonGestorUsers;
import amsi.dei.estg.ipleiria.evo_menu.Model.User;
import amsi.dei.estg.ipleiria.evo_menu.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


public class VerPerfilFragment extends Fragment {

    public VerPerfilFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ver_perfil, container, false);

        TextView tvUsername = view.findViewById(R.id.tvUsername);
        TextView tvNome = view.findViewById(R.id.tvNome);
        TextView tvEmail = view.findViewById(R.id.tvEmail);
        TextView tvTelemovel = view.findViewById(R.id.tvTelemovel);
        TextView tvNif = view.findViewById(R.id.tvNif);

        TextView tvRua = view.findViewById(R.id.tvRua);
        TextView tvCodPost = view.findViewById(R.id.tvCodPost);
        TextView tvPais = view.findViewById(R.id.tvPais);
        TextView tvCidade = view.findViewById(R.id.tvCidade);

        User user = SingletonGestorUsers.getInstance(getContext()).getUserLogado();
        SingletonGestorUsers.getInstance(getContext()).getMoradaAPI(getContext());

        if(user != null) {
            tvUsername.setText(user.getUsername());
            tvNome.setText(user.getNome());
            tvEmail.setText(user.getEmail());
            tvTelemovel.setText(user.getTelemovel());
            tvNif.setText(user.getNif());
        }

        Morada morada = user.getMorada();

        if(morada != null){
            tvPais.setText(morada.getPais());
            tvCidade.setText(morada.getCidade());
            tvRua.setText(morada.getRua());
            tvCodPost.setText(morada.getCodpost());
        }


        Button btn = view.findViewById(R.id.btEditarPerfil);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment editarPerfil = new EditarPerfilFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contentFragment, editarPerfil);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
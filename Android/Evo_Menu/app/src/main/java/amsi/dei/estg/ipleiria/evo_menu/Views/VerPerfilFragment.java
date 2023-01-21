package amsi.dei.estg.ipleiria.evo_menu.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import amsi.dei.estg.ipleiria.evo_menu.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.ViewGroup;


public class VerPerfilFragment extends Fragment {

    public VerPerfilFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ver_perfil, container, false);

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
package com.example.sigaamobile.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sigaamobile.MainActivity;
import com.example.sigaamobile.R;
import com.example.sigaamobile.models.mAtividadesDisciplina;
import com.example.sigaamobile.ui.adapter.AtividadesDisciplinasAdapter;
import com.example.sigaamobile.utils.FromJson;
import com.example.sigaamobile.utils.SigaaSharedPreferences;

import java.util.ArrayList;

public class AtividadesFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_atividades, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity.setNavBarButton(requireActivity(), R.id.btn_voltar);
        MainActivity.setNavBarTitle(requireActivity(), R.string.atividades);
        SigaaSharedPreferences preferences = new SigaaSharedPreferences(requireContext());
        RecyclerView listaAtividadesDisciplina = view.findViewById(R.id.recycler_view_atividades);

        ArrayList<mAtividadesDisciplina> atividades = FromJson.getmAtividadesDisciplina(
                preferences.getInt("idAluno"),
                requireActivity()
        );

        listaAtividadesDisciplina.setAdapter(
                new AtividadesDisciplinasAdapter(
                        atividades,
                        requireContext()
                )
        );

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        listaAtividadesDisciplina.setLayoutManager(layoutManager);
    }
}
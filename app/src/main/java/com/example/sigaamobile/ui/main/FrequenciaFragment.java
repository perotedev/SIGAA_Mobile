package com.example.sigaamobile.ui.main;

import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sigaamobile.MainActivity;
import com.example.sigaamobile.R;
import com.example.sigaamobile.models.mFrequenciaTodasAulas;
import com.example.sigaamobile.ui.adapter.FrequenciaAdapter;
import com.example.sigaamobile.utils.FromJson;
import com.example.sigaamobile.utils.SigaaSharedPreferences;

import java.util.ArrayList;

public class FrequenciaFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.slide_left));
        setEnterTransition(inflater.inflateTransition(R.transition.slide_right));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frequencia, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity.setNavBarButton(requireActivity(), R.id.btn_voltar);
        MainActivity.setNavBarTitle(requireActivity(), R.string.frequencia);
        SigaaSharedPreferences preferences = new SigaaSharedPreferences(requireContext());

        RecyclerView listagemFrequencia = view.findViewById(R.id.recycler_view_frequencia);
        ArrayList<mFrequenciaTodasAulas> listaFrequencias = FromJson.getmFrequenciaTodasAulas(
                preferences.getInt("idAluno"),
                requireActivity()
        );

        listagemFrequencia.setAdapter(
                new FrequenciaAdapter(
                        listaFrequencias,
                        requireContext()
                )
        );

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        listagemFrequencia.setLayoutManager(layoutManager);
    }
}
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
import com.example.sigaamobile.models.mDocumento;
import com.example.sigaamobile.ui.adapter.DocumentosAdapter;
import com.example.sigaamobile.utils.FromJson;

import java.util.ArrayList;

public class DocumentosFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_documentos, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity.setNavBarButton(requireActivity(), R.id.btn_voltar);
        MainActivity.setNavBarTitle(requireActivity(), R.string.documentos);

        RecyclerView listagemDocumentos = view.findViewById(R.id.recycler_view_documentos);
        ArrayList<mDocumento> arraymDocumentos = FromJson.getmDocumentos(requireActivity());

        listagemDocumentos.setAdapter(
                new DocumentosAdapter(
                        arraymDocumentos,
                        requireContext()
                )
        );
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        listagemDocumentos.setLayoutManager(layoutManager);
    }
}
package com.example.sigaamobile.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sigaamobile.MainActivity;
import com.example.sigaamobile.R;
import com.example.sigaamobile.models.mNotasDisciplina;
import com.example.sigaamobile.ui.adapter.NotasAdapter;
import com.example.sigaamobile.ui.dialog.LegendaNotasDialogFragment;
import com.example.sigaamobile.utils.FromJson;
import com.example.sigaamobile.utils.SigaaSharedPreferences;

import java.util.ArrayList;

public class NotasFragment extends Fragment {
    ImageView btnHelp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notas, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity.setNavBarButton(requireActivity(), R.id.btn_voltar);
        MainActivity.setNavBarTitle(requireActivity(), R.string.notas);
        this.btnHelp = requireActivity().findViewById(R.id.btn_help);
        this.btnHelp.setVisibility(View.VISIBLE);

        SigaaSharedPreferences preferences = new SigaaSharedPreferences(requireContext());
        RecyclerView listagemNotas = view.findViewById(R.id.recycler_view_notas);

        ArrayList<mNotasDisciplina> notasDisciplinas = FromJson.getmNotasDisciplina(
                preferences.getInt("idAluno"),
                requireActivity()
        );

        listagemNotas.setAdapter(
                new NotasAdapter(
                        notasDisciplinas,
                        requireContext()
                )
        );
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        listagemNotas.setLayoutManager(layoutManager);

        this.btnHelp.setOnClickListener(v -> this.showLegenda());
    }

    private void showLegenda(){
        LegendaNotasDialogFragment dialogFragment = new LegendaNotasDialogFragment();
        dialogFragment.show(requireActivity().getSupportFragmentManager(), "notas");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.btnHelp.setVisibility(View.INVISIBLE);
    }
}
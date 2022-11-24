package com.example.sigaamobile.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sigaamobile.MainActivity;
import com.example.sigaamobile.R;
import com.example.sigaamobile.models.mAtividade;
import com.example.sigaamobile.utils.DateTransform;
import com.google.gson.Gson;

public class VerAtividadeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ver_atividade, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAtividade mAtividade = new Gson().fromJson(
                    requireArguments().getString("atividade"),
                    mAtividade.class
        );

        MainActivity.setNavBarButton(requireActivity(), R.id.btn_voltar);
        MainActivity.setNavBarTitle(requireActivity(), R.string.detalhes_atividades);

        TextView titleAtividade = view.findViewById(R.id.title_card_atividade);
        TextView titleDetalheCard = view.findViewById(R.id.atividade_title);
        TextView descricaoAtividade = view.findViewById(R.id.atividade_descricao);
        TextView inicioAtividade = view.findViewById(R.id.data_inicio_atividade);
        TextView fimAtividade = view.findViewById(R.id.data_fim_atividade);
        TextView statusAtividade = view.findViewById(R.id.status_da_atividade);
        ImageView btnArrowRight = view.findViewById(R.id.btn_ir_atividade);

        btnArrowRight.setVisibility(View.INVISIBLE);
        titleDetalheCard.setHeight(0);
        titleAtividade.setText(mAtividade.getTitulo());
        descricaoAtividade.setText(mAtividade.getDescricao());
        inicioAtividade.setText(DateTransform.transformToStringDate(mAtividade.getDataInicio()));
        fimAtividade.setText(DateTransform.transformToStringDate(mAtividade.getDataFim()));
        statusAtividade.setText(mAtividade.getStatus());
    }
}
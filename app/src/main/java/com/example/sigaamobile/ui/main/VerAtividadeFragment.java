package com.example.sigaamobile.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
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
        TextView textAreaDoc = view.findViewById(R.id.doc_anexado);
        RelativeLayout btnSelectDoc = view.findViewById(R.id.btn_selecionar_doc);
        AppCompatButton btnAnexoAtividade = view.findViewById(R.id.btn_baixar_anexo_atividade);

        btnArrowRight.setVisibility(View.INVISIBLE);
        titleDetalheCard.setHeight(0);
        titleAtividade.setText(mAtividade.getTitulo());
        descricaoAtividade.setText(mAtividade.getDescricao());
        inicioAtividade.setText(DateTransform.transformToStringDate(mAtividade.getDataInicio()));
        fimAtividade.setText(DateTransform.transformToStringDate(mAtividade.getDataFim()));
        statusAtividade.setText(mAtividade.getStatus());

        textAreaDoc.setOnClickListener(v -> {
//            String path = Environment.getExternalStorageDirectory()+"/teste_sigaa/";
//            Uri uri = Uri.parse(path);
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            intent = Intent.createChooser(intent, "Choose a file");
            startActivity(intent);
        });

        btnSelectDoc.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            intent = Intent.createChooser(intent, "Choose a file");
            startActivity(intent);
        });

        btnAnexoAtividade.setOnClickListener(v -> {
            String link = "https://www.caceres.mt.gov.br/fotos_institucional_downloads/2.pdf";
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            requireContext().startActivity(browserIntent);
        });

    }
}
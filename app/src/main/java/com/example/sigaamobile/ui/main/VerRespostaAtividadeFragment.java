package com.example.sigaamobile.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.sigaamobile.MainActivity;
import com.example.sigaamobile.R;
import com.example.sigaamobile.models.mAtividade;
import com.example.sigaamobile.models.mRespostaAtividade;
import com.example.sigaamobile.utils.DateTransform;
import com.example.sigaamobile.utils.FromJson;
import com.example.sigaamobile.utils.SigaaSharedPreferences;
import com.google.gson.Gson;

public class VerRespostaAtividadeFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_ver_resposta_atividade, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity.setNavBarButton(requireActivity(), R.id.btn_voltar);
        MainActivity.setNavBarTitle(requireActivity(), R.string.resposta_atividade);

        SigaaSharedPreferences preferences = new SigaaSharedPreferences(requireContext());
        mAtividade mAtividade = new Gson().fromJson(
                requireArguments().getString("atividade"),
                mAtividade.class
        );

        mRespostaAtividade mRespostaAtividade = FromJson.getmRespostaAtividade(
                mAtividade.getAtividadeId(),
                preferences.getInt("idAluno"),
                requireActivity()
        );

        System.out.println("atividade: "+ mAtividade);
        System.out.println("resposta: "+ mRespostaAtividade);

        TextView titleAtividade = view.findViewById(R.id.title_card_atividade_resposta);
        TextView titleDetalheCard = view.findViewById(R.id.atividade_title);
        TextView descricaoAtividade = view.findViewById(R.id.atividade_descricao);
        TextView inicioAtividade = view.findViewById(R.id.data_inicio_atividade);
        TextView fimAtividade = view.findViewById(R.id.data_fim_atividade);
        TextView statusAtividade = view.findViewById(R.id.status_da_atividade);
        ImageView btnArrowRight = view.findViewById(R.id.btn_ir_atividade);
        TextView comentarioAluno = view.findViewById(R.id.comentario_aluno_content_resposta);
        TextView comentarioProfessor = view.findViewById(R.id.comentario_professor_content);
        AppCompatButton btnAnexoBaixarResposta = view.findViewById(R.id.btn_baixar_reposta);
        AppCompatButton btnBaixarCorrecao = view.findViewById(R.id.btn_baixar_correcao);

        btnArrowRight.setVisibility(View.INVISIBLE);
        titleDetalheCard.setHeight(0);
        titleAtividade.setText(mAtividade.getTitulo());
        descricaoAtividade.setText(mAtividade.getDescricao());
        inicioAtividade.setText(DateTransform.transformToStringDate(mAtividade.getDataInicio(), DateTransform.SECONDS));
        fimAtividade.setText(DateTransform.transformToStringDate(mAtividade.getDataFim(), DateTransform.SECONDS));
        statusAtividade.setText(mAtividade.getStatus());
        comentarioAluno.setText(mRespostaAtividade.getComentarioAluno());
        comentarioProfessor.setText(mRespostaAtividade.getComentarioProfessor());

        ViewGroup.MarginLayoutParams margins = new ViewGroup.MarginLayoutParams(descricaoAtividade.getLayoutParams());
        margins.setMargins(0,0,0,0);
        descricaoAtividade.setLayoutParams(margins);

        btnAnexoBaixarResposta.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mRespostaAtividade.getAnexoRespostaUrl()));
            requireContext().startActivity(browserIntent);
        });

        if (mRespostaAtividade.getAnexoCorrecaoUrl().equals("")){
            ViewGroup.LayoutParams params = btnBaixarCorrecao.getLayoutParams();
            params.height = 0;
            btnBaixarCorrecao.setLayoutParams(params);
        } else {
            btnBaixarCorrecao.setOnClickListener(v -> {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mRespostaAtividade.getAnexoCorrecaoUrl()));
                requireContext().startActivity(browserIntent);
            });
        }

    }
}
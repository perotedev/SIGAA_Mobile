package com.example.sigaamobile.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.sigaamobile.MainActivity;
import com.example.sigaamobile.R;
import com.example.sigaamobile.enums.eAlunoStatus;
import com.example.sigaamobile.models.mAluno;
import com.example.sigaamobile.models.mCurso;
import com.example.sigaamobile.models.mDadosAcademicos;
import com.example.sigaamobile.ui.dialog.DialogAlertaOnBackMenuFragment;
import com.example.sigaamobile.utils.AnimateChangeHeight;
import com.example.sigaamobile.utils.FromJson;
import com.example.sigaamobile.utils.SigaaSharedPreferences;

public class MenuFragment extends Fragment {
    private mAluno mAluno = new mAluno();
    private mDadosAcademicos mDadosAcademicos = new mDadosAcademicos();
    private mCurso mCurso = new mCurso();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                DialogAlertaOnBackMenuFragment dialog = new DialogAlertaOnBackMenuFragment();
                dialog.show(requireActivity().getSupportFragmentManager(), "sair");
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity.setNavBarButton(requireActivity(), R.id.btn_main_menu);

        SigaaSharedPreferences preferences = new SigaaSharedPreferences(requireContext());
        this.mAluno = FromJson.getmAluno(preferences.getInt("userId"), requireActivity());
        this.mDadosAcademicos = FromJson.getmDadosAcademicos(mAluno.getIdAluno(), requireActivity());
        this.mCurso = FromJson.getmCurso(mAluno.getIdCurso(), requireActivity());
        preferences.setInt("idAluno", mAluno.getIdAluno());
        this.setDadosBasicos(view);
        this.setDadosAcademicos(view);

        ImageView arrowDown = view.findViewById(R.id.arrow_down);
        RelativeLayout expContent = view.findViewById(R.id.frame_exp_content);
        CardView cardInfo = view.findViewById(R.id.card_menu_general_info);
        int contentExpHeight = this.getContentExpHeight(expContent);
        CardView btnVerNotas = view.findViewById(R.id.btn_ver_notas);
        CardView btnFrequenccia = view.findViewById(R.id.btn_frequencia);
        CardView btnAtividades = view.findViewById(R.id.btn_atividades);
        CardView btnDocumentos = view.findViewById(R.id.btn_documentos);

        cardInfo.setOnClickListener(v -> {
            if (arrowDown.getRotation() == 180){
                showInfo(expContent,  0);
                arrowDown.animate().rotation(0f).setDuration(300).start();
            } else {
                showInfo(expContent, contentExpHeight);
                arrowDown.animate().rotation(180f).setDuration(300).start();
            }
        });

        btnVerNotas.setOnClickListener(v -> this.navigateTo(R.id.notasFragment));
        btnFrequenccia.setOnClickListener(v -> this.navigateTo(R.id.frequenciaFragment));
        btnAtividades.setOnClickListener(v -> this.navigateTo(R.id.atividadesFragment));
        btnDocumentos.setOnClickListener(v -> this.navigateTo(R.id.documentosFragment));
    }

    private void navigateTo(int fragmentId){
        NavHostFragment.findNavController(MenuFragment.this).navigate(fragmentId);
    }

    private void showInfo(RelativeLayout expContent, int newHeight){
        AnimateChangeHeight animateChangeHeight = new AnimateChangeHeight(expContent, newHeight);
        animateChangeHeight.updateAnimate();
    }

    private int getContentExpHeight(RelativeLayout expContent){
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        expContent.setVisibility(View.INVISIBLE);
        expContent.setLayoutParams(params);
        int wrapSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        expContent.measure(wrapSpec, wrapSpec);
        int height = expContent.getMeasuredHeight();
        params.height = 0;
        params.addRule(RelativeLayout.BELOW, R.id.header_exp_menu);
        expContent.setLayoutParams(params);
        expContent.setVisibility(View.VISIBLE);
        return height;
    }

    private void setDadosBasicos(View view) {
        TextView nomeAluno = view.findViewById(R.id.textViewExpandableHeader1);
        TextView matriculaAluno = view.findViewById(R.id.textViewExpandableHeader3);
        nomeAluno.setText(this.mAluno.getNomeAluno());
        matriculaAluno.setText(this.mAluno.getMatricula());
    }

    private void setDadosAcademicos(View view) {
        TextView curso = view.findViewById(R.id.text_curso_nome);
        TextView nivel = view.findViewById(R.id.text_nivel);
        TextView status = view.findViewById(R.id.text_status);
        TextView email = view.findViewById(R.id.text_email);
        TextView entrada = view.findViewById(R.id.text_entrada);
        TextView cr = view.findViewById(R.id.text_cr);
        TextView mc = view.findViewById(R.id.text_mc);
        TextView mcn = view.findViewById(R.id.text_mcn);
        TextView chObrigatoria = view.findViewById(R.id.text_ch_obrigatoria);
        TextView chOptativa = view.findViewById(R.id.text_ch_optativa);
        TextView chTotal = view.findViewById(R.id.text_ch_total);
        TextView chComplementar = view.findViewById(R.id.text_ch_complementar_pendente);
        String statusAluno = "INATIVO";

        if (mAluno.getStatusAluno() == eAlunoStatus.valueOf("ATIVO").ordinal()){
            statusAluno = "ATIVO";
        }

        curso.setText(this.mCurso.getNomeCurso());
        nivel.setText(this.mCurso.getNivelCurso());
        status.setText(statusAluno);
        email.setText(mAluno.getEmail());
        entrada.setText(mAluno.getDataEntrada());
        cr.setText(String.valueOf(this.mDadosAcademicos.getCr()));
        mc.setText(String.valueOf(this.mDadosAcademicos.getMc()));
        mcn.setText(String.valueOf(this.mDadosAcademicos.getMcn()));
        chObrigatoria.setText(String.valueOf(this.mDadosAcademicos.getCh_obrigatoriaPendente()));
        chOptativa.setText(String.valueOf(this.mDadosAcademicos.getCh_optativaPendente()));
        chTotal.setText(String.valueOf(this.mDadosAcademicos.getCh_totalCurriculo()));
        chComplementar.setText(String.valueOf(this.mDadosAcademicos.getCh_complementarPendente()));
    }

    @Override
    public void onResume() {
        super.onResume();

        MainActivity.setNavBarTitle(
                requireActivity(),
                R.string.app_name
        );
    }
}

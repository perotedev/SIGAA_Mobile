package com.example.sigaamobile.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.sigaamobile.MainActivity;
import com.example.sigaamobile.R;
import com.example.sigaamobile.models.mAtividade;
import com.example.sigaamobile.ui.dialog.AlertaEnviarRespostaDialogFragment;
import com.example.sigaamobile.ui.dialog.AtividadeEnviadaDialogFragment;
import com.example.sigaamobile.utils.AttatchDocument;
import com.example.sigaamobile.utils.DateTransform;
import com.google.gson.Gson;

public class VerAtividadeFragment extends Fragment {
    private final AttatchDocument document = new AttatchDocument();
    private ActivityResultLauncher<Intent> someActivityResultLauncher;
    private TextView textAreaDoc;
    private static final String DOC_NAME = "documento_teste.pdf";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setExitTransition(inflater.inflateTransition(R.transition.slide_left));
        setEnterTransition(inflater.inflateTransition(R.transition.slide_right));

        // this line is because in fragments registerForActivityResult() must to be called before view is created
        this.someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    System.out.println("resultado: "+data);

                    if (data != null) {
                        this.document.setDocumentDirectoy(data.getData().getPath());
                        this.document.setDocumentSize();
                        textAreaDoc.setText(DOC_NAME);
                        this.showToast("Documento "+DOC_NAME+" anexado!");
                    } else {
                        this.showToast("Erro ao anexar documento!");
                    }
                } else {
                    this.showToast("Erro ao anexar documento!");
                }
            }
        );
    }

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
        RelativeLayout btnSelectDoc = view.findViewById(R.id.btn_selecionar_doc);
        AppCompatButton btnAnexoAtividade = view.findViewById(R.id.btn_baixar_anexo_atividade);
        AppCompatButton btnEnviarAtividade = view.findViewById(R.id.btn_baixar_enviar_reposta);
        this.textAreaDoc = view.findViewById(R.id.doc_anexado);

        btnArrowRight.setVisibility(View.INVISIBLE);
        titleDetalheCard.setHeight(0);
        titleAtividade.setText(mAtividade.getTitulo());
        descricaoAtividade.setText(mAtividade.getDescricao());
        inicioAtividade.setText(DateTransform.transformToStringDate(mAtividade.getDataInicio(), DateTransform.SECONDS));
        fimAtividade.setText(DateTransform.transformToStringDate(mAtividade.getDataFim(), DateTransform.SECONDS));
        statusAtividade.setText(mAtividade.getStatus());

        ViewGroup.MarginLayoutParams margins = new ViewGroup.MarginLayoutParams(descricaoAtividade.getLayoutParams());
        margins.setMargins(0,0,0,0);
        descricaoAtividade.setLayoutParams(margins);

        textAreaDoc.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("application/pdf");
            intent = Intent.createChooser(intent, "Selecionar arquivo...");
            this.someActivityResultLauncher.launch(intent);
        });

        btnSelectDoc.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("application/pdf");
            intent = Intent.createChooser(intent, "Selecionar arquivo...");
            this.someActivityResultLauncher.launch(intent);
        });

        btnAnexoAtividade.setOnClickListener(v -> {
            String link = "https://www.caceres.mt.gov.br/fotos_institucional_downloads/2.pdf";
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            requireContext().startActivity(browserIntent);
        });

        btnEnviarAtividade.setOnClickListener(v -> {
            if (textAreaDoc.getText().equals(DOC_NAME)){
                view.findViewById(R.id.login_loader).setVisibility(View.VISIBLE);
                this.animateAndNavigateToMenu();
            } else {
                AlertaEnviarRespostaDialogFragment dialog = new AlertaEnviarRespostaDialogFragment();
                dialog.show(requireActivity().getSupportFragmentManager(), "anexoResposta");
            }
        });
    }

    private void showToast(String message){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void animateAndNavigateToMenu(){
        ImageView logoSigaa = requireActivity().findViewById(R.id.logo_sigaa_loader);
        logoSigaa.animate().rotation(820f).setDuration(2200).start();

        // Não é válido para aplicação em uso real, estou apenas simulando o delay da consumo de API de login
        Handler handler = new Handler();
        handler.postDelayed(this::navigateToMenu, 1800);
    }

    private void navigateToMenu(){
        requireActivity().findViewById(R.id.login_loader).setVisibility(View.INVISIBLE);
        NavHostFragment.findNavController(VerAtividadeFragment.this).navigate(R.id.fragment_menu);
        AtividadeEnviadaDialogFragment dialog = new AtividadeEnviadaDialogFragment();
        dialog.setCancelable(false);
        dialog.show(requireActivity().getSupportFragmentManager(), "atividadeEnviada");
    }
}
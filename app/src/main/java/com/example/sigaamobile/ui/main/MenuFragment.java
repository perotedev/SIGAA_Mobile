package com.example.sigaamobile.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.sigaamobile.MainActivity;
import com.example.sigaamobile.R;
import com.example.sigaamobile.utils.AnimateChangeHeight;

public class MenuFragment extends Fragment {

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        // This callback will only be called when MyFragment is at least Started.
//        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
//            @Override
//            public void handleOnBackPressed() {
//                // Handle the back button event
//            }
//        };
//        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
//
//        // The callback can be enabled or disabled here or in handleOnBackPressed()
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

        btnVerNotas.setOnClickListener(v -> {
            NavHostFragment.findNavController(MenuFragment.this).navigate(R.id.notasFragment);
        });

        btnFrequenccia.setOnClickListener(v -> {
//            NavHostFragment.findNavController(MenuFragment.this).navigate(R.id.notasFragment);
        });

        btnAtividades.setOnClickListener(v -> {
//            NavHostFragment.findNavController(MenuFragment.this).navigate(R.id.notasFragment);
        });

        btnDocumentos.setOnClickListener(v -> {
            NavHostFragment.findNavController(MenuFragment.this).navigate(R.id.documentosFragment);
        });
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
}

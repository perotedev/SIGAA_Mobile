package com.example.sigaamobile.ui.main;

import androidx.activity.OnBackPressedCallback;
import androidx.cardview.widget.CardView;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.example.sigaamobile.R;
import com.example.sigaamobile.utils.AnimateChangeHeight;

import java.util.Objects;

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

        this.setNavBarButtons();
        ImageView arrowDown = view.findViewById(R.id.arrow_down);
        RelativeLayout expContent = view.findViewById(R.id.frame_exp_content);
        CardView cardInfo = view.findViewById(R.id.card_menu_general_info);
        int contentExpHeight = this.getContentExpHeight(expContent);

        cardInfo.setOnClickListener(v -> {
            if (arrowDown.getRotation() == 180){
                showInfo(expContent,  0);
                arrowDown.setRotation(0);
            } else {
                showInfo(expContent, contentExpHeight);
                arrowDown.setRotation(180);
            }
        });
    }

    private void showInfo(RelativeLayout expContent, int newHeight){
        AnimateChangeHeight animateChangeHeight;
        animateChangeHeight = new AnimateChangeHeight(expContent, newHeight);
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

    private void setNavBarButtons(){
        requireActivity().findViewById(R.id.btn_main_menu).setVisibility(View.VISIBLE);
        requireActivity().findViewById(R.id.btn_voltar).setVisibility(View.INVISIBLE);
        requireActivity().findViewById(R.id.btn_sair).setVisibility(View.INVISIBLE);
    }
}
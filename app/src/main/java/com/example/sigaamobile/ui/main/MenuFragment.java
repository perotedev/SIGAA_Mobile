package com.example.sigaamobile.ui.main;

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

public class MenuFragment extends Fragment {

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
}
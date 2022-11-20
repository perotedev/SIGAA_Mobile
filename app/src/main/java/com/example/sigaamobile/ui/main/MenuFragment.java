package com.example.sigaamobile.ui.main;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
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

    private MainViewModel mViewModel;

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
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

        cardInfo.setOnClickListener(v -> {
            if (arrowDown.getRotation() == 180){
                showInfo(expContent, false);
                arrowDown.setRotation(0);
            } else {
                showInfo(expContent, true);
                arrowDown.setRotation(180);
            }
        });
    }

    private void showInfo(RelativeLayout layoutInfo, Boolean showContent){
        int newHeight = showContent?678:0;
        AnimateChangeHeight animateChangeHeight;
        animateChangeHeight = new AnimateChangeHeight(layoutInfo, newHeight);
        animateChangeHeight.updateAnimate();
    }
}
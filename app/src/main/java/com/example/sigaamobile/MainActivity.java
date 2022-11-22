package com.example.sigaamobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sigaamobile.utils.SigaaSharedPreferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void voltar(View v){
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0)
            super.onBackPressed();
    }

    public static void setNavBarButton(Activity activity, int btnId){
        activity.findViewById(R.id.btn_main_menu).setVisibility(View.INVISIBLE);
        activity.findViewById(R.id.btn_voltar).setVisibility(View.INVISIBLE);
        activity.findViewById(R.id.btn_sair).setVisibility(View.INVISIBLE);
        activity.findViewById(btnId).setVisibility(View.VISIBLE);
    }

    public static void setNavBarTitle(Activity activity, int stringId){
        TextView titleNavBar = activity.findViewById(R.id.app_toolbar_title);
        titleNavBar.setText(stringId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SigaaSharedPreferences.clearSharedPreferences(getApplicationContext());
    }
}
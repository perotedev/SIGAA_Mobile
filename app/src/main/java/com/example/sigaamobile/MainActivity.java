package com.example.sigaamobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

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
}
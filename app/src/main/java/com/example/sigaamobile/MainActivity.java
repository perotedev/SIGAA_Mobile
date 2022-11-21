package com.example.sigaamobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import com.example.sigaamobile.ui.main.LoginFragment;
import com.example.sigaamobile.ui.main.MenuFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void voltar(View v){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow();
//        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//        int count = getFragmentManager().getBackStackEntryCount();
//        if (count == 0)
//            super.onBackPressed();
    }
}
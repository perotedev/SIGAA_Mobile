package com.example.sigaamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sigaamobile.ui.main.LoginFragment;
import com.example.sigaamobile.ui.main.MenuFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, MenuFragment.newInstance())
//                    .commitNow();
//        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LoginFragment.newInstance())
                    .commitNow();
        }
    }
}
package com.example.sigaamobile;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_view);

        ImageView logoSigaa = findViewById(R.id.sigaa_logo);
        logoSigaa.animate().rotation(1080f).setDuration(3000).start();

        Thread splashScreenStarter = new Thread() {
            public void run(){
                try{
                    int delay = 0;
                    while (delay < 2000) {
                        sleep(1);
                        delay = delay + 1;
                    }
                } catch(InterruptedException e) {
                    assert e != null;
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                    finish();
                }
            }
        };
        splashScreenStarter.start();
    }
}
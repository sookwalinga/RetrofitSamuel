package com.samo.retrofitsamuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {
    /**
     * Duration for the splash screen.
     **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    int progress = 0;
    ProgressBar prog_timer;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
// initializing variables.
        imageView = findViewById(R.id.retrofit_logo);
        prog_timer = findViewById(R.id.prog_timer);
        prog_timer(progress);

        //        Handler.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

//  New intent.
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);

            }
        }, SPLASH_DISPLAY_LENGTH);
    };
    //   Horizontal Progress bar animation
    private void prog_timer(final int progress) {
        // set the progress
        prog_timer.setProgress(progress);
        // thread is used to change the progress value
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                prog_timer(progress + 32);
            }
        });
        thread.start();
    }

}
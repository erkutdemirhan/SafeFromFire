package com.sparky.safefromfire.screens;

import android.os.Bundle;
import android.os.Handler;

import com.sparky.safefromfire.R;
import com.sparky.safefromfire.base.BaseActivity;

/**
 * Created by Erkut Demirhan on 29/04/17.
 */
public class SplashActivity extends BaseActivity {

    private static final long SPLASH_DURATION = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startSplashTimer();
    }

    private void startSplashTimer() {
        Handler switchScreenHandler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                gotoActivity(StartActivity.class, true);
            }
        };
        switchScreenHandler.postDelayed(runnable, SPLASH_DURATION);
    }

    @Override
    protected void setupViews() {}
}

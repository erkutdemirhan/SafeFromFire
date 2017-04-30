package com.sparky.safefromfire.screens;

import android.os.Bundle;
import android.widget.Toast;

import com.sparky.safefromfire.R;
import com.sparky.safefromfire.base.BaseActivity;
import com.sparky.safefromfire.screens.home.HomeActivity;
import com.sparky.safefromfire.screens.register.RegisterActivity;

import butterknife.OnClick;

/**
 * Created by Erkut Demirhan on 29/04/17.
 */
public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    @Override
    protected void setupViews() {

    }

    @OnClick(R.id.startactivity_registerbutton)
    public void onRegisterButtonPressed() {
        gotoActivity(RegisterActivity.class, false);
        finish();
    }

    @OnClick(R.id.startactivity_skipbutton)
    public void onSkipButtonPressed() {
        gotoActivity(HomeActivity.class, false);
        finish();
    }
}

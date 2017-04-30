package com.sparky.safefromfire.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Erkut Demirhan on 29/04/17.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(BaseActivity.this);
        setupViews();
    }

    protected abstract void setupViews();

    public  <T> void gotoActivity(Class<T> activityClass, boolean shouldClearAllTasksBefore) {
        Intent intent = new Intent(BaseActivity.this, activityClass);
        if(shouldClearAllTasksBefore) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
    }
}

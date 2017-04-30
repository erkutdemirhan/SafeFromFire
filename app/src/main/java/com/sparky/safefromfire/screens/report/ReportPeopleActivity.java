package com.sparky.safefromfire.screens.report;

import android.os.Bundle;

import com.sparky.safefromfire.R;
import com.sparky.safefromfire.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by Erkut Demirhan on 30/04/17.
 */
public class ReportPeopleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_people);
    }

    @Override
    protected void setupViews() {

    }

    @OnClick(R.id.reportactivity_nextbutton)
    public void onNextButtonPressed() {
        gotoActivity(ReportDisabilityActivity.class, false);
    }

    @OnClick(R.id.reportactivity_backbutton)
    public void onBackButtonPressed() {
        finish();
    }
}

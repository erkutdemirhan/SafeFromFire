package com.sparky.safefromfire.screens.report;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.sparky.safefromfire.R;
import com.sparky.safefromfire.SessionManager;
import com.sparky.safefromfire.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Erkut Demirhan on 30/04/17.
 */
public class ReportAddressActivity extends BaseActivity {

    @BindView(R.id.reportactivity_address)
    EditText primaryAddressEt;

    @BindView(R.id.reportactivity_secondary_address)
    EditText secondaryAddressEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_address);
    }

    @Override
    protected void setupViews() {
        final String userPrimaryAddress   = SessionManager.getInstance(ReportAddressActivity.this).getUserAddress();
        final String userSecondaryAddress = SessionManager.getInstance(ReportAddressActivity.this).getUserSecondaryAddress();
        primaryAddressEt.setText(userPrimaryAddress);
        secondaryAddressEt.setText(userSecondaryAddress);
    }

    @OnClick(R.id.reportactivity_nextbutton)
    public void onNextButtonPressed() {
        final String userPrimaryAddress = primaryAddressEt.getText().toString();
        if(userPrimaryAddress.isEmpty()) {
            Toast.makeText(ReportAddressActivity.this, "Please enter at least an address", Toast.LENGTH_SHORT).show();
            return;
        }
        gotoActivity(ReportPlaceActivity.class, false);
    }

    @OnClick(R.id.reportactivity_backbutton)
    public void onBackButtonPressed() {
        finish();
    }
}

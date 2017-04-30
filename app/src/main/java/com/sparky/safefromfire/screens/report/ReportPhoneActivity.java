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
public class ReportPhoneActivity extends BaseActivity {

    @BindView(R.id.reportactivity_phone)
    EditText phoneEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_phone);
    }

    @Override
    protected void setupViews() {
        final String phoneNumber = SessionManager.getInstance(ReportPhoneActivity.this).getUserPhone();
        phoneEt.setText(phoneNumber);
    }

    @OnClick(R.id.reportactivity_nextbutton)
    public void onNextButtonPressed() {
        final String phoneNumber = phoneEt.getText().toString();
        if(phoneNumber.isEmpty()) {
            Toast.makeText(ReportPhoneActivity.this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
            return;
        }
        gotoActivity(ReportAddressActivity.class, false);
    }

    @OnClick(R.id.reportactivity_backbutton)
    public void onBackButtonPressed() {
        finish();
    }
}

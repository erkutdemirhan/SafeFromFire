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
public class ReportNameActivity extends BaseActivity {

    @BindView(R.id.reportactivity_name)
    EditText nameEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_name);
    }

    @Override
    protected void setupViews() {
        final String savedUserName = SessionManager.getInstance(ReportNameActivity.this).getUserName();
        nameEt.setText(savedUserName);
    }

    @OnClick(R.id.reportactivity_nextbutton)
    public void onNextButtonPressed() {
        final String userName = nameEt.getText().toString();
        if(userName.isEmpty()) {
            Toast.makeText(ReportNameActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
            return;
        }
        gotoActivity(ReportPhoneActivity.class, false);
    }
}

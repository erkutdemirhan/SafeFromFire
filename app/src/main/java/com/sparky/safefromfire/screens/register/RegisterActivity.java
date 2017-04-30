package com.sparky.safefromfire.screens.register;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.EditText;
import android.widget.Toast;

import com.sparky.safefromfire.R;
import com.sparky.safefromfire.SessionManager;
import com.sparky.safefromfire.base.BaseActivity;
import com.sparky.safefromfire.screens.home.HomeActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Erkut Demirhan on 29/04/17.
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.register_name)
    EditText nameEt;

    @BindView(R.id.register_phone_number)
    EditText phoneNumberEt;

    @BindView(R.id.register_age)
    EditText ageEt;

    @BindView(R.id.register_disabilities)
    EditText disabilitiesEt;

    @BindView(R.id.register_address)
    EditText addressEt;

    @BindView(R.id.register_secondary_address)
    EditText secondaryAddressEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void setupViews() {}

    @OnClick(R.id.register_confirm_button)
    public void onConfirmButtonPressed() {
        final String userName  = nameEt.getText().toString();
        final String userPhone = phoneNumberEt.getText().toString();
        final String userAge   = ageEt.getText().toString();
        final String userDisabilities     = disabilitiesEt.getText().toString();
        final String userPrimaryAddress   = addressEt.getText().toString();
        final String userSecondaryAddress = secondaryAddressEt.getText().toString();

        if(userName.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userPhone.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userAge.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please enter your age", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userPrimaryAddress.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please enter at least an address", Toast.LENGTH_SHORT).show();
            return;
        }

        SessionManager.getInstance(RegisterActivity.this).setUserName(userName);
        SessionManager.getInstance(RegisterActivity.this).setUserPhone(userPhone);
        SessionManager.getInstance(RegisterActivity.this).setUserAge(userAge);
        SessionManager.getInstance(RegisterActivity.this).setUserDisabilities(userDisabilities);
        SessionManager.getInstance(RegisterActivity.this).setUserPrimaryAddress(userPrimaryAddress);
        SessionManager.getInstance(RegisterActivity.this).setUserSecondaryAddress(userSecondaryAddress);

        SessionManager.getInstance(RegisterActivity.this).setUserRegistered(true);
        gotoActivity(HomeActivity.class, true);
        finish();
    }

}

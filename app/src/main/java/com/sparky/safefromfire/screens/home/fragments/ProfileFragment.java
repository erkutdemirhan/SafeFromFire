package com.sparky.safefromfire.screens.home.fragments;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.sparky.safefromfire.R;
import com.sparky.safefromfire.SessionManager;
import com.sparky.safefromfire.base.BaseActivity;
import com.sparky.safefromfire.base.BaseFragment;
import com.sparky.safefromfire.screens.FirefighterActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Erkut Demirhan on 29/04/17.
 */
public class ProfileFragment extends BaseFragment {

    public static final String TAG = "ProfileFragment";

    @BindView(R.id.profile_name)
    EditText nameEt;

    @BindView(R.id.profile_age)
    EditText ageEt;

    @BindView(R.id.profile_disabilities)
    EditText disabilitiesEt;

    @BindView(R.id.profile_phone_number)
    EditText phoneNumberEt;

    @BindView(R.id.profile_address)
    EditText primaryAddressEt;

    @BindView(R.id.profile_secondary_address)
    EditText secondaryAddressEt;

    @BindView(R.id.profile_is_firefighter)
    CheckBox isFirefighter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void setupViews(View view) {
        final String savedUserName  = SessionManager.getInstance(getContext()).getUserName();
        final String savedUserPhone = SessionManager.getInstance(getContext()).getUserPhone();
        final String savedUserAge   = SessionManager.getInstance(getContext()).getUserAge();
        final String savedUserDisabilities = SessionManager.getInstance(getContext()).getUserDisabilities();
        final String savedPrimaryAddress      = SessionManager.getInstance(getContext()).getUserAddress();
        final String savedSecondaryAddress    = SessionManager.getInstance(getContext()).getUserSecondaryAddress();

        nameEt.setText(savedUserName);
        phoneNumberEt.setText(savedUserPhone);
        ageEt.setText(savedUserAge);
        disabilitiesEt.setText(savedUserDisabilities);
        primaryAddressEt.setText(savedPrimaryAddress);
        secondaryAddressEt.setText(savedSecondaryAddress);
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    @OnClick(R.id.profile_save_button)
    public void onSaveButtonPressed() {
        final String userName  = nameEt.getText().toString();
        final String userPhone = phoneNumberEt.getText().toString();
        final String userAge   = ageEt.getText().toString();
        final String userDisabilities     = disabilitiesEt.getText().toString();
        final String userPrimaryAddress   = primaryAddressEt.getText().toString();
        final String userSecondaryAddress = secondaryAddressEt.getText().toString();


        if(userName.isEmpty()) {
            Toast.makeText(getContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userPhone.isEmpty()) {
            Toast.makeText(getContext(), "Please enter your phone number", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userAge.isEmpty()) {
            Toast.makeText(getContext(), "Please enter your age", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userPrimaryAddress.isEmpty()) {
            Toast.makeText(getContext(), "Please enter at least an address", Toast.LENGTH_SHORT).show();
            return;
        }

        if(isFirefighter.isChecked()) {
            ((BaseActivity) getActivity()).gotoActivity(FirefighterActivity.class, true);
            ((BaseActivity) getActivity()).finish();
        }

        SessionManager.getInstance(getContext()).setUserName(userName);
        SessionManager.getInstance(getContext()).setUserPhone(userPhone);
        SessionManager.getInstance(getContext()).setUserAge(userAge);
        SessionManager.getInstance(getContext()).setUserDisabilities(userDisabilities);
        SessionManager.getInstance(getContext()).setUserPrimaryAddress(userPrimaryAddress);
        SessionManager.getInstance(getContext()).setUserSecondaryAddress(userSecondaryAddress);

        SessionManager.getInstance(getContext()).setUserRegistered(true);
        Toast.makeText(getContext(), "Profile information is updated", Toast.LENGTH_SHORT).show();
    }
}

package com.sparky.safefromfire.screens.home;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sparky.safefromfire.R;
import com.sparky.safefromfire.base.BaseActivity;
import com.sparky.safefromfire.base.BaseFragment;
import com.sparky.safefromfire.receiver.LocationReceiver;
import com.sparky.safefromfire.screens.home.fragments.HomeFragment;
import com.sparky.safefromfire.screens.home.fragments.InfoFragment;
import com.sparky.safefromfire.screens.home.fragments.MapFragment;
import com.sparky.safefromfire.screens.home.fragments.ProfileFragment;
import com.sparky.safefromfire.screens.report.ReportNameActivity;
import com.sparky.safefromfire.utils.PermissionUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.homeactivity_hometabimage)
    ImageView homeTabIw;

    @BindView(R.id.homeactivity_maptabimage)
    ImageView mapTabIw;

    @BindView(R.id.homeactivity_infotabimage)
    ImageView infoTabIw;

    @BindView(R.id.homeactivity_profiletabimage)
    ImageView profileTabIw;

    @BindView(R.id.homeactivity_homeselectedview)
    View homeSelectedView;

    @BindView(R.id.homeactivity_mapselectedview)
    View mapSelectedView;

    @BindView(R.id.homeactivity_infoselectedview)
    View infoSelectedView;

    @BindView(R.id.homeactivity_profileselectedview)
    View profileSelectedView;

    private LocationManager locationManager;
    private LocationReceiver locationReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        checkForLocationPermission();
    }

    private void checkForLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (!PermissionUtils.hasPermissions(HomeActivity.this, permissions)) {
            requestPermissions(permissions, 0);
        } else {
            registerForLocationUpdates();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(requestCode == 0) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                registerForLocationUpdates();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void registerForLocationUpdates() {
        locationManager  = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationReceiver = new LocationReceiver(HomeActivity.this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10 * 1000, 5.0f, locationReceiver);
    }

    @Override
    protected void setupViews() {
        selectTab(1);
        switchToFragment(new HomeFragment());
    }

    @Override
    public void onBackPressed() {
        final int stackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if(stackEntryCount > 1) {
            getSupportFragmentManager().popBackStackImmediate();
            final String backEntryTag = getTopFragmentTag();
            if(backEntryTag.equals(HomeFragment.TAG)) {
                selectTab(1);
            } else if(backEntryTag.equals(MapFragment.TAG)) {
                selectTab(2);
            } else if(backEntryTag.equals(InfoFragment.TAG)) {
                selectTab(3);
            } else if(backEntryTag.equals(ProfileFragment.TAG)) {
                selectTab(4);
            } else {
                selectTab(1);
            }
        } else {
            finish();
        }
    }

    private String getTopFragmentTag() {
        final int stackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if(stackEntryCount > 0) {
            FragmentManager.BackStackEntry backStackEntry = getSupportFragmentManager().getBackStackEntryAt(stackEntryCount-1);
            return backStackEntry.getName();
        }
        return "";
    }

    @OnClick(R.id.homeactivity_report_fire_button)
    public void onReportFireButtonPressed() {
        gotoActivity(ReportNameActivity.class, false);
    }

    @OnClick(R.id.homeactivity_hometab)
    public void onHomeTabPressed() {
        selectTab(1);
        switchToFragment(new HomeFragment());
    }

    @OnClick(R.id.homeactivity_maptab)
    public void onMapTabPressed() {
        selectTab(2);
        switchToFragment(new MapFragment());
    }

    @OnClick(R.id.homeactivity_infotab)
    public void onInfoTabPressed() {
        selectTab(3);
        switchToFragment(new InfoFragment());
    }

    @OnClick(R.id.homeactivity_profiletab)
    public void onProfileTabPressed() {
        selectTab(4);
        switchToFragment(new ProfileFragment());
    }

    public void switchToFragment(final BaseFragment selectedFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.homeactivity_framelayout, selectedFragment, selectedFragment.getFragmentTag());
        fragmentTransaction.addToBackStack(selectedFragment.getFragmentTag());
        fragmentTransaction.commit();
    }

    private void selectTab(final int tabOrder) {
        deselectAllTabs();
        switch (tabOrder) {
            case 1:
                homeSelectedView.setVisibility(View.VISIBLE);
                homeTabIw.setImageResource(R.drawable.house_selected);
                break;
            case 2:
                mapSelectedView.setVisibility(View.VISIBLE);
                mapTabIw.setImageResource(R.drawable.map_selected);
                break;
            case 3:
                infoSelectedView.setVisibility(View.VISIBLE);
                infoTabIw.setImageResource(R.drawable.info_selected);
                break;
            case 4:
                profileSelectedView.setVisibility(View.VISIBLE);
                profileTabIw.setImageResource(R.drawable.profile_selected);
                break;
            default:
                homeSelectedView.setVisibility(View.VISIBLE);
                homeTabIw.setImageResource(R.drawable.house_selected);
                break;
        }
    }

    private void deselectAllTabs() {
        homeSelectedView.setVisibility(View.GONE);
        mapSelectedView.setVisibility(View.GONE);
        infoSelectedView.setVisibility(View.GONE);
        profileSelectedView.setVisibility(View.GONE);

        homeTabIw.setImageResource(R.drawable.house_unselected);
        mapTabIw.setImageResource(R.drawable.map_unselected);
        infoTabIw.setImageResource(R.drawable.info_unselected);
        profileTabIw.setImageResource(R.drawable.profile_unselected);
    }

    @Override
    protected void onDestroy() {
        if(locationManager != null && locationReceiver != null) {
            locationManager.removeUpdates(locationReceiver);
        }
        super.onDestroy();
    }
}

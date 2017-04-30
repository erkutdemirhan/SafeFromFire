package com.sparky.safefromfire.screens;

import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sparky.safefromfire.R;
import com.sparky.safefromfire.SessionManager;
import com.sparky.safefromfire.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Erkut Demirhan on 30/04/17.
 */
public class FirefighterActivity extends BaseActivity implements OnMapReadyCallback {

    @BindView(R.id.safetyzoneactivity_mapview)
    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firefighter);
    }

    @Override
    protected void setupViews() {
        mapView.onCreate(null);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        final Location userLocation  = SessionManager.getInstance(FirefighterActivity.this).getUserLocation();
        final LatLng userCoordinates = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(userCoordinates).title("User Location"));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(userCoordinates, 15);
        googleMap.moveCamera(cameraUpdate);
        mapView.onResume();
    }
}

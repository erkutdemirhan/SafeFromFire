package com.sparky.safefromfire.screens.home.fragments;

import android.location.Location;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sparky.safefromfire.R;
import com.sparky.safefromfire.SafeFromFire;
import com.sparky.safefromfire.SessionManager;
import com.sparky.safefromfire.base.BaseFragment;
import com.sparky.safefromfire.model.FireRecord;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Erkut Demirhan on 29/04/17.
 */
public class MapFragment extends BaseFragment implements OnMapReadyCallback {

    public static final String TAG = "MapFragment";

    @BindView(R.id.mapfragment_map)
    MapView mapView;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_map;
    }

    @Override
    protected void setupViews(View view) {
        mapView.onCreate(null);
        mapView.getMapAsync(this);
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        final LatLngBounds.Builder latLngBoundsBuilder = new LatLngBounds.Builder();
        final Location userLocation  = SessionManager.getInstance(getContext()).getUserLocation();
        final LatLng userCoordinates = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
        final BitmapDescriptor icon  = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_user);
        googleMap.addMarker(new MarkerOptions().position(userCoordinates).title("User Location").icon(icon));
        latLngBoundsBuilder.include(userCoordinates);
        final List<FireRecord> fireRecordList = ((SafeFromFire) getActivity().getApplication()).getFireRecordList();
        for(FireRecord fireRecord:fireRecordList) {
            final LatLng fireRecordCoordinate = new LatLng(fireRecord.getLocation().getLatitude(), fireRecord.getLocation().getLongitude());
            googleMap.addMarker(new MarkerOptions().position(fireRecordCoordinate).title("Fire date: " + fireRecord.getDate()));
            latLngBoundsBuilder.include(fireRecordCoordinate);
        }
        googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(latLngBoundsBuilder.build(), 100);
                googleMap.moveCamera(cameraUpdate);
                googleMap.setOnCameraChangeListener(null);
            }
        });
        mapView.onResume();
    }
}

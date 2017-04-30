package com.sparky.safefromfire.receiver;

import android.content.Context;
import android.content.res.AssetManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;

import com.sparky.safefromfire.SafeFromFire;
import com.sparky.safefromfire.SessionManager;
import com.sparky.safefromfire.model.FireRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erkut Demirhan on 29/04/17.
 */
public class LocationReceiver implements LocationListener {


    private static final float FIRE_RECORDS_RADIUS = 100000;
    private static final String FIRE_RECORDS_FILE  = "fire_records.csv";

    private Context context;
    private FireLocationsTask fireLocationsTask;

    public LocationReceiver(Context context) {
        this.context           = context;
    }

    @Override
    public void onLocationChanged(Location location) {
        SessionManager.getInstance(context).setUserLocation(location);
        fireLocationsTask = new FireLocationsTask(context);
        fireLocationsTask.execute(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {}

    @Override
    public void onProviderEnabled(String s) {}

    @Override
    public void onProviderDisabled(String s) {}

    private class FireLocationsTask extends AsyncTask<Location,Void,List<FireRecord>> {
        private AssetManager assetManager;

        public FireLocationsTask(Context context) {
            assetManager = context.getAssets();
        }

        @Override
        protected List<FireRecord> doInBackground(Location... locations) {
            final Location userLocation     = locations[0];
            List<FireRecord> fireRecordList = new ArrayList<>();
            try {
                InputStream is = assetManager.open(FIRE_RECORDS_FILE);
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br     = new BufferedReader(isr);
                br.readLine();
                String line;
                while((line = br.readLine()) != null) {
                    String[] params = line.split(",");
                    final double fireLat  = Double.parseDouble(params[0]);
                    final double fireLon  = Double.parseDouble(params[1]);
                    final Location fireLocation   = new Location("");
                    fireLocation.setLatitude(fireLat);
                    fireLocation.setLongitude(fireLon);
                    if(fireLocation.distanceTo(userLocation) < FIRE_RECORDS_RADIUS) {
                        FireRecord fireRecord = new FireRecord(fireLocation, params[5]);
                        fireRecordList.add(fireRecord);
                    }
                }
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return fireRecordList;
        }

        @Override
        protected void onPostExecute(List<FireRecord> fireRecords) {
            ((SafeFromFire) context.getApplicationContext()).updateFireRecordList(fireRecords);
        }
    }
}

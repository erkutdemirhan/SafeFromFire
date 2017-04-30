package com.sparky.safefromfire;

import android.app.Application;

import com.sparky.safefromfire.model.FireRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erkut Demirhan on 29/04/17.
 */
public class SafeFromFire extends Application {

    private List<FireRecord> fireRecordList;

    @Override
    public void onCreate() {
        super.onCreate();
        fireRecordList = new ArrayList<>();
    }

    public void updateFireRecordList(final List<FireRecord> fireRecords) {
        if(fireRecords != null && !fireRecords.isEmpty()) {
            fireRecordList.clear();
            fireRecordList.addAll(fireRecords);
        }
    }

    public List<FireRecord> getFireRecordList() {
        return fireRecordList;
    }
}

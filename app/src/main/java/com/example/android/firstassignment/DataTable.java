package com.example.android.firstassignment;

import android.os.Build;
import android.support.annotation.RequiresApi;


@RequiresApi(api = Build.VERSION_CODES.N)
public class DataTable {
   private String userid;
   private double longtitude;
   private double latitude;
   private String timestamp;

    public String getUserid() {
        return userid;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public DataTable(String userid, double longtitude, double latitude, String timestamp) {
        this.userid = userid;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.timestamp = timestamp;

    }


}

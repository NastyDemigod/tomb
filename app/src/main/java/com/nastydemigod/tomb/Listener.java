package com.nastydemigod.tomb;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.annotation.NonNull;

public class Listener implements LocationListener {
    private LocationInterface locationInterface;
    @Override
    public void onLocationChanged(@NonNull Location location) {
        locationInterface.OnLocationChanged(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    public void setLocationInterface(LocationInterface locationInterface) {
        this.locationInterface = locationInterface;
    }
}

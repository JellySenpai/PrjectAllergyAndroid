package com.uat.foodmeister.Permissions;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.uat.foodmeister.AppConfig;
import com.uat.foodmeister.R;

import static android.support.v4.app.ActivityCompat.*;

public final class Permissions  {

    private static final int PLAY_SERVICE_RESOLUTION_REQUEST = 9000;

    private static Permissions instance = null;

    private static Context context;

    private static Activity activity;

    private static LocationManager locationManager;

    private static CoordinatorLayout layout;

    private static String tag = "Permissions";

    private Permissions(Context _context, Activity _activity, LocationManager _locationManager)
    {
        this.context = _context;
        this.activity = _activity;
        this.locationManager = _locationManager;
        layout = (CoordinatorLayout) activity.findViewById(R.id.mainCoordinatorLayout);
    }

    static public Permissions getInstance(Context _context, Activity _activity, LocationManager _locationManager)
    {
        if (instance == null) {
            instance = new Permissions(_context, _activity, _locationManager);
            return instance;
        } else {
            return instance;
        }
    }

    public static boolean isLocationServicePermissionsDenied()
    {
        return checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;
    }

    //Check if google Play services is available
    public static boolean isGooglePlayServicesAvailable()
    {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(context);

        if (resultCode != ConnectionResult.SUCCESS)
        {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(activity, resultCode, PLAY_SERVICE_RESOLUTION_REQUEST).show();
            }
            else {
                Log.i("ERROR:", "This device is not Supported");
                //Finish this activity
                activity.finish();
            }
        }

        return true;
    }

    public static void requestLocationPermissions()
    {
        Log.i(tag, "Requesting Permission");

        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION))
            {

                activity.requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, AppConfig.REQUEST_LOCATION);

                activity.recreate();

            } else {
                activity.requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, AppConfig.REQUEST_LOCATION);
            }
        }
    }
}

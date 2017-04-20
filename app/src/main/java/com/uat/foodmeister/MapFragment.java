package com.uat.foodmeister;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.uat.foodmeister.DB.DBWorkerDelegate;
import com.uat.foodmeister.Permissions.Permissions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.LOCATION_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, LocationListener, GoogleMap.OnMarkerClickListener {
    private GoogleMap mMap;

    private MapView mapView;

    private CoordinatorLayout mainCoordinateLayout;


    private Snackbar gpsDisabledSnackbar;

    private MainActivity mainActivity;

    private static final String TAG = "MapFragment";


    private OnFragmentInteractionListener mListener;

    public MapFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        mainActivity = (MainActivity)getActivity();

        Permissions permissions = Permissions.getInstance(getContext(), getActivity(), mainActivity.locationManager);

        if (Permissions.isGooglePlayServicesAvailable()) {

            if (Permissions.isLocationServicePermissionsDenied())
            {
                Log.i("Permissions", "Location Services Denied");
                Permissions.requestLocationPermissions();
            }
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
<<<<<<< HEAD
=======
        // Inflate the layout for this fragment
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
        View v = inflater.inflate(R.layout.fragment_map2, container, false);
        mapView = (MapView) v.findViewById(R.id.maps);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
        return v;
<<<<<<< HEAD
=======
        //return inflater.inflate(R.layout.fragment_map2, container, false);
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "OnPause() Ran");
<<<<<<< HEAD
=======
        //mainActivity.locationManager.up
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onLocationChanged(Location location)
    {
        Log.i("Location", "Updating Location");

        double latitude = location.getLatitude();

        double longitude = location.getLongitude();

<<<<<<< HEAD
=======
        //this is for debug only, this is hard coding the address of UAT for use while in the emulator.
        //LatLng UAT = new LatLng(33.3769580,-111.9758610);
        //latitude = 33.3769580;
        //longitude = 111.9758610;
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0

        LatLng currentLocation = new LatLng(latitude, longitude);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));

        loadNearByPlaces(latitude, longitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
    private void reloadActivity()
    {
        getActivity().recreate();
    }
    private void deniedLocation()
    {
        getActivity().finish();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode)
        {
            case AppConfig.REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    reloadActivity();
                }
                else if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    deniedLocation();
                }
            }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }
    @Override
    public void onProviderEnabled(String provider)
    {
        if (provider.equals("gps"))
        {
            gpsDisabledSnackbar.dismiss();

            displayCurrentLocation(mMap);
        }
    }

    @Override
    public void onProviderDisabled(String provider)
    {
        if (provider.equals("gps"))
        {
            gpsDisabledSnackbar = Snackbar.make(mainCoordinateLayout, "Cannot Access GPS Services", Snackbar.LENGTH_INDEFINITE);

            View gpsSnackbarView = gpsDisabledSnackbar.getView();

            gpsSnackbarView.setBackgroundColor(Color.RED);

            gpsDisabledSnackbar.show();

            mMap.clear();

        }
    }

    @Override
    public boolean onMarkerClick(Marker marker)
    {

        Log.d("Latitude", "value: " + marker.getPosition().latitude);

        Log.d("Longitude", "value: " + marker.getPosition().longitude);

        String latitude, longitude;

        latitude = Double.toString(marker.getPosition().latitude);

        longitude = Double.toString(marker.getPosition().longitude);

        String type = "location";

        BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());

        backgroundWorker.execute(type, latitude, longitude);

        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        if (mMap == null)
            mMap = googleMap;

        displayCurrentLocation(mMap);

        mMap.setOnMarkerClickListener(this);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void displayCurrentLocation(GoogleMap mMap) {

        //If locations services is enabled
        try{
        if (!Permissions.isLocationServicePermissionsDenied()) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);

            Criteria criteria = new Criteria();
            String bestProvider = mainActivity.locationManager.getBestProvider(criteria, true);
            Location location = mainActivity.locationManager.getLastKnownLocation(bestProvider);

            if (location != null) {
                onLocationChanged(location);
            }

            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
            //mainActivity.locationManager.requestLocationUpdates(bestProvider, AppConfig.MIN_TIME_BW_UPDATES, AppConfig.MIN_DISTANCE_BW_UPDATES, this);
            mainActivity.locationManager.requestSingleUpdate(bestProvider, this, null);
        }
        } catch (SecurityException ex)
        {
            deniedLocation();
        }
    }

    private void loadNearByPlaces(double latitude, double longitude)
    {
<<<<<<< HEAD
        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/textsearch/json?");
=======
        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
        googlePlacesUrl.append("location=").append(latitude).append(",").append(longitude);
        googlePlacesUrl.append("&radius=").append(AppConfig.PROXIMITY_RADIUS);
        googlePlacesUrl.append("&types=").append("restaurant");
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + "AIzaSyDuUPsfVHN7RWR5IohI6imgRbQMfxC7it0");

        Log.i("URL", googlePlacesUrl.toString());

        JsonObjectRequest request = new JsonObjectRequest(googlePlacesUrl.toString(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("URL", "onResponse: Result= " + response.toString());
                parseLocationResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("URL", "onResponse: Error = " + error);
                Log.i("URL", "onResponse: Error = " + error.toString());
            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }

    private void parseLocationResponse(JSONObject response)
    {
        double latitude, longitude;
        String placeName = null;

        try {
            JSONArray jsonArray = response.getJSONArray(AppConfig.RESULTS);

            if (response.getString(AppConfig.STATUS).equalsIgnoreCase(AppConfig.OK)) {
                mMap.clear();

                for (int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject place = jsonArray.getJSONObject(i);

                    if (!place.isNull("name")) {
                        placeName = place.getString("name");
                    }

                    latitude = place.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                    longitude = place.getJSONObject("geometry").getJSONObject("location").getDouble("lng");

                    MarkerOptions markerOptions = new MarkerOptions();
                    LatLng latLng = new LatLng(latitude, longitude);
                    markerOptions.position(latLng);
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.fm_custom_map_marker_1));
                    markerOptions.title(placeName + "-\n" + latitude + ":" + longitude);

                    mMap.addMarker(markerOptions);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("JSON ERROR", "parseLocation Error= " + e.getMessage());
        }
    }
    //*/
}

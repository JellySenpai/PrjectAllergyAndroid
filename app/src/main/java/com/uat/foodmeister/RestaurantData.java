package com.uat.foodmeister;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class RestaurantData {

    private String name = "name";
    private String streetAddress = "1234 restaurant Ave";
    private String city  = "city";
    private String state = "state";
    private String zip = "zip";

    private final double _lat;
    private final double _long;


    public RestaurantData(String name, String streetAddress, String city, String state, String zip, double _lat, double _long)
    {
        this.name = name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this._lat = _lat;
        this._long = _long;
    }

    public MarkerOptions getMarker()
    {
        MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(_lat, _long)).title(name);

        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));

        return markerOptions;
    }
}
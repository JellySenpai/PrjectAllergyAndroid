package com.uat.foodmeister;


public final class AppConfig {

    public static final String TAG = "gplaces";

    public static final long MIN_TIME_BW_UPDATES =6000;         //miliseconds = 1 min

    public static final long MIN_DISTANCE_BW_UPDATES = 10;      //Meters

    public static final long PROXIMITY_RADIUS = 15000;          // Meters

    public static final String RESULTS = "results";

    public static final String STATUS = "status";

    public static final String OK = "OK";

    public static final int REQUEST_LOCATION = 19;

    public static final String ACCOUNT_HOLDER_NAME = "AccountHolderName";

    public static final String ACCOUNT_HOLDER_EMAIL = "AccountHolderEmail";

    public static final String VERIFY_REGISTERED_EMAIL_URL = "http://thefoodmeister.com/verify_login_android.php";

    public static final int LOAD_ADD_PROFILE_FRAGMENT = 12;

    public static final int TIME_BEFORE_REFRESH =600000;  //miliseconds = 1 min
}

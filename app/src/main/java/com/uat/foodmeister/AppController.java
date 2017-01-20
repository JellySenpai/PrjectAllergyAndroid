package com.uat.foodmeister;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import static com.uat.foodmeister.AppConfig.TAG;

public class AppController extends Application {

    private RequestQueue requestQueue;
    private static AppController instance;

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
    }

    public static synchronized AppController getInstance()
    {
        return instance;
    }

    public RequestQueue getRequestQueue()
    {
        if (requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag)
    {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
    }

    public <T> void addToRequestQueue(Request req)
    {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void canclePendingRequests(Object tag)
    {
        if (requestQueue != null)
        {
            requestQueue.cancelAll(tag);
        }
    }

}

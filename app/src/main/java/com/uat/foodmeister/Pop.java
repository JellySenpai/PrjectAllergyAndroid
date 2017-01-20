package com.uat.foodmeister;

import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by Nathan on 10/2/2016.
 */
public class Pop extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);

        setContentView(R.layout.popwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));
    }


}

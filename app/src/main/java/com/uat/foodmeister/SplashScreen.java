package com.uat.foodmeister;

/**
 * Created by rayhardi on 2/17/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.uat.foodmeister.Registration.SignInActivity;

public class SplashScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        Thread timerThread = new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(3000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent intent = new Intent(SplashScreen.this, MapsActivity.class);
                    startActivity(intent);
                }
            }
        };
                timerThread.start();
    }
    protected void onPause()
    {
        super.onPause();
        finish();
    }
}

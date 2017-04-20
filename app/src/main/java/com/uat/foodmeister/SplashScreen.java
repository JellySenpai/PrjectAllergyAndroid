package com.uat.foodmeister;

/**
 * Created by rayhardi on 2/17/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.uat.foodmeister.Registration.SignInActivity;
import com.uat.foodmeister.User.UserProfile;

import static com.google.android.gms.wearable.DataMap.TAG;

public class SplashScreen extends Activity {
    boolean userLoggedIn = false;
    String email, name;
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
                    Log.i("SplashScreen", "Part 1");

                    email = FMSharedPrefs.getString(getApplicationContext(), AppConfig.ACCOUNT_HOLDER_EMAIL,"god@test.com");
                    name = FMSharedPrefs.getString(getApplicationContext(), AppConfig.ACCOUNT_HOLDER_NAME,"Satan");
                    if (email != "god@test.com" && name != "Satan")
                    {
                        userLoggedIn = true;
                    }
                    Log.i(TAG, "Part 2");
                    sleep(3000);

                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent intent=null;
                    if(userLoggedIn)
                    {
                        intent = new Intent(SplashScreen.this, MainActivity.class);
                    }
                    else
                    {
                        intent = new Intent(SplashScreen.this, SignInActivity.class);
                        UserProfile userProfile = new UserProfile(name,email);
                        intent.putExtra("UserProfile", userProfile);

                    }
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

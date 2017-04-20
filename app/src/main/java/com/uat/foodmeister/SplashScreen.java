package com.uat.foodmeister;

/**
 * Created by rayhardi on 2/17/2017.
 */

import android.app.Activity;
import android.content.Intent;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
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
<<<<<<< HEAD
=======
=======
import android.os.Bundle;

import com.uat.foodmeister.Registration.SignInActivity;

public class SplashScreen extends Activity {
>>>>>>> d5f05734d3ce1c56188fe3aa3e6a2a5edea4ccf3
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
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
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
                    Log.i("SplashScreen", "Part 1");

                    email = FMSharedPrefs.getString(getApplicationContext(), AppConfig.ACCOUNT_HOLDER_EMAIL,"god@test.com");
                    name = FMSharedPrefs.getString(getApplicationContext(), AppConfig.ACCOUNT_HOLDER_NAME,"Satan");
                    if (email != "god@test.com" && name != "Satan")
                    {
                        userLoggedIn = true;
                    }
                    Log.i(TAG, "Part 2");
                    sleep(3000);

<<<<<<< HEAD
=======
=======
                    sleep(3000);
>>>>>>> d5f05734d3ce1c56188fe3aa3e6a2a5edea4ccf3
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
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

<<<<<<< HEAD
=======
=======
                    Intent intent = new Intent(SplashScreen.this, MapsActivity.class);
                    startActivity(intent);
                }
>>>>>>> d5f05734d3ce1c56188fe3aa3e6a2a5edea4ccf3
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
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

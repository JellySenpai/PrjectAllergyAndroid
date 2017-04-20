package com.uat.foodmeister;

import android.util.Log;
import com.uat.foodmeister.AppConfig;

/**
 * Created by rayhardi on 3/24/2017.
 */

public class SyncInfo implements Runnable
{
    private Thread thready;
    private final String TAG = "SycnInfo running";

    public SyncInfo()
    {

    }

    @Override
    public void run()
    {
        thready = Thread.currentThread();
        while (thready == Thread.currentThread())
        {
            try
            {
                Thread.sleep(AppConfig.TIME_BEFORE_REFRESH);
                Log.i(TAG,"Sync taskrun");

        } catch (InterruptedException exCep)
       {
           stopThread();
       }
    }
}

    public void stopThread()
    {
        thready = null;
    }

}

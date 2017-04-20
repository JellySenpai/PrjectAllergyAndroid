package com.uat.foodmeister.DB;

import org.json.JSONObject;

/**
 * Created by rayhardi on 3/10/2017.
 */

public interface DBWorkerDelegate
{
    public void taskFinished(boolean isSuccess, JSONObject returnJSON);
}

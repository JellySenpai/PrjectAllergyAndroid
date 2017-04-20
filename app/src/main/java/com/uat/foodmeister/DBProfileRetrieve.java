package com.uat.foodmeister;

import android.os.AsyncTask;
import android.util.Log;

import com.uat.foodmeister.DB.DBWorkerDelegate;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by rayhardi on 4/7/2017.
 */

public class DBProfileRetrieve extends AsyncTask <Void, Void, JSONObject>
{
    private final static String TAG = "DBProfileRetrieve";
    private DBWorkerDelegate dbWorkerDelegate;
    private String email;
    public void setOnFinishedListener (DBWorkerDelegate dbWorkerDelegate1) {this.dbWorkerDelegate = dbWorkerDelegate1;}

    public DBProfileRetrieve(String email)
    {
        this.email = email;
    }


    @Override
    protected JSONObject doInBackground(Void... params) {

       try {
           URL url = new URL("http://thefoodmeister.com/load_user_account.php");
           HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
           httpURLConnection.setRequestMethod("POST");
           httpURLConnection.setDoInput(true);
           httpURLConnection.setDoOutput(true);
           OutputStream outputStream = httpURLConnection.getOutputStream();
           BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
           String post_data = URLEncoder.encode("email", "UTF-8")+ "=" +URLEncoder.encode(email,"UTF-8") ;
           bufferedWriter.write(post_data);
           bufferedWriter.flush();
           bufferedWriter.close();
           JSONObject jsonObject = new JSONObject(readURLReturnData(httpURLConnection));

           httpURLConnection.disconnect();
           return jsonObject;

       } catch(MalformedURLException ex)
       {
           Log.e(TAG, ex.getMessage());
       }
        catch (Exception ex)
        {
            Log.e(TAG, ex.getMessage());
        }
        return null;
    }

    protected void onPostExecute(JSONObject result)
    {
        this.dbWorkerDelegate.taskFinished(true, result);
    }

    private String readURLReturnData(HttpURLConnection httpURLConnection)
    {
        String result = null;
        StringBuffer sb = new StringBuffer();
        InputStream is = null;

        try{
            is = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";
            while((inputLine = br.readLine()) != null){
                sb.append(inputLine);
            }
            result = sb.toString();
        }
        catch(Exception e){
            Log.i(TAG, "Error Reading Input Stream");
            result = null;
        }
        finally{
            if(is != null){
                try{
                    is.close();
                }
                catch(IOException e){}
            }
        }
        return result;

    }
}

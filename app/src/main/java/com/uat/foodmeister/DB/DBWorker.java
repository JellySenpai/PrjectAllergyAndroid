package com.uat.foodmeister.DB;

import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;

import org.json.JSONObject;

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
<<<<<<< HEAD
import com.uat.foodmeister.User.UserAccount;
import com.uat.foodmeister.User.UserProfile;
=======
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0

/**
 * Created by rayhardi on 3/10/2017.
 */

public class DBWorker extends AsyncTask<String, Void, JSONObject>
{
    private final String TAG = "DBWorker";
    private DBWorkerDelegate delegate;
    private String URL;
    public void setOnFinishedListener(DBWorkerDelegate delegate)
    {
        this.delegate = delegate;
    }
    public static final String VERIFY_REGISTERED_EMAIL_URL = "http://thefoodmeister.com/verify-login-android.php";

    @Override
    protected JSONObject doInBackground(String... params)
    {
        try{
            URL url = new URL(params[0]);
            String email = params[1];
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();

            JSONObject jsonObject = new JSONObject(readURLReturnData(httpURLConnection));

            httpURLConnection.disconnect();
            return jsonObject;
    }
        catch (MalformedURLException ex)
        {
            Log.e(TAG, ex.getMessage());
        }
        catch (IOException ex) {
            Log.e(TAG, ex.getMessage());
        }
        catch (Exception ex){
            Log.e(TAG, ex.getMessage());
        }
        return null;
    }
    @Override
    protected void onPostExecute(JSONObject result) {
        this.delegate.taskFinished(true, result);
    }
    private String readURLReturnData(HttpURLConnection connection){
        String result = null;
        StringBuffer sb = new StringBuffer();
        InputStream is = null;

        try{
            is = new BufferedInputStream(connection.getInputStream());
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

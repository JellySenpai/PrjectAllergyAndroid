package com.uat.foodmeister.Registration;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.util.StringBuilderPrinter;

import com.uat.foodmeister.DB.DBWorkerDelegate;
import com.uat.foodmeister.User.UserAccount;
import com.uat.foodmeister.User.UserProfile;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.security.spec.ECField;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataBaseHelper extends AsyncTask<UserAccount,Void,JSONObject> {

    private final String TAG = "DBHelper";

    private String email, name, gender;

    private int houseHoldSize, numOfProfiles;

    private UserAccount userAccount;
    private UserProfile userProfile;

    private Context context;

    private Method method;

    private DBWorkerDelegate delegate;

    private HashMap<String, Integer> allergyMap;

    public DataBaseHelper(UserAccount account) {
        this.userAccount = account;
        this.userProfile = account.getUserProfile(account.getName());
    }

    public void setOnFinishedListener(DBWorkerDelegate delegate){
        this.delegate = delegate;
    }

    @Override
    protected JSONObject doInBackground(UserAccount... params) {

        String login_url = "http://thefoodmeister.com/register-new-user.php";

        try {

            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String post_data = postDataReturn("fullName", userAccount.getName()) + "&" + postDataReturn("email", userAccount.getEmail());


                    //+ "&" + postDataReturn("houseHoldSize", "1") + "&"
           // + postDataReturn("numOfProfiles", "1") + "&" + postDataReturn("gender", userProfile.getGender().toString()) + "&";

           /* Iterator it = userProfile.getAllergyMap().entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                post_data = post_data + postDataReturn(pair.getKey().toString(), pair.getValue().toString()) + "&";
            }

            post_data = post_data.substring(0, post_data.length() -1);
            */
            Log.i(TAG, post_data);

            bufferedWriter.write(post_data);

            bufferedWriter.flush();

            bufferedWriter.close();

            outputStream.close();

            JSONObject jsonObject = new JSONObject(readURLReturnData(httpURLConnection));

            httpURLConnection.disconnect();

            return jsonObject;

        } catch (MalformedURLException ex) {
            Log.e(TAG, ex.getMessage());
        } catch (IOException ex) {
            Log.e(TAG, ex.getMessage());
        }catch (Exception ex)
        {
            Log.e(TAG, ex.getMessage());
        }
        if (method.equals("login"))
        {

        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        this.delegate.taskFinished(true, result);
    }

    String postDataReturn(String key, String val) throws IOException{
        return  URLEncoder.encode(key, "UTF-8")+"="+URLEncoder.encode(val, "UTF-8");
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

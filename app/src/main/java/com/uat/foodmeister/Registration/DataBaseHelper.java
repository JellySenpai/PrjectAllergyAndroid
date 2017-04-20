package com.uat.foodmeister.Registration;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.util.StringBuilderPrinter;

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
import com.uat.foodmeister.DB.DBWorkerDelegate;
import com.uat.foodmeister.User.UserAccount;
import com.uat.foodmeister.User.UserProfile;

import org.json.JSONObject;

<<<<<<< HEAD
=======
=======
import com.uat.foodmeister.User.UserProfile;

>>>>>>> d5f05734d3ce1c56188fe3aa3e6a2a5edea4ccf3
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
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
<<<<<<< HEAD
import java.security.spec.ECField;
=======
<<<<<<< HEAD
import java.security.spec.ECField;
=======
>>>>>>> d5f05734d3ce1c56188fe3aa3e6a2a5edea4ccf3
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataBaseHelper extends AsyncTask<UserAccount,Void,JSONObject> {

    private final String TAG = "DBHelper";
<<<<<<< HEAD
=======

    private final String TAG = "DBHelper";
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0

    private String email, name, gender;

    private int houseHoldSize, numOfProfiles;

<<<<<<< HEAD
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
=======
<<<<<<< HEAD
    private UserAccount userAccount;
    private UserProfile userProfile;

    private Context context;

    private DBWorkerDelegate delegate;

    private HashMap<String, Integer> allergyMap;

    public DataBaseHelper(UserAccount account) {
        this.userAccount = account;
        this.userProfile = account.getUserProfile(account.getName());
    }

    public void setOnFinishedListener(DBWorkerDelegate delegate){
        this.delegate = delegate;
=======
    private UserProfile m_userProfile;

    private Context context;

    private HashMap<String, Integer> allergyMap;

    public DataBaseHelper(Context context, UserProfile profile) {

        this.context = context;
        this.m_userProfile = profile;
>>>>>>> d5f05734d3ce1c56188fe3aa3e6a2a5edea4ccf3
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
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

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
            String post_data = postDataReturn("fullName", userAccount.getName()) + "&" + postDataReturn("email", userAccount.getEmail());


                    //+ "&" + postDataReturn("houseHoldSize", "1") + "&"
           // + postDataReturn("numOfProfiles", "1") + "&" + postDataReturn("gender", userProfile.getGender().toString()) + "&";

           /* Iterator it = userProfile.getAllergyMap().entrySet().iterator();
<<<<<<< HEAD
=======
=======
            String post_data = postDataReturn("fullName", m_userProfile.getUserName()) + "&" + postDataReturn("email", m_userProfile.getEmail()) + "&" + postDataReturn("houseHoldSize", "1") + "&"
            + postDataReturn("numOfProfiles", "1") + "&" + postDataReturn("gender", m_userProfile.getGender().toString()) + "&";

            Iterator it = m_userProfile.getAllergyMap().entrySet().iterator();
>>>>>>> d5f05734d3ce1c56188fe3aa3e6a2a5edea4ccf3
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0

            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                post_data = post_data + postDataReturn(pair.getKey().toString(), pair.getValue().toString()) + "&";
            }

            post_data = post_data.substring(0, post_data.length() -1);
<<<<<<< HEAD
            */
=======
<<<<<<< HEAD
            */
=======

>>>>>>> d5f05734d3ce1c56188fe3aa3e6a2a5edea4ccf3
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
            Log.i(TAG, post_data);

            bufferedWriter.write(post_data);

            bufferedWriter.flush();

            bufferedWriter.close();

            outputStream.close();

<<<<<<< HEAD
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
=======
<<<<<<< HEAD
            JSONObject jsonObject = new JSONObject(readURLReturnData(httpURLConnection));

            httpURLConnection.disconnect();

            return jsonObject;

        } catch (MalformedURLException ex) {
            Log.e(TAG, ex.getMessage());
        } catch (IOException ex) {
            Log.e(TAG, ex.getMessage());
        }catch (Exception ex){
            Log.e(TAG, ex.getMessage());
=======
            String urlResult = readURLReturnData(httpURLConnection);

            Log.i(TAG + " urlResult", urlResult);

            httpURLConnection.disconnect();

        } catch (MalformedURLException e) {
            Log.i(TAG, e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, e.getMessage());
>>>>>>> d5f05734d3ce1c56188fe3aa3e6a2a5edea4ccf3
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
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

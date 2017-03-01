package com.uat.foodmeister.Registration;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.util.StringBuilderPrinter;

import com.uat.foodmeister.User.UserProfile;

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
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataBaseHelper extends AsyncTask<DataBaseHelper,Void,Void> {

    private final String TAG = "DBHelper";

    private String email, name, gender;

    private int houseHoldSize, numOfProfiles;

    private UserProfile m_userProfile;

    private Context context;

    private HashMap<String, Integer> allergyMap;

    public DataBaseHelper(Context context, UserProfile profile) {

        this.context = context;
        this.m_userProfile = profile;
    }

    @Override
    protected Void doInBackground(DataBaseHelper... params) {

        String login_url = "http://thefoodmeister.com/register-new-user.php";

        try {

            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String post_data = postDataReturn("fullName", m_userProfile.getUserName()) + "&" + postDataReturn("email", m_userProfile.getEmail()) + "&" + postDataReturn("houseHoldSize", "1") + "&"
            + postDataReturn("numOfProfiles", "1") + "&" + postDataReturn("gender", m_userProfile.getGender().toString()) + "&";

            Iterator it = m_userProfile.getAllergyMap().entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                post_data = post_data + postDataReturn(pair.getKey().toString(), pair.getValue().toString()) + "&";
            }

            post_data = post_data.substring(0, post_data.length() -1);

            Log.i(TAG, post_data);

            bufferedWriter.write(post_data);

            bufferedWriter.flush();

            bufferedWriter.close();

            outputStream.close();

            String urlResult = readURLReturnData(httpURLConnection);

            Log.i(TAG + " urlResult", urlResult);

            httpURLConnection.disconnect();

        } catch (MalformedURLException e) {
            Log.i(TAG, e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        //alertDialog = new AlertDialog.Builder(context).create();
        //alertDialog.setTitle("Restaurant Info");
    }

//    @Override
//    protected void onPostExecute(DataBaseHelper result) {
//
//        //alertDialog.setMessage(Html.fromHtml(result));
//        //.show();
//        //alertDialog.getWindow().setLayout(2000,2400);
//        //String running = "running";
//        //Log.d("running", "successful yay");
//    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
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

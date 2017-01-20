package com.uat.foodmeister.Registration;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;

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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataBaseHelper extends AsyncTask<DataBaseHelper,Void,Void> {

    private String email, name, gender;

    private int houseHoldSize, numOfProfiles;

    private Context context;

    private HashMap<String, Integer> allergyMap;

    public DataBaseHelper(Context context, String email, String name, int houseHoldSize, int numOfProfiles, String gender, HashMap<String, Integer> allergyMap) {

        this.context = context;

        this.email = email;

        this.name = name;

        this.houseHoldSize = houseHoldSize;

        this.numOfProfiles = numOfProfiles;

        this.gender = gender;

        this.allergyMap = allergyMap;
    }

    @Override
    protected Void doInBackground(DataBaseHelper... params) {

        String login_url = "http://thefoodmeister.com/register-user-profile.php";

        try {

            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String post_data = postDataReturn("name", name) + "&" + postDataReturn("email", email) + "&" + postDataReturn("houseHoldSize", "1") + "&"
            + postDataReturn("numOfProfiles", "1") + "&" + postDataReturn("gender", gender) + "&";

            Iterator it = allergyMap.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                post_data = post_data + postDataReturn(pair.getKey().toString(), pair.getValue().toString()) + "&";
            }

            post_data = post_data.substring(0, post_data.length() -1);

            Log.i("DATABASEHELPER", post_data);

            bufferedWriter.write(post_data);

            bufferedWriter.flush();

            bufferedWriter.close();

            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();

            //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            //bufferedReader.close();

            inputStream.close();

            httpURLConnection.disconnect();

        } catch (MalformedURLException e) {
            Log.i("DATABASEHELPER", e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("DATABASEHELPER", e.getMessage());
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
}

package com.uat.foodmeister;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.StackView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.uat.foodmeister.DB.DBWorkerDelegate;
import com.uat.foodmeister.User.UserAccount;
import com.uat.foodmeister.User.UserProfile;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment implements DBWorkerDelegate {
    DBProfileRetrieve dbProfileRetrieve;
    MainActivity mainActivity;
    UserAccount userAccount;
    private String name,email;
    private TextView emailEntry;
    private EditText nameField, emailE;
    private final String Tag = "Taggy";
    private TableLayout tableLayout;



    public MyAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_my_account, container, false);

        emailEntry = (TextView) v.findViewById(R.id.emailText);
        emailE = (EditText) v.findViewById(R.id.editText1);
        nameField = (EditText) v.findViewById(R.id.editText);
        tableLayout = (TableLayout) v.findViewById(R.id.prof_Table);
        mainActivity = (MainActivity)getActivity();
        userAccount = mainActivity.userAccount;
        dbProfileRetrieve = new DBProfileRetrieve(userAccount.getEmail());

        dbProfileRetrieve.execute();

        dbProfileRetrieve.setOnFinishedListener(this);

        
        return v;
    }

    private void updateFunction()
    {
        ArrayList <UserProfile> userProfileArrayList = userAccount.getAllUserProfiles();
        for(UserProfile profile: userProfileArrayList)
        {
            TableRow tableRow = (TableRow) getActivity().getLayoutInflater().inflate(R.layout.custom_table_row, null);
            StackView stackView = (StackView) tableRow.findViewById(R.id.prof_Stack);
            TextView textView = (TextView) tableRow.findViewById(R.id.prof_name);


            textView.setText(profile.getUserName());
            tableLayout.addView(tableRow);

        }
    }
    @Override
    public void taskFinished(boolean isSuccess, JSONObject returnJSON)
    {
        // Todo - parse the JSOn data

        /*
        Parse Json data, looping the number the of profiles
        Step 1; get number of profiles out of Json
        Step 2: Loop num of profile times, grabbing and parsing profiles 1 by 1
        Step 3: Update user account with user profiles
         */
        Log.i(Tag, returnJSON.toString());
        String jData = "";




        try {
            int numProfiles = returnJSON.getInt("num_user_profiles");
            name = returnJSON.getString("full_name");
            email = returnJSON.getString("email");
            ArrayList<UserProfile> tempProfiles = new ArrayList<>();
            String[] tracked_allergies = AppConfig.TRACKED_ALLERGIES;

            int i = 0;
            while(i < numProfiles){
                JSONObject tempProfileObj = returnJSON.getJSONObject("profile-"+(i+1));
                UserProfile tempProfile = new UserProfile();
                tempProfile.setUserName(tempProfileObj.getString("name"));
                tempProfile.setBirthDate(tempProfileObj.getString("birthdate"));
                tempProfile.setEmail(email);


                for(int j = 0; j<tracked_allergies.length; j++)
                {
                    Boolean isChecked = tempProfileObj.getBoolean(tracked_allergies[j]);
                    if (isChecked)
                    {
                        tempProfile.changeAllergy(tracked_allergies[j], true);
                    }
                }

                tempProfiles.add(tempProfile);
                //Log.i(Tag, tempProfileObj.toString());
                i++;
            }
            userAccount.setNewProfiles(tempProfiles);
            //getFragmentManager().beginTransaction().detach(this).commitAllowingStateLoss();
            //getFragmentManager().beginTransaction().attach(this).commitAllowingStateLoss();
            updateFunction();
        } catch (JSONException ex)
        {
            Log.i(Tag, returnJSON.toString());
            //Log.i(Tag, name.toString());
        }


    }

}

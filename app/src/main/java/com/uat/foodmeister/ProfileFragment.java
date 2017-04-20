package com.uat.foodmeister;


<<<<<<< HEAD
import android.content.Context;
import android.nfc.Tag;
=======
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.AppLaunchChecker;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
<<<<<<< HEAD
import android.util.Log;
=======
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import com.uat.foodmeister.BackgroundWorker;
import com.uat.foodmeister.DB.DBWorker;
import com.uat.foodmeister.DB.DBWorkerDelegate;
import com.uat.foodmeister.Registration.DataBaseHelper;
import com.uat.foodmeister.User.UserAccount;
import com.uat.foodmeister.User.UserGender;
import com.uat.foodmeister.User.UserProfile;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
=======
import android.widget.TextView;
import android.widget.Toast;

import com.uat.foodmeister.User.UserGender;
import com.uat.foodmeister.User.UserProfile;

>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment  {

    FragmentManager manager;
<<<<<<< HEAD
    DBProfileRetrieve dbProfileRetrieve;

    private boolean itemChanged;
    UserProfile userProfile;
    UserAccount userAccount;
    private TextView emailEntry;
    private EditText nameField, emailE;
    private MainActivity mainActivity;
    private Button btn_addProf;
    Toast toasty;
    private Context context;
    private final String Tag = "TAGMOTHAFUCKA";
    private String name,email;
=======

    private boolean itemChanged;
    UserProfile userProfile;
    private MainActivity mainActivity;
    private Button btn_addProf;
    Toast toasty;
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0




    public ProfileFragment()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = getFragmentManager();

<<<<<<< HEAD
        mainActivity = (MainActivity)getActivity();
        //dbWorker = new BackgroundWorker(userAccount);





=======
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        mainActivity = (MainActivity) getActivity();
<<<<<<< HEAD
        View v = inflater.inflate(R.layout.activity_mainprofile, container, false);
        emailEntry = (TextView) v.findViewById(R.id.textView7);
        emailE = (EditText) v.findViewById(R.id.date_holder);
        nameField = (EditText) v.findViewById(R.id.editText10);


        userAccount = (UserAccount) getActivity().getIntent().getSerializableExtra("UserAccount");



        //btn_addProf = (Button)v.findViewById(R.id.btn_addProfile);
        //btn_addProf.setOnClickListener(new View.OnClickListener() {
           // @Override
            //public void onClick(View v) {
               // mainActivity.changeFragment(AppConfig.LOAD_ADD_PROFILE_FRAGMENT, "Raymond Harding");
            //}
        //});
=======
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        btn_addProf = (Button)v.findViewById(R.id.btn_addProfile);
        btn_addProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.changeFragment(AppConfig.LOAD_ADD_PROFILE_FRAGMENT, "Raymond Harding");
            }
        });
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
        if (mainActivity.userAccount.isAtMaxProfiles())
        {
            btn_addProf.setEnabled(false);

            Toast.makeText(mainActivity.getApplicationContext(), "You are at max profiles",Toast.LENGTH_SHORT).show();
        }
        return v;

    }

<<<<<<< HEAD
    public void onLoadProfiles()
    {

    }

=======
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
    public void addProfiles()
    {

    }

    public void deleteProfiles()
    {

    }

    public void addProfile(View view){

    }



}

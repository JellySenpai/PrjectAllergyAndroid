package com.uat.foodmeister;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.uat.foodmeister.Registration.DataBaseHelper;
import com.uat.foodmeister.User.UserProfile;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddProfileFragment extends Fragment {
    private MainActivity mainActivity;
    private UserProfile userProfile;


    public AddProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_profile, container, false);
        mainActivity = (MainActivity)getActivity();
        String name = "";
        if((name = getArguments().getString("profileName")) != null){
            userProfile = mainActivity.userAccount.getUserProfile(name);
        }
        else{
            //do something on failure to get profile, like go back to my account screen
            mainActivity.changeFragment(R.id.nav_profile, null);

        }

        return v;
    }

}

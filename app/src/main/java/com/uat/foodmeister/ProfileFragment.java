package com.uat.foodmeister;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.AppLaunchChecker;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.uat.foodmeister.User.UserGender;
import com.uat.foodmeister.User.UserProfile;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment  {

    FragmentManager manager;

    private boolean itemChanged;
    UserProfile userProfile;
    private MainActivity mainActivity;
    private Button btn_addProf;
    Toast toasty;




    public ProfileFragment()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = getFragmentManager();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        mainActivity = (MainActivity) getActivity();
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        btn_addProf = (Button)v.findViewById(R.id.btn_addProfile);
        btn_addProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.changeFragment(AppConfig.LOAD_ADD_PROFILE_FRAGMENT, "Raymond Harding");
            }
        });
        if (mainActivity.userAccount.isAtMaxProfiles())
        {
            btn_addProf.setEnabled(false);

            Toast.makeText(mainActivity.getApplicationContext(), "You are at max profiles",Toast.LENGTH_SHORT).show();
        }
        return v;

    }

    public void addProfiles()
    {

    }

    public void deleteProfiles()
    {

    }

    public void addProfile(View view){

    }



}

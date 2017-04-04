package com.uat.foodmeister.User;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by rayhardi on 3/11/2017.
 */

public class UserAccount implements Serializable
{
    protected String name;
    protected String email;
    protected int iD;
    protected int numUserProfile;

    protected final int maxUserProfile = 1;
    protected ArrayList<UserProfile> userProfiles;


    public UserAccount(String name, String email)
    {
        this.name = name;
        this.email = email;
        this.loadUserProfile();
    }
    public UserAccount()
    {

    }

    public String getName()
    {
        return name;
    }

    public int getNumUserProfile()
    {
        return numUserProfile;
    }

    public int getMaxUserProfile()
    {
        return maxUserProfile;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void addUserProfile(UserProfile profile)
    {
        if (this.numUserProfile != this.maxUserProfile)
        {
            this.userProfiles.add(profile);
        }
        else
        {
            //Write an exception that limits user profile
        }
    }

    public void editUserProfile()
    {

    }
    public UserProfile getUserProfile(String name)
    {
        for (UserProfile profile : this.userProfiles)
        {
            if (profile.getUserName() == name)
            {
                return profile;
            }
        }
        return null;
    }
    private void loadUserProfile()
    {
        this.userProfiles = new ArrayList<UserProfile>();
    }

    public boolean isAtMaxProfiles()
    {
        return this.userProfiles.size() == this.maxUserProfile;
    }

}

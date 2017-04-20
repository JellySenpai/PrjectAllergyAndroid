package com.uat.foodmeister.User;

<<<<<<< HEAD
import android.widget.Button;

=======
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
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
<<<<<<< HEAD
    protected Button button;
=======
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0


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
<<<<<<< HEAD

=======
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
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
<<<<<<< HEAD

    public ArrayList <UserProfile> getAllUserProfiles()
    {
        return this.userProfiles;
    }
=======
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
    private void loadUserProfile()
    {
        this.userProfiles = new ArrayList<UserProfile>();
    }

    public boolean isAtMaxProfiles()
    {
        return this.userProfiles.size() == this.maxUserProfile;
    }

<<<<<<< HEAD
    public void setNewProfiles(ArrayList<UserProfile> profiles)
    {
        this.userProfiles = profiles;
    }

=======
>>>>>>> 8a7b2569e5b51f89d471add4d821594c082d2ac0
}

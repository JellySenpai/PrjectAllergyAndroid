package com.uat.foodmeister;

import java.util.HashMap;

/**
 * Created by Kyle Tommet on 12/20/2016.
 */

public class TempUserProfile {
    private String m_UserName, m_email, m_birthdate, m_gender;
    private HashMap<String, Integer> allergies;

    public TempUserProfile(){
        this.allergies = new HashMap<String, Integer>();
        this.m_UserName = null;
        this.m_email = null;
        this.m_gender = null;
        this.m_birthdate = null;
    }
    public void setUserName(String name){
        this.m_UserName = name;
    }
    public String getUserName(){
        return this.m_UserName;
    }
    public void setBirthDate(String date){
        this.m_birthdate = date;
    }
    public String getBirthDate(){
        return this.m_birthdate;
    }
    public String toString(){
        return "Name: " + this.m_UserName + " Birthdate: " + this.m_birthdate;
    }
}

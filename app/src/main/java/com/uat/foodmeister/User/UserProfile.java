package com.uat.foodmeister.User;


import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;

public class UserProfile implements Serializable {

    private static final String TAG = "UserProfile";
    private String name;
    private String email;
    private UserGender gender;
    private String birthDate;
    private HashMap<String, Boolean> allergies;
    private String[] allergyNames = {"milk", "egg", "wheat", "fish", "shellfish", "peanut", "tree_nut", "soy"};

    public UserProfile(){
        this(null, UserGender.DEFAULT, null, "");
    }
    public UserProfile(String name, UserGender gender, String birthdate, String email){
        this.name      = name;
        this.gender    = gender;
        this.birthDate = birthdate;
        this.email = email;
        loadAllergiesIntoMap();
    }
    //Class Getters/Setters
    public void setUserName(String name){
        this.name = name;
    }
    public String getUserName(){
        return this.name;
    }
    public void setBirthDate(String date){
        this.birthDate = date;
    }
    public String getBirthDate(){
        return this.birthDate;
    }
    public String getEmail() {return this.email;}
    public void setEmail(String email) {this.email = email;}
    public void setUserGender(UserGender gender){this.gender = gender;}
    public String getGender(){return this.gender.toString();}
    public HashMap <String, Boolean> getAllergyMap() {return this.allergies;}
    public Boolean getAllergyVal(String name) {return this.allergies.get(name);}
    public String toString(){
        return "Name: " + name+ " Gender: " + gender + "Email: " +email + " Birthdate: " + birthDate + " Allergies" + allergies.toString();
    }

    //initializes the allergies HashMap to all false depending on the allergy array
    private void loadAllergiesIntoMap(){
        allergies = new HashMap<>();
        for(int i = 0; i < allergyNames.length; i++) {
            allergies.put(allergyNames[i], Boolean.FALSE);
        }
    }
    //allows for allergies to be either toggled to true or false
    public void changeAllergy(String name, Boolean value){
        allergies.put(name, value);
    }
}
package com.uat.foodmeister.Registration;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;

import com.uat.foodmeister.R;
import com.uat.foodmeister.User.UserProfile;

public class AllergyActivity extends AppCompatActivity {

    HashMap<String, Integer> allergyMap = new HashMap<String, Integer>();

    private String[] allergiesArray = {"milk", "egg", "wheat", "fish", "shellfish", "peanut", "treenut", "soy"};

    private int darkBlue;

    private int lightBlue;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        setContentView(R.layout.activity_allergy);

        Intent temporaryIntent = getIntent();
        UserProfile newUserProfile= (UserProfile) temporaryIntent.getSerializableExtra("UserProfile");

        Log.i("AllergyActivity", newUserProfile.toString());

        darkBlue = Color.BLUE;

        lightBlue = ContextCompat.getColor(this, R.color.light_blue);

        sortArray(allergiesArray);

        ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.text_view_layout, allergiesArray);

        ListView listView = (ListView) findViewById(R.id.allergy_list);

        listView.setAdapter(adapter);

        putAllergiesInMap();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String item = ((TextView)view).getText().toString();

                changeMapValue(item);

                changeViewColor(item, view);
            }
        });

        Button confirmButton = (Button) findViewById(R.id.confirm_button_allergy);

        confirmButton.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                //DataBaseHelper dataBaseHelper = new DataBaseHelper(getBaseContext(), UserProfile.email, UserProfile.name, 1, 1, UserProfile.gender, allergyMap);

                //dataBaseHelper.execute(dataBaseHelper);

            }
        });
    }

    private void changeMapValue(String item) {
        int val = allergyMap.get(item);

        if (val == 0) {
            allergyMap.put(item, 1);
        } else if (val == 1) {
            allergyMap.put(item, 0);
        } else {
            /* For Safety */
            allergyMap.put(item, 0);
        }
    }

    private void changeViewColor(String item, View v) {

        int val = (Integer)allergyMap.get(item);

        if (val == 0) {
            v.setBackgroundColor(lightBlue);
        } else if (val == 1) {
            v.setBackgroundColor(darkBlue);
        } else {
            /* For Safety */
            v.setBackgroundColor(lightBlue);
        }

    }

    //TODO Read from database
    private void putAllergiesInMap() {
        allergyMap.put("milk", 0);
        allergyMap.put("egg", 0);
        allergyMap.put("wheat", 0);
        allergyMap.put("fish", 0);
        allergyMap.put("shellfish", 0);
        allergyMap.put("peanut", 0);
        allergyMap.put("treenut", 0);
        allergyMap.put("soy", 0);
    }

    private void sortArray(String[] arry) {
        Arrays.sort(arry);
    }
}

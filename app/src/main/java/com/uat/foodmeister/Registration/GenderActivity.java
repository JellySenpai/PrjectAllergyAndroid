package com.uat.foodmeister.Registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.uat.foodmeister.R;

public class GenderActivity extends AppCompatActivity implements View.OnClickListener{

    private String gender = "";

    private String TAG = "Gender Activity";

    private String name = "";

    private String email = "";

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        setContentView(R.layout.registration_activity_v3);

        //ImageButton femaleButton = (ImageButton) findViewById(R.id.female_button);

       // ImageButton maleButton = (ImageButton) findViewById(R.id.male_button);

        TextView textView = (TextView) findViewById(R.id.gendertextView);

        //name = UserProfile.name;

        String genderTextViewText = "Hello, " + name + " what is your gender?";

        textView.setText(genderTextViewText);

        //femaleButton.setOnClickListener(this);

        //maleButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.female_button:
                gender = "female";
                break;
            case R.id.male_button:
                gender = "male";
                break;
        }
        Log.i(TAG, "User selected gender: " + gender);

        startDateActivity();
    }

    private void startDateActivity() {
        Intent dateIntent = new Intent(this, DateActivity.class);

        dateIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //UserProfile.gender = gender;

        Log.i(TAG, "Stashing " + gender);

        startActivity(dateIntent);
    }

    private String getFirstName(String name) {
        return name.substring(0, name.indexOf(" "));
    }
}

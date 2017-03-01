package com.uat.foodmeister;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.uat.foodmeister.Registration.AllergyActivity;
import com.uat.foodmeister.User.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Calendar birthDate;



    UserProfile newUserProfile;

    private static final String TAG = "RegistrationActivity";

    private EditText dateEdit, nameField, emailEntry;

    private Spinner userGender;

    private boolean bDaySet = false;
    private Button registerProfile;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateFormatter = new SimpleDateFormat("MM-dd-yyyy", Locale.US);

        dateEdit = (EditText) findViewById(R.id.dateEdit);
        //dateEdit.setInputType(InputType.emailEntry);
        nameField = (EditText) findViewById(R.id.users_name);
        userGender = (Spinner) findViewById(R.id.genderEntry);

        registerProfile = (Button) findViewById(R.id.btnSubmit);


        //setDateTimeField();

    }
    private void setDateTimeField() {
        //dateEdit.setOnClickListener(this);

        int year = 1993;

        int month = 0;

        int day = 19;

        datePickerDialog = new DatePickerDialog(this, R.style.style_date_picker_dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                birthDate = Calendar.getInstance();

                birthDate.set(year, month, dayOfMonth);

                dateEdit.setText(dateFormatter.format(birthDate.getTime()));
            }
        }, year, month, day);

        datePickerDialog.setTitle("Date of Birth");
    }
    @Override
    public void onClick(View v) {
        if (v == dateEdit) {
            datePickerDialog.show();
        }
    }
    public void registerNewAccount(View v){
        boolean correctInfo = true;

        String name = nameField.getText().toString();
        if(TextUtils.isEmpty(nameField.getText().toString().trim())){
            Log.i(TAG, "TEST");
            nameField.setError("This Field is Required");
            correctInfo = false;
        }

       /* UserGender gender = UserGender.DEFAULT;

        String tempGenderContainer = userGender.getItemAtPosition(userGender.getSelectedItemPosition()).toString();
        switch (tempGenderContainer) {
            case "Male":
                gender = UserGender.MALE;
                break;
            case "Female":
                gender = UserGender.FEMALE;
                break;
            case "Other":
                gender = UserGender.OTHER;
                break;
            default:
                gender = UserGender.DEFAULT;
                correctInfo = false;
                break;

        }*/
        String birthdate = dateEdit.getText().toString();
        if(TextUtils.isEmpty(birthdate)){
            correctInfo = false;
        }
        if(correctInfo){
            newUserProfile = new UserProfile(name, UserGender.DEFAULT, birthdate, "test@gamil.com");

            Log.i(TAG, newUserProfile.toString());
            //here will transition to next Activity which will get the allergy info
            Intent intent = new Intent(this, AllergyActivity.class);
            intent.putExtra("UserProfile", newUserProfile);
            startActivity(intent);
        }


    }
}

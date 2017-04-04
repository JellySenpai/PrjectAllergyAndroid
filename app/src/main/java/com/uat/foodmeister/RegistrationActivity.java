package com.uat.foodmeister;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.uat.foodmeister.DB.DBWorkerDelegate;
import com.uat.foodmeister.Registration.DataBaseHelper;
import com.uat.foodmeister.User.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Set;


public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener, DBWorkerDelegate, DatePickerDialog.OnDateSetListener {

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Calendar birthDate;


    UserAccount newUserAccount;
    UserProfile newUserProfile;

    DataBaseHelper dbHelper;

    private static final String TAG = "RegistrationActivity";

    private EditText nameField, emailEntry;

    private TextView dateEdit;

    private Spinner userGender;

    private boolean bDaySet = false;
    private Button registerProfile;






    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newUserAccount = (UserAccount) getIntent().getSerializableExtra("UserAccount");

        dbHelper = new DataBaseHelper(newUserAccount);
        dbHelper.setOnFinishedListener(this);

        dateFormatter = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
        dateEdit = (TextView) findViewById(R.id.bDayText);
        dateEdit.setOnClickListener(this);
        dateEdit.setShowSoftInputOnFocus(false);

        nameField = (EditText) findViewById(R.id.users_name);
        nameField.setText(newUserAccount.getName());

        emailEntry = (EditText) findViewById(R.id.emailEntry);
        emailEntry.setText(newUserAccount.getEmail());

        userGender = (Spinner) findViewById(R.id.genderEntry);

        registerProfile = (Button) findViewById(R.id.createButton);



    }


    @Override
    public void onClick(View v) {
        showMeDates(v);
    }
    public void registerNewAccount(View v){
        boolean correctInfo = true;

        String name = nameField.getText().toString();
        if(TextUtils.isEmpty(nameField.getText().toString().trim())){
            Log.i(TAG, "TEST");
            nameField.setError("This Field is Required");
            correctInfo = false;
        }

        UserGender gender = UserGender.DEFAULT;

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

        }
        String birthdate = dateEdit.getText().toString();
        if(TextUtils.isEmpty(birthdate)){
            correctInfo = false;
        }

        newUserProfile = new UserProfile(newUserAccount.getName(), gender, birthdate, newUserAccount.getEmail());
        newUserAccount.addUserProfile(newUserProfile);

        dbHelper.execute(newUserAccount);
    }

    @Override
    public void taskFinished(boolean isSuccess, JSONObject returnJSON) {
        try{
            boolean wasRegistered = returnJSON.getBoolean("account_registered");
            if(wasRegistered){
                //TODO: place name and enail into sharedPreferences before navigating to MainActivity
                FMSharedPrefs.save(this, AppConfig.ACCOUNT_HOLDER_EMAIL, newUserAccount.getEmail());
                FMSharedPrefs.save(this, AppConfig.ACCOUNT_HOLDER_NAME, newUserAccount.getName());
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("UserAccount", newUserAccount);
                startActivity(intent);
                finish();
            }
        }catch(JSONException ex){
            Log.e(TAG, ex.getMessage());
        }
    }
   public void showMeDates(View view)
   {
       DatePickerDialogy datePickerDialogy = new DatePickerDialogy();
       datePickerDialogy.show(getFragmentManager(), "datePickerDialogy");
   }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,dayOfMonth);
        dateEdit.setText(dateFormatter.format(calendar.getTime()));
    }

}

package com.uat.foodmeister.Registration;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.uat.foodmeister.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText dateText;
    private EditText rayDateText;

    private DatePickerDialog datePickerDialog;

    private SimpleDateFormat dateFormatter;

    private Button  confirmButton;

    private final String TAG = "DATE";

    private boolean bDaySet = false;

    private Calendar birthDate;

    private String name = "";

    private String email = "";

    private String gender = "";

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        setContentView(R.layout.registration_activity_v3);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        findViewById();

        setDateTimeField();

    }

    private void findViewById() {
        dateText = (EditText) findViewById(R.id.dateEdit);


        dateText.setInputType(InputType.TYPE_NULL);

        dateText.requestFocus();

        dateText.addTextChangedListener(textWatcher);

        confirmButton = (Button) findViewById(R.id.confirm_button_date);

        confirmButton.setOnClickListener(this);
    }

    private void setDateTimeField() {
        dateText.setOnClickListener(this);

        int year = 1993;

        int month = 0;

        int day = 19;

        datePickerDialog = new DatePickerDialog(this, R.style.style_date_picker_dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                birthDate = Calendar.getInstance();

                birthDate.set(year, month, dayOfMonth);

                dateText.setText(dateFormatter.format(birthDate.getTime()));
            }
        }, year, month, day);

        datePickerDialog.setTitle("Date of Birth");
    }

    @Override
    public void onClick(View v) {
        if (v == dateText) {
            datePickerDialog.show();
        } else if (v == confirmButton) {
            startAllergyActivity();
        }
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            bDaySet = true;
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void startAllergyActivity() {

        if (!bDaySet) {
            Log.i("DATE", "Bdate Null");
            return;
        }

        //TODO test that birthdate is within so many years

        Log.i("DATE", "Bdate Set");

        Intent allergyIntent = new Intent(this, AllergyActivity.class);

        //UserProfile.birthDate = birthDate;

//        allergyIntent.putExtra(getResources().getString(R.string.date_of_birth), birthDate);
//
//        allergyIntent.putExtra(getResources().getString(R.string.name), name);
//
//        allergyIntent.putExtra(getResources().getString(R.string.name), name);

        allergyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(allergyIntent);
    }
}
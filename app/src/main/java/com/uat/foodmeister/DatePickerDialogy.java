package com.uat.foodmeister;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by rayhardi on 3/24/2017.
 */

public class DatePickerDialogy extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);

        return new DatePickerDialog(getActivity(),(RegistrationActivity)getActivity(), year, month, day);
    }



}


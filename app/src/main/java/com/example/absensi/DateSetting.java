package com.example.absensi;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

public class DateSetting implements DatePickerDialog.OnDateSetListener {

    Context context;

    public DateSetting(Context context) {
        this.context = context;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

        int i3 = i1+1;

    }
}

package com.jia.jason.jgametest.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import com.jia.jason.jgametest.R;

import java.util.Calendar;

public class DatePickerTest extends BaseActivity {
    public static final String TAG = "DatePickerTest";

    private TextView mDateTv;
    private CalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_picker_layout);

        mDateTv = (TextView) findViewById(R.id.date_tv);
        mDateTv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.date_tv) {
            Log.d(TAG, "onclick:date_tv");
            showDatePickerDialog();
        }
    }

    private void showDatePickerDialog() {
        final StringBuffer time = new StringBuffer();
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(DatePickerTest.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                time.append(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);
                Log.d(TAG, time.toString());
                mDateTv.setText(time.toString());
            }
        }, year, month, day);
        datePickerDialog.show();

    }
}

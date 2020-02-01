package com.android.attendance.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.attendance.bean.EventBean;
import com.android.attendance.bean.QueryBean;
import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;

import java.util.Calendar;


public class AddEventActivity extends Activity {
    private ImageButton date;
    private Calendar cal;
    private int day;
    private int month;
    private int dyear;
    private EditText dateEditText,event_time,event_venue,event_name;
    Button eventtoupload;
    public static int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        date = (ImageButton) findViewById(R.id.DateImageButton);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        dyear = cal.get(Calendar.YEAR);
        dateEditText = (EditText) findViewById(R.id.DateEditText);
        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                showDialog(0);

            }
        });
        event_time=(EditText)findViewById(R.id.event_time);


        event_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar currentime = Calendar.getInstance();
                int hour = currentime.get(Calendar.HOUR_OF_DAY);
                int minute = currentime.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog;
                timePickerDialog = new TimePickerDialog(AddEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        event_time.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });

        event_name=(EditText)findViewById(R.id.event_name);
        event_venue=(EditText)findViewById(R.id.event_venue);
        eventtoupload=(Button)findViewById(R.id.eventtoupload);
        eventtoupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=event_name.getText().toString();
                String venue=event_venue.getText().toString();
                String time=event_time.getText().toString();
                String date =dateEditText.getText().toString();

                if(TextUtils.isEmpty(name))
                {
                    event_name.setError("Please enter name of the event");
                }
                else if(TextUtils.isEmpty(venue))
                {
                    event_venue.setError("Please enter venue");
                }
                else if(TextUtils.isEmpty(time))
                {
                    event_time.setError("Please enter timing of the event");
                }
                else if (TextUtils.isEmpty(date))
                {
                    dateEditText.setError("Please choose Date of event");
                }
                else
                    {
                        EventBean eventBean = new EventBean();
                        eventBean.setEvent_name(name);
                        eventBean.setEvent_date(date);
                        eventBean.setEvent_venues(venue);
                        eventBean.setEvent_Timing(time);
                        DBAdapter dbAdapter = new DBAdapter(AddEventActivity.this);
                        dbAdapter.addEvent(eventBean);
                        count=1;
                        Intent intent= new Intent(AddEventActivity.this,FacultySection.class);
                        startActivity(intent);

                        finish();


                    }

            }
        });
    }
    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, dyear, month, day);
    }
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            dateEditText.setText(selectedDay + " - " + (selectedMonth + 1) + " - "
                    + selectedYear);
        }
    };
}

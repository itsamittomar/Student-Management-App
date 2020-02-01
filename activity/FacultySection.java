package com.android.attendance.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;

public class FacultySection extends Activity {
    ImageButton attendance_upload,homework_upload,user_question,event_upload;
    Button cancel;
    TextView badge;

    DBAdapter dbAdapter = new DBAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_section);
        badge=(TextView)findViewById(R.id.badge_event);
        homework_upload=(ImageButton)findViewById(R.id.homework_upload);
        homework_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultySection.this,AddHomeActivity.class);
                startActivity(intent);

            }
        });
        attendance_upload=(ImageButton)findViewById(R.id.attendance_upload);
        attendance_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultySection.this,AddAttandanceSessionActivity.class);
                startActivity(intent);
            }
        });
        user_question=(ImageButton)findViewById(R.id.user_question);
        user_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                badge.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(FacultySection.this,ViewQuery.class);
                startActivity(intent);
            }
        });

        event_upload=(ImageButton)findViewById(R.id.event_upload);
        event_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FacultySection.this,AddEventActivity.class);
                startActivity(intent);
            }
        });
        cancel=(Button)findViewById(R.id.canelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultySection.this,Loginsection.class);
                startActivity(intent);
                finish();
            }
        });
        if(queryActivity.querycount==1)
        {
            badge.setText("1");
            badge.setVisibility(View.VISIBLE);
        }





    }
}

package com.android.attendance.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.androidattendancesystem.R;

import java.nio.InvalidMarkException;

public class Loginsection extends Activity {
    ImageButton admin,faculty,student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsection);

        admin=(ImageButton)findViewById(R.id.admin_login);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userrole="admin";
                Intent intent=new Intent(Loginsection.this,LoginActivity.class);
                intent.putExtra("userrole", userrole);
                startActivity(intent);

            }
        });
        faculty=(ImageButton)findViewById(R.id.faculty_login);
        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userrole="faculty";
                Intent intent=new Intent(Loginsection.this,LoginActivity.class);
                intent.putExtra("userrole", userrole);
                startActivity(intent);

            }
        });
        student=(ImageButton)findViewById(R.id.student_login);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userrole="student";
                Intent intent=new Intent(Loginsection.this,LoginActivity.class);
                intent.putExtra("userrole", userrole);
                startActivity(intent);
            }
        });

    }
}

package com.android.attendance.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.androidattendancesystem.R;

public class StudentSection extends Activity {
    ImageButton homework,query,event;
    TextView badge,homeworkbadge;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_section);
        cancel=(Button)findViewById(R.id.canelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(StudentSection.this,Loginsection.class);
                startActivity(intent);
            }
        });

        homeworkbadge=(TextView)findViewById(R.id.badge_homework);
        if(AddHomeActivity.homeworkcount==1)
        {
            homeworkbadge.setText("1");
            homeworkbadge.setVisibility(View.VISIBLE);
        }



        badge=(TextView)findViewById(R.id.badge_event);
        if(AddEventActivity.count==1)
        {
            badge.setText("1");
           badge.setVisibility(View.VISIBLE);
        }
       homework=(ImageButton)findViewById(R.id.homework);
        homework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StudentSection.this,ShowHomework.class);
                startActivity(intent);

            }
        });
        query=(ImageButton)findViewById(R.id.query);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StudentSection.this, "Clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),queryActivity.class));
            }
        });
        event=(ImageButton)findViewById(R.id.event);
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               badge.setVisibility(View.INVISIBLE);
               startActivity(new Intent(getApplicationContext(),ViewEvent.class));
            }
        });


    }


    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

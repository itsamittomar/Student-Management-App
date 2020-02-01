package com.android.attendance.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.attendance.bean.HomeworkBean;
import com.android.attendance.bean.StudentBean;
import com.android.attendance.context.ApplicationContext;
import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;

import java.util.ArrayList;
import java.util.Calendar;

public  class AddHomeActivity extends Activity {
    private ImageButton date , viewhomework;
    private Calendar cal;
    private int day;
    private int month;
    private int dyear;
    EditText dateEditText,homework;
    Button upload_homework;
    Spinner Deptspinner1,Yearspinner1,Subjectspinner1;
    String branch = "cse";
    String year;
    String subject = "SC";
    String work;
    public static int homeworkcount=0;

    private String[] branchString = new String[] { "I","II","III","IV","V","VI","VII","VIII","IX","X"};
    private String[] yearString = new String[] {"2016","2017","2018","2019"};
    private String[] subjectSEString = new String[] {"ENGLISH","MATH","SCIENCE","G.K.","S.ST","HINDI","SANSKRIT"};
    private String[] subjectTEString = new String[] {"ENGLISH","MATH","SCIENCE","G.K.","S.ST","HINDI","SANSKRIT"};
    private String[] subjectBEString = new String[] {"ENGLISH","MATH","SCIENCE","G.K.","S.ST","HINDI","SANSKRIT"};

    private String[] subjectFinal = new String[]{"ENGLISH","MATH","SCIENCE","G.K.","S.ST","HINDI","SANSKRIT"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_homework);
        homework=(EditText)findViewById(R.id.homeworktoupload);
        Deptspinner1=(Spinner)findViewById(R.id.Deptspinner1);
        Yearspinner1=(Spinner)findViewById(R.id.Yearspinner1);
        Subjectspinner1=(Spinner)findViewById(R.id.Subjectspinner1);

        ArrayAdapter<String> adapter_branch = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, branchString);
        adapter_branch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Deptspinner1.setAdapter(adapter_branch);
        Deptspinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                branch =(String) Deptspinner1.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        //#########################Spinner 2############################
        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yearString);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Yearspinner1.setAdapter(adapter_year);
        Yearspinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?>  arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                year = (String) Yearspinner1.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        //#############Spinner 3 #####################################
        ArrayAdapter<String> adapter_subject = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subjectFinal);
        adapter_subject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Subjectspinner1.setAdapter(adapter_subject);
        Subjectspinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?>arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                subject =(String) Subjectspinner1.getSelectedItem();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });



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
        upload_homework=(Button)findViewById(R.id.upload_homework);
        upload_homework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String date =dateEditText.getText().toString();
                String work=homework.getText().toString();
                if(TextUtils.isEmpty(date))
                {
                    dateEditText.setError("Please Choose Date");
                }
                else if(TextUtils.isEmpty(work))
                {
                    homework.setError("Please Enter Homework");
                }
                else {
                    HomeworkBean homeworkBean = new HomeworkBean();
                    homeworkBean.setDepartment_homework(branch);
                    homeworkBean.setYear_Homework(year);
                    homeworkBean.setSubject_homework(subject);
                    homeworkBean.setHomewrok(work);
                    homeworkBean.setDate(date);
                    DBAdapter dbAdapter = new DBAdapter(AddHomeActivity.this);
                    dbAdapter.addHomework(homeworkBean);
                    homeworkcount = 1;
                    Intent intent = new Intent(AddHomeActivity.this, FacultySection.class);
                    startActivity(intent);
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
            dateEditText.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);
        }
    };
}

package com.android.attendance.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.android.attendance.bean.FacultyBean;
import com.android.attendance.bean.QueryBean;
import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;

public class queryActivity extends Activity {
    EditText Query_user, Query_regarding, Query_query;
    Button Query_Ask;
    public static int querycount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        Query_user=(EditText)findViewById(R.id.Query_user);
        Query_regarding=(EditText)findViewById(R.id.Query_regarding);
        Query_query=(EditText)findViewById(R.id.Query_query);
        Query_Ask=(Button) findViewById(R.id.Query_Ask);
         
        Query_Ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_query=Query_user.getText().toString();
                String regarding=Query_regarding.getText().toString();
                String question=Query_query.getText().toString();
                if(TextUtils.isEmpty(user_query))
                {
                    Query_user.setError("Enter Your Name");

                }
                else if(TextUtils.isEmpty(regarding))
                {
                    Query_regarding.setError("Please enter this field");

                }
               else if(TextUtils.isEmpty(question))
                {
                    Query_query.setError("Please enter your query");

                }
               else
                   {
                       QueryBean queryBean = new QueryBean();
                       queryBean.setQuery_user(user_query);
                       queryBean.setQuery_regarding(regarding);
                       queryBean.setQuery_query(question);
                       DBAdapter dbAdapter = new DBAdapter(queryActivity.this);
                       dbAdapter.addQuery(queryBean);
                       querycount=1;
                       showMessage("Success","Query Uploaded Succcesfully");
                       Intent intent= new Intent(queryActivity.this,StudentSection.class);
                       startActivity(intent);
                       clearText();
                       finish();





                   }
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

    public  void clearText()
    {
        Query_user.setText("");
        Query_regarding.setText("");
        Query_query.setText("");



    }
}

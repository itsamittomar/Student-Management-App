package com.android.attendance.activity;

 import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
 import android.app.AlertDialog;
 import android.content.DialogInterface;
 import android.os.Bundle;
 import android.util.Log;
 import android.view.View;
 import android.widget.AdapterView;
 import android.widget.ArrayAdapter;
import android.widget.ListView;
 import android.widget.Toast;

 import com.android.attendance.bean.AttendanceBean;
 import com.android.attendance.bean.FacultyBean;
 import com.android.attendance.bean.HomeworkBean;
import com.android.attendance.bean.StudentBean;
import com.android.attendance.context.ApplicationContext;
import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;

import java.util.ArrayList;

public class ShowHomework extends Activity {

    ArrayList<HomeworkBean> homeworkBeanList;

    private ArrayAdapter<String> listAdapter;
    private ListView listView ;
    DBAdapter dbAdapter = new DBAdapter(ShowHomework.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.__listview_main);
        listView=(ListView)findViewById(R.id.listview);

        final ArrayList<String> worklist = new ArrayList<String>();

        homeworkBeanList=dbAdapter.gethomework();

        for(HomeworkBean homeworkBean : homeworkBeanList)
        {
            String work = "Class: " + homeworkBean.getDepartment_homework()+" \nYear:"+homeworkBean.getYear_Homework()+"\nDate:"+homeworkBean.getDate()+"\nHomework:"+homeworkBean.getHomewrok();

            worklist.add(work);
            worklist.add("-------------------------------------------------------");
            Log.d("users: ", work);

        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.view_faculty_list, R.id.labelF, worklist);
        listView.setAdapter( listAdapter );
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int position, long arg3) {



                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ShowHomework.this);

                alertDialogBuilder.setTitle(getTitle()+"decision");
                alertDialogBuilder.setMessage("Are you sure want to delete the Homework data?");

                alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        worklist.remove(position);
                        listAdapter.notifyDataSetChanged();
                        listAdapter.notifyDataSetInvalidated();

                        dbAdapter.deleteHomework(homeworkBeanList.get(position).getHomewrok_id());
                        worklist.clear();
                        homeworkBeanList=dbAdapter.gethomework();

                        for(HomeworkBean homeworkBean : homeworkBeanList)
                        {
                            String work = "Class: " + homeworkBean.getDepartment_homework()+" \nYear:"+homeworkBean.getYear_Homework()+"\nDate:"+homeworkBean.getDate()+"\nHomework:"+homeworkBean.getHomewrok();

                            worklist.add(work);
                            worklist.add("-------------------------------------------------------");
                            Log.d("users: ", work);

                        }

                    }

                });
                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // cancel the alert box and put a Toast to the user
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(), "You choose cancel",
                                Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                // show alert
                alertDialog.show();





                return false;
            }
        });







    }
}

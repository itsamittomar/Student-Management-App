package com.android.attendance.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.attendance.bean.EventBean;
import com.android.attendance.bean.FacultyBean;
import com.android.attendance.bean.HomeworkBean;
import com.android.attendance.bean.QueryBean;
import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;

public class ViewEvent extends Activity {

    ArrayList<EventBean> eventBeanArrayList;
    private ListView listView;
    private ArrayAdapter<String> listAdapter;

    DBAdapter dbAdapter = new DBAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.__listview_main);

        listView = (ListView) findViewById(R.id.listview);
        final ArrayList<String> eventlist = new ArrayList<String>();

        eventBeanArrayList = dbAdapter.getAllEvent();
       

        for (EventBean eventBean : eventBeanArrayList) {
            String queries = "Date:     " + eventBean.getEvent_date().toUpperCase() +"\nTiming:     " + eventBean.getEvent_Timing().toUpperCase() +"\nVenue:     " + eventBean.getEvent_venues().toUpperCase()+"\nName:     " + eventBean.getEvent_name().toUpperCase();

            eventlist.add(queries);
            eventlist.add("-------------------------------------------------------");
            Log.d("users: ", queries);

        }

        listAdapter = new ArrayAdapter<String>(this, R.layout.view_faculty_list, R.id.labelF, eventlist);
        listView.setAdapter(listAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int position, long arg3) {



                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewEvent.this);

                alertDialogBuilder.setTitle(getTitle()+"decision");
                alertDialogBuilder.setMessage("Are you sure want to delete the event data?");

                alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        eventlist.remove(position);
                        listAdapter.notifyDataSetChanged();
                        listAdapter.notifyDataSetInvalidated();

                        dbAdapter.deleteEvent(eventBeanArrayList.get(position).getEvent_id());

                        eventBeanArrayList=dbAdapter.getAllEvent();

                        for(EventBean eventBean : eventBeanArrayList)
                        {
                            String queries = "Date:     " + eventBean.getEvent_date().toUpperCase() +"\nTiming:     " + eventBean.getEvent_Timing().toUpperCase() +"\nVenue:     " + eventBean.getEvent_venues().toUpperCase()+"\nName:     " + eventBean.getEvent_name().toUpperCase();

                            eventlist.add(queries);
                            eventlist.add("-------------------------------------------------------");
                            Log.d("users: ", queries);

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

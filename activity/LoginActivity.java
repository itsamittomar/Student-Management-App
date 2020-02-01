package com.android.attendance.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.attendance.bean.FacultyBean;
import com.android.attendance.context.ApplicationContext;
import com.android.attendance.db.DBAdapter;
import com.example.androidattendancesystem.R;

public class LoginActivity extends Activity {
	String userrole;
	Button login;
	EditText username,password;
	Spinner spinnerloginas;

	private String[] userRoleString = new String[] { "Admin", "Faculty","Student"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		userrole = getIntent().getExtras().getString("userrole");

		login =(Button)findViewById(R.id.buttonlogin);
		username=(EditText)findViewById(R.id.editTextusername);
		password=(EditText)findViewById(R.id.editTextpassword);




		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if(userrole.equals("admin"))
				{

					String user_name = username.getText().toString();
					String pass_word = password.getText().toString();

					if (TextUtils.isEmpty(user_name)) 
					{
						username.setError("Invalid User Name");
					}
					else if(TextUtils.isEmpty(pass_word))
					{
						password.setError("enter password");
					}
					else
					{
						if(user_name.equals("admin") & pass_word.equals("admin123")){
						Intent intent =new Intent(LoginActivity.this,MenuActivity.class);
						startActivity(intent);

						clearText();
						finish();
						}else{
							showMessage("Warning!", " Invalid Credentials");
						}
					}
				}
				
				else if (userrole.equals("faculty"))
				{
					String user_name = username.getText().toString();
					String pass_word = password.getText().toString();

					if (TextUtils.isEmpty(user_name)) 
					{
						username.setError("Invalid User Name");
					}
					else if(TextUtils.isEmpty(pass_word))
					{
						password.setError("Enter password");
					}
					DBAdapter dbAdapter = new DBAdapter(LoginActivity.this);
					FacultyBean facultyBean = dbAdapter.validateFaculty(user_name, pass_word);
					
					if(facultyBean!=null)
					{
						Intent intent = new Intent(LoginActivity.this,FacultySection.class);
						startActivity(intent);
						((ApplicationContext)LoginActivity.this.getApplicationContext()).setFacultyBean(facultyBean);

						clearText();
						finish();
					}
					else
					{
						showMessage("Warning!", "Login Failed!!");
					}
				}
				else
                {

                    String user_name = username.getText().toString();
                    String pass_word = password.getText().toString();

					if (user_name.equals("admin") & pass_word.equals("12345"))
					{

						Intent intent = new Intent(LoginActivity.this,StudentSection.class);
						startActivity(intent);

						clearText();


					}

                    if (TextUtils.isEmpty(user_name))
                    {
                        username.setError("Invalid User Name");
                    }
                    else if(TextUtils.isEmpty(pass_word))
                    {
                        password.setError("Enter password");
                    }

                    else
                    {
						showMessage("Warning!", "Invalide Credentials");
						clearText();
						finish();
                    }
                }


			}
		});



	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
		username.setText("");
		password.setText("");

	}


}

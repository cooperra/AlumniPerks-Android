package com.example.roseperksapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	public static final String EXTRA_EMAIL = "com.example.roseperksapp.EMAIL";
	public static final String EXTRA_PASSWORD = "com.example.roseperksapp.PASSWORD";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onLoginButtonClick(View button) {
		// TODO: login here

		Intent intent = new Intent(this, CategoryMenuActivity.class);
		
//		EditText emailEditText = (EditText) findViewById(R.id.editText1);
//	    String email = emailEditText.getText().toString();
//	    
//	    EditText passwordEditText = (EditText) findViewById(R.id.editText2);
//	    String password = passwordEditText.getText().toString();
//	    
//	    intent.putExtra(EXTRA_EMAIL, email);
//	    intent.putExtra(EXTRA_PASSWORD, password);
	    
	    startActivity(intent);
	}

}

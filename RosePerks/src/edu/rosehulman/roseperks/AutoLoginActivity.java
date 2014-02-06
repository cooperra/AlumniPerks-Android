package edu.rosehulman.roseperks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AutoLoginActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String sessionkey = CredentialManager.getSessionKey(getApplicationContext());
		if ("".equals(sessionkey)) {
			Intent DetailView = new Intent(getApplicationContext(), LoginActivity.class);
			startActivity(DetailView);
		} else {
			Intent DetailView = new Intent(getApplicationContext(), MainScreenActivity.class);
			startActivity(DetailView);
		}
		finish();
	}
}

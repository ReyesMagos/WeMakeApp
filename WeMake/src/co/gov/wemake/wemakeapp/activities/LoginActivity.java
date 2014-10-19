package co.gov.wemake.wemakeapp.activities;

import java.io.ByteArrayOutputStream;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import co.gov.wemake.wemakeapp.R;
import co.gov.wemake.wemakeapp.controller.AbstractController;
import co.gov.wemake.wemakeapp.controller.LoginController;
import co.gov.wemake.wemakeapp.factory.FactoryUser;
import co.gov.wemake.wemakeapp.security.EncryptUtils;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class LoginActivity extends Activity {

	private EditText userNameEditText;
	private EditText passwordEditText;
	private LoginController loginController;
	private String username;
	private String password;
	private ActionBar action;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Parse.initialize(this.getApplicationContext(),
				getString(R.string.parse_id_number),
				getString(R.string.parse_key_number));
		createComponents();
		loginController = new LoginController(this);
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		username = sharedPreferences.getString("username", "NAN");
		password = sharedPreferences.getString("password", "NAN");
		action = getActionBar();

	}

	private void createComponents() {

		this.userNameEditText = (EditText) super
				.findViewById(R.id.txt_username);
		this.passwordEditText = (EditText) super
				.findViewById(R.id.txt_password);
		if (username != null && !username.equals("NAN"))
			userNameEditText.setText(username);
		if (password != null && !password.equals("NAN"))
			passwordEditText.setText(password);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	
	

	public void buttonLogin_Click(View view) {

		loginController.login(userNameEditText.getText().toString()
				.toLowerCase(), passwordEditText.getText().toString());
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub

		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Log.i("output", "PERRA2");
		return super.onOptionsItemSelected(item);
	}
	

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Log.i("output", "PERRA3");
		return super.onContextItemSelected(item);
	}

	public void singUp(View view) {
		loginController.changeActivity(RegisterActivity.class);
	}
}

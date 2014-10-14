package co.gov.wemake.wemakeapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import co.gov.wemake.wemakeapp.R;
import co.gov.wemake.wemakeapp.controller.LoginController;
import co.gov.wemake.wemakeapp.security.EncryptUtils;

import com.parse.Parse;

public class LoginActivity extends Activity {

	private EditText userNameEditText;
	private EditText passwordEditText;
	private LoginController loginController;
	private String username;
	private String password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Parse.initialize(this.getApplicationContext(),
				getString(R.string.parse_id_number),
				getString(R.string.parse_key_number));
		createComponents();
		loginController = new LoginController(this);
	}

	private void createComponents() {

		this.userNameEditText = (EditText) super
				.findViewById(R.id.txt_username);
		this.passwordEditText = (EditText) super
				.findViewById(R.id.txt_password);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void buttonLogin_Click(View view) {

		loginController.login(userNameEditText.getText().toString()
				.toLowerCase(), passwordEditText.getText().toString());
	}

	public void singUp(View view) {
		loginController.changeActivity(RegisterActivity.class);
	}
}

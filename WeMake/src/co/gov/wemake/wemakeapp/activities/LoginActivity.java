package co.gov.wemake.wemakeapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import co.gov.wemake.wemakeapp.R;

import com.parse.Parse;

public class LoginActivity extends Activity {

	private EditText userNameEditText;
	private EditText passwordEditText;

	private String username;
	private String password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		createComponents();
	}

	private void createComponents() {
		Parse.initialize(this, "oqPVy34Q3lVwxi56FcVcGDishkY9Wg2JVL7ggBL4",
				"EbmtfciIe91q9GZWp9jnsrsjKAidqDA5UHBS3dvj");
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
}

package co.gov.wemake.wemakeapp.activities;

import co.gov.wemake.wemakeapp.R;
import co.gov.wemake.wemakeapp.R.id;
import co.gov.wemake.wemakeapp.R.layout;
import co.gov.wemake.wemakeapp.R.menu;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterActivity extends Activity {

	private TextView txtName;
	private TextView txtAge;
	private TextView txtEmail;
	private TextView txtPhone;
	private AutoCompleteTextView acProfession;
	private ImageView btnNext;
	private AutoCompleteTextView acState;
	private AutoCompleteTextView acCity;
	private AutoCompleteTextView acNeighborhood;
	private AutoCompleteTextView acSkills;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		init();
	}

	private void init() {
		txtName = (TextView) findViewById(R.id.txt_name);
		txtAge = (TextView) findViewById(R.id.txt_age);
		txtEmail = (TextView) findViewById(R.id.txt_age);
		txtPhone = (TextView) findViewById(R.id.txt_phone);
		acProfession = (AutoCompleteTextView) findViewById(R.id.ac_profession);
		acState = (AutoCompleteTextView) findViewById(R.id.ac_state);
		acCity = (AutoCompleteTextView) findViewById(R.id.ac_city);
		acNeighborhood = (AutoCompleteTextView) findViewById(R.id.ac_neighborhood);
		acSkills = (AutoCompleteTextView) findViewById(R.id.ac_skills);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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

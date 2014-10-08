package co.gov.wemake.wemakeapp.activities;

import co.gov.wemake.wemakeapp.R;
import co.gov.wemake.wemakeapp.R.id;
import co.gov.wemake.wemakeapp.R.layout;
import co.gov.wemake.wemakeapp.R.menu;
import co.gov.wemake.wemakeapp.controller.RegisterController;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterActivity extends Activity {

	private TextView txtName;
	private TextView txtAge;
	private TextView txtEmail;
	private TextView txtPhone;
	private TextView txtLastname;
	private AutoCompleteTextView acProfession;
	private ImageView btnNextFirstLayout;
	private ImageView btnNextSecondLayout;
	private ImageView btnNextThirdLayout;
	private AutoCompleteTextView acState;
	private AutoCompleteTextView acCity;
	private AutoCompleteTextView acNeighborhood;
	private AutoCompleteTextView acSkills;
	private RegisterController registerController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initLayoutOneComponents();
		registerController = new RegisterController(this);

	}

	/**
	 * this method is for initialize the first layout components.
	 */
	private void initLayoutOneComponents() {
		txtName = (TextView) findViewById(R.id.txt_name);
		txtLastname = (TextView) findViewById(R.id.txt_lastname);
		txtAge = (TextView) findViewById(R.id.txt_age);
		txtEmail = (TextView) findViewById(R.id.txt_email);
		txtPhone = (TextView) findViewById(R.id.txt_phone);
		btnNextFirstLayout = (ImageView) findViewById(R.id.btn_next_first_layout);

	}

	public void btnNextFirstLayout_Click(View view) {
		registerController.verifyPartOneSingUpData(
				txtName.getText().toString(), txtLastname.getText().toString(),
				txtAge.getText().toString(), txtEmail.getText().toString(),
				txtPhone.getText().toString());
	}

	/**
	 * this method is for initialize the second layout components.
	 */
	private void initLayoutTwoComponents() {
		btnNextSecondLayout = (ImageView) findViewById(R.id.btn_next_second_layout);
		acProfession = (AutoCompleteTextView) findViewById(R.id.ac_profession);
		acSkills = (AutoCompleteTextView) findViewById(R.id.ac_skills);

	}

	/**
	 * this method is for initialize the third layout components.
	 */
	private void initLayoutThreeComponents() {
		acState = (AutoCompleteTextView) findViewById(R.id.ac_state);
		acCity = (AutoCompleteTextView) findViewById(R.id.ac_city);
		acNeighborhood = (AutoCompleteTextView) findViewById(R.id.ac_neighborhood);
		btnNextThirdLayout = (ImageView) findViewById(R.id.btn_next_third_layout);
	}

	public void changeToSecondLayout() {
		setContentView(R.layout.activity_register_part_two);
		initLayoutTwoComponents();
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

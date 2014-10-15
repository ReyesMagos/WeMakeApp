package co.gov.wemake.wemakeapp.activities;

import co.gov.wemake.wemakeapp.R;
import co.gov.wemake.wemakeapp.R.id;
import co.gov.wemake.wemakeapp.R.layout;
import co.gov.wemake.wemakeapp.R.menu;
import co.gov.wemake.wemakeapp.controller.ProfileController;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ProfileActivity extends Activity {

	private ProfileController profileController;
	private TextView txtName;
	private TextView txtLastname;
	private TextView txtEmail;
	private TextView txtProfession;
	private TextView txtSkills;
	private TextView txtCity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		profileController = new ProfileController(this);
		init();
		profileController.fillProfileInformation();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

	public void init() {
		txtName = (TextView) findViewById(R.id.txt_name);
		txtLastname = (TextView) findViewById(R.id.txt_lastname);
		txtEmail = (TextView) findViewById(R.id.txt_email);
		txtProfession = (TextView) findViewById(R.id.txt_profession);
		txtSkills = (TextView) findViewById(R.id.txt_skills);
		txtCity = (TextView) findViewById(R.id.txt_city);
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

	public ProfileController getProfileController() {
		return profileController;
	}

	public void setProfileController(ProfileController profileController) {
		this.profileController = profileController;
	}

	public TextView getTxtName() {
		return txtName;
	}

	public void setTxtName(TextView txtName) {
		this.txtName = txtName;
	}

	public TextView getTxtLastname() {
		return txtLastname;
	}

	public void setTxtLastname(TextView txtLastname) {
		this.txtLastname = txtLastname;
	}

	public TextView getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(TextView txtEmail) {
		this.txtEmail = txtEmail;
	}

	public TextView getTxtProfession() {
		return txtProfession;
	}

	public void setTxtProfession(TextView txtProfession) {
		this.txtProfession = txtProfession;
	}

	public TextView getTxtSkills() {
		return txtSkills;
	}

	public void setTxtSkills(TextView txtSkills) {
		this.txtSkills = txtSkills;
	}

	public TextView getTxtCity() {
		return txtCity;
	}

	public void setTxtCity(TextView txtCity) {
		this.txtCity = txtCity;
	}

}

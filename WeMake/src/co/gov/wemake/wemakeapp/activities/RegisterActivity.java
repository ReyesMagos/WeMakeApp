package co.gov.wemake.wemakeapp.activities;

import co.gov.wemake.wemakeapp.R;
import co.gov.wemake.wemakeapp.R.id;
import co.gov.wemake.wemakeapp.R.layout;
import co.gov.wemake.wemakeapp.R.menu;
import co.gov.wemake.wemakeapp.controller.RegisterController;
import android.app.Activity;
import android.media.audiofx.AcousticEchoCanceler;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	private TextView txtName;
	private TextView txtAge;
	private TextView txtEmail;
	private TextView txtPhone;
	private TextView txtLastname;
	private TextView txtUsername;
	private TextView txtPassword;
	private TextView txtPasswordConfirmation;
	private AutoCompleteTextView acProfession;
	private ImageView btnNextFirstLayout;
	private ImageView btnNextSecondLayout;
	private ImageView btnNextThirdLayout;
	private AutoCompleteTextView acState;
	private AutoCompleteTextView acCity;
	private AutoCompleteTextView acNeighborhood;
	private AutoCompleteTextView acSkills;
	private RegisterController registerController;
	private ArrayAdapter<String> acProfessionsAdapter;
	private ArrayAdapter<String> acSkillsAdapter;

	private ArrayAdapter<String> acStateAdapter;
	private ArrayAdapter<String> acCityAdapter;
	private ArrayAdapter<String> acNeighborhoodAdapter;
	private String stateSelected;
	private String citySelected;
	private String neighborhoodSelected;
	private String professionSelected;
	private String skillsSelected;

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

	public void btnNextSecondLayout_Click(View view) {
		registerController.verifyPartTwoSingUpData(professionSelected,
				skillsSelected);
	}

	public void btnNextThirdLayout_Click(View view) {
		registerController.verifyPartThreeSingUpData(stateSelected,
				citySelected, neighborhoodSelected);
	}

	public void btnNextFourthLayout_Click(View view) {
		registerController.verifyPartFourthSingUpData(txtUsername.getText()
				.toString(), txtPassword.getText().toString(),
				txtPasswordConfirmation.getText().toString());

	}

	/**
	 * this method is for initialize the second layout components.
	 */
	private void initLayoutTwoComponents() {
		btnNextSecondLayout = (ImageView) findViewById(R.id.btn_next_second_layout);
		acProfession = (AutoCompleteTextView) findViewById(R.id.ac_profession);
		acSkills = (AutoCompleteTextView) findViewById(R.id.ac_skills);

		acProfessionsAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				registerController.getProfessionArray());

		acSkillsAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				registerController.getSkillsArray());

		acProfession.setAdapter(acProfessionsAdapter);
		acSkills.setAdapter(acSkillsAdapter);

		acProfession.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub

				Toast.makeText(getApplicationContext(),
						adapter.getAdapter().getItem(position).toString(),
						Toast.LENGTH_LONG).show();

				professionSelected = adapter.getAdapter().getItem(position)
						.toString();

			}
		});

		acSkills.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub

				Toast.makeText(getApplicationContext(),
						adapter.getAdapter().getItem(position).toString(),
						Toast.LENGTH_LONG).show();

				skillsSelected = adapter.getAdapter().getItem(position)
						.toString();

			}
		});

	}

	/**
	 * this method is for initialize the third layout components.
	 */
	private void initLayoutThreeComponents() {
		acState = (AutoCompleteTextView) findViewById(R.id.ac_state);
		acCity = (AutoCompleteTextView) findViewById(R.id.ac_city);
		acNeighborhood = (AutoCompleteTextView) findViewById(R.id.ac_neighborhood);
		btnNextThirdLayout = (ImageView) findViewById(R.id.btn_next_third_layout);

		acStateAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				registerController.getStatesArray());
		acState.setAdapter(acStateAdapter);

		acState.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				stateSelected = adapter.getAdapter().getItem(position)
						.toString();

			}
		});

		acCityAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				registerController.getCityArray());
		acCity.setAdapter(acCityAdapter);

		acCity.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				citySelected = adapter.getAdapter().getItem(position)
						.toString();

			}
		});

		acNeighborhoodAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				registerController.getNeighborhoodArray());
		acNeighborhood.setAdapter(acNeighborhoodAdapter);

		acNeighborhood.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				neighborhoodSelected = adapter.getAdapter().getItem(position)
						.toString();

			}
		});

	}

	public void initLayoutFourthComponents() {
		txtUsername = (TextView) findViewById(R.id.txt_username);
		txtPassword = (TextView) findViewById(R.id.txt_password);
		txtPasswordConfirmation = (TextView) findViewById(R.id.txt_password_confirmation);

	}

	public void changeToSecondLayout() {
		setContentView(R.layout.activity_register_part_two);
		initLayoutTwoComponents();
	}

	public void changeToThirdLayout() {
		setContentView(R.layout.activity_register_part_tree);
		initLayoutThreeComponents();
	}

	public void changeToFourthLayout() {
		setContentView(R.layout.activity_register_fourth);
		initLayoutFourthComponents();
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

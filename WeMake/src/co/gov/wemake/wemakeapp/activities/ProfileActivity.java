package co.gov.wemake.wemakeapp.activities;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import co.gov.wemake.wemakeapp.R;
import co.gov.wemake.wemakeapp.R.id;
import co.gov.wemake.wemakeapp.R.layout;
import co.gov.wemake.wemakeapp.R.menu;
import co.gov.wemake.wemakeapp.controller.ProfileController;
import co.gov.wemake.wemakeapp.factory.FactoryUser;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends Activity {

	private ProfileController profileController;
	private TextView txtName;
	private TextView txtLastname;
	private TextView txtEmail;
	private TextView txtProfession;
	private TextView txtSkills;
	private TextView txtCity;

	private EditText txtEditName;
	private EditText txtEditLastname;
	private EditText txtEditEmail;

	private AutoCompleteTextView acEditProfession;
	private AutoCompleteTextView acEditSkills;
	private AutoCompleteTextView acEditCity;

	private ImageView ivProfilePicture;
	private ImageView ivEditProfilePicture;

	private ArrayAdapter<String> acProfessionsAdapter;
	private ArrayAdapter<String> acSkillsAdapter;
	private ArrayAdapter<String> acCityAdapter;

	private String professionSelected;
	private String skillsSelected;
	private String citySelected;

	private static final int READ_REQUEST_CODE = 42;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		profileController = new ProfileController(this);
		init();
		profileController.fillProfileInformation();

	}

	public void btnEditProfile_Click() {
		setContentView(R.layout.editable_profile);
		init2();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

	public void init() {
		ivProfilePicture = (ImageView) findViewById(R.id.iv_profile_picture);
		txtName = (TextView) findViewById(R.id.txt_name);
		txtLastname = (TextView) findViewById(R.id.txt_lastname);
		txtEmail = (TextView) findViewById(R.id.txt_email);
		txtProfession = (TextView) findViewById(R.id.txt_profession);
		txtSkills = (TextView) findViewById(R.id.txt_skills);
		txtCity = (TextView) findViewById(R.id.txt_city);

	}

	public void changeToLayoutOne() {
		setContentView(R.layout.activity_profile);
		init();
		profileController.fillProfileInformation();
	}

	public void cancelEditProcess(View view) {
		setContentView(R.layout.activity_profile);
		init();
		profileController.fillProfileInformation();
		
	}

	public void init2() {
		txtEditName = (EditText) findViewById(R.id.txt_edit_name);
		txtEditLastname = (EditText) findViewById(R.id.txt_edit_lastname);
		txtEditEmail = (EditText) findViewById(R.id.txt_edit_email);
		acEditProfession = (AutoCompleteTextView) findViewById(R.id.ac_edit_professions);
		acEditSkills = (AutoCompleteTextView) findViewById(R.id.ac_edit_skills);
		acEditCity = (AutoCompleteTextView) findViewById(R.id.ac_edit_city);
		ivEditProfilePicture = (ImageView) findViewById(R.id.iv_edit_profile_picture);

		acProfessionsAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				profileController.getProfessionsArray());

		acSkillsAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				profileController.getSkillsArray());

		acEditProfession.setAdapter(acProfessionsAdapter);

		acEditSkills.setAdapter(acSkillsAdapter);

		acEditProfession.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				professionSelected = adapter.getAdapter().getItem(position)
						.toString();
			}
		});

		acEditSkills.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				professionSelected = adapter.getAdapter().getItem(position)
						.toString();
			}
		});

		acCityAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				profileController.getCitysArray());
		acEditCity.setAdapter(acCityAdapter);
		acEditCity.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				citySelected = adapter.getAdapter().getItem(position)
						.toString();
			}
		});

		profileController.fillProfileInformationToEdit();

	}

	public void saveEditedInformation(View view) {
		profileController.verifyAndSaveEditedData(txtEditName.getText()
				.toString(), txtEditLastname.getText().toString(), txtEditEmail
				.getText().toString(), professionSelected, skillsSelected,
				citySelected);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.upload_profile_picture) {
			// performFileSearch();
			btnEditProfile_Click();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void setProfessionSkillAndCitySelected(String profession,
			String skills, String city) {
		this.professionSelected = profession;
		this.skillsSelected = skills;
		this.citySelected = city;
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

	public ImageView getIvProfilePicture() {
		return ivProfilePicture;
	}

	public void setIvProfilePicture(ImageView ivProfilePicture) {
		this.ivProfilePicture = ivProfilePicture;
	}

	public void changeProfilePicture(View view) {
		performFileSearch();
	}

	public EditText getTxtEditName() {
		return txtEditName;
	}

	public void setTxtEditName(EditText txtEditName) {
		this.txtEditName = txtEditName;
	}

	public EditText getTxtEditLastname() {
		return txtEditLastname;
	}

	public void setTxtEditLastname(EditText txtEditLastname) {
		this.txtEditLastname = txtEditLastname;
	}

	public EditText getTxtEditEmail() {
		return txtEditEmail;
	}

	public void setTxtEditEmail(EditText txtEditEmail) {
		this.txtEditEmail = txtEditEmail;
	}

	public AutoCompleteTextView getAcEditProfession() {
		return acEditProfession;
	}

	public void setAcEditProfession(AutoCompleteTextView atEditProfession) {
		this.acEditProfession = atEditProfession;
	}

	public AutoCompleteTextView getAcEditSkills() {
		return acEditSkills;
	}

	public void setAcEditSkills(AutoCompleteTextView atEditSkills) {
		this.acEditSkills = atEditSkills;
	}

	public AutoCompleteTextView getAcEditCity() {
		return acEditCity;
	}

	public void setAcEditCity(AutoCompleteTextView acEditCity) {
		this.acEditCity = acEditCity;
	}

	public ImageView getIvEditProfilePicture() {
		return ivEditProfilePicture;
	}

	public void setIvEditProfilePicture(ImageView ivEditProfilePicture) {
		this.ivEditProfilePicture = ivEditProfilePicture;
	}

	public String getProfessionSelected() {
		return professionSelected;
	}

	public void setProfessionSelected(String professionSelected) {
		this.professionSelected = professionSelected;
	}

	public String getSkillsSelected() {
		return skillsSelected;
	}

	public void setSkillsSelected(String skillsSelected) {
		this.skillsSelected = skillsSelected;
	}

	public String getCitySelected() {
		return citySelected;
	}

	public void setCitySelected(String citySelected) {
		this.citySelected = citySelected;
	}

	/**
	 * Fires an intent to spin up the "file chooser" UI and select an image.
	 */
	public void performFileSearch() {

		// ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's
		// file
		// browser.
		Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

		// Filter to only show results that can be "opened", such as a
		// file (as opposed to a list of contacts or timezones)
		intent.addCategory(Intent.CATEGORY_OPENABLE);

		// Filter to show only images, using the image MIME data type.
		// If one wanted to search for ogg vorbis files, the type would be
		// "audio/ogg".
		// To search for all documents available via installed storage
		// providers,
		// it would be "*/*".
		intent.setType("image/*");

		startActivityForResult(intent, READ_REQUEST_CODE);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode,
			Intent resultData) {

		// The ACTION_OPEN_DOCUMENT intent was sent with the request code
		// READ_REQUEST_CODE. If the request code seen here doesn't match, it's
		// the
		// response to some other intent, and the code below shouldn't run at
		// all.

		if (requestCode == READ_REQUEST_CODE
				&& resultCode == Activity.RESULT_OK) {

			// The document selected by the user won't be returned in the
			// intent.
			// Instead, a URI to that document will be contained in the return
			// intent
			// provided to this method as a parameter.
			// Pull that URI using resultData.getData().
			Uri uri = null;
			if (resultData != null) {
				uri = resultData.getData();
				try {
					Bitmap mBitmap = Media.getBitmap(this.getContentResolver(),
							uri);
					Log.i("Uri: ", uri.toString());
					profileController.saveProfilePicture(mBitmap);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

}

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
	private ImageView ivProfilePicture;

	private static final int READ_REQUEST_CODE = 42;

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
		ivProfilePicture = (ImageView) findViewById(R.id.iv_profile_picture);
		txtName = (TextView) findViewById(R.id.txt_name);
		txtLastname = (TextView) findViewById(R.id.txt_lastname);
		txtEmail = (TextView) findViewById(R.id.txt_email);
		txtProfession = (TextView) findViewById(R.id.txt_profession);
		txtSkills = (TextView) findViewById(R.id.txt_skills);
		txtCity = (TextView) findViewById(R.id.txt_city);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.upload_profile_picture) {
			performFileSearch();
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

	public ImageView getIvProfilePicture() {
		return ivProfilePicture;
	}

	public void setIvProfilePicture(ImageView ivProfilePicture) {
		this.ivProfilePicture = ivProfilePicture;
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

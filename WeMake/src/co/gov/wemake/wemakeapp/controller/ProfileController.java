package co.gov.wemake.wemakeapp.controller;

import java.io.ByteArrayOutputStream;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import co.gov.wemake.wemakeapp.R;
import co.gov.wemake.wemakeapp.activities.LoginActivity;
import co.gov.wemake.wemakeapp.activities.ProfileActivity;
import co.gov.wemake.wemakeapp.domain.entities.User;
import co.gov.wemake.wemakeapp.factory.FactoryUser;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ProfileController extends AbstractController {

	public ProfileController(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	public void fillProfileInformation() {
		User user = FactoryUser.getInstance().getCurrentUserInActivity();
		if (user == null)// We must return error message
			return;
		ProfileActivity profileActivity = (ProfileActivity) getActivity();
		profileActivity.getTxtName().setText(user.getName());
		profileActivity.getTxtLastname().setText(user.getLastname());
		profileActivity.getTxtEmail().setText(user.getEmail());
		profileActivity.getTxtProfession().setText(user.getProfession());
		profileActivity.getTxtSkills().setText(user.getSkills());
		profileActivity.getTxtCity().setText(user.getCity());
		getProfilePicture();

	}

	public void getProfilePicture() {

		
		byte[] byteArray = FactoryUser.getInstance()
				.getProfilePictureOfParseUser();
		if (byteArray != null) {
			Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
					byteArray.length);
			ProfileActivity profileActivity = (ProfileActivity) getActivity();
			profileActivity.getIvProfilePicture().setImageBitmap(bitmap);
		}
	

	}

	public void saveProfilePicture() {

		ParseUser user = FactoryUser.getInstance().getCurrentUserInActivity()
				.getParseUser();
		Bitmap picture = BitmapFactory.decodeResource(getActivity()
				.getResources(), R.drawable.yo);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		picture.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		// get byte array here
		byte[] bytearray = stream.toByteArray();
		if (bytearray != null) {
			showProgressDialog(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity().getResources().getString(
							R.string.uploading_profile_picture_message));
			ParseFile file = new ParseFile(FactoryUser.getInstance()
					.getCurrentUserInActivity().getName()
					+ FactoryUser.getInstance().getCurrentUserInActivity()
							.getLastname() + "profilepicture.jpg", bytearray);
			file.saveInBackground();
			user.put("profilepic", file);
			user.saveInBackground(new SaveCallback() {

				@Override
				public void done(ParseException arg0) {
					// TODO Auto-generated method stub
					if (arg0 == null) {
						Log.i("saved", "image Saved");
						showAlertMessage(
								getActivity().getResources().getString(
										R.string.alert_label),
								getActivity()
										.getResources()
										.getString(
												R.string.profile_picture_upload_success));
					} else {
						showAlertMessage(
								getActivity().getResources().getString(
										R.string.alert_label),
								getActivity().getResources().getString(
										R.string.profile_picture_upload_error));
					}
					dissmissProgressDialog();

				}
			});
		}
	}
}

package co.gov.wemake.wemakeapp.controller;

import java.io.ByteArrayOutputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import co.gov.wemake.wemakeapp.R;
import co.gov.wemake.wemakeapp.activities.ProfileActivity;
import co.gov.wemake.wemakeapp.domain.entities.User;
import co.gov.wemake.wemakeapp.factory.FactoryUser;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class ProfileController extends AbstractController {

	private String[] professionsArray;
	private String[] skillsArray;
	private String[] citysArray;

	public ProfileController(Activity activity) {
		super(activity);
		initStuff();
	}

	public void initStuff() {
		professionsArray = getActivity().getResources().getStringArray(
				R.array.professions_array);
		skillsArray = getActivity().getResources().getStringArray(
				R.array.skills_array);
		citysArray = getActivity().getResources().getStringArray(
				R.array.city_array);
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

	public void fillProfileInformationToEdit() {
		User user = FactoryUser.getInstance().getCurrentUserInActivity();
		if (user == null)// We must return error message
			return;
		ProfileActivity profileActivity = (ProfileActivity) getActivity();
		profileActivity.getTxtEditName().setText(user.getName());
		profileActivity.getTxtEditLastname().setText(user.getLastname());
		profileActivity.getTxtEditEmail().setText(user.getEmail());
		profileActivity.getAcEditProfession().setText(user.getProfession());
		profileActivity.getAcEditSkills().setText(user.getSkills());
		profileActivity.getAcEditCity().setText(user.getCity());
		if (user.getProfilePicture() != null)
			profileActivity.getIvEditProfilePicture().setImageBitmap(
					user.getProfilePicture());
		profileActivity.setProfessionSkillAndCitySelected(user.getProfession(),
				user.getSkills(), user.getCity());

	}

	public void getProfilePicture() {

		byte[] byteArray = FactoryUser.getInstance()
				.getProfilePictureOfParseUser();
		if (byteArray != null) {
			Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
					byteArray.length);
			ProfileActivity profileActivity = (ProfileActivity) getActivity();
			profileActivity.getIvProfilePicture().setImageBitmap(bitmap);
			FactoryUser.getInstance().getCurrentUserInActivity()
					.setProfilePicture(bitmap);
		}

	}

	public void saveProfilePicture(final Bitmap picture) {

		ParseUser user = FactoryUser.getInstance().getCurrentUserInActivity()
				.getParseUser();
		// Bitmap picture = BitmapFactory.decodeResource(getActivity()
		// .getResources(), R.drawable.yo);
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
						ProfileActivity profileActivity = (ProfileActivity) getActivity();
						profileActivity.getIvProfilePicture().setImageBitmap(
								picture);
						if (profileActivity.getIvEditProfilePicture() != null)
							profileActivity.getIvEditProfilePicture()
									.setImageBitmap(picture);
						FactoryUser.getInstance().getCurrentUserInActivity()
								.setProfilePicture(picture);

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

	@SuppressLint("NewApi")
	public boolean verifyString2(int lenght, int messageType, String input) {

		String lenghtErrorMessage = "";
		String formatErrorMessage = "";

		switch (messageType) {
		case 0:// this case its for name
			lenghtErrorMessage = getActivity().getResources().getString(
					R.string.name_lenght_error);
			formatErrorMessage = getActivity().getResources().getString(
					R.string.name_format_error);

			break;
		case 1:// this case its for lastname
			lenghtErrorMessage = getActivity().getResources().getString(
					R.string.lasname_lenght_error);
			formatErrorMessage = getActivity().getResources().getString(
					R.string.lastname_format_error);
			break;

		}

		if (input.length() < lenght) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity().getResources().getString(
							R.string.name_lenght_error));
			return false;
		}

		for (int i = 0; i < input.length(); i++) {
			if (!(Character.isAlphabetic(input.charAt(i)) || !Character
					.toString(input.charAt(i)).equals(" "))) {

				showAlertMessage(
						getActivity().getResources().getString(
								R.string.alert_label),
						getActivity().getResources().getString(
								R.string.name_format_error));
				return false;
			}

			if (Character.isDigit(input.charAt(i))) {
				showAlertMessage(
						getActivity().getResources().getString(
								R.string.alert_label),
						getActivity().getResources().getString(
								R.string.name_format_error));
				return false;
			}
		}
		return true;

	}

	public void verifyAndSaveEditedData(String name, String lastname,
			String email, String profession, String skills, String city) {
		if (name == null || name.equals(" ")) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label), getActivity()
							.getResources().getString(R.string.no_name_alert));
			return;

		}
		if (lastname == null || lastname.equals(" ")) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity().getResources().getString(
							R.string.no_lastname_alert));
			return;
		}

		if (email == null || email.equals(" ")) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label), getActivity()
							.getResources().getString(R.string.no_email_alert));
			return;
		}

		if (profession == null) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity().getResources().getString(
							R.string.no_profession_label));
			return;
		}

		if (skills == null) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label), getActivity()
							.getResources().getString(R.string.no_skills_label));
			return;
		}
		if (city == null) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label), getActivity()
							.getResources().getString(R.string.no_city_label));
		}
		if (!verifyString2(3, 0, name))
			return;
		if (!verifyString2(3, 0, lastname))
			return;
		if (!verifyEmail(email))
			return;

		final ParseUser parseUser = FactoryUser.getInstance()
				.getCurrentUserInActivity().getParseUser();
		if (parseUser != null) {
			parseUser.put("name", name);
			parseUser.put("lastname", lastname);
			parseUser.setEmail(email);
			parseUser.put("profession", profession);
			parseUser.put("skills", skills);

			parseUser.put("city", city);
			parseUser.saveInBackground(new SaveCallback() {

				@Override
				public void done(ParseException e) {
					// TODO Auto-generated method stub
					if (e == null) {
						showAlertMessage(
								getActivity().getResources().getString(
										R.string.alert_label),
								getActivity()
										.getResources()
										.getString(
												R.string.edit_information_save_succesfull));
						FactoryUser.getInstance().updateUser(parseUser);
						ProfileActivity profileActivity = (ProfileActivity) getActivity();
						profileActivity.changeToLayoutOne();
					} else {
						// code 101 is exception for invalid credentials
						// code 100 is exeption for network problems
						if (e.getCode() == 100) {
							showAlertMessage(
									getActivity().getResources().getString(
											R.string.alert_label),
									getActivity().getResources().getString(
											R.string.network_problem));

						} else if (e.getCode() == 101) {
							showAlertMessage(
									getActivity().getResources().getString(
											R.string.alert_label),
									getActivity().getResources().getString(
											R.string.invalid_login_credentials));

						} else {
							showAlertMessage(
									getActivity().getResources().getString(
											R.string.alert_label),
									getActivity()
											.getResources()
											.getString(
													R.string.edit_information_save_error));
						}
					}
				}
			});

		}

	}

	public String[] getProfessionsArray() {
		return professionsArray;
	}

	public void setProfessionsArray(String[] professionsArray) {
		this.professionsArray = professionsArray;
	}

	public String[] getSkillsArray() {
		return skillsArray;
	}

	public void setSkillsArray(String[] skillsArray) {
		this.skillsArray = skillsArray;
	}

	public String[] getCitysArray() {
		return citysArray;
	}

	public void setCitysArray(String[] citysArray) {
		this.citysArray = citysArray;
	}

	public boolean verifyEmail(String email) {
		String firstPartOfEmail = null;
		String secondPartOfEmail = null;
		for (int i = 0; i < email.length(); i++) {
			if (Character.toString(email.charAt(i)).equals("@")) {
				firstPartOfEmail = email.substring(0, i);
				System.out.println(firstPartOfEmail);
				if (!verifyStringThatHasNumberPointDash(firstPartOfEmail)) {
					showAlertMessage(
							getActivity().getResources().getString(
									R.string.alert_label),
							getActivity().getResources().getString(
									R.string.email_format_error));
					return false;
				}

				String aux = email.substring(i + 1, email.length());
				email = aux;
				System.out.println(email);
				break;

			}
		}

		if (firstPartOfEmail == null) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity().getResources().getString(
							R.string.email_format_error));
			return false;
		}

		for (int i = 0; i < email.length(); i++) {
			if (Character.toString(email.charAt(i)).equals(".")) {
				secondPartOfEmail = email.substring(0, i);
				System.out.println(secondPartOfEmail);
				if (!verifyStringThatHasNumberPoint(secondPartOfEmail)) {
					showAlertMessage(
							getActivity().getResources().getString(
									R.string.alert_label),
							getActivity().getResources().getString(
									R.string.email_format_error));
					return false;
				}
				String aux = email.substring(i + 1, email.length());
				email = aux;
				System.out.println(email);
				break;

			}
		}

		if (secondPartOfEmail == null) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity().getResources().getString(
							R.string.email_format_error));
			return false;
		}

		if (!verifyStringThatHasNumber(email)) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity().getResources().getString(
							R.string.email_format_error));
			return false;
		}
		return true;

	}

	@SuppressLint("NewApi")
	public boolean verifyStringThatHasNumberPointDash(String s) {
		if (s.length() < 4)
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (!(Character.isAlphabetic(s.charAt(i))
					|| Character.isDigit(s.charAt(i))
					|| Character.toString(s.charAt(i)).equals(".")
					|| Character.toString(s.charAt(i)).equals("_") || Character
					.toString(s.charAt(i)).equals("-"))) {
				return false;
			}

		}
		return true;

	}

	@SuppressLint("NewApi")
	public boolean verifyStringThatHasNumberPoint(String s) {
		if (s.length() < 4)
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (!(Character.isAlphabetic(s.charAt(i))
					|| Character.isDigit(s.charAt(i)) || Character.toString(
					s.charAt(i)).equals("."))) {
				return false;
			}

		}
		return true;

	}

	@SuppressLint("NewApi")
	public boolean verifyStringThatHasNumber(String s) {
		if (s.length() < 2)
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (!(Character.isAlphabetic(s.charAt(i)) || Character.isDigit(s
					.charAt(i)))) {
				return false;
			}

		}
		return true;

	}

}

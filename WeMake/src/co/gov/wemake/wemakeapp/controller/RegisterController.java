package co.gov.wemake.wemakeapp.controller;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import co.gov.wemake.wemakeapp.R;
import co.gov.wemake.wemakeapp.activities.RegisterActivity;
import co.gov.wemake.wemakeapp.security.EncryptUtils;
import android.annotation.SuppressLint;
import android.app.Activity;

public class RegisterController extends AbstractController {

	private String[] professionArray;
	private String[] skillsArray;
	private String[] statesArray;
	private String[] cityArray;
	private String[] neighborhoodArray;
	private String username;
	private String name;
	private String lastname;
	private String age;
	private String email;
	private String phone;
	private String profession;
	private String skill;
	private String state;
	private String city;
	private String neighborhood;
	private String password;

	public RegisterController(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
		initStuff();
	}

	public void initStuff() {
		professionArray = getActivity().getResources().getStringArray(
				R.array.professions_array);
		skillsArray = getActivity().getResources().getStringArray(
				R.array.skills_array);
		statesArray = getActivity().getResources().getStringArray(
				R.array.state_array);
		cityArray = getActivity().getResources().getStringArray(
				R.array.city_array);
		neighborhoodArray = getActivity().getResources().getStringArray(
				R.array.neighborhood_array);

	}

	public String[] getStatesArray() {
		return statesArray;
	}

	public void setStatesArray(String[] statesArray) {
		this.statesArray = statesArray;
	}

	public String[] getCityArray() {
		return cityArray;
	}

	public void setCityArray(String[] cityArray) {
		this.cityArray = cityArray;
	}

	public String[] getNeighborhoodArray() {
		return neighborhoodArray;
	}

	public void setNeighborhoodArray(String[] neighborhoodArray) {
		this.neighborhoodArray = neighborhoodArray;
	}

	public void verifyPartFourthSingUpData(String username, String password,
			String passwordConfirmation) {
		if (username == null || username.equals(" ")) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity().getResources().getString(
							R.string.username_lentght_error));
			return;
		}

		if (password == null || password.equals(" ")) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity().getResources().getString(
							R.string.password_length_error));
			return;
		}
		if (passwordConfirmation == null || passwordConfirmation.equals(" ")) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity().getResources().getString(
							R.string.confirmation_length_error));
			return;
		}
		if (!verifyUsername(username)) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity().getResources().getString(
							R.string.username_format_error));
			return;
		}

		if (!verifyUsername(password)) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity().getResources().getString(
							R.string.password_format_error));
			return;
		}

		if (!verifyUsername(passwordConfirmation)) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity().getResources().getString(
							R.string.password_confirmation_format_error));
			return;
		}
		if (!password.equals(passwordConfirmation)) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity()
							.getResources()
							.getString(
									R.string.password_do_not_match_with_confirmation_error));
			return;
		}
		this.username = username.toLowerCase();
		this.password = EncryptUtils.stringToSha1(password);
		singUpUser();

	}

	public void verifyPartTwoSingUpData(String professionSelected,
			String skillSelected) {

		if (professionSelected == null) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity().getResources().getString(
							R.string.no_profession_label));
			return;
		}

		if (skillSelected == null) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label), getActivity()
							.getResources().getString(R.string.no_skills_label));
			return;
		}

		this.profession = professionSelected;
		this.skill = skillSelected;

		RegisterActivity registerActivity = (RegisterActivity) getActivity();
		registerActivity.changeToThirdLayout();

	}

	public void verifyPartThreeSingUpData(String stateSelected,
			String citySelected, String neighborhoodSelected) {
		if (stateSelected == null) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label), getActivity()
							.getResources().getString(R.string.no_state_label));
			return;
		}

		if (citySelected == null) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label), getActivity()
							.getResources().getString(R.string.no_city_label));
			return;
		}
		if (neighborhoodSelected == null) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity().getResources().getString(
							R.string.no_neighborhood_label));
			return;
		}

		this.state = stateSelected;
		this.city = citySelected;
		this.neighborhood = neighborhoodSelected;
		RegisterActivity registerActivity = (RegisterActivity) getActivity();
		registerActivity.changeToFourthLayout();

	}

	public void singUpUser() {
		showProgressDialog(
				getActivity().getResources().getString(R.string.alert_label),
				getActivity().getResources()
						.getString(R.string.sing_up_ongoing));
		ParseUser user = new ParseUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.put("name", name);
		user.put("lastname", lastname);
		user.put("age", age);
		user.put("phone", phone);
		user.put("profession", profession);
		user.put("skills", skill);
		user.put("state", state);
		user.put("city", city);
		user.put("neighborhood", neighborhood);

		user.signUpInBackground(new SignUpCallback() {
			public void done(ParseException e) {
				if (e == null) {
					showAlertMessage(
							getActivity().getResources().getString(
									R.string.alert_label),
							getActivity().getResources().getString(
									R.string.sing_up_success_full));
				} else {
					showAlertMessage(
							getActivity().getResources().getString(
									R.string.alert_label),
							getActivity().getResources().getString(
									R.string.sing_up_error));
				}
				dissmissProgressDialog();
			}
		});
	}

	public String[] getProfessionArray() {
		return professionArray;
	}

	public void setProfessionArray(String[] professionArray) {
		this.professionArray = professionArray;
	}

	public String[] getSkillsArray() {
		return skillsArray;
	}

	public void setSkillsArray(String[] skillsArray) {
		this.skillsArray = skillsArray;
	}

	public void verifyPartOneSingUpData(String name, String lastname,
			String age, String email, String phone) {
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
		if (age == null || age.equals(" ")) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label), getActivity()
							.getResources().getString(R.string.no_age_alert));
			return;
		}
		if (email == null || email.equals(" ")) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label), getActivity()
							.getResources().getString(R.string.no_email_alert));
			return;
		}
		if (phone == null || phone.equals(" ")) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label), getActivity()
							.getResources().getString(R.string.no_phone_alert));
			return;
		}

		if (!verifyName(name))
			return;
		if (!verifyLastname(lastname))
			return;
		if (!verifyAge(age))
			return;
		if (!verifyEmail(email))
			return;
		if (!verifyPhone(phone))
			return;

		this.name = name;
		this.lastname = lastname;
		this.age = age;
		this.email = email;
		this.phone = phone;

		RegisterActivity registerActivity = (RegisterActivity) getActivity();
		registerActivity.changeToSecondLayout();

	}

	@SuppressLint("NewApi")
	public boolean verifyUsername(String username) {
		if (username.length() < 5) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity().getResources().getString(
							R.string.username_lentght_error));
			return false;
		}

		for (int i = 0; i < username.length(); i++) {
			if (!(Character.isAlphabetic(username.charAt(i)) || !Character
					.toString(username.charAt(i)).equals(" "))) {

				showAlertMessage(
						getActivity().getResources().getString(
								R.string.alert_label),
						getActivity().getResources().getString(
								R.string.username_format_error));
				return false;
			}

		}
		return true;

	}

	/**
	 * this method verify the name of the users. The name can contain numbers,
	 * but if has another especial character it will return false
	 * 
	 * @param name
	 *            the name of user
	 * @return true if name is ok, false beside
	 */
	@SuppressLint("NewApi")
	public boolean verifyName(String name) {
		if (name.length() < 3) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity().getResources().getString(
							R.string.name_lenght_error));
			return false;
		}

		for (int i = 0; i < name.length(); i++) {
			if (!(Character.isAlphabetic(name.charAt(i)) || !Character
					.toString(name.charAt(i)).equals(" "))) {

				showAlertMessage(
						getActivity().getResources().getString(
								R.string.alert_label),
						getActivity().getResources().getString(
								R.string.name_format_error));
				return false;
			}

			if (Character.isDigit(name.charAt(i))) {
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

	@SuppressLint("NewApi")
	public boolean verifyLastname(String lastname) {
		if (lastname.length() < 4) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity().getResources().getString(
							R.string.lasname_lenght_error));
			return false;

		}

		for (int i = 0; i < lastname.length(); i++) {
			if (!(Character.isAlphabetic(lastname.charAt(i)) || !Character
					.isWhitespace(lastname.charAt(i)))) {
				showAlertMessage(
						getActivity().getResources().getString(
								R.string.alert_label),
						getActivity().getResources().getString(
								R.string.lasname_lenght_error));
				return false;
			}
			if (Character.isDigit(lastname.charAt(i))) {
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

	/**
	 * this methos verify the age of the user. the age must be greater that 1
	 * and less that 3
	 * 
	 * @param age
	 *            age of the user
	 * 
	 * @return true if age is correct false beside
	 */
	public boolean verifyAge(String age) {
		if (age.length() < 2 || age.length() > 2) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label), getActivity()
							.getResources()
							.getString(R.string.age_lenght_error));
			return false;
		}

		try {
			Integer i = Integer.parseInt(age);
			if (i < 18) {
				showAlertMessage(
						getActivity().getResources().getString(
								R.string.alert_label),
						getActivity().getResources().getString(
								R.string.age_less_than_18));

			}
			return true;

		} catch (NumberFormatException n) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity().getResources().getString(
							R.string.age_no_number_error));
			return false;

		}
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

	public boolean verifyPhone(String phone) {
		if (phone.length() < 7 || phone.length() > 10) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity().getResources().getString(
							R.string.phone_length_error));
			return false;
		}

		for (int i = 0; i < phone.length(); i++) {
			if (!Character.isDigit(phone.charAt(i))) {
				showAlertMessage(
						getActivity().getResources().getString(
								R.string.alert_label),
						getActivity().getResources().getString(
								R.string.phone_format_error));
				return false;
			}
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

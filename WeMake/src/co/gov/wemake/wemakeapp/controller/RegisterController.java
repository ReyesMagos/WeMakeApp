package co.gov.wemake.wemakeapp.controller;

import co.gov.wemake.wemakeapp.R;
import co.gov.wemake.wemakeapp.activities.RegisterActivity;
import android.annotation.SuppressLint;
import android.app.Activity;

public class RegisterController extends AbstractController {

	public RegisterController(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
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
		RegisterActivity registerActivity = (RegisterActivity) getActivity();
		registerActivity.changeToSecondLayout();

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
			if (!(Character.isAlphabetic(name.charAt(i))
					|| Character.isDigit(name.charAt(i)) || Character
						.isWhitespace(name.charAt(i)))) {
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
			if (!(Character.isAlphabetic(lastname.charAt(i))
					|| Character.isDigit(lastname.charAt(i)) || Character
						.isWhitespace(lastname.charAt(i)))) {
				showAlertMessage(
						getActivity().getResources().getString(
								R.string.alert_label),
						getActivity().getResources().getString(
								R.string.lasname_lenght_error));
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

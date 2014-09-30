package co.gov.wemake.wemakeapp.controller;

import java.io.Console;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;

public class RegisterController extends AbstractController {

	public RegisterController(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	public void verifyPartOneSingUpData(String name, String age, String email,
			String phone, String profession) {

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
		if (name.length() < 14)
			return false;
		for (int i = 0; i < name.length(); i++) {
			if (!(Character.isAlphabetic(name.charAt(i))
					|| Character.isDigit(name.charAt(i)) || Character
						.isWhitespace(name.charAt(i)))) {
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
		if (age.length() < 2 || age.length() > 2)
			return false;
		try {
			Integer i = Integer.parseInt(age);
			return true;

		} catch (NumberFormatException n) {
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
				if (!verifyStringThatHasNumberPointDash(firstPartOfEmail))
					return false;
				String aux = email.substring(i + 1, email.length());
				email= aux;
				System.out.println(email);
				break;

			}
		}

		if (firstPartOfEmail == null)
			return false;

		for (int i = 0; i < email.length(); i++) {
			if (Character.toString(email.charAt(i)).equals(".")) {
				secondPartOfEmail = email.substring(0, i);
				System.out.println(secondPartOfEmail);
				if (!verifyStringThatHasNumberPoint(secondPartOfEmail))
					return false;
				String aux = email.substring(i + 1, email.length());
				email= aux;
				System.out.println(email);
				break;

			}
		}

		if (secondPartOfEmail == null)
			return false;

		if (!verifyStringThatHasNumber(email))
			return false;
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

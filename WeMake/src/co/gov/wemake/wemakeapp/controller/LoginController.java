package co.gov.wemake.wemakeapp.controller;

import co.gov.wemake.wemakeapp.R;
import co.gov.wemake.wemakeapp.factory.FactoryUser;
import co.gov.wemake.wemakeapp.security.EncryptUtils;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;

public class LoginController extends AbstractController {

	public LoginController(Activity activity) {
		super(activity);
	}

	@SuppressLint("NewApi")
	public boolean verifyUsername(String username) {
		if (username == null || username.equals(" ")) {
			return false;
		}
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

	@SuppressLint("NewApi")
	public boolean verifyPassword(String password) {
		if (password == null || password.equals(" ")) {
			return false;
		}
		if (password.length() < 5) {
			showAlertMessage(
					getActivity().getResources()
							.getString(R.string.alert_label),
					getActivity().getResources().getString(
							R.string.password_length_error));
			return false;
		}

		for (int i = 0; i < password.length(); i++) {
			if (!(Character.isAlphabetic(password.charAt(i)) || !Character
					.toString(password.charAt(i)).equals(" "))) {

				showAlertMessage(
						getActivity().getResources().getString(
								R.string.alert_label),
						getActivity().getResources().getString(
								R.string.password_format_error));
				return false;
			}

		}
		return true;
	}

	public void login(final String username, final String password) {
		showProgressDialog(
				getActivity().getResources().getString(R.string.alert_label),
				getActivity().getResources().getString(R.string.login_ongoing));
		if (!verifyUsername(username))
			return;
		if (!verifyPassword(password))
			return;
		String passwordEncrypted = EncryptUtils.stringToSha1(password);
		ParseUser.logInInBackground(username, passwordEncrypted,
				new LogInCallback() {

					@Override
					public void done(ParseUser user, ParseException e) {
						dissmissProgressDialog();
						if (user != null) {

							FactoryUser factoryUser = FactoryUser.getInstance();
							factoryUser.setUser(factoryUser.createUser(user));
							if (user.getBoolean("emailVerified"))
								Log.i("Login", "success");
							else
								showAlertMessage(
										getActivity().getResources().getString(
												R.string.alert_label),
										getActivity().getResources().getString(
												R.string.email_not_verified));

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
										getActivity()
												.getResources()
												.getString(
														R.string.invalid_login_credentials));

							} else {
								showAlertMessage(
										getActivity().getResources().getString(
												R.string.alert_label),
										getActivity().getResources().getString(
												R.string.login_error)
												+ e.getMessage());
							}

						}
					}
				});
	}
}

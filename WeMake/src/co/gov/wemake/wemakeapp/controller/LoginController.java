package co.gov.wemake.wemakeapp.controller;

import co.gov.wemake.wemakeapp.factory.FactoryUser;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.app.Activity;

public class LoginController extends AbstractController {

	public LoginController(Activity activity) {
		super(activity);
	}
	
	public void login(final String username, final String password){
		ParseUser.logInInBackground(username, password, new LogInCallback() {
			
			@Override
			public void done(ParseUser user, ParseException e) {
				if(user != null){
					FactoryUser factoryUser = FactoryUser.getInstance();
				}
			}
		});
	}

}

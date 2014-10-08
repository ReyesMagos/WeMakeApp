package co.gov.wemake.wemakeapp.factory;

import com.parse.ParseUser;

import co.gov.wemake.wemakeapp.domain.entities.User;
import co.gov.wemake.wemakeapp.factory.interfaces.IFactoryUser;

public class FactoryUser implements IFactoryUser{
	private User user;
	private static FactoryUser instance;
	
	private FactoryUser(){
		
	}
	
	public static FactoryUser getInstance(){
		if(instance == null){
			instance = new FactoryUser();
		}
		
		return instance;
	}

	@SuppressWarnings("unused")
	@Override
	public User creaUser(ParseUser parseUser) {
		User userMethod = null;
		if(userMethod != null){
			userMethod = new User(parseUser);
			this.user = userMethod;
		}
		return userMethod;
	}

	@Override
	public User getCurrentUserInActivity() {
		return this.user;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
		
	}
}

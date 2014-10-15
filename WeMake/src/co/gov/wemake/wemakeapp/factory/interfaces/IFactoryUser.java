package co.gov.wemake.wemakeapp.factory.interfaces;

import co.gov.wemake.wemakeapp.domain.entities.User;

import com.parse.ParseUser;

public interface IFactoryUser {

	public User createUser(ParseUser parseUser);

	public User getCurrentUserInActivity();

	public void setUser(User user);

}

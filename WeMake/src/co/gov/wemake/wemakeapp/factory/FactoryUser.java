package co.gov.wemake.wemakeapp.factory;

import com.parse.ParseUser;

import co.gov.wemake.wemakeapp.domain.entities.User;
import co.gov.wemake.wemakeapp.factory.interfaces.IFactoryUser;

public class FactoryUser implements IFactoryUser {
	private User user;

	private static FactoryUser instance;

	private FactoryUser() {

	}

	public static FactoryUser getInstance() {
		if (instance == null) {
			instance = new FactoryUser();
		}

		return instance;
	}

	@Override
	public User createUser(ParseUser parseUser) {
		// TODO Auto-generated method stub
		User user = new User(parseUser);
		user.setName(parseUser.getString("name"));
		user.setLastname(parseUser.getString("lastname"));
		user.setEmail(parseUser.getEmail());
		user.setAge(parseUser.getString("age"));
		user.setPhone(parseUser.getString("phone"));
		user.setState(parseUser.getString("state"));
		user.setCity(parseUser.getString("city"));
		user.setNeighborhood("neighborhood");
		user.setProfession(parseUser.getString("profession"));
		user.setSkills(parseUser.getString("skills"));

		return user;
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

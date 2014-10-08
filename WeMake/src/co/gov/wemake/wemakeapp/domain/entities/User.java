package co.gov.wemake.wemakeapp.domain.entities;

import java.util.List;

import com.parse.ParseUser;

public class User {

	private ParseUser parseUser;
	private String fullName;
	private String email;
	private String age;
	private String telephone;
	private String department;
	private String province;
	private List<String> habilities;
	
	public User(ParseUser user){
		this.setParseUser(user);
		this.setEmail(user.getEmail());
	}

	public ParseUser getParseUser() {
		return parseUser;
	}

	public void setParseUser(ParseUser parseUser) {
		this.parseUser = parseUser;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public List<String> getHabilities() {
		return habilities;
	}

	public void setHabilities(List<String> habilities) {
		this.habilities = habilities;
	}

}

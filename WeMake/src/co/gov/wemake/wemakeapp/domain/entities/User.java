package co.gov.wemake.wemakeapp.domain.entities;

import java.util.List;

import com.parse.ParseUser;

public class User {

	private ParseUser parseUser;
	private String name;
	private String lastname;
	private String email;
	private String age;
	private String phone;
	private String state;
	private String city;
	private String neighborhood;
	private String skills;
	private String profession;
	
	
	public User(ParseUser user){
		this.setParseUser(user);
		this.setEmail(user.getEmail());
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getNeighborhood() {
		return neighborhood;
	}


	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}


	public String getSkills() {
		return skills;
	}


	public void setSkills(String skills) {
		this.skills = skills;
	}


	public String getProfession() {
		return profession;
	}


	public void setProfession(String profession) {
		this.profession = profession;
	}


	public ParseUser getParseUser() {
		return parseUser;
	}

	public void setParseUser(ParseUser parseUser) {
		this.parseUser = parseUser;
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
		return phone;
	}

	public void setTelephone(String telephone) {
		this.phone = telephone;
	}

	public String getDepartment() {
		return state;
	}

	public void setDepartment(String department) {
		this.state = department;
	}

	public String getProvince() {
		return city;
	}

	public void setProvince(String province) {
		this.city = province;
	}

	public String getHabilities() {
		return skills;
	}

	public void setHabilities(String habilities) {
		this.skills = habilities;
	}

}

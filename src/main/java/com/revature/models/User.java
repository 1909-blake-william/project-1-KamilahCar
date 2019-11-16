package com.revature.models;

public class User {
	private int id, roleId;
	private String userName, userPass, firstName, lastName, email;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String userName, String userPass, String firstName, String lastName, String email, int roleId) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPass = userPass;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roleId = roleId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id +" roleId=" + roleId + "userName=" + userName + ", userPass=" + userPass + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + "]";
	}
	
}
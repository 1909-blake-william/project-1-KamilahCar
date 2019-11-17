package com.revature.driver;

import com.revature.models.User;

public class UserDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User currentUser = new User(1, "CCarlos", "CCarlos_123@work", "Cameron", "Carlos", "CCarlos@gmail.com", 2);
		System.out.println(currentUser.getUserPass().hashCode());
		//System.out.println(currentUser);
	}

}

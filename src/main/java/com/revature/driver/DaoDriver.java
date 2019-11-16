package com.revature.driver;

import com.revature.daos.UserDao;

public class DaoDriver {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDao userDao = UserDao.currentImplementation;

		System.out.println("find all USERs");
		userDao.findAll().forEach(user -> {
			System.out.println(user);
		});
		//User currentUser = userDao.findByUsername(username);
	}

}

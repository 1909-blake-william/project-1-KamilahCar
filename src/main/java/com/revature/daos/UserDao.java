package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDao {
	UserDao currentImplementation = new UserInDatabase();
	User findByUsernameAndPassword(String userName, String userPass);
	int save(User currentUser);
	User findByUsername(String username);
	List<User> findAll();
	
}

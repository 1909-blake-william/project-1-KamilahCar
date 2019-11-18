package com.revature.driver;

import java.util.List;

import com.revature.daos.ReimbursementDao;
import com.revature.daos.UserDao;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class DaoDriver {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDao userDao = UserDao.currentImplementation;
		ReimbursementDao reimburseDao = ReimbursementDao.currentImplementation;

		System.out.println("find all USERs");
		userDao.findAll().forEach(user -> {
			System.out.println(user);
		});
		
		System.out.println("find all reimbursements");
		reimburseDao.findAll().forEach(reimb -> {
			System.out.println(reimb);
		});
		User currentUser = userDao.findByUsername("KCarlisle");
		System.out.println(currentUser);
		
		System.out.println("find all reimbursements for this user");
		List <Reimbursement> reimbursements = reimburseDao.findByAuthor(22);
		System.out.println(reimbursements);
		
	}

}

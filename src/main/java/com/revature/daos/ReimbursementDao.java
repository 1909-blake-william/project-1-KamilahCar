package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;

//import com.revature.models.User;

public interface ReimbursementDao {

	ReimbursementDao currentImplementation = new ReimbursementInDatabase();

	List<Reimbursement> findAll();
	//Reimbursemnt save();
	int save(Reimbursement newReimbursement);
	int changeStatus(Reimbursement changeStatus);
	
	List <Reimbursement> findByStatus(int statusId);
	//Use for id, type id, status id
	List <Reimbursement> findById(int id, String idName);
	//Reimbursement findByStatusId(int id);
	//Reimbursement findByTypeId(int id);
	List<Reimbursement> findByAuthor(int author);
	int changeAuthor(Reimbursement changeAuthor);
	
	
 
}

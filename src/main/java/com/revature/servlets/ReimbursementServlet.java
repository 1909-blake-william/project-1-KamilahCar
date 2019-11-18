package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.ReimbursementDao;
import com.revature.models.Reimbursement;
import com.revature.models.User;


public class ReimbursementServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ObjectMapper om = new ObjectMapper();
	ReimbursementDao reimburseDao = ReimbursementDao.currentImplementation;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("To context param: " + req.getServletContext().getInitParameter("To"));
		//resp.addHeader("Access-Control-Allow-Origin", "http://localhost:5500");
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		resp.addHeader("Access-Control-Allow-Headers",
				"Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.setContentType("application/json");
		super.service(req, resp);
	}
	@Override
	//Manager can find all reimbursements
	//
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//resp.getWriter().write("Hwllo");
		List<Reimbursement> reimbursements = reimburseDao.findAll();
		String json = om.writeValueAsString(reimbursements);

		resp.addHeader("content-type", "application/json");
		resp.getWriter().write(json);
	}
	@Override
	//When a user submits a request it should be saved
	//
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		ObjectMapper om = new ObjectMapper();
		Reimbursement currentReimbursement = (Reimbursement) om.readValue(req.getReader(), Reimbursement.class);

		System.out.println(currentReimbursement);

		reimburseDao.save(currentReimbursement);

		String json = om.writeValueAsString(currentReimbursement);

		resp.getWriter().write(json);
		resp.setStatus(201); // created status code
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		ObjectMapper om = new ObjectMapper();
		Reimbursement currentReimbursement = (Reimbursement) om.readValue(req.getReader(), Reimbursement.class);

		System.out.println(currentReimbursement);
		User loggedInUser = (User)req.getSession().getAttribute("user");
		
		reimburseDao.findByAuthor(loggedInUser.getId());

		String json = om.writeValueAsString(currentReimbursement);

		resp.getWriter().write(json);
		
	}
}

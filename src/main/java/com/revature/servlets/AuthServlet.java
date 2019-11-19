package com.revature.servlets;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.UserDao;
import com.revature.models.User;

public class AuthServlet extends HttpServlet {

	private UserDao userDao = UserDao.currentImplementation;
	private Logger logger = Logger.getLogger(getClass()); 

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getRequestURL());
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		resp.addHeader("Access-Control-Allow-Headers",
				"Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.setContentType("application/json");
		// TODO Auto-generated method stub
		super.service(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("uri = " + req.getRequestURI());
		ObjectMapper om = new ObjectMapper();
		//change this
		if ("/Project1/login".equals(req.getRequestURI())) {
			User credentials = (User) om.readValue(req.getReader(), User.class);
			logger.info("Object credentials created");
			User loggedInUser = userDao.findByUsernameAndPassword(credentials.getUserName(), credentials.getUserPass());
			logger.info("User object created");
			//Admin login username = KCarlisle
			//password = Admin_123@work
			
			//Getting input from textfields in  html form
			/*String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			User loggedInUser = userDao.findByUsernameAndPassword(username, password);*/
			if (loggedInUser == null) {
				// Unauthorized status code
				resp.setStatus(401); 
				logger.error("Incorrect Login");
				return;
			} else {
				//Successful Status Code
				
				/*req.getSession().setAttribute("user", loggedInUser);*/
				try {
					resp.setStatus(201);
					req.getSession().setAttribute("user", loggedInUser);
					/*HttpSession session = req.getSession();
					session.setAttribute("currentUser", loggedInUser);*/
					resp.getWriter().write(om.writeValueAsString(loggedInUser));
					logger.trace("Tracing");
					resp.sendRedirect("EmployeeHomePage.jsp");					
					/*if (credentials.getRoleId() == 1) {
						resp.sendRedirect("AdminHmePage.jsp");
						return;
					} else {
						resp.sendRedirect("EmployeeHomePage.jsp");
						return;
					}*/
				} catch(IOException e) {
					logger.error("JSON error");
				}
				
				
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//change this
		if ("/Project1/login/session-user".equals(req.getRequestURI())) {
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(req.getSession().getAttribute("user"));
			resp.getWriter().write(json);
		}
	}
}

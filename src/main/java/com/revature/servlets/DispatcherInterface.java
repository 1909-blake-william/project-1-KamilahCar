package com.revature.servlets;

import javax.servlet.http.HttpServlet;

public interface DispatcherInterface {
	Object execute (HttpServlet request, HttpServlet response);
	boolean supportService (HttpServlet request, HttpServlet response);
}

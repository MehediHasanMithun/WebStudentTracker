package com.mhm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String Username = request.getParameter("username");
		String Password = request.getParameter("password");
		
		//System.out.println(Username+" "+Password);
		
		try {
			List<Admin> admins = studentDbUtil.getAdmin();
			for(Admin temp : admins) {
			//	System.out.println(temp.getPassword()+" "+temp.getUserName());
				if(temp.getUserName().equals(Username) && temp.getPassword().equals(Password)) {
					response.sendRedirect("list-students.jsp");
				}
				else {
					out.println("Wrong Username and Password");
				}
			}
			
			
		}catch(Exception exc) {
			System.out.println(exc);
		}
		
	}

}

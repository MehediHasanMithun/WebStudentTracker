package com.mhm.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ViewOnlyServlet
 */
@WebServlet("/ViewOnlyServlet")
public class ViewOnlyServlet extends HttpServlet {
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
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     

		try {
			
			List<Student> students = studentDbUtil.getStudents();
			
			request.setAttribute("Student_list", students);
					
			RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students-with-scriptlets.jsp");
			dispatcher.forward(request, response);
			
		}catch(Exception exc) {
			System.out.println(exc);
		}
		
	}

}

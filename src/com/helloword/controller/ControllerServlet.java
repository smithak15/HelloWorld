package com.helloword.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.helloword.db.*;
import com.helloworld.entity.Report;
import com.helloworld.entity.Users;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doProcess(request,response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//doGet(request, response);
		doProcess(request,response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		String requestUri = request.getRequestURI();
		if(requestUri.equalsIgnoreCase("/HelloWorldAssignment/registrationSubmit.do")){
			registerUser(request,response);
		}else if(requestUri.equalsIgnoreCase("/HelloWorldAssignment/adminReport.do")){
			generateReport(request,response);
		}
	}

	/**
	 * @param request
	 * @param response
	 * This method takes user input and validates it. If the validation failed,
	 * appropriate error messages are loaded and redirected to RegistrationForm.jsp.
	 * If validation succeeds, the confirmation page is displayed.
	 */
	private void registerUser(HttpServletRequest request, HttpServletResponse response) {
		
		try{
			DatabaseConnector dc = new DatabaseConnector();
			dc.getConnection();
			
			String firstName = request.getParameter("fname");
			String lastName = request.getParameter("lname");
			String address1 = request.getParameter("address1");
			String address2 = request.getParameter("address2");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String zipString = request.getParameter("zip");
			int zip = 00000;
			if(null != zipString){ 
				zip = Integer.parseInt(request.getParameter("zip"));
			}
			String country = "US";
			List<String> errMessage = new ArrayList<String>();
			boolean error = false;
			if(null == firstName || firstName.trim().equals("")){
				errMessage.add("First Name is required");
				error = true;
			}
			if(null == lastName || lastName.trim().equals("")){
				errMessage.add("Last Name is required");
				error = true;
			}
			if(null == address1 || address1.trim().equals("")){
				errMessage.add("Address1 is required");
				error = true;
			}
			if(null == address2){
				address2 = "";
			}
			if(null == city || city.trim().equals("")){
				errMessage.add("City is required");
				error = true;
			}
			if(null == state || state.equals("")){
				errMessage.add("State is required");
				error = true;
			}
			if(null == zipString || zipString.equals("")){
				errMessage.add("Zip is required");
				error = true;
			}else if(!(zipString.length() == 5 || zipString.length() == 9)){
				errMessage.add("Zip should have 5 or 9 digits");
				error = true;
			}
			if(error){
				request.setAttribute("errList", errMessage);
				request.getRequestDispatcher("RegistrationForm.jsp").forward(request, response);
			}else{
				java.util.Date today = new java.util.Date();
				java.sql.Timestamp time = new java.sql.Timestamp(today.getTime());
				Users user = new Users(firstName, lastName, address1, address2, city, state, zip, country, time);
				dc.registerUser(user);
				dc.closeConnection();
				request.getRequestDispatcher("Confirmation.jsp").forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @param request
	 * @param response
	 * This method calls the database connector and retreives the user registration list and forwards
	 * it in the request to AdminReport.jsp
	 */
	private void generateReport(HttpServletRequest request, HttpServletResponse response) {
		try{
			DatabaseConnector dc = new DatabaseConnector();
			dc.getConnection();
			List<Report> registrationList = dc.generateReport();
			request.setAttribute("registrationList", registrationList);
			dc.closeConnection();
			request.getRequestDispatcher("AdminReport.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

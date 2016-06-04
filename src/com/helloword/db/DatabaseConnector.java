/**
 * 
 */
package com.helloword.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.helloworld.entity.Report;
import com.helloworld.entity.Users;

/**
 * @author Smitha
 *
 */
public class DatabaseConnector {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	final private String host = "localhost";
	final private String user = "root";
	final private String passwd = "Kitty.123";
	
	/**
	 * @throws Exception
	 */
	public void getConnection() throws Exception{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager
			          .getConnection("jdbc:mysql://" + host + "/HELLOWORLD?"
			              + "user=" + user + "&password=" + passwd );
			System.out.println("Connection success");
		}catch(Exception e){
			System.out.println("Connection failed");
		}
	}
	
	public void closeConnection() throws Exception{
		if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException logOrIgnore) {}
        if (connect != null) try { connect.close(); } catch (SQLException logOrIgnore) {}
        if (resultSet != null) try { resultSet.close(); } catch (SQLException logOrIgnore) {}
	}
	
	public void registerUser(Users user) throws Exception{
		try{
			String sql = "INSERT INTO USERS(FNAME,LNAME,ADDRESS1,ADDRESS2,CITY,STATE,ZIP,REGISTRATION_DATE,COUNTRY) "
						+"VALUES(?,?,?,?,?,?,?,?,?)";
			preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getAddress1());
			preparedStatement.setString(4, user.getAddress2());
			preparedStatement.setString(5, user.getCity());
			preparedStatement.setString(6, user.getState());
			preparedStatement.setInt(7, user.getZip());
			preparedStatement.setTimestamp(8, user.getRegistrationDate());
			preparedStatement.setString(9, user.getCountry());
			preparedStatement.executeUpdate();
		}catch(Exception e){
			//TODO: WRITE EXCEPTION CODE
		}
	}
	
	public List<Report> generateReport() throws Exception{
		List<Report> reportList = new ArrayList<Report>();
		try{
			String sql = "SELECT * FROM USERS ORDER BY REGISTRATION_DATE DESC;";
			preparedStatement = connect.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				
				String registrationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(resultSet.getTimestamp(9));
				System.out.println(registrationDate);
				Report report = new Report(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
						resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getInt(8),resultSet.getString(10),registrationDate);
				reportList.add(report);
			}
		}catch(Exception e){
			//TODO: WRITE EXCEPTION CODE
		}
		return reportList;
	}
}

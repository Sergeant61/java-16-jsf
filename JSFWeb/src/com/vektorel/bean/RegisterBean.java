package com.vektorel.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.vektorel.db.DatabaseConnection;

@ManagedBean
@SessionScoped
public class RegisterBean implements Serializable {

	private static final long serialVersionUID = 1463121656479400876L;

	private String name;
	private String surname;
	private Date birtDate;
	private String userName;
	private String email;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirtDate() {
		return birtDate;
	}

	public void setBirtDate(Date birtDate) {
		this.birtDate = birtDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String register() {
		String name = getName();
		String surname = getSurname();
		Date birtDate = getBirtDate();
		String userName = getUserName();
		String password = getPassword();
		String email = getEmail();
		
		String SQL = "Insert into users (name, surname, email, birthdate, username, password, created_date)"
				+ " values (?, ?, ?, ?, ?, ?, ?)";
		
		DatabaseConnection databaseConnection = new DatabaseConnection();
		
		try (Connection connection = databaseConnection.getConnection()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
				
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, surname);
				preparedStatement.setString(3, email);
				java.sql.Date sqlDate = new java.sql.Date(birtDate.getTime());
				preparedStatement.setDate(4, sqlDate);
				preparedStatement.setString(5, userName);
				preparedStatement.setString(6, password);
				preparedStatement.setDate(7, new java.sql.Date(new Date().getTime()));
				
				preparedStatement.executeUpdate();
				
				return "login";
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}

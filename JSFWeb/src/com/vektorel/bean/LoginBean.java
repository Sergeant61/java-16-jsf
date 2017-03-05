package com.vektorel.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import com.vektorel.db.DatabaseConnection;

@ManagedBean(name="login")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -4832965450025495052L;
	
	private String userName;
	private String password;
	
	private boolean isLoggedIn = false;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String login() {
		
		String SQL = "Select password from users where username = ?";
		
		DatabaseConnection databaseConnection = new DatabaseConnection();
		try (Connection connection = databaseConnection.getConnection()) {
			
			try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
				
				preparedStatement.setString(1, getUserName());
				
				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					if (resultSet.next()) {
						String password = resultSet.getString("password");
						if (password.equals(getPassword())) {
							isLoggedIn = true;
							return "index";
						} else {
							System.out.println("Þifre eþleþmedi!");
							return null;
						}
					} else {
						System.out.println(getUserName() + " isimli bir kullanýcý yok!");
						return null;
					}
				}
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void isLoggedIn(ComponentSystemEvent event) {

		FacesContext fc = FacesContext.getCurrentInstance();

		if (!isLoggedIn) {

			ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();

			nav.performNavigation("login.xhtml");
		}
	}

}

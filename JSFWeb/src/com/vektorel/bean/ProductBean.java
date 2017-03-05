package com.vektorel.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.vektorel.db.DatabaseConnection;
import com.vektorel.entity.ProductEntity;

@ManagedBean
@SessionScoped
public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1803604643150459147L;
	
	private List<ProductEntity> products;
	
	@PostConstruct
	public void init() {
		
		String SQL = "SELECT p.id, p.name, p.description, p.price, p.category_id, pg.name as category_name FROM product p, product_category pg WHERE p.category_id = pg.id";
		
		DatabaseConnection databaseConnection = new DatabaseConnection();
		try (Connection connection = databaseConnection.getConnection()) {
			
			try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
				
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					
					products = new ArrayList<>();
					
					while (resultSet.next()) {
						
						ProductEntity productEntity = new ProductEntity(
								resultSet.getInt("id"), 
								resultSet.getInt("category_id"),
								resultSet.getString("category_name"),
								resultSet.getString("name"),
								resultSet.getFloat("price"),
								resultSet.getString("description"));
						
						products.add(productEntity);
						
					}
					
				}
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<ProductEntity> getProducts() {
		return products;
	}
	
	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}

}

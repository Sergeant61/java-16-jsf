package com.vektorel.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.vektorel.entity.ProductEntity;

@ManagedBean
@SessionScoped
public class OrderBean implements Serializable {

	private static final long serialVersionUID = -639477974845660842L;
	
	private List<ProductEntity> cartList;
	private int productCount;
	
	@PostConstruct
	public void init() {
		cartList = new ArrayList<>();
	}
	
	public int getProductCount() {
		productCount = cartList.size();
		return productCount;
	}
	
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	
	public List<ProductEntity> getCartList() {
		return cartList;
	}
	
	public void setCartList(List<ProductEntity> cartList) {
		this.cartList = cartList;
	}
	
	public void addCart(ProductEntity productEntity) {
		cartList.add(productEntity);
		productCount++;
	}

}

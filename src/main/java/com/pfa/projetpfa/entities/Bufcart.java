package com.pfa.projetpfa.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Bufcart implements Serializable {

	private static final long serialVersionUID = 4049687597028261161L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bufcartId;

	@Column(nullable = true)
	private int orderId;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "Bufcart [bufcartId=" + bufcartId + ", orderId=" + orderId + ", email=" + email + ", dateAdded="
				+ dateAdded + ", quantity=" + quantity + ", price=" + price + ", productId=" + productId
				+ ", productname=" + productname + "]";
	}

	private String email;

	private Date dateAdded;

	private int quantity;
	private double price;
	private String productId;

	private String productname;

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getBufcartId() {
		return bufcartId;
	}

	public void setBufcartId(int bufcartId) {
		this.bufcartId = bufcartId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}

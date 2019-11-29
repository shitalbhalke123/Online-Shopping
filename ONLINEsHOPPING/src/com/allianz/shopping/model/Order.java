package com.allianz.shopping.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order
{
	private int order_id;
	private  Date date;
	private String oredr_status;
	private String UserName;
	private float price;
	private List<AddToCart> addToCartList=new ArrayList<AddToCart>();
	Customer customer=new Customer();
	
	public List<AddToCart> getAddToCartList() {
		return addToCartList;
	}
	public void setAddToCartList(List<AddToCart> addToCartList) {
		this.addToCartList = addToCartList;
	}
	public Order()
	{
		this.order_id = 0;
		this.date = null;
		this.oredr_status = null;
		UserName =  null;
		this.price = 0.0f;
	}
	public Order(int order_id, Date date, String oredr_status, String userName, float price) {
		super();
		this.order_id = order_id;
		this.date = date;
		this.oredr_status = oredr_status;
		UserName = userName;
		this.price = price;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getOredr_status() {
		return oredr_status;
	}
	public void setOredr_status(String oredr_status) {
		this.oredr_status = oredr_status;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", Date=" + date + ", oredr_status=" + oredr_status + ", UserName="
				+ UserName + ", price=" + price + "]";
	}
	
	
}

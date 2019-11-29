package com.allianz.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.allianz.shopping.model.Order;
import com.allianz.shopping.model.Product;
import com.allianz.shopping.utility.DBUtilityConnection;
import com.allianz.shopping.utility.DateUtility;

public class OrderDAOImpl implements OrderDAO
{
	AddToCartDAO addtocart=new AddToCartDAOimpl();

	@Override
	public int addOrder(Order o) {
		int orderID = 0;
		Connection con=DBUtilityConnection.getConnection();
		String sql;
		
		try
		{
			sql="INSERT INTO orders(Order_Date,Status,Username,Total_Price) values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			ps.setDate(1, DateUtility.convertUtilDateToSQLDate(o.getDate()));
			ps.setString(2,o.getOredr_status());
			ps.setString(3, o.getUserName());
			ps.setFloat(4, o.getPrice());
			int no=ps.executeUpdate();
			if(no>0)

			{
				try(ResultSet generateKeys=ps.getGeneratedKeys())
				{
					if(generateKeys.next())
					{
						orderID=generateKeys.getInt(1);
						System.out.println(orderID);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return orderID;
	}

	@Override
	public Order getOrderbyOrderId(int order_id) {
		Connection connection=null;	
		try
		{	
			
			connection=DBUtilityConnection.getConnection();
			String sql="SELECT * from orders WHERE Order_ID=?";
			
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setInt(1,order_id);
			
			ResultSet rs=ps.executeQuery();
			
			
			if(rs.next()) {
				Order order=new Order();
				order.setOrder_id(rs.getInt(1));
				order.setDate(rs.getDate(2));
				order.setOredr_status(rs.getString(3));
				order.setUserName(rs.getString(4));
				order.setPrice(rs.getFloat(5));
				order.setAddToCartList(addtocart.getAddToCartByOrderId(order.getOrder_id()));
				return order;
			
			}
		}
		catch(Exception s)
		{
			s.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Order> getAllOrder() {
		List<Order> orderlist=new ArrayList<Order>();
		Connection connection=null;	
		try
		{	
			
			connection=DBUtilityConnection.getConnection();
			String sql="SELECT * from orders";
			
			PreparedStatement ps=connection.prepareStatement(sql);
			
			
			ResultSet rs=ps.executeQuery();
			
			
			while(rs.next()) {
				Order order=new Order();
				order.setOrder_id(rs.getInt(1));
				order.setDate(rs.getDate(2));
				order.setOredr_status(rs.getString(3));
				order.setUserName(rs.getString(4));
				order.setPrice(rs.getFloat(5));
				order.setAddToCartList(addtocart.getAddToCartByOrderId(order.getOrder_id()));
				orderlist.add(order) ;
			
			}
		}
		catch(Exception s)
		{
			s.printStackTrace();
		}
		
		return orderlist;
	}

}

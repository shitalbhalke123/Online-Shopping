package com.allianz.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.allianz.shopping.model.AddToCart;
import com.allianz.shopping.model.Category;
import com.allianz.shopping.utility.DBUtilityConnection;

public class AddToCartDAOimpl implements AddToCartDAO  
{
	@Override
	public boolean addToCart(AddToCart add)
	{
		Connection con=DBUtilityConnection.getConnection();
		String sql;
		try
		{
			sql="INSERT INTO add_to_cart(Order_ID,productID,quantity,Total_price) values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			
			ps.setInt(1, add.getOrder_id());
			ps.setInt(2,add.getProduct_id());
			ps.setInt(3, add.getQuantity());
			ps.setFloat(4,add.getTotal());
			int no=ps.executeUpdate();
			if(no>0)
				return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<AddToCart> getAddToCartByOrderId(int order_id) {
		
		
		  List<AddToCart> addToCartlist=new ArrayList<AddToCart>(); Connection
		  connection=null; try {
		  
		  connection=DBUtilityConnection.getConnection(); String
		  sql="SELECT * FROM add_to_cart where Order_ID=?";
		  PreparedStatement ps=connection.prepareStatement(sql);
		  ps.setInt(1,order_id);	  
		  ResultSet rs=ps.executeQuery();
		  while(rs.next())
		  { 
			AddToCart addToCart=new AddToCart();
			addToCart.setAdd_To_Cart_id(rs.getInt(1));
			addToCart.setOrder_id(rs.getInt(2));
			addToCart.setProduct_id(rs.getInt(3));
			addToCart.setQuantity(rs.getInt(4));
			addToCart.setTotal(rs.getFloat(5));
			addToCartlist.add(addToCart);
		  }
		  return addToCartlist;
		  } 
		  catch(Exception s) { s.printStackTrace(); }
		  
		  return null;
		 
	}
	

}

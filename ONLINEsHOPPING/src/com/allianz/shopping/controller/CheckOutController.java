package com.allianz.shopping.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allianz.shopping.dao.AddToCartDAO;
import com.allianz.shopping.dao.AddToCartDAOimpl;
import com.allianz.shopping.dao.OrderDAO;
import com.allianz.shopping.dao.OrderDAOImpl;
import com.allianz.shopping.model.AddToCart;
import com.allianz.shopping.model.Order;
import com.allianz.shopping.model.Product;
import com.allianz.shopping.utility.DateUtility;
import com.sun.istack.internal.Pool.Impl;

@WebServlet("/CheckOutController")
public class CheckOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CheckOutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//System.out.println("Helllo");
		OrderDAO orderdao=new OrderDAOImpl();
		AddToCartDAO addDao=new AddToCartDAOimpl();
			
		
		String username=(String)request.getSession().getAttribute("username");
				
		String product_id[]=request.getParameterValues("product_id");
		
		if(product_id!=null && product_id.length>0)
		{
			
			float grantTotal=0;
			for(int i=0;i<product_id.length;i++)
			{
				String total=request.getParameter("total"+product_id[i]);
				grantTotal=grantTotal+ Float.parseFloat(total);
			}
			Order order=new Order();
			order.setDate(new Date());
			order.setOredr_status("Ordered");
			order.setUserName(username);
			order.setPrice(grantTotal);
			int order_id=orderdao.addOrder(order);
			
			
			
			for(int i=0;i<product_id.length;i++)
			{
				System.out.println("Helllo");
				if(product_id[i]!=null && !product_id[i].trim().equals(""))
				{
					String quantity=request.getParameter("quantity"+product_id[i]);
					System.out.println(Integer.parseInt(quantity));
					String total=request.getParameter("total"+product_id[i]);
					System.out.println(total);
					if(quantity!=null)
					{
						
						AddToCart addToCart=new AddToCart();
						addToCart.setOrder_id(order_id);
						addToCart.setQuantity(Integer.parseInt(quantity));
						addToCart.setProduct_id(Integer.parseInt(product_id[i]));
						addToCart.setTotal(Float.parseFloat(total));
						addDao.addToCart(addToCart);
					}
				}
			}
			//OrderDAO orderdao1=new OrderDAOImpl();
			List<Order> orderlist=orderdao.getAllOrder();
			request.setAttribute("orderlist", orderlist);
			request.getRequestDispatcher("order.jsp").forward(request, response);
		}
	}

}

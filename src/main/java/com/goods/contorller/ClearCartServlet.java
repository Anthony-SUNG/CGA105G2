package com.goods.contorller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goods.model.Cart.pojo.Cart;
import com.goods.model.Cart.dao.impl.CartDetailRedis;
import com.order.model.service.OrderService;

@WebServlet("/cart/clear")
public class ClearCartServlet extends HttpServlet{
	 private final OrderService ordersService = new OrderService();

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doPost(request, response);
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	        response.setCharacterEncoding("UTF-8");
	        String userId = String.valueOf(request.getSession().getAttribute("memId"));
	        CartDetailRedis cartSvc = new CartDetailRedis();
	        Cart cart = cartSvc.get(userId);
	        ordersService.addOrder(cart);
	        cartSvc.clear(userId);
	        request.getRequestDispatcher("/front-end/Member/goods/Member_order_listAll.jsp").forward(request, response);
	    }
}

package com.order.contorller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodorder.model.service.FoodorderService;
import com.goods.model.Cart.pojo.Cart;
import com.goods.model.Goods.dao.impl.GoodsJDBCDAO;
import com.goods.model.Goods.pojo.Goods;
import com.goods.model.Cart.dao.impl.CartDetailRedis;
import com.goods.model.service.GoodsService;
import com.order.model.Order.dao.impl.OrderJDBCDAO;
import com.order.model.Order.pojo.Order;
import com.order.model.OrderDetail.pojo.OrderDetail;
import com.order.model.service.OrderService;

@WebServlet("/order/order.do")
@MultipartConfig
public class OrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Integer storeId = (Integer) req.getSession().getAttribute("storeId");
		Integer memId   = (Integer) req.getSession().getAttribute("memId");
		String action = req.getParameter("action");
		if ("getMemId_For_Display_Order".equals(action)) {
		 	req.setAttribute("memId", memId);
			String url = "/front-end/Member/order/Member_order_listAll.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("checkout".equals(action)) { // 來自Member_cart.jsp的請求將redis取的值轉交checkout
			String[] goodsId = req.getParameterValues("goodsId");
			String[] goodsPrice = req.getParameterValues("goodsPrice");
			String[] detailQuantity = req.getParameterValues("detailQuantity");
			String[] goodsName = req.getParameterValues("goodsName");
			int[] goodsIdInt = new int[goodsId.length];
			int[] goodsPriceInt = new int[goodsId.length];
			int[] detailQuantityInt = new int[goodsId.length];
			GoodsService goodsSvc;
			Goods goods = null;
			List<Goods> goodsList = new LinkedList<>();
			for (int i = 0; i < goodsIdInt.length; i++) {
				goodsIdInt[i] = Integer.parseInt(goodsId[i]);
				goodsPriceInt[i] = Integer.parseInt(goodsPrice[i]);
				detailQuantityInt[i] = Integer.parseInt(detailQuantity[i]);
				goodsSvc = new GoodsService();
				goods = goodsSvc.getOneGoods(goodsIdInt[i]);
				goodsList.add(goods);
			}
			//*************************** 傳值至 Member_order_checkout.jsp *************/
			if (goods != null) {
				req.setAttribute("storeId", storeId);
				req.setAttribute("goodsList", goodsList);
				req.setAttribute("goodsIdInt", goodsIdInt);
				req.setAttribute("goodsPriceInt", goodsPriceInt);
				req.setAttribute("goodsName", goodsName);
				req.setAttribute("detailQuantityInt", detailQuantityInt);
				req.setAttribute("memId", memId);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/Member/order/Member_order_checkout.jsp");
				failureView.forward(req, res);
			}
			return;// 程式中斷
		}
		if ("orderSuccess".equals(action)) {
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			//*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			List<String> goodList= List.of(req.getParameterValues("goodsId"));
			if (goodList.isEmpty()){
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/Member/goods/Member_cart.jsp");
				failureView.forward(req, res);
				return;
			}
			//*************************** 2.開始新增資料 ***************************************/
			List<String> qList= List.of(req.getParameterValues("detailQuantity"));
			Map<Integer,List<OrderDetail>> orderDetailMap = new LinkedHashMap<>();
			List<String> stroeIdList=new ArrayList<>();
			OrderService service=new OrderService();
			service.toReFish(goodList,qList,orderDetailMap,stroeIdList);
			stroeIdList = new ArrayList<>(new LinkedHashSet<>(stroeIdList));
			service.insertWithDetail(stroeIdList,orderDetailMap,memId);
			//*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front-end/Member/order/Member_order_listAll.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllGoods.jsp
			successView.forward(req, res);
		}
	}
}

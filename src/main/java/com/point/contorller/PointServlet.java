package com.point.contorller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.point.model.PointGoods.pojo.PointGoods;
import com.point.model.service.PointGoodsService;

public class PointServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		Integer storeId = (Integer) req.getSession().getAttribute("storeId");
		Integer memId   = (Integer) req.getSession().getAttribute("memId");
		Integer empId   = (Integer) req.getSession().getAttribute("empId");

		if ("insert".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String pdName = req.getParameter("pdName");
			String pdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
			if (pdName == null || pdName.trim().length() == 0) {
				errorMsgs.put("pdName", "商品名稱請勿空白");
			} else if (!pdName.trim().matches(pdNameReg)) {
				errorMsgs.put("pdName", "員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
			}

			Integer pdPrice = null;
			try {
				pdPrice = Integer.valueOf(req.getParameter("pdPrice").trim());
			} catch (NumberFormatException e) {
				pdPrice = 0;
				errorMsgs.put("pdPrice", "商品單價請填數字");
			}

			String pdText = req.getParameter("pdText").trim();
			if (pdText == null || pdText.trim().length() == 0) {
				errorMsgs.put("pdText", "商品介紹請勿空白");
			}

			Integer pdStatus = Integer.valueOf(req.getParameter("pdStatus").trim());

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pointgood/addPointGood.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			PointGoodsService pointGoodsSvc = new PointGoodsService();
			pointGoodsSvc.addPointGood(pdName, pdPrice, pdText, null, null, pdStatus);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/pointgood/listPointGood.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer pdId = Integer.valueOf(req.getParameter("pdId").trim());

			/*************************** 2.開始查詢資料 ****************************************/
			PointGoodsService pointGoodsSvc = new PointGoodsService();
			PointGoods pointgoods = pointGoodsSvc.getPointGood(pdId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			String param = "?pdId=" + pointgoods.getPdId() + "&pdName=" + pointgoods.getPdName() + "&pdPrice="
					+ pointgoods.getPdPrice() + "&pdText=" + pointgoods.getPdText() + "&pdStatus="
					+ pointgoods.getPdStatus();
			String url = "/back-end/pointgood/updPointGood.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer pdId = Integer.valueOf(req.getParameter("pdId").trim());
			String pdName = req.getParameter("pdName");
			String pdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
			if (pdName == null || pdName.trim().length() == 0) {
				errorMsgs.put("pdName", "商品名稱請勿空白");
			} else if (!pdName.trim().matches(pdNameReg)) {
				errorMsgs.put("pdName", "員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
			}

			Integer pdPrice = null;
			try {
				pdPrice = Integer.valueOf(req.getParameter("pdPrice").trim());
			} catch (NumberFormatException e) {
				pdPrice = 0;
				errorMsgs.put("pdPrice", "商品單價請填數字");
			}

			String pdText = req.getParameter("pdText").trim();
			if (pdText == null || pdText.trim().length() == 0) {
				errorMsgs.put("pdText", "商品介紹請勿空白");
			}

			Integer pdStatus = Integer.valueOf(req.getParameter("pdStatus").trim());

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pointgood/updPointGood.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 ***************************************/
			PointGoodsService pointGoodsSvc = new PointGoodsService();
			PointGoods pointgoods = pointGoodsSvc.updatePointGood(pdId, pdName, pdPrice, pdText, null, pdStatus);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("pointgoods", pointgoods);
			String url = "/back-end/pointgood/listPointGood.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
}

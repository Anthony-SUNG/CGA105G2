package com.foodorder.contorller;



import com.foodorder.model.Meal.pojo.Meal;
import com.foodorder.model.Reserva.pojo.Reserva;
import com.foodorder.model.service.FoodorderService;
import com.store.model.Store.pojo.Store;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@WebServlet(urlPatterns= {"/front-end/store/food_order/food_order.do","/front-end/Member/food_order/food_order.do"})
public class FoodorderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		Integer storeId = (Integer) req.getSession().getAttribute("storeId");
		Integer memId   = (Integer) req.getSession().getAttribute("memId");
		Integer empId   = (Integer) req.getSession().getAttribute("empId");

		if ("food_order_button".equals(action)) {
			// 取得店家id，這邊照理來說應該要在session可以取到，因為最前面進入店家
			// 現在先模擬自己set進去 再get出來 之後前面頁面有set過 *這邊set要移除*
			HttpSession session = req.getSession();
			session.setAttribute("storeId", 6);
			Integer storeid = (Integer) session.getAttribute("storeId");

			// 利用service取資料
			// 1.清單資料
			FoodorderService foodorderSvc = new FoodorderService();
			List<Meal> list = foodorderSvc.getAllStatusOk(storeid);
			// 2.訂位設定資料
			// 盧逸臣dao
			Store storeInfo = foodorderSvc.getStoreInfo(storeid);
			String timestr = "未設定";
			Integer tablecount = 0;
			Integer orderlimit = 0;
			if (storeInfo.getStoreEtime() != null) {
				timestr = storeInfo.getStoreEtime();
			}
			if (storeInfo.getStoreTable() != null) {
				tablecount = storeInfo.getStoreTable();
			}
			if (storeInfo.getStoreEtable() != null) {
				orderlimit = storeInfo.getStoreEtable();
			}
			// 測試json
//		    Meal meal1 = new Meal(2,2);
//		    Meal meal2 = new Meal(3,3);
//		    List<Meal> list2 = new ArrayList<Meal>();
//		    list2.add(meal1);
//		    list2.add(meal2);
//		    String jsonString = JSON.toJSONString(list2);
			// 轉交資料
			req.setAttribute("list", list); // 資料庫取出的list物件,存入req
			req.setAttribute("timestr", timestr);// 尚未寫邏輯
			req.setAttribute("tablecount", tablecount);// 尚未寫邏輯
			req.setAttribute("orderlimit", orderlimit);// 尚未寫邏輯
//			req.setAttribute("testjsonstring", jsonString);// 測試json字串到前面
			// Send the Success view
			String url = "/front-end/store/food_order/updateFoodOrderInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交listAllEmp2_getFromSession.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer mealId = Integer.valueOf(req.getParameter("mealId").trim());
			/*************************** 2.開始查詢資料 ****************************************/
			FoodorderService foodorderSvc = new FoodorderService();
			Meal meal = foodorderSvc.getOneMeal(mealId);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("mealVO", meal); // 資料庫取出的empVO物件,存入req
			String url = "/front-end/store/food_order/updateFoodOrderByFood.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			System.out.println("進入");
			List<String> errorMsgs = new LinkedList<String>();
			String errorMealNameMsgs = "";
			String errorMealPriceMsgs = "";
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer mealId = Integer.valueOf(req.getParameter("mealId").trim());

			String mealName = req.getParameter("mealName");
			System.out.println(mealName.trim().length());
			System.out.println(mealName);
			String mealnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (mealName == null || mealName.trim().length() == 0) {
//				errorMsgs.add("餐點名稱: 請勿空白");
				errorMealNameMsgs = errorMealNameMsgs + "請勿空白!";
			} else if (!mealName.trim().matches(mealnameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("餐點名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//				System.out.println(mealName);
				errorMealNameMsgs = errorMealNameMsgs + "只能是中、英文字母、數字和_ , 且長度必需在2到10之間!";
			}

			String mealPrice = req.getParameter("mealPrice").trim();

			if (mealPrice == null || mealPrice.trim().length() == 0) {
//				errorMsgs.add("金額請勿空白");
				errorMealPriceMsgs = errorMealPriceMsgs + "金額請勿空白!";
			}
			try {
				if (Integer.parseInt(mealPrice) <= 0) {
//					errorMsgs.add("金額請填大於0的整數");
					errorMealPriceMsgs = errorMealPriceMsgs + "金額請填大於0的整數!";
				}
			} catch (NumberFormatException e) {
//				errorMsgs.add("金額請填整數");
				errorMealPriceMsgs = errorMealPriceMsgs + "金額請填整數!";
			}
//			if (!errorMsgs.isEmpty()) {
			if (errorMealNameMsgs != "" || errorMealPriceMsgs != "") {
				req.setAttribute("errorMealNameMsgs", errorMealNameMsgs);
				req.setAttribute("errorMealPriceMsgs", errorMealPriceMsgs);
//				System.out.println(req.getAttribute("errorMealPriceMsgs"));
				// 撈出該id資料再回傳 因為輸入錯誤
				FoodorderService foodorderSvc = new FoodorderService();
				Meal oldmeal = foodorderSvc.getOneMeal(mealId);
				req.setAttribute("mealVO", oldmeal);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/store/food_order/updateFoodOrderByFood.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			// 利用service
			FoodorderService foodorderSvc = new FoodorderService();
			// 取得店家id，這邊照理來說應該要在session可以取到，因為最前面進入店家
			HttpSession session = req.getSession();
			Integer storeid = (Integer) session.getAttribute("storeId");
			// 整理新的vo
			Meal meal = new Meal(mealId, storeid, mealName, Integer.parseInt(mealPrice), 1);
			Meal oldmeal = foodorderSvc.getOneMeal(mealId);
			// 資料處理
			// 新的不等於舊的
			System.out.println(meal);
			System.out.println(oldmeal);
			if (!meal.equals(oldmeal)) {
				meal = new Meal(storeid, mealName, Integer.parseInt(mealPrice), 1);
				foodorderSvc.updateMeal(mealId, meal);
			}
			// 把要跳轉的的資料也要準備好 不然都沒有資料
			List<Meal> list = foodorderSvc.getAllStatusOk(storeid);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("list", list); // 資料庫update成功後,正確的的empVO物件,存入req
			// 2.訂位設定資料
			// 盧逸臣dao
			Store storeInfo = foodorderSvc.getStoreInfo(storeid);
			String timestr = "未設定";
			Integer tablecount = 0;
			Integer orderlimit = 0;
			if (storeInfo.getStoreEtime() != null) {
				timestr = storeInfo.getStoreEtime();
			}
			if (storeInfo.getStoreTable() != null) {
				tablecount = storeInfo.getStoreTable();
			}
			if (storeInfo.getStoreEtable() != null) {
				orderlimit = storeInfo.getStoreEtable();
			}
			req.setAttribute("timestr", timestr);// 尚未寫邏輯
			req.setAttribute("tablecount", tablecount);// 尚未寫邏輯
			req.setAttribute("orderlimit", orderlimit);// 尚未寫邏輯
			String url = "/front-end/store/food_order/updateFoodOrderInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer mealid = Integer.valueOf(req.getParameter("mealId"));

			/*************************** 2.開始刪除資料 ***************************************/
			// 取得店家id，這邊照理來說應該要在session可以取到，因為最前面進入店家
			HttpSession session = req.getSession();
			Integer storeid = (Integer) session.getAttribute("storeId");
			
			FoodorderService foodorderSvc = new FoodorderService();
			foodorderSvc.deleteMeal(mealid);
			// 把要跳轉的的資料也要準備好 不然都沒有資料
			List<Meal> list = foodorderSvc.getAllStatusOk(storeid);
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("list", list); // 資料庫update成功後,正確的的empVO物件,存入req
			// 2.訂位設定資料
			// 盧逸臣dao
			Store storeInfo = foodorderSvc.getStoreInfo(storeid);
			String timestr = "未設定";
			Integer tablecount = 0;
			Integer orderlimit = 0;
			if (storeInfo.getStoreEtime() != null) {
				timestr = storeInfo.getStoreEtime();
			}
			if (storeInfo.getStoreTable() != null) {
				tablecount = storeInfo.getStoreTable();
			}
			if (storeInfo.getStoreEtable() != null) {
				orderlimit = storeInfo.getStoreEtable();
			}
			req.setAttribute("timestr", timestr);// 尚未寫邏輯
			req.setAttribute("tablecount", tablecount);// 尚未寫邏輯
			req.setAttribute("orderlimit", orderlimit);// 尚未寫邏輯
			String url = "/front-end/store/food_order/updateFoodOrderInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自update_emp_input.jsp的請求

//			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
			String errorMealNameMsgs = "";
			String errorMealPriceMsgs = "";
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String mealName = req.getParameter("mealName");
			String mealnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (mealName == null || mealName.trim().length() == 0) {
//				errorMsgs.add("餐點名稱: 請勿空白");
				errorMealNameMsgs = errorMealNameMsgs + "請勿空白!";
			} else if (!mealName.trim().matches(mealnameReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("餐點名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				errorMealNameMsgs = errorMealNameMsgs + "只能是中、英文字母、數字和_ , 且長度必需在2到10之間!";
			}

			String mealPrice = req.getParameter("mealPrice").trim();

			if (mealPrice == null || mealPrice.trim().length() == 0) {
//				errorMsgs.add("金額請勿空白");
				errorMealPriceMsgs = errorMealPriceMsgs + "金額請勿空白!";
				Integer price = 0;
			}
			Integer price = null;
			try {
				if (Integer.parseInt(mealPrice) <= 0) {
//					errorMsgs.add("金額請填大於0的整數");
					errorMealPriceMsgs = errorMealPriceMsgs + "金額請填大於0的整數!";
					price = 0;
				} else {
					price = Integer.parseInt(mealPrice);
				}
			} catch (NumberFormatException e) {
//				errorMsgs.add("金額請填整數");
				errorMealPriceMsgs = errorMealPriceMsgs + "金額請填整數!";
				price = 0;
			}

			if (errorMealNameMsgs != "" || errorMealPriceMsgs != "") {
//			if (!errorMsgs.isEmpty()) {
				req.setAttribute("errorMealNameMsgs", errorMealNameMsgs);
				req.setAttribute("errorMealPriceMsgs", errorMealPriceMsgs);
				// 因為輸入錯誤 old資料往回傳
				Meal oldmeal = new Meal();
				oldmeal.setMealName(mealName);
				oldmeal.setMealPrice(price);
				req.setAttribute("mealVO", oldmeal);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/store/food_order/addFoodOrderByFood.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			// 利用service
			FoodorderService foodorderSvc = new FoodorderService();
			// 取得店家id，這邊照理來說應該要在session可以取到，因為最前面進入店家
			HttpSession session = req.getSession();
			Integer storeid = (Integer) session.getAttribute("storeId");
			// 整理新的vo
			Meal meal = new Meal();
			meal.setStoreId(storeid);
			meal.setMealName(mealName);
			meal.setMealPrice(Integer.parseInt(mealPrice));
			meal.setMealStatus(1);
			// 資料處理
			foodorderSvc.insertMeal(meal);
			// 把要跳轉的的資料也要準備好 不然都沒有資料
			List<Meal> list = foodorderSvc.getAllStatusOk(storeid);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("list", list); // 資料庫update成功後,正確的的empVO物件,存入req
			// 2.訂位設定資料
			// 盧逸臣dao
			Store storeInfo = foodorderSvc.getStoreInfo(storeid);
			String timestr = "未設定";
			Integer tablecount = 0;
			Integer orderlimit = 0;
			if (storeInfo.getStoreEtime() != null) {
				timestr = storeInfo.getStoreEtime();
			}
			if (storeInfo.getStoreTable() != null) {
				tablecount = storeInfo.getStoreTable();
			}
			if (storeInfo.getStoreEtable() != null) {
				orderlimit = storeInfo.getStoreEtable();
			}
			req.setAttribute("timestr", timestr);
			req.setAttribute("tablecount", tablecount);
			req.setAttribute("orderlimit", orderlimit);
			String url = "/front-end/store/food_order/updateFoodOrderInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("updateStoreSetting".equals(action)) {
			// 取得當前店家id
			HttpSession session = req.getSession();
			Integer storeid = (Integer) session.getAttribute("storeId");

			/*************************** 2.開始修改資料 *****************************************/
			// 利用service
			FoodorderService foodorderSvc = new FoodorderService();
			Store storeInfo = foodorderSvc.getStoreInfo(storeid);
			String timestr = "未設定";
			Integer tablecount = 0;
			Integer orderlimit = 0;
			ArrayList timestrArrayList = new ArrayList();
			if (storeInfo.getStoreEtime() != null) {
				timestr = storeInfo.getStoreEtime();
				String[] timestrSplit = timestr.split(", ");
				// 整理時間list
				for (int i = 0; i < timestrSplit.length; i++) {
					String[] timestrSplitSplit = timestrSplit[i].split(":");
					timestrArrayList.add(timestrSplitSplit[0]);
					System.out.println(timestrSplit[i]);
					System.out.println(timestrSplitSplit[0]);
				}
			}

			if (storeInfo.getStoreTable() != null) {
				tablecount = storeInfo.getStoreTable();
			}
			if (storeInfo.getStoreEtable() != null) {
				orderlimit = storeInfo.getStoreEtable();
			}
			req.setAttribute("timestrArrayList", timestrArrayList);
			req.setAttribute("tablecount", tablecount);
			req.setAttribute("orderlimit", orderlimit);
			String url = "/front-end/store/food_order/setFoodOrderInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

		}

		if ("updateStoreSettingButton".equals(action)) {
			String timeSelect1 = req.getParameter("timeSelect1");
			String timeSelect2 = req.getParameter("timeSelect2");
			String timeSelect3 = req.getParameter("timeSelect3");
			String timeSelect4 = req.getParameter("timeSelect4");
			String tablecount = req.getParameter("tablecount");
			String orderlimit = req.getParameter("orderlimit");
			// 取得當前店家id
			HttpSession session = req.getSession();
			Integer storeid = (Integer) session.getAttribute("storeId");
			String errortimeMsgs = "";
			String errortablecountMsgs = "";
			String errororderlimitMsgs = "";
			String temp = timeSelect1 + timeSelect2 + timeSelect3 + timeSelect4;
			if ("0000".equals(temp)) {
				errortimeMsgs = errortimeMsgs + "請至少設定一個時段!";
			}
			ArrayList timestrArrayList = new ArrayList();
			timestrArrayList.add(timeSelect1);
			timestrArrayList.add(timeSelect2);
			timestrArrayList.add(timeSelect3);
			timestrArrayList.add(timeSelect4);

			Integer tablecountTemp = null;
			try {
				if (Integer.parseInt(tablecount) <= 0) {
					errortablecountMsgs = errortablecountMsgs + "總桌數請填大於0的整數!";
					tablecountTemp = 0;
				} else {
					tablecountTemp = Integer.parseInt(tablecount);
				}
			} catch (NumberFormatException e) {
				errortablecountMsgs = errortablecountMsgs + "總桌數請填整數!";
				tablecountTemp = 0;
			}

			Integer orderlimitTemp = null;
			try {
				if (Integer.parseInt(orderlimit) <= 0) {
					errororderlimitMsgs = errororderlimitMsgs + "訂位數請填大於0的整數!";
					orderlimitTemp = 0;
				} else {
					orderlimitTemp = Integer.parseInt(orderlimit);
				}
			} catch (NumberFormatException e) {
				errororderlimitMsgs = errororderlimitMsgs + "訂位數請填整數!";
				orderlimitTemp = 0;
			}
			System.out.println(errortimeMsgs);
			if (errortablecountMsgs != "" || errororderlimitMsgs != "" || errortimeMsgs != "") {
				req.setAttribute("errortablecountMsgs", errortablecountMsgs);
				req.setAttribute("errororderlimitMsgs", errororderlimitMsgs);
				req.setAttribute("errortimeMsgs", errortimeMsgs);
				System.out.println(errortimeMsgs);
				// 因為輸入錯誤 old資料往回傳
				req.setAttribute("tablecount", tablecount);
				req.setAttribute("orderlimit", orderlimit);
				req.setAttribute("timestrArrayList", timestrArrayList);
				req.setAttribute("isShowinfo", 0);
				System.out.println(errortimeMsgs);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/store/food_order/setFoodOrderInfo.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			// *****到此都沒有任何錯誤，所以接著整理資料寫進資料庫****
			// 去重複
			Set<String> set = new LinkedHashSet<>(timestrArrayList);
			timestrArrayList = new ArrayList<>(set);
			String tempstr = "";
			for (int i = 0; i < timestrArrayList.size(); i++) {
				if(Integer.parseInt((String) timestrArrayList.get(i))>0) {
					tempstr = tempstr + timestrArrayList.get(i) + ":00, ";
				}
			}
			tempstr = tempstr.substring(0, tempstr.length()-2);
			System.out.println(tempstr);
			// 準備寫進資料庫
			// 利用service
			FoodorderService foodorderSvc = new FoodorderService();
			foodorderSvc.updateStoreInfo(storeid, tablecount, orderlimit, tempstr);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			// 把要跳轉的的資料也要準備好 不然都沒有資料
			List<Meal> list = foodorderSvc.getAllStatusOk(storeid);
			req.setAttribute("list", list); // 資料庫update成功後,正確的的empVO物件,存入req
			// 2.訂位設定資料
			// 盧逸臣dao
			Store storeInfo = foodorderSvc.getStoreInfo(storeid);
			String newtimestr = "未設定";
			Integer newtablecount = 0;
			Integer neworderlimit = 0;
			if (storeInfo.getStoreEtime() != null) {
				newtimestr = storeInfo.getStoreEtime();
			}
			if (storeInfo.getStoreTable() != null) {
				newtablecount = storeInfo.getStoreTable();
			}
			if (storeInfo.getStoreEtable() != null) {
				neworderlimit = storeInfo.getStoreEtable();
			}
			req.setAttribute("timestr", newtimestr);
			req.setAttribute("tablecount", newtablecount);
			req.setAttribute("orderlimit", neworderlimit);
			String url = "/front-end/store/food_order/updateFoodOrderInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

		////////////////////////////////////////////// member ////////////////////
		if ("Member_order_button".equals(action)) {
			// 取得店家id，這邊照理來說應該要在session可以取到，因為最前面進入店家
			// 現在先模擬自己set進去 再get出來 之後前面頁面有set過 *這邊set要移除*
			HttpSession session = req.getSession();
			session.setAttribute("storeId", 6);
			session.setAttribute("memberId", 1);
			Integer storeid = (Integer) session.getAttribute("storeId");
			// 利用service
			FoodorderService foodorderSvc = new FoodorderService();
			Store storeInfo = foodorderSvc.getStoreInfo(storeid);
			if(storeInfo.getStoreEtime()!=null) {
				String[] split = storeInfo.getStoreEtime().split(", ");
				for(int i =0; i< split.length; i++) {
					System.out.println(split[i]);
				}
				req.setAttribute("storetime", split);
			}
			req.setAttribute("storename", storeInfo.getStoreName());
			String url = "/front-end/Member/food_order/setFoodOrderInfo.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
		// 會員店餐資訊回傳處理
		if ("Member_order_to_meal".equals(action)) {
			HttpSession session = req.getSession();
			Integer storeid = (Integer) session.getAttribute("storeId");
			// 取得前面資訊
			String orderShopName = req.getParameter("order_shop_name");
			String nameInput = req.getParameter("nameInput");
			String phoneInput = req.getParameter("phoneInput");
			String peopleNum1 = req.getParameter("peopleNum1");
			String dateInput = req.getParameter("dateInput");
			String time1 = req.getParameter("time1");
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();

			String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (nameInput == null || nameInput.trim().length() == 0) {
				errorMsgs.put("name","請勿空白!");
			} else if(!nameInput.trim().matches(nameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("name","只能是中、英文字母、數字和_ , 且長度必需在2到10之間!");
            }
			String phoneReg = "^09[\\d]{8}$";
			if (phoneInput == null || phoneInput.trim().length() == 0) {
				errorMsgs.put("phone","請勿空白!");
			} else if(!phoneInput.trim().matches(phoneReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("phone","請輸入正確手機格式!");
            }
			if (peopleNum1 == null || "0".equals(peopleNum1)) {
				errorMsgs.put("peopleNum1","請選擇!");
			}
			String dateInputReg = "^[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]$";
			if (dateInput == null || "".equals(dateInput)) {
				errorMsgs.put("dateInput","請勿空白!");
			} else if(!dateInput.trim().matches(dateInputReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("dateInput","請輸入正確日期格式!");
            }
			if (time1 == null || "0".equals(time1)) {
				errorMsgs.put("time1","請選擇!");
			}

			System.out.println(errorMsgs);
			System.out.println(dateInput);
			if (!errorMsgs.isEmpty()) {
				FoodorderService foodorderSvc = new FoodorderService();
				Store storeInfo = foodorderSvc.getStoreInfo(storeid);
				if(storeInfo.getStoreEtime()!=null) {
					String[] split = storeInfo.getStoreEtime().split(", ");
					for(int i =0; i< split.length; i++) {
						System.out.println(split[i]);
					}
					req.setAttribute("storetime", split);
				}
				req.setAttribute("nameInput", nameInput);
				req.setAttribute("phoneInput", phoneInput);
				req.setAttribute("storename", orderShopName);

				req.setAttribute("peopleNum1", peopleNum1);
				req.setAttribute("dateInput", dateInput);
				req.setAttribute("time1", time1);

				req.setAttribute("errorMsgs", errorMsgs);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/Member/food_order/setFoodOrderInfo.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			// 先查詢該日訂單人數上限是否已滿，如果已滿一樣要把資料設回去 讓前端在重輸
			// 利用service
			FoodorderService foodorderSvc = new FoodorderService();
			// 用id + 日期 +時段+訂單狀態成立、報到(一定要取消訂單或者完成訂單 才釋出訂位數) 的訂單 取出
			List<Reserva> reservabyStoreidRendate = foodorderSvc.getReservabyStoreidRendate(storeid, dateInput, time1+":00", 0);
			List<Reserva> reservabyStoreidRendate2 = foodorderSvc.getReservabyStoreidRendate(storeid, dateInput, time1+":00", 2);
			Store storeInfo = foodorderSvc.getStoreInfo(storeid);
			// 取出店家訂位上限數
			Integer limit = 0;
			if(storeInfo.getStoreEtable()!=null) {
				limit = storeInfo.getStoreEtable();
			}
			System.out.println(storeid);
			System.out.println(dateInput);
			System.out.println(time1);
			System.out.println(reservabyStoreidRendate.size());
			System.out.println(limit);
			if((reservabyStoreidRendate.size()+reservabyStoreidRendate2.size())>=limit) {
				errorMsgs.put("overlimit","該日期時段已訂滿，請重新選擇日期!");
				storeInfo = foodorderSvc.getStoreInfo(storeid);
				if(storeInfo.getStoreEtime()!=null) {
					String[] split = storeInfo.getStoreEtime().split(", ");
					for(int i =0; i< split.length; i++) {
						System.out.println(split[i]);
					}
					req.setAttribute("storetime", split);
				}
				req.setAttribute("nameInput", nameInput);
				req.setAttribute("phoneInput", phoneInput);
				req.setAttribute("storename", orderShopName);

				req.setAttribute("peopleNum1", peopleNum1);
				req.setAttribute("dateInput", dateInput);
				req.setAttribute("time1", time1);

				req.setAttribute("errorMsgs", errorMsgs);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/Member/food_order/setFoodOrderInfo.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			// 到這都正常  準備將資料放進session 讓後面餐點跟優惠劵 一併到時候到結帳頁面 在寫入 訂單 跟 訂單明細
			session.setAttribute("foodorder_name", nameInput);
			session.setAttribute("foodorder_phone", phoneInput);
			session.setAttribute("foodorder_peopleNum", peopleNum1);
			session.setAttribute("foodorder_time", time1+":00");
			session.setAttribute("foodorder_date", dateInput);

			// 準備下一頁資料
			List<Meal> allMealbyStoreidStatus = foodorderSvc.getAllMealbyStoreidStatus(storeid, 1);
			req.setAttribute("list", allMealbyStoreidStatus);
			// 防止店家設定訂位時間及人數  但"未"設定餐點  所以這邊就把它擋住
			if(allMealbyStoreidStatus.size()==0) {
				errorMsgs.put("meal","該店家未設定餐點");
				storeInfo = foodorderSvc.getStoreInfo(storeid);
				if(storeInfo.getStoreEtime()!=null) {
					String[] split = storeInfo.getStoreEtime().split(", ");
					for(int i =0; i< split.length; i++) {
						System.out.println(split[i]);
					}
					req.setAttribute("storetime", split);
				}
				req.setAttribute("nameInput", nameInput);
				req.setAttribute("phoneInput", phoneInput);
				req.setAttribute("storename", orderShopName);

				req.setAttribute("peopleNum1", peopleNum1);
				req.setAttribute("dateInput", dateInput);
				req.setAttribute("time1", time1);

				req.setAttribute("errorMsgs", errorMsgs);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/Member/food_order/setFoodOrderInfo.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			String url = "/front-end/Member/food_order/orderFoodOrderPage2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);

		}
		// 結帳按鈕
		if ("Member_order_meal".equals(action)) {
			// 整理餐點訂單資訊 [{mealId:2, mealName:套餐1, rdQuantity:1, pdPrice:180},{mealId:3, mealName:套餐2, rdQuantity:1, pdPrice:180}]
			List<Map> mealListInfo = new ArrayList<Map>();
			String errorMealNumMsgs = "";
			HttpSession session = req.getSession();
			Integer storeid = (Integer) session.getAttribute("storeId");
			Integer peopleNum = Integer.parseInt((String) session.getAttribute("foodorder_peopleNum"));
			//優惠劵
			String codeinput = req.getParameter("codeinput");
			session.setAttribute("foodorder_code", codeinput);
			// 利用service
			FoodorderService foodorderSvc = new FoodorderService();
			List<Meal> allMealbyStoreidStatus = foodorderSvc.getAllMealbyStoreidStatus(storeid, 1);
			// 先檢查數量是否正確 如果不對則跳回那頁
			Integer total = 0;
			for (int i = 0; i<allMealbyStoreidStatus.size(); i++) {
				total = total + Integer.parseInt(req.getParameter(String.valueOf(allMealbyStoreidStatus.get(i).getMealId())));
			}
			if(total<peopleNum) {
				errorMealNumMsgs = errorMealNumMsgs + "餐點數量不正確!請選"+peopleNum+"份";
				req.setAttribute("list", allMealbyStoreidStatus);
				req.setAttribute("errorMealNumMsgs", errorMealNumMsgs);
				req.setAttribute("codeinput", codeinput);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/Member/food_order/orderFoodOrderPage2.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			Integer totalMoney = 0;
			for (int i = 0; i<allMealbyStoreidStatus.size(); i++) {
				Map oneMeal = new LinkedHashMap();
				oneMeal.put("mealId", allMealbyStoreidStatus.get(i).getMealId());
				oneMeal.put("mealName", allMealbyStoreidStatus.get(i).getMealName());
				oneMeal.put("rdQuantity", Integer.parseInt(req.getParameter(String.valueOf(allMealbyStoreidStatus.get(i).getMealId()))));
				oneMeal.put("pdPrice", allMealbyStoreidStatus.get(i).getMealPrice());
				mealListInfo.add(oneMeal);
				totalMoney = totalMoney + Integer.parseInt(req.getParameter(String.valueOf(allMealbyStoreidStatus.get(i).getMealId())))*allMealbyStoreidStatus.get(i).getMealPrice();
				System.out.println(oneMeal);
			}
			for (int i = 0; i<allMealbyStoreidStatus.size(); i++) {
				System.out.println(mealListInfo.get(i));
			}
			// 準備轉交的資料
			//1. 店家名字
			Store storeInfo = foodorderSvc.getStoreInfo(storeid);
			req.setAttribute("storeName", storeInfo.getStoreName());
			//2. 優惠劵金額 and 優惠劵編號
			List<Integer> codeMoneyList = foodorderSvc.getCodeMoney(req.getParameter("codeinput"), storeid);
			Integer codeMoney = 0;
			Integer codeId = null;
			if (codeMoneyList.size()!=0) {
				codeMoney = codeMoneyList.get(0);
				codeId = foodorderSvc.getCodeId(req.getParameter("codeinput"), storeid).get(0);
			}
			session.setAttribute("foodorder_codeMoney", codeMoney);
			session.setAttribute("foodorder_codeId", codeId);
			//3.總金額
			session.setAttribute("foodorder_totalMoney", totalMoney);
			//4.結帳金額
			Integer totalCodeMoney = 0;
			if(totalMoney-codeMoney>0) {
				totalCodeMoney = totalMoney-codeMoney;
			}
			session.setAttribute("foodorder_totalCodeMoney", totalCodeMoney);
			//5.餐點清單
			session.setAttribute("foodorder_mealList", mealListInfo);
			// 跳轉到結帳頁
			String url = "/front-end/Member/food_order/checkout.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);

		}

	}

}

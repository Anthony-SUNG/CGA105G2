package com.core.servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import com.emp.model.Employee.pojo.Employee;
import com.emp.model.EmployeeRoot.pojo.EmployeeRoot;
import com.emp.model.service.EmployeeService;
import com.member.model.service.MemberService;
import com.member.model.Member.pojo.Member;
import com.store.model.Store.pojo.Store;
import com.store.model.service.StoreService;

import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@WebServlet("/LonginServlet")
public class LonginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String memname = request.getParameter("MEM_NAME");
			String memnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (memname == null || memname.trim().length() == 0) {
				errorMsgs.add("姓名: 請勿空白");
			} else if (!memname.trim().matches(memnameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			String memacc = request.getParameter("MEM_ACC").trim();
			if (memacc == null || memacc.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			}
			String mempwd = request.getParameter("MEM_PWD").trim();
			if (mempwd == null || mempwd.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}
			String mempwd2 = request.getParameter("MEM_PWD2").trim();
			if (mempwd2 == null || mempwd2.trim().length() == 0) {
				errorMsgs.add("確認密碼請勿空白");
			}
			String memrecipient = request.getParameter("MEM_RECIPIENT").trim();
			if (memrecipient == null || memrecipient.trim().length() == 0) {
				errorMsgs.add("姓名欄位請勿空白");
			}
			String memtwid = request.getParameter("MEM_TW_ID").trim();
			String memtwidReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (memtwid == null || memtwid.trim().length() == 0) {
				errorMsgs.add("身分證字號請勿空白");
			} else if (!memtwid.trim().matches(memtwidReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("身分證字號格式錯誤");
			}
			java.sql.Date membirthday = null;
			try {
				membirthday = java.sql.Date.valueOf(request.getParameter("MEM_BIRTHDAY").trim());
			} catch (IllegalArgumentException e) {
				membirthday = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
			String memphone = request.getParameter("MEM_PHONE").trim();
			if (memphone == null || memphone.trim().length() == 0) {
				errorMsgs.add("電話號碼請勿空白");
			}
			Integer mempostalcode = Integer.valueOf(request.getParameter("MEM_POSTAL_CODE").trim());
			if (mempostalcode == null) {
				errorMsgs.add("郵遞區號請勿空白");
			}
			String memcity = request.getParameter("MEM_CITY").trim();
			if (memcity == null || memcity.trim().length() == 0) {
				errorMsgs.add("城市請勿空白");
			}
			String memdistrict = request.getParameter("MEM_DISTRICT").trim();
			if (memdistrict == null || memdistrict.trim().length() == 0) {
				errorMsgs.add("地區請勿空白");
			}
			String memaddress = request.getParameter("MEM_ADDRESS").trim();
			if (memaddress == null || memaddress.trim().length() == 0) {
				errorMsgs.add("地址請勿空白");
			}
			String memmail = request.getParameter("MEM_MAIL").trim();
			if (memmail == null || memmail.trim().length() == 0) {
				errorMsgs.add("電子信箱請勿空白");
			}
			Member Member = new Member();
			MemberService memSvc = new MemberService();
			Member = memSvc.topojo(memname, memacc, mempwd, memrecipient, memtwid, membirthday, memphone, mempostalcode,
					memcity, memdistrict, memaddress, memmail);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("Member", Member); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = request
						.getRequestDispatcher("/front-end/Member/member/memberRegister.jsp");
				failureView.forward(request, response);
				return;
			}
			/*************************** 2.開始新增資料 ***************************************/
			Member = memSvc.addMem(memname, memacc, mempwd, memrecipient, memtwid, membirthday, memphone, mempostalcode,
					memcity, memdistrict, memaddress, memmail);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/index.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(request, response);
		}
		if ("Signinm".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgm = new LinkedList<String>();
			request.setAttribute("errorMsgm", errorMsgm);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String memacc = request.getParameter("MEM_ACC");
			String mempwd = request.getParameter("MEM_PWD");
			Member member= new Member();
			member.setMemAcc(memacc);
			request.setAttribute("member",member);
			if (memacc == null || (memacc.trim()).length() == 0 || mempwd == null || (mempwd.trim()).length() == 0 ) {
				errorMsgm.add("帳密不可為空白");
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/Member/member/memberLognIn.jsp");
				failureView.forward(request, response);
				return;// 程式中斷
			}
			MemberService memSvc = new MemberService();
			member = memSvc.signin(memacc, mempwd);
			Integer memid = member.getMemId();
			if (memid == 0) {
				errorMsgm.add("查無帳號");
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/Member/member/memberLognIn.jsp");
				failureView.forward(request, response);
				return;// 程式中斷
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/


			request.getSession().setAttribute("memberName", memSvc.getById(memid).getMemName());
			request.getSession().setAttribute("memId", memid);
			String url = "/index.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(request, response);
	}
		// signin store -------------------------------------------------------------------------------------------------------------------------------
		if ("Signins".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgS = new LinkedList<String>();
			request.setAttribute("errorMsgS", errorMsgS);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String storeacc = request.getParameter("STORE_ACC");
			String storepwd = request.getParameter("STORE_PWD");
			Store store=new Store();
			store.setStoreAcc(storeacc);
			request.setAttribute("store",store);
			request.setAttribute("errorMsgS", errorMsgS);
			if (storeacc == null || (storeacc.trim()).length() == 0 || storepwd == null || (storepwd.trim()).length() == 0 ) {
				errorMsgS.add("帳密不可為空白");
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/Member/member/memberLognIn.jsp");
				failureView.forward(request, response);
				return;// 程式中斷
			}
			StoreService storeSvc = new StoreService();
			Store Store = storeSvc.signin(storeacc, storepwd);
			Integer storeid = Store.getStoreId();
			if (storeid == 0) {
				errorMsgS.add("查無帳號");
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/Member/member/memberLognIn.jsp");
				failureView.forward(request, response);
				return;// 程式中斷
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			request.getSession().setAttribute("storeId", storeid);
			request.getSession().setAttribute("StoreName", storeSvc.getById(storeid).getStoreName());
			String url = "/index.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(request, response);
		}
		// signin emp -------------------------------------------------------------------------------------------------------------------------------
		if ("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String acc = request.getParameter("empAcc");
			String pwd = request.getParameter("empPwd");
			EmployeeService employeeService = new EmployeeService();
			Employee employee = employeeService.getAcc(acc);
			if(employee==null) {
				errorMsgs.add("*請輸入正確的員工帳號");
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/emp/employeeLogin.jsp");
				failureView.forward(request, response);
				return;
			}
			String empPwd = employee.getEmpPwd();
			if(!(pwd.equals(empPwd))) {
				errorMsgs.add("*請輸入正確的員工密碼");
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/emp/employeeLogin.jsp");
				failureView.forward(request, response);
				return;
			}
			Integer empId = employee.getEmpId();
			List<EmployeeRoot> empRoot = employeeService.getRoot(empId);
			HttpSession session = request.getSession();
			session.setAttribute("loginEmployee",employee);
			session.setAttribute("empRoot",empRoot);



			session.setAttribute("empId",empId);



			String url = "/index.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}
		if ("out".equals(action)){
			request.getSession().setAttribute("memId", 0);
			request.getSession().setAttribute("storeId",0);
			request.getSession().setAttribute("empId",0);
			String url = "/index.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(request, response);
		}
		if ("byStoreName".equals(action)) { // 來自index.jsp的請求
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("errorMsgs", errorMsgs);
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String storeName = request.getParameter("storeName");
			if (storeName == null || (storeName.trim()).length() == 0) {
				errorMsgs.put("error1", "請輸入要查詢的文字");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request
						.getRequestDispatcher("/index.jsp");
				failureView.forward(request, response);
				return;//程式中斷
			}
			/***************************2.開始查詢資料*****************************************/
			StoreService storeService = new StoreService();
			List<Store> list = storeService.getStoreName(storeName);
			if (list.size()==0) {
				errorMsgs.put("error2", "查無資料");
				RequestDispatcher failureView = request
						.getRequestDispatcher("/index.jsp");
				failureView.forward(request, response);
				return;//程式中斷
			}
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			request.setAttribute("list", list); // 資料庫取出的list物件,存入req
			String url = "/front-end/Member/member/searchStore.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(request, response);
		}
		if ("StorePage".equals(action)) { //來自searchStore的請求 要轉交到店家頁面
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer storeIds = Integer.valueOf(request.getParameter("SearchstoreId").trim());	//拿到我設定的input hidden

			/***************************2.開始查詢資料*****************************************/

			StoreService storeService = new StoreService();
			Store store = storeService.getById(storeIds);  //依照剛剛取得的id 去找尋該筆店家


			/***************************3.查詢完成,準備轉交(Send the Success view)*************/

			request.setAttribute("store", store); //set店家讓下個頁面能收到值
			String url = "/front-end/Member/member/showStorePage.jsp";

			RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(request, response);
		}

		
		
	//  forget1(update)  	-------------------------------------------------------------------------------------------------------------------------------
			if ("forget1".equals(action)) { // 來自addEmp.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				request.setAttribute("errorMsgs", errorMsgs);

				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/			

				String memacc = request.getParameter("MEM_ACC").trim();
				String mempwd = request.getParameter("MEM_PWD").trim();
				String mempwd2 = request.getParameter("MEM_PWD2").trim();
				if (memacc == null || memacc.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}

				if (mempwd == null || mempwd.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}

				if (mempwd2 == null || mempwd2.trim().length() == 0) {
					errorMsgs.add("確認密碼請勿空白");
				}
				
				Member Member = new Member();
				Member.setMemAcc(memacc);
				Member.setMemPwd(mempwd);				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("Member", Member); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request
							.getRequestDispatcher("/front-end/Member/member/forget1.jsp");
					failureView.forward(request, response);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MemberService memSvc = new MemberService();
				Member = memSvc.forget1(memacc, mempwd);
				Member member1 = memSvc.signin(memacc, mempwd);
				Integer memid = member1.getMemId();
				if (memid == 0) {
					errorMsgs.add("查無帳號");
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/Member/member/forget1.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/Member/member/memberLognIn.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(request, response);

			}






	}


}
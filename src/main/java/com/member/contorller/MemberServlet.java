package com.member.contorller;

import javax.servlet.*;
import javax.servlet.http.*;

import com.member.model.Member.pojo.Member;
import com.member.model.service.MemberService;

import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/Member/MemberServlet")
public class MemberServlet extends HttpServlet {
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
		Integer storeId = (Integer) request.getSession().getAttribute("storeId");
		Integer memid = (Integer) request.getSession().getAttribute("memId");
		Integer empId = (Integer) request.getSession().getAttribute("empId");
//  insert  	-------------------------------------------------------------------------------------------------------------------------------
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
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
			String url = "/front-end/Member/member/memberIInfo.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(request, response);
		}
//  update  	-------------------------------------------------------------------------------------------------------------------------------
		if ("update".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
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
			if (mempwd2 != mempwd) {
				errorMsgs.add("確認密碼請與密碼相同");
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

			String memtext = request.getParameter("MEM_TEXT").trim();

			byte[] mempic = request.getPart("MEM_PIC").getInputStream().readAllBytes();

			Member Member = new Member();
			Member.setMemName(memname);
			Member.setMemAcc(memacc);
			Member.setMemPwd(mempwd);
			Member.setMemRecipient(memrecipient);
			Member.setMemTwId(memtwid);
			Member.setMemBirthday(membirthday);
			Member.setMemPhone(memphone);
			Member.setMemPostalCode(mempostalcode);
			Member.setMemCity(memcity);
			Member.setMemDistrict(memdistrict);
			Member.setMemAddress(memaddress);
			Member.setMemMail(memmail);
			Member.setMemText(memtext);
			Member.setMemPic(mempic);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("Member", Member); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = request
						.getRequestDispatcher("/front-end/Member/member/memberIInfo.jsp");
				failureView.forward(request, response);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			MemberService memSvc = new MemberService();
			Member = memSvc.updateMem(memid, memname, memacc, memrecipient, memtwid, membirthday, memphone, mempostalcode,
					memcity, memdistrict, memaddress, memmail, memtext, mempic);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front-end/Member/member/memberIInfo.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(request, response);

		}

//  search  	-------------------------------------------------------------------------------------------------------------------------------

		if ("Search".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String srh = request.getParameter("search");
			String srhms = request.getParameter("Search");

			if (srh == null || (srh.trim()).length() == 0) {
				errorMsgs.add("請輸內容");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request.getRequestDispatcher("/index.jsp");
				failureView.forward(request, response);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			if ("searchMember".equals(srhms)) {
				MemberService memSvc = new MemberService();
				List<Member> Member = memSvc.getMember(srh);
				if (Member == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/index.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				request.setAttribute("memId", Member); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/Member/member/searchMember.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);
			} else if ("searchStore".equals(srhms)) {

			}
			;
		}

		// memberinfo
		// -------------------------------------------------------------------------------------------------------------------------------

		if ("MemberInfo".equals(action)) {

			MemberService memSvc = new MemberService();
			Member Member = memSvc.meminfo(memid);

			request.setAttribute("Member", Member);
			String url = "/front-end/Member/member/memberIInfo.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);

		}
	}


}
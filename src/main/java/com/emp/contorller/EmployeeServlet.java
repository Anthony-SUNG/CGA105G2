package com.emp.contorller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.Employee.pojo.Employee;
import com.emp.model.EmployeeRoot.pojo.EmployeeRoot;
import com.emp.model.service.EmployeeService;
import com.store.model.Store.dao.impl.StoreDAO;
import com.store.model.Store.pojo.Store;

@WebServlet("/back-end/emp/test")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		Integer storeId = (Integer) req.getSession().getAttribute("storeId");
		Integer memId   = (Integer) req.getSession().getAttribute("memId");
		Integer empId   = (Integer) req.getSession().getAttribute("empId");

		if ("getOne".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			String str = req.getParameter("employee");
			if (str == null || (str.trim()).length() == 0) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/updateEmp2.jsp");
				failureView.forward(req, res);
				return;
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("<%=request.getContextPath()%>/back-end/emp/updateEmp2.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}



			empId = Integer.valueOf(str);



			EmployeeService employeeService = new EmployeeService();
			Employee employee = employeeService.getOneEmp(empId);
			if (employee == null) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/updateEmp2.jsp");
				failureView.forward(req, res);
				return;//
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("<%=request.getContextPath()%>/back-end/emp/updateEmp2.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			req.setAttribute("employee", employee);
			String url = "/back-end/emp/updateEmp3.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String acc = req.getParameter("acc");
			String enameReg = "^[(a-zA-Z0-9_)]{2,10}$";
			if (acc == null || acc.trim().length() == 0) {
				errorMsgs.add("員工帳號請勿空白");
			} else if (!acc.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("員工姓名只能是英文字母、數字和_ , 且長度必需在2到10之間");
			}
			String pwd = req.getParameter("pwd").trim();
			if (pwd == null || pwd.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}
			Employee employee = new Employee();
			employee.setEmpAcc(acc);
			employee.setEmpPwd(pwd);
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("employee", employee);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/addEmp2.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 ***************************************/
			EmployeeService empSvc = new EmployeeService();
			employee = empSvc.addEmp(acc, pwd);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/emp/updateEmp2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/


			empId = Integer.valueOf(req.getParameter("empId"));



			/*************************** 2.開始查詢資料 ****************************************/
			EmployeeService employeeService = new EmployeeService();
			Employee employee = employeeService.getOneEmp(empId);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("employee", employee); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/emp/updateEmp4.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer empid = Integer.valueOf(req.getParameter("empid").trim());
			Integer empstatus = Integer.valueOf(req.getParameter("empstatus").trim());
			Integer empper = Integer.valueOf(req.getParameter("empper").trim());
			java.sql.Date emptime = java.sql.Date.valueOf(req.getParameter("emptime").trim());
			String empacc = req.getParameter("empacc");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (empacc == null || empacc.trim().length() == 0) {
				errorMsgs.add("員工姓名: 請勿空白");
			} else if (!empacc.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			String emppwd = req.getParameter("emppwd").trim();
			if (emppwd == null || emppwd.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}
			java.sql.Date emprtime = null;
			try {
				emprtime = java.sql.Date.valueOf(req.getParameter("emprtime").trim());
			} catch (IllegalArgumentException e) {
				emprtime = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
			Employee employee = new Employee();
			employee.setEmpStatus(empstatus);
			employee.setEmpId(empid);
			employee.setEmpAcc(empacc);
			employee.setEmpPwd(emppwd);
			employee.setEmpPer(empper);
			employee.setEmpTime(emptime);
			employee.setEmpRtime(emprtime);
			/*************************** 2.開始修改資料 *****************************************/
			EmployeeService empSvc = new EmployeeService();
			empSvc.updateEmp(employee);
			List list = empSvc.all();
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("employee", employee);
			req.setAttribute("list", list);// 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/emp/updateEmp2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		if ("employeeall".equals(action)) {
			EmployeeService empSvc = new EmployeeService();
			List<Employee> list = empSvc.all();
			req.setAttribute("list", list); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/emp/updateEmp2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		if ("getRoot".equals(action)) {
			String str = req.getParameter("getRootID");
			Integer rootID = Integer.valueOf(str);;
			System.out.println(rootID);
			EmployeeService empSvc = new EmployeeService();
			List<EmployeeRoot> list = empSvc.getRootEmp(rootID);
			for (EmployeeRoot i : list) {
				System.out.print(i.getEmpId() + ",");
				System.out.print(i.getRootId() + " \n");
			}
			req.setAttribute("list", list); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/emp/show_page2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

		}

	}

}

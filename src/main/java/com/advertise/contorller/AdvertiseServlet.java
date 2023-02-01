package com.advertise.contorller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advertise.model.Advertise.pojo.Advertise;
import com.advertise.model.service.AdvertiseService;

@WebServlet("/adServlet")
public class AdvertiseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("adPass".equals(action)) {
			String str = req.getParameter("advId");
			Integer advId = Integer.valueOf(str);
			String str2 = req.getParameter("empId");
			Integer empId = Integer.valueOf(str2);

			AdvertiseService adSvc = new AdvertiseService();
			adSvc.updatePass(advId, empId);

			String url = "/back-end/advertise/reviewAdvertise2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

		}
		if ("adNotPass".equals(action)) {
			String str = req.getParameter("advId");
			Integer advId = Integer.valueOf(str);
			String str2 = req.getParameter("empId");
			Integer empId = Integer.valueOf(str2);

			AdvertiseService adSvc = new AdvertiseService();
			adSvc.update(advId, empId);

			String url = "/back-end/advertise/reviewAdvertise2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

		}
		if ("getPhoto".equals(action)) { // 秀文章圖片
			OutputStream out = res.getOutputStream();
			String adId = req.getParameter("adId");
			System.out.println(adId);// 該如何使用
			AdvertiseService adSvc = new AdvertiseService();
			Advertise advertise = adSvc.getByAdvId(Integer.valueOf(adId));
			System.out.println(advertise);
			res.setContentType("image/jpg");
			out.write(advertise.getAdvImg());
			out.close();

		}
	}
}

package com.advertise.contorller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advertise.model.Advertise.pojo.Advertise;
import com.advertise.model.service.AdvertiseService;
@MultipartConfig
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
			String url = "/back-end/advertise/reviewAdvertise.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		if ("adNotPass".equals(action)) {
			String str = req.getParameter("advId");
			Integer advId = Integer.valueOf(str);
			String str2 = req.getParameter("empId");
			Integer empId = Integer.valueOf(str2);
			AdvertiseService adSvc = new AdvertiseService();
			adSvc.update(advId, empId);
			String url = "/back-end/advertise/reviewAdvertise.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("getPhoto".equals(action)) { // 秀文章圖片
			OutputStream out = res.getOutputStream();
			String adId = req.getParameter("adId");
			AdvertiseService adSvc = new AdvertiseService();
			Advertise advertise = adSvc.getByAdvId(Integer.valueOf(adId));
			res.setContentType("image/jpg");
			out.write(advertise.getAdvImg());
			out.close();
		}
		if("addAD".equals(action)) {
            byte adImg[] = req.getPart("adImg").getInputStream().readAllBytes();
            Advertise advertise = new Advertise();
            advertise.setStoreId(Integer.valueOf(77));
            advertise.setEmpId(Integer.valueOf(2));
            advertise.setAdvStatus(Integer.valueOf(1));
            advertise.setAdvImg(adImg);
            advertise.setAdvText("0203成功");
            advertise.setAdvStime(Date.valueOf("2022-12-11"));
            advertise.setAdvNtime(Date.valueOf("2023-12-11"));
            AdvertiseService adSvc = new AdvertiseService();
            adSvc.addAD(advertise);
            String url = "/back-end/advertise/reviewAdvertise.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
        }
	}
}

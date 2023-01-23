package com.waiting.contorller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/ContactUs/SendMailServlet" })
public class SendMailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String mail = req.getParameter("email");
		String message = req.getParameter("message");

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		Mailer mailer = new Mailer();
		mailer.send(name, phone, mail, message);
		out.print("message has been sent successfully");
		out.close();
		String url = "/BlankPage/contactUsDone.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, resp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}

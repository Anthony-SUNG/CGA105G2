package com.waiting.contorller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.calltable.service.TableService;
import com.foodorder.model.Reserva.pojo.Reserva;
import com.google.gson.Gson;
import com.waiting.model.pojo.Standby;
import com.waiting.model.service.StandbyService;

@WebServlet("/standby")
public class StandbyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void TestStandBy(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        Integer storeId = (Integer) req.getSession().getAttribute("storeId");
        Integer memId = (Integer) req.getSession().getAttribute("memId");
        Integer empId = (Integer) req.getSession().getAttribute("empId");
        String Stadefault = "off";

        String onOff = req.getParameter("onOff");
        req.setAttribute("onOff", onOff);
        StandbyService staSvc = new StandbyService();

        if ("addStaLoading".equals(action)) {
            res.setContentType("text/plain");

        }

        if ("getOneUpdate".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);
            Integer staId = Integer.valueOf(req.getParameter("staId"));
            StandbyService standbySvc = new StandbyService();
            Standby standbyVo = standbySvc.getOneStandBy(staId);
            req.setAttribute("standbyVo", standbyVo);
            String url = "/front-end/Member/standby/update_status_input.jsp";
            RequestDispatcher suceeessDispatcher = req.getRequestDispatcher(url);
            suceeessDispatcher.forward(req, res);
        }

        // -------------- Update狀態----------------------
        if ("Update".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);
            // ---接收請求參數 & 輸入錯誤處理---
            Integer staId = Integer.valueOf(req.getParameter("staId").trim());
            Integer staStatus = Integer.valueOf(req.getParameter("staStatus").trim());
            Standby standbyVo = new Standby();
            standbyVo.setStaId(staId);
            standbyVo.setStaStatus(staStatus);
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("standbyVo", standbyVo);
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/front-end/Member/standby/update_status_input.jsp");
                failureView.forward(req, res);
                return;
            }
            // 開始修改狀態
            StandbyService standBySvc = new StandbyService();
            standbyVo = standBySvc.updateStandBy(staId, staStatus);
            // 3.修改完成,準備轉交
            req.setAttribute("standbyVo", standbyVo);
            String url = "/front-end/store/calltable/callTable.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        // ===============================Insert(addStandBy.jsp)============================================
        if ("insertSta".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);
            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            String staName = req.getParameter("staName");
            String sta_nameReg = "^[(\u4e00-\u9fa5)]{2,10}$";
            if (staName == null || staName.trim().length() == 0) {
                errorMsgs.add("姓名請勿空白");
            } else if (!staName.trim().matches(sta_nameReg)) {
                errorMsgs.add("填寫姓名只能使用中文，且長度在2-10之間");
            }
            storeId = Integer.valueOf(req.getParameter("storeId").trim());
            if (storeId == null) {
                errorMsgs.add("請填寫店家");
            }
            String staPhone = req.getParameter("staPhone");
            String phoneReg = "^09\\d{8}$";
            if (staPhone == null || staPhone.trim().length() == 0) {
                errorMsgs.add("電話請勿空白");
            } else if (!staPhone.trim().matches(phoneReg)) {
                errorMsgs.add("電話格式輸入錯誤");
            }
            Integer staNumber = null;
            try {
                staNumber = Integer.valueOf(req.getParameter("staNumber").trim());
            } catch (NumberFormatException e) {
                if (staNumber == null) {
                    errorMsgs.add("候位人數請勿空白");
                } else if (staNumber > 10) {
                    errorMsgs.add("候位人數太多了");
                }
            }
            Standby standbyVo = new Standby();
            standbyVo.setStaName(staName);
            standbyVo.setStoreId(storeId);
            standbyVo.setStaPhone(staPhone);
            standbyVo.setStaNumber(staNumber);

            if (!errorMsgs.isEmpty()) {
                req.setAttribute("standbyVo", standbyVo); // 含有輸入格式錯誤的waitingVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Member/waiting/addStandBy.jsp");
                failureView.forward(req, res);
                return;
            }
            // ============================開始新增================================================
            StandbyService standBySvc = new StandbyService();
            standbyVo = standBySvc.addStandBy(storeId, staName, staPhone, staNumber);

            req.setAttribute("standbyVo", standbyVo);

            String url = "/front-end/Member/waiting/listOneStandby.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        // =============================Delete(callTable)================================
        if ("delete".equals(action)) {// 來自listAllStandBy.jsp
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);
            // ===接收請求參數====
            Integer staId = Integer.valueOf(req.getParameter("staId").trim());
            // ===========開始刪除(叫號移除候位表)================d
            StandbyService standBySvc = new StandbyService();
            standBySvc.deleteStandBy(staId);
            // ==========刪除(叫號完成)===================
            String url = "/front-end/store/calltable/callTable.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        if ("selectStandBy".equals(action)) {
            res.setContentType("text/html;charset=UTF-8");
            // String date = req.getParameter("date");
            // String totime = req.getParameter("totime");
            // req.setAttribute("date", date);
            // req.setAttribute("totime", totime);
            List<Standby> list = staSvc.getAll();
            List<Standby> staList = new ArrayList<Standby>();
            for (Standby list2 : list) {
                staList.add(list2);
            }
            if (onOff.equals("on")){
                req.setAttribute("onOff", true);
            }
            Gson gson = new Gson();
            String json = gson.toJson(staList);
            res.getWriter().write(json);
            System.out.println(onOff);
            String url = "/TableServlet?action=table";
                       RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
                       successView.forward(req, res);
        }

        if ("checkMem".equals(action)) {
            // 修改STANDBY狀態狀態
        	System.out.println("before5");
            Integer staId = Integer.valueOf(req.getParameter("staId"));
            Standby standby = staSvc.updateStandBy(staId, 2);
           
            res.setContentType("text/html;charset=UTF-8");
            res.getWriter().write("update succ");
            // ===新增到訂位===
            String date = req.getParameter("date");
            String totime = req.getParameter("totime").trim();
            req.setAttribute("date", date);
            req.setAttribute("totime", totime);
            String staName = req.getParameter("staName");
            String staPhone = req.getParameter("staPhone");
            Integer staNumber = Integer.valueOf(req.getParameter("staNumber"));
            
            Reserva reserva = new Reserva();
            TableService tableSvc = new TableService();
            // Integer storeId, Integer memId, String renName, String renPhone, String
            // renTime,
            // java.sql.Date renDate, Integer renHeadcount, Integer renPrice, Integer
            // renFprice)
            

            reserva = tableSvc.checkStandBy(storeId, 0, staName, staPhone, totime, java.sql.Date.valueOf(date),
                    staNumber, 0, 0);
          
            Gson gson = new Gson();
            String json = gson.toJson(reserva);
            res.getWriter().write(json);
//            String url = "/TableServlet?action=table";
//            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//            successView.forward(req, res);

        }

    }

    public static void main(String[] args) {
        // StandbyService staSvc = new StandbyService();
        // List<Standby> staList = new ArrayList<Standby>();
        // List<Standby> list = staSvc.getAll();
        // for (Standby list2 : list) {
        // staList.add(list2);
        // }
        // for (Standby list3 : staList) {
        // System.out.println(list3.getStaId());
        // }
        // StandbyService standbyService = new StandbyService();
        // Standby standby = standbyService.updateStandBy(11, 2);
  
    }
}

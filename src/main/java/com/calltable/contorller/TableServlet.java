package com.calltable.contorller;


import com.calltable.service.TableService;
import com.store.model.Store.pojo.Store;
import org.json.simple.JSONArray;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@WebServlet("/TableServlet")
public class TableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        TableService tbs=new TableService();
        String action = req.getParameter("action");
        Integer storeId = (Integer) req.getSession().getAttribute("storeId");
        Integer memId   = (Integer) req.getSession().getAttribute("memId");
        Integer empId   = (Integer) req.getSession().getAttribute("empId");

        if("table".equals(action)){
            //進入網頁抓時段、桌數、動態產生網頁選項
            Store store=tbs.topage(storeId);
            String time[]=store.getStoreEtime().split(",");
            List<Integer> tablelist=new ArrayList<>();
            for (int i=1;i<=store.getStoreTable();i++){
                tablelist.add(i);
            }
            req.setAttribute("time",time);
            req.setAttribute("table",tablelist);
            String url = "/front-end/store/calltable/callTable.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
            successView.forward(req, res);
        }
        //查詢訂位資訊
        if("search".equals(action)){
            String date= req.getParameter("date");
            String totime=req.getParameter("totime");
            Map<String,JSONArray> ans=tbs.search(storeId,date,totime,0);
            JSONArray json=ans.get("json");
            JSONArray tablehave=ans.get("tablehave");
            req.setAttribute("date",date);
            req.setAttribute("totime",totime);
            req.setAttribute("list",json);
            req.setAttribute("listq",json.size());
            req.setAttribute("tablehave",tablehave);
            String url = "/TableServlet?action=table";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
            successView.forward(req, res);
        }
        //重新整理
        if("reload".equals(action)){
            String date= req.getParameter("date");
            String totime=req.getParameter("totime");
            req.setAttribute("date",date);
            req.setAttribute("totime",totime);
            String url = "/TableServlet?action=search";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
            successView.forward(req, res);
        }
        //帶位
        if("totable".equals(action)){
            Integer id= Integer.valueOf(req.getParameter("toid"));
            Integer table= Integer.valueOf(req.getParameter("table"));

        }
        //報到
        if("check".equals(action)){
            Integer id= Integer.valueOf(req.getParameter("toid"));
        }



    }
}

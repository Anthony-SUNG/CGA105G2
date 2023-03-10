package com.foodorder.contorller;

import com.art.model.Article.pojo.Article;
import com.foodorder.model.Meal.pojo.Meal;
import com.foodorder.model.Reserva.pojo.Reserva;

import com.foodorder.model.ReservaDetail.pojo.ReservaDetail;
import com.foodorder.model.service.FoodorderService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.member.model.Member.pojo.Member;
import com.store.model.Store.pojo.Store;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.io.Serial;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet(urlPatterns = {"/front-end/store/food_order/food_order.do", "/front-end/Member/food_order/food_order.do"})
public class FoodorderServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    public static AllInOne domain;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        Integer storeId = (Integer) req.getSession().getAttribute("storeId");
        Integer memId = (Integer) req.getSession().getAttribute("memId");
        Integer empId = (Integer) req.getSession().getAttribute("empId");
        if ("food_order_button".equals(action)) {
            // ????????????id?????????????????????????????????session??????????????????????????????????????????
            // ?????????????????????set?????? ???get?????? ?????????????????????set??? *??????set?????????*
            HttpSession session = req.getSession();
            Integer storeid = (Integer) session.getAttribute("storeId");
            // ??????service?????????
            // 1.????????????
            FoodorderService foodorderSvc = new FoodorderService();
            List<Meal> list = foodorderSvc.getAllStatusOk(storeid);
            // 2.??????????????????
            Store storeInfo = foodorderSvc.getStoreInfo(storeid);
            String timestr = "?????????";
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
            // ????????????
            req.setAttribute("list", list); // ??????????????????list??????,??????req
            req.setAttribute("timestr", timestr);// ???????????????
            req.setAttribute("tablecount", tablecount);// ???????????????
            req.setAttribute("orderlimit", orderlimit);// ???????????????
            String url = "/front-end/store/food_order/updateFoodOrderInfo.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// ????????????listAllEmp2_getFromSession.jsp
            successView.forward(req, res);
        }
        if ("getOne_For_Update".equals(action)) {
            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);
            Integer mealId = Integer.valueOf(req.getParameter("mealId").trim());
            /*************************** 2.?????????????????? ****************************************/
            FoodorderService foodorderSvc = new FoodorderService();
            Meal meal = foodorderSvc.getOneMeal(mealId);
            /*************************** 3.????????????,????????????(Send the Success view) ************/
            req.setAttribute("mealVO", meal); // ??????????????????empVO??????,??????req
            String url = "/front-end/store/food_order/updateFoodOrderByFood.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// ???????????? update_emp_input.jsp
            successView.forward(req, res);
        }
        if ("update".equals(action)) { // ??????update_emp_input.jsp?????????
            List<String> errorMsgs = new LinkedList<String>();
            String errorMealNameMsgs = "";
            String errorMealPriceMsgs = "";
            /*************************** 1.?????????????????? - ??????????????????????????? **********************/
            Integer mealId = Integer.valueOf(req.getParameter("mealId").trim());
            String mealName = req.getParameter("mealName");
            String mealnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (mealName == null || mealName.trim().length() == 0) {
                errorMealNameMsgs = errorMealNameMsgs + "????????????!";
            } else if (!mealName.trim().matches(mealnameReg)) { // ??????????????????(???)?????????(regular-expression)
                errorMsgs.add("????????????: ???????????????????????????????????????_ , ??????????????????2???10??????");
                errorMealNameMsgs = errorMealNameMsgs + "???????????????????????????????????????_ , ??????????????????2???10??????!";
            }
            String mealPrice = req.getParameter("mealPrice").trim();
            if (mealPrice == null || mealPrice.trim().length() == 0) {
                errorMealPriceMsgs = errorMealPriceMsgs + "??????????????????!";
            }
            try {
                if (Integer.parseInt(mealPrice) <= 0) {
                    errorMealPriceMsgs = errorMealPriceMsgs + "??????????????????0?????????!";
                }
            } catch (NumberFormatException e) {
                errorMealPriceMsgs = errorMealPriceMsgs + "??????????????????!";
            }
            if (errorMealNameMsgs != "" || errorMealPriceMsgs != "") {
                req.setAttribute("errorMealNameMsgs", errorMealNameMsgs);
                req.setAttribute("errorMealPriceMsgs", errorMealPriceMsgs);
                // ?????????id??????????????? ??????????????????
                FoodorderService foodorderSvc = new FoodorderService();
                Meal oldmeal = foodorderSvc.getOneMeal(mealId);
                req.setAttribute("mealVO", oldmeal);
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/front-end/store/food_order/updateFoodOrderByFood.jsp");
                failureView.forward(req, res);
                return; // ????????????
            }
            /*************************** 2.?????????????????? *****************************************/
            // ??????service
            FoodorderService foodorderSvc = new FoodorderService();
            // ????????????id?????????????????????????????????session??????????????????????????????????????????
            HttpSession session = req.getSession();
            Integer storeid = (Integer) session.getAttribute("storeId");
            // ????????????vo
            Meal meal = new Meal(mealId, storeid, mealName, Integer.parseInt(mealPrice), 1);
            Meal oldmeal = foodorderSvc.getOneMeal(mealId);
            // ????????????
            // ?????????????????????
            if (!meal.equals(oldmeal)) {
                meal = new Meal(storeid, mealName, Integer.parseInt(mealPrice), 1);
                foodorderSvc.updateMeal(mealId, meal);
            }
            // ??????????????????????????????????????? ?????????????????????
            List<Meal> list = foodorderSvc.getAllStatusOk(storeid);
            /*************************** 3.????????????,????????????(Send the Success view) *************/
            req.setAttribute("list", list); // ?????????update?????????,????????????empVO??????,??????req
            // 2.??????????????????
            Store storeInfo = foodorderSvc.getStoreInfo(storeid);
            String timestr = "?????????";
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
            req.setAttribute("timestr", timestr);// ???????????????
            req.setAttribute("tablecount", tablecount);// ???????????????
            req.setAttribute("orderlimit", orderlimit);// ???????????????
            String url = "/front-end/store/food_order/updateFoodOrderInfo.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // ???????????????,??????listOneEmp.jsp
            successView.forward(req, res);
        }
        if ("delete".equals(action)) { // ??????listAllEmp.jsp
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);
            /*************************** 1.?????????????????? ***************************************/
            Integer mealid = Integer.valueOf(req.getParameter("mealId"));
            /*************************** 2.?????????????????? ***************************************/
            // ????????????id?????????????????????????????????session??????????????????????????????????????????
            HttpSession session = req.getSession();
            Integer storeid = (Integer) session.getAttribute("storeId");
            FoodorderService foodorderSvc = new FoodorderService();
            foodorderSvc.deleteMeal(mealid);
            // ??????????????????????????????????????? ?????????????????????
            List<Meal> list = foodorderSvc.getAllStatusOk(storeid);
            /*************************** 3.????????????,????????????(Send the Success view) ***********/
            req.setAttribute("list", list); // ?????????update?????????,????????????empVO??????,??????req
            // 2.??????????????????
            Store storeInfo = foodorderSvc.getStoreInfo(storeid);
            String timestr = "?????????";
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
            req.setAttribute("timestr", timestr);// ???????????????
            req.setAttribute("tablecount", tablecount);// ???????????????
            req.setAttribute("orderlimit", orderlimit);// ???????????????
            String url = "/front-end/store/food_order/updateFoodOrderInfo.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// ???????????????,????????????????????????????????????
            successView.forward(req, res);
        }
        if ("insert".equals(action)) { // ??????update_emp_input.jsp?????????
            String errorMealNameMsgs = "";
            String errorMealPriceMsgs = "";
            /*************************** 1.?????????????????? - ??????????????????????????? **********************/
            String mealName = req.getParameter("mealName");
            String mealnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (mealName == null || mealName.trim().length() == 0) {
                errorMealNameMsgs = errorMealNameMsgs + "????????????!";
            } else if (!mealName.trim().matches(mealnameReg)) { // ??????????????????(???)?????????(regular-expression)
                errorMealNameMsgs = errorMealNameMsgs + "???????????????????????????????????????_ , ??????????????????2???10??????!";
            }
            String mealPrice = req.getParameter("mealPrice").trim();
            if (mealPrice == null || mealPrice.trim().length() == 0) {
                errorMealPriceMsgs = errorMealPriceMsgs + "??????????????????!";
                Integer price = 0;
            }
            Integer price = null;
            try {
                if (Integer.parseInt(mealPrice) <= 0) {
                    errorMealPriceMsgs = errorMealPriceMsgs + "??????????????????0?????????!";
                    price = 0;
                } else {
                    price = Integer.parseInt(mealPrice);
                }
            } catch (NumberFormatException e) {
                errorMealPriceMsgs = errorMealPriceMsgs + "??????????????????!";
                price = 0;
            }
            if (errorMealNameMsgs != "" || errorMealPriceMsgs != "") {
                req.setAttribute("errorMealNameMsgs", errorMealNameMsgs);
                req.setAttribute("errorMealPriceMsgs", errorMealPriceMsgs);
                // ?????????????????? old???????????????
                Meal oldmeal = new Meal();
                oldmeal.setMealName(mealName);
                oldmeal.setMealPrice(price);
                req.setAttribute("mealVO", oldmeal);
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/front-end/store/food_order/addFoodOrderByFood.jsp");
                failureView.forward(req, res);
                return; // ????????????
            }
            /*************************** 2.?????????????????? *****************************************/
            // ??????service
            FoodorderService foodorderSvc = new FoodorderService();
            // ????????????id?????????????????????????????????session??????????????????????????????????????????
            HttpSession session = req.getSession();
            Integer storeid = (Integer) session.getAttribute("storeId");
            // ????????????vo
            Meal meal = new Meal();
            meal.setStoreId(storeid);
            meal.setMealName(mealName);
            meal.setMealPrice(Integer.parseInt(mealPrice));
            meal.setMealStatus(1);
            // ????????????
            foodorderSvc.insertMeal(meal);
            // ??????????????????????????????????????? ?????????????????????
            List<Meal> list = foodorderSvc.getAllStatusOk(storeid);
            /*************************** 3.????????????,????????????(Send the Success view) *************/
            req.setAttribute("list", list); // ?????????update?????????,????????????empVO??????,??????req
            // 2.??????????????????
            Store storeInfo = foodorderSvc.getStoreInfo(storeid);
            String timestr = "?????????";
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
            RequestDispatcher successView = req.getRequestDispatcher(url); // ???????????????,??????listOneEmp.jsp
            successView.forward(req, res);
        }
        if ("updateStoreSetting".equals(action)) {
            // ??????????????????id
            HttpSession session = req.getSession();
            Integer storeid = (Integer) session.getAttribute("storeId");
            /*************************** 2.?????????????????? *****************************************/
            // ??????service
            FoodorderService foodorderSvc = new FoodorderService();
            Store storeInfo = foodorderSvc.getStoreInfo(storeid);
            String timestr = "?????????";
            Integer tablecount = 0;
            Integer orderlimit = 0;
            ArrayList timestrArrayList = new ArrayList();
            if (storeInfo.getStoreEtime() != null) {
                timestr = storeInfo.getStoreEtime();
                String[] timestrSplit = timestr.split(", ");
                // ????????????list
                for (int i = 0; i < timestrSplit.length; i++) {
                    String[] timestrSplitSplit = timestrSplit[i].split(":");
                    timestrArrayList.add(timestrSplitSplit[0]);
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
            RequestDispatcher successView = req.getRequestDispatcher(url); // ???????????????,??????listOneEmp.jsp
            successView.forward(req, res);
        }
        if ("updateStoreSettingButton".equals(action)) {
            String timeSelect1 = req.getParameter("timeSelect1");
            String timeSelect2 = req.getParameter("timeSelect2");
            String timeSelect3 = req.getParameter("timeSelect3");
            String timeSelect4 = req.getParameter("timeSelect4");
            String tablecount = req.getParameter("tablecount");
            String orderlimit = req.getParameter("orderlimit");
            // ??????????????????id
            HttpSession session = req.getSession();
            Integer storeid = (Integer) session.getAttribute("storeId");
            String errortimeMsgs = "";
            String errortablecountMsgs = "";
            String errororderlimitMsgs = "";
            String temp = timeSelect1 + timeSelect2 + timeSelect3 + timeSelect4;
            if ("0000".equals(temp)) {
                errortimeMsgs = errortimeMsgs + "???????????????????????????!";
            }
            ArrayList timestrArrayList = new ArrayList();
            timestrArrayList.add(timeSelect1);
            timestrArrayList.add(timeSelect2);
            timestrArrayList.add(timeSelect3);
            timestrArrayList.add(timeSelect4);
            Integer tablecountTemp = null;
            try {
                if (Integer.parseInt(tablecount) <= 0) {
                    errortablecountMsgs = errortablecountMsgs + "?????????????????????0?????????!";
                    tablecountTemp = 0;
                } else {
                    tablecountTemp = Integer.parseInt(tablecount);
                }
            } catch (NumberFormatException e) {
                errortablecountMsgs = errortablecountMsgs + "?????????????????????!";
                tablecountTemp = 0;
            }
            Integer orderlimitTemp = null;
            try {
                if (Integer.parseInt(orderlimit) <= 0) {
                    errororderlimitMsgs = errororderlimitMsgs + "?????????????????????0?????????!";
                    orderlimitTemp = 0;
                } else {
                    orderlimitTemp = Integer.parseInt(orderlimit);
                }
            } catch (NumberFormatException e) {
                errororderlimitMsgs = errororderlimitMsgs + "?????????????????????!";
                orderlimitTemp = 0;
            }
            if (errortablecountMsgs != "" || errororderlimitMsgs != "" || errortimeMsgs != "") {
                req.setAttribute("errortablecountMsgs", errortablecountMsgs);
                req.setAttribute("errororderlimitMsgs", errororderlimitMsgs);
                req.setAttribute("errortimeMsgs", errortimeMsgs);
                // ?????????????????? old???????????????
                req.setAttribute("tablecount", tablecount);
                req.setAttribute("orderlimit", orderlimit);
                req.setAttribute("timestrArrayList", timestrArrayList);
                req.setAttribute("isShowinfo", 0);
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/front-end/store/food_order/setFoodOrderInfo.jsp");
                failureView.forward(req, res);
                return; // ????????????
            }
            // *****?????????????????????????????????????????????????????????????????????****
            // ?????????
            Set<String> set = new LinkedHashSet<>(timestrArrayList);
            timestrArrayList = new ArrayList<>(set);
            String tempstr = "";
            for (int i = 0; i < timestrArrayList.size(); i++) {
                if (Integer.parseInt((String) timestrArrayList.get(i)) > 0) {
                    tempstr = tempstr + timestrArrayList.get(i) + ":00, ";
                }
            }
            tempstr = tempstr.substring(0, tempstr.length() - 2);
            // ?????????????????????
            // ??????service
            FoodorderService foodorderSvc = new FoodorderService();
            foodorderSvc.updateStoreInfo(storeid, tablecount, orderlimit, tempstr);
            /*************************** 3.????????????,????????????(Send the Success view) *************/
            // ??????????????????????????????????????? ?????????????????????
            List<Meal> list = foodorderSvc.getAllStatusOk(storeid);
            req.setAttribute("list", list); // ?????????update?????????,????????????empVO??????,??????req
            // 2.??????????????????
            Store storeInfo = foodorderSvc.getStoreInfo(storeid);
            String newtimestr = "?????????";
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
            RequestDispatcher successView = req.getRequestDispatcher(url);// ???????????????,????????????????????????????????????
            successView.forward(req, res);
        }
        /// member
        if ("Member_order_button".equals(action)) {
            // ????????????id?????????????????????????????????session??????????????????????????????????????????
            // ?????????????????????set?????? ???get?????? ?????????????????????set??? *??????set?????????*
            HttpSession session = req.getSession();
            Integer storeid = Integer.parseInt(req.getParameter("foodorder_storeId"));
            session.setAttribute("foodorder_storeId", storeid);
            // ??????service
            FoodorderService foodorderSvc = new FoodorderService();
            Store storeInfo = foodorderSvc.getStoreInfo(storeid);
            if (storeInfo.getStoreEtime() != null) {
                String[] split = storeInfo.getStoreEtime().split(", ");
                req.setAttribute("storetime", split);
            }
            req.setAttribute("storename", storeInfo.getStoreName());
            String url = "/front-end/Member/food_order/setFoodOrderInfo.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// ???????????????,????????????????????????????????????
            successView.forward(req, res);
        }
        // ??????????????????????????????
        if ("Member_order_to_meal".equals(action)) {
            HttpSession session = req.getSession();
            Integer storeid = (Integer) session.getAttribute("foodorder_storeId");
            // ??????????????????
            String orderShopName = req.getParameter("order_shop_name");
            String nameInput = req.getParameter("nameInput");
            String phoneInput = req.getParameter("phoneInput");
            String peopleNum1 = req.getParameter("peopleNum1");
            String dateInput = req.getParameter("dateInput");
            String time1 = req.getParameter("time1");
            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();

            String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (nameInput == null || nameInput.trim().length() == 0) {
                errorMsgs.put("name", "????????????!");
            } else if (!nameInput.trim().matches(nameReg)) { //??????????????????(???)?????????(regular-expression)
                errorMsgs.put("name", "???????????????????????????????????????_ , ??????????????????2???10??????!");
            }
            String phoneReg = "^09[\\d]{8}$";
            if (phoneInput == null || phoneInput.trim().length() == 0) {
                errorMsgs.put("phone", "????????????!");
            } else if (!phoneInput.trim().matches(phoneReg)) { //??????????????????(???)?????????(regular-expression)
                errorMsgs.put("phone", "???????????????????????????!");
            }
            if (peopleNum1 == null || "0".equals(peopleNum1)) {
                errorMsgs.put("peopleNum1", "?????????!");
            }
            String dateInputReg = "^[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]$";
            if (dateInput == null || "".equals(dateInput)) {
                errorMsgs.put("dateInput", "????????????!");
            } else if (!dateInput.trim().matches(dateInputReg)) { //??????????????????(???)?????????(regular-expression)
                errorMsgs.put("dateInput", "???????????????????????????!");
            }
            if (time1 == null || "0".equals(time1)) {
                errorMsgs.put("time1", "?????????!");
            }
            if (!errorMsgs.isEmpty()) {
                FoodorderService foodorderSvc = new FoodorderService();
                Store storeInfo = foodorderSvc.getStoreInfo(storeid);
                if (storeInfo.getStoreEtime() != null) {
                    String[] split = storeInfo.getStoreEtime().split(", ");
                    req.setAttribute("storetime", split);
                }
                req.setAttribute("nameInput", nameInput);
                req.setAttribute("phoneInput", phoneInput);
                req.setAttribute("storename", orderShopName);
                req.setAttribute("peopleNum1", peopleNum1);
                req.setAttribute("dateInput", dateInput);
                req.setAttribute("time1", time1);
                req.setAttribute("errorMsgs", errorMsgs);
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Member/food_order/setFoodOrderInfo.jsp");
                failureView.forward(req, res);
                return; // ????????????
            }
            // ??????????????????????????????????????????????????????????????????????????????????????? ??????????????????
            // ??????service
            FoodorderService foodorderSvc = new FoodorderService();
            // ???id + ?????? +??????+???????????????????????????(??????????????????????????????????????? ??????????????????) ????????? ??????
            List<Reserva> reservabyStoreidRendate = foodorderSvc.getReservabyStoreidRendate(storeid, dateInput,
                    time1 + ":00", 0);
            List<Reserva> reservabyStoreidRendate2 = foodorderSvc.getReservabyStoreidRendate(storeid, dateInput,
                    time1 + ":00", 2);
            Store storeInfo = foodorderSvc.getStoreInfo(storeid);
            // ???????????????????????????
            Integer limit = 0;
            if (storeInfo.getStoreEtable() != null) {
                limit = storeInfo.getStoreEtable();
            }
            if ((reservabyStoreidRendate.size() + reservabyStoreidRendate2.size()) >= limit) {
                errorMsgs.put("overlimit", "????????????????????????????????????????????????!");
                storeInfo = foodorderSvc.getStoreInfo(storeid);
                if (storeInfo.getStoreEtime() != null) {
                    String[] split = storeInfo.getStoreEtime().split(", ");
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
                return; // ????????????
            }
            // ???????????????  ?????????????????????session ??????????????????????????? ?????????????????????????????? ????????? ?????? ??? ????????????
            session.setAttribute("foodorder_name", nameInput);
            session.setAttribute("foodorder_phone", phoneInput);
            session.setAttribute("foodorder_peopleNum", peopleNum1);
            session.setAttribute("foodorder_time", time1 + ":00");
            session.setAttribute("foodorder_date", dateInput);
            // ?????????????????????
            List<Meal> allMealbyStoreidStatus = foodorderSvc.getAllMealbyStoreidStatus(storeid, 1);
            req.setAttribute("list", allMealbyStoreidStatus);
            // ???????????????????????????????????????  ???"???"????????????  ???????????????????????????
            if (allMealbyStoreidStatus.size() == 0) {
                errorMsgs.put("meal", "????????????????????????");
                storeInfo = foodorderSvc.getStoreInfo(storeid);
                if (storeInfo.getStoreEtime() != null) {
                    String[] split = storeInfo.getStoreEtime().split(", ");
                    req.setAttribute("storetime", split);
                }
                req.setAttribute("nameInput", nameInput);
                req.setAttribute("phoneInput", phoneInput);
                req.setAttribute("storename", orderShopName);
                req.setAttribute("peopleNum1", peopleNum1);
                req.setAttribute("dateInput", dateInput);
                req.setAttribute("time1", time1);
                req.setAttribute("errorMsgs", errorMsgs);
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Member/food_order/setFoodOrderInfo.jsp");
                failureView.forward(req, res);
                return; // ????????????
            }
            String url = "/front-end/Member/food_order/orderFoodOrderPage2.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// ???????????????,????????????????????????????????????
            successView.forward(req, res);
        }
        // ????????????
        if ("Member_order_meal".equals(action)) {
            // ???????????????????????? [{mealId:2, mealName:??????1, rdQuantity:1, pdPrice:180},{mealId:3,
            // mealName:??????2, rdQuantity:1, pdPrice:180}]
            List<Map> mealListInfo = new ArrayList<Map>();
            String errorMealNumMsgs = "";
            HttpSession session = req.getSession();
            Integer storeid = (Integer) session.getAttribute("foodorder_storeId");
            Integer peopleNum = Integer.parseInt((String) session.getAttribute("foodorder_peopleNum"));
            // ?????????
            String codeinput = req.getParameter("codeinput");
            session.setAttribute("foodorder_code", codeinput);
            // ??????service
            FoodorderService foodorderSvc = new FoodorderService();
            List<Meal> allMealbyStoreidStatus = foodorderSvc.getAllMealbyStoreidStatus(storeid, 1);
            // ??????????????????????????? ???????????????????????????
            Integer total = 0;
            for (int i = 0; i < allMealbyStoreidStatus.size(); i++) {
                total = total
                        + Integer.parseInt(req.getParameter(String.valueOf(allMealbyStoreidStatus.get(i).getMealId())));
            }
            if (total < peopleNum) {
                errorMealNumMsgs = errorMealNumMsgs + "?????????????????????!??????" + peopleNum + "???";
                req.setAttribute("list", allMealbyStoreidStatus);
                req.setAttribute("errorMealNumMsgs", errorMealNumMsgs);
                req.setAttribute("codeinput", codeinput);
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/front-end/Member/food_order/orderFoodOrderPage2.jsp");
                failureView.forward(req, res);
                return; // ????????????
            }
            Integer totalMoney = 0;
            for (int i = 0; i < allMealbyStoreidStatus.size(); i++) {
                Map oneMeal = new LinkedHashMap();
                oneMeal.put("mealId", allMealbyStoreidStatus.get(i).getMealId());
                oneMeal.put("mealName", allMealbyStoreidStatus.get(i).getMealName());
                oneMeal.put("rdQuantity", Integer.parseInt(req.getParameter(String.valueOf(allMealbyStoreidStatus.get(i).getMealId()))));
                oneMeal.put("pdPrice", allMealbyStoreidStatus.get(i).getMealPrice());
                mealListInfo.add(oneMeal);
                totalMoney = totalMoney + Integer.parseInt(req.getParameter(String.valueOf(allMealbyStoreidStatus.get(i).getMealId()))) * allMealbyStoreidStatus.get(i).getMealPrice();
            }
            // ?????????????????????
            //1. ????????????
            Store storeInfo = foodorderSvc.getStoreInfo(storeid);
            req.setAttribute("storeName", storeInfo.getStoreName());
            //2. ??????????????? and ???????????????
            List<Integer> codeMoneyList = foodorderSvc.getCodeMoney(req.getParameter("codeinput"), storeid);
            Integer codeMoney = 0;
            Integer codeId = null;
            if (codeMoneyList.size() != 0) {
                codeMoney = codeMoneyList.get(0);
                codeId = foodorderSvc.getCodeId(req.getParameter("codeinput"), storeid).get(0);
            }
            session.setAttribute("foodorder_codeMoney", codeMoney);
            session.setAttribute("foodorder_codeId", codeId);
            //3.?????????
            session.setAttribute("foodorder_totalMoney", totalMoney);
            //4.????????????
            Integer totalCodeMoney = 0;
            if (totalMoney - codeMoney > 0) {
                totalCodeMoney = totalMoney - codeMoney;
            }
            session.setAttribute("foodorder_totalCodeMoney", totalCodeMoney);
            //5.????????????
            session.setAttribute("foodorder_mealList", mealListInfo);
            // ??????????????????
            String url = "/front-end/Member/food_order/checkout.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// ???????????????,????????????????????????????????????
            successView.forward(req, res);
        }
        if ("checkout".equals(action)) {
            String url = "/front-end/Member/food_order/checkout2.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        if ("ecpay".equals(action)) {
            // ?????????????????????????????? (?????????????????????)
            // ?????????????????????????????????????????????
            // ????????????????????????..... (????????????!)
            // ????????????: ??????????????????????????? : 4311-9522-2222-2222 ????????? : 222
            // ????????????????????????/??????????????? MM/YYYY ??????????????????????????????????????????
            // ????????? 2016/04/20 ??????????????????????????? 05/2016(???)???????????????????????????????????????????????????
            // ?????????????????????????????????????????????
            // ????????????: ??????????????? - ???????????? - ??????
            domain = new AllInOne("");
            AioCheckOutOneTime obj = new AioCheckOutOneTime();
            // ??? view ????????????????????? https://developers.ecpay.com.tw/?p=2866 ?????????????????????
            // MerchantTradeNo  : ?????? ?????????????????? (???????????????????????????????????????)
            obj.setMerchantTradeNo(new String("salon" + System.currentTimeMillis()));
            // MerchantTradeDate  : ?????? ?????????????????? yyyy/MM/dd HH:mm:ss
            obj.setMerchantTradeDate(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date()));
            // TotalAmount  : ?????? ????????????
            Integer money = (Integer) req.getSession().getAttribute("foodorder_totalCodeMoney");
            obj.setTotalAmount(String.valueOf(money));
            // TradeDesc  : ?????? ????????????
            Integer sID = (Integer) req.getSession().getAttribute("foodorder_storeId");
            obj.setTradeDesc("StoreID:" + sID);
            // ItemName  : ?????? ????????????
            obj.setItemName("FoodMap Buy Eat");
            // ReturnURL   : ??????  ?????????????????????????????????????????????
            obj.setReturnURL("a");
            // OrderResultURL   : ?????? ????????????????????????????????????????????????
            String url = "http://localhost:8081/CGA105G2/front-end/Member/food_order/food_order.do?action=checkout_final";
            obj.setOrderResultURL(url);
            obj.setNeedExtraPaidInfo("N");
            // ??????form?????? ??????????????????????????? ??????
            String form = domain.aioCheckOut(obj, null);
            res.setCharacterEncoding("UTF-8");
            res.getWriter().print("<html><body>" + form + "</body></html>");
        }
        if ("checkout_final".equals(action)) {
            HttpSession session = req.getSession();
            Integer storeid = (Integer) session.getAttribute("foodorder_storeId");
            Integer memid = (Integer) session.getAttribute("memId");
            String name = (String) session.getAttribute("foodorder_name");
            String phone = (String) session.getAttribute("foodorder_phone");
            Integer peopleNum = Integer.parseInt((String) session.getAttribute("foodorder_peopleNum"));
            String time = (String) session.getAttribute("foodorder_time");
            String date = (String) session.getAttribute("foodorder_date");
            Integer codeMoney = (Integer) session.getAttribute("foodorder_codeMoney");
            Integer codeId = null;
            if (session.getAttribute("foodorder_codeId") != null) {
                codeId = (Integer) session.getAttribute("foodorder_codeId");
            }
            Integer totalMoney = (Integer) session.getAttribute("foodorder_totalMoney");
            Integer totalCodeMoney = (Integer) session.getAttribute("foodorder_totalCodeMoney");
            List<Map> mealList = (List<Map>) session.getAttribute("foodorder_mealList");
            // ??????service
            FoodorderService foodorderSvc = new FoodorderService();
            // ??????rdQuantity???0?????????
            List<Map> mealListTemp = new ArrayList<Map>();
            for (int i = 0; i < mealList.size(); i++) {
                if ((Integer) mealList.get(i).get("rdQuantity") != 0) {
                    mealListTemp.add(mealList.get(i));
                }
            }
            mealList = mealListTemp;
            if (session.getAttribute("foodorder_codeId") == null) {
                Reserva reserva = new Reserva(storeid, memid, name, phone, time, Date.valueOf(date), peopleNum,
                        totalMoney, totalCodeMoney);
                foodorderSvc.insertReservaReservaDetail(reserva, mealList);
            } else {
                Integer codeid = (Integer) session.getAttribute("foodorder_codeId");
                Reserva reserva = new Reserva(storeid, memid, name, phone, time, Date.valueOf(date), peopleNum, codeid,
                        totalMoney, totalCodeMoney);
                foodorderSvc.insertReservaReservaDetail(reserva, mealList);
            }
            String url = "/front-end/Member/food_order/food_order.do?action=listAllFoodOrder";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        //member ??????????????????
        if ("listAllFoodOrder".equals(action)) {
            HttpSession session = req.getSession();
            // ?????????????????????
            Integer memid = (Integer) session.getAttribute("memId");
            // ????????????
            // ??????service
            FoodorderService foodorderSvc = new FoodorderService();
            List<Reserva> reservaList = foodorderSvc.getBymemIdrenStatus(memid, 0);
            List<Map> foodorderListInfo = new ArrayList<Map>();
            if (reservaList.size() > 0) {
                for (int i = 0; i < reservaList.size(); i++) {
                    Map onedata = new LinkedHashMap();
                    onedata.put("REN_ID", reservaList.get(i).getRenId());
                    onedata.put("REN_NAME", reservaList.get(i).getRenName());
                    onedata.put("REN_PHONE", reservaList.get(i).getRenPhone());
                    onedata.put("REN_TIME", reservaList.get(i).getRenTime());
                    onedata.put("REN_STATUS", "?????????");
                    onedata.put("REN_DATE", reservaList.get(i).getRenDate());
                    onedata.put("REN_HEADCOUNT", reservaList.get(i).getRenHeadcount());
                    onedata.put("REN_FPRICE", reservaList.get(i).getRenFprice());
                    Store storeInfo = foodorderSvc.getStoreInfo(reservaList.get(i).getStoreId());
                    onedata.put("STORE_NAME", storeInfo.getStoreName());
                    List<ReservaDetail> reservaDetail = foodorderSvc.getByPK(reservaList.get(i).getRenId(), "renId");
                    String mealStringTemp = "";
                    for (int j = 0; j < reservaDetail.size(); j++) {
                        Integer mealId = reservaDetail.get(j).getMealId();
                        Integer rdQuantity = reservaDetail.get(j).getRdQuantity();
                        Meal oneMeal = foodorderSvc.getByMealId(mealId);
                        String oneMealName = oneMeal.getMealName();
                        mealStringTemp = mealStringTemp + oneMealName + "*" + rdQuantity + ", ";
                    }
                    mealStringTemp = mealStringTemp.substring(0, mealStringTemp.length() - 2);
                    onedata.put("MEAL_NAME_LIST", mealStringTemp);
                    foodorderListInfo.add(onedata);
                }
            }
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            String listJson = gson.toJson(foodorderListInfo);
            req.setAttribute("foodorderListInfo", listJson);
            // ?????????????????????
            String url = "/front-end/Member/food_order/listAllFoodOrder.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// ???????????????,????????????????????????????????????
            successView.forward(req, res);
        }
        if ("Member_order_delete_button".equals(action)) {
            String deleteRenid = req.getParameter("deleteid");
            // ??????service ??????????????????
            FoodorderService foodorderSvc = new FoodorderService();
            foodorderSvc.updaterenStatusByrenId(Integer.parseInt(deleteRenid), 1);
            // ??????????????????
            HttpSession session = req.getSession();
            Integer memid = (Integer) session.getAttribute("memId");
            List<Reserva> reservaList = foodorderSvc.getBymemIdrenStatus(memid, 0);
            List<Map> foodorderListInfo = new ArrayList<Map>();
            if (reservaList.size() > 0) {
                for (int i = 0; i < reservaList.size(); i++) {
                    Map onedata = new LinkedHashMap();
                    onedata.put("REN_ID", reservaList.get(i).getRenId());
                    onedata.put("REN_NAME", reservaList.get(i).getRenName());
                    onedata.put("REN_PHONE", reservaList.get(i).getRenPhone());
                    onedata.put("REN_TIME", reservaList.get(i).getRenTime());
                    onedata.put("REN_STATUS", "?????????");
                    onedata.put("REN_DATE", reservaList.get(i).getRenDate());
                    onedata.put("REN_HEADCOUNT", reservaList.get(i).getRenHeadcount());
                    onedata.put("REN_FPRICE", reservaList.get(i).getRenFprice());
                    Store storeInfo = foodorderSvc.getStoreInfo(reservaList.get(i).getStoreId());
                    onedata.put("STORE_NAME", storeInfo.getStoreName());
                    List<ReservaDetail> reservaDetail = foodorderSvc.getByPK(reservaList.get(i).getRenId(), "renId");
                    String mealStringTemp = "";
                    for (int j = 0; j < reservaDetail.size(); j++) {
                        Integer mealId = reservaDetail.get(j).getMealId();
                        Integer rdQuantity = reservaDetail.get(j).getRdQuantity();
                        Meal oneMeal = foodorderSvc.getByMealId(mealId);
                        String oneMealName = oneMeal.getMealName();
                        mealStringTemp = mealStringTemp + oneMealName + "*" + rdQuantity + ", ";
                    }
                    mealStringTemp = mealStringTemp.substring(0, mealStringTemp.length() - 2);
                    onedata.put("MEAL_NAME_LIST", mealStringTemp);
                    foodorderListInfo.add(onedata);
                }
            }
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            String listJson = gson.toJson(foodorderListInfo);
            req.setAttribute("foodorderListInfo", listJson);
            // ?????????????????????
            String url = "/front-end/Member/food_order/listAllFoodOrder.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// ???????????????,????????????????????????????????????
            successView.forward(req, res);
        }
        if ("listFoodOrderScore".equals(action)) {
            HttpSession session = req.getSession();
            // ?????????????????????
            Integer memid = (Integer) session.getAttribute("memId");
            // ????????????
            // ??????service
            FoodorderService foodorderSvc = new FoodorderService();
            List<Reserva> reservaList = foodorderSvc.getReservaBymemId(memid);
            List<Map> foodorderListInfo = new ArrayList<Map>();
            if (reservaList.size() > 0) {
                for (int i = 0; i < reservaList.size(); i++) {
                    Map onedata = new LinkedHashMap();
                    onedata.put("REN_ID", reservaList.get(i).getRenId());
                    onedata.put("REN_NAME", reservaList.get(i).getRenName());
                    onedata.put("REN_PHONE", reservaList.get(i).getRenPhone());
                    onedata.put("REN_TIME", reservaList.get(i).getRenTime());
                    onedata.put("REN_DATE", reservaList.get(i).getRenDate());
                    onedata.put("REN_HEADCOUNT", reservaList.get(i).getRenHeadcount());
                    onedata.put("REN_FPRICE", reservaList.get(i).getRenFprice());
                    Store storeInfo = foodorderSvc.getStoreInfo(reservaList.get(i).getStoreId());
                    onedata.put("STORE_NAME", storeInfo.getStoreName());
                    // ??????????????????
                    String reservaStringStatus = "";
                    if (reservaList.get(i).getRenStatus() == 0) {
                        reservaStringStatus = "?????????";
                    } else if (reservaList.get(i).getRenStatus() == 1) {
                        reservaStringStatus = "??????";
                    } else if (reservaList.get(i).getRenStatus() == 2) {
                        reservaStringStatus = "??????";
                    } else if (reservaList.get(i).getRenStatus() == 3) {
                        reservaStringStatus = "??????";
                    }
                    onedata.put("REN_STATUS", reservaStringStatus);
                    // ????????????????????????????????????
                    //1. ????????????????????????????????????????????????????????????
                    if ("??????".equals(reservaStringStatus)) {
                        List<Article> articleByMemIdStoreId = foodorderSvc.getArticleByMemIdStoreId(memid, reservaList.get(i).getStoreId());
                        if (articleByMemIdStoreId.size() > 0) {
                            Timestamp artTime = articleByMemIdStoreId.get(articleByMemIdStoreId.size() - 1).getArtTime();
                            if (artTime.before(reservaList.get(i).getRenCurdate())) {
                                onedata.put("SCORE_INPUT", "1");
                            } else {
                                onedata.put("SCORE_INPUT", "0");
                            }
                        } else {
                            onedata.put("SCORE_INPUT", "1");
                        }
                    } else {
                        onedata.put("SCORE_INPUT", "0");
                    }
                    List<ReservaDetail> reservaDetail = foodorderSvc.getByPK(reservaList.get(i).getRenId(), "renId");
                    String mealStringTemp = "";
                    // ????????????????????????????????????
                    for (int j = 0; j < reservaDetail.size(); j++) {
                        Integer mealId = reservaDetail.get(j).getMealId();
                        Integer rdQuantity = reservaDetail.get(j).getRdQuantity();
                        Meal oneMeal = foodorderSvc.getByMealId(mealId);
                        String oneMealName = oneMeal.getMealName();
                        mealStringTemp = mealStringTemp + oneMealName + "*" + rdQuantity + ", ";
                    }
                    mealStringTemp = mealStringTemp.substring(0, mealStringTemp.length() - 2);
                    onedata.put("MEAL_NAME_LIST", mealStringTemp);
                    onedata.put("MEM_ID", reservaList.get(i).getMemId());
                    onedata.put("STORE_ID", reservaList.get(i).getStoreId());
                    foodorderListInfo.add(onedata);
                }
            }
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            String listJson = gson.toJson(foodorderListInfo);
            req.setAttribute("foodorderListInfo", listJson);
            // ?????????????????????
            String url = "/front-end/Member/food_order/listFoodOrderScore.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// ???????????????,????????????????????????????????????
            successView.forward(req, res);
        }
        ////store ?????????????????? ////////////////////
        if ("storelistAllFoodOrderReserve".equals(action)) {
            HttpSession session = req.getSession();
            // ????????????????????? ??????????????????
            Integer storeid = (Integer) session.getAttribute("storeId");
            // ????????????
            // ??????service
            FoodorderService foodorderSvc = new FoodorderService();
            List<Reserva> reservaList = foodorderSvc.getBystoreIdrenStatus(storeid, 0);
            List<Map> foodorderListInfo = new ArrayList<Map>();
            if (reservaList.size() > 0) {
                for (int i = 0; i < reservaList.size(); i++) {
                    Map onedata = new LinkedHashMap();
                    onedata.put("REN_ID", reservaList.get(i).getRenId());
                    onedata.put("REN_NAME", reservaList.get(i).getRenName());
                    onedata.put("REN_PHONE", reservaList.get(i).getRenPhone());
                    onedata.put("REN_TIME", reservaList.get(i).getRenTime());
                    onedata.put("REN_STATUS", "?????????");
                    onedata.put("REN_DATE", reservaList.get(i).getRenDate());
                    onedata.put("REN_HEADCOUNT", reservaList.get(i).getRenHeadcount());
                    onedata.put("REN_FPRICE", reservaList.get(i).getRenFprice());
                    List<ReservaDetail> reservaDetail = foodorderSvc.getByPK(reservaList.get(i).getRenId(), "renId");
                    String mealStringTemp = "";
                    for (int j = 0; j < reservaDetail.size(); j++) {
                        Integer mealId = reservaDetail.get(j).getMealId();
                        Integer rdQuantity = reservaDetail.get(j).getRdQuantity();
                        Meal oneMeal = foodorderSvc.getByMealId(mealId);
                        String oneMealName = oneMeal.getMealName();
                        mealStringTemp = mealStringTemp + oneMealName + "*" + rdQuantity + ", ";
                    }
                    mealStringTemp = mealStringTemp.substring(0, mealStringTemp.length() - 2);
                    onedata.put("MEAL_NAME_LIST", mealStringTemp);
                    foodorderListInfo.add(onedata);
                    Member memberInfo = foodorderSvc.getmemberById(reservaList.get(i).getMemId());
                    onedata.put("MEM_NAME", memberInfo.getMemName());
                    onedata.put("MEM_PHONE", memberInfo.getMemPhone());
                }
            }
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            String listJson = gson.toJson(foodorderListInfo);
            req.setAttribute("foodorderListInfo", listJson);
            // ?????????????????????
            String url = "/front-end/store/food_order/listAllFoodOrderReserve.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// ???????????????,????????????????????????????????????
            successView.forward(req, res);
        }
        if ("storelistAllFoodOrder".equals(action)) {
            HttpSession session = req.getSession();
            // ????????????????????? ??????????????????
            Integer storeid = (Integer) session.getAttribute("storeId");
            // ????????????
            // ??????service
            FoodorderService foodorderSvc = new FoodorderService();
            List<Reserva> reservaList = foodorderSvc.getReservaBystoreId(storeid);
            List<Map> foodorderListInfo = new ArrayList<Map>();
            if (reservaList.size() > 0) {
                for (int i = 0; i < reservaList.size(); i++) {
                    Map onedata = new LinkedHashMap();
                    onedata.put("REN_ID", reservaList.get(i).getRenId());
                    onedata.put("REN_NAME", reservaList.get(i).getRenName());
                    onedata.put("REN_PHONE", reservaList.get(i).getRenPhone());
                    onedata.put("REN_TIME", reservaList.get(i).getRenTime());
                    onedata.put("REN_DATE", reservaList.get(i).getRenDate());
                    onedata.put("REN_HEADCOUNT", reservaList.get(i).getRenHeadcount());
                    onedata.put("REN_FPRICE", reservaList.get(i).getRenFprice());
                    // ??????????????????
                    String reservaStringStatus = "";
                    if (reservaList.get(i).getRenStatus() == 0) {
                        reservaStringStatus = "?????????";
                    } else if (reservaList.get(i).getRenStatus() == 1) {
                        reservaStringStatus = "??????";
                    } else if (reservaList.get(i).getRenStatus() == 2) {
                        reservaStringStatus = "??????";
                    } else if (reservaList.get(i).getRenStatus() == 3) {
                        reservaStringStatus = "??????";
                    }
                    onedata.put("REN_STATUS", reservaStringStatus);
                    List<ReservaDetail> reservaDetail = foodorderSvc.getByPK(reservaList.get(i).getRenId(), "renId");
                    String mealStringTemp = "";
                    for (int j = 0; j < reservaDetail.size(); j++) {
                        Integer mealId = reservaDetail.get(j).getMealId();
                        Integer rdQuantity = reservaDetail.get(j).getRdQuantity();
                        Meal oneMeal = foodorderSvc.getByMealId(mealId);
                        String oneMealName = oneMeal.getMealName();
                        mealStringTemp = mealStringTemp + oneMealName + "*" + rdQuantity + ", ";
                    }
                    mealStringTemp = mealStringTemp.substring(0, mealStringTemp.length() - 2);
                    onedata.put("MEAL_NAME_LIST", mealStringTemp);
                    foodorderListInfo.add(onedata);
                    Member memberInfo = foodorderSvc.getmemberById(reservaList.get(i).getMemId());
                    onedata.put("MEM_NAME", memberInfo.getMemName());
                    onedata.put("MEM_PHONE", memberInfo.getMemPhone());
                }
            }
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            String listJson = gson.toJson(foodorderListInfo);
            req.setAttribute("foodorderListInfo", listJson);
            // ?????????????????????
            String url = "/front-end/store/food_order/listAllFoodOrder.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// ???????????????,????????????????????????????????????
            successView.forward(req, res);
        }
        if ("Store_order_delete_button".equals(action)) {
            String deleteRenid = req.getParameter("deleteid");
            // ??????service ??????????????????
            FoodorderService foodorderSvc = new FoodorderService();
            foodorderSvc.updaterenStatusByrenId(Integer.parseInt(deleteRenid), 1);
            // ??????????????????
            HttpSession session = req.getSession();
            Integer storeid = (Integer) session.getAttribute("storeId");
            // ????????????
            // ??????service
            List<Reserva> reservaList = foodorderSvc.getBystoreIdrenStatus(storeid, 0);
            List<Map> foodorderListInfo = new ArrayList<Map>();
            if (reservaList.size() > 0) {
                for (int i = 0; i < reservaList.size(); i++) {
                    Map onedata = new LinkedHashMap();
                    onedata.put("REN_ID", reservaList.get(i).getRenId());
                    onedata.put("REN_NAME", reservaList.get(i).getRenName());
                    onedata.put("REN_PHONE", reservaList.get(i).getRenPhone());
                    onedata.put("REN_TIME", reservaList.get(i).getRenTime());
                    onedata.put("REN_STATUS", "?????????");
                    onedata.put("REN_DATE", reservaList.get(i).getRenDate());
                    onedata.put("REN_HEADCOUNT", reservaList.get(i).getRenHeadcount());
                    onedata.put("REN_FPRICE", reservaList.get(i).getRenFprice());
                    List<ReservaDetail> reservaDetail = foodorderSvc.getByPK(reservaList.get(i).getRenId(), "renId");
                    String mealStringTemp = "";
                    for (int j = 0; j < reservaDetail.size(); j++) {
                        Integer mealId = reservaDetail.get(j).getMealId();
                        Integer rdQuantity = reservaDetail.get(j).getRdQuantity();
                        Meal oneMeal = foodorderSvc.getByMealId(mealId);
                        String oneMealName = oneMeal.getMealName();
                        mealStringTemp = mealStringTemp + oneMealName + "*" + rdQuantity + ", ";
                    }
                    mealStringTemp = mealStringTemp.substring(0, mealStringTemp.length() - 2);
                    onedata.put("MEAL_NAME_LIST", mealStringTemp);
                    foodorderListInfo.add(onedata);
                    Member memberInfo = foodorderSvc.getmemberById(reservaList.get(i).getMemId());
                    onedata.put("MEM_NAME", memberInfo.getMemName());
                    onedata.put("MEM_PHONE", memberInfo.getMemPhone());
                }
            }
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            String listJson = gson.toJson(foodorderListInfo);
            req.setAttribute("foodorderListInfo", listJson);
            // ?????????????????????
            String url = "/front-end/store/food_order/listAllFoodOrderReserve.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// ???????????????,????????????????????????????????????
            successView.forward(req, res);
        }
    }
}

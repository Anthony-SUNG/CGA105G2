<%@page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.Timestamp" %>
<%@ page import="com.waiting.model.service.StandbyService" %>
<%@ page import="com.waiting.model.pojo.Standby" %>


<%--<%--%>
<%--	StandbyService standbySvc = new StandbyService();--%>
<%--	List<Standby> list = standbySvc.getAll();--%>

<%--	pageContext.setAttribute("list", list);--%>
<%--%>--%>


<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <title>店家首頁</title>
    <!-- Bootstrap css123 -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
          crossorigin="anonymous"/>
    <!-- jquery 3.4.1  css -->
    <link rel="stylesheet" href="/CGA105G2/resources/demos/style.css">

    <link rel="stylesheet" href="/CGA105G2/assets/css/vendor.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/css/style.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/custom.css">
    <link rel="stylesheet"
          href="/CGA105G2/assets/fonts/font-awesome/css/font-awesome.css"/>
    <!-- <link rel="stylesheet" href="/CGA105G2/src/main/webapp/assets/css/carousel.css" /> -->

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <link
            href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"
            rel="stylesheet" type="text/css">


    <style>
        a {
            color: black;
        }
    </style>
</head>

<body>
<!-- header start -->
<%@ include file="/front-end/store/01h/headeronly.jsp" %>
<!-- header end -->

<!-- main -->
<div class="p-0">
    <!--        篩選-->
    <div class="d-flex flex-nowrap p-0">
        <!--            日期-->
        <div class="btn-group btn-group-toggle p-0 flex-grow-1">
            <!--              today-->
            <label class="input-group-text col-2">
                <button class="btn btn-secondary form-control  p-0"
                        style="background-color: rgba(9, 148, 101, 0.42); color: white;"
                        id="today">Today
                </button>
            </label>
            <!--              date-->
            <label class="input-group-text col-4" for="datepicker"> <input
                    type="text" class="form-control  pl-5"
                    style="background-color: rgb(9, 148, 101, 0.42); color: white;"
                    id="datepicker" placeholder="yyyy-mm-dd">
            </label>
            <!--              time-->
            <label class="input-group-text col-2"> <select
                    class="btn btn-secondary form-control  p-0"
                    style="background-color: rgba(9, 148, 101, 0.42); color: white;"
                    id="Select01">
            </select>
            </label>
            <div class="input-group-text col-4">
                <p>訂位數：</p>
            </div>
        </div>

    </div>
    <!--        下文-->
    <div class="d-flex">
        <!--      第一欄    -->
        <div class="col-2 p-0 border border-right">
            <!--            上-->
            <div>
                <div class="input-group-text">
                    <p class="form-control pt-5"
                       style="background-color: rgba(253, 72, 72, 0.65); color: black;">訂位表</p>
                </div>

                <div class="btn-group btn-group-toggle" style="display: block"
                     id="tablecheck">
                    <div class="table-responsive ">
                        <table class="table table-striped m-0">
                            <thead>
                            <tr class="text-center">
                                <th class="col-5">電話</th>
                                <th>姓名</th>
                                <th class="pl-0">人數</th>
                            </tr>
                            </thead>
                            <tbody class="code_tbody">
                            </tbody>
                        </table>
                    </div>
                </div>
                <section class="section p-0 " id="faq1"
                         style="overflow-y: scroll; height: 300px">
                    <div class="section-content container">
                        <div class="row">
                            <div class="col-12 p-0">
                                <article class="faq p-0">
                                    <header class="faq-header" data-toggle="collapse"
                                            data-target="#faq1-item-1" aria-expanded="false">
                                        <table class="table table-striped m-0">
                                            <tbody class="code_tbody">
                                            <tr class="text-center">
                                                <td class="col-5 fs-3">0955555555</td>
                                                <td class="fs-3 ">王小姐</td>
                                                <td class="fs-3 pl-8">3位</td>
                                            </tr>


                                            </tbody>
                                        </table>
                                        <div class="faq-toggle" style="display: none">
                                            <i class="material-icons faq-toggle-closed">add</i> <i
                                                class="material-icons faq-toggle-open">remove</i>
                                        </div>
                                    </header>
                                    <div class="faq-body collapse" id="faq1-item-1">
                                        <div class="card-body d-flex flex-column p-5"
                                             data-mh="gift-vouchers">
                                            <select class="custom-select mb-5">
                                                <option selected>桌號</option>
                                                <option value="1">A1</option>
                                                <option value="2">A2</option>
                                                <option value="3">A3</option>
                                            </select>
                                            <div
                                                    class="d-flex align-items-center justify-content-between">
													<span class="text-dark">1 <span class="text-muted">×</span>
														Creeme Soup
													</span>
                                                <div class="price fs-6 text-black">
                                                    <div class="price-currency pr-0">$</div>
                                                    <div class="price-value">9</div>
                                                </div>
                                            </div>
                                            <div
                                                    class="d-flex align-items-center justify-content-between mt-3">
													<span class="text-dark">2 <span class="text-muted">×</span>
														Multi-greens Salad
													</span>
                                                <div class="price fs-6 text-black">
                                                    <div class="price-currency pr-0">$</div>
                                                    <div class="price-value">22</div>
                                                </div>
                                            </div>
                                            <div class="border-lighter border-top my-9"></div>
                                            <div
                                                    class="d-flex align-items-center justify-content-between mt-3">
                                                <span class="text-dark"><strong>TOTAL</strong></span>
                                                <div class="price fs-6 text-black">
                                                    <div class="price-currency pr-0">$</div>
                                                    <div class="price-value">34</div>
                                                </div>
                                            </div>
                                            <a href="#" class="btn btn-primary d-block mt-10">報到</a>
                                        </div>
                                    </div>
                                </article>
                            </div>
                        </div>
                    </div>
                </section>
            </div>


            <!--            下-->
            <div>
                <div class="input-group-text">
                    <p class="form-control pt-5"
                       style="background-color: rgb(255, 201, 123); color: black;">候位表</p>
                </div>
                <div class="p-0 flex-grow-1" id="tablenumber" style="display: none">
                    <div class="btn-group btn-group-toggle flex-grow-1"
                         data-toggle="buttons">
                        <label class="btn btn-secondary active" id="btmon"> <input
                                type="radio" name="options" id="option1">on
                        </label> <label class="btn btn-secondary" id="btmoff"> <input
                            type="radio" name="options" id="option2" checked>off
                    </label>
                    </div>
                </div>
                <div class="btn-group btn-group-toggle" style="display: none"
                     id="tablewait">
                    <div class="table-responsive ">
                        <table class="table table-striped m-0">
                            <thead>
                            <tr class="text-center">
                                <th class="col-5 fs-3">電話</th>
                                <th class="fs-3">姓名</th>
                                <th class="fs-3 ml-8">人數</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <section class="section p-0 " id="faq2"
                             style="overflow-y: scroll; height: 300px">
                        <div class="section-content container ">
                            <div class="row">
                                <div class="col-12 p-0">
                                    <article class="faq p-0">


                                        <c:forEach var="standbyVo" items="${list}">
                                            <header class="faq-header" data-toggle="collapse"
                                                    data-target="#faq2-item-${standbyVo.staId}"
                                                    aria-expanded="false">
                                                <table class="table table-striped m-0">
                                                    <tbody class="code_tbody">

                                                    <tr class="text-center">
                                                        <td class="col-5">${standbyVo.staPhone}</td>
                                                        <td>${standbyVo.staName}</td>
                                                        <td>${standbyVo.staNumber}位</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <div class="faq-toggle" style="display: none">
                                                    <i class="material-icons faq-toggle-closed">add</i> <i
                                                        class="material-icons faq-toggle-open">remove</i>
                                                </div>
                                            </header>
                                            <div class="faq-body collapse row mx-auto"
                                                 id="faq2-item-${standbyVo.staId}"
                                                 style="width: 100%; justify-content: center">
                                                <!-- 													<div class="radio-buttons-group" -->
                                                <!-- 														style="width: 100%; justify-content: center"> -->

                                                <form METHOD="post"
                                                      ACTION="<%=request.getContextPath()%>/standby">
                                                    <input type="submit" value="通知"
                                                           onclick="" class="btn btn-outline-info"
                                                           data-value="call"> <input type="hidden"
                                                                                     name="staId"
                                                                                     value="${standbyVo.staId}"> <input
                                                        type="hidden" name="staStatus"
                                                        value="${standbyVo.staStatus}"> <input
                                                        type="hidden" name="action" value="getOneUpdate">

                                                    <!-- 															<button class="btn btn-light selected" data-value="call">通知</button> -->
                                                </form>

                                                <form METHOD="post"
                                                      ACTION="<%=request.getContextPath()%>/standby">
                                                    <input class="btn btn-outline-info" data-value="come"
                                                           onclick="deletetStandby()" type="submit" value="報到d">
                                                    <input type="hidden" name="staId"
                                                           value="${standbyVo.staId}"> <input type="hidden"
                                                                                              name="action"
                                                                                              value="delete">

                                                    <!-- 															<button class="btn btn-light" data-value="come">報到</button> -->
                                                </form>
                                            </div>
                                            <!-- </div> -->
                                        </c:forEach>


                                    </article>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>


        <!--      第二欄    -->
        <div class="col-2 p-0 border border-left " style="z-index: 1;">
            <div class="input-group-text ">
                <p class="form-control pt-5 "
                   style="background-color: rgba(253, 72, 72, 0.65); color: black;">現場桌況</p>
            </div>
            <div class="btn-group btn-group-toggle" style="display: block">
                <div class="table-responsive ">
                    <table class="table table-striped m-0">
                        <thead>
                        <tr>
                            <th>桌號</th>
                            <th>姓名</th>
                            <th>人數</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <section class="section p-0 " id="faq3"
                     style="overflow-y: scroll; height: 600px">
                <div class="section-content container">
                    <div class="row">
                        <div class="col-12 p-0">
                            <article class="faq p-0">
                                <header class="faq-header" data-toggle="collapse"
                                        data-target="#faq3-item-1" aria-expanded="false">
                                    <table class="table table-striped m-0">
                                        <tbody class="code_tbody">
                                        <tr>
                                            <td>A1</td>
                                            <td>王小姐</td>
                                            <td>3位</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <div class="faq-toggle" style="display: none">
                                        <i class="material-icons faq-toggle-closed">add</i> <i
                                            class="material-icons faq-toggle-open">remove</i>
                                    </div>
                                </header>
                                <div class="faq-body collapse" id="faq3-item-1">
                                    <div class="card-body d-flex flex-column p-5"
                                         data-mh="gift-vouchers">
                                        <select class="custom-select mb-5">
                                            <option selected>桌號</option>
                                            <option value="1">A1</option>
                                            <option value="2">A2</option>
                                            <option value="3">A3</option>
                                        </select>
                                        <div
                                                class="d-flex align-items-center justify-content-between">
												<span class="text-dark">1 <span class="text-muted">×</span>
													Creeme Soup
												</span>
                                            <div class="price fs-6 text-black">
                                                <div class="price-currency pr-0">$</div>
                                                <div class="price-value">9</div>
                                            </div>
                                        </div>
                                        <div
                                                class="d-flex align-items-center justify-content-between mt-3">
												<span class="text-dark">2 <span class="text-muted">×</span>
													Multi-greens Salad
												</span>
                                            <div class="price fs-6 text-black">
                                                <div class="price-currency pr-0">$</div>
                                                <div class="price-value">22</div>
                                            </div>
                                        </div>
                                        <div class="border-lighter border-top my-9"></div>
                                        <div
                                                class="d-flex align-items-center justify-content-between mt-3">
                                            <span class="text-dark"><strong>TOTAL</strong></span>
                                            <div class="price fs-6 text-black">
                                                <div class="price-currency pr-0">$</div>
                                                <div class="price-value">34</div>
                                            </div>
                                        </div>
                                        <a href="#" class="btn btn-primary d-block mt-10">結帳</a>
                                    </div>
                                </div>
                            </article>
                        </div>
                    </div>
                </div>
                <div class="section-content container">
                    <div class="row">
                        <div class="col-12 p-0">
                            <article class="faq p-0">
                                <header class="faq-header" data-toggle="collapse"
                                        data-target="#faq2-item-2" aria-expanded="false">
                                    <table class="table table-striped m-0">
                                        <tbody class="code_tbody">
                                        <tr>
                                            <td>A1</td>
                                            <td>王小姐</td>
                                            <td>3位</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <div class="faq-toggle" style="display: none">
                                        <i class="material-icons faq-toggle-closed">add</i> <i
                                            class="material-icons faq-toggle-open">remove</i>
                                    </div>
                                </header>
                                <div class="faq-body collapse" id="faq2-item-2">
                                    <div class="card-body d-flex flex-column p-5"
                                         data-mh="gift-vouchers">
                                        <select class="custom-select mb-5">
                                            <option selected>桌號</option>
                                            <option value="1">A1</option>
                                            <option value="2">A2</option>
                                            <option value="3">A3</option>
                                        </select>
                                        <div
                                                class="d-flex align-items-center justify-content-between">
												<span class="text-dark">1 <span class="text-muted">×</span>
													Creeme Soup
												</span>
                                            <div class="price fs-6 text-black">
                                                <div class="price-currency pr-0">$</div>
                                                <div class="price-value">9</div>
                                            </div>
                                        </div>
                                        <div
                                                class="d-flex align-items-center justify-content-between mt-3">
												<span class="text-dark">2 <span class="text-muted">×</span>
													Multi-greens Salad
												</span>
                                            <div class="price fs-6 text-black">
                                                <div class="price-currency pr-0">$</div>
                                                <div class="price-value">22</div>
                                            </div>
                                        </div>
                                        <div class="border-lighter border-top my-9"></div>
                                        <div
                                                class="d-flex align-items-center justify-content-between mt-3">
                                            <span class="text-dark"><strong>TOTAL</strong></span>
                                            <div class="price fs-6 text-black">
                                                <div class="price-currency pr-0">$</div>
                                                <div class="price-value">34</div>
                                            </div>
                                        </div>
                                        <a href="#" class="btn btn-primary d-block mt-10">結帳</a>
                                    </div>
                                </div>
                            </article>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <!--      第三欄    -->
        <div class="col-8 p-0 ">
            <div class="d-flex"
                 style="flex-wrap: wrap; align-content: space-around; height: 100%">
                <div class="col-md-3 p-5">
                    <div class="card border-0" style="height: 150px !important;">
                        <div class="card-body p-19 p-md-20 d-flex"
                             style="border: 1px solid black">
                            <svg width="16" height="16" viewBox="0 0 16 16">
                                <path
                                        d="M13.902.334a.5.5 0 0 1-.28.65l-2.254.902-.4 1.927c.376.095.715.215.972.367.228.135.56.396.56.82 0 .046-.004.09-.011.132l-.962 9.068a1.28 1.28 0 0 1-.524.93c-.488.34-1.494.87-3.01.87-1.516 0-2.522-.53-3.01-.87a1.28 1.28 0 0 1-.524-.93L3.51 5.132A.78.78 0 0 1 3.5 5c0-.424.332-.685.56-.82.262-.154.607-.276.99-.372C5.824 3.614 6.867 3.5 8 3.5c.712 0 1.389.045 1.985.127l.464-2.215a.5.5 0 0 1 .303-.356l2.5-1a.5.5 0 0 1 .65.278zM9.768 4.607A13.991 13.991 0 0 0 8 4.5c-1.076 0-2.033.11-2.707.278A3.284 3.284 0 0 0 4.645 5c.146.073.362.15.648.222C5.967 5.39 6.924 5.5 8 5.5c.571 0 1.109-.03 1.588-.085l.18-.808zm.292 1.756C9.445 6.45 8.742 6.5 8 6.5c-1.133 0-2.176-.114-2.95-.308a5.514 5.514 0 0 1-.435-.127l.838 8.03c.013.121.06.186.102.215.357.249 1.168.69 2.438.69 1.27 0 2.081-.441 2.438-.69.042-.029.09-.094.102-.215l.852-8.03a5.517 5.517 0 0 1-.435.127 8.88 8.88 0 0 1-.89.17zM4.467 4.884s.003.002.005.006l-.005-.006zm7.066 0-.005.006c.002-.004.005-.006.005-.006zM11.354 5a3.174 3.174 0 0 0-.604-.21l-.099.445.055-.013c.286-.072.502-.149.648-.222z"/>
                            </svg>
                            <h3 class="fs-6 fs-lg-9">A1</h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 p-5">
                    <div class="card border-0" style="height: 150px !important;">
                        <div class="card-body p-19 p-md-20 d-flex"
                             style="border: 1px solid black">
                            <svg width="16" height="16" viewBox="0 0 16 16">
                                <path
                                        d="M13.902.334a.5.5 0 0 1-.28.65l-2.254.902-.4 1.927c.376.095.715.215.972.367.228.135.56.396.56.82 0 .046-.004.09-.011.132l-.962 9.068a1.28 1.28 0 0 1-.524.93c-.488.34-1.494.87-3.01.87-1.516 0-2.522-.53-3.01-.87a1.28 1.28 0 0 1-.524-.93L3.51 5.132A.78.78 0 0 1 3.5 5c0-.424.332-.685.56-.82.262-.154.607-.276.99-.372C5.824 3.614 6.867 3.5 8 3.5c.712 0 1.389.045 1.985.127l.464-2.215a.5.5 0 0 1 .303-.356l2.5-1a.5.5 0 0 1 .65.278zM9.768 4.607A13.991 13.991 0 0 0 8 4.5c-1.076 0-2.033.11-2.707.278A3.284 3.284 0 0 0 4.645 5c.146.073.362.15.648.222C5.967 5.39 6.924 5.5 8 5.5c.571 0 1.109-.03 1.588-.085l.18-.808zm.292 1.756C9.445 6.45 8.742 6.5 8 6.5c-1.133 0-2.176-.114-2.95-.308a5.514 5.514 0 0 1-.435-.127l.838 8.03c.013.121.06.186.102.215.357.249 1.168.69 2.438.69 1.27 0 2.081-.441 2.438-.69.042-.029.09-.094.102-.215l.852-8.03a5.517 5.517 0 0 1-.435.127 8.88 8.88 0 0 1-.89.17zM4.467 4.884s.003.002.005.006l-.005-.006zm7.066 0-.005.006c.002-.004.005-.006.005-.006zM11.354 5a3.174 3.174 0 0 0-.604-.21l-.099.445.055-.013c.286-.072.502-.149.648-.222z"/>
                            </svg>
                            <h3 class="fs-6 fs-lg-9">A2</h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 p-5">
                    <div class="card border-0" style="height: 150px !important;">
                        <div class="card-body p-19 p-md-20 d-flex"
                             style="border: 1px solid black">
                            <svg width="16" height="16" viewBox="0 0 16 16">
                                <path
                                        d="M13.902.334a.5.5 0 0 1-.28.65l-2.254.902-.4 1.927c.376.095.715.215.972.367.228.135.56.396.56.82 0 .046-.004.09-.011.132l-.962 9.068a1.28 1.28 0 0 1-.524.93c-.488.34-1.494.87-3.01.87-1.516 0-2.522-.53-3.01-.87a1.28 1.28 0 0 1-.524-.93L3.51 5.132A.78.78 0 0 1 3.5 5c0-.424.332-.685.56-.82.262-.154.607-.276.99-.372C5.824 3.614 6.867 3.5 8 3.5c.712 0 1.389.045 1.985.127l.464-2.215a.5.5 0 0 1 .303-.356l2.5-1a.5.5 0 0 1 .65.278zM9.768 4.607A13.991 13.991 0 0 0 8 4.5c-1.076 0-2.033.11-2.707.278A3.284 3.284 0 0 0 4.645 5c.146.073.362.15.648.222C5.967 5.39 6.924 5.5 8 5.5c.571 0 1.109-.03 1.588-.085l.18-.808zm.292 1.756C9.445 6.45 8.742 6.5 8 6.5c-1.133 0-2.176-.114-2.95-.308a5.514 5.514 0 0 1-.435-.127l.838 8.03c.013.121.06.186.102.215.357.249 1.168.69 2.438.69 1.27 0 2.081-.441 2.438-.69.042-.029.09-.094.102-.215l.852-8.03a5.517 5.517 0 0 1-.435.127 8.88 8.88 0 0 1-.89.17zM4.467 4.884s.003.002.005.006l-.005-.006zm7.066 0-.005.006c.002-.004.005-.006.005-.006zM11.354 5a3.174 3.174 0 0 0-.604-.21l-.099.445.055-.013c.286-.072.502-.149.648-.222z"/>
                            </svg>
                            <h3 class="fs-6 fs-lg-9">A3</h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 p-5">
                    <div class="card border-0" style="height: 150px !important;">
                        <div class="card-body p-19 p-md-20 d-flex"
                             style="border: 1px solid black">
                            <svg width="16" height="16" viewBox="0 0 16 16">
                                <path
                                        d="M13.902.334a.5.5 0 0 1-.28.65l-2.254.902-.4 1.927c.376.095.715.215.972.367.228.135.56.396.56.82 0 .046-.004.09-.011.132l-.962 9.068a1.28 1.28 0 0 1-.524.93c-.488.34-1.494.87-3.01.87-1.516 0-2.522-.53-3.01-.87a1.28 1.28 0 0 1-.524-.93L3.51 5.132A.78.78 0 0 1 3.5 5c0-.424.332-.685.56-.82.262-.154.607-.276.99-.372C5.824 3.614 6.867 3.5 8 3.5c.712 0 1.389.045 1.985.127l.464-2.215a.5.5 0 0 1 .303-.356l2.5-1a.5.5 0 0 1 .65.278zM9.768 4.607A13.991 13.991 0 0 0 8 4.5c-1.076 0-2.033.11-2.707.278A3.284 3.284 0 0 0 4.645 5c.146.073.362.15.648.222C5.967 5.39 6.924 5.5 8 5.5c.571 0 1.109-.03 1.588-.085l.18-.808zm.292 1.756C9.445 6.45 8.742 6.5 8 6.5c-1.133 0-2.176-.114-2.95-.308a5.514 5.514 0 0 1-.435-.127l.838 8.03c.013.121.06.186.102.215.357.249 1.168.69 2.438.69 1.27 0 2.081-.441 2.438-.69.042-.029.09-.094.102-.215l.852-8.03a5.517 5.517 0 0 1-.435.127 8.88 8.88 0 0 1-.89.17zM4.467 4.884s.003.002.005.006l-.005-.006zm7.066 0-.005.006c.002-.004.005-.006.005-.006zM11.354 5a3.174 3.174 0 0 0-.604-.21l-.099.445.055-.013c.286-.072.502-.149.648-.222z"/>
                            </svg>
                            <h3 class="fs-6 fs-lg-9">A4</h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 p-5">
                    <div class="card border-0" style="height: 150px !important;">
                        <div class="card-body p-19 p-md-20 d-flex"
                             style="border: 1px solid black">
                            <svg width="16" height="16" viewBox="0 0 16 16">
                                <path
                                        d="M13.902.334a.5.5 0 0 1-.28.65l-2.254.902-.4 1.927c.376.095.715.215.972.367.228.135.56.396.56.82 0 .046-.004.09-.011.132l-.962 9.068a1.28 1.28 0 0 1-.524.93c-.488.34-1.494.87-3.01.87-1.516 0-2.522-.53-3.01-.87a1.28 1.28 0 0 1-.524-.93L3.51 5.132A.78.78 0 0 1 3.5 5c0-.424.332-.685.56-.82.262-.154.607-.276.99-.372C5.824 3.614 6.867 3.5 8 3.5c.712 0 1.389.045 1.985.127l.464-2.215a.5.5 0 0 1 .303-.356l2.5-1a.5.5 0 0 1 .65.278zM9.768 4.607A13.991 13.991 0 0 0 8 4.5c-1.076 0-2.033.11-2.707.278A3.284 3.284 0 0 0 4.645 5c.146.073.362.15.648.222C5.967 5.39 6.924 5.5 8 5.5c.571 0 1.109-.03 1.588-.085l.18-.808zm.292 1.756C9.445 6.45 8.742 6.5 8 6.5c-1.133 0-2.176-.114-2.95-.308a5.514 5.514 0 0 1-.435-.127l.838 8.03c.013.121.06.186.102.215.357.249 1.168.69 2.438.69 1.27 0 2.081-.441 2.438-.69.042-.029.09-.094.102-.215l.852-8.03a5.517 5.517 0 0 1-.435.127 8.88 8.88 0 0 1-.89.17zM4.467 4.884s.003.002.005.006l-.005-.006zm7.066 0-.005.006c.002-.004.005-.006.005-.006zM11.354 5a3.174 3.174 0 0 0-.604-.21l-.099.445.055-.013c.286-.072.502-.149.648-.222z"/>
                            </svg>
                            <h3 class="fs-6 fs-lg-9">A5</h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 p-5">
                    <div class="card border-0" style="height: 150px !important;">
                        <div class="card-body p-19 p-md-20 d-flex"
                             style="border: 1px solid black">
                            <svg width="16" height="16" viewBox="0 0 16 16">
                                <path
                                        d="M13.902.334a.5.5 0 0 1-.28.65l-2.254.902-.4 1.927c.376.095.715.215.972.367.228.135.56.396.56.82 0 .046-.004.09-.011.132l-.962 9.068a1.28 1.28 0 0 1-.524.93c-.488.34-1.494.87-3.01.87-1.516 0-2.522-.53-3.01-.87a1.28 1.28 0 0 1-.524-.93L3.51 5.132A.78.78 0 0 1 3.5 5c0-.424.332-.685.56-.82.262-.154.607-.276.99-.372C5.824 3.614 6.867 3.5 8 3.5c.712 0 1.389.045 1.985.127l.464-2.215a.5.5 0 0 1 .303-.356l2.5-1a.5.5 0 0 1 .65.278zM9.768 4.607A13.991 13.991 0 0 0 8 4.5c-1.076 0-2.033.11-2.707.278A3.284 3.284 0 0 0 4.645 5c.146.073.362.15.648.222C5.967 5.39 6.924 5.5 8 5.5c.571 0 1.109-.03 1.588-.085l.18-.808zm.292 1.756C9.445 6.45 8.742 6.5 8 6.5c-1.133 0-2.176-.114-2.95-.308a5.514 5.514 0 0 1-.435-.127l.838 8.03c.013.121.06.186.102.215.357.249 1.168.69 2.438.69 1.27 0 2.081-.441 2.438-.69.042-.029.09-.094.102-.215l.852-8.03a5.517 5.517 0 0 1-.435.127 8.88 8.88 0 0 1-.89.17zM4.467 4.884s.003.002.005.006l-.005-.006zm7.066 0-.005.006c.002-.004.005-.006.005-.006zM11.354 5a3.174 3.174 0 0 0-.604-.21l-.099.445.055-.013c.286-.072.502-.149.648-.222z"/>
                            </svg>
                            <h3 class="fs-6 fs-lg-9">A6</h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 p-5">
                    <div class="card border-0" style="height: 150px !important;">
                        <div class="card-body p-19 p-md-20 d-flex"
                             style="border: 1px solid black">
                            <svg width="16" height="16" viewBox="0 0 16 16">
                                <path
                                        d="M13.902.334a.5.5 0 0 1-.28.65l-2.254.902-.4 1.927c.376.095.715.215.972.367.228.135.56.396.56.82 0 .046-.004.09-.011.132l-.962 9.068a1.28 1.28 0 0 1-.524.93c-.488.34-1.494.87-3.01.87-1.516 0-2.522-.53-3.01-.87a1.28 1.28 0 0 1-.524-.93L3.51 5.132A.78.78 0 0 1 3.5 5c0-.424.332-.685.56-.82.262-.154.607-.276.99-.372C5.824 3.614 6.867 3.5 8 3.5c.712 0 1.389.045 1.985.127l.464-2.215a.5.5 0 0 1 .303-.356l2.5-1a.5.5 0 0 1 .65.278zM9.768 4.607A13.991 13.991 0 0 0 8 4.5c-1.076 0-2.033.11-2.707.278A3.284 3.284 0 0 0 4.645 5c.146.073.362.15.648.222C5.967 5.39 6.924 5.5 8 5.5c.571 0 1.109-.03 1.588-.085l.18-.808zm.292 1.756C9.445 6.45 8.742 6.5 8 6.5c-1.133 0-2.176-.114-2.95-.308a5.514 5.514 0 0 1-.435-.127l.838 8.03c.013.121.06.186.102.215.357.249 1.168.69 2.438.69 1.27 0 2.081-.441 2.438-.69.042-.029.09-.094.102-.215l.852-8.03a5.517 5.517 0 0 1-.435.127 8.88 8.88 0 0 1-.89.17zM4.467 4.884s.003.002.005.006l-.005-.006zm7.066 0-.005.006c.002-.004.005-.006.005-.006zM11.354 5a3.174 3.174 0 0 0-.604-.21l-.099.445.055-.013c.286-.072.502-.149.648-.222z"/>
                            </svg>
                            <h3 class="fs-6 fs-lg-9">A7</h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 p-5">
                    <div class="card border-0" style="height: 150px !important;">
                        <div class="card-body p-19 p-md-20 d-flex"
                             style="border: 1px solid black">
                            <svg width="16" height="16" viewBox="0 0 16 16">
                                <path
                                        d="M13.902.334a.5.5 0 0 1-.28.65l-2.254.902-.4 1.927c.376.095.715.215.972.367.228.135.56.396.56.82 0 .046-.004.09-.011.132l-.962 9.068a1.28 1.28 0 0 1-.524.93c-.488.34-1.494.87-3.01.87-1.516 0-2.522-.53-3.01-.87a1.28 1.28 0 0 1-.524-.93L3.51 5.132A.78.78 0 0 1 3.5 5c0-.424.332-.685.56-.82.262-.154.607-.276.99-.372C5.824 3.614 6.867 3.5 8 3.5c.712 0 1.389.045 1.985.127l.464-2.215a.5.5 0 0 1 .303-.356l2.5-1a.5.5 0 0 1 .65.278zM9.768 4.607A13.991 13.991 0 0 0 8 4.5c-1.076 0-2.033.11-2.707.278A3.284 3.284 0 0 0 4.645 5c.146.073.362.15.648.222C5.967 5.39 6.924 5.5 8 5.5c.571 0 1.109-.03 1.588-.085l.18-.808zm.292 1.756C9.445 6.45 8.742 6.5 8 6.5c-1.133 0-2.176-.114-2.95-.308a5.514 5.514 0 0 1-.435-.127l.838 8.03c.013.121.06.186.102.215.357.249 1.168.69 2.438.69 1.27 0 2.081-.441 2.438-.69.042-.029.09-.094.102-.215l.852-8.03a5.517 5.517 0 0 1-.435.127 8.88 8.88 0 0 1-.89.17zM4.467 4.884s.003.002.005.006l-.005-.006zm7.066 0-.005.006c.002-.004.005-.006.005-.006zM11.354 5a3.174 3.174 0 0 0-.604-.21l-.099.445.055-.013c.286-.072.502-.149.648-.222z"/>
                            </svg>
                            <h3 class="fs-6 fs-lg-9">A8</h3>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


<!-- main -->

<!-- footer start -->
<%@ include file="/front-end/store/01h/footerin.jsp" %>
<!-- footer end -->
<script>
    $("a:contains(🚩帶位)").closest("a").addClass("active disabled topage");
</script>


<script src="/CGA105G2/assets/js/vendor.js"></script>
<script src="/CGA105G2/assets/js/polyfills.js"></script>
<script src="/CGA105G2/assets/js/app.js"></script>

<!-- Bootstrap 4.6.2 & Vue 3 & jquery 3.4.1-->
<!-- jquery 3.4.1 -->
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
        src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

<script>
    const timelist = ['11:00', '13:00', '17;30', '19;30'];
    $(function () {
        let currentDate = new Date();
        $("#datepicker").datepicker({
            showButtonPanel: false,
            dayNamesMin: ["日", "一", "二", "三", "四", "五", "六"],
            dateFormat: "yy-mm-dd",
            maxDate: "+1m",
            minDate: "0",
        });
    });
    $(function () {
        $("#datepicker").change(function () {
            list();
            alert("date:" + $("#datepicker").val() + " " + "Time:" + $("#Select01").val());
        })
    });


    // 調整成今天才會顯示候位選項
    $(function () {
        $("#Select01").change(function () {
            if ($("#Select01").val() === "Time") {
                $("#tablenumber").css("display", "none");
            } else {
                $("#tablenumber").css("display", "flex");
            }
        })
    });
    $(function () {
        $("#option1").click(function () {
            $("#tablewait").css("display", "block");
        });
        $("#option2").click(function () {
            $("#tablewait").css("display", "none");
        })
    });
    $(function () {

    });

    function list() {
        $("#Select01").html("<option class=\"btn btn-secondary form-control  p-0\" selected>Time</option>");
        for (let e in timelist) {
            let option = $("<option>").val(e).text(timelist[e]);
            $("#Select01").append(option)
        }
        if ($("#Select01").val() === "Time") {
            $("#tablenumber").css("display", "none");
        } else {
            $("#tablenumber").css("display", "flex");
        }
    };
    $(function () {
        let currentDate = new Date();
        $("#today").click(function () {
            $("#datepicker").val(formatDate(currentDate));
            list();
            alert("date:" + $("#datepicker").val() + " " + "Time:" + $("#Select01").val());
        });
    });

    function formatDate(date) {
        let d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2)
            month = '0' + month;
        if (day.length < 2)
            day = '0' + day;
        return [year, month, day].join('-');
    }

</script>


<!-- 	standby -->
<script>

</script>


<!-- Vue -->
<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script>
    const {
        createApp
    } = Vue

    createApp({
        data() {
            return {
                message: 'Hello Vue!'
            }
        }
    }).mount('#app')
</script>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
    function addStandBy() {
        setTimeout(addStandByAlert(), 1000);
    }

    function addStandByAlert() {
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-outline-primary m-5 fs-5',

            },
            buttonsStyling: false
        })

        swalWithBootstrapButtons.fire({
            position: 'middle',
            icon: 'success',
            title: '登記成功',
            showConfirmButton: false,
            timer: 1500
        })
    }

</script>

</body>

</html>
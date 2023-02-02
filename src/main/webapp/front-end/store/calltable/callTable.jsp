<%@ page import="java.util.List" %>
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

            <%@ page import="java.sql.Timestamp" %>
                <%@ page import="com.waiting.model.service.StandbyService" %>
                    <%@ page import="com.waiting.model.pojo.Standby" %>


                        <%-- <% // StandbyService standbySvc=new StandbyService(); // List<Standby> stanbyList =
                            standbySvc.getAll();
                            // pageContext.setAttribute("stanbtList", stanbyList);
                            <%-- %> --%>


                                <!DOCTYPE html>
                                <html class="no-js" lang="en">

                                    <head>
                                        <meta charset="utf-8" />
                                        <meta http-equiv="x-ua-compatible" content="ie=edge" />
                                        <meta name="viewport"
                                              content="width=device-width, initial-scale=1, shrink-to-fit=no" />

                                        <title>店家首頁</title>
                                        <!-- Bootstrap css123 -->
                                        <link rel="stylesheet"
                                              href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
                                              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
                                              crossorigin="anonymous" />
                                        <!-- jquery 3.4.1  css -->

                                        <link rel="stylesheet" href="/CGA105G2/assets/css/vendor.css" />
                                        <link rel="stylesheet" href="/CGA105G2/assets/css/style.css" />
                                        <link rel="stylesheet" href="/CGA105G2/assets/custom.css">
                                        <link rel="stylesheet"
                                              href="/CGA105G2/assets/fonts/font-awesome/css/font-awesome.css" />
                                        <!-- <link rel="stylesheet" href="/CGA105G2/src/main/webapp/assets/css/carousel.css" /> -->

                                        <link rel="stylesheet"
                                              href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
                                        <link rel="stylesheet"
                                              href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
                                        <link href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"
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
                                                                    id="today">Today</button>
                                                        </label>
                                                        <form METHOD="post" ACTION="/CGA105G2/TableServlet"
                                                              class="btn-group btn-group-toggle p-0 flex-grow-1 col-6">
                                                            <!--              date-->
                                                            <label class="input-group-text col-6" for="datepicker">
                                                                <input type="text" class="form-control  pl-5"
                                                                       style="background-color: rgb(9, 148, 101, 0.42); color: white;"
                                                                       id="datepicker" placeholder="yyyy-mm-dd"
                                                                       name="date" value="${date}">
                                                            </label>
                                                            <!--              time-->
                                                            <label class="input-group-text col-4"> <select
                                                                        class="btn btn-secondary form-control  p-0"
                                                                        style="background-color: rgba(9, 148, 101, 0.42); color: white;"
                                                                        id="Select01" name="totime" ; v def=${totime }>
                                                                </select>
                                                            </label>
                                                            <%-- search--%>
                                                                <label class="input-group-text col-2">
                                                                    <button class="btn btn-secondary form-control  p-0"
                                                                            style="background-color: rgba(9, 148, 101, 0.42); color: white;"
                                                                            id="send">
                                                                        <svg xmlns="http://www.w3.org/2000/svg"
                                                                             width="35" height="35" fill="currentColor"
                                                                             class="bi bi-search" viewBox="0 0 16 16"
                                                                             id="IconChangeColor">
                                                                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"
                                                                                  id="mainIconPathAttribute"></path>
                                                                        </svg>
                                                                    </button> <input type="hidden" name="action"
                                                                           value="search" class="d-none">
                                                                </label>
                                                        </form>
                                                        <%-- reload--%>
                                                            <label class="input-group-text col-1">
                                                                <form METHOD="post" ACTION="/CGA105G2/TableServlet"
                                                                      class="col-12">
                                                                    <input type="hidden" name="date" value="${date}"
                                                                           class="d-none">
                                                                    <input type="hidden" name="totime" value="${totime}"
                                                                           class="d-none">
                                                                    <button class="btn btn-secondary form-control  p-0"
                                                                            style="background-color: rgba(9, 148, 101, 0.42); color: white;"
                                                                            id="reload">
                                                                        <svg xmlns="http://www.w3.org/2000/svg"
                                                                             width="38" height="38" fill="currentColor"
                                                                             class="bi bi-arrow-repeat"
                                                                             viewBox="0 0 16 16" id="IconChangeColor">
                                                                            <path d="M11.534 7h3.932a.25.25 0 0 1 .192.41l-1.966 2.36a.25.25 0 0 1-.384 0l-1.966-2.36a.25.25 0 0 1 .192-.41zm-11 2h3.932a.25.25 0 0 0 .192-.41L2.692 6.23a.25.25 0 0 0-.384 0L.342 8.59A.25.25 0 0 0 .534 9z"
                                                                                  id="mainIconPathAttribute"
                                                                                  stroke-width="0.7" stroke="#4162c3">
                                                                            </path>
                                                                            <path fill-rule="evenodd"
                                                                                  d="M8 3c-1.552 0-2.94.707-3.857 1.818a.5.5 0 1 1-.771-.636A6.002 6.002 0 0 1 13.917 7H12.9A5.002 5.002 0 0 0 8 3zM3.1 9a5.002 5.002 0 0 0 8.757 2.182.5.5 0 1 1 .771.636A6.002 6.002 0 0 1 2.083 9H3.1z"
                                                                                  id="mainIconPathAttribute"
                                                                                  stroke="#4162c3"></path>
                                                                        </svg>
                                                                    </button>
                                                                    <input type="hidden" name="action" value="reload"
                                                                           class="d-none">
                                                                </form>
                                                            </label>
                                                            <div class="input-group-text col-3">
                                                                <p>剩餘訂位數：${listq}</p>
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
                                                                   style="background-color: rgba(253, 72, 72, 0.65); color: black;">
                                                                    訂位表</p>
                                                            </div>
                                                            <div class="btn-group btn-group-toggle"
                                                                 style="display: block" id="tablecheck">
                                                                <div class="table-responsive">
                                                                    <table class="table table-striped m-0">
                                                                        <thead>
                                                                            <tr class="text-center">
                                                                                <th class="col-4">電話</th>
                                                                                <th class="col-4">姓名</th>
                                                                                <th class="col-4">人數</th>
                                                                            </tr>
                                                                        </thead>
                                                                    </table>
                                                                </div>
                                                            </div>
                                                            <section class="section p-0" id="faq1"
                                                                     style="overflow-y: scroll; height: 300px">
                                                                <div class="section-content container">
                                                                    <div class="row">
                                                                        <div class="col-12 p-0 toorder"></div>
                                                                    </div>
                                                                </div>
                                                            </section>
                                                        </div>


                                                        <!--            下-->
                                                        <div>
                                                            <div class="input-group-text">
                                                                <p class="form-control pt-5"
                                                                   style="background-color: rgb(255, 201, 123); color: black;">
                                                                    候位表</p>
                                                            </div>
                                                            <form>
                                                                <div class="p-0 flex-grow-1" id="tablenumber"
                                                                     style="display: none">
                                                                    <div class="btn-group btn-group-toggle flex-grow-1"
                                                                         data-toggle="buttons">


                                                                        <label class="btn btn-secondary active"
                                                                               id="btmon"> <input type="radio"
                                                                                   name="options" id="option1">on <input
                                                                                   type="hidden" value="onStandby">
                                                                        </label> <label class="btn btn-secondary"
                                                                               id="btmoff"> <input type="radio"
                                                                                   name="options" id="option2"
                                                                                   checked>off
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                            </form>
                                                            <div class="btn-group btn-group-toggle"
                                                                 style="display: none" id="tablewait">
                                                                <div class="table-responsive ">
                                                                    <table class="table table-striped m-0">
                                                                        <thead>
                                                                            <tr class="text-center">
                                                                                <th class="col-4 ">電話</th>
                                                                                <th class="col-4">姓名</th>
                                                                                <th class="">人數</th>
                                                                            </tr>
                                                                        </thead>
                                                                    </table>
                                                                </div>
                                                                <section class="section p-0 " id="faq2"
                                                                         style="overflow-y: scroll; height: 300px">
                                                                    <div class="section-content container ">
                                                                        <div class="row">
                                                                            <div class="col-12 p-0">
                                                                                <article class="faq p-0"
                                                                                         id="selectStandByResult">




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
                                                               style="background-color: rgba(253, 72, 72, 0.65); color: black;">
                                                                現場桌況</p>
                                                        </div>
                                                        <div class="btn-group btn-group-toggle" style="display: block">
                                                            <div class="table-responsive ">
                                                                <table class="table table-striped m-0">
                                                                    <thead>
                                                                        <tr class="text-center">
                                                                            <th class="col-4">桌號</th>
                                                                            <th class="col-4">姓名</th>
                                                                            <th class="col-4">人數</th>
                                                                        </tr>
                                                                    </thead>
                                                                </table>
                                                            </div>
                                                        </div>
                                                        <section class="section p-0 " id="faq3"
                                                                 style="overflow-y: scroll; height: 600px">
                                                            <div class="section-content container">
                                                                <div class="row">
                                                                    <div class="col-12 p-0 nowuse">
                                                                        <article class="faq p-0"></article>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </section>
                                                    </div>
                                                    <!--      第三欄    -->
                                                    <div class="col-8 p-0 ">
                                                        <div class="d-flex table3CU"
                                                             style="flex-wrap: wrap; align-content: space-around; height: 100%">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>


                                            <!-- main -->

                                            <!-- footer start -->
                                            <%@ include file="/front-end/store/01h/footerin.jsp" %>
                                                <!-- footer end -->

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
                                                    const timelist = [<c:forEach var="time" items="${time}">'${time}', </c:forEach>];
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

                                                    function list() {
                                                        $("#Select01").html("<option class=\"btn btn-secondary form-control  p-0\" selected><c:if test="${ totime != null} ">${totime}</c:if><c:if test="${ totime == null } ">Time</c:if></option>");
                                                    for (let e in timelist) {
                                                        let option = $("<option>").val(timelist[e]).text(timelist[e]);
                                                        $("#Select01").append(option)
                                                    }
    };
                                                    list();
                                                    $(function () {
                                                        let currentDate = new Date();
                                                        $("#today").click(function () {
                                                            $("#datepicker").val(formatDate(currentDate));
                                                            list();

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
                                                    };

                                                </script>


                                                <script>
                                                    const tables = [<c:forEach var="table" items="${table}">${table}, </c:forEach>];

                                                    //動態產生桌數
                                                    function tableview(tb) {
                                                        // 定義變數，在使用變數
                                                        const table3CU = document.querySelector('.table3CU');
                                                        table3CU.innerHTML = '';
                                                        for (let i of tb) {
                                                            table3CU.innerHTML +=
                                                                `
            <div class="col-md-3 p-5">
                <div class="card border-0" id="A\${i}" style="height: 100px !important;">
                    <div class="card-body p-19 p-md-20 d-flex"
                         style="border: 1px solid black">
                        <svg width="16" height="16" viewBox="0 0 16 16">
                            <path d="M13.902.334a.5.5 0 0 1-.28.65l-2.254.902-.4 1.927c.376.095.715.215.972.367.228.135.56.396.56.82 0 .046-.004.09-.011.132l-.962 9.068a1.28 1.28 0 0 1-.524.93c-.488.34-1.494.87-3.01.87-1.516 0-2.522-.53-3.01-.87a1.28 1.28 0 0 1-.524-.93L3.51 5.132A.78.78 0 0 1 3.5 5c0-.424.332-.685.56-.82.262-.154.607-.276.99-.372C5.824 3.614 6.867 3.5 8 3.5c.712 0 1.389.045 1.985.127l.464-2.215a.5.5 0 0 1 .303-.356l2.5-1a.5.5 0 0 1 .65.278zM9.768 4.607A13.991 13.991 0 0 0 8 4.5c-1.076 0-2.033.11-2.707.278A3.284 3.284 0 0 0 4.645 5c.146.073.362.15.648.222C5.967 5.39 6.924 5.5 8 5.5c.571 0 1.109-.03 1.588-.085l.18-.808zm.292 1.756C9.445 6.45 8.742 6.5 8 6.5c-1.133 0-2.176-.114-2.95-.308a5.514 5.514 0 0 1-.435-.127l.838 8.03c.013.121.06.186.102.215.357.249 1.168.69 2.438.69 1.27 0 2.081-.441 2.438-.69.042-.029.09-.094.102-.215l.852-8.03a5.517 5.517 0 0 1-.435.127 8.88 8.88 0 0 1-.89.17zM4.467 4.884s.003.002.005.006l-.005-.006zm7.066 0-.005.006c.002-.004.005-.006.005-.006zM11.354 5a3.174 3.174 0 0 0-.604-.21l-.099.445.055-.013c.286-.072.502-.149.648-.222z"/>
                        </svg>
                        <h3 class="fs-6 fs-lg-9">A\${i}</h3>
                    </div>
                </div>
            </div>
            `;
                                                        }
                                                    };
                                                    tableview(tables);
                                                </script>
                                                <script>
                                                    const tablehave = [<c:forEach var="tablehave" items="${tablehave}">${tablehave}, </c:forEach>];
                                                    //查詢得到的資料
                                                    const foodorder = [
                                                        <c:forEach var="list" items="${list}">
                                                            ${list},
                                                        </c:forEach>
                                                    ];
                                                    const noworder = [
                                                        <c:forEach var="usejson" items="${usejson}">
                                                            ${usejson},
                                                        </c:forEach>
                                                    ];
                                                    //可以帶位的桌號-->cantab
                                                    const cantable = $(tables).not(tablehave).toArray();
                                                    // 調整成今天才會顯示候位選項
                                                    $(function () {
                                                        //         $("#Select01").change(function () {
                                                        if ($("#Select01").val() === "Time") {
                                                            $("#tablenumber").css("display", "none");
                                                        } else {
                                                            $("#tablenumber").css("display", "flex");
                                                        }
                                                        //         })
                                                    });
                                                    $(function () {
                                                        $("#option1").click(function () {
                                                            $("#tablewait").css("display", "block");
                                                        });
                                                        $("#option2").click(function () {
                                                            $("#tablewait").css("display", "none");
                                                        })
                                                    });
                                                    function orderlist(obder) {
                                                        // 定義變數，在使用變數
                                                        const toorder = document.querySelector('.toorder');
                                                        toorder.innerHTML = '';
                                                        for (let i of obder) {
                                                            let a = "桌號";
                                                            if (i.TABLE > 0) {
                                                                a = i.TABLE;
                                                            }
                                                            toorder.innerHTML += `
            <article class="faq p-0">
                                    <header class="faq-header" data-toggle="collapse"
                                            data-target="#faq1-item-\${i.REN_ID}" aria-expanded="false">
                                        <table class="table table-striped m-0">
                                            <tbody class="code_tbody">
                                            <tr class="text-center">
                                                <td class="col-4">\${i.NAME}</td>
                                                <td class="col-4">\${i.PHONE}</td>
                                                <td class="col-4">\${i.PepQ}位</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <div class="faq-toggle" style="display: none">
                                            <i class="material-icons faq-toggle-closed">add</i>
                                            <i class="material-icons faq-toggle-open">remove</i>
                                        </div>
                                    </header>
                                    <div class="faq-body collapse" id="faq1-item-\${i.REN_ID}">
                                        <div class="card-body pt-0 pb-1" data-mh="gift-vouchers">
                                            <div>
                                                <form METHOD="post" ACTION="/CGA105G2/TableServlet" class="d-flex col-12">
                                                    <select class="btn btn-dark p-0 w-75 cantab" name="table">
                                                        <option value=\${i.TABLE} selected>A\${a}</option>
                                                        <option value=0>A桌號</option>
                                                    </select>
                                                    <input type="hidden" name="date" value="${date}" class="d-none">
                                                    <input type="hidden" name="totime" value="${totime}" class="d-none">
                                                    <button class="btn btn-dark p-0 w-25">✔</button>
                                                    <input type="hidden" name="action" value="totable" class="d-none">
                                                    <input type="hidden" name="toid" value="\${i.REN_ID}" class="d-none">
                                                </form>
                                            </div>
                                            <div id="orderD\${i.REN_ID}"></div>
                                            <div class="border-lighter border-top my-2"></div>
                                            <div class="d-flex align-items-center justify-content-between mt-2">
                                                <div class="text-dark">TOTAL</div>
                                                <div class="price-value fs-3">$\${i.PRICE}</div>
                                            </div>
                                            <form METHOD="post" ACTION="/CGA105G2/TableServlet">
                                                <input type="hidden" name="date" value="${date}" class="d-none">
                                                <input type="hidden" name="totime" value="${totime}" class="d-none">
                                                <input type="hidden" name="toid" value="\${i.REN_ID}" class="d-none">
                                                <input type="hidden" name="table" value="\${i.TABLE}" class="d-none">
                                                <button class="btn p-0 w-100 " style="background-color: rgba(253, 72, 72, 0.65); color: black;">報到</button>
                                                <input type="hidden" name="action" value="check" class="d-none">
                                            </form>
                                        </div>
                                    </div>
                                </article>
            `;
                                                        }
                                                    }
                                                    function now(ulist) {
                                                        // 定義變數，在使用變數
                                                        const useorder = document.querySelector('.nowuse');
                                                        useorder.innerHTML = '';
                                                        for (let e of ulist) {
                                                            let b = "桌號";
                                                            if (e.TABLE > 0) {
                                                                b = e.TABLE;
                                                            }
                                                            let to = "out";
                                                            let p = "離席"
                                                            if (e.PRICE == 0) {
                                                                p = "點餐";
                                                                to = "tobuy";
                                                            }
                                                            useorder.innerHTML += `
            <article class="faq p-0">
                                    <header class="faq-header" data-toggle="collapse"
                                            data-target="#faq1-item-\${e.REN_ID}" aria-expanded="false">
                                        <table class="table table-striped m-0">
                                            <tbody class="code_tbody">
                                            <tr class="text-center">
                                                <td class="col-4">A\${e.TABLE}</td>
                                                <td class="col-4">\${e.NAME}</td>
                                                <td class="col-4">\${e.PepQ}位</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <div class="faq-toggle" style="display: none">
                                            <i class="material-icons faq-toggle-closed">add</i>
                                            <i class="material-icons faq-toggle-open">remove</i>
                                        </div>
                                    </header>
                                    <div class="faq-body collapse" id="faq1-item-\${e.REN_ID}">
                                        <div class="card-body pt-0 pb-1" data-mh="gift-vouchers">
                                            <div>
                                                <form METHOD="post" ACTION="/CGA105G2/TableServlet" class="d-flex col-12">
                                                    <select class="btn btn-dark p-0 w-75 cantab" name="table">
                                                        <option value=\${e.TABLE} selected>A\${b}</option>
                                                        <option value=0>A桌號</option>
                                                    </select>
                                                    <input type="hidden" name="date" value="${date}" class="d-none">
                                                    <input type="hidden" name="totime" value="${totime}" class="d-none">
                                                    <button class="btn btn-dark p-0 w-25">✔</button>
                                                    <input type="hidden" name="action" value="totable" class="d-none">
                                                    <input type="hidden" name="toid" value="\${e.REN_ID}" class="d-none">
                                                </form>
                                            </div>
                                            <div id="orderD\${e.REN_ID}"></div>
                                            <div class="border-lighter border-top my-2"></div>
                                            <div class="d-flex align-items-center justify-content-between mt-2">
                                                <div class="text-dark">TOTAL</div>
                                                <div class="price-value fs-3">$\${e.PRICE}</div>
                                            </div>
                                            <form METHOD="post" ACTION="/CGA105G2/TableServlet">
                                                <input type="hidden" name="date" value="${date}" class="d-none">
                                                <input type="hidden" name="totime" value="${totime}" class="d-none">
                                                <input type="hidden" name="toid" value="\${e.REN_ID}" class="d-none">
                                                <button class="btn p-0 w-100 " style="background-color: rgba(253, 72, 72, 0.65); color: black;">\${p}</button>
                                                <input type="hidden" name="table" value="\${e.TABLE}" class="d-none">
                                                <input type="hidden" name="action" value=\${to} class="d-none">
                                            </form>
                                        </div>
                                    </div>
                                </article>
            `;
                                                        }
                                                    }

                                                    function detail(oderD) {
                                                        for (let e of oderD) {
                                                            const detil = e.DETAIL;
                                                            const id = "orderD" + e.REN_ID;
                                                            const order = document.getElementById(id.toString());
                                                            order.innerHTML = '';
                                                            for (let i of detil) {
                                                                order.innerHTML += `<div class="text-dark">\${i.EAT_NAME}<span>×</span>\${i.EAT_Q}</div>`;
                                                            }
                                                        }
                                                    }

                                                    function tablecolor(tablehave) {
                                                        for (let e of tablehave) {
                                                            const id = "A" + e;
                                                            document.getElementById(id.toString()).setAttribute("style", "height: 100px !important; background-color: rgba(253, 72, 72, 0.65);");
                                                        }
                                                    }

                                                    function tab(list) {
                                                        const order = document.querySelectorAll('.cantab');
                                                        for (let i of order) {
                                                            for (let e of list) {
                                                                i.innerHTML += `<option value=\${e} >A\${e}</option>`;
                                                            }
                                                        }
                                                    }
                                                    orderlist(foodorder);
                                                    now(noworder);
                                                    tablecolor(tablehave);
                                                    detail(foodorder);
                                                    detail(noworder);
                                                    tab(cantable);

                                                </script>
                                                <!-- 	==========候位============== -->
                                                <script>
                                                    $(document).ready(function () {
                                                        if ($('#option2').is(':checked')) {
                                                            $.ajax({
                                                                type: "POST",
                                                                url: "/CGA105G2/standby",
                                                                data: { onOff: "off" },
                                                                success: function () {
                                                                    console.log("sta:off");
                                                                }
                                                            });
                                                        }



                                                        // ============Select===================
                                                        $('#option1').click(function () {
                                                            $.ajax({
                                                                type: "POST",
                                                                url: "/CGA105G2/standby",
                                                                data: { action: "selectStandBy" ,onOff:"on"},
                                                                dataType: "json",
                                                                success: function (data) {
                                                                    console.log("select success!");
                                                                    // console.log(data);
                                                                    let html = "";
                                                                    for (let i = 0; i < data.length; i++) {

                                                                        html +=         
                                                                                    `<div id="staHeader-\${data[i].staId}">
                                                                                        <header class="faq-header"
                                                                                                data-toggle="collapse"
                                                                                                data-target="#faq2-item-\${data[i].staId}"
                                                                                                aria-expanded="false" >
                                                                                            <table
                                                                                                   class="table table-striped m-0">
                                                                                                <tbody
                                                                                                       class="code_tbody">

                                                                                                    <tr
                                                                                                        class="text-center">
                                                                                                        <td
                                                                                                            class="fs-2 col-5 p-2 py-3 my-auto align-middle">
                                                                                                            \${data[i].staPhone}
                                                                                                        </td>
                                                                                                        <td class="col-4 pl-0 ">\${data[i].staName}
                                                                                                        </td>
                                                                                                        <td class="">\${data[i].staNumber}位
                                                                                                        </td>
                                                                                                    </tr>
                                                                                                </tbody>
                                                                                            </table>
                                                                                            <div class="faq-toggle"
                                                                                                 style="display: none">
                                                                                                <i
                                                                                                   class="material-icons faq-toggle-closed">add</i>
                                                                                                <i
                                                                                                   class="material-icons faq-toggle-open">remove</i>
                                                                                            </div>
                                                                                        </header>

                                                                                        <div class="faq-body collapse row mx-auto"
                                                                                        id="faq2-item-\${data[i].staId}"
                                                                                        style="width: 100%; justify-content: center">
                                                                                        
                                                                                        <div class="radio-buttons-group w-100 justify-content-center " >

                                                                                            <form METHOD="post"
                                                                                                  id=noticeStandby
                                                                                                  ACTION="<%=request.getContextPath()%>/standby" class="d-block">
                                                                                                <input type="submit"
                                                                                                	   id="callMem"
                                                                                                       value="通知"
                                                                                                       onclick=""
                                                                                                       class="btn btn-outline-info w-auto text-dark btn-lg "
                                                                                                       data-value="call" >
                                                                                                <input type="hidden"
                                                                                                       name="staId"
                                                                                                       value="\${data[i].staId}">
                                                                                                <input type="hidden"
                                                                                                       name="staStatus"
                                                                                                       value="\${data[i].staStatus}">
                                                                                                <input type="hidden"
                                                                                                       name="action"
                                                                                                       value="delete">
                                                                                        <input type="hidden" name="date" value="${date}" class="d-none">
                                                                                        <input type="hidden" name="totime" value="${totime}" class="d-none">
                                                                                            </form>

                                                                                            <form METHOD="post"
                                                                                                  id="checkStandby"
                                                                                                  ACTION="<%=request.getContextPath()%>/standby" class="d-block">
                                                                                               

                                                                                                <input type="hidden"
                                                                                                       name="staId"
                                                                                                       id="checkStaId"
                                                                                                       value="\${data[i].staId}">


                                                                                                <input type="hidden"
                                                                                                       name="action"
                                                                                                       value="checkMem">

                                                                                                <input  type= "submit" class="d-none">


                                                                                             <input class="btn btn-outline-info w-auto text-dark btn-lg"
                                                                                                       data-value="come"
                                                                                                       id="checkMemBtn"
                                                                                                       type="button"
                                                                                                       onclick="checkMem()"
                                                                                                       value="報到"
                                                                                                       >


                                                                                            </form>
                                                                                        </div> 
                                                                                         </div>
                                                                                          </div>`;
                                                                    }
                                                                    $("#selectStandByResult").html(html);
                                                                }
                                                            });
                                                        });
                                                        // ------------------------------------------
                                                        //報到 

                                                        // $('#checkMemBtn').click(function(){
                                                       
                                                        // });

                                                        // ---------doc.ready--------
                                                    });
                                                    // ---------doc.ready--------
                                                    $('#checkMemBtn').click(function(){

                                                        console.log('staTest');
                                                    });

                                                    function checkMem(){
                                                            let staId = $('#checkStaId').val();
                                                            console.log(staId);
                                                            $.ajax({
                                                                type:"POST",
                                                                url:"/CGA105G2/standby",
                                                                data:$('#checkStandby').serialize(),
                                                                success:function(data){
                                                                    console.log("checkStaSuccess!");
                                                                    $(`#staHeader-\${staId}`).css("display","none");
                                                                    $("#option1").trigger('click');
                                                                },
                                                                error:function(){
                                                                    console.log("erroeSta");
                                                                }
                                                            });
                                                    } 


                                                    // 通知
                                                    $("#noticeStandby").submit(function (event) {
                                                        event.preventDefault();
                                                        setTimeout(function () {
                                                            $.ajax({
                                                                url: "/CGA105G2/standby",
                                                                type: "POST",
                                                                data: ("#noticeStandby").serialize(),

                                                            });

                                                        }, 300000);

                                                    });





                                                </script>

                                                <!-- Vue -->
                                                <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
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
                                                <script>
                                                    $("a:contains(🚩帶位)").closest("a").addClass("active disabled topage");
                                                </script>
                                                <script>

                                                </script>

                                    </body>

                                </html>
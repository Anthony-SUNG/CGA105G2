<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <title>💰point</title>
</head>

<body>
<!-- header start -->
<%@ include file="/back-end/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid p-0">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/back-end/01h/nav/navin02.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4 p-0">
            <section class="section jarallax text-white" data-jarallax data-speed="0.2">
                <div class="section-background-color"
                     style="background: linear-gradient(to right top, rgb(25, 182, 143), rgb(68, 100, 148)) rgb(25, 182, 143); padding-top: 120px; padding-bottom: 40px;">
                </div>
                <div class="section-content container d-flex flex-column align-items-center">
            <span
                    class="badge badge-pill badge-ghost fs-2 font-family-dark text-uppercase font-weight-bold letter-spacing-caption">
              FoodMap
            </span>
                    <h1 class="mt-5 mb-17 fs-10 fs-md-10">🔆新增商品</h1>
                </div>
            </section>
            <FORM METHOD="post" ACTION="point.do" name="form1">
                <table>
                    <tr>
                        <td>商品名稱:</td>
                        <td><input type="TEXT" name="pdName"
                                   value="${param.pdName}"/></td>
                        <td>${errorMsgs.pdName}</td>
                    </tr>
                    <tr>
                        <td>商品單價:</td>
                        <td><input type="TEXT" name="pdPrice"
                                   value="${param.pdPrice}"/></td>
                        <td>${errorMsgs.pdPrice}</td>
                    </tr>

                    <tr>
                        <td>商品介紹:</td>
                        <td><input type="TEXT" name="pdText"
                                   value="${param.pdText}"/></td>
                        <td>${errorMsgs.pdText}</td>
                    </tr>

                    <tr>
                        <td>商品狀態:</td>
                        <td>
                            <input type="radio" name="pdStatus" value=1/> 上架
                            <input type="radio" name="pdStatus" value=0 checked/> 下架
                        </td>
                    </tr>
                </table>
                <br>
                <input type="hidden" name="action" value="insert">
                <input type="submit" value="送出新增"></FORM>
        </main>
    </div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/back-end/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sidebar menu Class -->
<script>
    $("a:contains(📔商城)").closest("a").addClass("active disabled topage");
    $("a:contains(🔻商品管理)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu3").removeClass("collapse");
    $("#pageSubmenu3 a:contains(🔆新增商品)").closest("a").addClass("active disabled bg-white topage");
</script>
<script>
    const list=[];
    <c:forEach var="empRoot" items="${empRoot}">
    list.push(${empRoot.rootId});
    </c:forEach>
    for (let e of list){
        switch (e){
            case 1:
                $("#a2").removeClass("disabled");
                $("#a3").removeClass("disabled");
                $("#a4").removeClass("disabled");
                $("#a5").removeClass("disabled");
                break;
            case 2:
                $("#a2").removeClass("disabled");
                break;
            case 3:
                $("#a3").removeClass("disabled");
                break;
            case 4:
                $("#a4").removeClass("disabled");
                break;
            case 5:
                $("#a5").removeClass("disabled");
                break;
        }
    }
</script>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<body>
<nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="p-4 pt-5">
        <ul class="list-unstyled components mb-5">
            <li class="my-4">
                <a href="/CGA105G2/front-end/Member/member/Member.jsp">
                    <h2>Home</h2>
                </a>
            </li>
            <li class="my-4">
                <a href="#pageSubmenu1" data-toggle="collapse" aria-expanded="false" class="dropdown fs-md-6">🔻優惠券</a>
                <ul class="collapse list-unstyled  " id="pageSubmenu1" >
                    <li>
                        <hr><a href="/CGA105G2/CodeServlet?action=memCodeAllU" class="nav-link">🔆有效優惠券</a>
                    </li>
                    <li><a href="/CGA105G2/CodeServlet?action=memCodeAll" class="nav-link">🔆歷史查詢</a>
                        <hr>
                    </li>
                </ul>
            </li>
            <li class="my-4">
                <a href="#pageSubmenu2" data-toggle="collapse" aria-expanded="false" class="dropdown fs-md-6">🔻訂單</a>
                <ul class="collapse list-unstyled collapse" id="pageSubmenu2" >
                    <li>
                        <hr><a href="/CGA105G2/BlankPage/Loader2.jsp" class="nav-link">🔆待出貨</a>
                    </li>
                    <li><a href="/CGA105G2/BlankPage/Loader2.jsp" class="nav-link">🔆待評價</a></li>
                    <li><a href="/CGA105G2/BlankPage/Loader2.jsp" class="nav-link">🔆歷史查詢</a>
                        <hr>
                    </li>
                </ul>
            </li>
            <li class="my-4">
                <a href="#pageSubmenu3" data-toggle="collapse" aria-expanded="false" class="dropdown fs-md-6">🔻訂位</a>
                <ul class="collapse list-unstyled" id="pageSubmenu3">
                    <li>
                        <hr><a href="/CGA105G2/front-end/Member/food_order/listAllFoodOrder.jsp" class="nav-link">🔆訂位預約</a>
                    </li>
                    <li><a href="/CGA105G2/BlankPage/Loader2.jsp" class="nav-link">🔆訂位紀錄</a>
                        <hr>
                    </li>
                </ul>
            </li>
            <li class="my-4">
                <a href="#pageSubmenu4" data-toggle="collapse" aria-expanded="false" class="dropdown fs-md-6">🔻設定
                </a>
                <ul class="collapse list-unstyled" id="pageSubmenu4">
                    <li>
                        <hr><a href="/CGA105G2/Member/MemberServlet?action=MemberInfo" class="nav-link">🔆基本資料</a>
                    </li>
                    <li><a href="/CGA105G2/BlankPage/Loader2.jsp" class="nav-link">🔆收件地址</a></li>
                    <li><a href="/CGA105G2/BlankPage/Loader2.jsp" class="nav-link">🔆付款方式</a></li>
                    <li><a href="/CGA105G2/BlankPage/Loader2.jsp" class="nav-link">🔆變更密碼</a>
                        <hr>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
</body>
</html>

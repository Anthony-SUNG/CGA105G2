<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<body>
<nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse ">
    <div class="p-4 pt-5 sidebar__inner">
        <ul class="list-unstyled components mb-5">
            <li class="m-5">
                <a href="/CGA105G2/front-end/Member/member/Member.jsp">
                    <h2>Home</h2>
                </a>
            </li>
            <li class="m-5">
                <a href="/CGA105G2/front-end/Member/point/listPoint.jsp" data-toggle="collapse" aria-expanded="false" class="dropdown">
                    <h3>💰點數查詢</h3>
                </a>
            </li>
            <li class="m-5">
                <a href="/CGA105G2/front-end/Member/point/listPointGood.jsp" data-toggle="collapse"
                   aria-expanded="false" class="collapse">
                    <h3>💰點數商品</h3>
                </a>
            </li>
        </ul>
    </div>
</nav>
</body>
<!-- stickey bar: -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sticky-sidebar/3.3.1/sticky-sidebar.min.js"></script>

<script>
    let a = new StickySidebar("#sidebar", {
        topSpacing: 40,
        bottomSpacing: 20,
        containerSelector: ".container",
        innerWrapperSelector: ".sidebar__inner"
    });
</script>


</html>

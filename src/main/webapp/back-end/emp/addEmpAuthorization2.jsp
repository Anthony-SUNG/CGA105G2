<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.emp.model.EmployeeRoot.pojo.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>εΎε°</title>
</head>
<body>
<!-- header start -->
<%@ include file="/back-end/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<div class="container-fluid">
    <div class="row">
        <!-- nav start -->
        <%@ include file="/back-end/01h/nav/navin03.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-15 border-bottom">
                <h1 class="h2">πζ°ε’ζ¬ι</h1>
            </div>
            <jsp:useBean id="empSvc" scope="page" class="com.emp.model.service.EmployeeService"/>
            <div class=" shadow card-body  rounded mb-20 bg-secondary " id="div11" style="border-radius: 30px;">
                <form METHOD="post" ACTION="<%=request.getContextPath()%>/EmployeeServlet" name="form1">
                    <div class="form-group">
                        <label>ιΈζε‘ε·₯</label><br>
                        <select size="1" name="emp" id="selectEmp">
                            <c:forEach var="empVO" items="${empSvc.all}">
                            <option value="${empVO.empId}">${empVO.empAcc}
                                </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>ιΈζζ¬ι</label><br>
                        <select size="1" name="root">
                            <c:forEach var="empRoot" items="${empSvc.empRootAll}">
                            <option value="${empRoot.rootId}">${empRoot.rootText}
                                </c:forEach>
                        </select>
                    </div>
                    <input type="hidden" name="action" value="addEmpRoot">
                    <button type="submit" class="btn btn-outline-dark mb-1 " data-toggle="modal"
                            data-target="#exampleModalCenter" onclick="addEmpAlert()">Submit
                    </button>
                </form>
            </div>
        </main>
    </div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/back-end/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sidebar menu Class -->
<script>
    $("a:contains(ποΈη?‘η)").closest("a").addClass("active disabled topage");
    $("a:contains(π»ε‘ε·₯ζ¬ι)").closest("a").attr("data-toggle", "show");
    $("#pageSubmenu3").removeClass("collapse");
    $("#pageSubmenu3 a:contains(πζ°ε’ζ¬ι)").closest("a").addClass("active disabled bg-white topage");
</script>
<script>
    const list = [];
    <c:forEach var="empRoot" items="${empRoot}">
    list.push(${empRoot.rootId});
    </c:forEach>
    for (let e of list) {
        switch (e) {
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
<!-- sweetalert2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
    function addEmpAlert() {
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-outline-primary m-5 fs-5',
            },
            buttonsStyling: false
        })
        swalWithBootstrapButtons.fire({
            position: 'middle',
            icon: 'success',
            title: 'ζ°ε’ζε',
            showConfirmButton: false,
            timer: 1500
        })
    }
</script>
</body>
</html>
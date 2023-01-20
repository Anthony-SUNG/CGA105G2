<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <title>後台</title>
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
            <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-15 border-bottom">
                <h1 class="h2">🔆新增權限</h1>

            </div>
            <div class="shadow bg-secondary card-body rounded mb-20 " id="div11">
                <form class="form">
                    <div class="dropdown">
                        <p class="mb-0 fs-4">員工</p>
                        <button class="btn btn-secondary  dropdown-toggle  mb-10 mt-10 fs-5" type="button"
                                id="dropdownMenuButton1" data-toggle="dropdown" aria-haspopup="true"
                                aria-expanded="false">
                            1001
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="#">1001</a>
                            <a class="dropdown-item" href="#">1002</a>
                            <a class="dropdown-item" href="#">1003</a>
                        </div>
                    </div>

                    <div class="form-group mb-20">
                        <div class="dropdown">
                            <p class="mb-0 fs-4">權限</p>
                            <button class="btn btn-secondary dropdown-toggle mb-15 mt-10 fs-5" type="button"
                                    id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                    aria-expanded="false">
                                總管理員
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                <a class="dropdown-item" href="#">審核管理</a>
                                <a class="dropdown-item" href="#">點數商城管理</a>
                                <a class="dropdown-item" href="#">數據管理</a>
                            </div>
                        </div>
                    </div>
                    <button type="button" class="btn btn-outline-dark mb-1 " data-toggle="modal"
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
    $("a:contains(🗃️管理)").closest("a").addClass("active disabled topage");
    $("a:contains(🔻員工權限)").closest("a").attr("data-toggle","show");
    $("#pageSubmenu3").removeClass("collapse");
    $("#pageSubmenu3 a:contains(🔆新增權限)").closest("a").addClass("active disabled bg-white topage");
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
            title: '新增成功',
            showConfirmButton: false,
            timer: 1500
        })
    }
</script>
</body>

</html>
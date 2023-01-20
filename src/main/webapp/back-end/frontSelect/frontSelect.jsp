<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <title>後台</title>
</head>
<body>
<!-- header start -->
<%@ include file="/back-end/01h/headerin.jsp" %>
<!-- header end -->

<!-- main -->
<div class="container-fluid">
    <div class="row">
        <main role="main" class="col-md-9 m-sm-auto col-lg-10 pl-md-4 shadow">
            <div
                    class=" d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-15 mt-5 border-bottom">
                <h1 class="h2">🔆前台查詢</h1>

            </div>
            <div class="container">
                <div class="card">
                    <div class="m-10 ml-25">
                        <form action="">
                            <select class="custom-select col-md-10" aria-placeholder="選擇查尋項目">
                                <option selected="" value="1">會員資料</option>
                                <option value="2">店家資料</option>
                                <option value="3">訂單資料</option>
                                <option value="4">訂單明細</option>
                            </select>
                            <input type="submit" class="black btn btn-primary fs-5 p-4 " style="width: 147px;"
                                   value="送出">
                        </form>
                    </div>
                    <table class="table mt-5">
                        <thead class="thead-light text-center">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">First</th>
                            <th scope="col">Last</th>
                            <th scope="col">Handle</th>
                            <th scope="col">Handle</th>
                        </tr>
                        </thead>
                        <tbody class="text-center">
                        <tr>
                            <th scope="row">1</th>
                            <td>Mark</td>
                            <td>Otto</td>
                            <td>@mdo</td>
                            <td>@mdo</td>
                        </tr>
                        <tr>
                            <th scope="row">2</th>
                            <td>Jacob</td>
                            <td>Thornton</td>
                            <td>@fat</td>
                            <td>@fat</td>
                        </tr>
                        <tr>
                            <th scope="row">3</th>
                            <td>Larry</td>
                            <td>the Bird</td>
                            <td>@twitter</td>
                            <td>@twitter</td>
                        </tr>
                        <tr>
                            <th scope="row">4</th>
                            <td>Larry</td>
                            <td>the Bird</td>
                            <td>the Bird</td>
                            <td>@twitter</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="shuffle-grid-item hiddenBtn" data-groups="storeLogin" id="storeLogin1"
                 style="display:none;">


            </div>
            <canvas class="my-4 w-100" id="myChart" width="900" height="350"></canvas>
        </main>
    </div>
</div>
<!-- main -->
<!-- footer start -->
<%@ include file="/back-end/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sidebar menu Class -->
<script>
    $("a:contains(前台查詢)").closest("a").addClass("active disabled topage");
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
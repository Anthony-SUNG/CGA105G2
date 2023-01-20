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
        <!-- nav start -->
        <%@ include file="/back-end/01h/nav/navin03.jsp" %>
        <!-- nav end -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
            <canvas height="200"></canvas>
            <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-15 border-bottom">
                <h1 class="h2">🔆權限設定</h1>

            </div>

            <form>
                <div class="input-group">
                    <input type="search" class="form-control rounded"
                           placeholder="搜尋員工" aria-label="Search"
                           aria-describedby="search-addon"/>
                    <button type="submit" class="btn btn-outline-dark"
                            data-mdb-ripple-color="dark">search
                    </button>
                </div>
            </form>

            <div class="table-responsive ">

                <table class="table table-striped ">
                    <thead>
                    <tr>
                        <td></td>
                        <td>員工編號</td>
                        <td>員工名稱</td>
                        <td>員工權限</td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody class="code_tbody">
                    </tbody>
                </table>
            </div>
            <nav aria-label="Page navigation example"
                 class="d-flex justify-content-center" style="padding: 10px 0 25px">
                <ul class="pagination">
                    <li class="page-item"><a class="page-link" href="#"
                                             aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                    </a></li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#"
                                             aria-label="Next"> <span aria-hidden="true">&raquo;</span>
                    </a></li>
                </ul>
            </nav>
        </main>
        <section class="jumbotron jumbotron-fluid mb-0 bg-secondary">
        </section>
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
    $("#pageSubmenu3 a:contains(🔆權限設定)").closest("a").addClass("active disabled bg-white topage");
</script>

<script>
const list = [
    {
        MENBER_ID: '1001',
        MENBER_NAME: 'JIA',
        MENBER_STATUS: '總管理員'

    },
    {
        MENBER_ID: '1002',
        MENBER_NAME: 'tom',
        MENBER_STATUS: '審核管理員'


    },
    {
        MENBER_ID: '1003',
        MENBER_NAME: 'ROGER',
        MENBER_STATUS: '點數商城管理員'


    },
    {
        MENBER_ID: '1004',
        MENBER_NAME: 'LOUIE',
        MENBER_STATUS: '前台網站管理員'


    },
    {
        MENBER_ID: '1005',
        MENBER_NAME: 'DAVIE',
        MENBER_STATUS: '營運管理員'


    }
];
render(list);

function render(list) {
    const codetbody = document.querySelector('.code_tbody');
    codetbody.innerHTML = '';
    for (let item of list) {
        codetbody.innerHTML +=

            `<tr>
                <td></td>
                <td>${item.MENBER_ID}</td>
                <td>${item.MENBER_NAME}</td>
                <td>${item.MENBER_STATUS}</td>
                <td><button type="button" class="btn btn-outline-dark btn-sm" id="submit"  >修改</button></td>
              </tr> `;
    }

}

document.getElementById('submit').onclick = function () {
    this.value = 'Processing…';
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
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
          crossorigin="anonymous"/>
    <link rel="stylesheet" href="/CGA105G2/assets/css/vendor.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/css/style.css"/>
    <link rel="stylesheet" href="/CGA105G2/assets/custom.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.min.css">
    <!-- jquery 3.4.1  css -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <style>
        body {
            height: 100%;
        }

        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        a {
            color: black;
        }

        body {
            height: 100%;
        }

        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        a {
            color: black;
        }

        .table th {
            white-space: nowrap !important;
        }

        .topage {
            color: #216a51 !important;
        }

    </style>
</head>
<body>
<!-- header start -->
<section class="header header-fixed-xl">
    <div class="header-main bg-warning">
        <div class="container" style="max-width: 1400px">
            <div class="row" style="max-width: 1400px">
                <nav class="navbar navbar-expand-lg navbar-light fs-md-6" id="header-navbar">
                    <!-- @*Navbar(白色)*@ -->
                    <div>
                        <a class="navbar-brand font-weight-bold" href="/CGA105G2/front-end/Member/member/Member.jsp">
                            <img src="/CGA105G2/assets/images/Logo.PNG" style="width: 100px; height: 100px" alt=""/></a>
                    </div>
                    <form class="form-inline my-2 my-md-0 bg-white p-1 " style="border-radius: 30px;">
                        <div class="single-icon" data-toggle="tooltip" title="" data-original-title="search"
                             style="border: 0; "><i
                                class="material-icons">search</i>
                        </div>
                        <input class="form-control " type="text" placeholder="Search"
                               style="border: 0; border-radius: 30px;">
                    </form>

                    <button class="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ml-auto">
                            <div class="navbar-spacer"></div>
                            <div class="navbar-spacer"></div>
                            <li class="nav-item">
                                <a class="nav-link text-uppercase" data-toggle="none" href="/CGA105G2/BlankPage/contactUs.jsp">
                                    📭聯繫我們
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-uppercase" data-toggle="none" href="/CGA105G2/front-end/Member/art/listArt.jsp">
                                    🌟
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-uppercase" data-toggle="none" href="/CGA105G2/front-end/Member/point/listPointGood.jsp">
                                    💰point
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-uppercase" data-toggle="none" href="/CGA105G2/BlankPage/Loader2.jsp">
                                    🛒
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-uppercase " data-toggle="none" href="/CGA105G2/CodeServlet?action=memCodeAllU">
                                    🗃️管理
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-uppercase" data-toggle="none"
                                   href="/CGA105G2/LonginServlet?action=out">
                                    🚪Sing out
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
    </div>
</section>
<!-- header end -->
<script src="/CGA105G2/assets/js/vendor.js"></script>
<script src="/CGA105G2/assets/js/polyfills.js"></script>
<script src="/CGA105G2/assets/js/app.js"></script>
<!-- Bootstrap 4.6.2 & Vue 3 & jquery 3.4.1-->
<!-- jquery 3.4.1 -->
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/2.0.0/clipboard.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<!-- Bootstrap js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
<!-- stickey bar: -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sticky-sidebar/3.3.1/sticky-sidebar.min.js"></script>
<script>
    let a = new StickySidebar("#sidebar", {
        topSpacing: 40,
        bottomSpacing: 20,
        containerSelector: ".container",
        innerWrapperSelector: ".sidebar__inner"
    });
    $(document).ready(function () {
        new ClipboardJS('.btn');
    });
</script>
</body>
</html>

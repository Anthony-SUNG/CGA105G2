<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>店家首頁</title>
</head>

<body>
<!-- header start -->
<%@ include file="/front-end/store/01h/headerin.jsp" %>
<!-- header end -->
<!-- main -->
<section class="hero hero-fullscreen jarallax py-5" data-speed="0.2">
    <div class="hero-background ">
        <img src="/CGA105G2/assets/images/resterant.jpg"
             alt="hero background" class="jarallax-img"/>
        <div
                class="d-flex align-items-center justify-content-center py-md-10 h-100">
            <div
                    class="card border-0 text-center mx-10 mx-lg-0 bg-white-90 w-50  mb-10 h-100 ">
                <!-- 搜尋 -->
                <div class="card-body text-black h-25 m-5 mb-14">
                    <h2 class="text-black fs-7 m-1">FoodMap</h2>
                    <Form class="form-inline  justify-content-center mb-5"
                          method="post"
                          action="${pageContext.request.contextPath}/Member/MemberServlet"
                          name="searchfrom">
                        <input class="form-control mr-sm-2 w-50 mb-1" type="text" placeholder="搜尋. ."
                               aria-label="Search" name="search">
                        <select id="inputState" class="form-control btn-outline-warning " style="width: 15%"
                                name="Search">
                            <option value="searchMember">會員</option>
                            <option value="searchStore">店家</option>
                        </select>
                        <input type="hidden" name="action" value="Search">
                        <button class="btn btn-outline-warning my-sm-0 my-5"
                                type="submit" style="width: 15%">收尋
                        </button>
                    </form>

                </div>
                <!-- 廣告牆 -->
                <div id="carouselExampleCaptions"
                     class="carousel slide card-body hero m-5 h-75"
                     data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#carouselExampleCaptions" data-slide-to="0"
                            class="active"></li>
                        <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
                        <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
                        <li data-target="#carouselExampleCaptions" data-slide-to="3"></li>
                        <li data-target="#carouselExampleCaptions" data-slide-to="4"></li>
                        <li data-target="#carouselExampleCaptions" data-slide-to="5"></li>
                        <li data-target="#carouselExampleCaptions" data-slide-to="6"></li>
                        <li data-target="#carouselExampleCaptions" data-slide-to="7"></li>
                        <li data-target="#carouselExampleCaptions" data-slide-to="8"></li>
                    </ol>
                    <div class="carousel-inner" style="height: 100%">
                        <div class="carousel-item active h-100 p-auto">
                            <img src="/CGA105G2/assets/images/nig.png"
                                 class="d-block  h-100 w-auto "
                                 style="min-width: auto; margin: 0 auto; position: static; !important"
                                 alt="...">
                            <div class="carousel-caption d-none d-md-block h-25">
                                <h5>1.阿雄麵店</h5>
                                <p>歡迎聚餐、聚餐、喝酒</p>
                            </div>
                        </div>
                        <div class="carousel-item h-100 p-auto">
                            <img src="/CGA105G2/assets/images/tenor.gif"
                                 class="d-block  h-100 w-auto "
                                 style="min-width: auto; margin: 0 auto; position: static; !important"
                                 alt="...">
                            <div class="carousel-caption d-none d-md-block h-25">
                                <h5>2.麥當勞歡唱無限</h5>
                                <p>歡迎聚餐、聚餐、喝酒</p>
                            </div>
                        </div>
                        <div class="carousel-item h-100 p-auto">
                            <img src="/CGA105G2/assets/images/nig.png"
                                 class="d-block  h-100 w-auto "
                                 style="min-width: auto; margin: 0 auto; position: static; !important"
                                 alt="...">
                            <div class="carousel-caption d-none d-md-block h-25">
                                <h5>3.偉育尾牙聚餐</h5>
                                <p>歡迎聚餐、聚餐、喝酒</p>
                            </div>
                        </div>
                        <div class="carousel-item h-100 p-auto">
                            <img src="/CGA105G2/assets/images/nig.png"
                                 class="d-block  h-100 w-auto "
                                 style="min-width: auto; margin: 0 auto; position: static; !important"
                                 alt="...">
                            <div class="carousel-caption d-none d-md-block h-25">
                                <h5>4.???</h5>
                                <p>歡迎聚餐、聚餐、喝酒</p>
                            </div>
                        </div>
                        <div class="carousel-item h-100 p-auto">
                            <img src="/CGA105G2/assets/images/nig.png"
                                 class="d-block  h-100 w-auto "
                                 style="min-width: auto; margin: 0 auto; position: static; !important"
                                 alt="...">
                            <div class="carousel-caption d-none d-md-block h-25">
                                <h5>5.???</h5>
                                <p>歡迎聚餐、聚餐、喝酒</p>
                            </div>
                        </div>
                        <div class="carousel-item h-100 p-auto">
                            <img src="/CGA105G2/assets/images/tenor.gif"
                                 class="d-block  h-100 w-auto "
                                 style="min-width: auto; margin: 0 auto; position: static; !important"
                                 alt="...">
                            <div class="carousel-caption d-none d-md-block h-25">
                                <h5>6.???</h5>
                                <p>歡迎聚餐、聚餐、喝酒</p>
                            </div>
                        </div>
                        <div class="carousel-item h-100 p-auto">
                            <img src="/CGA105G2/assets/images/nig.png"
                                 class="d-block  h-100 w-auto "
                                 style="min-width: auto; margin: 0 auto; position: static; !important"
                                 alt="...">
                            <div class="carousel-caption d-none d-md-block h-25">
                                <h5>7.麥當勞歡唱無限</h5>
                                <p>歡迎聚餐、聚餐、喝酒</p>
                            </div>
                        </div>
                        <div class="carousel-item h-100 p-auto">
                            <img src="/CGA105G2/assets/images/tenor.gif"
                                 class="d-block  h-100 w-auto "
                                 style="min-width: auto; margin: 0 auto; position: static; !important"
                                 alt="...">
                            <div class="carousel-caption d-none d-md-block h-25">
                                <h5>8.???</h5>
                                <p>歡迎聚餐、聚餐、喝酒</p>
                            </div>
                        </div>
                        <div class="carousel-item h-100 p-auto">
                            <img src="/CGA105G2/assets/images/nig.png"
                                 class="d-block  h-100 w-auto "
                                 style="min-width: auto; margin: 0 auto; position: static; !important"
                                 alt="...">
                            <div class="carousel-caption d-none d-md-block h-25">
                                <h5>9.???</h5>
                                <p>歡迎聚餐、聚餐、喝酒</p>
                            </div>
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleCaptions"
                       role="button" data-slide="prev"> <span
                            class="carousel-control-prev-icon" aria-hidden="true"></span> <span
                            class="sr-only">Previous</span>
                    </a> <a class="carousel-control-next" href="#carouselExampleCaptions"
                            role="button" data-slide="next"> <span
                        class="carousel-control-next-icon" aria-hidden="true"></span> <span
                        class="sr-only">Next</span>
                </a>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- main -->
<!-- footer start -->
<%@ include file="/front-end/store/01h/footerin.jsp" %>
<!-- footer end -->
<!-- sidebar menu Class -->
<script>
</script>

<script>
    let a = new StickySidebar("#sidebar", {
        topSpacing: 40,
        bottomSpacing: 20,
        containerSelector: ".container",
        innerWrapperSelector: ".sidebar__inner"
    });
</script>
</body>

</html>
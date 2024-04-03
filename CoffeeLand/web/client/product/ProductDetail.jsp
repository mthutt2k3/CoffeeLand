<%-- 
    Document   : ProductDetail
    Created on : Mar 1, 2024, 11:58:00 PM
    Author     : acer
--%>

<%@page import="model.Information.*"%>
<%@page import="model.Users.Users"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*"%>
<%@page import="model.Product.*"%>
<%@page import="model.Feedback.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Category.*"%>
<%@ page import="model.CartClient.CartClientDAO" %>
<%@ page import="model.CartClient.CartClient" %>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
CartClientDAO ccD = new CartClientDAO();
int countCart=0;
    
    session = request.getSession(false);
    Users xUser = new Users();
    if (session.getAttribute("user") != null) {
        xUser = (Users) session.getAttribute("user");
        countCart=ccD.countCartById(xUser.getUserId());
    }else {xUser=null;}
    if (session.getAttribute("x") != null){
        session.removeAttribute("x");
    }
    InformationDAO iDAO = new InformationDAO();
    Informations info =(Informations) iDAO.getInformation("1");

%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Chi tiết sản phẩm</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet"> 

        <!-- Icon Font Stylesheet -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="/CoffeeLand/assestsClient/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
        <link href="/CoffeeLand/assestsClient/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


        <!-- Customized Bootstrap Stylesheet -->
        <link href="/CoffeeLand/assestsClient/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="/CoffeeLand/assestsClient/css/style.css" rel="stylesheet">
        <style>
            .selected {
                background-color: lightgreen;
            }
            .dropdown-menu {
                display: none;
                position: absolute;
                z-index: 1000;
            }

            .dropdown-menu.show {
                display: block;
            }
            /* Đẩy thanh dropdown sang bên trái */
.dropdown {
    position: relative;
}

.dropdown-menu {
    left: -100%; /* Đẩy sang bên trái */
    margin-top: 0; /* Đảm bảo dropdown menu nằm ngang với biểu tượng */
}

/* Màu chính và font chữ */
.dropdown-menu {
    background-color: #E0F2F1; /* Màu xanh nhạt */
    border: none;
    border-radius: 0;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    font-family: Arial, sans-serif;
    width: 190px;
    left: -100%; /* Đẩy sang bên trái */
    margin-top: 0;
}

/* Màu chữ và hiệu ứng hover */
.dropdown-menu a {
    color: #424242; /* Màu xám đậm */
    padding: 12px 20px;
    text-decoration: none;
    display: block;
}

.dropdown-menu a:hover {
    background-color: #81C784; /* Màu xanh lá nhạt khi hover */
}

/* Ẩn danh sách dropdown ban đầu */
.dropdown-menu {
    display: none;
}

/* Hiển thị danh sách dropdown khi nhấp vào */
.dropdown:hover .dropdown-menu {
    display: block;
}
        </style>
    </head>

    <body>
        <%int count=0;%>
        <%@include file="/navigator/ToastClient.jsp" %>
        <!-- Spinner Start -->
        <div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
            <div class="spinner-grow text-primary" role="status"></div>
        </div>
        <!-- Spinner End -->


        <!-- Navbar start -->
<div class="container-fluid fixed-top">
            <div class="container topbar bg-primary d-none d-lg-block">
                <div class="d-flex justify-content-between">
                    <div class="top-info ps-2">
                        <small class="me-3"><i class="fas fa-map-marker-alt me-2 text-secondary"></i> <a href="#" class="text-white"><%=info.getAddress()%></a></small>
                        <small class="me-3"><i class="fas fa-envelope me-2 text-secondary"></i><a href="#" class="text-white"><%=info.getContactEmail()%></a></small>
                    </div>
                    <div class="top-link pe-2">
                        <%if(xUser == null){%>
                        <a href="/CoffeeLand/loginclient" class="text-white"><small class="text-white mx-2">Đăng nhập</small>/</a>
                        <a href="/CoffeeLand/signup" class="text-white"><small class="text-white mx-2">Đăng ký</small></a>
                        <%}else{%>
                        <a href="/CoffeeLand/common/Profile.jsp" class="text-white"><small class="text-white ms-2"><%=xUser.getName()%></small> /</a>
                        <a href="/CoffeeLand/logoutclient" class="text-white"><small class="text-white mx-2">Đăng xuất</small></a>
                        <%}%>
                    </div>
                </div>
            </div>
            <div class="container px-0">
                <nav class="navbar navbar-light bg-white navbar-expand-xl">
                    <a href="/CoffeeLand/client/Homepage.jsp" class="navbar-brand"><h1 class="text-primary display-6"><%=info.getNameStore()%></h1></a>
                    <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars text-primary"></span>
                    </button>
                    <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                        <div class="navbar-nav mx-auto">
                            <a href="/CoffeeLand/client/Homepage.jsp" class="nav-item nav-link">Trang chủ</a>
                            <a href="/CoffeeLand/client/productlist" class="nav-item nav-link active">Sản phẩm</a>
                            <a href="/CoffeeLand/newslistclient" class="nav-item nav-link">Tin tức</a>
                            <a href="/CoffeeLand/listvoucherclient" class="nav-item nav-link">Khuyến mãi</a>

                        </div>
                        <div class="d-flex m-3 me-0">
                            

                            <%if(xUser != null){%>
                            <a href="/CoffeeLand/client/cart" class="position-relative me-4 my-auto">
                                <i class="fa fa-shopping-bag fa-2x"></i>
                                <span class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1" style="top: -5px; left: 15px; height: 20px; min-width: 20px;"><%=countCart%></span>
                            </a>
                            <div class="dropdown">
                                <a href="#" id="userDropdown" class="my-auto dropdown-toggle">
                                    <i class="fas fa-user fa-2x"></i>
                                </a>
                                <ul id="dropdownMenu" class="dropdown-menu">
                                    <li><a href="/CoffeeLand/common/Profile.jsp"><i class="fas fa-user"></i> Thông tin cá nhân</a></li>
                                    <li><a href="/CoffeeLand/client/cart"><i class="fas fa-shopping-cart"></i> Giỏ hàng</a></li>
                                    <li><a href="/CoffeeLand/orderhistoryforcus"><i class="fas fa-history"></i> Lịch sử mua hàng</a></li>
                                    <li><a href="/CoffeeLand/logoutclient"><i class="fas fa-sign-out-alt"></i> Đăng xuất</a></li>
                                </ul>
                            </div>
                            <%}%>


                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <!-- Navbar End -->


        <!-- Modal Search Start -->
        <div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-fullscreen">
                <div class="modal-content rounded-0">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Tìm kiếm bởi từ khóa</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body d-flex align-items-center">
                        <div class="input-group w-75 mx-auto d-flex">
                            <input type="search" class="form-control p-3" placeholder="Tìm kiếm..." aria-describedby="search-icon-1">
                            <span id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal Search End -->


        <!-- Single Page Header start -->
        <div class="container-fluid page-header py-5">
            <h1 class="text-center text-white display-6">Chi tiết sản phẩm</h1>
            <ol class="breadcrumb justify-content-center mb-0">
                <li class="breadcrumb-item"><a href="/CoffeeLand/homepage">Trang chủ</a></li>
                <li class="breadcrumb-item"><a href="/CoffeeLand/client/productlist">Sản phẩm</a></li>
                <li class="breadcrumb-item active text-white">${product.getProductName()}</li>
            </ol>
        </div>
        <!-- Single Page Header End -->


        <!-- Single Product Start -->
        <div class="container-fluid mt-5" style="padding: 0px 0px 0px 0px;">
            <div class="container py-5">
                <div class="row g-4 mb-5">
                    <div class="col-lg-8 col-xl-9">
                        <div class="row g-4">
                            <div class="col-lg-6">
                                <div class="border rounded">
                                    <a href="#">
                                        <img src="/CoffeeLand/assests/image/thumbnails/${product.getThumbnail()}" class="img-fluid rounded" alt="Image">
                                    </a>
                                </div>
                            </div>

                            <div class="col-lg-6">
                                <%if(xUser!=null){%>
                                <form action="/CoffeeLand/client/addtocart" method="GET">
                                    <%}else{%>
                                    <form action="/CoffeeLand/loginclient" method="GET">
                                        <%}%>
                                        <input type="hidden" name="productId" value="${product.getProductId()}">
                                        <h4 class="fw-bold mb-3">${product.getProductName()}</h4>
                                        <p class="mb-3">Loại sản phẩm: ${product.getCategoryName()}</p>
                                        <h5 class="fw-bold mb-3">${firstPrice} ${lastPrice}</h5>
                                        <div class="d-flex mb-4">
                                            <c:choose>
                                                <c:when test="${product.getRatedStar() == 5}">
                                                    <c:forEach var="i" begin="0" end="4">
                                                        <i class="fa fa-star text-secondary"></i>
                                                    </c:forEach>
                                                </c:when>
                                                <c:when test="${product.getRatedStar() == 0}">
                                                    <c:forEach var="i" begin="0" end="4">
                                                        <i class="fa fa-star"></i>
                                                    </c:forEach>
                                                </c:when>        
                                                <c:otherwise>
                                                    <c:forEach var="i" begin="0" end="${product.getRatedStar()-1}">
                                                        <i class="fa fa-star text-secondary"></i>
                                                    </c:forEach>
                                                    <c:forEach var="i" begin="0" end="${5-product.getRatedStar()-1}">
                                                        <i class="fa fa-star"></i>
                                                    </c:forEach>
</c:otherwise>
                                            </c:choose>
                                        </div>
                                        <p class="mb-4">
                                        <div style="margin-bottom: 15px; display: flex; align-items: center">
                                            <div class="size-selection">
                                                <div class="size-buttons">
                                                    <%int x=0; %>
                                                    <c:forEach items="${splst}" var="sp">
                                                        <%if(x==0){%>
                                                        <button type="button" class="size-button selected" onclick="setSize('${sp.getSizeName()}')" data-size="${sp.getSizeName()}" data-price="${sp.getPrice()}">${sp.getSizeName()}</button>
                                                        <%x=1;}else{%>
                                                        <button type="button" class="size-button" onclick="setSize('${sp.getSizeName()}')" data-size="${sp.getSizeName()}" data-price="${sp.getPrice()}">${sp.getSizeName()}</button>
                                                        <%}%>
                                                    </c:forEach>
                                                </div>
                                                    <c:set var="firstElement" value="${splst[0]}" />
                                                    
                                                <div id="selected-size-info" style="margin-top: 15px">
                                                    <p><span id="selected-size">${firstElement.getSizeName()}</span> - Giá: <span id="selected-price">${firstElement.getPrice()}</span></p>
                                                </div>
                                            </div>
                                        </div>
                                        <input type="hidden" id="selected-size-input" name="selectedSize" value="${firstElement.getSizeName()}">

                                        </p>
                                        <div class="input-group quantity mb-5" style="width: 100px;">
                                            <div class="input-group-btn">
                                                <button type="button" class="btn btn-sm btn-minus rounded-circle bg-light border" >
                                                    <i class="fa fa-minus"></i>
                                                </button>
                                            </div>
                                            <input type="text" class="form-control form-control-sm text-center border-0" value="1" name="quantity">
                                            <div class="input-group-btn">
                                                <button type="button" class="btn btn-sm btn-plus rounded-circle bg-light border">
                                                    <i class="fa fa-plus"></i>
                                                </button>
                                            </div>    
                                        </div>

                                        <button type="submit" class="btn border border-secondary rounded-pill px-4 py-2 mb-4 text-primary btn-add-to-cart"><i class="fa fa-shopping-bag me-2 text-primary"></i> Thêm vào giỏ hàng</button>

                                    </form>

                            </div>



                            <div class="col-lg-12">
                                <nav>
<div class="nav nav-tabs mb-3">
                                        <button class="nav-link active border-white border-bottom-0" type="button" role="tab"
                                                id="nav-about-tab" data-bs-toggle="tab" data-bs-target="#nav-about"
                                                aria-controls="nav-about" aria-selected="true">Mô tả</button>
                                        <button class="nav-link border-white border-bottom-0" type="button" role="tab"
                                                id="nav-mission-tab" data-bs-toggle="tab" data-bs-target="#nav-mission"
                                                aria-controls="nav-mission" aria-selected="false">Đánh giá</button>
                                    </div>
                                </nav>
                                <div class="tab-content mb-5">
                                    <div class="tab-pane active" id="nav-about" role="tabpanel" aria-labelledby="nav-about-tab">
                                        <p>${product.getDescription()}</p>
                                    </div>
                                    <div class="tab-pane" id="nav-mission" role="tabpanel" aria-labelledby="nav-mission-tab">
                                        <%
                                    Integer proID = (Integer) session.getAttribute("productId");
                                        
                                    FeedbackDAO_1 fbd = new FeedbackDAO_1();
                                    List<Feedback_1> listF = fbd.getAllFeedbackTOP3PID(proID);
                                    if(listF.isEmpty()){
                                        %><p>Chưa có đánh giá nào cho sản phẩm này</p><%
                                    }
                                    for(Feedback_1 fb: listF){
                                    String name = fbd.getNameFromUserID(fb.getUserid());
                                    String image = fbd.getImageByUserId(fb.getUserid());
if(image!=null){
                                        %>

                                        <div class="d-flex">
                                            <img src="/CoffeeLand/assestsClient/img/avatar.jpg" class="img-fluid rounded-circle p-3" style="width: 100px; height: 100px;" alt="">
                                            <%}else{%>
                                            <div class="d-flex">
                                                <img src="/CoffeeLand/assestsClient/img/avatar.jpg" class="img-fluid rounded-circle p-3" style="width: 100px; height: 100px;" alt="">
                                                <%}%>
                                                <div class="">
                                                    <p class="mb-2" style="font-size: 14px;"><%=fb.getFeedbackTime()%></p>
                                                    <div class="d-flex justify-content-between">
<h5><%=name%></h5>
                                                        <p>
                                                            <% 
                                                            int ratedStar = fb.getRatedStar();
                                                            for (int i = 0; i < ratedStar; i++) {
                                                            %>
                                                            <i style="color: yellow;" class="fa fa-star star"></i>
                                                            <% } %>
                                                        </p>
                                                    </div>
                                                    <p><%=fb.getMessage()%> </p>
                                                </div>
                                            </div>
                                            <%}%>
                                            <div class="allfeedback">
                                                <form action="/CoffeeLand/getallfeedback" method="post">
                                                    <input type="hidden" name="productID" value="${product.getProductId()}"/>
                                                    <input style="border-radius:10px; color: white; background-color: green; border: solid 1px green" type="submit" value="Xem toàn bộ đánh giá"/>
                                                </form>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-4 col-xl-3">
                            <div class="row g-4 fruite">
                                <div class="col-lg-12">
                                    <form id="searchForm" action="/CoffeeLand/client/searchproductlist" method="GET">
                                    <div class="input-group w-100 mx-auto d-flex">
                                        <input id="searchInput" type="search" class="form-control p-3" placeholder="Tìm kiếm..." aria-describedby="search-icon-1" name="textSearch" value="${txtSearch}">
                                        <button id="searchButton" type="submit" class="input-group-text p-3" aria-label="Tìm kiếm"><i class="fa fa-search"></i></button>
                                    </div>
                                </form>
                                    <div class="mb-4">
                                        <h4>Danh mục</h4>
                                        <ul class="list-unstyled fruite-categorie">
                                            <li>
                                                <div class="d-flex justify-content-between fruite-name">
                                                    <a href="#"><i class="fas fa-apple-alt me-2"></i>Tất cả</a>
                                                    <span>(${totalProduct})</span>
                                                </div>
                                            </li>
<c:forEach items="${lstCategory}" var="c">
                                                <li>
                                                    <div class="d-flex justify-content-between fruite-name">
                                                        <a href="/CoffeeLand/client/filterproductlist?target=category&&categoryId=${c.getCategoryId()}"><i class="fas fa-apple-alt me-2"></i>${c.getCategoryName()}</a>
                                                        <span>(${c.getCountProduct()})</span>
                                                    </div>
                                                </li>    
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <h4 class="mb-4">Sản phẩm nổi bật</h4>
                                    <c:forEach items="${top5Product}" var="p">
                                        <div class="d-flex align-items-center justify-content-start">
                                            <div class="rounded" style="width: 100px; height: 100px;">
                                                <img src="/CoffeeLand/assests/image/thumbnails/${p.getThumbnail()}" style="width: 75px; height: 75px" class="img-fluid rounded" alt="Image">
                                            </div>
                                            <div>
                                                <h6 class="mb-2">${p.getProductName()}</h6>
                                                <div class="d-flex mb-2">
                                                    <c:choose>
                                                        <c:when test="${p.getRatedStar() == 5}">
                                                            <c:forEach var="i" begin="0" end="4">
                                                                <i class="fa fa-star text-secondary"></i>
                                                            </c:forEach>
                                                        </c:when>
                                                        <c:when test="${p.getRatedStar() == 0}">
                                                            <c:forEach var="i" begin="0" end="4">
                                                                <i class="fa fa-star"></i>
                                                            </c:forEach>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:forEach var="i" begin="0" end="${p.getRatedStar()-1}">
                                                                <i class="fa fa-star text-secondary"></i>
</c:forEach>
                                                            <c:forEach var="i" begin="0" end="${5-p.getRatedStar()-1}">
                                                                <i class="fa fa-star"></i>
                                                            </c:forEach>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <div class="d-flex mb-2">
                                                    <h5 class="fw-bold me-2">${p.getPrice()} đ</h5>
                                                </div>
                                            </div>
                                        </div>   
                                    </c:forEach>
                                    <div class="d-flex justify-content-center my-4">
                                        <a href="/CoffeeLand/client/sortproductlist?productlist=most" class="btn border border-secondary px-4 py-3 rounded-pill text-primary w-100">Xem thêm</a>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <h1 class="fw-bold mb-0">Sản phẩm liên quan</h1>
                        <div class="vesitable">
                            <div class="owl-carousel vegetable-carousel justify-content-center">
                                <c:forEach items="${relatedProduct}" var="r">
                                    <div class="border border-primary rounded position-relative vesitable-item">
                                        <div class="vesitable-img">
                                            <img src="/CoffeeLand/assests/image/thumbnails/${r.getThumbnail()}" class="img-fluid w-100 rounded-top" alt="">
                                        </div>
                                        <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">${r.getCategoryName()}</div>
                                        <div class="p-4 pb-0 rounded-bottom">
                                            <h4>${r.getProductName()}</h4>
                                            <div class="d-flex justify-content-between flex-lg-wrap">
                                                <p class="text-dark fs-5 fw-bold">${r.getPrice()} đ</p>
                                                <a href="/CoffeeLand/client/productdetail?productId=${r.getProductId()}" class="btn border border-secondary rounded-pill px-3 py-1 mb-4 text-primary"><i class="fas fa-eye text-primary mr-1"></i> Xem thêm</a>
                                            </div>
</div>
                                    </div>

                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Single Product End -->



                <!-- Footer Start -->
                <div class="container-fluid bg-dark text-white-50 footer pt-5 mt-5">
                    <div class="container py-5">
                        <div class="row g-5">
                            <div class="col-lg-3 col-md-6">
                                <div class="footer-item">
                                    <a href="#">
                                        <h1 class="text-primary mb-0"><%=info.getNameStore()%></h1>
                                        <p class="text-secondary mb-0">Sản phẩm thức uống</p>
                                    </a>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-6">
                                <div class="d-flex flex-column text-start footer-item">
                                    <h4 class="text-light mb-3">Về cửa hàng</h4>
                                    <a class="btn-link" href="">Trang chủ</a>
                                    <a class="btn-link" href="">Danh sách sản phẩm</a>
                                    <a class="btn-link" href="">Tin tức</a>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-6">
                                <div class="d-flex flex-column text-start footer-item">
                                    <h4 class="text-light mb-3">Tài khoản</h4>
                                    <a class="btn-link" href="">Tài khoản của tôi</a>
                                    <a class="btn-link" href="">Giỏ hàng</a>
                                    <a class="btn-link" href="">Lịch sử mua hàng</a>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-6">
                                <div class="footer-item">
                                    <h4 class="text-light mb-3">Liên hệ</h4>
                                    <p>Địa chỉ: <%=info.getAddress()%></p>
                                    <p>Email: <%=info.getContactEmail()%></p>
                                    <p>Số điện thoại: <%=info.getContactPhone()%></p>
                                </div>
                                <div class="d-flex justify-content-begin pt-3">
                                    <a class="btn  btn-outline-secondary me-2 btn-md-square rounded-circle" href=""><i class="fab fa-twitter"></i></a>
                                    <a class="btn btn-outline-secondary me-2 btn-md-square rounded-circle" href="https://www.facebook.com/m.thutrant.fpt"><i class="fab fa-facebook-f"></i></a>
<a class="btn btn-outline-secondary me-2 btn-md-square rounded-circle" href=""><i class="fab fa-youtube"></i></a>
                                    <a class="btn btn-outline-secondary btn-md-square rounded-circle" href=""><i class="fab fa-linkedin-in"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Footer End -->

                <!-- Copyright Start -->
                <div class="container-fluid copyright bg-dark py-4">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
                                <span class="text-light"><a href="#"><i class="fas fa-copyright text-light me-2"></i><%=info.getNameStore()%></a>, đảm bảo mọi quyền lợi của khách hàng.</span>
                            </div>
                            <div class="col-md-6 my-auto text-center text-md-end text-white">
                                <!--/*** This template is free as long as you keep the below author’s credit link/attribution link/backlink. ***/-->
                                <!--/*** If you'd like to use the template without the below author’s credit link/attribution link/backlink, ***/-->
                                <!--/*** you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". ***/-->
                                Thiết kế bởi <a class="border-bottom" href="https://www.facebook.com/truongnd2201">team CoffeeLand</a> đóng góp bởi <a class="border-bottom" href="https://www.facebook.com/m.thutrant.fpt">Trần Thư</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Copyright End -->



                <!-- Back to Top -->
                <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>   


                <!-- JavaScript Libraries -->
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
                <script src="/CoffeeLand/assestsClient/lib/easing/easing.min.js"></script>
                <script src="/CoffeeLand/assestsClient/lib/waypoints/waypoints.min.js"></script>
                <script src="/CoffeeLand/assestsClient/lib/lightbox/js/lightbox.min.js"></script>
                <script src="/CoffeeLand/assestsClient/lib/owlcarousel/owl.carousel.min.js"></script>

                <!-- Template Javascript -->
                <script src="/CoffeeLand/assestsClient/js/main.js"></script>
                <script>
document.addEventListener("DOMContentLoaded", function () {
                                                                    const sizeButtons = document.querySelectorAll(".size-button");

                                                                    sizeButtons.forEach(function (button) {
                                                                        button.addEventListener("click", function () {
                                                                            <%count++;%>
                                                                            // Loại bỏ lớp 'selected' từ tất cả các nút
                                                                            sizeButtons.forEach(function (btn) {
                                                                                btn.classList.remove("selected");
                                                                            });

                                                                            // Thêm lớp 'selected' cho nút đã được chọn
                                                                            this.classList.add("selected");

                                                                            const selectedSize = this.getAttribute("data-size");
                                                                            const selectedPrice = this.getAttribute("data-price");

                                                                            document.getElementById("selected-size").textContent = selectedSize;
                                                                            document.getElementById("selected-price").textContent = selectedPrice;
                                                                        });
                                                                    });
                                                                });
                                                                document.getElementById("userDropdown").addEventListener("click", function (event) {
                                                                    event.preventDefault();
                                                                    var dropdownMenu = document.getElementById("dropdownMenu");
                                                                    dropdownMenu.classList.toggle("show");
                                                                });
                                                                function setSize(selectedSize) {
                                                                    document.getElementById("selected-size-input").value = selectedSize;
                                                                }
                </script>
                </body>

                </html>
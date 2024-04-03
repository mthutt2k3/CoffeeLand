<%-- 
    Document   : ChangePassword
    Created on : Oct 8, 2023, 2:19:36 PM
    Author     : lap21
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Client.Category" %>
<%@ page import="model.Users.Users" %>
<%@ page import="model.Client.Product" %>
<%@ page import="model.Client.ProductImage" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="model.Client.ClientDAO" %>
<%@ page import="model.Information.InformationDAO" %>
<%@ page import="model.Information.Informations" %>
<%@ page import="model.Slider.Slider" %>
<%@ page import="model.Slider.SliderDAO" %>
<%@ page import="model.NewsClient.News" %>
<%@ page import="model.NewsClient.NewsDAO" %>
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
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">
        <!-- Thư viện CSS -->
        <!-- Thư viện CSS -->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.min.css">
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
            h1{
                color: #65B741;
            }
            /* Màu đỏ cho thông báo lỗi */
            h3 {
                color: red;
            }

            /* Màu xanh cho thông báo thành công */
            h3.success {
                color: green;
            }

            /* Thiết lập style cho form */
            /* Thiết lập style cho form */
            .change2{
                margin-top: 200px;
                margin-left: 400px /* Căn giữa nội dung trong phần tử */
            }
            
            .change form {
                margin-top: 20px;
            }

            /* Thiết lập style cho các phần nhập */
            .change input[type="password"] {
                width: 60%;
                padding: 10px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }

            /* Thiết lập style cho nút submit */
            .change input[type="submit"] {
                width: 60%;
                padding: 10px;
                border: none;
                border-radius: 5px;
                background-color: #65B741; /* Màu nút mặc định */
                color: #fff;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            /* Hover effect cho nút submit */
            .change input[type="submit"]:hover {
                background-color: #0056b3; /* Màu nút khi hover */
            }
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
        <title>Change Password|WebMaster</title>
    </head>
    <body>
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
                            <button class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4" data-bs-toggle="modal" data-bs-target="#searchModal"><i class="fas fa-search text-primary"></i></button>


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
                                    <li><a href="/CoffeeLand/common/ProfileClient.jsp">Thông tin cá nhân</a></li>
                                    <li><a href="/CoffeeLand/client/cart">Giỏ hàng</a></li>
                                    <li><a href="/CoffeeLand/orderhistoryforcus">Lịch sử mua hàng</a></li>
                                    <li><a href="/CoffeeLand/logoutclient">Đăng xuất</a></li>
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
        <div class="change2">
            <h1>Thay đổi mật khẩu</h1>
            <h3 class="${requestScope.er.equals("Thay đổi password thành công!") ? "success" : ""}">
                ${requestScope.er}
            </h3>
            <div class="change">
                <form action="changepasswordclient" method="post">
                    <p>Nhập mật khẩu cũ<span style="color: red">*</span></p><input type="password" name="pass" placeholder="Nhập mật khẩu cũ vào đây..." required/>
                    <p>Nhập mật khẩu mới<span style="color: red">*</span></p><input type="password" name="newpass" placeholder="Nhập mật khẩu mới vào đây..." required/>
                    <p>Nhập lại mật khẩu mới<span style="color: red">*</span></p><input type="password" name="renewpass" placeholder="Nhập mật khẩu lại mật khẩu mới vào đây..." required/>
                    <input type="submit" value="Thay đổi"/>
                </form>
            </div>
        </div>
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
                            <a class="btn-link" href="/CoffeeLand/client/Homepage.jsp">Trang chủ</a>
                            <a class="btn-link" href="/CoffeeLand/client/productlist">Danh sách sản phẩm</a>
                            <a class="btn-link" href="/CoffeeLand/newslistclient">Tin tức</a>
                            <a class="btn-link" href="/CoffeeLand/listvoucherclient">Khuyến mãi</a>
                        </div>
                    </div>
                    <%if(xUser != null){%>        
                    <div class="col-lg-3 col-md-6">
                        <div class="d-flex flex-column text-start footer-item">
                            <h4 class="text-light mb-3">Tài khoản</h4>
                            <a class="btn-link" href="/CoffeeLand/common/Profile.jsp">Tài khoản của tôi</a>
                            <a class="btn-link" href="/CoffeeLand/client/cart">Giỏ hàng</a>
                            <a class="btn-link" href="/CoffeeLand/orderhistoryforcus">Lịch sử mua hàng</a>
                        </div>
                    </div>
                    <%}else{%>
                    <div class="col-lg-3 col-md-6"></div>
                    <%}%>
                    <div class="col-lg-3 col-md-6">
                        <div class="footer-item">
                            <h4 class="text-light mb-3">Liên hệ</h4>
                            <p>Địa chỉ: <%=info.getAddress()%></p>
                            <p>Email: <%=info.getContactEmail()%></p>
                            <p>Số điện thoại: <%=info.getContactPhone()%></p>
                        </div>
                        <div class="d-flex justify-content-begin pt-3">
                            <a class="btn  btn-outline-secondary me-2 btn-md-square rounded-circle" href=""><i class="fab fa-twitter"></i></a>
                            <a class="btn btn-outline-secondary me-2 btn-md-square rounded-circle" href=""><i class="fab fa-facebook-f"></i></a>
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

        <!-- JavaScript imports -->
        <script src="/CoffeeLand/assestsClient/news_template/js/jquery.min.js"></script>
        <script src="/CoffeeLand/assestsClient/news_template/js/jquery-migrate-3.0.1.min.js"></script>
        <script src="/CoffeeLand/assestsClient/news_template/js/popper.min.js"></script>
        <script src="/CoffeeLand/assestsClient/news_template/js/bootstrap.min.js"></script>
        <script src="/CoffeeLand/assestsClient/news_template/js/jquery.easing.1.3.js"></script>
        <script src="/CoffeeLand/assestsClient/news_template/js/jquery.waypoints.min.js"></script>
        <script src="/CoffeeLand/assestsClient/news_template/js/jquery.stellar.min.js"></script>
        <script src="/CoffeeLand/assestsClient/news_template/js/owl.carousel.min.js"></script>
        <script src="/CoffeeLand/assestsClient/news_template/js/jquery.magnific-popup.min.js"></script>
        <script src="/CoffeeLand/assestsClient/news_template/js/jquery.animateNumber.min.js"></script>
        <script src="/CoffeeLand/assestsClient/news_template/js/scrollax.min.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
        <script src="/CoffeeLand/assestsClient/news_template/js/google-map.js"></script>
        <script src="/CoffeeLand/assestsClient/news_template/js/main.js"></script>


        <!-- Template Javascript -->
        <script src="/CoffeeLand/assestsClient/js/main.js"></script>
    </body>
</html>

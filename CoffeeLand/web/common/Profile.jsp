<%-- 
    Document   : Profile
    Created on : Jan 27, 2024, 5:48:00 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.Users.*" %>
<%@ page import="javax.servlet.http.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="model.News.News" %>
<%@page import="model.Information.*"%>
<%@page import="model.Users.Users"%>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    session = request.getSession(false);
    Users xUser = new Users();
    if (session.getAttribute("user") != null) {
        xUser = (Users) session.getAttribute("user");
        session.removeAttribute("x");
    } else {
        response.sendRedirect("/CoffeeLand/server/Users/login.jsp");
    }
    InformationDAO iDAO = new InformationDAO();
    Informations info =(Informations) iDAO.getInformation("1");
%>


<!DOCTYPE html>
<html lang="en">
    <head>
        <title>The Coffee Land</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css2?family=Spectral:ital,wght@0,200;0,300;0,400;0,500;0,700;0,800;1,200;1,300;1,400;1,500;1,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="/CoffeeLand/assestsClient/news_template/css/animate.css">
        <link rel="stylesheet" href="/CoffeeLand/assestsClient/news_template/css/owl.carousel.min.css">
        <link rel="stylesheet" href="/CoffeeLand/assestsClient/news_template/css/owl.theme.default.min.css">
        <link rel="stylesheet" href="/CoffeeLand/assestsClient/news_template/css/magnific-popup.css">
        <link rel="stylesheet" href="/CoffeeLand/assestsClient/news_template/css/flaticon.css">
        <link rel="stylesheet" href="/CoffeeLand/assestsClient/news_template/css/style.css">
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
            .img-fluid {
                cursor: pointer;

            }
            .heading{
                cursor: pointer;
            }

            .text{
                margin-bottom: 20px;
                height: 100%;
            }
            .click {
                flex-grow: 1;
                display: flex;
                flex-direction: column;
            }

            .meta {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-top: 15px; /* Adjust as needed */
            }

            .meta p {
                margin-bottom:  10px; /* Remove default margin */
            }


            .news-item-container {
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                height: 100%; /* Ensure each item takes up full height */
            }

            .news-item-container .text {
                flex-grow: 1; /* Allow text content to grow to fill the space */
            }

            .news-item-container .meta {
                margin-top: auto; /* Push the meta info to the bottom */
            }
            .search {
                position: absolute;
                top: 0;
                right: 0;
                margin-bottom: 20px;
                margin-top: 20px;
            }
            .block-27 {
                display: flex;
                justify-content: center;
            }

            .block-27 a {
                text-decoration: none;
                color: black;
                padding: 5px 10px;
                border: 1px solid black;
                background-color: #ffffcc;
                margin: 0 5px;
            }
          
            .myclass{
                text-align: center;
            }
            .button-container {
                display: flex; /* Sử dụng flexbox */
                align-items: center; /* Căn giữa theo chiều dọc */
            }
            .button-container > * {
                margin: 0 10px; /* Khoảng cách 20px giữa các phần tử */
            }
            /* Footer CSS */
            footer {
                position: fixed;
                left: 0;
                bottom: 0;
                width: 100%;
                background-color: #343a40;
                color: white;
                text-align: center;
                padding: 10px 0;
                margin-bottom: 100px;
                z-index: 9999; /* Đặt z-index cao hơn để footer hiển thị trên top */
            }

            /* Bảng thông tin CSS */
            .profile {
                margin-top: 600px;
                position: relative; /* Đặt position thành relative */
             
                left: 50%;
                transform: translate(-50%, -50%);
                width: 50%;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
                background-color: #f9f9f9;
                z-index: 1; /* Đặt z-index thấp hơn để footer hiển thị trên top */
                margin-bottom: 0; /* Thêm margin-bottom để tạo khoảng cách giữa bảng thông tin và footer */
            }







        </style>

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
                            
                            
                            <%if(xUser != null){%>
                            <a href="/CoffeeLand/client/cart" class="position-relative me-4 my-auto">
                                <i class="fa fa-shopping-bag fa-2x"></i>
                                <span class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1" style="top: -5px; left: 15px; height: 20px; min-width: 20px;">3</span>
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
        <script>
            // Lắng nghe sự kiện click trên biểu tượng tìm kiếm
            document.getElementById("searchButton").addEventListener("click", function (event) {
                // Ngăn chặn hành vi mặc định của nút submit
                event.preventDefault();

                // Gửi form
                document.getElementById("searchForm").submit();
            });
        </script>
        <!-- Modal Search End -->
        <div class="profile" >
            <h2 class="myclass">THÔNG TIN</h2>
            <form action="/CoffeeLand/updateprofile" method="get">
                <!-- Các trường dữ liệu -->
                <input type="hidden" value="<%=xUser.getUserId()%>" class="form-control" name="userId">
                <div class="form-group">
                    <label>Tên</label> 
                    <input type="text" value="<%=xUser.getName()%>" class="form-control" name="userName" placeholder="Enter name">
                </div>
                <div class="form-group">
                    <label>Số Điện Thoại</label> 
                    <input type="text" value="<%=xUser.getPhoneNumber()%>" class="form-control" name="phoneNumber" placeholder="Enter phone number" readonly>
                </div>
                <div class="form-group">
                    <label>Email</label> 
                    <input type="text" value="<%=xUser.getEmail()%>" class="form-control" name="email" placeholder="Enter email" readonly>
                </div>
                <div class="form-group">
                    <label>Mật Khẩu</label> 
                    <input type="password" value="<%=xUser.getPassword()%>" class="form-control" name="password" placeholder="Enter password" readonly>
                </div>
                <div class="form-group">
                    <label>Địa Chỉ</label> 
                    <input type="text" value="<%=xUser.getAddress()%>" class="form-control" name="address" placeholder="Enter address">
                </div>
                <div class="form-group">
                    <input type="hidden" value="<%=xUser.getAvatar()%>" class="form-control" name="avatar" placeholder="Upload avatar">
                </div><div class="form-group">
                    <input type="hidden" value="<%=xUser.getRoleId()%>" class="form-control" name="role">
                </div>

                <div class="button-container">


                    <button type="submit" class="btn btn-primary">Lưu</button>

                    <a class="btn btn-primary"  href="/CoffeeLand/homepage">Quay Lại</a>
                    <a class="btn btn-primary"  href="/CoffeeLand/client/ChangePasswordClient.jsp">Đổi Mật Khẩu</a>

                </div>
                <script>
                    function goBack() {
                        window.history.back();
                    }
                </script>
            </form>

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
        <script>
                        document.getElementById("userDropdown").addEventListener("click", function (event) {
                            event.preventDefault();
                            var dropdownMenu = document.getElementById("dropdownMenu");
                            dropdownMenu.classList.toggle("show");
                        });

                        window.addEventListener('click', function (event) {
                            var dropdownMenu = document.getElementById("dropdownMenu");
                            if (!event.target.matches('.dropdown-toggle')) {
                                dropdownMenu.classList.remove("show");
                            }
                        });

        </script>

    </body>
</html>



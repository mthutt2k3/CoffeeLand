<%-- 
    Document   : EditFeedback
    Created on : Mar 12, 2024, 11:44:03 AM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Information.*"%>
<%@page import="model.Users.Users"%>
<%@page import="model.FeedbackClient.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="model.Product.*"%>
<%@page import="model.Category.*"%>
<%@ page import="model.CartClient.CartClientDAO" %>
<%@ page import="model.CartClient.CartClient" %>
<!DOCTYPE html>
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
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            .star {
                color: grey; /* Màu mặc định của các icon sao */
            }

            .star.selected {
                color: yellow; /* Màu khi icon sao được chọn */
            }
            h1{
                margin-top: 10px;
                margin-bottom: 10px;
                font-size: 20px;
                color:green;
                font-weight: bold;
                margin-left: 10px
            }
            .editF{
                margin-top: 200px
            }
            form {
                width: 50%;
                margin-top:20px;
                margin-left: 100px
            }

            /* Định dạng input */
            input[type="file"],
            input[type="text"],
            textarea {
                width: 100%;
                padding: 8px;
                margin: 6px 0;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }

            /* Định dạng nút gửi */
            button[type="submit"] {
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                margin: 8px 0;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                margin-top: -10px
            }
            form label,p{
                color: green;
                font-weight: bold;

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
    </head>
    <body>
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
                                    <li><a href="/CoffeeLand/common/Profile.jsp">Thông tin cá nhân</a></li>
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

        <!-- Modal Search End -->
        <%
         String userId = request.getParameter("userId");
         int userId1 = Integer.parseInt(userId);
         String productId = request.getParameter("productId");
         int productId1 = Integer.parseInt(productId);
         String oldTime = request.getParameter("postedTime");
        %>
        <div class="editF">
            <h1>Sửa đánh giá của bạn tại đây:</h1>

            <form action="/CoffeeLand/editfeedback" method="post" enctype="multipart/form-data" onsubmit="return validateRating()">

                <input type="hidden" name="postedTime" value="<%= oldTime %>"/>
                <input type="hidden" name="userId" value="<%= userId %>"/>
                <input type="hidden" name="productId" value="<%= productId %>"/>
                <div>
                    <label for="feedbackContent">Sửa nội dung phản hồi:</label><br>
                    <textarea id="feedbackContent" name="feedbackContent" rows="4" cols="50" required></textarea>
                </div>

                <!-- Upload hình ảnh -->
                <div>
                    <label for="imageUpload">Chọn hình ảnh để sửa (nếu có):</label><br>
                    <input type="file" id="imageUpload" name="imageUpload" accept="image/*">
                </div>

                <input type="hidden" id="rating" name="rating" value="0">

                <!-- Hiển thị các icon sao -->
                <div class="d-flex justify-content-between py-3 mb-5">
                    <div class="d-flex align-items-center">
                        <p class="mb-0 me-3">Sửa chấm điểm:</p>
                        <div class="d-flex align-items-center" style="font-size: 12px;">
                            <i class="fa fa-star star" data-value="1"></i>
                            <i class="fa fa-star star" data-value="2"></i>
                            <i class="fa fa-star star" data-value="3"></i>
                            <i class="fa fa-star star" data-value="4"></i>
                            <i class="fa fa-star star" data-value="5"></i>
                        </div>
                    </div>
                </div>
                <!-- Nút gửi phản hồi -->
                <div>
                    <button type="submit">Gửi Phản hồi</button>
                </div>
            </form>
        </div>

        <!-- Single Page Header start -->
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
    </body>
    <script>
            const stars = document.querySelectorAll('.star');

            stars.forEach(star => {
                star.addEventListener('click', function () {
                    const value = parseInt(this.getAttribute('data-value')); // Lấy giá trị đánh giá từ data-value của icon sao
                    document.getElementById('rating').value = value; // Cập nhật giá trị của trường rating

                    // Đánh dấu các sao trước icon được nhấn
                    stars.forEach(s => {
                        const sValue = parseInt(s.getAttribute('data-value'));
                        if (sValue <= value) {
                            s.classList.add('selected');
                        } else {
                            s.classList.remove('selected');
                        }
                    });
                });
            });
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
            function validateRating() {
                var rating = document.getElementById('rating').value;
                if (rating == 0) {
                    alert('Vui lòng chọn số sao trước khi gửi phản hồi.');
                    return false;
                }
                return true;
            }

            // Function to handle star rating selection
            document.addEventListener('DOMContentLoaded', function () {
                var stars = document.querySelectorAll('.star');
                stars.forEach(function (star) {
                    star.addEventListener('click', function () {
                        var value = this.getAttribute('data-value');
                        document.getElementById('rating').value = value;
                        // Add 'checked' class to stars up to selected star
                        stars.forEach(function (s) {
                            s.classList.remove('checked');
                        });
                        for (var i = 0; i < value; i++) {
                            stars[i].classList.add('checked');
                        }
                    });
                });
            });
    </script>
</html>

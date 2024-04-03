<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="model.News.News" %>
<%@page import="model.Information.*"%>
<%@page import="model.Users.Users"%>
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
                background-color: #99cc00;
                margin: 0 5px;
            }
            .news-item-container img {
                max-width: 100%; /* Giới hạn chiều rộng của ảnh */
                height: auto; /* Cho phép tỷ lệ khung hình tự động điều chỉnh */
            }
            .news-item-container .heading {
                overflow: hidden; /* Ngăn tiêu đề tràn ra ngoài phần tử cha */
                text-overflow: ellipsis; /* Hiển thị dấu chấm (...) khi tiêu đề quá dài */
                white-space: nowrap; /* Ngăn tiêu đề xuống dòng */
                margin-top:20px;
            }
            .news-item-container .meta p {
                overflow: hidden; /* Ngăn ngày tràn ra ngoài phần tử cha */
                text-overflow: ellipsis; /* Hiển thị dấu chấm (...) khi ngày quá dài */
                white-space: nowrap; /* Ngăn ngày xuống dòng */
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
                            <a href="/CoffeeLand/client/productlist" class="nav-item nav-link">Sản phẩm</a>
                            <a href="/CoffeeLand/newslistclient" class="nav-item nav-link active">Tin tức</a>
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
                    <form action="searchnews?index=1" method="POST">
                        <input class="searchBox" type="text" name="txtSearch" required>
                        <button class="searchButton" type="submit" name="btnGo" required>
                            <i class="fas fa-search text-primary"></i>
                        </button>

                    </form>
                </nav>
            </div>
        </div>
        <!-- Navbar End -->





        <section class="ftco-section">

            <div class="container">

                <div class="row" style="margin-top: 100px;">
                    <!--                    <div class="search">
                                            <form action="searchnews?index=1" method="POST">
                                                Tìm kiếm: <input class="searchBox" type="text" name="txtSearch" required>
                                                <input class="searchButton" type="submit" name="btnGo" value="Tìm Kiếm" required>
                                            </form>
                                        </div>-->
                    <c:forEach var="news" items="${lstNews}" varStatus="loop">
                        <div class="col-lg-4 d-flex align-items-stretch ftco-animate" onclick="redirectToNewsDetail('${news.newsId}')">
                            <div class="news-item-container" style="width: 356px; height: 344px; margin-bottom: 20px;">
                                <div class="text p-4 bg-light">  

                                    <div class="click">
                                        <img src="/CoffeeLand/assests/image/thumbnails/${news.image}" style="height: 214px; " class="img-fluid">
                                        <h4 class="heading mb-3">${news.title}</h4>
                                    </div>
                                    <div class="meta">
                                        <p><span class="fa fa-calendar"></span> ${news.postedTime}</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <c:if test="${loop.index % 3 == 2 or loop.last}">
                        </div>
                        <c:if test="${!loop.last}">
                            <div class="row">
                            </c:if>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
            <div class="row mt-5">
                <div class="col text-center">
                    <div class="block-27">
                        <c:forEach begin="1" end="${endP}" var="i">
                            <a href="newslistclient?index=${i}">${i}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <script>
                function redirectToNewsDetail(newsId) {
                    window.location.href = 'newsdetails?id=' + newsId; // Chuyển hướng đến trang newsDetail.jsp với tham số id
                }
            </script>
        </section>




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
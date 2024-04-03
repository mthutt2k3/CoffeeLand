<%-- 
    Document   : profile
    Created on : Jan 28, 2024, 12:08:19 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.CartClient.CartClientDAO" %>
<%@ page import="model.CartClient.CartClient" %>
<!DOCTYPE html>
<html>
    <head>
        <%int countCart = (int) session.getAttribute("countCart");%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>JSP Page</title>
        <style>
            /* Reset CSS for margin and padding */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }



            /* Header styles */
            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 20px;

                color: #fff; /* Màu chữ trắng */
            }

            .header-logo img {
                border-radius: 50%; /* Làm tròn hình ảnh */
                width: 150px; /* Kích thước hình ảnh */
                height: 150px; /* Kích thước hình ảnh */
                margin-left: 50px; /* Khoảng cách giữa logo và phần còn lại của header */
                border: solid #ffbe76;
            }
            .header-search {
                display: flex;
                justify-content: center;
                align-items: center;
                padding-bottom: 20px
            }

            .header-search input[type="text"] {
                padding: 10px;
                width: 300px;
                border: 1px solid #ccc;
                border-radius: 5px;
                margin-right: 10px;
                font-family: 'Courier New', Courier, monospace;
            }

            .header-search input[type="submit"] {
                padding: 10px 20px;
                background-color: #EAB543;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-family: 'Courier New', Courier, monospace;
            }
            /* Header menu styles */
            .header-menu ul {
                list-style-type: none;
                display: flex;
                padding-left: 100px;
                padding-top: 20px;

            }

            .header-menu li {
                margin-right: 30px;
                padding-right: 130px;
            }

            .header-menu a {
                text-decoration: none;
                color: #f0932b;
                font-weight: bold;

                font-size: 20px
            }

            /* Header button styles */
            .header-button{
                padding-bottom: 20px;
                margin-right: 20px
            }
            .header-button a {
                text-decoration: none;
            }

            .header-button button {
                padding: 10px 20px;
                background-color: #ffcc00; /* Màu vàng */
                color: #fff; /* Màu xanh lá cây nhạt */
                border: none;
                border-radius: 10px; /* Viền tròn */
                cursor: pointer;
                transition: background-color 0.3s ease;
                font-size: 15px;
                font-family: 'Courier New', Courier, monospace;
                font-weight: bold;

            }



            /* Cart button styles */

            .header-button-cart {
                padding: 10px 15px;
                margin-left: 20px;
                color: #ff793f;
                border: none;
                border-radius: 5px; /* Viền tròn */
                cursor: pointer;
                transition: background-color 0.3s ease;
                font-weight: bold;

            }
            .footer-container {
                background: rgb(236,196,110);
                background: linear-gradient(0deg, rgba(236,196,110,1) 12%, rgba(237,175,58,1) 64%);
                color: #fff; /* Màu chữ của footer */
                padding: 5px;
                margin-top: 389px;
                display: flex
            }

            .footer-section2 {
                margin-bottom: 5px; /* Khoảng cách giữa các phần trong footer */
                margin-left: 230px
            }
            .footer-section1
            {
                width: 48%; /* Đặt chiều rộng của mỗi phần */
            }
            .footer-section1 h3
            {
                padding-bottom: 10px
            }
            .footer-section1 p
            {
                padding-left: 20px
            }
            .footer-section2 p
            {
                padding-left: 20px
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
        <footer>
            <div class="footer-container">
                <div class="footer-section1">
                    <h3>Chúng tôi</h3>
                    <p>The Coffee Shop - nơi nơi hương vị đắm chìm trong sự hòa quyện của cà phê tinh tế và không khí ấm áp. Chúng tôi không chỉ là một quán cà phê, mà còn là một trải nghiệm, một điểm hẹn của những đam mê yêu cà phê và tận hưởng cuộc sống.</p>
                </div>
                <div class="footer-section2">
                    <div class="footer-section">
                        <h3>Liên hệ</h3>
                        <p>Email: info@coffeeshop.com<br>Phone: (123) 456-7890</p>
                    </div>
                    <div class="footer-section">
                        <h3>Địa chỉ</h3>
                        <p>Đại học FPT, Hà Nội<br>Viet Nam</p>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>

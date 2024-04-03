<%-- 
    Document   : OrderDetailForCus
    Created on : Mar 6, 2024, 5:46:11 AM
    Author     : Admin
--%>

<%@page import="model.OrderDetail.*"%>
<%@page import="model.ProductSaler.*"%>
<%@page import="model.Orders.*"%>
<%@page import="model.Information.*"%>
<%@page import="model.CartClient.*"%>
<%@page import="model.VoucherClient.*"%>
<%@page import="model.Users.Users"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*"%>
<%@page import="model.Product.*"%>
<%@page import= "java.text.DecimalFormat"%>
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
    List<Orders> lstOrder = (List<Orders>) session.getAttribute("lstOrder");
    List<ProductSaler> lstDetailPro = (List<ProductSaler>) session.getAttribute("lstProductDetail");
    Orders x = (Orders) session.getAttribute("OrderDetail");
    if(x == null){
        x = new Orders();
    }
    DecimalFormat decimalFormat = new DecimalFormat("###,###.##");
%>
<head>
    <meta charset="utf-8">
    <title>Fruitables - Vegetable Website Template</title>
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

        .card{
            margin: auto;
            margin-top: 20px;
            width: 38%;
            max-width:600px;
            padding: 4vh 0;
            box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
            border-top: 3px solid rgb(252, 103, 49);
            border-bottom: 3px solid rgb(252, 103, 49);
            border-left: none;
            border-right: none;
        }
        @media(max-width:768px){
            .card{
                width: 90%;
            }
        }
        .title{
            color: rgb(252, 103, 49);
            font-weight: 600;
            margin-bottom: 2vh;
            padding: 0 8%;
            font-size: initial;
        }
        #details{
            font-weight: 400;
        }
        .info{
            padding: 5% 8%;
        }
        .info .col-5{
            padding: 0;
        }
        #heading{
            color: grey;
            line-height: 6vh;
        }
        .pricing{
            background-color: #ddd3;
            padding: 2vh 8%;
            font-weight: 400;
            line-height: 2.5;
        }
        .pricing .col-3{
            padding: 0;
        }
        .total{
            padding: 2vh 8%;
            color: rgb(252, 103, 49);
            font-weight: bold;
        }
        .total .col-3{
            padding: 0;
        }
        .footerx{
            padding: 0 8%;
            font-size: x-small;
            color: black;
        }
        .footer img{
            height: 5vh;
            opacity: 0.2;
        }
        .footer a{
            color: rgb(252, 103, 49);
        }
        .footer .col-10, .col-2{
            display: flex;
            padding: 3vh 0 0;
            align-items: center;
        }
        .footer .row{
            margin: 0;
        }
        #progressbar {
            margin-bottom: 3vh;
            overflow: hidden;
            color: rgb(252, 103, 49);
            padding-left: 0px;
            margin-top: 3vh
        }

        #progressbar li {
            list-style-type: none;
            font-size: x-small;
            width: 25%;
            float: left;
            position: relative;
            font-weight: 400;
            color: rgb(160, 159, 159);
        }

        #progressbar #step1:before {
            content: "";
            color: rgb(252, 103, 49);
            width: 5px;
            height: 5px;
            margin-left: 0px !important;
            /* padding-left: 11px !important */
        }

        #progressbar #step2:before {
            content: "";
            color: #fff;
            width: 5px;
            height: 5px;
            margin-left: 32%;
        }

        #progressbar #step3:before {
            content: "";
            color: #fff;
            width: 5px;
            height: 5px;
            margin-right: 32% ;
            /* padding-right: 11px !important */
        }

        #progressbar #step4:before {
            content: "";
            color: #fff;
            width: 5px;
            height: 5px;
            margin-right: 0px !important;
            /* padding-right: 11px !important */
        }

        #progressbar li:before {
            line-height: 29px;
            display: block;
            font-size: 12px;
            background: #ddd;
            border-radius: 50%;
            margin: auto;
            z-index: -1;
            margin-bottom: 1vh;
        }

        #progressbar li:after {
            content: '';
            height: 2px;
            background: #ddd;
            position: absolute;
            left: 0%;
            right: 0%;
            margin-bottom: 2vh;
            top: 1px;
            z-index: 1;
        }
        .progress-track{
            padding: 0 8%;
        }
        #progressbar li:nth-child(2):after {
            margin-right: auto;
        }

        #progressbar li:nth-child(1):after {
            margin: auto;
        }

        #progressbar li:nth-child(3):after {
            float: left;
            width: 68%;
        }
        #progressbar li:nth-child(4):after {
            margin-left: auto;
            width: 132%;
        }

        #progressbar  li.active{
            color: black;
        }

        #progressbar li.active:before,
        #progressbar li.active:after {
            background: rgb(252, 103, 49);
        }
        .divider {
            border: none;
            border-top: 3px solid #000; /* Màu và kiểu của đường gạch ngang */
            margin: 10px 0; /* Khoảng cách giữa dấu gạch và các phần tử xung quanh */
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
    <!-- Navbar End -->


    <!-- Modal Search Start -->
    <div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-fullscreen">
            <div class="modal-content rounded-0">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Search by keyword</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body d-flex align-items-center">
                    <div class="input-group w-75 mx-auto d-flex">
                        <input type="search" class="form-control p-3" placeholder="keywords" aria-describedby="search-icon-1">
                        <span id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Modal Search End -->


    <!-- Single Page Header start -->
    <div class="container-fluid page-header py-5">
            <h1 class="text-center text-white display-6">Chi tiết đơn hàng</h1>
            <ol class="breadcrumb justify-content-center mb-0">
                <li class="breadcrumb-item"><a href="/CoffeeLand/client/Homepage.jsp">Trang chủ</a></li>
                <li class="breadcrumb-item"><a href="/client/order/OrderListForCus.jsp">Lịch sử mua hàng</a></li>
                <li class="breadcrumb-item active text-white">Chi tiết đơn hàng</li>
            </ol>
        </div>
    <!-- Single Page Header End -->


    <div class="card">
        <div class="title">Chi tiết đơn hàng</div>
        <div class="info">

            <div class="row">
                <div class="col-6">
                    <span id="heading">Mã đơn hàng</span>
                </div>
                <div class="col-6 text-right">
                    <span id="details"><%=x.getOrderCode()%></span>
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <span id="heading">Thời gian đặt hàng</span>
                </div>
                <div class="col-6 text-right">
                    <span id="details"><%=x.getOrderTime()%></span>
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <span id="heading">Tên người nhận</span>
                </div>
                <div class="col-6 text-right">
                    <span id="details"><%=x.getReceiverName()%></span>
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <span id="heading">Địa chỉ nhận hàng</span>
                </div>
                <div class="col-6 text-right">
                    <span id="details"><%=x.getReceiverAddress()%></span>
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <span id="heading">Số điện thoại</span>
                </div>
                <div class="col-6 text-right">
                    <span id="details"><%=x.getReceiverPhone()%></span>
                </div>
            </div>


        </div>      
        <div class="pricing">
            <div class="row">
                <div class="col-6">
                    <span id="name">Tên sản phẩm</span>  
                </div>
                <div class="col-3">
                    <span id="size">Size</span>  
                </div>
                <div class="col-3">
                    <span id="quantity">Số lượng</span>
                </div>
            </div>
            <hr class="divider">
            <%
                                if(lstDetailPro != null){
                                    for(ProductSaler a: lstDetailPro) {
            %>
            <div class="row">
                <div class="col-6">
                    <span id="name"><%= a.getProductName() %></span>  
                </div>
                <div class="col-3">
                    <span id="size"><%= a.getSize() %></span>  
                </div>
                <div class="col-3">
                    <span id="quantity"><%= a.getQuantity() %></span>
                </div>
            </div>
                <%
                if(x.getStatusId().equals("Giao hàng thành công")) {
        %>
        <button class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4 checkoutButton addFeedbackButton" data-product-id="<%= a.getProductId() %>">Thêm Đánh giá của sản phẩm này</button>
           
            <%} }}%>
            <hr class="divider">
            <div class="row">
                <div class="col-9">
                    <span id="name">Giá gốc(VND)</span>  
                </div>
                <div class="col-3">
                    <%String xTotalAmount = decimalFormat.format(Integer.parseInt(x.getTotalAmount()));%>
                    <span id="price"><%=xTotalAmount%></span>
                </div>
            </div>
            <div class="row">
                <div class="col-9">
                    <span id="name">Giảm giá(VND)</span>
                </div>
                <div class="col-3">
                    <%String xDiscountAmount = decimalFormat.format(Integer.parseInt(x.getDiscountAmount()));%>
                    <span id="price"><%=xDiscountAmount%></span>
                </div>
            </div>
        </div>
        <div class="total">
            <div class="row">
                <div class="col-9"></div>
                <%String xGrandTotal = decimalFormat.format(Integer.parseInt(x.getGrandTotal()));%>
                <div class="col-3"><big><%=xGrandTotal%></big></div>
            </div>
        </div>
        <div class="tracking">
            <div class="title">Trạng thái đơn hàng</div>
            <%if(x.getStatusId().equals("Giao hàng không thành công")){%>
                <p class="title">Đặt hàng không thành công</p>

                <%}%>
            
        </div>
        <div class="progress-track">
            
            <ul id="progressbar" class="d-flex justify-content-between">
                <%if(x.getStatusId().equals("Chờ xác nhận")){%>
                <li class="step0 active flex-fill text-center" id="step1">Chờ xác nhận</li>
                <li class="step0  flex-fill text-center" id="step2">Đang chuẩn bị</li>
                <li class="step0  flex-fill text-center" id="step3">Đang giao hàng</li>
                <li class="step0 flex-fill text-center" id="step4">Giao hàng thành công</li>
                    <%}%>
                    <%if(x.getStatusId().equals("Đang chuẩn bị")){%>
                <li class="step0 active flex-fill text-center" id="step1">Chờ xác nhận</li>
                <li class="step0 active flex-fill text-center" id="step2">Đang chuẩn bị</li>
                <li class="step0  flex-fill text-center" id="step3">Đang giao hàng</li>
                <li class="step0 flex-fill text-center" id="step4">Giao hàng thành công</li>
                    <%}%>
                    <%if(x.getStatusId().equals("Đang giao hàng")){%>
                <li class="step0 active flex-fill text-center" id="step1">Chờ xác nhận</li>
                <li class="step0 active flex-fill text-center" id="step2">Đang chuẩn bị</li>
                <li class="step0 active flex-fill text-center" id="step3">Đang giao hàng</li>
                <li class="step0 flex-fill text-center" id="step4">Giao hàng thành công</li>
                    <%}%>
                    <%if(x.getStatusId().equals("Giao hàng thành công")){%>
                <li class="step0 active flex-fill text-center" id="step1">Chờ xác nhận</li>
                <li class="step0 active flex-fill text-center" id="step2">Đang chuẩn bị</li>
                <li class="step0 active flex-fill text-center" id="step3">Đang giao hàng</li>
                <li class="step0 active flex-fill text-center" id="step4">Giao hàng thành công</li>
                    <%}%>
            </ul>
        </div>
            <%if(x.getStatusId().equals("Chờ xác nhận")){%>
            <form action="/CoffeeLand/updateorderforcus" method="get">
            <input type="hidden" name="orderId" value="<%=x.getOrderId()%>"/>
            <button  class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4 checkoutButton" type="submit">Sửa đơn hàng</button>
        </form>
            <%}%>
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
                        Thiết kế bởi <a class="border-bottom" href="https://www.facebook.com/truongnd2201">team CoffeeLand</a> đóng góp bởi <a class="border-bottom" href="https://www.facebook.com/profile.php?id=100048209379368">Quý Dương</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Copyright End -->



    <!-- Back to Top -->
    <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>   

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="/CoffeeLand/assestsClient/lib/easing/easing.min.js"></script>
    <script src="/CoffeeLand/assestsClient/lib/waypoints/waypoints.min.js"></script>
    <script src="/CoffeeLand/assestsClient/lib/lightbox/js/lightbox.min.js"></script>
    <script src="/CoffeeLand/assestsClient/lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Javascript -->
    <script src="/CoffeeLand/assestsClient/js/main.js"></script>
    <script>
    // Lặp qua tất cả các nút "Thêm Đánh giá" và thêm sự kiện click vào mỗi nút
    var addFeedbackButtons = document.querySelectorAll(".addFeedbackButton");
    addFeedbackButtons.forEach(function(button) {
        button.addEventListener("click", function() {
            // Lấy userId từ xUser
            var userId = "<%= xUser.getUserId() %>";
            
            // Lấy productId từ thuộc tính data-product-id của nút được nhấn
            var productId = button.getAttribute("data-product-id");

            // Tạo URL chứa thông tin userId và productId
            var url = "/CoffeeLand/client/feedback/AddNewFeedback.jsp?userId=" + encodeURIComponent(userId) + "&productId=" + encodeURIComponent(productId);

            // Chuyển hướng đến trang addfeedback
            window.location.href = url;
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
</script>
</body>
</html>

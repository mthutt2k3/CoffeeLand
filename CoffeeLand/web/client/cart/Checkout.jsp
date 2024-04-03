<%-- 
    Document   : Checkout
    Created on : Mar 3, 2024, 3:38:13 PM
    Author     : Admin
--%>

<%@page import="model.Information.*"%>
<%@page import="model.Users.Users"%>
<%@page import="model.VoucherClient.*"%>
<%@page import="model.CartClient.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*"%>
<%@page import= "java.text.DecimalFormat"%>
<%@page import="model.Product.*"%>
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
    VoucherClient Voucher = new VoucherClient();
        Voucher = (VoucherClient) session.getAttribute("voucher");
    
    List<CartClient> lstProCart = (List<CartClient>) session.getAttribute("lstProCart");
    List<CartClient> lstQuantity = (List<CartClient>) session.getAttribute("lstQuantity");
    int totalAmount=0;
    int grandTotal=0;
    int discount=0;
for(CartClient to : lstProCart){
        grandTotal+=  Integer.parseInt(to.getTotal()) ;
        
    }
    totalAmount=grandTotal;
    session.setAttribute("totalAmount", grandTotal);
    if(session.getAttribute("voucher")!=null){
    discount=grandTotal*Integer.parseInt(Voucher.getDiscount())/100;
        grandTotal= grandTotal*(100-Integer.parseInt(Voucher.getDiscount()))/100;
    }
    
    session.setAttribute("grandTotal", grandTotal);
    session.setAttribute("discount", discount);
    
DecimalFormat decimalFormat = new DecimalFormat("###,###.##");
        String xgrandTotal = decimalFormat.format(grandTotal);
        String xdiscount = decimalFormat.format(discount);
        String xtotalAmount = decimalFormat.format(totalAmount);
        

%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Chi tiết sản phẩm</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">
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
            <h1 class="text-center text-white display-6">Xác nhận đặt hàng</h1>
            <ol class="breadcrumb justify-content-center mb-0">
                <li class="breadcrumb-item"><a href="/CoffeeLand/client/Homepage.jsp">Trang chủ</a></li>
                <li class="breadcrumb-item"><a href="/client/cart/Cart.jsp">Giỏ hàng</a></li>
                <li class="breadcrumb-item active text-white">Xác nhận đặt hàng</li>
            </ol>
        </div>
        <!-- Single Page Header End -->


        <!-- Checkout Page Start -->
        <div class="container-fluid py-5">
            <div class="container py-5">
                <h1 class="mb-4">Chi tiết đặt hàng</h1>
                <form action="/CoffeeLand/checkout" method="POST" name="checkoutForm" onsubmit="return validateForm()">

                    <div class="row g-5">
                        <div class="col-md-12 col-lg-6 col-xl-7">
                            <div class="form-item">
                                <label class="form-label my-3">Tên<sup>*</sup></label>
                                <input name="name" type="text" class="form-control" value="<%=xUser.getName()%>" required>
                            </div>
                            <div class="form-item">
                                <label class="form-label my-3">Địa chỉ <sup>*</sup></label>
                                <input name="address" type="text" class="form-control" value="<%=xUser.getAddress()%>" required>
                            </div>
                            <div class="form-item">
                                <label class="form-label my-3">Xã<sup>*</sup></label>
                                <select class="form-select" name="chonxa" >
                                    <option value="Bình Phú">Bình Phú</option>
                                    <option value="Bình Yên">Bình Yên</option>
                                    <option value="Cẩm Yên">Cẩm Yên</option>
                                    <option value="Cần Kiệm">Cần Kiệm</option>
                                    <option value="Canh Nậu">Canh Nậu</option>
                                    <option value="Chàng Sơn">Chàng Sơn</option>
                                    <option value="Đại Đồng">Đại Đồng</option>
                                    <option value="Dị Nậu">Dị Nậu</option>
                                    <option value="Đồng Trúc">Đồng Trúc</option>
                                    <option value="Hạ Bằng">Hạ Bằng</option>
                                    <option value="Hương Ngải">Hương Ngải</option>
                                    <option value="Hữu Bằng">Hữu Bằng</option>
                                    <option value="Kim Quan">Kim Quan</option>
                                    <option value="Lại Thượng">Lại Thượng</option>
                                    <option value="Liên Quan">Liên Quan</option>
                                    <option value="Phú Kim">Phú Kim</option>
                                    <option value="Phùng Xá">Phùng Xá</option>
                                    <option value="Tân Xã">Tân Xã</option>
                                    <option value="Thạch Xá">Thạch Xá</option>                                   
                                </select>
                            </div>
                            <div class="form-item">
                                <label class="form-label my-3">Số điện thoại<sup>*</sup></label>
                                <input name="phoneNumber" type="tel" class="form-control" value="<%=xUser.getPhoneNumber()%>" required>
                            </div>
                            <div class="form-item">
                                <label class="form-label my-3">Email<sup>*</sup></label>
                                <input name="email" type="email" class="form-control" value="<%=xUser.getEmail()%>" required>
                            </div>
                        </div>
                        <div class="col-md-12 col-lg-6 col-xl-5">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">Sản phẩm</th>
                                            <th scope="col">Tên</th>
                                            <th scope="col">Đơn Giá(VND)</th>
                                            <th scope="col">Số lượng</th>
                                            <th scope="col">Thành tiền (VND)</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%for(CartClient pro : lstProCart){%>
                                        <tr>
                                            <th scope="row">
                                                <div class="d-flex align-items-center mt-2">
                                                    <img src="/CoffeeLand/assests/image/thumbnails/<%=pro.getThumbnail()%>" class="img-fluid rounded-circle" style="width: 90px; height: 90px;" alt="">
                                                </div>
                                            </th>
                                            <td class="py-5"><%=pro.getName()%></td>
                                            <%String xPrice = decimalFormat.format(Integer.parseInt(pro.getPrice()));%>
                                            <td class="py-5"><%=xPrice%></td>
                                            <td class="py-5"><%=pro.getQuantity()%></td>
                                            <%String xTotal = decimalFormat.format(Integer.parseInt(pro.getTotal()));%>
                                            <td class="py-5"><%=xTotal%></td>
                                        </tr>
                                        <%}%>
                                        <tr>
            <td colspan="4" class="text-right">Tổng tiền:</td>
            <td class="text-left"><%=xtotalAmount%></td>
        </tr>
        <tr>
            <td colspan="4" class="text-right">Giảm giá:</td>
            <td class="text-left"><%=xdiscount%></td>
        </tr>
        <tr>
            <td colspan="4" class="text-right">Tổng cộng:</td>
            <td class="text-left"><%=xgrandTotal%></td>
        </tr>

                                    </tbody>
                                </table>              
                            </div>


                            <div class="row g-4 text-center align-items-center justify-content-center pt-4">
                                <button type="submit" class="btn border-secondary py-3 px-4 text-uppercase w-100 text-primary">Đặt hàng</button>
                            </div>
                        </div>    
                    </div>
                </form>
            </div>
        </div>
        <!-- Checkout Page End -->


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
                                            
                                            function validateForm() {
    var phoneNumber = document.forms["checkoutForm"]["phoneNumber"].value;
    var email = document.forms["checkoutForm"]["email"].value;

    var phoneRegex = /^\d{10}$/; // Định dạng số điện thoại: 10 chữ số
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // Định dạng email

    // Kiểm tra số điện thoại
    if (!phoneRegex.test(phoneNumber)) {
        alert("Vui lòng nhập số điện thoại hợp lệ.");
        return false;
    }

    // Kiểm tra email
    if (!emailRegex.test(email)) {
        alert("Vui lòng nhập địa chỉ email hợp lệ.");
        return false;
    }
}

        </script>
    </body>

</html>

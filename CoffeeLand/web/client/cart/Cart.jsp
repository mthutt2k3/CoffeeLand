<!DOCTYPE html>
<html lang="en">

    <%@page import="model.Information.*"%>
    <%@page import="model.CartClient.*"%>
    <%@page import="model.VoucherClient.*"%>
    <%@page import="model.Users.Users"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page import="java.util.*"%>
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
        List<CartClient> lst = (List<CartClient>) session.getAttribute("lstProCart");
        List<VoucherClient> lstVoucher = (List<VoucherClient>) session.getAttribute("lstVoucher");
        String[] cartSession = (String[]) session.getAttribute("cartSession");

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
            <h1 class="text-center text-white display-6">Giỏ hàng</h1>
            <ol class="breadcrumb justify-content-center mb-0">
                <li class="breadcrumb-item"><a href="/CoffeeLand/client/Homepage.jsp">Trang chủ</a></li>
                <li class="breadcrumb-item active text-white">Giỏ hàng</li>
            </ol>
        </div>
        <!-- Single Page Header End -->


        <!-- Cart Page Start -->
        <div class="container-fluid py-5">
            <div class="container py-5">
                <form id="checkoutForm" action="/CoffeeLand/checkout" method="GET">
                    <input type="hidden" id="selectedProducts" name="selectedProducts" />
                    <div class="row">
                        <%int totalTien=0;%>
                        <div class="col-lg-8">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">Chọn</th>
                                            <th scope="col">Sản phẩm</th>
                                            <th scope="col">Tên</th>
                                            <th scope="col">Kích thước</th>
                                            <th scope="col">Đơn giá</th>
                                            <th scope="col">Số lượng</th>
                                            <th scope="col">Xóa sản phẩm</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <%for(CartClient x : lst){%>
                                        <%int check=0;%>
                                        <%for(int i= 0; i< cartSession.length ; i++){%>
                                        <%if(x.getProductId().equals(cartSession[i])){
                                            check = 1;
                                            totalTien += Integer.parseInt(x.getPrice()) * Integer.parseInt(x.getQuantity());
                                        }%>
                                        <%}%>

                                        <tr>
                                            <td>
                                                <%if(check == 1){%>
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" value="" id="checkbox1" checked>
                                                    <label class="form-check-label" for="checkbox1"></label>
                                                </div>
                                                <%}else{%>
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" value="" id="checkbox1">
                                                    <label class="form-check-label" for="checkbox1"></label>
                                                </div>
                                                <%}%>
                                            </td>
                                            <th scope="row">
                                                <div class="d-flex align-items-center">
                                                    <img src="/CoffeeLand/assests/image/thumbnails/<%=x.getThumbnail()%>" class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt="">
                                                </div>
                                            </th>
                                            <td>
                                                <p class="mb-0 mt-4"><%=x.getName()%></p>
                                            </td>
                                            <td>
                                                <p class="mb-0 mt-4"><%=x.getSize()%></p>
                                            </td>
                                            <td>
                                                <p class="mb-0 mt-4 price"><%=x.getPrice()%> VND</p>
                                            </td>
                                            <td>
                                                <div class="input-group quantity mt-4" style="width: 100px;">
                                                    <div class="input-group-btn">
                                                        <button type="button" class="btn btn-sm btn-minus rounded-circle bg-light border add" >
                                                            <i class="fa fa-minus "></i>
                                                        </button>
                                                    </div>
                                                    <input type="text" class="form-control form-control-sm text-center border-0 quantity" value="<%=x.getQuantity()%>" name="quantity">
                                                    <div class="input-group-btn">
                                                        <button type="button"  class="btn btn-sm btn-plus rounded-circle bg-light border minus">
                                                            <i class="fa fa-plus "></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>


                                                <input type="hidden" name="productId" value="<%=x.getProductId()%>">
                                                <input type="hidden" name="size" value="<%=x.getSize()%>">
                                                <button type="button" data-product-id="<%=x.getProductId()%>" data-size-id="<%=x.getSize()%>" class="btn btn-md rounded-circle bg-light border mt-4 xoa" >
                                                    <i class="fa fa-times text-danger"></i>
                                                </button>

                                            </td>

                                        </tr>

                                        <%}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div class="col-lg-4">

                            <div class="row g-4 justify-content-end">
                                <div class="col-12">
                                    <label for="voucherCode" class="form-label">Nhập mã voucher:</label>
                                    <input type="text" class="form-control" id="voucherCode" name="voucherCode">
                                </div>  

                                <div class="col-12">
                                    <div class="bg-light rounded">
                                        <div class="py-4 mb-4 border-top border-bottom d-flex justify-content-between">
                                            <h5 class="mb-0 ps-4 me-4">Tổng tiền</h5>
                                            <span id="total"><%=totalTien%></span>
                                        </div>
                                        <button id="checkoutButton" class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4 checkoutButton" type="button">Đặt hàng</button>
                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>
                </form>
            </div>
        </div>





        <!-- Cart Page End -->


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
            // Sử dụng jQuery để đợi tất cả các phần tử HTML được tải xong
            $(document).ready(function () {
                // Khởi tạo biến tổng giá tiền
                var totalPrice = 0;

                // Thêm sự kiện cho checkbox
                $('input[type="checkbox"]').change(function () {
                    updateTotal();
                });

                // Thêm sự kiện cho ô nhập số lượng
                $('.quantity input').change(function () {
                    updateTotal();
                });
                $('.add').click(function () {
                    updateTotal();
                });
                $('.minus').click(function () {
                    updateTotal();
                });

                function updateTotal() {
                    totalPrice = 0;
                    // Lặp qua tất cả các hàng trong bảng
                    $('tbody tr').each(function () {
                        // Lấy giá tiền từ hàng tương ứng với checkbox và thêm vào tổng giá tiền
                        var price = parseFloat($(this).find('.price').text());
                        var quantity = parseInt($(this).find('.quantity input').val());
                        // Kiểm tra nếu số lượng nhỏ hơn 1, gán giá trị là 1
                        if (quantity < 1) {
                            quantity = 1;
                            $(this).find('.quantity input').val(quantity); // Cập nhật giá trị trong ô nhập
                        }
                        // Kiểm tra nếu checkbox được chọn, mới tính tổng giá tiền
                        if ($(this).find('input[type="checkbox"]').is(':checked')) {
                            totalPrice += price * quantity;
                        }
                    });
                    // Hiển thị tổng giá tiền ở total với định dạng số tiền
                    $('#total').text(totalPrice.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}));
                }


            });

            $(document).ready(function () {
                $('.xoa').click(function () {
                    var productId = $(this).data('product-id');
                    var sizeId = $(this).data('size-id');
                    $.ajax({
                        type: 'POST',
                        url: '/CoffeeLand/client/cart',
                        data: {productId: productId,
                            size: sizeId},
                        success: function (response) {
                            location.reload(); // Tải lại trang sau khi xóa sản phẩm
                        },
                        error: function () {
                            alert('Đã xảy ra lỗi khi xóa sản phẩm.');
                        }
                    });
                });

                $('#checkoutButton').click(function () {
                    var selectedProducts = [];

                    // Lặp qua tất cả các hàng sản phẩm
                    var productSelected = false; // Biến kiểm tra có sản phẩm nào được chọn hay không
                    $('tbody tr').each(function () {
                        // Kiểm tra checkbox có được chọn hay không
                        if ($(this).find('input[type="checkbox"]').is(':checked')) {
                            productSelected = true; // Đánh dấu có sản phẩm được chọn
                            var productId = $(this).find('input[name="productId"]').val();
                            var size = $(this).find('input[name="size"]').val();
                            var quantity = $(this).find('.quantity input').val();

                            // Thêm thông tin sản phẩm vào mảng
                            selectedProducts.push({
                                productId: productId,
                                size: size,
                                quantity: quantity
                            });
                        }
                    });

                    // Kiểm tra xem có sản phẩm nào được chọn không
                    if (!productSelected) {
                        alert('Bạn phải chọn sản phẩm trước khi đặt hàng.');
                    } else {
                        // Chuyển đổi mảng thành chuỗi JSON và đặt vào trường ẩn
                        $('#selectedProducts').val(JSON.stringify(selectedProducts));

                        // Submit form
                        $('#checkoutForm').submit();
                    }
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

            function formatCurrency(amount) {
                // Chia dấu phẩy sau mỗi 3 chữ số từ cuối lên đầu
                return amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            }
        </script>
    </body>

</html>
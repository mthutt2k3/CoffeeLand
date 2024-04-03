<%-- 
    Document   : AddProduct
    Created on : Jan 20, 2024, 4:51:13 PM
    Author     : acer
--%>
<%@page import="model.OrderDetail.*"%>
<%@page import="model.ProductSaler.*"%>
<%@page import="model.Users.*"%>
<%@page import="model.Information.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Category.*"%>
<%@page import = "model.Orders.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.Product.*" %>
<%@page import = "java.util.*" %>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    session = request.getSession(false);
    Users xUser = new Users();
    if (session.getAttribute("user") != null) {
        xUser = (Users) session.getAttribute("user");
    } else {
        response.sendRedirect("/CoffeeLand/server/Users/login.jsp");
    }
%>

<%
    List<ProductSaler> lstDetailPro = (List<ProductSaler>) session.getAttribute("lstProductDetail");
    Orders x = (Orders) session.getAttribute("OrderDetail");
    if(x == null){
        x = new Orders();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/style_login.css"/>
        <title>Cập nhật người dùng|CoffeeLand</title>
        <style>
            .dropdown {
                width: 100%;
                padding: 5px;
                margin-bottom: 10px;
                font-size: 16px;
                border-radius: 5px;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }
            .product-list {
                /* Hiển thị các sản phẩm theo hàng dọc */
                /* Display the products in a vertical row */
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            /* Định dạng cho phần tử chứa mỗi sản phẩm */
            /* Style the element that contains each product */
            .product-item {
                /* Hiển thị ảnh và thông tin sản phẩm theo hàng ngang */
                /* Display the image and product information in a horizontal row */
                display: flex;
                flex-direction: row;
                align-items: center;
                /* Thêm đường viền xung quanh sản phẩm */
                /* Add a border around the product */
                border: 1px solid black;
                /* Thêm khoảng cách giữa các sản phẩm */
                /* Add some space between the products */
                margin: 10px;
            }

            /* Định dạng cho phần tử chứa ảnh sản phẩm */
            /* Style the element that contains the product image */
            .product-thumbnail {
                /* Thêm khoảng cách giữa ảnh và thông tin sản phẩm */
                /* Add some space between the image and product information */
                margin-right: 10px;
            }

            /* Định dạng cho phần tử chứa thông tin sản phẩm */
            /* Style the element that contains the product information */
            .product-info {
                /* Hiển thị các thông tin sản phẩm theo hàng dọc */
                /* Display the product information in a vertical column */
                display: flex;
                flex-direction: column;
            }
        </style>
    </head>
    <body>
        <!--nav-->
        <%@include file="/navigator/Navbar.jsp" %>
        <%@include file="/navigator/Toast.jsp" %>

        <div class="wrapper" style="background-color: black">
            <div class="container main">
                <div class="row" style="height: 1500px; width: 1200px">
                    <div class="col-md-6 side-image" style="background-image: url(/CoffeeLand/assests/image/background/updateuser.jpg)">
                    </div>
                    <div class="col-md-6 right">
                        <div class="input-box">
                            <header>Chi tiết đơn hàng</header>
                            <form action="/CoffeeLand/orderhistory" method="POST">
                                <input type="hidden" class="input" name="orderId" value="<%=x.getOrderId()%>" required>
                                <input type="hidden" class="input" name="userId" value="<%=x.getUserId()%>" required>


                                <div class="">
                                    <div class="text-info">Mã đơn hàng</div>
                                    <input type="text" class="input" name="orderCode" value="<%=x.getOrderCode()%>" readonly>
                                </div>
                                <div class="mb-3">
                                    <div class="text-info mb-1">Danh sách sản phẩm</div>
                                    <div class="product-list">
                                        <%
                                if(lstDetailPro != null){
                                    for(ProductSaler a: lstDetailPro) {
                                        %>
                                        <div class="product-item">
                                            <img src="<%= a.getThumbnail() %>" alt="<%= a.getProductName() %>" class="product-thumbnail">
                                            <div class="product-info">
                                                <div><%= a.getProductName() %></div>
                                                <div>Size: <%= a.getSize() %></div>
                                                <div>Số lượng: <%= a.getQuantity() %></div>
                                            </div>
                                        </div>
                                        <% }}%>
                                    </div>
                                </div>
                                <div class="">
                                    <div class="text-info">Nhân viên bán hàng</div>
                                    <input type="text" class="input" name="salerId" value="<%=x.getSalerId()%>" readonly>
                                </div>
                                <div class="">
                                    <div class="text-info">Thời gian đặt hàng</div>
                                    <input type="text" class="input" name="orderTime" value="<%=x.getOrderTime()%>" readonly>
                                </div>
                                <div class="">
                                    <div class="text-info">Tên người nhận</div>
                                    <input type="text" class="input" name="receiverName" value="<%=x.getReceiverName()%>" readonly>
                                </div>
                                <div class="mb-3">
                                    <div class="text-info mb-1">Địa chỉ nhận hàng</div>
                                    <input type="text" class="input" name="receiverAddress" value="<%=x.getReceiverAddress()%>" readonly>
                                </div>
                                <div class="">
                                    <div class="text-info">Số điện thoại người nhận</div>
                                    <input type="text" class="input" name="receiverPhone" value="<%=x.getReceiverPhone()%>" readonly>
                                </div>
                                <div class="">
                                    <div class="text-info">Giá</div>
                                    <input type="text" class="input" name="totalAmount" value="<%=x.getTotalAmount()%>" readonly>
                                </div>
                                <div class="">
                                    <div class="text-info">Giảm giá</div>
                                    <input type="text" class="input" name="discountAmount" value="<%=x.getDiscountAmount()%>" readonly>
                                </div>
                                <div class="">
                                    <div class="text-info">Tổng tiền</div>
                                    <input type="text" class="input" name="grandTotal" value="<%=x.getGrandTotal()%>" readonly>
                                </div>
                                <div class="">
                                    <div class="text-info">Trạng thái đơn hàng</div>
                                    <input type="text" class="input" name="" value="<%=x.getStatusId()%>" readonly>

                                </div>
                                <div class="input-field">
                                    <input type="submit" class="submit" name="requestAction" value="Trở lại">
                                </div>
                            </form>
                        </div>  
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript" type="text/javascript" src="/CoffeeLand/assests/js/bootstrap.bundle.js"></script>
    </body>
</html>


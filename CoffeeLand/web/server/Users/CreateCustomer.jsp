<%-- 
    Document   : AddProduct
    Created on : Jan 20, 2024, 4:51:13 PM
    Author     : acer
--%>
<%@page import="model.Information.*"%>
<%@page import="model.Users.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Category.*"%>
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
    Users x = (Users) session.getAttribute("x");
    if(x == null){
        x = new Users("", "", "", "", "", "", "");
    }else{
        session.removeAttribute("x");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/style_login.css"/>
        <title>Tạo khách hàng mới|CoffeeLand</title>
    </head>
    <body>
        <!--nav-->
        <%@include file="/navigator/Navbar.jsp" %>
        <%@include file="/navigator/Toast.jsp" %>
        <%@include file="/navigator/sideBarMKTManage.jsp" %>

        <div class="wrapper">
            <div class="container main">
                <div class="row" style="height: 800px; width: 1200px">
                    <div class="col-md-6 side-image" style="background-image: url(/CoffeeLand/assests/image/background/addcustomer.jpg)">
                    </div>
                    <div class="col-md-6 right">
                        <div class="input-box">
                           <header>Tạo khách hàng mới</header>
                           <form action="/CoffeeLand/control" method="POST">
                                <div class="">
                                    <div class="text-info">Tên*<div>
                                            <input type="text" class="input" name="userName" value="<%=x.getName()%>" required>
                                </div>
                                <div class="">
                                    <div class="text-info">Số điện thoại*<div>
                                            <input type="text" class="input" name="phoneNumber" value="<%=x.getPhoneNumber()%>" required>
                                </div>
                                <div class="">
                                    <div class="text-info">Email*<div>
                                            <input type="text" class="input" name="email" value="<%=x.getEmail()%>" required>
                                </div>
                                <div class="">
                                    <div class="text-info">Mật khẩu*<div>
                                            <input type="text" class="input" name="password" value="" required>
                                </div>
                                <div class="">
                                    <div class="text-info">Nhập lại mật khẩu*<div>
                                            <input type="text" class="input" name="confirmPassword" value="" required>
                                </div>
                                <div class="">
                                    <div class="text-info">Địa chỉ*<div>
                                            <input type="text" class="input" name="address" value="<%=x.getAddress()%>" required>
                                        </div>
                                
                                <div class="input-field">
                                    <input type="submit" class="submit" name="requestAction" value="Tạo khách hàng">
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


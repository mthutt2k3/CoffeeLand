<%-- 
    Document   : ProfileServer
    Created on : Mar 19, 2024, 11:24:09 PM
    Author     : dell
--%>

<%@page import="model.Information.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.Users.*" %>
<%@page import = "java.util.*" %>
<%@ page import="javax.servlet.http.*" %>

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


<!DOCTYPE html>
<html>
    <head>
        <title>The Coffee Land</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/style.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <style>
            body{
                background-color: #6b3e2e;
            }
            .bg-navbar
            {
                --bs-bg-opacity:1;
                background-color:#5e5643;
            }
            .dropdown-menu-darknavbar{
                --bs-dropdown-color:#dee2e6;
                --bs-dropdown-bg:#403d34;
                --bs-dropdown-border-color:var(--bs-border-color-translucent);
                --bs-dropdown-link-color:#dee2e6;
                --bs-dropdown-link-hover-color:#171512;
                --bs-dropdown-divider-bg:var(--bs-border-color-translucent);
                --bs-dropdown-link-hover-bg:rgba(255, 255, 255, 0.15);
                --bs-dropdown-link-active-color:#fff;
                --bs-dropdown-link-active-bg:#0d6efd;
                --bs-dropdown-link-disabled-color:#adb5bd;
                --bs-dropdown-header-color:#adb5bd
            }
            .bg-bodylist
            {
                --bs-bg-opacity:1;
                background-color:#94927b;
            }
            .search-inputlist{
                font-size: 14px;
                border: none;
                background: #c5c1ae;
                margin: 0;
                color: inhefrit;
                border: 1px solid black;
                border-radius: inherit;
            }
            .table-infolist{
                --bs-table-color:#000;
                --bs-table-bg:#bbbd27;
                --bs-table-border-color:#000000;
                --bs-table-striped-bg:#c5e8ef;
                --bs-table-striped-color:#000;
                --bs-table-active-bg:#badce3;
                --bs-table-active-color:#000;
                --bs-table-hover-bg:#757b33;
                --bs-table-hover-color:#000;
                color:var(--bs-table-color);
                border-color:var(--bs-table-border-color)
            }
            .btn-outline-infolist{
                --bs-btn-color:#e8ff00;
                --bs-btn-border-color:#e8ff00;
                --bs-btn-hover-color:#000;
                --bs-btn-hover-bg:#e8ff00;
                --bs-btn-hover-border-color:#e8ff00;
                --bs-btn-focus-shadow-rgb:13,202,240;
                --bs-btn-active-color:#000;
                --bs-btn-active-bg:#0dcaf0;
                --bs-btn-active-border-color:#e8ff00;
                --bs-btn-active-shadow:inset 0 3px 5px rgba(0, 0, 0, 0.125);
                --bs-btn-disabled-color:#0dcaf0;
                --bs-btn-disabled-bg:transparent;
                --bs-btn-disabled-border-color:#0dcaf0;
                --bs-gradient:none
            }
            .profile {
                max-width: 400px;
                margin-top: 100px;
                margin-left: 500px;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
                background-color: #e3c099;
            }

            .profile .myclass {
                text-align: center;
                margin-bottom: 17px;
                color: #d35400;
            }

            .profile .form-group {
                margin-bottom: 20px;
            }

            .profile label {
                display: block;
                font-weight: bold;
            }

            .profile input[type="text"],
            .profile input[type="password"] {
                width: 100%;
                height: 40px;
                padding: 5px 10px;
                font-size: 16px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }

            .profile input[type="text"]:read-only,
            .profile input[type="password"]:read-only {
                background-color: #f0f0f0;
                cursor: not-allowed;
            }

            .profile .button-container {
                text-align: center;
            }

            .profile .button-container .btn {
                margin-right: 10px;
            }

            .profile .button-container .btn:last-child {
                margin-right: 0;
            }

        </style>
    </head>
    <body>
        <!--nav-->
        <%@include file="/navigator/Navbar.jsp" %>
        <%@include file="/navigator/Toast.jsp" %>


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
                    <a class="btn btn-primary"  href="/CoffeeLand/common/ChangePassword.jsp">Đổi Mật Khẩu</a>

                </div>
                <script>
                    function goBack() {
                        window.history.back();
                    }
                </script>
            </form>

        </div>



    </body>
</html>

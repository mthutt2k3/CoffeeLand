<%-- 
    Document   : ChangePassword
    Created on : Oct 8, 2023, 2:19:36 PM
    Author     : lap21
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.Users.*" %>
<%@page import = "java.util.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="model.News.News" %>
<%@include file="/navigator/Toast.jsp" %>
<%@page import="model.Information.*"%>
<%@page import="model.Users.Users"%>


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
<html lang="en">
    <head>
          <title>The Coffee Land</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/style.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

        <style>
            

            .change-container {
                margin-top: 400px;
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
            .change-container input[type="password"],
            .change-container input[type="submit"] {
                width: 100%; /* Đặt kích thước các input là 100% của chiều rộng của phần tử cha */
                margin-bottom: 10px; /* Thêm khoảng cách giữa các input */
            }


            .change-container h1 {
                color: #333; /* Màu cho tiêu đề "Thay đổi mật khẩu" */
                font-family: 'Spectral', serif; /* Font chữ mềm mại hơn */
                text-align: center; /* Canh giữa nội dung trong phần tử change-container */

            }
            .change-container h6 {
                color: #333; /* Màu cho tiêu đề "Thay đổi mật khẩu" */
                font-family: 'Spectral', serif; /* Font chữ mềm mại hơn */
                text-align: center; /* Canh giữa nội dung trong phần tử change-container */

            }

            .change-container input[type="submit"] {
                background-color: #99cc00; /* Màu nền của nút, ví dụ: màu xanh dương */
                color: #fff; /* Màu chữ của nút */
                font-family: 'Spectral', serif; /* Font chữ mềm mại hơn */
                border: none; /* Loại bỏ viền của nút */
                border-radius: 10px; /* Bo tròn các góc của nút */
                padding: 10px 20px; /* Thêm padding cho nút */
                transition: background-color 0.3s, color 0.3s; /* Thêm hiệu ứng chuyển đổi màu */
            }

            .change-container input[type="submit"]:hover {
                background-color: #0056b3; /* Màu nền của nút khi hover */
                color: #fff; /* Màu chữ của nút khi hover */
            }
            .change-container p {
                font-weight: bold;
                color: black;
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
            body{
                background-color: #6b3e2e;
                
            }


        </style>

    </head>
    <body>
        <!--nav-->
        <%@include file="/navigator/Navbar.jsp" %>
        <%--<%@include file="/navigator/Toast.jsp" %>--%>
        <%@include file="/navigator/sideBarAdminManage.jsp" %>

        <div class="change-container" style="background-color: #e3c099; ">
            <h1>Thay đổi mật khẩu</h1>
            <%-- Sử dụng biến requestScope để lấy giá trị từ request --%>
            <h6 style="color: ${requestScope.success ? 'green' : 'red'};">${requestScope.er}</h6>

            <form action="/CoffeeLand/changepassword" method="post">
                <input type="hidden" name="userId" value="<%=xUser.getUserId()%>"/>
                <p>Nhập mật khẩu cũ <span style="color: red;">*</span></p><input type="password" name="pass" placeholder="Nhập mật khẩu cũ vào đây..."/>
                <p>Nhập mật khẩu mới <span style="color: red;">*</span></p><input type="password" name="newpass" placeholder="Nhập mật khẩu mới vào đây..."/>
                <p>Nhập lại mật khẩu mới <span style="color: red;">*</span></p><input type="password" name="renewpass" placeholder="Nhập lại mật khẩu mới vào đây..."/>

                <input type="submit" value="Thay đổi"style="background-color: #993300" />
            </form>
        </div>



    </body>
</html>

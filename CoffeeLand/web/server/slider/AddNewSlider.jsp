<%-- 
    Document   : AddNewSlider
    Created on : Mar 6, 2024, 9:45:55 AM
    Author     : dell
--%>

<%@page import="model.Information.*"%>
<%@page import="model.Users.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Slider.*" %>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Slider Page</title>
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/CSSMaketer/addslider.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/style.css"/>
        <style>
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
        </style>
    </head>
    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        session = request.getSession(false);
        Users xUser = (Users) session.getAttribute("user");
        if (xUser == null) {
            response.sendRedirect("/CoffeeLand/server/Users/login.jsp");
        } else {
    %>
    <%@include file="/navigator/Navbar.jsp" %>
    <%
        }
    %>
    <body>
        <%@include file="/navigator/Toast.jsp" %>
        <%@include file="/navigator/sideBarMKTManage.jsp" %>
        <div class="wrapper bg-bodylist" style="height: fit-content; min-height: 100vh">
            <div class="container main" style="margin-top: 62px">
                <div class="row bg-bodylist" style="height: fit-content">
                    <div class="mb-3 mt-2">
                        <form class="search-form mx-auto" action="/CoffeeLand/control" method="GET">
                            <input type="hidden" name="target" value="ProductManage"/>
                            <input type="hidden" name="requestAction" value="Search"/>
                            <input type="search" name="input" class="search-inputlist form-control" placeholder="Tìm sản phẩm...">
                            <button type="submit" class="search-button"></button>
                        </form>
                    </div>
                    <div class="form-slider">
                        <form action="/CoffeeLand/addslider" method="post" enctype="multipart/form-data">
                            <p>Tải ảnh slide lên: <input type="file" name="file" required/></p>
                            <label>Thứ tự hiện trên slide:</label>
                            <select name="orderS">
                                <%
                                    SliderDAO sd = new SliderDAO();
                                    List<Integer> listSlider = sd.getOrderOfSlide();
                                    for(Integer order: listSlider){
                                %>
                                <option value="<%= order %>"><%= order %></option>
                                <% } %>
                                </select>
                            
                            <p>Liên kết của slide (link): <input type="text" name="link" required/></p>
                            <input type="submit" value="Tải lên"/>
                        </form>
                    </div>


                </div>

            </div>


        </div>
    </body>
</html>

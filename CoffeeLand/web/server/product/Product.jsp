<%-- 
    Document   : Product
    Created on : Jan 20, 2024, 4:51:25 PM
    Author     : acer
--%>
<%@page import="model.Information.*"%>
<%@page import="model.Users.Users"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.Product.*" %>
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
    ProductDAO u = new ProductDAO();
    String productId = request.getParameter("productId");
    Product product = u.getProduct(productId);
    
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sản phẩm | CoffeeLand</title>
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/style.css"/>
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/Rate.css"/>
    </head>
    <body>
        <!--nav-->
        <%@include file="/navigator/Navbar.jsp" %>
        <%@include file="/navigator/Toast.jsp" %>
        <%@include file="/navigator/sideBarMKTManage.jsp" %>

        <div class="wrapper bg-dark" id="home" style="min-height: 915px">
            <!--post-->
            <div class="container bg-dark bg-opacity-80" style="height: 800px; margin-top: 80px">
                <div class="row h-auto w-auto p-5 d-flex align-items-center justify-content-center">
                    <div class="col-md-6">
                        <img class="img img-thumbnail w-100" src="/CoffeeLand/assests/image/product_thumnail/<%=product.getImage()%>" alt="">
                    </div>
                    <div class="col-md-6 p-5">
                        <h1 >
                            <%=product.getProductName()%>
                        </h1>
                        <h5 style="color: #f5c2c7; display: flex">
                            Loại:  
                            <span class="text-info ms-2">
                                <button type="submit" 
                                        class="btn btn-light btn-sm p-1"
                                        style="background-color: #3dd5f3; border: none"
                                        >
                                    <%=product.getCategory()%>
                                </button>
                            </span>
                        </h5>

                        <P class="text-secondary">
                            <%=product.getDescription()%>
                        </P>
                        <ul>
                            <li class="text-warning">
                                Quality checked by CoffeeLand
                                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                </svg>
                            </li>
                            <li class="text-warning">
                                Future updates
                                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                </svg>
                            </li>
                            <li class="text-warning">
                                Buy one time, use life time
                                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                </svg>
                            </li>
                        </ul>
                        <ul style="display: flex">
                            <h3 class="text-decoration-line-through text-secondary me-3">
                                $<%=product.getOriginalPrice()%>
                            </h3>
                            <h1 style="color: #20c997">
                                $<%=product.getRetailPrice()%>
                            </h1>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

<%-- 
    Document   : UpdateCategory
    Created on : Feb 11, 2024, 9:57:02 PM
    Author     : acer
--%>
<%@page import="model.Information.*"%>
<%@page import="model.Category.Category"%>
<%@page import="java.util.List"%>
<%@page import="model.Users.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    session = request.getSession(false);
    Users xUser = new Users();
    if (session.getAttribute("user") != null) {
        xUser = (Users) session.getAttribute("user");
    } else {
        response.sendRedirect("/CoffeeLand/server/Users/login.jsp");
    }
    List<Category> lstCat = (List<Category>) session.getAttribute("lstCategory");
    Category x = (Category) session.getAttribute("x");
    if(x == null){
        x = new Category(0, "", "");
    }
%>
    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/style_login.css"/>
        <title>Cập nhật loại sản phẩm|CoffeeLand</title>
    </head>
    <body>
        <!--nav-->
        <%@include file="/navigator/Navbar.jsp" %>
        <%@include file="/navigator/Toast.jsp" %>
        <%@include file="/navigator/sideBarMKTManage.jsp" %>

        <div class="wrapper">
            <div class="container main">
                <div class="row" style="height: 800px; width: 1200px">
                    <div class="col-md-6 side-image"  style="display: flex; justify-content: center; align-items: center;">
                        <img style="height: 500px; width: 600px;" src='/CoffeeLand/assests/image/background/AddProduct2.jpg'>
                    </div>
                    <div class="col-md-6 right">
                        <div class="input-box">
                           <header>Cập nhật loại sản phẩm</header>
                           <form action="/CoffeeLand/updatecategory" method="get">
                               <div class="">
                                   <input type="hidden" class="input" name="categoryId" value="<%=x.getCategoryId()%>" required>
                                </div>
                               <div class="">
                                    <div class="text-info">Tên loại sản phẩm *<div>
                                            <input type="text" class="input" name="categoryName" value="<%=x.getCategoryName()%>" required>
                                </div>
                                <div class="input-field">
                                    <input type="submit" class="submit" name="requestAction" value="Cập nhật loại sản phẩm">
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

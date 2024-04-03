<%-- 
    Document   : AddNews
    Created on : Feb 13, 2024, 11:26:26 AM
    Author     : acer
--%>
<%@page import="model.Information.*"%>
<%@page import="model.News.News"%>
<%@page import="java.util.List"%>
<%@page import="model.Users.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>
    
<%
    session = request.getSession(false);
    Users xUser = new Users();
    if (session.getAttribute("user") != null) {
        xUser = (Users) session.getAttribute("user");
    } else {
        response.sendRedirect("/CoffeeLand/server/Users/login.jsp");
    }
    List<News> lstNews = (List<News>) session.getAttribute("lstNews");
    News x = (News) session.getAttribute("x");
    if(x == null){
        x = new News(0, "", "", "", "", "", "");
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
        <title>Thêm tin tuc|CoffeeLand</title>
    </head>
    <body>
        <!--nav-->
        <%@include file="/navigator/Navbar.jsp" %>
        <%@include file="/navigator/Toast.jsp" %>
        <%@include file="/navigator/sideBarMKTManage.jsp" %>

        <div class="wrapper">
            <div class="container main">
                <div class="row" style="height: 800px; width: 1200px">
                    <div class="col-md-6 side-image" style="background-image: url(/CoffeeLand/assests/image/background/AddProduct2.jpg)">
                    </div>
                    <div class="col-md-6 right">
                        <div class="input-box">
                           <header>Thêm tin tức</header>
                           <form action="/CoffeeLand/addnews" method="POST" enctype="multipart/form-data">
                               <input type="hidden" name="userId" value="<%=xUser.getUserId()%>">
                               <div class="">
                                    <div class="text-info">Tiêu đề *<div>
                                        <textarea type="text" class="input" name="title"required><%=x.getTitle()%></textarea>
                                </div>
                                <div class="">
                                    <div class="text-info">Nội dung *<div>
                                        <textarea type="text" class="input" name="content"required><%=x.getContent()%></textarea>
                                </div>
                                <div class="">
                                    <div class="text-info">Hình đại diện *</div>
                                    <input type="file" class="input" placeholder=".jpg | .png" pattern="^.+(\.jpg|\.png)$" title="must end witth .jpg or .png" name="image" value="" required>
                                </div>
                                <div class="mb-3">
                                    <div class="text-info mb-1">Mức độ *</div>
                                    
                                    <label class="text-light text-opacity-75">
                                        <input type="radio" name="priorityId" value="1" <%= "High".equalsIgnoreCase(x.getPriorityName()) ? "checked" : "" %> required> Cao
                                    </label>
                                    <label class="text-light text-opacity-75">
                                        <input type="radio" name="priorityId" value="2" <%= "Medium".equalsIgnoreCase(x.getPriorityName()) ? "checked" : "" %> required> Trung bình
                                    </label>
                                    <label class="text-light text-opacity-75">
                                        <input type="radio" name="priorityId" value="3" <%= "Low".equalsIgnoreCase(x.getPriorityName()) ? "checked" : "" %> required> Thấp
                                    </label>
                                    
                                </div>
                                <div class="input-field">
                                    <input type="submit" class="submit" value="Thêm tin tức">
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


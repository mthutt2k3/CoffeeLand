<%-- 
    Document   : UpdateProduct
    Created on : Jan 20, 2024, 4:52:21 PM
    Author     : acer
--%>
<%@page import="model.Information.*"%>
<%@page import="model.Category.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import = "model.Users.*" %>
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
    List<Category> lstCat = (List<Category>) session.getAttribute("lstCategory");
    Product x = (Product) session.getAttribute("x");
    if(x == null){
        x = new Product(0, "", "", "", "", "", "", "", "", "");
    }
%>
    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/style_login.css"/>
        <title>Cập nhật sản phẩm|CoffeeLand</title>
    </head>
    <style>
            .input-box header{
                font-size: x-large;
                font-weight: 700;
                text-align: center;
                margin-bottom: 13px;
                color: #fff;
            }
            .mainn{
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
            }
            /* Style cho dropdown box */
            #categorySelect {
                padding: 8px;
                font-size: 14px;
                border: 1px solid #ccc;
                border-radius: 4px;
                width: 150px;
                outline: none;
                background-color: #fff;
                color: #333;
            }

            /* Style cho các option trong dropdown */
            #categorySelect option {
                padding: 8px;
                font-size: 14px;
                cursor: pointer;
            }

            /* Hover style cho option */
            #categorySelect option:hover {
                background-color: #f0f0f0;
            }


        </style>
    <body>
        <!--nav-->
        <%@include file="/navigator/Navbar.jsp" %>
        <%@include file="/navigator/Toast.jsp" %>
        <%@include file="/navigator/sideBarMKTManage.jsp" %>

        <div class="wrapper">
            <div class="container mainn">
                
                <div class="col-md-3 left">
                    <form action="/CoffeeLand/updateimg" method="post" enctype="multipart/form-data">
                        <input type="file" class="" name="photo">
                        <input type="hidden" name="target" value="UpdateProduct">
                        <button type="submit" class="btn btn-outline-success mx-1 border-0 p-0" title="Cap nhat anh">
                            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                            <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                            </svg>
                        </button>
                    </form>
                    <img style="height: 200px; width: 200px;" src='/CoffeeLand/assests/image/thumbnails/${filename}'/>
                </div>
                <div class="col-md-6 right">
                    <div class="input-box" style="width: 700px;
                        box-sizing: border-box;
                        color: #fff;">
                        <header style="font-size: x-large;
                                font-weight: 700;
                                text-align: center;
                                margin-bottom: 13px;
                                color: red;">Cập nhật sản phẩm</header>
                                <form action="/CoffeeLand/updateproduct" method="post" enctype="multipart/form-data">
                            <table class="table">
                                <td class="col-md-2">
                                    <div class="text-info">Tên sản phẩm*</div>
                                        <textarea type="text" class="input" name="productName"required><%=x.getProductName()%></textarea>
                                    
                                    <div class="text-info">Loại*</div>
                                    <div class="input">
                                        <select id="categorySelect" class="input" name="categoryId" required>
                                            <option value="">--Chọn loại sản phẩm--</option>
                                            <c:forEach items="${lstCategory}" var="c">
                                                <option value="${c.getCategoryId()}" ${(c.getCategoryId() == categoryId) ? "selected = \"selected\"" : ""}>${c.getCategoryName()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    
                                    <div class="text-info">Mô tả*<div>
                                            <textarea type="text" class="input" name="description" style="height: 250px" required><%=x.getDescription()%></textarea>
                                    
                                </td>
                                <td class="col-md-1">
                                    <div class="">
                                        <div class="text-info">Giá sản phẩm *</div>
                                    </div>
                                    <c:forEach items="${lstSize}" var="s">
                                        <div class="">
                                            <div class="text-info text-warning">+ Giá sản phẩm size ${s.getSizeName()} </div>
                                            <c:choose>
                                                <c:when test="${s.getSizeName() eq 'S'}">
                                                    <input type="text" class="input" placeholder="0.000 đ" name="priceS" value="${priceS}">
                                                </c:when>
                                                <c:when test="${s.getSizeName() eq 'M'}">
                                                    <input type="text" class="input" placeholder="0.000 đ" name="priceM" value="${priceM}">
                                                </c:when>
                                                <c:when test="${s.getSizeName() eq 'L'}">
                                                    <input type="text" class="input" placeholder="0.000 đ" name="priceL" value="${priceL}">
                                                </c:when>
                                            </c:choose>
                                        </div>
                                    </c:forEach>
                                    <div class="mb-3">
                                        <div class="text-info mb-1">Trạng thái *</div>
                                        <label class="text-light text-opacity-75">
                                            <input type="radio" name="statusId" value="1" <%= "1".equals(x.getStatusName()) ? "checked" : "" %> required> Hoạt động
                                        </label>
                                        <label class="text-light text-opacity-75">
                                            <input type="radio" name="statusId" value="2" <%= "2".equals(x.getStatusName()) ? "checked" : "" %> required> Ngừng hoạt động
                                        </label>
                                    </div>
                                </td>
                            </table>
                            <div class="">
                                <input type="hidden" class="" name="thumbnail" value=${filename}>
                                <input type="hidden" class="input" name="userId" value="<%=xUser.getUserId()%>">
                            </div>
                            
                            <div class="">
                                <div class="input-field">
                                    <input type="submit" class="submit" name="requestAction" value="Cập nhật">
                                </div> 
                            </div>      
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript" type="text/javascript" src="/CoffeeLand/assests/js/bootstrap.bundle.js"></script>
    </body>
</html>


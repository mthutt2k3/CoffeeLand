<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List" %>
<%@page import="model.News.News" %>
<%@page import="model.Information.*"%>
<%@page import="model.Users.Users"%>
<%@page import="model.Information.*"%>
<%@page import="model.Users.Users"%>
<%@page import="java.util.*"%>
<%@page import="model.Product.*"%>
<%@page import="model.Category.*"%>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    
    session = request.getSession(false);
    Users xUser = new Users();
    if (session.getAttribute("user") != null) {
        xUser = (Users) session.getAttribute("user");
    } else {
        response.sendRedirect("/CoffeeLand/server/Users/login.jsp");
    }
    
    if (session.getAttribute("x") != null){
        session.removeAttribute("x");
    }
    List<Product> lst = (List<Product>) session.getAttribute("lstProduct");
    List<Category> lstCat = (List<Category>) session.getAttribute("lstCategory");
    String colName;
    
%>  
<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý sản phẩm</title>
        <meta charset="UTF-8">
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
    <body style="background-color: #94927b; ">

        <!--nav-->
        <%@include file="/navigator/Navbar.jsp" %>
        <%@include file="/navigator/Toast.jsp" %>
        <%@include file="/navigator/sideBarMKTManage.jsp" %>

        <div class="noidung" style="margin-top: 100px; width: 1200px; margin-left: auto; margin-right: auto;">
            <div style="display: flex;margin-bottom: 10px;" >
                <div class="mb-3 mt-2 text-center">
                        <h class="text-light big-title" style="font-size: 53px; font-weight: bold;">
                            Quản lý bình luận
                        </h>
                    </div>
<!--                <form action="/CoffeeLand/productfilterforfb" method="post" style="margin-right: 20px;">
                    Danh sách sản phẩm:
                    <select name="productName">
                        <option value="">Tất cả</option>
                        <c:forEach items="${listproduct}" var="product">
                            <option value="${product.productName}">${product.productName}</option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="Lọc">
                </form>

                <form action="/CoffeeLand/searchfeedback" method="POST">
                    <input type="hidden" name="index" value="1">
                    Tìm kiếm: <input class="searchBox" type="text" name="txtSearch" required>
                    <input class="searchButton" type="submit" name="btnGo" value="Tìm Kiếm" required>
                </form>-->
            </div>


            <table class="table table-bordered">
                <thead class="thead-dark" style="background-color: #5E5643; color: white; text-align: center;" >
                    <tr>
                        <th>Tên Sản Phẩm</th>
                        <th>Tên Người Dùng</th>
                        <th>Sao</th>
                        <th>Ảnh</th>
                        <th>Nội Dung</th>
                        <th>Trạng thái</th>
                        <th>Thời gian</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${lst}" var="feedback">
                        <tr>
                            <td>${feedback.productName}</td>
                            <td>${feedback.username}</td>
                            <td>${feedback.ratedStar}</td>
                            <td><img src="/CoffeeLand/assests/image/feedback/${feedback.image}" style="width: 100px; height: 60px;" alt="Image"></td>
                            <td>${feedback.message}</td>
                            <td>
                                <input type="radio" id="statusId_${feedback.getFeedbackId()}" name="statusId_${feedback.getFeedbackId()}" value="1" <c:if test="${feedback.getStatusId() eq 1}">checked</c:if>>
                                <label for="statusId_${feedback.getFeedbackId()}">Hiện</label>
                                <input type="radio" id="statusId_${feedback.getFeedbackId()}" name="statusId_${feedback.getFeedbackId()}" value="2" <c:if test="${feedback.getStatusId() eq 2}">checked</c:if>>
                                <label for="statusId_${feedback.getFeedbackId()}">Ẩn</label>
                            </td>
                            <td>${feedback.getFeedbackTime()}</td>
                            <td>
                                <form action="/CoffeeLand/feedbacklist" method="POST">
                                    <input type="hidden" name="feedbackId" value="${feedback.getFeedbackId()}">
                                    <input type="hidden" name="statusId" value="${feedback.getStatusId() == 1 ? 2 : 1}">
                                    <button type="submit" class="btn btn-primary">Lưu</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="row justify-content-center">
                <ul class="pagination">
                    <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                        <a class="page-link" href="/CoffeeLand/feedbacklist?page=1">First</a>
                    </li>
                    <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                        <a class="page-link" href="/CoffeeLand/feedbacklist?page=${currentPage - 1}">Previous</a>
                    </li>
                    <c:forEach begin="1" end="${totalPages}" var="page">
                        <li class="page-item ${page == currentPage ? 'active' : ''}">
                            <a class="page-link" href="/CoffeeLand/feedbacklist?${page}">${page}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                        <a class="page-link" href="/CoffeeLand/feedbacklist?page=${currentPage + 1}">Next</a>
                    </li>
                    <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                        <a class="page-link" href="/CoffeeLand/feedbacklist?page=${totalPages}">Last</a>
                    </li>
                </ul>
            </div>
        </div>
    </body>
</html>

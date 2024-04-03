<%-- 
    Document   : CategoryManage
    Created on : Feb 10, 2024, 10:16:00 PM
    Author     : acer
--%>
<%@page import="model.Information.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Users.Users"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    
    List<Category> lstCat = (List<Category>) session.getAttribute("lstCategory");
    String colName;
    
%>  
<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý loại sản phẩm</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/style.css"/>
        <style>
            .bg-navbar {
                --bs-bg-opacity:1;
                background-color:#5e5643;
            }
            .dropdown-menu-darknavbar {
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
            .bg-bodylist {
                --bs-bg-opacity:1;
                background-color:#94927b;
            }
            .search-inputlist {
                font-size: 14px;
                border: none;
                background: #c5c1ae;
                margin: 0;
                color: inherit;
                border: 1px solid black;
                border-radius: inherit;
            }
            .table-infolist {
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
            .btn-outline-infolist {
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
    <body>
        <!--nav-->
        <%@include file="/navigator/Navbar.jsp" %>
        <%@include file="/navigator/Toast.jsp" %>
        <%@include file="/navigator/sideBarMKTManage.jsp" %>

        <!--Main information-->
        <div class="wrapper bg-bodylist" style="height: fit-content; min-height: 100vh">
            <div class="container main" style="margin-top: 62px">
                <div class="row bg-bodylist" style="height: fit-content">
                    <div class="mb-3 mt-2 text-center">
                        <h class="text-light big-title" style="font-size: 53px; font-weight: bold;">
                            Quản lý loại sản phẩm
                        </h>
                    </div>
                    <div class="mb-3 mt-2">
                        <form class="search-form mx-auto" action="/CoffeeLand/control" method="GET">
                            <input type="hidden" name="target" value="CategoryManage"/>
                            <input type="hidden" name="requestAction" value="Search"/>
                            <input type="search" name="input" class="search-inputlist form-control" placeholder="Tìm kiếm...">
                            <button type="submit" class="search-button"></button>
                        </form>
                    </div>
                    
                    <div>
                        <form action="/CoffeeLand/control" method="POST">
                            <input type="text" name="categoryName" placeholder="Nhập loại sản phẩm mới...">
                            <input type="hidden" name='requestAction' value="Add Category"/>
                            <button type="submit" class="btn btn-outline-warning mx-1 border-0 p-0" title="Add Category">
                                Thêm loại sản phẩm
                            </button>
                        </form>
                    </div>
                    
                    <br/>
                    <br/>
                    

                    <div class="col-md-12">
                        <table class="table table-hover table-bordered">
                            <tr class="table-infolist">
                                <th class="col-md-1">
                                    <div style="display: flex">
                                        ID
                                        <%colName = "categoryId";%>
                                        <%@include file="/server/category/CategorySortForm.jsp"%>
                                    </div>
                                </th>
                                <th class="col-md-4">
                                    <div style="display: flex">
                                        Loại sản phẩm
                                        <%colName = "categoryName";%>
                                        <%@include file="/server/category/CategorySortForm.jsp"%>
                                    </div>
                                </th>
                                <th class="col-md-3">
                                    <div style="display: flex">
                                        Số lượng sản phẩm
                                        <%colName = "countProduct";%>
                                        <%@include file="/server/category/CategorySortForm.jsp"%>
                                    </div>
                                </th>
                            <th class="col-md-2 text-center">
                                Tùy chọn
                            </th>
                            </tr>
                            <c:choose>
                                <c:when test="${empty lstCategory}">
                                    <div class="row h-auto w-auto mt-2" style="padding-left: 100px; padding-right: 100px">
                                        <h1 class="text-warning text-center">Không có loại sản phẩm</h1>
                                    </div>
                                </c:when>
                                <c:otherwise>

                                    <c:forEach items="${lstCategory}" var="x">
                                        <tr>
                                            <td class="text-light ">${x.getCategoryId()}<span class="ms-1 text-secondary"></span></td>
                                            <td class="text-light ">${x.getCategoryName()}<span class="ms-1 text-secondary"></span></td>
                                            <td class="text-light ">${x.getCountProduct()}<span class="ms-1 text-secondary"></span></td>
                                            <td class="" style="display: flex; align-items: center; justify-content: center;">
                                                <form action="/CoffeeLand/updatecategory" method="get">
                                                    <input type="hidden" name="categoryId" value="${x.getCategoryId()}">
                                                    <input type="hidden" name='requestAction' value="Update Category"/>
                                                    <button type="submit" class="btn btn-outline-success mx-1 border-0 p-0" title="Cập nhật loại sản phẩm">
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                                        <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                                        <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                                        </svg>
                                                    </button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </table>
                        
                    <div class="row justify-content-center">
                        <ul class="pagination">
                            <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                <a class="page-link" href="/CoffeeLand/categorylist?page=1">Trang 1</a>
                            </li>
                            <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                <a class="page-link" href="/CoffeeLand/categorylist?page=${currentPage - 1}">Trang trước</a>
                            </li>
                            <c:forEach begin="1" end="${totalPages}" var="page">
                                <li class="page-item ${page == currentPage ? 'active' : ''}">
                                    <a class="page-link" href="/CoffeeLand/categorylist?${page}">${page}</a>
                                </li>
                            </c:forEach>
                            <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                <a class="page-link" href="/CoffeeLand/categorylist?page=${currentPage + 1}">Trang sau</a>
                            </li>
                            <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                <a class="page-link" href="/CoffeeLand/categorylist?page=${totalPages}">Trang cuối</a>
                            </li>
                        </ul>
                    </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

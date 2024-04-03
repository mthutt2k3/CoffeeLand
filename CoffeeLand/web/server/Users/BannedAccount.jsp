<%-- 
    Document   : UserManage
    Created on : Oct 5, 2023, 6:05:27 PM
    Author     : lap21
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

<%
    List<Users> lst = (List<Users>) session.getAttribute("lstBanned");
    String colName;
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý người dùng|CoffeeLand</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/style.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
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
    <body>
        <!--nav-->
        <%@include file="/navigator/Navbar.jsp" %>
        <%@include file="/navigator/Toast.jsp" %>
        <%@include file="/navigator/sideBarAdminManage.jsp" %>

        <!--Main information-->
        <div class="wrapper bg-bodylist" style="height: fit-content; min-height: 100vh">
            <div class="container main" style="margin-top: 62px">
                <div class="row bg-bodylist" style="height: fit-content">
                    <div class="mb-3 mt-2 text-center">
                        <h class="text-light big-title" style="font-size: 53px; font-weight: bold;">
                            Tài khoản bị ngừng hoạt động
                        </h>
                    </div>
                    <div class="col-md-12">
                        <table class="table table-hover table-bordered">
                            <tr class="table-infolist">
                                <th class="col-md-2">Mã</th>
                                <th class="col-md-2 text-center">
                                    <div style="display: flex">
                                        Tên
                                        <%colName = "name";%>
                                        <%@include file="/server/Users/UsersSortForm.jsp"%>
                                    </div>
                                </th>
                                <th class="col-md-2">Số điện thoại</th>
                                <th class="col-md-2">Email</th>
                                <th class="col-md-1 text-center">
                                    <button class="btn p-0 dropdown-toggle fw-bold"
                                            id="typeDropdown" 
                                            role="button"
                                            data-bs-toggle="dropdown"
                                            aria-expanded="false"
                                            >
                                        Vai trò
                                    </button>
                            <d class="dropdown-menu dropdown-menu-dark"
                               aria-labelledby="typeDropdown">
                                <li>
                                    <form action="/CoffeeLand/control">
                                        <input type="hidden" name="requestAction" value="Get User By Role">
                                        <input type="hidden" name="role" value="all">
                                        <button type="submit" 
                                                class="dropdown-item d-flex align-items-center"
                                                >
                                            Tất cả
                                        </button>
                                    </form>
                                </li>
                                <c:forEach items="${lstRoles}" var="c">
                                    <li>
                                        <form action="/CoffeeLand/control">
                                            <input type="hidden" name="requestAction" value="Get User By Role">
                                            <input type="hidden" name="role" value="${c.getRoleId()}">
                                            <button type="submit" 
                                                    class="dropdown-item d-flex align-items-center"
                                                    >
                                                ${c.getRoleId()}
                                            </button>
                                        </form>
                                    </li>
                                </c:forEach>
                            </d>
                            </th>
                            <th class="col-md-1">Tùy chọn</th>
                            </tr>
                            <%
                                if(lst != null){
                                    for(Users x: lst) {
                            %>
                            <tr>
                                <td class="text-light"><%= x.getUserName() %></td>
                                <td class="text-light"><%= x.getName() %></td>
                                <td class="text-light"><%= x.getPhoneNumber() %></td>
                                <td class="text-light"><%= x.getEmail() %></td>
                                <td class="text-light"><%= x.getRoleId() %></td>

                                <% { %>
                                <td class="form-control" style="display: flex">
                                    <form action="/CoffeeLand/control" method="POST">
                                        <input type="hidden" name="userId" value="<%=x.getUserId()%>"/>
                                        <input type="hidden" name='requestAction' value="Inactive User"/>
                                        <button type="submit" class="btn btn-outline-danger border-0 p-0" title="Kích hoạt tài khoản">
                                            <i class="fas fa-user-slash" style="width: 25px; height: 25px; font-size: 22px;"></i>
                                        </button>
                                    </form>
                                </td>
                                <% } %>
                            </tr>
                            <% }} %> 
                        </table>
                        <div class="row justify-content-center">
                            <ul class="pagination">
                                <c:forEach begin="1" end="${totalPages}" varStatus="page">
                                    <li class="page-item ${page.index == currentPage ? 'active' : ''}">
                                        <a class="page-link" href="/CoffeeLand/banned?page=${page.index}">${page.index}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>


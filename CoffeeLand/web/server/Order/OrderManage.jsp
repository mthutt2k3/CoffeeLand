<%-- 
    Document   : UserManage
    Created on : Oct 5, 2023, 6:05:27 PM
    Author     : lap21
--%>
<%@page import="model.Information.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.Users.*" %>
<%@page import = "model.Orders.*" %>
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
    List<Orders> lst = (List<Orders>) session.getAttribute("lstOrder");
    List<Users> lstSaler = (List<Users>) session.getAttribute("lstSaler");
    String type;
    if(session.getAttribute("pageType")==null){
    type="";}
    else{
    type = (String) session.getAttribute("pageType");}
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
        <%if(xUser.isSalerManager()){%>
        <%@include file="/navigator/sideBarSalerManager.jsp" %>
        <%}else{%>
        <%@include file="/navigator/sideBarSaler.jsp" %>
        <%}%>
        <!--Main information-->
        <div class="wrapper bg-bodylist" style="height: fit-content; min-height: 100vh">
            <div class="container main" style="margin-top: 62px">
                <div class="row bg-bodylist" style="height: fit-content">
                    <div class="mb-3 mt-2 text-center">
                        <h class="text-light big-title" style="font-size: 53px; font-weight: bold;">
                            
                            <%if(xUser.isSalerManager()){%>
                            Đơn hàng chưa được xử lý
                            <%}else{%>
                            Quản lý Đơn hàng
                            <%}%>
                        </h>
                    </div>
                    <div class="mb-3 mt-2">
                        <%if(xUser.isSaler()){%>
                        <div class="mb-3 mt-2">
                            <form class="search-form mx-auto" action="/CoffeeLand/control" method="GET">
                                <input type="hidden" name="target" value="Order"/>
                                <input type="hidden" name="requestAction" value="Search"/>
                                <input type="search" name="input" class="search-inputlist form-control" placeholder="Tìm kiếm sản phẩm...">
                                <button type="submit" class="search-button"></button>
                            </form>
                        </div>
                        <%}%>
                    </div>

                    <div class="col-md-12">

                        <table class="table table-hover table-bordered">
                            <tr class="table-infolist">
                                <%if(xUser.isSalerManager()){%>
                                <th class="col-md-2 text-center">
                                    <div style="display: flex">
                                        Mã đơn hàng
                                    </div>
                                </th>
                                <th class="col-md-2 text-center">
                                    <div style="display: flex">
                                        Thời gian
                                    </div>
                                </th>
                                <th class="col-md-2">Tên người nhận</th>
                                <th class="col-md-2 text-center">
                                    <div style="display: flex">
                                        Tổng tiền(VND)
                                    </div>
                                </th>
                                <%}else{%>
                                <th class="col-md-2 text-center">
                                    <div style="display: flex">
                                        Mã đơn hàng
                                        <%colName = "orderCode";%>
                                        <%@include file="/server/Order/OrderSortForm.jsp"%>
                                    </div>
                                </th>
                                <th class="col-md-2 text-center">
                                    <div style="display: flex">
                                        Thời gian
                                        <%colName = "orderTime";%>
                                        <%@include file="/server/Order/OrderSortForm.jsp"%>
                                    </div>
                                </th>
                                <th class="col-md-2">Tên người nhận</th>
                                <th class="col-md-2 text-center">
                                    <div style="display: flex">
                                        Tổng tiền(VND)
                                        <%colName = "grandTotal";%>
                                        <%@include file="/server/Order/OrderSortForm.jsp"%>
                                    </div>
                                </th>
                                <%}%>
                                <%if(xUser.isSalerManager()){%>
                                <th class="col-md-1">Phân công</th>
                                    <%}else{%>
                                <th class="col-md-1">Trạng thái đơn hàng</th>
                                    <%}%>
                                <th class="col-md-1">Tùy chọn</th>
                            </tr>
                            <%
                                if(lst != null){
                                    for(Orders x: lst) {
                            %>
                            <form action="/CoffeeLand/ordermanage" method="POST">
                                <tr>
                                    <td class="text-light"><%= x.getOrderCode() %></td>
                                    <td class="text-light"><%= x.getOrderTime() %></td>
                                    <td class="text-light"><%= x.getReceiverName() %></td>
                                    <td class="text-light"><%= x.getGrandTotal() %></td>
                                    <%if(xUser.isSalerManager()){%>
                                    <td class="text-light">
                                        <select class="dropdown" name="salerId">
                                            <option value="<%=xUser.getUserId()%>">Chọn saler tiếp nhận đơn</option>
                                            <%if(lstSaler!=null){
                                            for(Users a: lstSaler){%>
                                            <option value="<%=a.getUserId()%>"><%=a.getUserName()%></option>
                                            <%}}%>

                                        </select>
                                    </td>
                                    <%}else{%>
                                    <td class="text-light">
                                        <select class="dropdown" name="status">
                                            <%
                                            if(x.getStatusId().equals("1")){
                                            %>
                                            <option value="0">Chờ xác nhận</option>
                                            <%}%>
                                            <%
                                            if(x.getStatusId().equals("2")){
                                            %>
                                            <option value="0">Đang chuẩn bị</option>
                                            <%}%>
                                            <%
                                            if(x.getStatusId().equals("3")){
                                            %>
                                            <option value="0">Đang giao hàng</option>
                                            <%}%>
                                            <%
                                            if(x.getStatusId().equals("4")){
                                            %>
                                            <option value="0">Giao hàng thành công</option>
                                            <%}%>
                                            <%
                                            if(x.getStatusId().equals("5")){
                                            %>
                                            <option value="0">Giao hàng không thành công</option>
                                            <%}%>
                                            <option value="1">Chờ xác nhận</option>
                                            <option value="2">Đang chuẩn bị</option>
                                            <option value="3">Đang giao hàng</option>
                                            <option value="4">Giao hàng thành công</option>
                                            <option value="5">Giao hàng không thành công</option>
                                        </select>
                                    </td>
                                    <%}%>
                                    <td class="form-control" style="display: flex">

                                        <input type="hidden" name="orderId" value="<%=x.getOrderId()%>"/>
                                        <input type="hidden" name="orderCode" value="<%=x.getOrderCode()%>"/>
                                        <input type="hidden" name='requestAction' value="Order Detail"/>
                                        <button type="submit" class="btn btn-outline-success mx-1 border-0 p-0" title="Save">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-save" viewBox="0 0 16 16">
                                            <path d="M8 0a2.5 2.5 0 0 1 2.5 2.5c0 .838-.42 1.58-1.061 2.015-.076.05-.172.135-.282.247-.231.232-.498.498-.747.756a86.9 86.9 0 0 1-.914.887 89.143 89.143 0 0 1-.822.788 2.5 2.5 0 0 1-1.414.586A2.5 2.5 0 0 1 5 4.5c0-.435.12-.86.414-1.219A89.143 89.143 0 0 1 6.236 2.48a86.9 86.9 0 0 1 .914-.887c.249-.258.516-.524.747-.756.11-.112.206-.197.282-.247C7.58 1.58 8 0 8 0zM2.5 7a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1h-10a.5.5 0 0 1-.5-.5z"/>
                                            <path d="M2 10.5a1.5 1.5 0 0 1 1.5-1.5h9a1.5 1.5 0 0 1 1.5 1.5v4a.5.5 0 0 1-.5.5h-12a.5.5 0 0 1-.5-.5v-4z"/>
                                            </svg>
                                        </button>
                            </form>
                            <form action="/CoffeeLand/control" method="POST">
                                <input type="hidden" name="orderId" value="<%=x.getOrderId()%>"/>
                                <input type="hidden" name='requestAction' value="Order Detail"/>
                                <button type="submit" class="btn btn-outline-success mx-1 border-0 p-0" title="Update">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                    <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                    <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                    </svg>
                                </button>
                            </form>
                            </td>
                            </tr>


                            <% }} %> 
                        </table>
                        <%if(type.equals("list")){%>
                        <div class="row justify-content-center">
                            <ul class="pagination">
                                <c:forEach begin="1" end="${totalPages}" varStatus="page">
                                    <li class="page-item ${page.index == currentPage ? 'active' : ''}">
                                        <a class="page-link" href="/CoffeeLand/showlistusers?page=${page.index}">${page.index}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>


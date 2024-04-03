<%-- 
    Document   : MarketerDashboard
    Created on : Mar 20, 2024, 5:33:07 PM
    Author     : acer
--%>

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
    String[] topProduct = (String[]) session.getAttribute("topProduct");
    int[] topProductQuantity = (int[]) session.getAttribute("topProductQuantity");
    String[] TopCategory = (String[]) session.getAttribute("TopCategory");
    int[] TopCategoryQuantity = (int[]) session.getAttribute("TopCategoryQuantity");
    String[] TopFeedBack = (String[]) session.getAttribute("TopFeedBack");
    int[] TopFeedBackQuantity = (int[]) session.getAttribute("TopFeedBackQuantity");
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý người dùng|CoffeeLand</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/style.css"/>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-labels"></script>
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
        <%@include file="/navigator/sideBarMKTManage.jsp" %>

        <!--Main information-->
        <div class="wrapper bg-bodylist" style="height: fit-content; min-height: 100vh">
            <div class="container main" style="margin-top: 62px">
                <div class="row bg-bodylist" style="height: fit-content">
                    <div class="mb-3 mt-2 text-center">
                        <h class="text-light big-title" style="font-size: 53px; font-weight: bold;">
                            Bảng điều khiển
                        </h>
                    </div>
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card text-white shadow" style="background-color: rgb(99, 159, 184)">
                            <div class="card-body d-flex justify-content-center align-items-center">
                                <div class="text-center">
                                    <p class="mb-0">Sản phẩm</p>
                                    <h3 class="mb-0">${countProduct}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card text-white shadow" style="background-color: rgb(99, 159, 184)">
                            <div class="card-body d-flex justify-content-center align-items-center">
                                <div class="text-center">
                                    <p class="mb-0">Loại sản phẩm</p>
                                    <h3 class="mb-0">${countCategory}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card text-white shadow" style="background-color: rgb(99, 159, 184)">
                            <div class="card-body d-flex justify-content-center align-items-center">
                                <div class="text-center">
                                    <p class="mb-0">Tin tức</p>
                                    <h3 class="mb-0">${countNews}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card text-white shadow" style="background-color: rgb(99, 159, 184)">
                            <div class="card-body d-flex justify-content-center align-items-center">
                                <div class="text-center">
                                    <p class="mb-0">Khuyến mãi</p>
                                    <h3 class="mb-0">${countVoucher}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card text-white shadow" style="background-color: rgb(99, 159, 184)">
                            <div class="card-body d-flex justify-content-center align-items-center">
                                <div class="text-center">
                                    <p class="mb-0">Đánh giá</p>
                                    <h3 class="mb-0">${countFeedback}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div class="card text-white shadow" style="background-color: rgb(99, 159, 184)">
                            <div class="card-body d-flex justify-content-center align-items-center">
                                <div class="text-center">
                                    <p class="mb-0">Số lượng khách hàng</p>
                                    <h3 class="mb-0">${countCustomer}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div style="margin: auto;">
                            <canvas id="myPieChart1" width="200" height="200"></canvas>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div style="margin: auto;">
                            <canvas id="myPieChart2" width="200" height="200"></canvas>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6 mb-4">
                        <div style="margin: auto;">
                            <canvas id="myPieChart3" width="200" height="200"></canvas>
                        </div>
                    </div>
                    

                </div>
            </div>
        </div>
                                
             <script>
    // Dữ liệu mẫu về số lượng sản phẩm đã bán của từng loại sản phẩm
    function getRandomColor() {
    // Tạo giá trị ngẫu nhiên cho mỗi thành phần màu (RGB)
    var r = Math.floor(Math.random() * 256);
    var g = Math.floor(Math.random() * 256);
    var b = Math.floor(Math.random() * 256);

    // Tạo chuỗi màu rgba từ các giá trị ngẫu nhiên đã tạo
    var rgbaColor = 'rgba(' + r + ', ' + g + ', ' + b + ', 1)';

    return rgbaColor;
}

    const data1 = {
        labels: ['<%=topProduct[0]%>', '<%=topProduct[1]%>', '<%=topProduct[2]%>', '<%=topProduct[3]%>','<%=topProduct[4]%>','<%=topProduct[5]%>','<%=topProduct[6]%>'],
        datasets: [{
            label: 'Số lượng đã bán',
            data: [<%=topProductQuantity[0]%>, <%=topProductQuantity[1]%>, <%=topProductQuantity[2]%>, <%=topProductQuantity[3]%>, <%=topProductQuantity[4]%>, <%=topProductQuantity[5]%>, <%=topProductQuantity[6]%>],
            backgroundColor: [
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                 'rgba(255, 99, 132, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)',
                'rgba(255, 69, 0, 1)'
            ],
            borderColor: [
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                 'rgba(255, 99, 132, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)',
                'rgba(255, 69, 0, 1)'
            ],
            borderWidth: 1
        }]
    };

    const data2 = {
        labels: ['<%=TopCategory[0]%>', '<%=TopCategory[1]%>', '<%=TopCategory[2]%>', '<%=TopCategory[3]%>','<%=TopCategory[4]%>'],
        datasets: [{
            label: 'Số lượng đã bán',
            data: [<%=TopCategoryQuantity[0]%>, <%=TopCategoryQuantity[1]%>, <%=TopCategoryQuantity[2]%>, <%=TopCategoryQuantity[3]%>, <%=TopCategoryQuantity[4]%>],
            backgroundColor: [
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                 'rgba(255, 99, 132, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)'
            ],
            borderColor: [
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                 'rgba(255, 99, 132, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)'
            ],
            borderWidth: 1
        }]
    };

    const data3 = {
        labels: ['<%=TopFeedBack[0]%>', '<%=TopFeedBack[1]%>', '<%=TopFeedBack[2]%>', '<%=TopFeedBack[3]%>','<%=TopFeedBack[4]%>'],
        datasets: [{
            label: 'Số feedback',
            data: [<%=TopFeedBackQuantity[0]%>, <%=TopFeedBackQuantity[1]%>, <%=TopFeedBackQuantity[2]%>, <%=TopFeedBackQuantity[3]%>, <%=TopFeedBackQuantity[4]%>],
            backgroundColor: [
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                 'rgba(255, 99, 132, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)'
            ],
            borderColor: [
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                 'rgba(255, 99, 132, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)'
            ],
            borderWidth: 1
        }]
    };

    // Cấu hình biểu đồ
    const config1 = {
        type: 'pie',
        data: data1,
        options: {
            responsive: true,
            plugins: {
                title: {
                    display: true,
                    text: 'Biểu đồ 1: Số lượng sản phẩm đã bán'
                },
                legend: {
                    position: 'bottom',
                }
            }
        }
    };

    const config2 = {
        type: 'pie',
        data: data2,
        options: {
            responsive: true,
            plugins: {
                title: {
                    display: true,
                    text: 'Biểu đồ 2: Số lượng sản phẩm đã bán theo loại'
                },
                legend: {
                    position: 'bottom',
                }
            }
        }
    };

    const config3 = {
        type: 'pie',
        data: data3,
        options: {
            responsive: true,
            plugins: {
                title: {
                    display: true,
                    text: 'Biểu đồ 3: Số lượng feedback theo sản phẩm'
                },
                legend: {
                    position: 'bottom',
                }
            }
        }
    };

    // Vẽ biểu đồ tròn
    var myPieChart1 = new Chart(
        document.getElementById('myPieChart1'),
        config1
    );

    var myPieChart2 = new Chart(
        document.getElementById('myPieChart2'),
        config2
    );

    var myPieChart3 = new Chart(
        document.getElementById('myPieChart3'),
        config3
    );
</script>                   
    </body>
</html>






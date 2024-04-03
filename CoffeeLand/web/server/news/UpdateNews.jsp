<%-- 
    Document   : UpdateNews
    Created on : Feb 13, 2024, 3:57:32 PM
    Author     : acer
--%>
<%@page import="model.Information.*"%>
<%@page import="model.News.News"%>
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
    List<News> lst = (List<News>) session.getAttribute("lstNews");
    News x = (News) session.getAttribute("x");
    if(x == null){
        x = new News(0, "", "", "", "", "", "");
    }
%>
    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/style_login.css"/>
        <title>Cập nhật tin tức</title>
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
        </style>
    </head>
    <body>
        <!--nav-->
        <%@include file="/navigator/Navbar.jsp" %>
        <%@include file="/navigator/Toast.jsp" %>
        <%@include file="/navigator/sideBarMKTManage.jsp" %>
        <div class="wrapper">
            <div class="container mainn">
                <div class="col-md-3 left"   >
                    <form action="/CoffeeLand/updateimg" method="post" enctype="multipart/form-data">
                        <input type="file" class="" name="photo">
                        <input type="hidden" name="newsId" value="<%=x.getNewsId()%>">
                        <input type="hidden" name="target" value="UpdateNews">
                        <input type="hidden" name='requestAction' value="Update Image"/>
                        <button type="submit" class="btn btn-outline-success mx-1 border-0 p-0" title="Cap nhat anh">
                            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                            <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                            </svg>
                        </button>
                    </form>
                    <img style="height: 200px; width: 200px;" src='/CoffeeLand/assests/image/thumbnails/<%=x.getImage()%>'/>
                </div>
                <div class="col-md-6 right">
                    <div class="input-box" style="width: 700px;
                        box-sizing: border-box;
                        color: #fff;">
                        <header style="font-size: x-large;
                                font-weight: 700;
                                text-align: center;
                                margin-bottom: 13px;
                                color: red;">Cập nhật tin tức</header>
                        <div style="text-align: right; color: greenyellow;">
                            <i>Người chỉnh sửa lần cuối: <%=x.getUserName()%></i>
                        </div>
                        <form action="/CoffeeLand/updatenews" method="get" enctype="multipart/form-data">
                            <table class="table">
                                <td>
                             
                                    <div class="text-info">Tiêu đề *</div>
                                        <textarea type="text" class="input" name="title"required><%=x.getTitle()%></textarea>
                              
                                    <div class="text-info">Nội dung *<div>
                                            <textarea type="text" class="input" name="content" style="height: 300px" required><%=x.getContent()%></textarea>
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
                                    
                                    
                                </td>
                            </table>
                            <div class="">
                                <input type="hidden" class="" name="userId" value="<%=xUser.getUserId()%>" required>
                                <input type="hidden" name="newsId" value="<%=x.getNewsId()%>">
                            </div>
                            
                            <div class="">
                                
                                <br/>
                                <div class="input-field">
                                    <input type="submit" class="submit" value="Cập nhật tin tức">
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

<%-- 
    Document   : ChangePassword
    Created on : Oct 8, 2023, 2:19:36 PM
    Author     : lap21
--%>
<%@page import="model.Information.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.Users.*" %>
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
%>

<%
    String err = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/CoffeeLand/assests/css/style_login.css"/>
        <title>Change Information|WebMaster</title>
    </head>
    <body>
        <div class="wrapper">
            <div class="container main">
                <div class="row">
                    <div class="col-md-6 side-image" style="background-image: url(<%= xUser.isAdmin() ?  "/CoffeeLand/assests/images/logo/admin.png" : "/_WebMaster/assests/images/logo/Users.png"%>)">
                    </div>
                    <div class="col-md-6 right">
                        <div class="input-box">
                           <header>Change Information</header>
                           <form action="/_WebMaster/control" method="POST">
                                <div class="input-field">
                                    <input type="text" class="input" id="pass" name="firstName" value="" required="">
                                    <label for="email">First Name</label> 
                                </div> 
                                <div class="input-field">
                                    <input type="text" class="input" id="pass" name="lastName" value="" required="">
                                    <label for="email">Last Name</label>
                                </div> 
                                <%if(err != null){%>
                                    <div class="alert alert-danger p-1 mx-2" style="" role="alert">
                                        <%=err%>
                                    </div>
                                <%}%>
                                <div class="input-field">
                                    <input type="submit" class="submit" name="requestAction" value="Change Information">
                                </div>
                           </form>
                        </div>  
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript" type="text/javascript" src="/_WebMaster/assests/js/bootstrap.bundle.js"></script>
    </body>
</html>

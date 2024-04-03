<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Users.*" %>
<%@page import="model.Information.*"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    session = request.getSession(false);
    if (session.getAttribute("user") != null) {
        response.sendRedirect("/_WebMaster/client/MainPage.jsp");
    }
%>

<%
    Users x = (Users) request.getAttribute("user");
        if(x == null){
          x = new Users("","","","");
        }
    String err = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/_WebMaster/assests/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/_WebMaster/assests/css/style_login.css">

        <title>Create an account | WebMaster</title>
    </head>
    <body>
        <div class="wrapper">
            <div class="container main">
                <div class="row">
                    <div class="col-md-6 side-image" style="background-image: url(/_WebMaster/assests/images/background/Login.png)">
                        <img class="img" src="/_WebMaster/assests/images/logo/logo.png" alt="">
                    </div>
                    <div class="col-md-6 right">
                        <div class="input-box">
                            <header>Create an account</header>
                            <form action="/_WebMaster/control" method="POST">
                                <div class="input-field">
                                    <input type="text" class="input" id="first-name" name="firstName" value="<%= x.getFirstName() %>" required="">
                                    <label for="email">First Name</label>
                                </div> 
                                <div class="input-field">
                                    <input type="text" class="input" id="last-name" name="lastName" value="<%= x.getLastName() %>" required="">
                                    <label for="email">Last Name</label>
                                </div> 
                                <div class="input-field">
                                    <input type="text" class="input" id="email" name="email" value="<%= x.getEmail() %>" required="" autocomplete="off">
                                    <label for="email">Email</label> 
                                </div> 
                                <div class="input-field">
                                    <input type="password" class="input" name="password" value="" id="pass" required="">
                                    <label for="pass">Password</label>
                                </div>
                                <div class="input-field">
                                    <input type="password" class="input" name="rePassword" value="" id="re-pass" required="">
                                    <label for="pass">Confirm password</label>
                                </div> 
                                <%if(err != null){%>
                                <div class="alert alert-danger p-1 mx-2" role="alert">
                                    <%=err%>
                                </div>
                                <%}%>
                                <div class="input-field">
                                    <input type="submit" class="submit" name="requestAction" value="Register">
                                </div> 
                            </form>
                            <div class="signin">
                                <span>Already have an account? <a href="login.jsp">Login</a></span>
                            </div>
                        </div>  
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" type="text/javascript" src="assests/js/bootstrap.bundle.js"></script>
    </body>
</html>
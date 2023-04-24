<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <title>Login</title>
    </head>
    <body>
        <br>
         <div class="container text-center">
             <div class="row row-cols-2">
                <div class="col">
                    <h1>HOME nVentory</h1>
                    <h2>Login</h2>
                    <form action="login" method="post" class="row g-3">
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">@</span>
                            <input class="form-control" type="text" name="email" placeholder="Username"><br>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Password</span>
                            <input class="form-control" type="password" name="password"><br>
                            <input type="submit" value="Sign in" class="btn btn-outline-primary">
                            
                        </div>
                        <!--div class="col-12">
                        <input type="submit" value="Sign in" class="btn btn-primary mb-3">
                        </div-->
                    </form>
                    <p class="card-text bg-light" style="transform: rotate(0);">If you are not registered,                     
                    <a href="<c:url value="login"><c:param name="action" value="signup"></c:param></c:url>" class="text-warning stretched-link">Sign Up</a> here.
                    </p>
                    
                    <c:if test="${message != null}">
                        
                        <div class="alert alert-danger" role="alert">
                            ${message}
                        </div>
                    </c:if>

                    <c:if test="${message2 != null}">
                        <div class="alert alert-primary" role="alert">
                            ${message2}
                        </div>
                    </c:if>
                    
                </div>
                
             </div>
        </div>
        <br>
        <!-- Register new user -->
    
        <c:set var = "showDiv" scope = "session" value = "${showSignUp}"/>
        <div class="container" <c:if test = "${ ( showSignUp != 'yes') }"> hidden </c:if>> 
            <div class="row">
                <div class="col-4">
                    <h2>Register a User</h2>
                    <form action="login" method="post" class="row g-3">
                        
                        <input type="hidden" id="action2" name="action2" value="add">
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Email:</span>
                            <input id="email" name="email" type="text" class="form-control">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">First Name:</span>
                            <input id="firstName" name="firstName" type="text" class="form-control">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Last Name:</span>
                            <input id="lastName" name="lastName" type="text" class="form-control">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Password:</span>
                            <input id="password" name="password" type="text" class="form-control">
                            <button type="submit"  class="btn btn-outline-primary">Sign Up</button>
                        </div>
                        <br>
                    </form>
                </div>
            </div>
        </div>
    </body>

</html>

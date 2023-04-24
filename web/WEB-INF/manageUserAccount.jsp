<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <title>Manage User Account</title>
    </head>
    <body>
        <br>
        <div class="container">
            <nav class="navbar navbar-expand-lg bg-light">
                <div class="container-fluid">
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                      <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                      <h2>Manage User Account</h2>
                      <ul class="navbar-nav me-auto mb-2 mb-lg-0"><li class="nav-item"></li></ul>
                      <ul class="navbar-nav me-auto mb-2 mb-lg-0"><li class="nav-item"><h2>Welcome <%= session.getAttribute("username") %></h2> </li></ul>
                      <ul class="navbar-nav me-auto mb-2 mb-lg-0"><li class="nav-item"><a href="menu" class="navbar-brand">Menu</a></li></ul>
                      <ul class="navbar-nav me-auto mb-2 mb-lg-0"><li class="nav-item"><a href="item" class="navbar-brand">Inventory</a></li></ul>
                      <a href="user?action=logout" class="navbar-brand">Logout</a>
                    </div>
                </div>
            </nav>
        </div>

        <br>

        <div class="container">
            <div class="row">
                <div class="col">
                    
                    <table class="table">
                        <thead>
                            <tr>
                                <th>E-Mail</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Active</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td>${user.email}</td>
                                    <td>${user.firstName}</td>
                                    <td>${user.lastName}</td>
                                    <td>${user.active ? "Y" : "N"}</td>
                                    <td>
                                        <a href="<c:url value="manageUser">
                                            <c:param name="action" value="edit"></c:param>
                                            <c:param name="email" value="${user.email}"></c:param>
                                        </c:url>">Edit</a>
                                        <a href="<c:url value="manageUser">
                                            <c:param name="action" value="delete"></c:param>
                                            <c:param name="email" value="${user.email}"></c:param>
                                        </c:url>">Inactivate</a>
                                        
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <br>



        <!--c:set var = "showDiv" scope = "session" value = "${showEdit}"/-->
        <div class="container" <c:if test = "${ ( showEdit != 'yes') }"> hidden </c:if> > 
            <div class="row">
                <div class="col-4">
                    <h2>Edit User</h2>
                    
                    <form action="manageUser" method="post">
                        <input type="hidden" id="action" name="action" value="edition">
                        <p><label for="email" class="form-label">Email:</label><input id="email" name="email" type="text" value="${user.email}" readonly class="form-control" ></p>
                        <p><label for="firstName" class="form-label">First Name:</label><input id="firstName" name="firstName" type="text" value="${user.firstName}"  class="form-control" ></p>
                        <p><label for="lastName" class="form-label">Last Name:</label><input id="lastName" name="lastName" type="text" value="${user.lastName}"  class="form-control" ></p>
                        <p><label for="password" class="form-label">Password:</label><input id="password" name="password" type="text" value="${user.password}"  class="form-control" ></p>
                        <input type="hidden" id="roleId" name="roleId" value=${user.role.roleId}>
                        
                        

                        <button type="submit"  class="btn btn-outline-primary">Save</button>
                        <br>
                    </form>
                </div>
            </div>
        </div>        
        
    </body>
</html>

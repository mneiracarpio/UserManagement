<%-- 
    Document   : inventory
    Created on : Dec 4, 2022, 10:35:32 PM
    Author     : Marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <title>JSP Page</title>
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
                      <h2>Manage Inventory</h2>
                      <ul class="navbar-nav me-auto mb-2 mb-lg-0"><li class="nav-item"></li></ul>
                      <ul class="navbar-nav me-auto mb-2 mb-lg-0"><li class="nav-item"><h2>Welcome <%= session.getAttribute("username") %></h2> </li></ul>
                      <ul class="navbar-nav me-auto mb-2 mb-lg-0"><li class="nav-item"><a href="menu" class="navbar-brand">Menu</a></li></ul>
                      <a href="user?action=logout" class="navbar-brand">Logout</a>
                    </div>
                </div>
            </nav>
        </div>

        <br>
                      
        <div class="container">
            <div class="row">
                <div class="col">
                    
                    <button onclick="window.location.href='<c:url value="item">
                    <c:param name="action" value="add"></c:param></c:url>';"  class="btn btn-outline-primary">Create New Item</button>
                    
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Category</th>
                                <th>Actions</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${items}">
                                <tr>
                                    <td>${item.itemName}</td>
                                    <td>${item.price}</td>
                                    <td>${item.category.categoryName}</td>
                                    <td>
                                        <a href="<c:url value="item">
                                            <c:param name="action" value="edition"></c:param>
                                            <c:param name="itemId" value="${item.itemId}"></c:param>
                                        </c:url>">Edit</a>
                                        
                                    </td>
                                    <td>
                                        <form action="item" method="post">
                                            <input type="hidden" name="action" value="del">
                                                <input type="hidden" name="itemId" value="${item.itemId}">
                                            <input type="submit" value="Delete">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <c:if test="${message2 != null}">
                        <div class="alert alert-danger" role="alert">
                            ${message2}
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
        <br>
        
        
        <c:set var = "showAdd" scope = "session" value = "${showAdd}"/>
        <div class="container" <c:if test = "${ ( showAdd != 'add') }"> hidden </c:if>> 
            <div class="row">
                <div class="col-4">
                    <h2>Add Item</h2>
                    <form action="item" method="post">
                        
                        <input type="hidden" id="action" name="action" value="add">
                        <p><label for="name" class="form-label">Name:</label><input id="name" name="name" type="text" class="form-control"></p>
                        <p><label for="price" class="form-label">Price:</label><input id="price" name="price" type="text" class="form-control"></p>
                        <p><label for="category" class="form-label">Category:</label><select name="categoryId" id="categoryId" class="form-select" >
                            <c:forEach var="categoryList" items="${categories}">
                                <option value="${categoryList.categoryId}">${categoryList.categoryName}</option>
                            </c:forEach>
                            </select>
                        </p>

                        <button type="submit"  class="btn btn-outline-primary">Save</button>
                        <br>
                    </form>
                </div>
            </div>
        </div>
            
        
        <div class="container" <c:if test = "${ ( showEdit != 'edit') }"> hidden </c:if> > 
            <div class="row">
                <div class="col-4">
                    <h2>Edit Item</h2>
                    
                    <form action="item" method="post">
                        <input type="hidden" id="action" name="action" value="edition">
                        <input type="hidden" id="itemId" name="itemId" value="${item.itemId}">
                        <p><label for="itemName" class="form-label">Name:</label><input id="itemName" name="itemName" type="text" value="${item.itemName}" class="form-control" ></p>
                        <p><label for="price" class="form-label">Price:</label><input id="firstName" name="price" type="text" value="${item.price}"  class="form-control" ></p>
                        <p><label for="category" class="form-label">Category:</label><select name="categoryId" id="categoryId" class="form-select" >
                            <c:forEach var="categoryList" items="${categories}">
                                <c:set var = "id1" scope = "session" value = "${categoryList.categoryId}"/>
                                <c:set var = "id2" scope = "session" value = "${item.category.categoryId}"/>
                                <option value="${categoryList.categoryId}"  <c:if test = "${ (id1 == id2) }"> selected </c:if> >${categoryList.categoryName}</option>
                            </c:forEach>
                            </select>
                        </p>

                        <button type="submit"  class="btn btn-outline-primary">Save</button>
                        <br>
                    </form>
                </div>
            </div>
        </div>        
        
    </body>
</html>

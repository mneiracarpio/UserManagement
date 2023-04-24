
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <title>Manage Category Catalog</title>
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
                      <h2>Manage Category Catalog</h2>
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
                    <button onclick="window.location.href='<c:url value="category">
                    <c:param name="action" value="add"></c:param></c:url>';"  class="btn btn-outline-primary">Create New Category</button>
                    
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Category ID</th>
                                <th>Name</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="category" items="${categories}">
                                <tr>
                                    <td>${category.categoryId}</td>
                                    <td>${category.categoryName}</td>
                                    <td>
                                        <a href="<c:url value="category">
                                            <c:param name="action" value="edition"></c:param>
                                            <c:param name="categoryId" value="${category.categoryId}"></c:param>
                                        </c:url>">Edit</a>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <br>
        
        
        
        <div class="container" <c:if test = "${ ( showAdd != 'add') }"> hidden </c:if>> 
            <div class="row">
                <div class="col-4">
                    <h2>Add Category</h2>
                    <form action="category" method="post">
                        
                        <input type="hidden" id="action" name="action" value="add">
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Category Name</span>
                            <input class="form-control" type="text" name="categoryName" id="categoryName">
                            <button type="submit"  class="btn btn-outline-primary">Save</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
            
        
        <div class="container" <c:if test = "${ ( showEdit != 'edit') }"> hidden </c:if> > 
            <div class="row">
                <div class="col-4">
                    <h2>Edit Category</h2>
                    <form action="category" method="post">
                        <input type="hidden" id="action" name="action" value="edition">
                        <input type="hidden" id="categoryId" name="categoryId" value="${category.categoryId}">
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Category Name</span>
                            <input class="form-control" type="text" name="categoryName" id="categoryName" value="${category.categoryName}"><br>
                            <button type="submit"  class="btn btn-outline-primary">Save</button>
                        </div>
                        <br>
                    </form>
                </div>
            </div>
        </div>      
        <br>                
    </body>
</html>

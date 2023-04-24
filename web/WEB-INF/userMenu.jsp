<%-- 
    Document   : userMenu
    Created on : Nov 30, 2022, 10:42:36 AM
    Author     : Marco
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                      <h2>USER MENU</h2>
                      <ul class="navbar-nav me-auto mb-2 mb-lg-0"><li class="nav-item"></li></ul>
                      <ul class="navbar-nav me-auto mb-2 mb-lg-0"><li class="nav-item"><h2>Welcome <%= session.getAttribute("username") %> </h2></li></ul>
                      <a href="user?action=logout" class="navbar-brand">Logout</a>
                    </div>
                </div>
            </nav>
        </div>

        <br>

        <div class="container text-center">
          <div class="row align-items-center">
            <div class="col">
            </div>
            <div class="col">
              <div class="card">
                <svg class="bd-placeholder-img card-img-top" width="50%" height="100" role="img" aria-label="Placeholder" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#6495ED"/></svg>
                <div class="card-body">
                  <h5 class="card-title">Manage User Account</h5>
                  <p class="card-text">Users can edit their own account information</p>
                  <a href="manageUser" class="btn btn-primary">User Account</a></a>
                </div>
              </div>
            </div>
            <div class="col">
              <div class="card">
                <svg class="bd-placeholder-img card-img-top" width="50%" height="100" role="img" aria-label="Placeholder" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#6495ED"/></svg>
                <div class="card-body">
                  <h5 class="card-title">Manage Inventory</h5>
                  <p class="card-text">Users can view, add, delete and edit their own items</p>
                  <a href="item" class="btn btn-primary">Inventory</a>
                </div>
              </div>
            </div>
            <div class="col">
            </div>
          </div>
        </div>
        
        
    </body>
</html>

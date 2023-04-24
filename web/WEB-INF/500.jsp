<%-- 
    Document   : 500
    Created on : Dec 10, 2022, 12:24:44 PM
    Author     : Marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <title>Error 500</title>
    </head>
    <body>
        <br>
        <div class="container">
            <div class="alert alert-success" role="alert">
                <h4 class="alert-heading">Alert!</h4>
                <p>You are not authenticated yet. Please, <a href="login" class="alert-link">login</a> into the system.</p>
                <hr>
                <p class="mb-0">This page is forbidden for not authenicated users.</p>
            </div>
        </div>
    </body>
</html>

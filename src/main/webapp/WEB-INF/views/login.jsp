<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta "content-type=text/html">
        <link href="<c:url value='/static/css/bootstrap.min.css'/>" rel="stylesheet"></link>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
        <title>Login</title>
    </head>
    <body>
        <div class="container mt-4 mb-5 pt-5 pb-2">
            <nav class="navbar fixed-top navbar-expand navbar-light bg-light justify-content-center align-items-center">
                 <a class="navbar-brand" href="/cloneGag">
                    <span class="fa fa-microchip"></span>
                    CloneGag
                 </a>
            </nav>
            <div class="row">
            <div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3"></div>
                <div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6">
                    <p class="lead">Login you pleb. <a class="text-dark" href="/cloneGag/user/register">Or Register.</a></p>
                    <c:if test="${param.logout != null}">
                        <div class="text-center bg-success">
                            <p>You have been logged out successfully.</p>
                        </div>
                    </c:if>
                    <form method="post" action="login">
                        <div class="form-group">
                            <label for="username">Username:</label>
                            <input type="text" class="form-control" name="username" id="username" placeholder="Glorious username"/>
                            <small id="titleHelp" class="form-text text-muted">Surprise me.</small>
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" name="password" id="password" placeholder="Magnificent password"/>
                            <small id="passwordHelp" class="form-text text-muted">It better be good.</small>
                        </div>
                        <c:if test="${param.error != null}">
                            <div class="text-center text-danger">
                                <p>Invalid username and/or password.</p>
                            </div>
                        </c:if>
                        <input class="btn btn-primary btn-sm" type="submit" value="Login"/>
                        <a href="/cloneGag/post"><button type="button" class="btn btn-danger btn-sm">Cancel</button></a>
                    </form>
                </div>
                <div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3"></div>
            </div>
        </div>
    </body>
</html>

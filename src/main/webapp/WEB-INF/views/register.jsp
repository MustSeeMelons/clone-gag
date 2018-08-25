<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta "content-type=text/html">
        <link href="<c:url value='/static/css/bootstrap.min.css'/>" rel="stylesheet"></link>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
        <title>Register</title>
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
                    <p class="lead">Register you pleb.</p>
                    <c:if test="${errors}">
                        <c:forEach items="${errors}" var="e">
                            <span>e</span>
                        </c:forEach>
                    </c:if>
                    <form:form method="post" action="register" modelAttribute="user">
                        <div class="form-group">
                            <label for="username">Username:</label>
                            <input type="text" class="form-control" path="username" name="username" id="username" placeholder="Glorious username"/>
                            <small id="titleHelp" class="form-text text-muted">Surprise me.</small>
                            <div class="has-error text-danger">
                               <form:errors path="username" class="help-inline"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" path="password" name="password" id="password" placeholder="Magnificent password"/>
                            <small id="passwordHelp" class="form-text text-muted">It better be good.</small>
                            <div class="text-danger">
                                <form:errors path="password" class="help-inline"/>
                            </div>
                        </div>
                        <input class="btn btn-primary btn-sm" type="submit" value="Register"/>
                        <a href="/cloneGag/post"><button class="btn btn-danger btn-sm">Cancel</button></a>
                    </form:form>
                </div>
                <div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3"></div>
            </div>
        </div>
    </body>
</html>

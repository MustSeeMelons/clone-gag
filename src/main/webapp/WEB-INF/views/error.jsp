<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta "content-type=text/html">
        <link href="<c:url value='/static/css/bootstrap.min.css'/>" rel="stylesheet"></link>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
        <title>${pageTitle}</title>
    </head>
    <body>
        <div class="container mt-5 pt-5">
            <nav class="navbar fixed-top navbar-expand navbar-light bg-light justify-content-center align-items-center">
                 <a class="navbar-brand" href="/cloneGag/">
                    <span class="fa fa-microchip"></span>
                    CloneGag
                 </a>
            </nav>
            <div class="row">
                <div class="col">
                    <h1 class="text-center">${error}</h1>
                </div>
            </div>
        </div>
    </body>
</html>

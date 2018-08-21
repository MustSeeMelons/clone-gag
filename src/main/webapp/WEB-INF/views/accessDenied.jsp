<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta "content-type=text/html">
        <link href="<c:url value='/static/css/bootstrap.min.css'/>" rel="stylesheet"></link>
        <title>Denied</title>
    </head>
    <body>
        <div class="container">
            <div class="row mt-2 mb-2 pt-2 pb-2">
            <div class="col-4 col-sm-4 col-md-4 col-lg-4 col-xl-4"></div>
                <div class="col-4 col-sm-4 col-md-4 col-lg-4 col-xl-4">
                    <strong class="lead text-center">You are denied.</strong>
                    <p>${user}</p>
                </div>
                <div class="col-4 col-sm-4 col-md-4 col-lg-4 col-xl-4"></div>
            </div>
        </div>
    </body>
</html>

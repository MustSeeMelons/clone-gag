<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta "content-type=text/html">

        <title>${pageTitle}</title>
        <link href="<c:url value='/static/css/bootstrap.min.css'/>" rel="stylesheet"></link>
        <script src="<c:url value='/static/js/voteOnPost.js'/>"></script>
        <script src="<c:url value='/static/js/util.js'/>"></script>
        <script src="<c:url value='/static/js/redirectToPost.js'/>"></script>
        <script src="<c:url value='/static/js/postComment.js'/>"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
    </head>
    <body>
        <div class="container mt-3 mb-2 pt-5 pb-2">
            <nav class="navbar fixed-top navbar-expand navbar-light bg-light">
                 <a class="navbar-brand" href="/cloneGag/post">
                    <span class="fa fa-microchip"></span>
                    CloneGag
                 </a>
                 <div class="collapse navbar-collapse">
                     <div class="navbar-nav">
                        <a class="nav-item nav-link" href="/cloneGag/post">Home</a>
                        <a class="nav-item nav-link" href="/cloneGag/post/new">Upload</a>
                        <a class="nav-item nav-link" href="/cloneGag/post/list">My Funnies</a>
                        <a class="nav-item nav-link" href="/cloneGag/post/upvotes">My Upvotes</a>
                        <c:choose>
                            <c:when test="${isLoggedIn == true}">
                                <a class="nav-item nav-link navbar-text" href="/cloneGag/logout">Logout</a>
                            </c:when>
                            <c:otherwise>
                                <a class="nav-item nav-link navbar-text" href="/cloneGag/login">Login</a>
                                <a class="nav-item nav-link navbar-text" href="/cloneGag/user/register">Register</a>
                            </c:otherwise>
                        </c:choose>
                     </div>
                 </div>
            </nav>
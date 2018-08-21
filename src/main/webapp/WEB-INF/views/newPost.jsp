<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta "content-type=text/html">

        <title>Do a new Post</title>
        <link href="<c:url value='/static/css/bootstrap.min.css'/>" rel="stylesheet"></link>
    </head>
    <body>
        <div class="container">
            <div class="row mt-2 mb-2 pt-2 pb-2">
                <div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3"></div>
                <div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6">
                    <p class="lead">Impress someone.</p>
                    <form:form method="post" modelAttribute="post" action="new" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="title">Title:</label>
                            <form:input type="text" class="form-control" path="title" id="title" placeholder="Catchy post title"/>
                            <div class="has-error">
                                <form:errors path="title" class="help-inline"/>
                            </div>
                            <small id="titleHelp" class="form-text text-muted">Make your post noticed.</small>
                        </div>
                        <div class="form-group">
                            <label for="image">Image:</label>
                            <form:input type="file" class="form-control" path="image" id="image" placeholder="Funny meme"/>
                        </div>
                        <div className="form-group">
                            <img id="preview" class="img-fluid" src="<c:url value='/static/images/placeholder.jpg'/>" alt="preview" />
                        </div>
                        <div class="form-group">
                            <label for="tags">Tags:</label>
                            <form:input type="text" class="form-control" path="tags" id="tags" placeholder="Useless tags"/>
                        </div>
                        <input class="btn btn-primary btn-sm" type="submit" value="Upload"/>
                        <a href="<c:url value='/'/>"><button class="btn btn-danger btn-sm">Cancel</button></a>
                    </form:form>
                </div>
                <div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3"></div>
            </div>
        </div>
        <script src="<c:url value='/static/js/bootstrap.bundle.min.js'/>"></script>
    </body>
</html>
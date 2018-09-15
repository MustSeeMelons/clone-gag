<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />
<div class="row">
    <div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3"></div>
    <div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6">
        <p class="lead">Impress someone.</p>
        <form:form method="post" modelAttribute="post" action="new" enctype="multipart/form-data">
            <div class="form-group">
                <label for="title">Title:</label>
                <form:input type="text" class="form-control" path="title" id="title" placeholder="Catchy post title"/>
                <div class="has-error">
                    <form:errors path="title" class="help-inline text-danger"/>
                </div>
                <small id="titleHelp" class="form-text text-muted">Make your post noticed.</small>
            </div>
            <div class="form-group">
                <label for="image">Image:</label>
                <form:input type="file" class="form-control" path="image" id="image" placeholder="Funny meme"/>
                <div class="has-error">
                    <form:errors path="image" class="help-inline text-danger"/>
                </div>
            </div>
            <div className="form-group">
                <img id="preview" class="img-fluid" src="<c:url value='/static/images/placeholder.jpg'/>" alt="preview" />
            </div>
            <div class="form-group">
                <label for="tags">Tags:</label>
                <form:input type="text" class="form-control" path="tags" id="tags" placeholder="tag one, tag two, tag your it"/>
                <div class="has-error">
                    <form:errors path="tags" class="help-inline text-danger"/>
                </div>
            </div>
            <input class="btn btn-primary btn-sm" type="submit" value="Upload"/>
            <a href="/cloneGag/post"><button type="button" class="btn btn-danger btn-sm">Cancel</button></a>
        </form:form>
    </div>
    <div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3"></div>
</div>
<jsp:include page="footer.jsp" />
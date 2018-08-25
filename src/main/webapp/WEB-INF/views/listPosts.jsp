<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />
    <div class="row">
        <div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3"></div>
            <div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6">
                <div>
                    <c:forEach items="${posts}" var="post">
                        <div class="card mx-auto m-5">
                            <div class="card-header">
                                ${post.getTitle()}
                            </div>
                            <div class="card-body">
                                <img class="img-fluid" src="${post.getBase64EncodedImage()}"/>
                            </div>
                             <div class="card-footer text-muted">
                                ${post.getTags()}
                             </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        <div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3"></div>
    </div>
<jsp:include page="footer.jsp" />

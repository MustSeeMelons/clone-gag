<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />
    <div class="row mt-1 mb-1 pt-1 pb-1">
        <div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3"></div>
        <div class="col-6 col-sm-6 col-md-6 col-lg-6 col-xl-6">
            <div class="card">
                <div class="card-header">
                    ${title}
                </div>
                <div class="card-body">
                    <img class="img-fluid" src="${image}"/>
                </div>
                 <div class="card-footer text-muted">
                    ${tags}
                 </div>
            </div>
        </div>
        <div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3"></div>
    </div>
<jsp:include page="footer.jsp" />


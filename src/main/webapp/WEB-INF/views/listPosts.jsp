<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />
    <div class="row">
        <div class="col-2 col-sm-2 col-md-3 col-lg-3 col-xl-3"></div>
            <div class="col-8 col-sm-8 col-md-6 col-lg-6 col-xl-6">
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
                                <div class="row">
                                    <div class="col text-left">
                                        <button id="up-${post.getId()}" type="button" class="btn btn-default">
                                            <span class="fa fa-angle-up"></span>
                                        </button>
                                        <span id="point-${post.getId()}">${post.getPoints()}</span>
                                        <button id="down-${post.getId()}" type="button" class="btn btn-default">
                                            <span class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                    <div class="col text-right">
                                        ${post.getTags()}
                                    </div>
                                </div>
                             </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        <div class="col-2 col-sm-2 col-md-3 col-lg-3 col-xl-3"></div>
    </div>
    <script>
        let ups = document.querySelectorAll("button[id^=up-]");
        let downs = document.querySelectorAll("button[id^=down-]");

        for(let i=0;i<ups.length;i++){
            ups[i].addEventListener("click", () => {
                let index = ups[i].id.indexOf("-");
                let postId = ups[i].id.substring(index + 1);
                vote(postId, 1);
            });
        }

        for(let i=0;i<downs.length;i++){
            downs[i].addEventListener("click", () => {
                let index = downs[i].id.indexOf("-");
                let postId = downs[i].id.substring(index + 1);
                vote(postId, -1);
            });
        }
    </script>
<jsp:include page="footer.jsp" />

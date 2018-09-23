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
            <hr class="w-75"/>
            <c:if test="${isLoggedIn == true}">
                <form>
                    <label for="cmnt">Priceless comment:</label>
                    <div class="input-group">
                        <textarea id="cmnt" class="form-control" placeholder="Start writing here.."></textarea>
                    </div>
                    <div class="row">
                        <div class="col text-right">
                            <button id="post" type="button" class="btn btn-primary m-2">Post</button>
                        </div>
                    </div>
                </form>
                <hr class="w-75"/>
            </c:if>

            <div class="container" id="comments">
            </div>
        </div>
        <script>
            const postId = ${postId};
            let page = 0;
            let polling = false;

            let fetchReplies = () => {
                let page = 0;

                return () => {
                    page++;
                    console.log(page);
                }
            };

            let post = document.getElementById("post");

            if(post !== null){
                post.addEventListener("click", () => {
                    const cVal = document.getElementById("cmnt").value;

                    postComment({
                        postId: postId,
                        comment: cVal
                    }, () => {
                        const url = "${app.host}/post/view/" + postId;
                        window.open(url, "_self");
                    });
                });
            }

            function getComments(){
                polling = true;
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        let comments = JSON.parse(this.responseText);
                        if(comments.length !== 0) {
                            const main = document.getElementById("comments");
                            comments.forEach((comment) => {
                                const node = document.getElementById("comment-node").cloneNode(true);
                                node.id = "";
                                node.style.display = "block";
                                node.querySelector("#username").textContent  = comment.username;
                                node.querySelector("#points").textContent = comment.points + " points";
                                node.querySelector("#time").textContent  = comment.time;
                                node.querySelector("#cmnt").textContent  = comment.comment;

                                node.querySelector("#reply").addEventListener("click", (e) => {
                                    e.preventDefault();
                                    const replyForm = node.querySelector("#reply-form");
                                    if(replyForm.style.display === "block"){
                                        replyForm.style.display = "none";
                                    } else {
                                        replyForm.style.display = "block";
                                    }
                                });

                                if(comment.hasReplies) {
                                    node.querySelector("#view-replies").style.display = "inline";

                                    fetchReplies = () => {
                                        let page = 0;

                                        return () => {
                                            page++;
                                            console.log(page);
                                        }
                                    }

                                    node.querySelector("#view-replies").addEventListener("click", (e) => {
                                        e.preventDefault();

                                        var xhttp = new XMLHttpRequest();
                                        xhttp.open("GET", "${app.host}/${app.rest.post}/comment/replies/" + comment.id + "/" + 0, true);
                                        xhttp.send();
                                   });
                                }

                                node.querySelector("#post").addEventListener("click", () => {
                                    const cVal = node.querySelector("#reply-cmnt").value;

                                    postComment({
                                        postId: postId,
                                        commentId: comment.id,
                                        comment: cVal
                                    }, () => {
                                        const url = "${app.host}/post/view/" + postId;
                                        window.open(url, "_self");
                                    });
                               });

                                main.appendChild(node);
                            });
                            polling = false;
                            page++;
                        } else {
                           polling = true;
                        }
                    }
                };
                xhttp.open("GET", "${app.host}/${app.rest.post}/comment/get/" + postId + "/" + page, true);
                xhttp.send();
            }

            window.onscroll = () => {
                if(!polling && getDistFromBottom() < 100){
                   getComments();
                }
            };

            getComments();

            // expend to get sub comments, with extra to get more sub comments
        </script>
        <div class="col-3 col-sm-3 col-md-3 col-lg-3 col-xl-3">
            <jsp:include page="components/comment.jsp" />
        </div>
    </div>
<jsp:include page="footer.jsp" />


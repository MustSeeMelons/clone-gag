<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />
    <div class="row">
        <div class="col-2 col-sm-2 col-md-3 col-lg-3 col-xl-3"></div>
        <div id="main" class="col-8 col-sm-8 col-md-6 col-lg-6 col-xl-6">
        </div>
        <div class="col-2 col-sm-2 col-md-3 col-lg-3 col-xl-3"></div>
    </div>
    <script>
        let page = 0;
        let polling = false;
        const loadPosts = () => {
            polling = true;
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    if(JSON.parse(this.responseText).length !== 0){
                        page++;
                        polling = false;
                        const main = document.getElementById("main");

                        let posts;
                        try {
                            posts = JSON.parse(this.responseText);
                        } catch {
                            polling = true;
                            return;
                        }

                        posts.forEach((post) => {
                            const node = document.getElementById("post-node").cloneNode(true);
                            node.id = "";
                            node.style.display = "block";
                            node.querySelector("#title").textContent  = post.title;
                            node.querySelector("#image").src = post.image;
                            node.querySelector("#tags").textContent  = post.tags;
                            node.querySelector("#point").textContent  = post.points;

                            node.querySelector("#point").id += "-" + post.id;

                            node.querySelector("#up").addEventListener("click", () => {
                                vote(post.id, 1);
                            });

                            node.querySelector("#down").addEventListener("click", () => {
                                vote(post.id, -1);
                            });

                            main.appendChild(node);
                        });
                    } else {
                        // Stop fetching once we don't get data
                        polling = true;
                    }
                }
            };

            xhttp.open("GET", "${app.host}/post/votes/" + ${userId} + "/" + page, true);
            xhttp.send();
        };

        let getDistFromBottom = () => {
          var scrollPosition = window.pageYOffset;
          var windowSize     = window.innerHeight;
          var bodyHeight     = document.body.offsetHeight;

          return Math.max(bodyHeight - (scrollPosition + windowSize), 0);
        }

        window.onscroll = () => {
            if(!polling && getDistFromBottom() < 200){
                loadPosts();
            }
        };

        loadPosts();
    </script>
    <div id="post-node" class="card mx-auto m-5" style="display: none">
        <div id="title" class="card-header">

        </div>
        <div class="card-body">
            <img id="image" class="img-fluid"/>
        </div>
         <div class="card-footer text-muted">
            <div class="row">
                <div class="col text-left">
                    <button id="up" type="button" class="btn btn-default">
                        <span class="fa fa-angle-up"></span>
                    </button>
                    <span id="point"></span>
                    <button id="down" type="button" class="btn btn-default">
                        <span class="fa fa-angle-down"></span>
                    </button>
                </div>
                <div id="tags" class="col text-right">

                </div>
            </div>
         </div>
    </div>
<jsp:include page="footer.jsp" />
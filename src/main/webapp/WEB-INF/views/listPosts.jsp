<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />
    <div class="row">
        <div class="col-2 col-sm-2 col-md-3 col-lg-3 col-xl-3"></div>
            <div class="col-8 col-sm-8 col-md-6 col-lg-6 col-xl-6">
                <nav id="top-nav">
                </nav>
                <div id="main">
                </div>
                <nav id="nav">
                    <ul id="page-nav" class="pagination justify-content-center">

                    </ul>
                </nav>
            </div>
        <div class="col-2 col-sm-2 col-md-3 col-lg-3 col-xl-3"></div>
    </div>
    <script>
        let page = 0;
        let polling = false;
        const loadPosts = () => {
            const userId = ${userId};
            polling = true;
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    if(JSON.parse(this.responseText).posts.length !== 0){
                        page++;
                        polling = false;
                        const main = document.getElementById("main");

                        const response = JSON.parse(this.responseText);

                        let posts;
                        try {
                            posts = response.posts;
                        } catch {
                            polling = true;
                            return;
                        }

                        while (main.lastChild) {
                            main.removeChild(main.lastChild);
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

                        if(posts.length > 0) {
                            let pageNav = document.getElementById("page-nav");
                            while (pageNav.lastChild) {
                                pageNav.removeChild(pageNav.lastChild);
                            }
                            for(let i = 0; i < response.pageCount; i++) {
                                const node = document.getElementById("page-node").cloneNode(true);
                                node.id = "";
                                node.style.display = "flex";
                                node.querySelector("#page-link").textContent = i + 1;
                                if(response.currentPage == i) {
                                    node.classList.add("active");
                                } else {
                                    node.addEventListener("click", () => {
                                        page = i;
                                        loadPosts();
                                    });
                                }
                                pageNav.appendChild(node);
                            }

                            document.getElementById("top-nav").appendChild(pageNav);
                        }
                        window.scrollTo(0,0)
                    } else {
                        polling = true;
                    }
                }
            };

            xhttp.open("GET", "${app.host}/${app.rest.post}/${userId}/" + page, true);
            xhttp.send();
        };

        loadPosts();
    </script>
    <li id="page-node" class="page-item" style="display: none"><a id="page-link" class="page-link"></a></li>
<jsp:include page="components/post.jsp" />
<jsp:include page="footer.jsp" />

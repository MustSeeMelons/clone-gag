function redirectToPost(postId) {
    const url = "${app.host}/post/view/" + postId;
    window.open(url, "_blank");
}
function vote(postId, point) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            if(!JSON.parse(this.responseText).code){
                document.getElementById("point-" + postId).innerHTML = JSON.parse(this.responseText).votes;
            }
        }
    };

    xhttp.open("GET", "${app.host}/post/vote/" + postId + "/" + point, true);
    xhttp.send();
}
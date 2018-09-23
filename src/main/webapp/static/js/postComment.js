function postComment(body, sCallback){
    const url = "${app.host}/${app.rest.post}/comment/add";

    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            sCallback();
        }
    };

    xhttp.open("POST", url, true);
    xhttp.setRequestHeader('Content-type','application/json; charset=utf-8');
    xhttp.send(JSON.stringify(body));
}
<div id="comment-node" class="mt-1" style="display: none;">
    <div class="row">
        <div class="col text-left mr-1">
            <small id="username"></small>
            <small id="points"></small>
            <small id="time"></small>
        </div>
    </div>
    <div class="row">
        <p id="cmnt" class="col"></p>
    </div>
     <button type="button" class="btn btn-sm" id="up">
        <span class="fa fa-angle-up"></span>
     </button>
     <button type="button" class="btn btn-sm" id="down">
        <span class="fa fa-angle-down"></span>
     </button>
     <a id="reply" href="" class="text-dark text-muted">Reply</a>

     <a id="view-replies" href="" class="text-dark text-muted" style="display: none;">View replies</a>

     <form id="reply-form" class="mt-2" style="display: none;">
         <div class="input-group">
             <textarea id="reply-cmnt" class="form-control" placeholder="Your savage reply.."></textarea>
         </div>
         <div class="row">
             <div class="col text-right">
                 <button id="post" type="button" class="btn btn-primary btn-small m-2">Post</button>
             </div>
         </div>
     </form>
</div>
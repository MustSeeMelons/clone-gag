package com.strautins.CloneGag.pojo;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

public class AddCommentRequest {
    private BigInteger postId;
    private BigInteger commentId;
    @NotNull
    private String comment;

    public AddCommentRequest() {
    }

    public AddCommentRequest(BigInteger postId, BigInteger commentId, String comment) {
        this.postId = postId;
        this.commentId = commentId;
        this.comment = comment;
    }

    public BigInteger getPostId() {
        return postId;
    }

    public void setPostId(BigInteger postId) {
        this.postId = postId;
    }

    public BigInteger getCommentId() {
        return commentId;
    }

    public void setCommentId(BigInteger commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

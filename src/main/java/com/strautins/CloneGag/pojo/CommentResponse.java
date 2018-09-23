package com.strautins.CloneGag.pojo;

import com.strautins.CloneGag.model.hql.CommentData;

import java.math.BigInteger;

public class CommentResponse {
    private BigInteger id;
    private String username;
    private BigInteger points;
    private String time;
    private String comment;
    private boolean hasReplies;

    public CommentResponse(CommentData commentData) {
        this.id = commentData.getId();
        this.username = commentData.getUsername();
        this.points = commentData.getPoints();
        // TODO process time value
        // this.time = commentData.getCreateDate();
        this.comment = commentData.getValue();
        this.hasReplies = commentData.isHasReplies();
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigInteger getPoints() {
        return points;
    }

    public void setPoints(BigInteger points) {
        this.points = points;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isHasReplies() {
        return hasReplies;
    }

    public void setHasReplies(boolean hasReplies) {
        this.hasReplies = hasReplies;
    }
}

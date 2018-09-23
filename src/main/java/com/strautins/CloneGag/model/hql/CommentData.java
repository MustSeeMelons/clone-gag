package com.strautins.CloneGag.model.hql;

import java.math.BigInteger;
import java.util.Date;

public class CommentData {
    private BigInteger id;
    private BigInteger owner;
    private String username;
    private BigInteger points;
    private Date createDate;
    private String value;
    private boolean hasReplies;

    public CommentData() {
    }

    public CommentData(BigInteger id,
                       BigInteger owner,
                       String username,
                       BigInteger points,
                       Date createDate,
                       String value) {
        this.id = id;
        this.owner = owner;
        this.username = username;
        this.points = points;
        this.createDate = createDate;
        this.value = value;
    }

    public CommentData(BigInteger id,
                       BigInteger owner,
                       String username,
                       BigInteger points,
                       Date createDate,
                       String value,
                       Long replies) {
        this.id = id;
        this.owner = owner;
        this.username = username;
        this.points = points;
        this.createDate = createDate;
        this.value = value;
        this.hasReplies = replies > 0L;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getOwner() {
        return owner;
    }

    public void setOwner(BigInteger owner) {
        this.owner = owner;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isHasReplies() {
        return hasReplies;
    }

    public void setHasReplies(boolean hasReplies) {
        this.hasReplies = hasReplies;
    }
}

package com.strautins.CloneGag.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "COMMENTS", schema = "gag")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(nullable = false)
    private BigInteger owner;

    @Column(name = "post_id", nullable = false)
    private BigInteger postId;

    @Column(name = "parent_comment_id")
    private BigInteger parentCommentId;

    @Column(nullable = false)
    private String value;

    private BigInteger points = BigInteger.ZERO;

    @Column(name = "c_date", nullable = false)
    private Date createDate;

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

    public BigInteger getPostId() {
        return postId;
    }

    public void setPostId(BigInteger postId) {
        this.postId = postId;
    }

    public BigInteger getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(BigInteger parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
}

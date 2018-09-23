package com.strautins.CloneGag.model;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "COMMENT_VOTES", schema = "gag")
public class CommentVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(nullable = false)
    private BigInteger owner;

    @Column(name = "comment_id", nullable = false)
    private BigInteger commentId;

    @Column(nullable = false)
    private Integer point;

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

    public BigInteger getCommentId() {
        return commentId;
    }

    public void setCommentId(BigInteger commentId) {
        this.commentId = commentId;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}

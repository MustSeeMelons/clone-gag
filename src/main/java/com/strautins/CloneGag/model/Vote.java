package com.strautins.CloneGag.model;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "VOTES", schema = "gag")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(nullable = false)
    private BigInteger owner;

    @Column(name = "post_id", nullable = false)
    private BigInteger postId;

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

    public BigInteger getPostId() {
        return postId;
    }

    public void setPostId(BigInteger postId) {
        this.postId = postId;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}

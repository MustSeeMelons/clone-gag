package com.strautins.CloneGag.pojo;

import com.strautins.CloneGag.model.Post;

import java.math.BigInteger;

public class PostResponse {
    private BigInteger id;
    private BigInteger owner;
    private String title;
    private String image;
    private BigInteger points;
    private String tags;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.owner = post.getOwner();
        this.title = post.getTitle();
        this.image = post.getBase64EncodedImage();
        this.points = post.getPoints();
        this.tags = post.getTags();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigInteger getPoints() {
        return points;
    }

    public void setPoints(BigInteger points) {
        this.points = points;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}

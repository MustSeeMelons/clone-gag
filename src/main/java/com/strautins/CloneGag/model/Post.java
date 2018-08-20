package com.strautins.CloneGag.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "POSTS", schema = "gag")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(nullable = false)
    private BigInteger owner;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private byte[] image;

    private String tags;

    @Column(name = "c_date", nullable = false)
    private Date createDate;

    @Column(name = "m_date")
    private Date modifiedDate;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", owner=" + owner +
                ", title='" + title + '\'' +
                ", tags='" + tags + '\'' +
                ", createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}

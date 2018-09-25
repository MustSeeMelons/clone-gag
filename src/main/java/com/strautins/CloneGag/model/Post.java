package com.strautins.CloneGag.model;

import com.strautins.CloneGag.model.validators.ValidImageConstraint;
import com.strautins.CloneGag.model.validators.ValidTagConstraint;
import com.strautins.CloneGag.utils.ImageUtils;
import org.apache.commons.lang.ArrayUtils;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Base64;
import java.util.Date;

@Entity
@Table(name = "POSTS", schema = "gag")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(nullable = false)
    private BigInteger owner;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String title;

    @ValidImageConstraint
    @Column(nullable = false)
    private Byte[] image;

    @ValidTagConstraint
    private String tags;

    @Column(name = "c_date", nullable = false)
    private Date createDate;

    @Column(name = "m_date")
    private Date modifiedDate;

    private BigInteger points = BigInteger.ZERO;

    public void modifyPoints(Integer value) {
        points = points.add(BigInteger.valueOf(value));
    }

    public String getBase64EncodedImage() {
        if (image != null && image.length > 0) {
            String mime = ImageUtils.getSupportedMime(ArrayUtils.toPrimitive(image));
            return "data:" + mime + ";base64, " + Base64.getEncoder().encodeToString(ArrayUtils.toPrimitive(image));
        }
        return null;
    }

    public BigInteger getPoints() {
        return points;
    }

    public void setPoints(BigInteger points) {
        this.points = points;
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

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
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

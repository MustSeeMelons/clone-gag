package com.strautins.CloneGag.pojo;

import com.strautins.CloneGag.model.Post;

import java.util.List;
import java.util.stream.Collectors;

public class PostPage {
    private List<PostResponse> posts;
    private Long currentPage;
    private Long pageCount;

    public PostPage(List<Post> posts, Long currentPage, Long pageCount) {
        this.posts = posts.stream().map(
                (Post post) -> new PostResponse(post)
        ).collect(Collectors.toList());
        this.currentPage = currentPage;
        this.pageCount = pageCount;
    }

    public List<PostResponse> getPosts() {
        return posts;
    }

    public void setPosts(List<PostResponse> posts) {
        this.posts = posts;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }
}

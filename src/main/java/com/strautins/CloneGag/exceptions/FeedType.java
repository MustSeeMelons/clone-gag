package com.strautins.CloneGag.exceptions;

public enum FeedType {
    FRESH("FRESH"),
    TRENDING("TRENDING"),
    HOT("HOT");

    private String feedType;

    FeedType(String feedType) {
        this.feedType = feedType;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }
}

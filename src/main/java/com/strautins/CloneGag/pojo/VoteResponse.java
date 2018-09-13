package com.strautins.CloneGag.pojo;

import java.math.BigInteger;

public class VoteResponse {
    private BigInteger id;
    private BigInteger votes;

    public VoteResponse(BigInteger id, BigInteger votes) {
        this.id = id;
        this.votes = votes;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getVotes() {
        return votes;
    }

    public void setVotes(BigInteger votes) {
        this.votes = votes;
    }
}

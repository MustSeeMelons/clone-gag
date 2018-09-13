package com.strautins.CloneGag.service;

import com.strautins.CloneGag.exceptions.RestException;
import com.strautins.CloneGag.pojo.VoteResponse;

import java.math.BigInteger;

public interface VoteService {
    VoteResponse vote(BigInteger postId, Integer point) throws RestException;
}

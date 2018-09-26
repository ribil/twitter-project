package com.gmail.ribil39.service;

import com.gmail.ribil39.domain.ReplyPool;
import com.gmail.ribil39.repository.ReplyPoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyPoolService {

    @Autowired
    ReplyPoolRepository replyPoolRepository;

    public ReplyPool findReplyPoolById(Integer id){
        ReplyPool replyPool = replyPoolRepository.findById(id);
        return replyPool;
    }

    public void saveReplyPool(ReplyPool replyPool){
        replyPoolRepository.save(replyPool);
    }
}

package com.gmail.ribil39.repository;

import com.gmail.ribil39.domain.ReplyPool;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ReplyPoolRepository extends CrudRepository<ReplyPool, Long> {

    ReplyPool findById(Integer id);

    @Transactional
    void deleteById(Integer id);


}



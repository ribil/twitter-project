package com.gmail.ribil39.repos;

import com.gmail.ribil39.domain.Message;
import com.gmail.ribil39.domain.ReplyPool;
import com.gmail.ribil39.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Set;

public interface ReplyPoolRepo extends CrudRepository<ReplyPool, Long> {

    ReplyPool findById(Integer id);

    @Transactional
    void deleteById(Integer id);


}



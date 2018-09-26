package com.gmail.ribil39.repository;

import com.gmail.ribil39.domain.Message;
import com.gmail.ribil39.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Set;

public interface MessageRepository extends CrudRepository<Message, Long> {

    Message findById(Integer id);

    ArrayList<Message> findByAuthor(User user);

    @Query("SELECT m FROM Message m JOIN FETCH m.retweets r WHERE r.id = ?1")
    Set<Message> findByCondition(Integer id);

    Page<Message> findAll(Pageable pageable);

    @Transactional
    void deleteById(Integer id);


}

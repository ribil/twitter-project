package com.gmail.ribil39.repos;

import com.gmail.ribil39.domain.Message;
import com.gmail.ribil39.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Set;

public interface MessageRepo extends CrudRepository<Message, Long> {

    Message findById(Integer id);

    ArrayList<Message> findByAuthor(User user);

    //@Query(value = "SELECT m FROM message m WHERE m.id = 6", nativeQuery = true)
    @Query("SELECT m FROM Message m JOIN FETCH m.retweets r WHERE r.id = ?1")
    Set<Message> findByCondition(Integer id);

    Page<Message> findAll(Pageable var1);

    @Transactional
    void deleteById(Integer id);


}

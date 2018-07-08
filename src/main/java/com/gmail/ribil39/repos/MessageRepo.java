package com.gmail.ribil39.repos;

import com.gmail.ribil39.domain.Message;
import com.gmail.ribil39.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface MessageRepo extends CrudRepository<Message, Long> {

    Message findById(Integer id);

    ArrayList<Message> findByAuthor(User user);

    Page<Message> findAll(Pageable var1);

    @Transactional
    void deleteById(Integer id);


}

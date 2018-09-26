package com.gmail.ribil39.service;

import com.gmail.ribil39.domain.Message;
import com.gmail.ribil39.repository.MessageRepository;
import com.gmail.ribil39.repository.ReplyPoolRepository;
import com.gmail.ribil39.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReplyPoolRepository replyPoolRepository;
    @Autowired
    UserSevice userSevice;

    public Page<Message> findAllTweetsPage(Pageable pageable) {

        Page<Message> messagePage = messageRepository.findAll(pageable);

        return messagePage;
    }

    public Iterable<Message> findAllTweets() {

        Iterable<Message> messages = messageRepository.findAll();

        return messages;

    }

    public Message findTweetById(Integer id) {

        Message message = messageRepository.findById(id);

        return message;
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    public void saveMessage(Message message){
        messageRepository.save(message);
    }

    public Iterable<Message> findTweetsByAuthor(Integer id) {

        Iterable<Message> messages = messageRepository.findByAuthor(userRepository.findById(id));

        return messages;
    }

}

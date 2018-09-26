package com.gmail.ribil39.controller;

import com.gmail.ribil39.domain.Message;
import com.gmail.ribil39.domain.ReplyPool;
import com.gmail.ribil39.domain.User;
import com.gmail.ribil39.repository.MessageRepository;
import com.gmail.ribil39.repository.ReplyPoolRepository;
import com.gmail.ribil39.repository.UserRepository;
import com.gmail.ribil39.service.MessageService;
import com.gmail.ribil39.service.ReplyPoolService;
import com.gmail.ribil39.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class MainController {

    @Autowired
    MessageService messageService;
    @Autowired
    UserSevice userSevice;
    @Autowired
    ReplyPoolService replyPoolService;


    @GetMapping("/")
    public String main(
            Map<String, Object> model,
            @AuthenticationPrincipal User user,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Message> messagePage = messageService.findAllTweetsPage(pageable);

        model.put("page", messagePage);
        model.put("url", "/");

        model.put("user", user);
        ArrayList userss = new ArrayList();
        userss.add(user);
        model.put("userss", userss);

        return "main";
    }

    @GetMapping("/retweet/{id}")
    public String retweet(@PathVariable("id") Integer id,
                          Map<String, Object> model,
                          @AuthenticationPrincipal User user) {
        Set<Message> retweets = user.getMessages();
        retweets.add(messageService.findTweetById(id));
        user.setMessages(retweets);
        userSevice.saveUser(user);
        return "redirect:/";
    }

    @PostMapping("/addmessage")
    public String add(@AuthenticationPrincipal User user, @RequestParam String text,
                      Map<String, Object> model) {

        if (text.length() > 250) {
            model.put("warning", "<div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">\n" +
                    "                <strong>Количество символов превышает 250!</strong>\n" +
                    "                <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                    "                    <span aria-hidden=\"true\">&times;</span>\n" +
                    "                </button>\n" +
                    "            </div>");
            Iterable<Message> messages = messageService.findAllTweets();
            model.put("messages", messages);
            return "main";
        }

        Date date = new Date();
        Message message = new Message(text, date, user);
        messageService.saveMessage(message);
        return "redirect:/";
    }

    @GetMapping("/user/{id}")
    public String userMessages(Map<String, Object> model,
                               @AuthenticationPrincipal User user,
                               @PathVariable("id") Integer id) {
        Iterable<Message> tweets = messageService.findTweetsByAuthor(id);
        model.put("tweets", tweets);

        Set<Message> retweets = user.getMessages();

        model.put("retweets", retweets);

        return "mymessages";
    }

    @GetMapping("/user/messageedit/{id}")
    public String messageEditPage(@AuthenticationPrincipal User user,
                                  Map<String, Object> model,
                                  @PathVariable("id") Integer id) {
        model.put("user", user);

        Message tweet = messageService.findTweetById(id);

        model.put("tweet", tweet);


        return "messageedit";
    }

    @PostMapping("editMessage")
    public String editMessage(@AuthenticationPrincipal User user,
                              Map<String, Object> model,
                              @ModelAttribute("tweet") Message tweet) {
        model.put("user", user);

        tweet.setAuthor(user);
        tweet.setDate(new Date());
        messageService.saveMessage(tweet);
        return "redirect:/user/" + user.getId();
    }

    @GetMapping("/message/{id}")
    public String messagePage(Map<String, Object> model,
                              @AuthenticationPrincipal User user,
                              @PathVariable("id") Integer id) {
        Iterable<Message> tweets = messageService.findTweetsByAuthor(id);
        model.put("tweets", tweets);

        Message currentMessage = messageService.findTweetById(id);
        model.put("currentMessage", currentMessage);

        if (messageService.findTweetById(id).getReply() != null) {
            Set<Message> replies;
            replies = messageService.findTweetById(id).getReply().getReplies();

            model.put("replies", replies);
        }

        return "message";
    }


    @PostMapping("/addreply")
    public String addReply(@AuthenticationPrincipal User user, @RequestParam String text,
                           @RequestParam Integer currentMessageId,
                           Map<String, Object> model) {

        Date date = new Date();
        Message message = new Message(text, date, user);

        ReplyPool replyPool;
        if (replyPoolService.findReplyPoolById(currentMessageId) != null) {
            replyPool = replyPoolService.findReplyPoolById(currentMessageId);
        } else {
            replyPool = new ReplyPool();
            replyPool.setId(currentMessageId);
        }
        message.setReplyPool(replyPool);
        replyPoolService.saveReplyPool(replyPool);
        messageService.saveMessage(message);

        return "redirect:/";
    }

    @GetMapping("/profile")
    public String editNickPage(@AuthenticationPrincipal User user,
                               Map<String, Object> model) {
        model.put("user", user);

        return "profile";
    }

    @PostMapping("editNick")
    public String editNick(@AuthenticationPrincipal User user,
                           Map<String, Object> model,
                           @RequestParam String nick,
                           @ModelAttribute("user") User u) {


        String firstLetter = String.valueOf(nick.charAt(0));


        if (MessageService.isNumeric(firstLetter)) {

            model.put("warning", "<div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">\n" +
                    "                <strong>Первый символ не должен быть цифрой!</strong>\n" +
                    "                <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                    "                    <span aria-hidden=\"true\">&times;</span>\n" +
                    "                </button>\n" +
                    "            </div>");
            model.put("user", user);
            return "profile";
        }

        if (!nick.matches("^[A-Za-z0-9]+$")) {

            model.put("warning", "<div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">\n" +
                    "                <strong>Ник должен состоять только из латинских букв!</strong>\n" +
                    "                <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                    "                    <span aria-hidden=\"true\">&times;</span>\n" +
                    "                </button>\n" +
                    "            </div>");
            model.put("user", user);
            return "profile";
        }

        user.setName(nick);
        userSevice.saveUser(user);
        model.put("user", user);

        return "profile";
    }


}

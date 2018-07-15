package com.gmail.ribil39.controller;

import com.gmail.ribil39.domain.Message;
import com.gmail.ribil39.domain.ReplyPool;
import com.gmail.ribil39.domain.User;
import com.gmail.ribil39.repos.MessageRepo;
import com.gmail.ribil39.repos.ReplyPoolRepo;
import com.gmail.ribil39.repos.UserRepo;
import com.gmail.ribil39.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class MainController {

    @Autowired
    MessageRepo messageRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ReplyPoolRepo replyPoolRepo;


    @GetMapping("/")
    public String main(Map<String, Object> model, @AuthenticationPrincipal User user) {


        // Портянка для пагинации
        Page<Message> messagePage = messageRepo
                .findAll(new PageRequest(
                        0, 10, Sort.Direction.ASC, "id"));
        List<Message> tweets = messagePage.getContent();

        model.put("tweets", tweets);

        model.put("user", user);
        ArrayList userss = new ArrayList();
        userss.add(user);
        model.put("userss", userss);

        int pagesNumber = messagePage.getTotalPages();
        model.put("totalNumber", pagesNumber);
        ArrayList pagesList = new ArrayList();
        for (int i = 0; i < pagesNumber; i++) {
            pagesList.add(i + 1);

        }
        model.put("pagesList", pagesList);

        ArrayList pag = new ArrayList();
        pag.add(1);
        model.put("pag", pag);


        return "main";
    }

    @GetMapping("/page")
    public String getMessagePage(Map<String, Object> model,
                                 @RequestParam Integer pageNumber,
                                 @AuthenticationPrincipal User user) {
        model.put("user", user);

        ArrayList userss = new ArrayList();
        userss.add(user);
        model.put("userss", userss);

        // Портянка для пагинации
        Page<Message> messagePage = messageRepo
                .findAll(new PageRequest(pageNumber - 1,
                        10, Sort.Direction.ASC, "id"));
        List<Message> tweets = messagePage.getContent();
        model.put("tweets", tweets);

        int pagesNumber = messagePage.getTotalPages();
        model.put("totalNumber", pagesNumber);
        ArrayList pagesList = new ArrayList();
        for (int i = 0; i < pagesNumber; i++) {
            pagesList.add(i + 1);
        }
        model.put("pagesList", pagesList);

        ArrayList pag = new ArrayList();
        pag.add(pageNumber);
        model.put("pag", pag);

        return "main";
    }

    @GetMapping("/retweet/{id}")
    public String retweet(@PathVariable("id") Integer id,
                          Map<String, Object> model,
                          @AuthenticationPrincipal User user) {
        Set<Message> retweets = user.getMessages();
        retweets.add(messageRepo.findById(id));
        user.setMessages(retweets);
        userRepo.save(user);
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
            Iterable<Message> messages = messageRepo.findAll();
            model.put("messages", messages);
            return "main";
        }

        Date date = new Date();
        Message message = new Message(text, date, user);
        messageRepo.save(message);
        return "redirect:/";
    }

    @GetMapping("/user/{id}")
    public String userMessages(Map<String, Object> model,
                               @AuthenticationPrincipal User user,
                               @PathVariable("id") Integer id) {
        Iterable<Message> tweets = messageRepo.findByAuthor(userRepo.findById(id));
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

        Message tweet = messageRepo.findById(id);

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
        messageRepo.save(tweet);
        return "redirect:/user/" + user.getId();
    }

    @GetMapping("/message/{id}")
    public String messagePage(Map<String, Object> model,
                              @AuthenticationPrincipal User user,
                              @PathVariable("id") Integer id) {
        Iterable<Message> tweets = messageRepo.findByAuthor(userRepo.findById(id));
        model.put("tweets", tweets);

        Message currentMessage = messageRepo.findById(id);
        model.put("currentMessage", currentMessage);

        if (messageRepo.findById(id).getReply() != null) {
            Set<Message> replies;
            replies = messageRepo.findById(id).getReply().getReplies();

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
        if (replyPoolRepo.findById(currentMessageId) != null) {
            replyPool = replyPoolRepo.findById(currentMessageId);
        } else {
            replyPool = new ReplyPool();
            replyPool.setId(currentMessageId);
        }
        message.setReplyPool(replyPool);
        replyPoolRepo.save(replyPool);
        messageRepo.save(message);

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
        userRepo.save(user);
        model.put("user", user);

        return "profile";
    }


}

package com.gmail.ribil39.controller;

import com.gmail.ribil39.domain.Message;
import com.gmail.ribil39.domain.User;
import com.gmail.ribil39.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    MessageRepo messageRepo;

    @GetMapping("/")
    public String main(Map<String, Object> model){
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/addmessage")
    public String add(@AuthenticationPrincipal User user, @RequestParam String text,
                      Map<String, Object> model) {

        if (text.length()>250) {
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


}

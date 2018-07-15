package com.gmail.ribil39.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String text;
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToMany(mappedBy = "messages", fetch = FetchType.EAGER)
    private Set<User> retweets = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "replypool_id")
    private ReplyPool replyPool;

    @OneToOne(mappedBy = "message")
    private ReplyPool reply;



    public Message() {
    }

    public Message(String text, Date date, User user) {
        this.author = user;
        this.text = text;
        this.date = date;
    }

    public Message(String text, Date date, User author, Set<User> retweets) {
        this.text = text;
        this.date = date;
        this.author = author;
        this.retweets = retweets;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }
    public String getAuthorNick() {
        return author != null ? author.getName() : "anon";
    }
    public Integer getAuthorId() {
        return author.getId();
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<User> getRetweets() {
        return retweets;
    }

    public void setRetweets(Set<User> retweets) {
        this.retweets = retweets;
    }

    public ReplyPool getReplyPool() {
        return replyPool;
    }

    public void setReplyPool(ReplyPool replyPool) {
        this.replyPool = replyPool;
    }

    public ReplyPool getReply() {
        return reply;
    }

    public void setReply(ReplyPool reply) {
        this.reply = reply;
    }
}


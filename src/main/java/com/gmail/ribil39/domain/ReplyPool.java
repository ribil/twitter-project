package com.gmail.ribil39.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ReplyPool {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "replyPool")
    Set<Message> replies = new HashSet<>();

    @OneToOne
    @PrimaryKeyJoinColumn
    private Message message;

    public ReplyPool() {
    }

    public ReplyPool(Set<Message> replies) {
        this.replies = replies;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Message> getReplies() {
        return replies;
    }

    public void setReplies(Set<Message> replies) {
        this.replies = replies;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}

package com.chat.Entity;

import com.chat.Entity.ThreadEntity;
import com.chat.model.Message;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonView;

import javax.persistence.*;

@Entity(name = "messages")
public class MessageEntity {
    @Column(name = "user")
    public String user;

    @Column(name = "text")
    public String text;

    @Column(name = "ts")
    public String ts;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "thread_name", updatable = false)
    public ThreadEntity thread;

    public Message getObject() {
        Message m = new Message();
        m.text = text;
        m.user = user;
        m.ts = ts;
        m.thread_name = thread.thread_name;
        return m;
    }
}
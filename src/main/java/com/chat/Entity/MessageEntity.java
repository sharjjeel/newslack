package com.chat.Entity;

import com.chat.Entity.ThreadEntity;
import com.chat.model.Message;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonView;

import javax.persistence.*;

@Entity(name = "messages")
public class MessageEntity {
    @Id
    @Column(name = "message_id")
    public String message_id;

    @Column(name = "user_name")
    public String user_name;

    @Column(name = "text")
    public String text;

    @Column(name = "ts")
    public String ts;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "thread_name", updatable = false)
    public ThreadEntity thread;

    public Message getObject() {
        Message m = new Message();
        m.message_id = message_id;
        m.text = text;
        m.user_name = user_name;
        m.ts = ts;
        m.thread_name = thread.thread_name;
        return m;
    }
}
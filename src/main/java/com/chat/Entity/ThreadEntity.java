package com.chat.Entity;

import com.chat.model.Message;
import com.chat.model.Thread;

import javax.persistence.*;
import java.util.List;

/**
 * Created by saziz on 7/28/16.
 */
@Entity(name = "threads")
public class ThreadEntity {
    @Id
    @Column(name = "thread_name")
    public String thread_name;

    @Column(name = "ts")
    public String ts;

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<MessageEntity> messages;
//
//    public List<MessageEntity> getMessages() {
//        return messages;
//    }

    public Thread getObject() {
        Thread t = new Thread();
        t.thread_name = thread_name;
        t.ts = ts;
        return t;
    }
}

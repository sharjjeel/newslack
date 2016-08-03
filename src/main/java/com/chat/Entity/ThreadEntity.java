package com.chat.Entity;

import com.chat.model.Thread;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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

    public Thread getObject() {
        Thread t = new Thread();
        t.thread_name = thread_name;
        t.ts = ts;
        return t;
    }
}

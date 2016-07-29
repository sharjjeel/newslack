package com.chat.DAO;

import com.chat.Entity.MessageEntity;
import com.chat.Entity.ThreadEntity;
import com.chat.model.Message;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by sharjjeel on 7/10/16.
 */
public class MessageDAO {

    public List<MessageEntity> getAllMessages() {
        EntityManager em = PersistenceUtil.getEntityManager();
        return em.createQuery("SELECT o FROM messages o", MessageEntity.class).getResultList();
    }

    public List<MessageEntity> getAllMessagesForThread(String thread_name) {
        EntityManager em = PersistenceUtil.getEntityManager();
        return em.createQuery("SELECT o FROM messages o where o.thread_name = :thread_name",
                MessageEntity.class)
                .setParameter("thread_name", thread_name)
                .getResultList();
    }

    public ThreadEntity addMessage(Message message) {
        EntityManager em = PersistenceUtil.getEntityManager();
        TypedQuery<ThreadEntity> query = em.createQuery(
                "SELECT e FROM threads e "
                        + "WHERE e.thread_name = :thread_name", ThreadEntity.class);
        query.setParameter("thread_name", message.thread_name);

        ThreadEntity threadEntity;
        List<ThreadEntity> threads = query.getResultList();
        if (threads.size() == 0) {
            threadEntity = new ThreadEntity();
            threadEntity.thread_name = message.thread_name;
            threadEntity.ts = message.ts;
        } else {
            threadEntity = threads.get(0);
        }
        threadEntity.ts = message.ts;
        em.persist(threadEntity);
        
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.text = message.text;
        messageEntity.thread = threadEntity;
        messageEntity.ts = message.ts;
        messageEntity.user = message.user;

        em.persist(messageEntity);
        em.flush();

        return threadEntity;
    }


}

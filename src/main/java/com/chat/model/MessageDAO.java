package com.chat.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by sharjjeel on 7/10/16.
 */
@SuppressWarnings("unchecked")
public class MessageDAO {
    public List<Message> getAllMessages() {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<Message> actors = em.createQuery("FROM messages").getResultList();
        transaction.commit();
        return actors;
    }


}

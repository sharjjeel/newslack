package com.chat.DAO;

import com.chat.Entity.MessageEntity;
import com.chat.Entity.ThreadEntity;
import com.chat.model.Thread;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by saziz on 7/28/16.
 */
public class ThreadDAO {
    public List<ThreadEntity> getAll() {
        EntityManager em = PersistenceUtil.getEntityManager();
        return em.createQuery("SELECT o FROM threads o", ThreadEntity.class).getResultList();
    }

    public List<ThreadEntity> getLastUpdated(int count) {
        EntityManager em = PersistenceUtil.getEntityManager();
        TypedQuery<ThreadEntity> query = em.createQuery("SELECT o FROM threads o " +
                "ORDER BY ts DESC LIMIT :count", ThreadEntity.class);
        query.setParameter("count", count);
        return query.getResultList();
    }

}

package com.chat.DAO;

import com.chat.Entity.MessageEntity;
import com.chat.Entity.ThreadEntity;
import com.chat.model.Thread;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

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
                "ORDER BY o.ts DESC", ThreadEntity.class);
        query.setMaxResults(count);
        return query.getResultList();
    }

    public Optional<ThreadEntity> getThread(String name) {
        EntityManager em = PersistenceUtil.getEntityManager();
        return Optional.ofNullable(
                em.createQuery("SELECT o FROM threads o where o.thread_name = :thread_name",
                        ThreadEntity.class)
                .setParameter("thread_name", name).getSingleResult());
    }

}

package com.wora.smartbank2.repositories.impl;

import com.wora.smartbank2.config.JPAUtil;
import com.wora.smartbank2.entities.models.Request;
import com.wora.smartbank2.repositories.IRequestRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class RequestRepository implements IRequestRepository {
    private EntityManager entityManager;
    public RequestRepository(){
        this.entityManager = (EntityManager) JPAUtil.entityManagerFactory().createEntityManager();
    }

    @Override
    public void create(Request request) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(request);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public void update(Request request) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(request);
            transaction.rollback();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public Request findById(Long id) {
        return entityManager.find(Request.class, id);
    }

    @Override
    public List<Request> findAll() {
            EntityTransaction transaction = entityManager.getTransaction();
            List<Request> requests = null;
        try {
            transaction.begin();
            requests = entityManager.createQuery("SELECT * FROM Request ", Request.class).getResultList();
            transaction.commit();

            return requests;
        } catch (Exception e){
            transaction.rollback();
        }
        return requests;
    }

    @Override
    public void delete(Long id){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(id);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        }
    }
}

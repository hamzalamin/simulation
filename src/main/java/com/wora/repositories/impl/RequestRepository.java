package com.wora.repositories.impl;

import com.wora.entities.models.Request;
import com.wora.repositories.IRequestRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transaction;

import java.util.List;

public class RequestRepository implements IRequestRepository {
    private EntityManager entityManager;

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
    public List<Request> findAll(Request request) {
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

package com.wora.smartbank2.repositories.impl;

import com.wora.smartbank2.config.JPAUtil;
import com.wora.smartbank2.entities.models.Request;
import com.wora.smartbank2.repositories.IRequestRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class RequestRepository implements IRequestRepository {
    private final EntityManagerFactory emf;
    public RequestRepository(){
        this.emf = JPAUtil.entityManagerFactory();
    }

    @Override
    public void create(Request request) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(request);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
        } finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    @Override
    public void update(Request request) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(request);
            entityManager.flush();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    @Override
    public Request findById(Long id) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Request request = null;
        try {
            transaction.begin();
            request = entityManager.find(Request.class, id);
            transaction.commit();
            return request;
        } catch (Exception e){
            transaction.rollback();
        } finally {
            if (entityManager != null){
                entityManager.close();
            }
        }

        return request;
    }

    @Override
    public List<Request> findAll() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
            List<Request> requests = null;
        try {
            transaction.begin();
            requests = entityManager.createQuery("SELECT r FROM Request r", Request.class).getResultList();
            transaction.commit();

            return requests;
        } catch (Exception e){
            transaction.rollback();
        } finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
        return requests;
    }

    @Override
    public void delete(Long id){
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Request request = entityManager.find(Request.class, id);
            entityManager.remove(request);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        } finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }
}

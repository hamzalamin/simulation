package com.wora.smartbank2.repositories.impl;

import com.wora.smartbank2.entities.models.RequestStatus;
import com.wora.smartbank2.repositories.IRequestStatusRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class RequestStatusRepository implements IRequestStatusRepository {
    private final EntityManagerFactory emf;

    public RequestStatusRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void create(RequestStatus requestStatus) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
//            System.out.println("request status lll : " + requestStatus);
            entityManager.persist(requestStatus);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<RequestStatus> findAll() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<RequestStatus> requestStatus = null;
        try {
            transaction.begin();
            requestStatus = entityManager.createQuery("SELECT r FROM RequestStatus r", RequestStatus.class).getResultList();
            transaction.commit();

            return requestStatus;
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return requestStatus;
    }
}

package com.wora.smartbank2.repositories.impl;

import com.wora.smartbank2.config.JPAUtil;
import com.wora.smartbank2.entities.models.RequestStatus;
import com.wora.smartbank2.repositories.IRequestStatusRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;

@ApplicationScoped
public class RequestStatusRepository implements IRequestStatusRepository {
    private final EntityManagerFactory emf;

    public RequestStatusRepository() {
        this.emf = JPAUtil.entityManagerFactory();
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
    public List<RequestStatus> findAll(Long requestId) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<RequestStatus> requestStatus = null;
        try {
            transaction.begin();
            requestStatus = entityManager.createQuery("SELECT r FROM RequestStatus r WHERE r.request.id = :id", RequestStatus.class)
                    .setParameter("id", requestId)
                    .getResultList();
            transaction.commit();

            return requestStatus;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Print the exception for debugging
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return requestStatus;
    }
}

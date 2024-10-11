package com.wora.smartbank2.repositories.impl;

import com.wora.smartbank2.config.JPAUtil;
import com.wora.smartbank2.entities.enums.CreditStatus;
import com.wora.smartbank2.entities.models.Request;
import com.wora.smartbank2.entities.models.Status;
import com.wora.smartbank2.repositories.IRequestRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RequestRepository implements IRequestRepository {
    private final EntityManagerFactory emf;

    public RequestRepository() {
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
    public void update(Request request) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(request);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("HI FROM THE ROLLBACK");
            transaction.rollback();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public Optional<Request> findById(Long id) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Request request = entityManager.find(Request.class, id);
            transaction.commit();
            return Optional.ofNullable(request);
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
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
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return requests;
    }

    @Override
    public void delete(Long id) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Request request = entityManager.find(Request.class, id);
            entityManager.remove(request);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<Request> filterByDate(LocalDate birthDate) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<Request> requests = null;
        try {
            transaction.begin();
            requests = entityManager.createQuery(
                            "SELECT r FROM Request r WHERE r.birthDate = :birthDate", Request.class)
                    .setParameter("birthDate", birthDate)
                    .getResultList();
            transaction.commit();
            return requests;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return requests;
    }

    @Override
    public List<Request> filterByStatus(String status) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            List<Request> requests = entityManager.createQuery(
                            "SELECT r FROM Request r " +
                                    "INNER JOIN r.status rs " +
                                    "INNER JOIN rs.status s " +
                                    "WHERE s.status = :status", Request.class)
                    .setParameter("status", status)
                    .getResultList();

            transaction.commit();
            System.out.println("yoo from the commit, size: " + requests.size());
            return requests;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            transaction.rollback();
            System.out.println("Yoo from the rollback");
            return Collections.emptyList();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }


}

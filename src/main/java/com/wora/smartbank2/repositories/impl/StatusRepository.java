package com.wora.smartbank2.repositories.impl;

import com.wora.smartbank2.config.JPAUtil;
import com.wora.smartbank2.entities.models.Status;
import com.wora.smartbank2.repositories.IStatusRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class StatusRepository implements IStatusRepository {
    private final EntityManagerFactory emf;

    public StatusRepository() {
        this.emf = JPAUtil.entityManagerFactory();
    }


    @Override
    public void create(Status status) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(status);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        } finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    @Override
    public void update(Status status) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(status);
            transaction.commit();
        } catch (Exception e){
            entityManager.close();
        } finally {
            if (entityManager != null){
                entityManager.close();
            }
        }


    }

    @Override
    public Optional<Status> findById(Long id) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Status status = entityManager.find(Status.class, id);
            transaction.commit();
            return Optional.ofNullable(status);
        } catch (Exception e){
            transaction.rollback();
            throw new RuntimeException(e.getMessage());
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Status status = entityManager.find(Status.class, id);
            entityManager.remove(status);
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
    public List<Status> getAll(){
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<Status> status = null;
        try {
            transaction.begin();
            status = entityManager.createQuery("SELECT s FROM Status s", Status.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            if (entityManager != null){
                entityManager.close();
            }
        }

        return status;
    }
}

package com.wora.smartbank2.services.impl;

import com.wora.smartbank2.entities.models.Request;
import com.wora.smartbank2.entities.models.Status;
import com.wora.smartbank2.repositories.IStatusRepository;
import com.wora.smartbank2.services.IStatusService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StatusService implements IStatusService {
    private final IStatusRepository repository;
    private final Validator validator;


    public StatusService(IStatusRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Status findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void create(Status status) {
        Set<ConstraintViolation<Status>> constraintViolations = validator.validate(status);
        if(!constraintViolations.isEmpty()) {
            throw new RuntimeException("error in validation of Status creation");
        }
        repository.create(status);
    }

    @Override
    public void update(Status status) {
        Set<ConstraintViolation<Status>> constraintViolations = validator.validate(status);
        if(!constraintViolations.isEmpty()) {
            throw new RuntimeException("error in validation of Status updating");
        }
        repository.update(status);
    }

    @Override
    public void delete(Long id) {
        Set<ConstraintViolation<Long>> constraintViolations = validator.validate(id);
        if(!constraintViolations.isEmpty()) {
            throw new RuntimeException("error in validation of Status deleting");
        }
        repository.delete(id);
    }

    @Override
    public List<Status> getAll(){
        try {
            return repository.getAll();
        } catch (Exception e) {
            e.getMessage();
            return new ArrayList<>();
        }
    }
}

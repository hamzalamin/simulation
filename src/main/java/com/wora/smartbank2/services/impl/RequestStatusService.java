package com.wora.smartbank2.services.impl;

import com.wora.smartbank2.entities.models.RequestStatus;
import com.wora.smartbank2.repositories.IRequestStatusRepository;
import com.wora.smartbank2.services.IRequestStatusService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class RequestStatusService implements IRequestStatusService {
    private final IRequestStatusRepository repository;
    private final Validator validator;

//    public RequestStatusService(){
//        this.repository = null;
//        this.validator = null;
//    }

    @Inject
    public RequestStatusService(IRequestStatusRepository repository) {
        this.repository = repository;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();

    }

    @Override
    public List<RequestStatus> findAll(Long requestId) {
        try {
            return repository.findAll(requestId);
        } catch (Exception e) {
            e.getMessage();
            return new ArrayList<>();
        }
    }

    @Override
    public void create(RequestStatus requestStatus) {
        Set<ConstraintViolation<RequestStatus>> constraintViolations = validator.validate(requestStatus);
        if (!constraintViolations.isEmpty()) {
            throw new RuntimeException("error in validation of Status creation");
        }
        repository.create(requestStatus);
    }
}

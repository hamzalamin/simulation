package com.wora.smartbank2.services.impl;

import com.wora.smartbank2.entities.enums.CreditStatus;
import com.wora.smartbank2.entities.models.Request;
import com.wora.smartbank2.entities.models.Status;
import com.wora.smartbank2.repositories.IRequestRepository;
import com.wora.smartbank2.services.IRequestService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class RequestService implements IRequestService {
    private final IRequestRepository requestRepository;
    private final Validator validator;

    @Inject
    public RequestService(IRequestRepository requestRepository, Validator validator) {
        this.requestRepository = requestRepository;
        this.validator = validator;
    }

    @Override
    public List<Request> findAll() {
        try {
            return requestRepository.findAll();
        } catch (Exception e) {
            e.getMessage();
            return new ArrayList<>();
        }
    }

    @Override
    public Request findById(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("request with id " + id + " not found"));
    }

    @Override
    public void create(Request request) {
        Set<ConstraintViolation<Request>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            String errorMessage = violations.iterator().next().getMessage();
            throw new RuntimeException(errorMessage);
        }

        requestRepository.create(request);
    }

    @Override
    public void update(Request request) {
        Set<ConstraintViolation<Request>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new RuntimeException("error in Validation of update request");
        }
        requestRepository.update(request);
    }

    @Override
    public void delete(Long id) {
        Set<ConstraintViolation<Long>> constraintViolations = validator.validate(id);
        if (!constraintViolations.isEmpty()) {
            throw new RuntimeException("error in Validation of Delete request");
        }
        requestRepository.delete(id);
    }

    @Override
    public List<Request> filterByDate(LocalDate birthDate) {
        Set<ConstraintViolation<LocalDate>> constraintViolations = validator.validate(birthDate);
        if (!constraintViolations.isEmpty()) {
            throw new RuntimeException("error in Validation of filter by date request");
        }
        return requestRepository.filterByDate(birthDate);
    }

    @Override
    public List<Request> filterByStatus(Status status) {
        Set<ConstraintViolation<Status>> constraintViolations = validator.validate(status);
        if (!constraintViolations.isEmpty()) {
            throw new RuntimeException("Error in validation of filter by status request");
        }

        String statusString = status.getStatus();
        return requestRepository.filterByStatus(statusString);
    }

}

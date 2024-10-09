package com.wora.smartbank2.services.impl;

import com.wora.smartbank2.entities.enums.CreditStatus;
import com.wora.smartbank2.entities.models.Request;
import com.wora.smartbank2.repositories.IRequestRepository;
import com.wora.smartbank2.services.IRequestService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RequestService implements IRequestService {
    private final IRequestRepository requestRepository;
    private final Validator validator;

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
        System.out.println("id + " + id);
        Request request = requestRepository.findById(id);
        System.out.println(request);
        return request;
    }

    @Override
    public void create(Request request) {
        Set<ConstraintViolation<Request>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
//            Map<String, String> errors = constraintViolations.stream()
//                    .collect(Collectors.toMap(
//                            e -> e.getPropertyPath().toString(),
//                            ConstraintViolation::getMessage
//                    ));
//            errors.forEach((k, v) -> System.err.println(k + " -> " + v));
            throw new RuntimeException("error in validation of request creation");
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
    public List<Request> filterByStatus(CreditStatus creditStatus) {
        Set<ConstraintViolation<CreditStatus>> constraintViolations = validator.validate(creditStatus);
        if (!constraintViolations.isEmpty()) {
            throw new RuntimeException("error in Validation of filter by status request");
        }
        return requestRepository.filterByStatus(creditStatus);
    }

}

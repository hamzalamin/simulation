package com.wora.smartbank2.services;

import com.wora.smartbank2.entities.enums.CreditStatus;
import com.wora.smartbank2.entities.models.Request;
import com.wora.smartbank2.entities.models.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IRequestService {
    List<Request> findAll();
    Request findById(Long id);
    void create(Request request);
    void update(Request request);
    void delete(Long id);
    List<Request> filterByDate(LocalDate birthDate);
    List<Request> filterByStatus(Status status);
}

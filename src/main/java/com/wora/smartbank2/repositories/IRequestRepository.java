package com.wora.smartbank2.repositories;

import com.wora.smartbank2.entities.enums.CreditStatus;
import com.wora.smartbank2.entities.models.Request;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IRequestRepository {
    void create(Request request);
    void update(Request request);
    Optional<Request> findById(Long id);
    void delete(Long id);
    List<Request> findAll();
    List<Request> filterByDate(LocalDate birthDate);
    List<Request> filterByStatus(CreditStatus creditStatus);
}

package com.wora.smartbank2.services.impl;
import com.wora.smartbank2.entities.enums.CreditStatus;
import com.wora.smartbank2.entities.models.Request;
import com.wora.smartbank2.repositories.IRequestRepository;
import com.wora.smartbank2.services.IRequestService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RequestService implements IRequestService {
    private final IRequestRepository requestRepository;

    public RequestService(IRequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }


    @Override
    public List<Request> findAll() {
        try {
            return requestRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Request findById(Long id) {
        return requestRepository.findById(id);
    }

    @Override
    public void create(Request request) {
        requestRepository.create(request);
    }

    @Override
    public void update(Request request) {
        requestRepository.update(request);
    }

    @Override
    public void delete(Long id) {
        requestRepository.delete(id);
    }

    @Override
    public List<Request> filterByDate(LocalDate birthDate){
        return requestRepository.filterByDate(birthDate);
    };

    @Override
    public List<Request> filterByStatus(CreditStatus creditStatus){
        return requestRepository.filterByStatus(creditStatus);
    };
}

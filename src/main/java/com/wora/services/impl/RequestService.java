package com.wora.services.impl;
import com.wora.entities.models.Request;
import com.wora.repositories.impl.RequestRepository;
import com.wora.service.IRequestService;

import java.util.List;

public class RequestService implements IRequestService {
    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }


    @Override
    public List<Request> findAll() {
        return requestRepository.findAll();
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
}

package com.wora.service;

import com.wora.entities.models.Request;

import java.util.List;

public interface IRequestService {
    List<Request> findAll();
    Request findById(Long id);
    void create(Request request);
    void update(Request request);
    void delete(Long id);
}

package com.wora.smartbank2.repositories;

import com.wora.smartbank2.entities.models.Request;

import java.util.List;

public interface IRequestRepository {
    void create(Request request);
    void update(Request request);
    Request findById(Long id);
    void delete(Long id);
    List<Request> findAll();
}

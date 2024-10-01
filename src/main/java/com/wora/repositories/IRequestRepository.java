package com.wora.repositories;

import com.wora.entities.models.Request;

import java.util.List;

public interface IRequestRepository {
    void create(Request request);
    void update(Request request);
    Request findById(Long id);
    void delete(Long id);
    List<Request> findAll(Request request);
}

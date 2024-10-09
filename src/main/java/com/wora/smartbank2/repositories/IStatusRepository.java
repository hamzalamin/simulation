package com.wora.smartbank2.repositories;

import com.wora.smartbank2.entities.models.Request;
import com.wora.smartbank2.entities.models.Status;

import java.util.List;
import java.util.Optional;

public interface IStatusRepository {
    void create(Status status);
    void update(Status status);
    Optional<Status> findById(Long id);
    void delete(Long id);
    List<Status> getAll();
}

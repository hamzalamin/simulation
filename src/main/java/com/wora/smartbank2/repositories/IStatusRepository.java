package com.wora.smartbank2.repositories;

import com.wora.smartbank2.entities.models.Request;
import com.wora.smartbank2.entities.models.Status;

public interface IStatusRepository {
    void create(Status status);
    void update(Status status);
    Status findById(Long id);
    void delete(Long id);
}

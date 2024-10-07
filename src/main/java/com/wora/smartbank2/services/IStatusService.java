package com.wora.smartbank2.services;

import com.wora.smartbank2.entities.models.Request;
import com.wora.smartbank2.entities.models.Status;

public interface IStatusService {
    Status findById(Long id);
    void create(Status status);
    void update(Status status);
    void delete(Long id);
}

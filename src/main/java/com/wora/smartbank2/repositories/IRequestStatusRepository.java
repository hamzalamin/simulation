package com.wora.smartbank2.repositories;

import com.wora.smartbank2.entities.models.RequestStatus;

import java.util.List;

public interface IRequestStatusRepository {
    void create(RequestStatus requestStatus);
    List<RequestStatus> findAll();

}

package com.wora.smartbank2.services;

import com.wora.smartbank2.entities.models.RequestStatus;

import java.util.List;

public interface IRequestStatusService {
    List<RequestStatus> findAll(Long requestId);

    void create(RequestStatus requestStatus);

}

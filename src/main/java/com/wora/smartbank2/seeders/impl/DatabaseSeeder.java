package com.wora.smartbank2.seeders.impl;

import com.wora.smartbank2.entities.models.Status;
import com.wora.smartbank2.seeders.IDatabaseSeeder;
import com.wora.smartbank2.services.IStatusService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DatabaseSeeder implements IDatabaseSeeder {

    @Inject
    private IStatusService statusService;

    @Override
    public void statusSeed() {
        Status PENDING = new Status("PENDING");
        Status IN_PROGRESS = new Status("IN PROGRESS");
        Status COMPLETED = new Status("COMPLETED");
        Status CANCELLED = new Status("CANCELLED");

        statusService.create(PENDING);
        statusService.create(IN_PROGRESS);
        statusService.create(COMPLETED);
        statusService.create(CANCELLED);
    }
}

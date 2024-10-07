package com.wora.smartbank2.web.controllers;

import com.wora.smartbank2.repositories.IStatusRepository;
import com.wora.smartbank2.repositories.impl.RequestRepository;
import com.wora.smartbank2.repositories.impl.StatusRepository;
import com.wora.smartbank2.services.IStatusService;
import com.wora.smartbank2.services.impl.RequestService;
import com.wora.smartbank2.services.impl.StatusService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.validation.Validation;

@WebServlet("/status/*")
public class StatusController extends HttpServlet {
    private IStatusRepository statusRepository;
    private IStatusService statusService;

    @Override
    public void init() throws ServletException {
        this.statusRepository = new StatusRepository();
        this.statusService = new StatusService(statusRepository, Validation.buildDefaultValidatorFactory().getValidator());
    }


}

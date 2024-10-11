package com.wora.smartbank2.web.controllers;


import com.wora.smartbank2.config.JPAUtil;
import com.wora.smartbank2.entities.models.Request;
import com.wora.smartbank2.entities.models.RequestStatus;
import com.wora.smartbank2.entities.models.Status;
import com.wora.smartbank2.repositories.IRequestRepository;
import com.wora.smartbank2.repositories.IRequestStatusRepository;
import com.wora.smartbank2.repositories.IStatusRepository;
import com.wora.smartbank2.repositories.impl.RequestRepository;
import com.wora.smartbank2.repositories.impl.RequestStatusRepository;
import com.wora.smartbank2.repositories.impl.StatusRepository;
import com.wora.smartbank2.services.IRequestService;
import com.wora.smartbank2.services.IRequestStatusService;
import com.wora.smartbank2.services.IStatusService;
import com.wora.smartbank2.services.impl.RequestService;
import com.wora.smartbank2.services.impl.RequestStatusService;
import com.wora.smartbank2.services.impl.StatusService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Validation;

import java.io.IOException;
import java.util.List;

@WebServlet("/request/status/*")
public class RequestStatusController extends HttpServlet {
    @Inject
    private IRequestStatusRepository requestStatusRepository;
    @Inject
    private IRequestStatusService requestStatusService;

    @Inject
    private IRequestRepository requestRepository;
    @Inject
    private IRequestService requestService;

    @Inject
    private IStatusRepository statusRepository;
    @Inject
    private IStatusService statusService;

    @Override
    public void init() throws ServletException {
//        this.requestStatusRepository = new RequestStatusRepository(JPAUtil.entityManagerFactory());

//        this.requestRepository = new RequestRepository();
//        this.requestService = new RequestService(requestRepository, Validation.buildDefaultValidatorFactory().getValidator());

//        this.requestStatusService = new RequestStatusService(requestStatusRepository,
//                Validation.buildDefaultValidatorFactory().getValidator());

//        this.statusRepository = new StatusRepository();
//        this.statusService = new StatusService(statusRepository, Validation.buildDefaultValidatorFactory().getValidator());
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "requestStatusForm":
                    showForm(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        }
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        Long requestId;
        if (idParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request ID is missing");
            return;
        }

        try {
            requestId = Long.parseLong(idParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request ID format");
            return;
        }

        Request requestEntity = requestService.findById(requestId);

        if (requestEntity != null) {
            List<Status> statusList = statusService.getAll();
            System.out.println("statuaaaaaaaaaaaaaat aaal7a9: " + statusList);
            List<RequestStatus> requestStatusList = requestStatusService.findAll(requestEntity.getId());

            request.setAttribute("request", requestEntity);
            request.setAttribute("status", statusList);
            request.setAttribute("requestStatusList", requestStatusList);

            request.getRequestDispatcher("/WEB-INF/views/requests/details.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Request not found");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            RequestStatus newStatus = buildStatusParams(request);
            try {
                requestStatusService.create(newStatus);
                response.sendRedirect(request.getContextPath() + "/requests");
            } catch (RuntimeException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/WEB-INF/views/requests/details.jsp").forward(request, response);
            }
        }
    }

    private RequestStatus buildStatusParams(HttpServletRequest request) {
        Long statusId = Long.parseLong(request.getParameter("statusId"));
        Long requestId = Long.parseLong(request.getParameter("requestId"));
        String description = request.getParameter("description");

        Status status = statusService.findById(statusId);
        Request request1 = requestService.findById(requestId);

        RequestStatus requestStatus = new RequestStatus();

        requestStatus.setStatus(status);
        requestStatus.setRequest(request1);
        requestStatus.setDescription(description);
        return requestStatus;
    }


}

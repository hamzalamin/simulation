package com.wora.smartbank2.web.controllers;

import com.wora.smartbank2.entities.models.RequestStatus;
import com.wora.smartbank2.entities.models.Status;
import com.wora.smartbank2.repositories.IStatusRepository;
import com.wora.smartbank2.repositories.impl.StatusRepository;
import com.wora.smartbank2.services.IStatusService;
import com.wora.smartbank2.services.impl.StatusService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Validation;

import java.io.IOException;
import java.util.List;

@WebServlet("/status/*")
public class StatusController extends HttpServlet {
    private IStatusRepository statusRepository;
    private IStatusService statusService;

    @Override
    public void init() throws ServletException {
        this.statusRepository = new StatusRepository();
        this.statusService = new StatusService(statusRepository, Validation.buildDefaultValidatorFactory().getValidator());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String action = request.getParameter("action");

        if (action != null){
            switch (action){
                case "createStatusForm":
                    showCreateStatusForm(request, response);
                case "updateStatusForm":
                    showUpdateStatusForm(request, response);
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } else {
            if (pathInfo == null || pathInfo.equals("/status")) {
                getAllStatus(request, response);
            } else {
                getStatusById(request, response);
            }
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "create":
                    createStatus(request, response);
                    break;
                case "update":
                    updateStatus(request, response);
                    break;
                case "delete":
                    deleteStatus(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing");
        }
    }


    private void showCreateStatusForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/status/create.jsp").forward(request, response);
    }

    private void showUpdateStatusForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Status statusObj = statusService.findById(id);

        if (statusObj != null) {
            request.setAttribute("status", statusObj);
            request.getRequestDispatcher("/WEB-INF/views/status/update.jsp").forward(request, response);
        } else {
            if (!response.isCommitted()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Status not found for ID: " + id);
            }
        }
    }

    private void getAllStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Status> status = statusService.getAll();
        request.setAttribute("status", status);
        request.getRequestDispatcher("/WEB-INF/views/status/allStatus.jsp").forward(request, response);
    }

    private void getStatusById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        try {
            System.out.println(pathInfo);
            Long id = Long.parseLong(pathInfo.substring(1));
            Status statusObj = statusService.findById(id);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }


    private void createStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Status newStatus = buildStatusParams(request);
        statusService.create(newStatus);
        response.sendRedirect(request.getContextPath() + "/status");
    }

    private void updateStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Status updatedStatus = buildStatusParams(request);
        updatedStatus.setId(id);
        try{
            statusService.update(updatedStatus);
            System.out.println(updatedStatus);

        } catch (Exception e) {
            e.printStackTrace();

            request.setAttribute("error", "Failed to update status.");
            request.getRequestDispatcher("/WEB-INF/views/status/update.jsp").forward(request, response);
        }

        response.sendRedirect(request.getContextPath() + "/status");
    }

    private void deleteStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        statusService.delete(id);
        response.sendRedirect(request.getContextPath() + "/status");
    }

    private Status buildStatusParams(HttpServletRequest request) {
        String status = request.getParameter("status");
        return new Status(status);
    }

}

package com.wora.smartbank2.web.controllers;

import com.wora.smartbank2.entities.enums.Civility;
import com.wora.smartbank2.entities.models.Request;
import com.wora.smartbank2.helpers.Utils;
import com.wora.smartbank2.repositories.IRequestRepository;
import com.wora.smartbank2.repositories.impl.RequestRepository;
import com.wora.smartbank2.services.IRequestService;
import com.wora.smartbank2.services.impl.RequestService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/requests")
public class RequestController extends HttpServlet {
    private IRequestRepository requestRepository;
    private IRequestService requestService;

    @Override
    public void init() throws ServletException {
        this.requestRepository = new RequestRepository();
        this.requestService = new RequestService(requestRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null | pathInfo.equals("/")) {
            getAllRequests(request, response);
        } else {
            getRequestById(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "create":
                    createRequest(request, response);
                    break;
                case "update":
                    updateRequest(request, response);
                    break;
                case "delete":
                    deleteRequest(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing");
        }
    }


    private void getAllRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Request> requests = requestService.findAll();
        request.setAttribute("requests", requests);
        request.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(request, response);
    }

    private void getRequestById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        try {
            Long id = Long.parseLong(pathInfo.substring(1));
            Request requestObj = requestService.findById(id);

            if (requestObj != null) {
                request.setAttribute("request", requestObj);
                request.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request ID");
        }
    }

    private void createRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Request newRequest = buildRequestParams(request);
        requestService.create(newRequest);
        response.sendRedirect(request.getContextPath() + "/requests");
    }

    private void updateRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Request updatedRequest = buildRequestParams(request);
        updatedRequest.setId(id);
        requestService.update(updatedRequest);
        response.sendRedirect(request.getContextPath() + "/requests");
    }

    private void deleteRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        requestService.delete(id);
        response.sendRedirect(request.getContextPath() + "/requests");
    }

    private Request buildRequestParams(HttpServletRequest request) {
        String projectName = request.getParameter("project_name");
        String userType = request.getParameter("user_type");
        Double loanAmount = Utils.getDoubleParameter(request, "loan_amount");
        Double loanDuration = Utils.getDoubleParameter(request, "loan_duration");
        Double monthlyPayment = Utils.getDoubleParameter(request, "monthly_payment");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Civility civility = Utils.getEnumParameter(request, "civility", Civility.class);
        String fName = request.getParameter("f_name");
        String lName = request.getParameter("l_name");
        String cin = request.getParameter("cin");
        LocalDate birthDate = Utils.getDateParameter(request, "birth_date", "yyyy-MM-dd");
        LocalDate localDate = Utils.getDateParameter(request, "local_date", "yyyy-MM-dd");
        Boolean hasCredits = Utils.getBooleanParameter(request, "has_credits");

        Request newRequest = new Request();
        newRequest.setProjectName(projectName);
        newRequest.setUserType(userType);
        newRequest.setLoanAmount(loanAmount);
        newRequest.setLoanDuration(loanDuration);
        newRequest.setMonthlyPayment(monthlyPayment);
        newRequest.setEmail(email);
        newRequest.setPhone(phone);
        newRequest.setCivility(civility);
        newRequest.setfName(fName);
        newRequest.setlName(lName);
        newRequest.setCin(cin);
        newRequest.setBirthDate(birthDate);
        newRequest.setEmploymentStartDate(localDate);
        newRequest.setHasCredits(hasCredits);

        return newRequest;
    }
}

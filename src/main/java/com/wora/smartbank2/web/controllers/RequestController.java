package com.wora.smartbank2.web.controllers;

import com.wora.smartbank2.entities.enums.Civility;
import com.wora.smartbank2.entities.models.Request;
import com.wora.smartbank2.entities.models.Status;
import com.wora.smartbank2.helpers.Utils;
import com.wora.smartbank2.repositories.IRequestRepository;
import com.wora.smartbank2.repositories.IStatusRepository;
import com.wora.smartbank2.repositories.impl.RequestRepository;
import com.wora.smartbank2.repositories.impl.StatusRepository;
import com.wora.smartbank2.services.IRequestService;
import com.wora.smartbank2.services.IStatusService;
import com.wora.smartbank2.services.impl.RequestService;
import com.wora.smartbank2.services.impl.StatusService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Validation;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/requests/*")
public class RequestController extends HttpServlet {
    @Inject
    private IRequestRepository requestRepository;
    @Inject
    private IRequestService requestService;

    @Inject
    private IStatusRepository statusRepository;
    @Inject
    private IStatusService statusService;

//    @Override
//    public void init() throws ServletException {
//        this.requestRepository = new RequestRepository();
//        this.statusRepository = new StatusRepository();
//        this.requestService = new RequestService(requestRepository, Validation.buildDefaultValidatorFactory().getValidator());
//        this.statusService = new StatusService(statusRepository, Validation.buildDefaultValidatorFactory().getValidator());
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String action = request.getParameter("action");


        if (action != null) {
            switch (action) {
                case "createForm":
                    showCreateForm(request, response);
                    break;
                case "updateForm":
                    showUpdateForm(request, response);
                    break;
                case "filterByStatus":
                    filterByStatus(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } else {
            if (pathInfo == null || pathInfo.equals("/")) {
                getAllRequests(request, response);
            } else {
                getRequestById(request, response);
            }
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
                case "filterByStatus":
                    filterByStatus(request, response);
                    break;
                case "filterByDate":
                    filterByDate(request, response);
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
        System.out.println("Number of requests found: " + requests.size());
        List<Status> statuses = statusService.getAll();
        request.setAttribute("statuses", statuses);
        request.setAttribute("requests", requests);
        request.getRequestDispatcher("/WEB-INF/views/requests/allRequests.jsp").forward(request, response);
    }

    public void filterByDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LocalDate birthDate = Utils.getDateParameter(request, "birth_date", "yyyy-MM-dd");
        if (birthDate == null){
            getAllRequests(request, response);
        }

        List<Request> requests = requestService.filterByDate(birthDate);
        request.setAttribute("requests", requests);
        request.getRequestDispatcher("/WEB-INF/views/requests/allRequests.jsp").forward(request, response);
    }

    public void filterByStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Filter by Status called yooooo");
        String statusParam = request.getParameter("status");

        if (statusParam == null || statusParam.isEmpty()) {
            getAllRequests(request, response);
        }

        Long statusId;

        try {
            statusId = Long.parseLong(statusParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid status ID format");
            return;
        }

        Status status = statusService.findById(statusId);

        if (status == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid status value");
            return;
        }
        List<Request> requests = requestService.filterByStatus(status);
        List<Status> statuses = statusService.getAll();
        request.setAttribute("requests", requests);
        request.setAttribute("statuses", statuses);

        request.getRequestDispatcher("/WEB-INF/views/requests/allRequests.jsp").forward(request, response);
    }


    private void getRequestById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        try {
            System.out.println(pathInfo);
            Long id = Long.parseLong(pathInfo.substring(1));
            Request requestObj = requestService.findById(id);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
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
        System.out.println("l3aybat dupdate: " + updatedRequest);
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

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/requests/create.jsp").forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));

            Request requestObj = requestService.findById(id);
            List<Status> statusList = statusService.getAll();
            System.out.println("status List is heeerere : " + statusList);

            if (requestObj != null && statusList != null) {
                request.setAttribute("status", statusList);
                request.setAttribute("request", requestObj);

                request.getRequestDispatcher("/WEB-INF/views/requests/update.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request ID");
        }
    }

}
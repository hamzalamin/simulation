package com.wora.smartbank2.entities.models;

import com.wora.smartbank2.entities.enums.Civility;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @Column(name = "project_name", length = 255, nullable = false)
    private String projectName;

    @NotBlank
    @Column(name = "user_type", length = 255, nullable = false)
    private String userType;

    @NotNull
    @Column(name = "loan_amount", nullable = false)
    private Double loanAmount;

    @NotNull
    @Column(name = "loan_duration", nullable = false)
    private Double loanDuration;

    @NotNull
    @Column(name = "monthly_payment", nullable = false)
    private Double monthlyPayment;

    @NotBlank
    @Column(name = "email" ,nullable = false, length = 255)
    private String email;

    @NotBlank
    @Column(name = "phone", nullable = false, length = 255)
    private String phone;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Civility civility;

    @NotBlank
    @Column(name = "f_name", length = 255, nullable = false)
    private String fName;

    @NotBlank
    @Column(name = "l_name", length = 255, nullable = false)
    private String lName;

    @NotBlank
    @Column(name = "cin", length = 255, nullable = false)
    private String cin;

    @NotNull
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotNull
    @Column(name = "employment_start_date")
    private LocalDate employmentStartDate;

    @NotNull
    @Column(name = "has_credits", nullable = false)
    private boolean hasCredits;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RequestStatus> status;

    public void addStatus(RequestStatus requestStatus) {
        this.status.add(requestStatus);
        requestStatus.setRequest(this);
    }

    public Request(long id, String projectName, String userType, Double loanAmount, Double loanDuration,
                   Double monthlyPayment, String email, String phone, Civility civility, String fName,
                   String lName, String cin, LocalDate birthDate, LocalDate employmentStartDate,
                   boolean hasCredits, List<RequestStatus> status) {
        this.id = id;
        this.projectName = projectName;
        this.userType = userType;
        this.loanAmount = loanAmount;
        this.loanDuration = loanDuration;
        this.monthlyPayment = monthlyPayment;
        this.email = email;
        this.phone = phone;
        this.civility = civility;
        this.fName = fName;
        this.lName = lName;
        this.cin = cin;
        this.birthDate = birthDate;
        this.employmentStartDate = employmentStartDate;
        this.hasCredits = hasCredits;
        this.status = status;
    }
    public Request() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotBlank String getProjectName() {
        return projectName;
    }

    public void setProjectName(@NotBlank String projectName) {
        this.projectName = projectName;
    }

    public @NotBlank String getUserType() {
        return userType;
    }

    public void setUserType(@NotBlank String userType) {
        this.userType = userType;
    }

    public @NotNull Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(@NotNull Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public @NotNull Double getLoanDuration() {
        return loanDuration;
    }

    public void setLoanDuration(@NotNull Double loanDuration) {
        this.loanDuration = loanDuration;
    }

    public @NotNull Double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(@NotNull Double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank String email) {
        this.email = email;
    }

    public @NotBlank String getPhone() {
        return phone;
    }

    public void setPhone(@NotBlank String phone) {
        this.phone = phone;
    }

    public @NotNull Civility getCivility() {
        return civility;
    }

    public void setCivility(@NotNull Civility civility) {
        this.civility = civility;
    }

    public @NotBlank String getfName() {
        return fName;
    }

    public void setfName(@NotBlank String fName) {
        this.fName = fName;
    }

    public @NotBlank String getlName() {
        return lName;
    }

    public void setlName(@NotBlank String lName) {
        this.lName = lName;
    }

    public @NotBlank String getCin() {
        return cin;
    }

    public void setCin(@NotBlank String cin) {
        this.cin = cin;
    }

    public @NotNull LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(@NotNull LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public @NotNull LocalDate getEmploymentStartDate() {
        return employmentStartDate;
    }

    public void setEmploymentStartDate(@NotNull LocalDate employmentStartDate) {
        this.employmentStartDate = employmentStartDate;
    }

    @NotNull
    public boolean isHasCredits() {
        return hasCredits;
    }

    public void setHasCredits(@NotNull boolean hasCredits) {
        this.hasCredits = hasCredits;
    }

    public  List<RequestStatus>  getStatus() {
        return status;
    }

    public void setStatus(List<RequestStatus> statusId) {
        this.status = statusId;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", userType='" + userType + '\'' +
                ", loanAmount=" + loanAmount +
                ", loanDuration=" + loanDuration +
                ", monthlyPayment=" + monthlyPayment +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", civility=" + civility +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", cin='" + cin + '\'' +
                ", birthDate=" + birthDate +
                ", employmentStartDate=" + employmentStartDate +
                ", hasCredits=" + hasCredits +
                ", status=" + status +
                '}';
    }
}
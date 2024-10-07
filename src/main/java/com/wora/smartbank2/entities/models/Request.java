package com.wora.smartbank2.entities.models;

import com.wora.smartbank2.entities.enums.Civility;
import com.wora.smartbank2.entities.enums.CreditStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


import java.time.LocalDate;

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

    @NotBlank
    @Column(name = "loan_amount", nullable = false)
    private Double loanAmount;

    @NotBlank
    @Column(name = "loan_duration", nullable = false)
    private Double loanDuration;

    @NotBlank
    @Column(name = "monthly_payment", nullable = false)
    private Double monthlyPayment;

    @NotBlank
    @Column(name = "email" ,nullable = false, length = 255)
    private String email;

    @NotBlank
    @Column(name = "phone", nullable = false, length = 255)
    private String phone;

    @NotBlank
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

    @NotBlank
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;

    @NotBlank
    @Temporal(TemporalType.DATE)
    private LocalDate employmentStartDate;

    @NotBlank
    @Column(name = "has_credits", nullable = false)
    private boolean hasCredits;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status statusId;

    public Request(long id, String projectName, String userType, Double loanAmount, Double loanDuration, Double monthlyPayment, String email, String phone, Civility civility, String fName, String lName, String cin, LocalDate birthDate, LocalDate employmentStartDate, boolean hasCredits, Status statusId) {
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
        this.statusId = statusId;
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

    public @NotBlank Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(@NotBlank Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public @NotBlank Double getLoanDuration() {
        return loanDuration;
    }

    public void setLoanDuration(@NotBlank Double loanDuration) {
        this.loanDuration = loanDuration;
    }

    public @NotBlank Double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(@NotBlank Double monthlyPayment) {
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

    public @NotBlank Civility getCivility() {
        return civility;
    }

    public void setCivility(@NotBlank Civility civility) {
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

    public @NotBlank LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(@NotBlank LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public @NotBlank LocalDate getEmploymentStartDate() {
        return employmentStartDate;
    }

    public void setEmploymentStartDate(@NotBlank LocalDate employmentStartDate) {
        this.employmentStartDate = employmentStartDate;
    }

    @NotBlank
    public boolean isHasCredits() {
        return hasCredits;
    }

    public void setHasCredits(@NotBlank boolean hasCredits) {
        this.hasCredits = hasCredits;
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
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
                ", creditStatus=" + statusId +
                '}';
    }
}
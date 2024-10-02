package com.wora.smartbank2.entities.models;

import com.wora.entities.enums.Civility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private Integer loanDuration;

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
}

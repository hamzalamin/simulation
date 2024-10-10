package com.wora.smartbank2.entities.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "request_status")
public class RequestStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = true)
    private Status status;

    @NotBlank
    @Column(name = "description" , nullable = true)
    private String description;

    @NotNull
    @Column(name = "date")
    private LocalDate date = LocalDate.now();

    public RequestStatus(Long id, Request request, Status status, String description) {
        this.id = id;
        this.request = request;
        this.status = status;
        this.description = description;
    }

    public RequestStatus() {
    }

    public RequestStatus(Long id, @NotBlank String status) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    //    @Override
//    public String toString() {
//        return "RequestStatus{" +
//                "id=" + id +
//                ", request=" + request +
//                ", status=" + status +
//                '}';
//    }
}

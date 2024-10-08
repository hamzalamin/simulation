package com.wora.smartbank2.entities.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status", nullable = false, unique = true)
    @NotBlank
    private String status;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RequestStatus> requestStatuses;


    public Status(Long id, List<RequestStatus> requestStatuses) {
        this.id = id;
        this.requestStatuses = requestStatuses;
    }

    public Status(String status) {
        this.status = status;
    }

    public Status() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getStatus() {
        return status;
    }

    public void setStatus(@NotBlank String status) {
        this.status = status;
    }

    public List<RequestStatus> getRequestStatuses() {
        return requestStatuses;
    }

    public void setRequestStatuses(List<RequestStatus> requestStatuses) {
        this.requestStatuses = requestStatuses;
    }

//    @Override
//    public String toString() {
//        return "Status{" +
//                "id=" + id +
//                ", status='" + status + '\'' +
//                ", requestStatuses=" + requestStatuses +
//                '}';
//    }
}

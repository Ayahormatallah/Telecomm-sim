package com.example.telecomm;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "simulation_results")
public class SimulationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double transmissionRate;

    @Column(nullable = false)
    private Double latency;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public SimulationResult() {
    }

    public SimulationResult(Double transmissionRate, Double latency) {
        this.transmissionRate = transmissionRate;
        this.latency = latency;
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTransmissionRate() {
        return transmissionRate;
    }

    public void setTransmissionRate(Double transmissionRate) {
        this.transmissionRate = transmissionRate;
    }

    public Double getLatency() {
        return latency;
    }

    public void setLatency(Double latency) {
        this.latency = latency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
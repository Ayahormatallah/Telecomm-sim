package com.example.telecomm.service;

import com.example.telecomm.SimulationRequest;
import com.example.telecomm.SimulationResult;
import com.example.telecomm.repository.SimulationResultRepository;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {

    private final SimulationResultRepository resultRepository;

    public SimulationService(SimulationResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public SimulationResult simulateProtocol(SimulationRequest request) {
        // Exemple de simulation
        double transmissionRate = request.getConnectionSpeed() - request.getPacketLoss() * 0.5;
        double latency =( (request.getDistance() / request.getVitsig())+request.getPacketSize()/request.getConnectionSpeed());

        SimulationResult result = new SimulationResult(transmissionRate, latency);
        resultRepository.save(result);
        return result;
    }
}
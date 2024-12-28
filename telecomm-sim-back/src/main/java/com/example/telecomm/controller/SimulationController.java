package com.example.telecomm.controller;

import com.example.telecomm.service.SimulationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/simulate")
public class SimulationController {

    private final SimulationService simulationService;

    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    private static final String[] PROTOCOLS = {"TCP", "UDP", "SCTP"};

    @GetMapping
    public String showSimulationPage(Model model) {
        model.addAttribute("protocols", PROTOCOLS);
        return "simulate";
    }

    @PostMapping
    public String simulateProtocol(@RequestParam String protocol,
                                   @RequestParam int packetSize,
                                   @RequestParam int connectionSpeed,
                                   @RequestParam int distance,
                                   @RequestParam double packetLoss,
                                   @RequestParam double vitsignal,
                                   Model model) {

        double baseLatency = (double) packetSize / connectionSpeed + (double) distance / vitsignal;
        double protocolLatencyFactor = switch (protocol) {
            case "TCP" -> 10 + packetLoss * 2;
            case "UDP" -> 5 + packetLoss * 1.5;
            case "SCTP" -> 8 + packetLoss * 1.8;
            default -> 0;
        };

        double latency = baseLatency + protocolLatencyFactor;
        double throughput = connectionSpeed * (1 - packetLoss / 100);

        model.addAttribute("throughput", throughput);
        model.addAttribute("latency", latency);
        model.addAttribute("protocol", protocol);
        model.addAttribute("protocols", PROTOCOLS);

        return "simulate";
    }
}

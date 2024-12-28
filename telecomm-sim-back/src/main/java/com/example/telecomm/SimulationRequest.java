package com.example.telecomm;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class SimulationRequest {

    @NotBlank(message = "Le protocole est obligatoire")
    private String protocol;

    @Min(value = 1, message = "La taille du paquet doit être un nombre positif")
    private int packetSize;

    @Min(value = 1, message = "La vitesse de connexion doit être un nombre positif")
    private double connectionSpeed;

    @Min(value = 0, message = "La distance doit être un nombre positif ou égale à zéro")
    private double distance;

    @Min(value = 0, message = "La perte de paquets doit être un nombre positif ou égale à zéro")
    private double packetLoss;
    @Min(value = 1, message = "La vitesse de signal doit être un nombre positif strictement")
private double vitsig;
    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getPacketSize() {
        return packetSize;
    }

    public void setPacketSize(int packetSize) {
        this.packetSize = packetSize;
    }

    public double getVitsig() {
        return vitsig;
    }

    public void setVitsig(double vitsig) {
        this.vitsig = vitsig;
    }

    public double getConnectionSpeed() {
        return connectionSpeed;
    }

    public void setConnectionSpeed(double connectionSpeed) {
        this.connectionSpeed = connectionSpeed;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getPacketLoss() {
        return packetLoss;
    }

    public void setPacketLoss(double packetLoss) {
        this.packetLoss = packetLoss;
    }
}
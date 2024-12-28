package com.example.telecomm;

import jakarta.persistence.*;

@Entity
public class ProtocolSimulation {

    public enum Protocol {
        TCP, UDP, SCTP
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Protocol protocol;

    private int packetSize;
    private double connectionSpeed;
    private double distance;
    private double packetLoss;
    private double vitsig;

    // Constructeurs
    public ProtocolSimulation() {}

    public ProtocolSimulation(Protocol protocol, int packetSize, double connectionSpeed, double distance,
                              double packetLoss, double vitsig) {
        this.protocol = protocol;
        this.packetSize = packetSize;
        this.connectionSpeed = connectionSpeed;
        this.distance = distance;
        this.packetLoss = packetLoss;
        this.vitsig = vitsig;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public int getPacketSize() {
        return packetSize;
    }

    public void setPacketSize(int packetSize) {
        this.packetSize = packetSize;
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

    public double getVitsig() {
        return vitsig;
    }

    public void setVitsig(double vitsig) {
        this.vitsig = vitsig;
    }

    // Méthodes de calcul
    public double simulateLatency() {
        // Vérifications pour éviter les divisions par zéro
        if (connectionSpeed == 0 || vitsig == 0) {
            throw new IllegalArgumentException("La vitesse de connexion et la vitesse du signal doivent être non nulles.");
        }

        double baseLatency = (distance / vitsig) + (packetSize / connectionSpeed);
        double protocolLatencyFactor;

        switch (protocol) {
            case TCP:
                protocolLatencyFactor = 10 + packetLoss * 2;
                break;
            case UDP:
                protocolLatencyFactor = 5 + packetLoss * 1.5;
                break;
            case SCTP:
                protocolLatencyFactor = 8 + packetLoss * 1.8;
                break;
            default:
                protocolLatencyFactor = 0;
        }

        return baseLatency + protocolLatencyFactor;
    }

    public double simulateTransmissionRate() {
        return connectionSpeed * (1 - packetLoss / 100);
    }

    @Override
    public String toString() {
        return "ProtocolSimulation{" +
                "id=" + id +
                ", protocol=" + protocol +
                ", packetSize=" + packetSize +
                ", connectionSpeed=" + connectionSpeed +
                ", distance=" + distance +
                ", packetLoss=" + packetLoss +
                ", vitsig=" + vitsig +
                '}';
    }
}

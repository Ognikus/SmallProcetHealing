package com.example.healingcontroller;

public class Pacient {
    Integer Id;
    String PacientName;
    String PacientDisease;
    String PacientHealer;

    public Pacient(String id, String pacientName, String pacientDisease, String pacientHealer) {
        this.Id = (id != null && !id.trim().isEmpty()) ? Integer.valueOf(id) : null;
        PacientName = pacientName;
        PacientDisease = pacientDisease;
        PacientHealer = pacientHealer;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getPacientName() {
        return PacientName;
    }

    public void setPacientName(String pacientName) {
        PacientName = pacientName;
    }

    public String getPacientDisease() {
        return PacientDisease;
    }

    public void setPacientDisease(String pacientDisease) {
        PacientDisease = pacientDisease;
    }

    public String getPacientHealer() {
        return PacientHealer;
    }

    public void setPacientHealer(String pacientHealer) {
        PacientHealer = pacientHealer;
    }
}

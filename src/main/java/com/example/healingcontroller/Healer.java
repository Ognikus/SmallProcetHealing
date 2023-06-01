package com.example.healingcontroller;

public class Healer {
    Integer Id;
    String HealerName;
    String SpecHealer;
    Integer CabinetHealer;

    public Healer(String id, String healerName, String specHealer, String  cabinetHealer) {
        this.Id = (id != null && !id.trim().isEmpty()) ? Integer.valueOf(id) : null;
        this.HealerName = healerName;
        this.SpecHealer = specHealer;
        this.CabinetHealer = (cabinetHealer != null && !cabinetHealer.trim().isEmpty()) ? Integer.valueOf(cabinetHealer) : null;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getHealerName() {
        return HealerName;
    }

    public void setHealerName(String healerName) {
        HealerName = healerName;
    }

    public String getSpecHealer() {
        return SpecHealer;
    }

    public void setSpecHealer(String specHealer) {
        SpecHealer = specHealer;
    }

    public Integer getCabinetHealer() {
        return CabinetHealer;
    }

    public void setCabinetHealer(Integer cabinetHealer) {
        CabinetHealer = cabinetHealer;
    }
}

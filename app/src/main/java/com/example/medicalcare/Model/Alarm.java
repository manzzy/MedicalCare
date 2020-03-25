package com.example.medicalcare.Model;

public class Alarm {

    private String time;
    private String medicine;
    private Boolean isOn;

    public Alarm(String time, String medicine, Boolean isOn) {
        this.time = time;
        this.medicine = medicine;
        this.isOn = isOn;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public Boolean getOn() {
        return isOn;
    }

    public void setOn(Boolean on) {
        isOn = on;
    }
}

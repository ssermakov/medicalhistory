package ru.ssermakov.medicalhistory;

/**
 * Created by btb_wild on 07.02.2018.
 */

public class Patient {

    private String name, status;
    private int img;

    public Patient(String name, String status, int img) {
        this.name = name;
        this.status = status;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public int getImg() {
        return img;
    }
}

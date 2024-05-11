package com.example.caminata.model;

import java.util.ArrayList;

public class sennales  {
    private int  personId;
    private double acelerometroX;
    private double acelerometroY;
    private double acelerometroZ;
    private double giroscopioX;
    private double giroscopioY;
    private double giroscopioZ;
    private double magnetometroX;
    private double magnetometroY;
    private double magnetometroZ;

    public sennales(int personId, double acelerometroX, double acelerometroY, double acelerometroZ,
                    double giroscopioX, double giroscopioY, double giroscopioZ, double magnetometroX,
                    double magnetometroY, double magnetometroZ) {
        this.personId = personId;
        this.acelerometroX = acelerometroX;
        this.acelerometroY = acelerometroY;
        this.acelerometroZ = acelerometroZ;
        this.giroscopioX = giroscopioX;
        this.giroscopioY = giroscopioY;
        this.giroscopioZ = giroscopioZ;
        this.magnetometroX = magnetometroX;
        this.magnetometroY = magnetometroY;
        this.magnetometroZ = magnetometroZ;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public double getAcelerometroX() {
        return acelerometroX;
    }

    public void setAcelerometroX(double acelerometroX) {
        this.acelerometroX = acelerometroX;
    }

    public double getAcelerometroY() {
        return acelerometroY;
    }

    public void setAcelerometroY(double acelerometroY) {
        this.acelerometroY = acelerometroY;
    }

    public double getAcelerometroZ() {
        return acelerometroZ;
    }

    public void setAcelerometroZ(double acelerometroZ) {
        this.acelerometroZ = acelerometroZ;
    }

    public double getGiroscopioX() {
        return giroscopioX;
    }

    public void setGiroscopioX(double giroscopioX) {
        this.giroscopioX = giroscopioX;
    }

    public double getGiroscopioY() {
        return giroscopioY;
    }

    public void setGiroscopioY(double giroscopioY) {
        this.giroscopioY = giroscopioY;
    }

    public double getGiroscopioZ() {
        return giroscopioZ;
    }

    public void setGiroscopioZ(double giroscopioZ) {
        this.giroscopioZ = giroscopioZ;
    }

    public double getMagnetometroX() {
        return magnetometroX;
    }

    public void setMagnetometroX(double magnetometroX) {
        this.magnetometroX = magnetometroX;
    }

    public double getMagnetometroY() {
        return magnetometroY;
    }

    public void setMagnetometroY(double magnetometroY) {
        this.magnetometroY = magnetometroY;
    }

    public double getMagnetometroZ() {
        return magnetometroZ;
    }

    public void setMagnetometroZ(double magnetometroZ) {
        this.magnetometroZ = magnetometroZ;
    }
}

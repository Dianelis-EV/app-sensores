package com.example.caminata.model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class dataPerson {

    private ArrayList<com.example.caminata.model.person> person;
    private Date date ;
    private Time hour;
    private int ege;
    private int affection;
    private double shues;
    private double cinturaTobillo;
    private double leg;
    private double hight;
    private String observation;

    public dataPerson(Date date, Time hour, int ege, int affection, double shues, double cinturaTobillo, double leg, double hight, String observation) {
        this.date = date;
        this.hour = hour;
        this.ege = ege;
        this.affection = affection;
        this.shues = shues;
        this.cinturaTobillo = cinturaTobillo;
        this.leg = leg;
        this.hight = hight;
        this.observation = observation;
        this.person = new ArrayList<person>();
    }

    public ArrayList<com.example.caminata.model.person> getPerson() {
        return person;
    }

    public void setPerson(ArrayList<com.example.caminata.model.person> person) {
        this.person = person;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHour() {
        return hour;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }

    public int getEge() {
        return ege;
    }

    public void setEge(int ege) {
        this.ege = ege;
    }

    public int getAffection() {
        return affection;
    }

    public void setAffection(int affection) {
        this.affection = affection;
    }

    public double getShues() {
        return shues;
    }

    public void setShues(double shues) {
        this.shues = shues;
    }

    public double getCinturaTobillo() {
        return cinturaTobillo;
    }

    public void setCinturaTobillo(double cinturaTobillo) {
        this.cinturaTobillo = cinturaTobillo;
    }

    public double getLeg() {
        return leg;
    }

    public void setLeg(double leg) {
        this.leg = leg;
    }

    public double getHight() {
        return hight;
    }

    public void setHight(double hight) {
        this.hight = hight;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}

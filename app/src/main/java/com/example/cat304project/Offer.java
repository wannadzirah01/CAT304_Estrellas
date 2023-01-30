package com.example.cat304project;

public class Offer {
    String startPoint, endPoint, date, time, fares, gender;

    public Offer() {

    }

    public Offer(String startPoint, String endPoint, String date, String time, String fares, String gender) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.date = date;
        this.time = time;
        this.fares = fares;
        this.gender = gender;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getFares() {
        return fares;
    }

    public String getGender() {
        return gender;
    }
}

package com.example.cat304project;

public class CarpoolOfferList extends Offer {
    private String startPoint;
    private String endPoint;
    private String date;
    private String time;
    private String fares;
    private String gender;
    private String phoneNum;

    public CarpoolOfferList() {
    }

    public CarpoolOfferList(String startPoint, String endPoint, String date,
                            String time, String fares, String gender, String phoneNum) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.date = date;
        this.time = time;
        this.fares = fares;
        this.gender = gender;
        this.phoneNum = phoneNum;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFares() {
        return fares;
    }

    public void setFares(String fares) {
        this.fares = fares;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNum() { return phoneNum; }

    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }
}

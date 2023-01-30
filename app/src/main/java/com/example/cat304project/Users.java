package com.example.cat304project;

public class Users {
    String email;
    String name;
    String phone;

    public Users(String email, String name, String phone) {
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    public String getEmail(){
        return email;
    }

    public String getName(){
        return name;
    }

    public String getPhone(){
        return phone;
    }
}

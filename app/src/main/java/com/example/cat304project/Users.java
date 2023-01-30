package com.example.cat304project;

public class Users {

    public String name, email, phone;
    public Users(){

    }
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

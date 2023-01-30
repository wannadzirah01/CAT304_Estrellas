package com.example.cat304project;

public class Posts {
    String stuEmail, postDetails;

    public Posts() {
    }

    public Posts(String stuEmail, String postDetails){
        this.stuEmail = stuEmail;
        this.postDetails = postDetails;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public String getPostDetails() {
        return postDetails;
    }
}

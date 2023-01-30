package com.example.cat304project;

public class NewsFeedPosts extends Posts {
    String email, description;

    public NewsFeedPosts() {
    }

    public NewsFeedPosts(String email, String description) {
        this.email = email;
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

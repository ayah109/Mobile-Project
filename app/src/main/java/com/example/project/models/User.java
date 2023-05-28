package com.example.project.models;


import java.util.ArrayList;

public class User {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String preferredTravelDestinations;

    public User(String email, String firstName, String lastName, String password, String preferredTravelDestinations) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.preferredTravelDestinations = preferredTravelDestinations;
    }

    public User() {

    }

    public String getPreferredTravelDestinations() {
        return preferredTravelDestinations;
    }

    public void setPreferredTravelDestinations(String preferredTravelDestinations) {
        this.preferredTravelDestinations = preferredTravelDestinations;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
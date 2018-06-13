package com.example.mmiazi.uabv1_admin;

public class CurrentUser {
    FirebaseCreateAd createAd1;
    FirebaseCreateAd createAd2;
    FirebaseCreateAd createAd3;
    String email;
    String firstName;
    String gender;
    String lastName;
    String uID;

    CurrentUser(){

    }

    public CurrentUser(FirebaseCreateAd createAd1, FirebaseCreateAd createAd2, FirebaseCreateAd createAd3, String email, String firstName, String gender, String lastName, String uID) {
        this.createAd1 = createAd1;
        this.createAd2 = createAd2;
        this.createAd3 = createAd3;
        this.email = email;
        this.firstName = firstName;
        this.gender = gender;
        this.lastName = lastName;
        this.uID = uID;
    }

    public FirebaseCreateAd getCreateAd1() {
        return createAd1;
    }

    public void setCreateAd1(FirebaseCreateAd createAd1) {
        this.createAd1 = createAd1;
    }

    public FirebaseCreateAd getCreateAd2() {
        return createAd2;
    }

    public void setCreateAd2(FirebaseCreateAd createAd2) {
        this.createAd2 = createAd2;
    }

    public FirebaseCreateAd getCreateAd3() {
        return createAd3;
    }

    public void setCreateAd3(FirebaseCreateAd createAd3) {
        this.createAd3 = createAd3;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }
}

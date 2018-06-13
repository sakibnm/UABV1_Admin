package com.example.mmiazi.uabv1_admin;

public class FirebaseCreateAd {

    String ucomment;
    String uname;
    String uphoto;
    String urating;
    FirebaseCreateAd(){

    }

    public FirebaseCreateAd(String ucomment, String uname, String uphoto, String urating) {
        this.ucomment = ucomment;
        this.uname = uname;
        this.uphoto = uphoto;
        this.urating = urating;
    }

    public String getUcomment() {
        return ucomment;
    }

    public void setUcomment(String ucomment) {
        this.ucomment = ucomment;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUphoto() {
        return uphoto;
    }

    public void setUphoto(String uphoto) {
        this.uphoto = uphoto;
    }

    public String getUrating() {
        return urating;
    }

    public void setUrating(String urating) {
        this.urating = urating;
    }
}

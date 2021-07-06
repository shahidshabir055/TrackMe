package com.example.kashapp.Model;

public class Users {

    private  String name,Phone,Password;

    public Users(){


    }

    public Users(String name, String Phone, String Password) {
        this.name = name;
        this.Phone = Phone;
        this.Password = Password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
}

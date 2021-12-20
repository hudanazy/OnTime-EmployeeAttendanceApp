package com.onTime.employer.addEmployee;

import java.io.*;
public class employeeModel {
    int id;
    static String fname;
    static String lname;
    static String username;
    static String email;
    static String company_name;
    static String password;


    public employeeModel(int id, String fname, String lname, String username, String email, String company_name, String password) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.email = email;
        this.company_name = company_name;
        this.password=password;
    }

    public employeeModel() {
    }

    //toString

    @Override
    public String toString() {
        return "employeeModel{" +
                "id=" + id +
                ", first name='" + fname + '\'' +
                ", last name=" + lname +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", company name='" + company_name + '\'' +
                ", password='"+password+"\'"+
                '}';
    }


    //getters setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getFname() {
        return fname;
    }

    public static void setFname(String fname) {
        employeeModel.fname = fname;
    }

    public static String getLname() {
        return lname;
    }

    public static void setLname(String lname) {
        employeeModel.lname = lname;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        employeeModel.username = username;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        employeeModel.email = email;
    }

    public static String getCompany_name() {
        return company_name;
    }

    public static void setCompany_name(String company_name) {
        employeeModel.company_name = company_name;
    }

    public static String getPassword(){
        return password;
    }
}

package com.onTime.employer.viewAllEmployees.models;

public class ViewAllEmployeesModel {
    String employeeName,status,checkIn,checkOut,Date;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public ViewAllEmployeesModel(String employeeName, String status, String checkIn, String checkOut, String date) {
        this.employeeName = employeeName;
        this.status = status;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        Date = date;
    }
}

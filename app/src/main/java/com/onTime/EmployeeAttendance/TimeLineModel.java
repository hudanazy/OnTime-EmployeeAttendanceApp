package com.onTime.EmployeeAttendance;

public class TimeLineModel {
    String inTime;
    String date;

    public TimeLineModel(String inTime, String date, String outTime) {
        this.inTime = inTime;
        this.date = date;
        this.outTime = outTime;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    String outTime;
}

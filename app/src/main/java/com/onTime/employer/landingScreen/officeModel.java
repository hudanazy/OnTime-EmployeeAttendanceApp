package com.onTime.employer.landingScreen;

public class officeModel {
    int id;
    static String LAT;
    static String LONG;
    static String Comp_ID;

    public officeModel(int id, String LAT, String LONG, String Comp_ID) {
        this.id = id;
        this.LAT = LAT;
        this.LONG = LONG;
        this.Comp_ID = Comp_ID;

    }

    public officeModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getLAT() {
        return LAT;
    }

    public static void setLAT(String LAT) {
        officeModel.LAT = LAT;
    }

    public static String getLONG() {
        return LONG;
    }

    public static void setLONG(String LONG) {
        officeModel.LONG = LONG;
    }

    public static String getComp_ID() {
        return Comp_ID;
    }

    public static void setComp_ID(String comp_ID) {
        Comp_ID = comp_ID;
    }
}

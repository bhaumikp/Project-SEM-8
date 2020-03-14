package com.projects_kidscare.Bean;


public class Bean_DosesMedicine {
    private int RegID;
    private String DiseasesName;
    private String date;
    private int MedicineID;
    private int DiseasesID;
    private String MedicineName;
    private String Morning;
    private String Afternoon;
    private String Night;
    private String Doses;

    public int getRegID() {
        return RegID;
    }

    public void setRegID(int regID) {
        RegID = regID;
    }

    public String getDiseasesName() {
        return DiseasesName;
    }

    public void setDiseasesName(String diseasesName) {
        DiseasesName = diseasesName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMedicineID() {
        return MedicineID;
    }

    public void setMedicineID(int medicineID) {
        MedicineID = medicineID;
    }

    public int getDiseasesID() {
        return DiseasesID;
    }

    public void setDiseasesID(int diseasesID) {
        DiseasesID = diseasesID;
    }

    public String getMedicineName() {
        return MedicineName;
    }

    public void setMedicineName(String medicineName) {
        MedicineName = medicineName;
    }

    public String getMorning() {
        return Morning;
    }

    public void setMorning(String morning) {
        Morning = morning;
    }

    public String getAfternoon() {
        return Afternoon;
    }

    public void setAfternoon(String afternoon) {
        Afternoon = afternoon;
    }

    public String getNight() {
        return Night;
    }

    public void setNight(String night) {
        Night = night;
    }

    public String getDoses() {
        return Doses;
    }

    public void setDoses(String doses) {
        Doses = doses;
    }
}

package com.projects_kidscare.Bean;


public class Bean_MedicineDoses {

    private int MedicineID;
    private int DiseasesID;
    private String MedicineName;
    private String Morning;
    private String Afternoon;
    private String Evening;
    private String Night;
    private String AfterFood;
    private String BeforeFood;
    private String Doses;

    public String getDoses() {
        return Doses;
    }

    public void setDoses(String doses) {
        Doses = doses;
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

    public String getEvening() {
        return Evening;
    }

    public void setEvening(String evening) {
        Evening = evening;
    }

    public String getNight() {
        return Night;
    }

    public void setNight(String night) {
        Night = night;
    }

    public String getAfterFood() {
        return AfterFood;
    }

    public void setAfterFood(String afterFood) {
        AfterFood = afterFood;
    }

    public String getBeforeFood() {
        return BeforeFood;
    }

    public void setBeforeFood(String beforeFood) {
        BeforeFood = beforeFood;
    }
}

package com.projects_kidscare.Bean;


public class Bean_VaccineTable {

    private int VaccineID;
    private String VaccineName;
    private int VaccineDetailId;
    private String DoseNo;
    private String AfterMonth;
    private String BeforeMonth;
    private String OnMonth;
    private String Description;

    public int getVaccineID() {
        return VaccineID;
    }

    public void setVaccineID(int vaccineID) {
        VaccineID = vaccineID;
    }

    public int getVaccineDetailId() {
        return VaccineDetailId;
    }

    public void setVaccineDetailId(int vaccineDetailId) {
        VaccineDetailId = vaccineDetailId;
    }

    public String getVaccineName() {
        return VaccineName;
    }

    public void setVaccineName(String vaccineName) {
        VaccineName = vaccineName;
    }

    public String getDoseNo() {
        return DoseNo;
    }

    public void setDoseNo(String doseNo) {
        DoseNo = doseNo;
    }

    public String getAfterMonth() {
        return AfterMonth;
    }

    public void setAfterMonth(String afterMonth) {
        AfterMonth = afterMonth;
    }

    public String getBeforeMonth() {
        return BeforeMonth;
    }

    public void setBeforeMonth(String beforeMonth) {
        BeforeMonth = beforeMonth;
    }

    public String getOnMonth() {
        return OnMonth;
    }

    public void setOnMonth(String onMonth) {
        OnMonth = onMonth;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}

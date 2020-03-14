package com.projects_kidscare.Bean;



public class Bean_Vaccine {

    private int VaccineID;
    private int RegID;
    private String VaccineName;
    private  String Date;
    private String DoseNo;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoseNo() {
        return DoseNo;
    }

    public void setDoseNo(String doseNo) {
        DoseNo = doseNo;
    }

    public int getVaccineID() {
        return VaccineID;
    }

    public void setVaccineID(int vaccineID) {
        VaccineID = vaccineID;
    }

    public int getRegID() {
        return RegID;
    }

    public void setRegID(int regID) {
        RegID = regID;
    }

    public String getVaccineName() {
        return VaccineName;
    }

    public void setVaccineName(String vaccineName) {
        VaccineName = vaccineName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }


}

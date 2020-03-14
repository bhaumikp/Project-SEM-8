package com.projects_kidscare.Bean;


public class Bean_Vaccine_Sedual
{
    private int VaccineID;

    private int RegID;
    private int VaccineSedualID;
    private String VaccineName;
    private  String Date;
    private String DoseNo;
    private int status;
    private String AfterDate;
    private String OnDate;

    public String getVaccineIDTable() {
        return VaccineIDTable;
    }

    public void setVaccineIDTable(String vaccineIDTable) {
        VaccineIDTable = vaccineIDTable;
    }

    private String BeforeDate;
    private String ActualDate;
    private String VaccineIDTable;

    public int getVaccineSedualID() {
        return VaccineSedualID;
    }

    public void setVaccineSedualID(int vaccineSedualID) {
        VaccineSedualID = vaccineSedualID;
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

    public String getDoseNo() {
        return DoseNo;
    }

    public void setDoseNo(String doseNo) {
        DoseNo = doseNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAfterDate() {
        return AfterDate;
    }

    public void setAfterDate(String afterDate) {
        AfterDate = afterDate;
    }

    public String getOnDate() {
        return OnDate;
    }

    public void setOnDate(String onDate) {
        OnDate = onDate;
    }

    public String getBeforeDate() {
        return BeforeDate;
    }

    public void setBeforeDate(String beforeDate) {
        BeforeDate = beforeDate;
    }

    public String getActualDate() {
        return ActualDate;
    }

    public void setActualDate(String actualDate) {
        ActualDate = actualDate;
    }
}

package com.projects_kidscare.Bean;



public class Bean_Registration {

    private int RegID;
    private String Name;
    private String DOB;
    private String PicurePath;
    private byte[] ImgID;
    private int isGallery;

    public String getPicurePath() {
        return PicurePath;
    }

    public void setPicurePath(String picurePath) {
        PicurePath = picurePath;
    }

    public byte[] getImgID() {
        return ImgID;
    }

    public void setImgID(byte[] imgID) {
        ImgID = imgID;
    }

    public int getRegID() {
        return RegID;
    }

    public void setRegID(int regID) {
        RegID = regID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public int getIsGallery() {
        return isGallery;
    }

    public void setIsGallery(int isGallery) {
        this.isGallery = isGallery;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
}

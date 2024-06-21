package com.example.sqlinserttest;

import java.time.LocalDate;

public class applicant {
    private int id;
    private String name;
    private String scholarshipID;
    private String sex;
    private LocalDate birthDate;
    private String birthPlace;
    private String citizenship;
    private String dualCitizenshipStatus;
    private String contactNo;
    private String emailAddress;
    private String permAddress;
    private int noSiblings;
    private int birthOrder;
    private String course;
    private String uniName;
    private String uniAddress;
    private String passportStatus;

    // Constructor
    public applicant(int id, String name, String scholarshipID, String sex, LocalDate birthDate, String birthPlace,
                     String citizenship, String dualCitizenshipStatus, String contactNo, String emailAddress,
                     String permAddress, int noSiblings, int birthOrder, String course, String uniName,
                     String uniAddress, String passportStatus) {
        this.id = id;
        this.name = name;
        this.scholarshipID = scholarshipID;
        this.sex = sex;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.citizenship = citizenship;
        this.dualCitizenshipStatus = dualCitizenshipStatus;
        this.contactNo = contactNo;
        this.emailAddress = emailAddress;
        this.permAddress = permAddress;
        this.noSiblings = noSiblings;
        this.birthOrder = birthOrder;
        this.course = course;
        this.uniName = uniName;
        this.uniAddress = uniAddress;
        this.passportStatus = passportStatus;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScholarshipID() {
        return scholarshipID;
    }

    public void setScholarshipID(String scholarshipID) {
        this.scholarshipID = scholarshipID;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getDualCitizenshipStatus() {
        return dualCitizenshipStatus;
    }

    public void setDualCitizenshipStatus(String dualCitizenshipStatus) {
        this.dualCitizenshipStatus = dualCitizenshipStatus;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPermAddress() {
        return permAddress;
    }

    public void setPermAddress(String permAddress) {
        this.permAddress = permAddress;
    }

    public int getNoSiblings() {
        return noSiblings;
    }

    public void setNoSiblings(int noSiblings) {
        this.noSiblings = noSiblings;
    }

    public int getBirthOrder() {
        return birthOrder;
    }

    public void setBirthOrder(int birthOrder) {
        this.birthOrder = birthOrder;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public String getUniAddress() {
        return uniAddress;
    }

    public void setUniAddress(String uniAddress) {
        this.uniAddress = uniAddress;
    }

    public String getPassportStatus() {
        return passportStatus;
    }

    public void setPassportStatus(String passportStatus) {
        this.passportStatus = passportStatus;
    }
}

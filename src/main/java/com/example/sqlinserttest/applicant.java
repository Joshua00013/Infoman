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
        // Use encapsulation and assign the variables to the instance using a setter
        setId(id);
        setName(name);
        setScholarshipID(scholarshipID);
        setSex(sex);
        setBirthDate(birthDate);
        setBirthPlace(birthPlace);
        setCitizenship(citizenship);
        setDualCitizenshipStatus(dualCitizenshipStatus);
        setContactNo(contactNo);
        setEmailAddress(emailAddress);
        setPermAddress(permAddress);
        setNoSiblings(noSiblings);
        setBirthOrder(birthOrder);
        setCourse(course);
        setUniName(uniName);
        setUniAddress(uniAddress);
        setPassportStatus(passportStatus);
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScholarshipID() {
        return scholarshipID;
    }

    public String getSex() {
        return sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public String getDualCitizenshipStatus() {
        return dualCitizenshipStatus;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPermAddress() {
        return permAddress;
    }

    public int getNoSiblings() {
        return noSiblings;
    }

    public int getBirthOrder() {
        return birthOrder;
    }

    public String getCourse() {
        return course;
    }

    public String getUniName() {
        return uniName;
    }

    public String getUniAddress() {
        return uniAddress;
    }

    public String getPassportStatus() {
        return passportStatus;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScholarshipID(String scholarshipID) {
        this.scholarshipID = scholarshipID;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public void setDualCitizenshipStatus(String dualCitizenshipStatus) {
        this.dualCitizenshipStatus = dualCitizenshipStatus;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPermAddress(String permAddress) {
        this.permAddress = permAddress;
    }

    public void setNoSiblings(int noSiblings) {
        this.noSiblings = noSiblings;
    }

    public void setBirthOrder(int birthOrder) {
        this.birthOrder = birthOrder;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public void setUniAddress(String uniAddress) {
        this.uniAddress = uniAddress;
    }

    public void setPassportStatus(String passportStatus) {
        this.passportStatus = passportStatus;
    }
}

package com.example.sqlinserttest;

public class scholarships {
    private String scholarshipID;
    private String scholarshipName;

    public scholarships(String ID, String scholarshipName){
        setScholarshipID(ID);
        setScholarshipName(scholarshipName);
    }
    public String getScholarshipID() {
        return scholarshipID;
    }

    public void setScholarshipID(String scholarshipID) {
        this.scholarshipID = scholarshipID;
    }

    public String getScholarshipName() {
        return scholarshipName;
    }

    public void setScholarshipName(String scholarshipName) {
        this.scholarshipName = scholarshipName;
    }
}

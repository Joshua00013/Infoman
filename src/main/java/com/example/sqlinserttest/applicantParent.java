package com.example.sqlinserttest;

public class applicantParent {
    private int parentid;
    private int childid;
    private String parentname;
    private String relationship;
    private String education;
    private String occupation;
    private String employee;

    private String annualincome;



    public applicantParent(int parentid, int applicantId, String parentName, String education, String occupation, String employer, String income, String relation){
        setParentid(parentid);
        setChildid(applicantId);
        setParentname(parentName);
        setEducation(education);
        setOccupation(occupation);
        setEmployee(employer);
        setAnnualincome(income);
        setRelationship(relation);
    }
    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public int getChildid() {
        return childid;
    }

    public void setChildid(int childid) {
        this.childid = childid;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getAnnualincome() {
        return annualincome;
    }

    public void setAnnualincome(String annualincome) {
        this.annualincome = annualincome;
    }
}

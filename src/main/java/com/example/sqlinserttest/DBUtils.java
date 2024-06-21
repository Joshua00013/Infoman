package com.example.sqlinserttest;

import java.sql.*;

public class DBUtils {
    private static Connection connection;
    public static void establishConnection() {
        String url = "jdbc:mysql://localhost:3306/scholarshipdb";
        String user = "root";
        String password = pazz.myPassword; //insert password here

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //call the sql driver
            connection = DriverManager.getConnection(url, user, password); //establish connection to the database using these variables
            System.out.println("Connection is Successful to the database" + url); //console message to display if connection is successful

        } catch (ClassNotFoundException | SQLException e) { //display an error if connection is not successful
            e.printStackTrace();
            System.err.println("Error establishing connection to database");
        }
    }

    static int addApplicant(String name, String scholarshipID, String sex, String birthDate, String birthPlace,
                            String citizenship, String dualCitizenshipStatus, String contactNo, String emailAddress,
                            String permAddress, int noSiblings, int birthOrder, String course, String uniName,
                            String uniAddress, String passportStatus) {
        String query = "INSERT INTO applicant (Name, ScholarshipID, Sex, Birthday, BirthPlace, Citizenship, " +
                "DualCitizStatus, ContactNo, EmailAddress, PermAddress, NoSiblings, BirthOrder, Course, " +
                "UnivName, UnivAddress, PassportStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, scholarshipID);
            preparedStatement.setString(3, sex);
            preparedStatement.setDate(4, java.sql.Date.valueOf(birthDate)); // Convert LocalDate to java.sql.Date
            preparedStatement.setString(5, birthPlace);
            preparedStatement.setString(6, citizenship);
            preparedStatement.setString(7, dualCitizenshipStatus);
            preparedStatement.setString(8, contactNo);
            preparedStatement.setString(9, emailAddress);
            preparedStatement.setString(10, permAddress);
            preparedStatement.setInt(11, noSiblings);
            preparedStatement.setInt(12, birthOrder);
            preparedStatement.setString(13, course);
            preparedStatement.setString(14, uniName);
            preparedStatement.setString(15, uniAddress);
            preparedStatement.setString(16, passportStatus);

            preparedStatement.executeUpdate();
            System.out.println("Applicant added successfully!");

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int applicantId = generatedKeys.getInt(1);
                    return applicantId;
                } else {
                    throw new SQLException("Creating applicant failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    static void addParentDetails(int applicantId, FormController controller, String parentName, String education, String occupation, String income) {
        String query = "INSERT INTO parentguardian_info (ApplicantID, Relationship, ParentGuardianName, EducAttainment, Occupation, AnnualIncome) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        String relationship = null;

        // Determine parent name and relationship based on the text fields
        if (parentName != null) {
            if (parentName.equals(controller.getMotherName())) {
                relationship = "Mother";
            } else if (parentName.equals(controller.getFatherName())) {
                relationship = "Father";
            }
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, applicantId);
            preparedStatement.setString(2, relationship);
            preparedStatement.setString(3, parentName);
            preparedStatement.setString(4, education);
            preparedStatement.setString(5, occupation);
            preparedStatement.setString(6, income);

            preparedStatement.executeUpdate();
            System.out.println("Parent details added successfully!");
        } catch (SQLException e) {
            System.out.println("adding error has occured");
            e.printStackTrace();
        }
    }


    static void addParentDetails(int applicantId, FormController controller, String parentName, String education, String occupation, String income, String relationship) {
        String query = "INSERT INTO parentguardian_info (ApplicantID, Relationship, ParentGuardianName, EducAttainment, Occupation, AnnualIncome) " +
                "VALUES (?, ?, ?, ?, ?, ?)";


        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, applicantId);
            preparedStatement.setString(2, relationship);
            preparedStatement.setString(3, parentName);
            preparedStatement.setString(4, education);
            preparedStatement.setString(5, occupation);
            preparedStatement.setString(6, income);

            preparedStatement.executeUpdate();
            System.out.println("Parent details added successfully!");
        } catch (SQLException e) {
            System.out.println("adding error has occured");
            e.printStackTrace();
        }
    }

}
package com.example.sqlinserttest;

import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    public static void errorDialogue(String title, String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setContentText(message);
        alert.setHeaderText(title);
        alert.showAndWait();
    }
    public static void adminSuccessDialogue(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Information submitted");
        alert.setContentText("Information added successfully!");
        alert.showAndWait();
    }
    public static void successDialogue(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Information submitted");
        alert.setContentText("You have successfully applied!");
        alert.showAndWait();
    }
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
            errorDialogue("SQL Error", e.getMessage());
        }

    }
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error closing connection");
                errorDialogue("SQL Error", e.getMessage());
            }
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
            errorDialogue("SQL Error", e.getMessage());
            return -1;
        }
    }
    static void addParentDetails(int applicantId, FormController controller, String parentName, String education, String occupation, String employee, String income) {
        String query = "INSERT INTO parentguardian_info (ApplicantID, Relationship, ParentGuardianName, EducAttainment, Occupation, EmployeeName, AnnualIncome) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

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
            preparedStatement.setString(6, employee);
            preparedStatement.setString(7, income);

            preparedStatement.executeUpdate();
            System.out.println("Parent details added successfully!");
        } catch (SQLException e) {
            System.out.println("adding error has occured");
            e.printStackTrace();
            errorDialogue("SQL Error", e.getMessage());
        }
    }


    static void addParentDetails(int applicantId, FormController controller, String parentName, String education, String occupation, String employee, String income, String relationship) {
        String query = "INSERT INTO parentguardian_info (ApplicantID, Relationship, ParentGuardianName, EducAttainment, Occupation, EmployeeName, AnnualIncome) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";


        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, applicantId);
            preparedStatement.setString(2, relationship);
            preparedStatement.setString(3, parentName);
            preparedStatement.setString(4, education);
            preparedStatement.setString(5, occupation);
            preparedStatement.setString(6, employee);
            preparedStatement.setString(7, income);

            preparedStatement.executeUpdate();
            System.out.println("Parent details added successfully!");
        } catch (SQLException e) {
            System.out.println("adding error has occured");
            errorDialogue("SQL Error", e.getMessage());
            e.printStackTrace();
        }
    }
    public static List<applicant> getAllApplicants() {
        List<applicant> applicants = new ArrayList<>();
        String query = "SELECT * FROM applicant";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("ApplicantID");
                String name = resultSet.getString("Name");
                String scholarshipID = resultSet.getString("ScholarshipID");
                String sex = resultSet.getString("Sex");
                LocalDate birthDate = resultSet.getDate("Birthday").toLocalDate();
                String birthPlace = resultSet.getString("BirthPlace");
                String citizenship = resultSet.getString("Citizenship");
                String dualCitizenshipStatus = resultSet.getString("DualCitizStatus");
                String contactNo = resultSet.getString("ContactNo");
                String emailAddress = resultSet.getString("EmailAddress");
                String permAddress = resultSet.getString("PermAddress");
                int noSiblings = resultSet.getInt("NoSiblings");
                int birthOrder = resultSet.getInt("BirthOrder");
                String course = resultSet.getString("Course");
                String uniName = resultSet.getString("UnivName");
                String uniAddress = resultSet.getString("UnivAddress");
                String passportStatus = resultSet.getString("PassportStatus");

                applicant applicant1 = new applicant(id, name, scholarshipID, sex, birthDate, birthPlace,
                        citizenship, dualCitizenshipStatus, contactNo, emailAddress,
                        permAddress, noSiblings, birthOrder, course, uniName,
                        uniAddress, passportStatus);
                applicants.add(applicant1);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving applicants: " + e.getMessage());
            errorDialogue("SQL Error", e.getMessage());
            e.printStackTrace();
        }

        return applicants;
    }

    public static List<applicantParent> getAllParents() {
        List<applicantParent> parents = new ArrayList<>();
        String query = "SELECT * FROM parentguardian_info";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int parentid = resultSet.getInt("ParentGuardianID");
                System.out.println(parentid);
                int applicantid = resultSet.getInt("ApplicantID");
                String parentname = resultSet.getString("ParentGuardianName");
                String education = resultSet.getString("EducAttainment");
                String occupation = resultSet.getString("Occupation");
                String income = resultSet.getString("AnnualIncome");
                String employee = resultSet.getString("EmployeeName");
                String relation = resultSet.getString("Relationship");

                applicantParent parent1 = new applicantParent(parentid, applicantid, parentname, education, occupation, employee, income, relation);
                parents.add(parent1);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving applicants: " + e.getMessage());
            errorDialogue("SQL Error", e.getMessage());
            e.printStackTrace();
        }

        // Debugging to check the data retrieval
        System.out.println("Total parents retrieved from database: " + parents.size());
        for (applicantParent parent : parents) {
            System.out.println(parent);
        }

        return parents;
    }
    public static List<scholarships> getAllScholarships() {
        List<scholarships> Scholarships = new ArrayList<>();
        String query = "SELECT * FROM scholarshiptype";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String scholarshipid = resultSet.getString("ScholarshipID");
                String scholarshipname = resultSet.getString("Scholarshipname");


                scholarships scholarship1 = new scholarships(scholarshipid, scholarshipname);
                Scholarships.add(scholarship1);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving applicants: " + e.getMessage());
            errorDialogue("SQL Error", e.getMessage());
            e.printStackTrace();
        }

        return Scholarships;
    }

    public static void deleteApplicant(int applicantId) {
        String deleteApplicantSQL = "DELETE FROM applicant WHERE ApplicantID = ?";
        String deleteParentsSQL = "DELETE FROM parentguardian_info WHERE ApplicantID = ?";

        try {
            // Delete parents first
            try (PreparedStatement parentStatement = connection.prepareStatement(deleteParentsSQL)) {
                parentStatement.setInt(1, applicantId);
                int parentRowsDeleted = parentStatement.executeUpdate();

                if (parentRowsDeleted > 0) {
                    System.out.println("Parents of applicant with ID " + applicantId + " deleted successfully.");
                } else {
                    System.out.println("No parents found for applicant with ID " + applicantId + ". No parents deleted.");
                }
            }

            // Then delete the applicant
            try (PreparedStatement applicantStatement = connection.prepareStatement(deleteApplicantSQL)) {
                applicantStatement.setInt(1, applicantId);
                int applicantRowsDeleted = applicantStatement.executeUpdate();

                if (applicantRowsDeleted > 0) {
                    System.out.println("Applicant with ID " + applicantId + " deleted successfully.");
                } else {
                    System.out.println("No applicant found with ID " + applicantId + ". Deletion failed.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error deleting applicant with ID " + applicantId + ": " + e.getMessage());
            errorDialogue("SQL Error", e.getMessage());
        }
    }

    public static void deleteParent(int parentId) {
        String sql = "DELETE FROM parentguardian_info WHERE ParentGuardianID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, parentId);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Applicant parent with ID " + parentId + " deleted successfully.");
            } else {
                System.out.println("No applicant parent found with ID " + parentId + ". Deletion failed.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting applicant parent with ID " + parentId + ": " + e.getMessage());
            errorDialogue("SQL Error", e.getMessage());
        }
    }
    public static void updateApplicant(applicant updatedApplicant) {
        String sql = "UPDATE applicant SET Name = ?, ScholarshipID = ?, Sex = ?, " +
                "Birthday = ?, Birthplace = ?, Citizenship = ?, DualCitizStatus = ?, " +
                "ContactNo = ?, EmailAddress = ?, PermAddress = ?, NoSiblings = ?, " +
                "birthOrder = ?, Course = ?, UnivName = ?, UnivAddress = ?, PassportStatus = ? " +
                "WHERE ApplicantID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, updatedApplicant.getName());
            statement.setString(2, updatedApplicant.getScholarshipID());
            statement.setString(3, updatedApplicant.getSex());
            statement.setDate(4, java.sql.Date.valueOf(updatedApplicant.getBirthDate()));
            statement.setString(5, updatedApplicant.getBirthPlace());
            statement.setString(6, updatedApplicant.getCitizenship());
            statement.setString(7, updatedApplicant.getDualCitizenshipStatus());
            statement.setString(8, updatedApplicant.getContactNo());
            statement.setString(9, updatedApplicant.getEmailAddress());
            statement.setString(10, updatedApplicant.getPermAddress());
            statement.setInt(11, updatedApplicant.getNoSiblings());
            statement.setInt(12, updatedApplicant.getBirthOrder());
            statement.setString(13, updatedApplicant.getCourse());
            statement.setString(14, updatedApplicant.getUniName());
            statement.setString(15, updatedApplicant.getUniAddress());
            statement.setString(16, updatedApplicant.getPassportStatus());
            statement.setInt(17, updatedApplicant.getId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Applicant with ID " + updatedApplicant.getId() + " updated successfully.");
            } else {
                System.out.println("No applicant found with ID " + updatedApplicant.getId() + ". Update failed.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating applicant with ID " + updatedApplicant.getId() + ": " + e.getMessage());
            errorDialogue("SQL Error", e.getMessage());
        }
    }
    public static void updateParent(applicantParent updatedParent) {
        String sql = "UPDATE parentguardian_info SET ParentGuardianName = ?, EducAttainment = ?, " +
                "Occupation = ?,EmployeeName = ?, AnnualIncome = ? , Relationship = ? WHERE ParentGuardianID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, updatedParent.getParentname());
            statement.setString(2, updatedParent.getEducation());
            statement.setString(3, updatedParent.getOccupation());
            statement.setString(4, updatedParent.getEmployee());
            statement.setString(5, updatedParent.getAnnualincome());
            statement.setString(6, updatedParent.getRelationship());
            statement.setInt(7, updatedParent.getParentid());


            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Parent with ID " + updatedParent.getParentid() + " updated successfully.");
            } else {
                System.out.println("No parent found with ID " + updatedParent.getParentid() + ". Update failed.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating parent with ID " + updatedParent.getParentid() + ": " + e.getMessage());
            errorDialogue("SQL Error", e.getMessage());
        }
    }
    public static void InsertGuardianDetails(String parentID, String applicantID, String parentName, String education, String occupation, String employee, String income, String relationship) {
        String query = "INSERT INTO parentguardian_info (ParentGuardianID, ApplicantID, Relationship, ParentGuardianName, EducAttainment, Occupation, EmployeeName, AnnualIncome) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, parentID);
            preparedStatement.setString(2, applicantID);
            preparedStatement.setString(3, relationship);
            preparedStatement.setString(4, parentName);
            preparedStatement.setString(5, education);
            preparedStatement.setString(6, occupation);
            preparedStatement.setString(7, employee);
            preparedStatement.setString(8, income);

            preparedStatement.executeUpdate();
            System.out.println("Parent details added successfully!");
        } catch (SQLException e) {
            System.out.println("Error occurred while adding parent details");
            errorDialogue("SQL Error", e.getMessage());
            e.printStackTrace();
        }
    }
    public static void InsertParentDetails(String parentID, String applicantID, String parentName, String education, String occupation, String employee, String income, String relationship) {
        String query = "INSERT INTO parentguardian_info (ParentGuardianID, ApplicantID, Relationship, ParentGuardianName, EducAttainment, Occupation, EmployeeName, AnnualIncome)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, parentID);
            preparedStatement.setString(2, applicantID);
            preparedStatement.setString(3, relationship);
            preparedStatement.setString(4, parentName);
            preparedStatement.setString(5, education);
            preparedStatement.setString(6, occupation);
            preparedStatement.setString(7, employee);
            preparedStatement.setString(8, income);

            preparedStatement.executeUpdate();
            System.out.println("Parent details added successfully!");
        } catch (SQLException e) {
            System.out.println("Error occurred while adding parent details");
            errorDialogue("SQL Error", e.getMessage());
            e.printStackTrace();
        }
    }

    public static boolean CheckParent(String applicantID, String relationship) {
        String query = "SELECT COUNT(*) FROM parentguardian_info WHERE ApplicantID = ? AND Relationship = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, applicantID);
            preparedStatement.setString(2,relationship);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // Returns true if count is greater than 0
                }
            }
        } catch (SQLException e) {
            System.err.println("Error checking mother for applicant ID " + applicantID + ": " + e.getMessage());
            errorDialogue("SQL Error", e.getMessage());
            e.printStackTrace();
        }
        return false; // Return false if no mother found or in case of an error
    }

    public static boolean CheckGuardian(String applicantID) {
        String query = "SELECT COUNT(*) FROM parentguardian_info WHERE ApplicantID = ? AND Relationship NOT IN ('Father', 'Mother')";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, applicantID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // Returns true if count is greater than 0
                }
            }
        } catch (SQLException e) {
            System.err.println("Error checking mother for applicant ID " + applicantID + ": " + e.getMessage());
            errorDialogue("SQL Error", e.getMessage());
            e.printStackTrace();
        }
        return false; // Return false if no mother found or in case of an error
    }




}

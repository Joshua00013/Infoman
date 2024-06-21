package com.example.sqlinserttest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<applicant> applicantTable;

    @FXML
    private TableColumn<applicant, Integer> idCol;

    @FXML
    private TableColumn<applicant, String> scholarshipIdCol;

    @FXML
    private TableColumn<applicant, String> nameCol;

    @FXML
    private TableColumn<applicant, String> sexCol;

    @FXML
    private TableColumn<applicant, LocalDate> birthDateCol;

    @FXML
    private TableColumn<applicant, String> birthPlaceCol;

    @FXML
    private TableColumn<applicant, String> citizenshipCol;

    @FXML
    private TableColumn<applicant, String> dualCitizenshipStatusCol;

    @FXML
    private TableColumn<applicant, String> contactNoCol;

    @FXML
    private TableColumn<applicant, String> emailAddressCol;

    @FXML
    private TableColumn<applicant, String> permAddressCol;

    @FXML
    private TableColumn<applicant, Integer> noSiblingsCol;

    @FXML
    private TableColumn<applicant, Integer> birthOrderCol;

    @FXML
    private TableColumn<applicant, String> courseCol;

    @FXML
    private TableColumn<applicant, String> uniNameCol;

    @FXML
    private TableColumn<applicant, String> uniAddressCol;

    @FXML
    private TableColumn<applicant, String> passportStatusCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBUtils.establishConnection();
        populateApplicantsTable();

        // Enable TableView editing
        enableTableViewEditing();
    }

    public void switchToHomepage1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void populateApplicantsTable() {
        List<applicant> applicants = DBUtils.getAllApplicants();
        ObservableList<applicant> data = FXCollections.observableArrayList(applicants);

        applicantTable.setItems(data);

        // Configure cell value factories to bind columns to properties in Applicant
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        scholarshipIdCol.setCellValueFactory(new PropertyValueFactory<>("scholarshipID"));
        sexCol.setCellValueFactory(new PropertyValueFactory<>("sex"));
        birthDateCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        birthPlaceCol.setCellValueFactory(new PropertyValueFactory<>("birthPlace"));
        citizenshipCol.setCellValueFactory(new PropertyValueFactory<>("citizenship"));
        dualCitizenshipStatusCol.setCellValueFactory(new PropertyValueFactory<>("dualCitizenshipStatus"));
        contactNoCol.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        emailAddressCol.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        permAddressCol.setCellValueFactory(new PropertyValueFactory<>("permAddress"));
        noSiblingsCol.setCellValueFactory(new PropertyValueFactory<>("noSiblings"));
        birthOrderCol.setCellValueFactory(new PropertyValueFactory<>("birthOrder"));
        courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));
        uniNameCol.setCellValueFactory(new PropertyValueFactory<>("uniName"));
        uniAddressCol.setCellValueFactory(new PropertyValueFactory<>("uniAddress"));
        passportStatusCol.setCellValueFactory(new PropertyValueFactory<>("passportStatus"));
    }
    @FXML
    private void handleDeleteButton(ActionEvent event) {
        // Get selected item from TableView
        applicant selectedApplicant = applicantTable.getSelectionModel().getSelectedItem();

        if (selectedApplicant != null) {
            // Remove from TableView
            applicantTable.getItems().remove(selectedApplicant);

            // Remove from data source (assuming you have a method in DBUtils to delete from database)
            DBUtils.deleteApplicant(selectedApplicant.getId()); // Example method; implement this in DBUtils

            // Optionally, you may want to refresh the TableView to reflect the changes
            applicantTable.refresh();
        } else {
            // No selection warning or error handling
            System.out.println("No applicant selected for deletion.");
        }
    }

    private void enableTableViewEditing() {
        applicantTable.setEditable(true);

        // Example for making id column editable (though typically, IDs are not editable)
        idCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        idCol.setOnEditCommit(event -> {
            applicant editedApplicant = event.getRowValue();
            editedApplicant.setId(event.getNewValue());
            DBUtils.updateApplicant(editedApplicant);
            applicantTable.refresh();
        });

        // Example for making name column editable on double-click
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(event -> {
            applicant editedApplicant = event.getRowValue();
            editedApplicant.setName(event.getNewValue());
            DBUtils.updateApplicant(editedApplicant);
            applicantTable.refresh();
        });

        // Example for making scholarshipId column editable on double-click
        scholarshipIdCol.setCellFactory(TextFieldTableCell.forTableColumn());
        scholarshipIdCol.setOnEditCommit(event -> {
            applicant editedApplicant = event.getRowValue();
            editedApplicant.setScholarshipID(event.getNewValue());
            DBUtils.updateApplicant(editedApplicant);
            applicantTable.refresh();
        });

        // Example for making sex column editable on double-click
        sexCol.setCellFactory(TextFieldTableCell.forTableColumn());
        sexCol.setOnEditCommit(event -> {
            applicant editedApplicant = event.getRowValue();
            editedApplicant.setSex(event.getNewValue());
            DBUtils.updateApplicant(editedApplicant);
            applicantTable.refresh();
        });

        // Example for making birthDate column editable on double-click
        birthDateCol.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        birthDateCol.setOnEditCommit(event -> {
            applicant editedApplicant = event.getRowValue();
            editedApplicant.setBirthDate(event.getNewValue());
            DBUtils.updateApplicant(editedApplicant);
            applicantTable.refresh();
        });

        // Repeat the above pattern for each column
        birthPlaceCol.setCellFactory(TextFieldTableCell.forTableColumn());
        birthPlaceCol.setOnEditCommit(event -> {
            applicant editedApplicant = event.getRowValue();
            editedApplicant.setBirthPlace(event.getNewValue());
            DBUtils.updateApplicant(editedApplicant);
            applicantTable.refresh();
        });

        citizenshipCol.setCellFactory(TextFieldTableCell.forTableColumn());
        citizenshipCol.setOnEditCommit(event -> {
            applicant editedApplicant = event.getRowValue();
            editedApplicant.setCitizenship(event.getNewValue());
            DBUtils.updateApplicant(editedApplicant);
            applicantTable.refresh();
        });

        dualCitizenshipStatusCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dualCitizenshipStatusCol.setOnEditCommit(event -> {
            applicant editedApplicant = event.getRowValue();
            editedApplicant.setDualCitizenshipStatus(event.getNewValue());
            DBUtils.updateApplicant(editedApplicant);
            applicantTable.refresh();
        });

        contactNoCol.setCellFactory(TextFieldTableCell.forTableColumn());
        contactNoCol.setOnEditCommit(event -> {
            applicant editedApplicant = event.getRowValue();
            editedApplicant.setContactNo(event.getNewValue());
            DBUtils.updateApplicant(editedApplicant);
            applicantTable.refresh();
        });

        emailAddressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailAddressCol.setOnEditCommit(event -> {
            applicant editedApplicant = event.getRowValue();
            editedApplicant.setEmailAddress(event.getNewValue());
            DBUtils.updateApplicant(editedApplicant);
            applicantTable.refresh();
        });

        permAddressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        permAddressCol.setOnEditCommit(event -> {
            applicant editedApplicant = event.getRowValue();
            editedApplicant.setPermAddress(event.getNewValue());
            DBUtils.updateApplicant(editedApplicant);
            applicantTable.refresh();
        });

        noSiblingsCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        noSiblingsCol.setOnEditCommit(event -> {
            applicant editedApplicant = event.getRowValue();
            editedApplicant.setNoSiblings(event.getNewValue());
            DBUtils.updateApplicant(editedApplicant);
            applicantTable.refresh();
        });

        birthOrderCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        birthOrderCol.setOnEditCommit(event -> {
            applicant editedApplicant = event.getRowValue();
            editedApplicant.setBirthOrder(event.getNewValue());
            DBUtils.updateApplicant(editedApplicant);
            applicantTable.refresh();
        });

        courseCol.setCellFactory(TextFieldTableCell.forTableColumn());
        courseCol.setOnEditCommit(event -> {
            applicant editedApplicant = event.getRowValue();
            editedApplicant.setCourse(event.getNewValue());
            DBUtils.updateApplicant(editedApplicant);
            applicantTable.refresh();
        });

        uniNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        uniNameCol.setOnEditCommit(event -> {
            applicant editedApplicant = event.getRowValue();
            editedApplicant.setUniName(event.getNewValue());
            DBUtils.updateApplicant(editedApplicant);
            applicantTable.refresh();
        });

        uniAddressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        uniAddressCol.setOnEditCommit(event -> {
            applicant editedApplicant = event.getRowValue();
            editedApplicant.setUniAddress(event.getNewValue());
            DBUtils.updateApplicant(editedApplicant);
            applicantTable.refresh();
        });

        passportStatusCol.setCellFactory(TextFieldTableCell.forTableColumn());
        passportStatusCol.setOnEditCommit(event -> {
            applicant editedApplicant = event.getRowValue();
            editedApplicant.setPassportStatus(event.getNewValue());
            DBUtils.updateApplicant(editedApplicant);
            applicantTable.refresh();
        });

}


}

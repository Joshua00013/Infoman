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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    private double x = 0;
    private double y = 0;

    private Stage stage;
    private Scene scene;
    private Parent root;
    //Add a generic to tableview representing the applicant table. Declare it as the applicant object so it knows what types of objects it will hold
    @FXML
    private TableView<applicant> applicantTable;
    //Table column generic, <S, T> s= row item type t=cell item type
    // This is equivalent to TableColumn ID = new TableColumn("ID");
    // = new TableColumn<>("ID"); is omitted because it's directly injected via fxml. The program already knows its a new instance of TableColumn through fxml
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

    @FXML
    private ImageView closebtn;

    @FXML
    private ImageView mnmbtn1;


    @FXML
    void closeclick(MouseEvent event) {
        javafx.application.Platform.exit();
    }

    @FXML
    void mnmclick(MouseEvent event) {
        Stage stage = (Stage) mnmbtn1.getScene().getWindow();
        stage.setIconified(true);
    }
    @FXML
    private StackPane stackpane;

    @FXML
    void stackpane_dragged(MouseEvent event) {
        Stage stage = (Stage) stackpane.getScene().getWindow();
        stage.setY(event.getScreenY() - y);
        stage.setX(event.getScreenX() - x);
    }

    @FXML
    void stackpane_pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBUtils.establishConnection();
        populateApplicantsTable();
        populateParentsTable();
        populateScholarshipsTable();
        // Enable TableView editing by calling the function.
        enableApplicantTableEditing();
        enableParentTableEditing();
    }

    public void switchToHomepage1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToInputApplicant(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("adminInputApplicant.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToInputParent(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("adminInputParents.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void populateApplicantsTable() {
        List<applicant> applicants = DBUtils.getAllApplicants();
        ObservableList<applicant> data = FXCollections.observableArrayList(applicants);

        applicantTable.setItems(data);

        // Configure cell value factories to bind columns to variables in applicant.java
        // The left side corresponds to the initialized widgets (refer to the top part) like TableColumn<Applicant, Integer> idCol = new PropertyValueFactory<>("id");
        // setCellValueFactory will set the values of each cell within id column to become the values of the id within applicant
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
    private void applicantDeleteButton(ActionEvent event) {
        // Get selected item from TableView
        applicant selectedApplicant = applicantTable.getSelectionModel().getSelectedItem();


        if (selectedApplicant != null) {
            // Remove from TableView
            applicantTable.getItems().remove(selectedApplicant);


            // Remove from data source (assuming you have a method in DBUtils to delete from database)
            DBUtils.deleteApplicant(selectedApplicant.getId()); // Example method; implement this in DBUtils

            // Optionally, you may want to refresh the TableView to reflect the changes
            refreshButtonPressed();

        } else {
            // No selection warning or error handling
            System.out.println("No applicant selected for deletion.");
        }
    }

    @FXML
    void parentDeleteButton(ActionEvent event) {
        applicantParent selectedParent = parenttable.getSelectionModel().getSelectedItem();
        if (selectedParent != null) {
            // Remove from TableView
            parenttable.getItems().remove(selectedParent);

            // Remove from data source (assuming you have a method in DBUtils to delete from database)
            DBUtils.deleteParent(selectedParent.getParentid()); // Example method; implement this in DBUtils

            // Optionally, you may want to refresh the TableView to reflect the changes
            refreshButtonPressed();
            parenttable.refresh();
        } else {
            // No selection warning or error handling
            System.out.println("No applicant parent selected for deletion.");
        }
    }

    private void enableApplicantTableEditing() {  // APPLICANT DETAIL INSERT
        applicantTable.setEditable(true);
        idCol.setEditable(false);

        //SET CELL FACTORY EXPLANATION
        // it returns the string 'Callback<TableColumn<S, T>, TableCell<S, T>>' where S is the attribute and T is the date type
        //
        // nameCol.setCellFactory(new Callback<TableColumn<Applicant, String>, TableCell<Applicant, String>>() {
        //    @Override
        //    public TableCell<Applicant, String> call(TableColumn<Applicant, String> param) {
        //        return new TextFieldTableCell<>();
        //    }
        //});
        //tldr: change the cells of name column as text field cells to be edited, .forTableColumn means it sets every table within that column


        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(event -> {
            applicant editedApplicant = event.getRowValue();      //get the position of the applicant table to be edited
            editedApplicant.setName(event.getNewValue());         //call a setter for changing the name of the applicant
            DBUtils.updateApplicant(editedApplicant);             //call the update applicant function in DBUtils class
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

    @FXML
    private TableView<applicantParent> parenttable;

    @FXML
    private TableColumn<applicantParent, Integer> parentidcol;
    @FXML
    private TableColumn<applicantParent, Integer> applicantidcol;
    @FXML
    private TableColumn<applicantParent, String> parentnamecol;
    @FXML
    private TableColumn<applicantParent, String> relationcol;
    @FXML
    private TableColumn<applicantParent, String> educcol;
    @FXML
    private TableColumn<applicantParent, String> occucol;
    @FXML
    private TableColumn<applicantParent, String> incomecol;

    private void populateParentsTable() { // INSERT TABLE
        List<applicantParent> parents = DBUtils.getAllParents();
        ObservableList<applicantParent> data = FXCollections.observableArrayList(parents);

        parenttable.setItems(data);

        parentidcol.setCellValueFactory(new PropertyValueFactory<>("parentid"));
        applicantidcol.setCellValueFactory(new PropertyValueFactory<>("childid"));
        parentnamecol.setCellValueFactory(new PropertyValueFactory<>("parentname"));
        relationcol.setCellValueFactory(new PropertyValueFactory<>("relationship"));
        educcol.setCellValueFactory(new PropertyValueFactory<>("education"));
        occucol.setCellValueFactory(new PropertyValueFactory<>("occupation"));
        incomecol.setCellValueFactory(new PropertyValueFactory<>("annualincome"));
    }
    private void enableParentTableEditing() {
        parenttable.setEditable(true);

        parentnamecol.setCellFactory(TextFieldTableCell.forTableColumn());
        parentnamecol.setOnEditCommit(event -> {
            applicantParent editedParent = event.getRowValue();
            editedParent.setParentname(event.getNewValue());
            DBUtils.updateParent(editedParent); // Implement this method in DBUtils
            parenttable.refresh();
        });

        relationcol.setCellFactory(TextFieldTableCell.forTableColumn());
        relationcol.setOnEditCommit(event -> {
            applicantParent editedParent = event.getRowValue();
            editedParent.setRelationship(event.getNewValue());
            DBUtils.updateParent(editedParent); // Implement this method in DBUtils
            parenttable.refresh();
        });

        educcol.setCellFactory(TextFieldTableCell.forTableColumn());
        educcol.setOnEditCommit(event -> {
            applicantParent editedParent = event.getRowValue();
            editedParent.setEducation(event.getNewValue());
            DBUtils.updateParent(editedParent); // Implement this method in DBUtils
            parenttable.refresh();
        });

        occucol.setCellFactory(TextFieldTableCell.forTableColumn());
        occucol.setOnEditCommit(event -> {
            applicantParent editedParent = event.getRowValue();
            editedParent.setOccupation(event.getNewValue());
            DBUtils.updateParent(editedParent); // Implement this method in DBUtils
            parenttable.refresh();
        });

        incomecol.setCellFactory(TextFieldTableCell.forTableColumn());
        incomecol.setOnEditCommit(event -> {
            applicantParent editedParent = event.getRowValue();
            editedParent.setAnnualincome(event.getNewValue());
            DBUtils.updateParent(editedParent); // Implement this method in DBUtils
            parenttable.refresh();
        });
    }

    @FXML
    private TableView<scholarships> scholarshipTable;
    @FXML
    private TableColumn<scholarships,String> schIDCol;
    @FXML
    private TableColumn<scholarships,String> schNameCol;

    private void populateScholarshipsTable(){
        List<scholarships> Scholarships = DBUtils.getAllScholarships();
        ObservableList<scholarships> data = FXCollections.observableArrayList(Scholarships);

        scholarshipTable.setItems(data);

        schIDCol.setCellValueFactory(new PropertyValueFactory<>("scholarshipID"));
        schNameCol.setCellValueFactory(new PropertyValueFactory<>("scholarshipName"));
    }
    @FXML
    void refreshButtonPressed(ActionEvent event) {
        applicantTable.getItems().clear();
        parenttable.getItems().clear();
        scholarshipTable.getItems().clear();

        populateApplicantsTable();
        populateParentsTable();
        populateScholarshipsTable();
    }

    void refreshButtonPressed() {
        applicantTable.getItems().clear();
        parenttable.getItems().clear();
        scholarshipTable.getItems().clear();

        populateApplicantsTable();
        populateParentsTable();
        populateScholarshipsTable();
    }


}
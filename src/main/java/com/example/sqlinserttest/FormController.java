package com.example.sqlinserttest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FormController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tab1; // Reference to the first tab

    @FXML
    private Tab tab2; // Reference to the second tab


    @FXML
    void switchToTab2(ActionEvent event) {
        tabPane.getSelectionModel().select(tab2);
    }

    @FXML
    void switchToTab1FromTab2(ActionEvent event) {
        tabPane.getSelectionModel().select(tab1);
    }

    public void switchToHomepage1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToForm2(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("formguiPage2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void switchToForm1(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("formgui.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TextField name;

    @FXML
    private ChoiceBox<String> scholarshipChoiceBox;
    private String[] scholarshipop = {"RA 10612","RA 7687","MERIT"};

    @FXML
    private ChoiceBox<String> sexChoiceBox;
    private final String[] sexop = {"M","F"};

    @FXML
    private DatePicker birthdayPicker;

    // Additional fields and methods
    @FXML
    private TextField birthOrder;

    @FXML
    private TextField birthplace;

    @FXML
    private TextField citizenship;

    @FXML
    private TextField contactno;

    @FXML
    private TextField course;

    @FXML
    private ChoiceBox<String> dualCitizStatusChoiceBox;
    private final String[] dualCitizStatusop = {"Yes","No"};

    @FXML
    private TextField email;

    @FXML
    private TextField fEdu;

    @FXML
    private TextField fIncome;

    @FXML
    private TextField fName;

    @FXML
    private TextField fOccu;

    @FXML
    private TextField fEmployee;

    @FXML
    private TextField gEdu;

    @FXML
    private TextField gIncome;

    @FXML
    private TextField gName;

    @FXML
    private TextField gOccu;

    @FXML
    private TextField gEmployee;

    @FXML
    private TextField gRelation;

    @FXML
    private TextField mEdu;

    @FXML
    private TextField mIncome;

    @FXML
    private TextField mName;

    @FXML
    private TextField mOccu;

    @FXML
    private TextField mEmployee;

    @FXML
    private ChoiceBox<String> passport;
    private String[] passportop = {"Yes","No"};

    @FXML
    private TextField permAddress;

    @FXML
    private TextField siblings;

    @FXML
    private TextField uniAddress;

    @FXML
    private TextField uniname;

    @FXML
    private ImageView closebtn;

    @FXML
    private ImageView mnmbtn;

    @FXML
    private ImageView mxmbtn;

    @FXML
    void closeclick(MouseEvent event) {
        javafx.application.Platform.exit();
    }

    @FXML
    void mnmclick(MouseEvent event) {
        Stage stage = (Stage) mnmbtn.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void mxmclick(MouseEvent event) {
        Stage stage = (Stage) mxmbtn.getScene().getWindow();
        if (stage.isMaximized()){
            stage.setMaximized(false);
        }else {stage.setMaximized(true);
        }
    }


    @FXML
    void submit(ActionEvent event) {
        try {
            DBUtils.establishConnection();

            String selectedScholarship = scholarshipChoiceBox.getValue();
            String selectedSex = sexChoiceBox.getValue();
            LocalDate selectedBirthday = birthdayPicker.getValue();

            int applicantID = DBUtils.addApplicant(name.getText(), selectedScholarship, selectedSex, selectedBirthday.toString(),
                    birthplace.getText(), citizenship.getText(), dualCitizStatusChoiceBox.getValue(),
                    contactno.getText(), email.getText(), permAddress.getText(),
                    Integer.parseInt(siblings.getText()), Integer.parseInt(birthOrder.getText()), course.getText(),
                    uniname.getText(), uniAddress.getText(), passport.getValue());

            DBUtils.addParentDetails(applicantID, this, mName.getText(), mEdu.getText(), mOccu.getText(),mEmployee.getText() ,mIncome.getText());
            DBUtils.addParentDetails(applicantID, this, fName.getText(), fEdu.getText(), fOccu.getText(),fEmployee.getText(), fIncome.getText());

            if (!gEdu.getText().isEmpty()) {
                DBUtils.addParentDetails(applicantID, this, gName.getText(), gEdu.getText(), gOccu.getText(),gEmployee.getText(), gIncome.getText(), gRelation.getText());
            }
            DBUtils.successDialogue();
            clearFields();
            DBUtils.closeConnection();
        }catch (NullPointerException e){
            DBUtils.errorDialogue("Null Pointer Exception", "Please fill up all the required fields!");
        }

    }
    private void clearFields() {
        name.clear();
        scholarshipChoiceBox.getSelectionModel().clearSelection();
        sexChoiceBox.getSelectionModel().clearSelection();
        birthdayPicker.setValue(null);
        birthplace.clear();
        citizenship.clear();
        dualCitizStatusChoiceBox.getSelectionModel().clearSelection();
        contactno.clear();
        email.clear();
        fEdu.clear();
        fIncome.clear();
        fName.clear();
        fOccu.clear();
        fEmployee.clear();
        gEdu.clear();
        gIncome.clear();
        gName.clear();
        gOccu.clear();
        gEmployee.clear();
        gRelation.clear();
        mEdu.clear();
        mIncome.clear();
        mName.clear();
        mOccu.clear();
        mEmployee.clear();
        passport.getSelectionModel().clearSelection();
        permAddress.clear();
        siblings.clear();
        uniAddress.clear();
        uniname.clear();
        course.clear();
        birthOrder.clear();
    }


    public String getMotherName() {
        return mName.getText();
    }

    public String getFatherName() {
        return fName.getText();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        scholarshipChoiceBox.getItems().addAll(scholarshipop);
        sexChoiceBox.getItems().addAll(sexop);
        dualCitizStatusChoiceBox.getItems().addAll(dualCitizStatusop);
        passport.getItems().addAll(passportop);
        tabPane.getStyleClass().add("tab-pane-no-header");
    }
}

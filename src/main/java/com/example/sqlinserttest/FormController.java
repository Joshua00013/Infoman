package com.example.sqlinserttest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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

    public void switchToHomepage1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
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
    private String[] sexop = {"M","F"};

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
    private String[] dualCitizStatusop = {"Yes","No"};

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
    private TextField gEdu;

    @FXML
    private TextField gIncome;

    @FXML
    private TextField gName;

    @FXML
    private TextField gOccu;

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
    void submit(MouseEvent event) {
        HelloApplication application = new HelloApplication();
        application.establishConnection();

        String selectedScholarship = scholarshipChoiceBox.getValue();
        String selectedSex = sexChoiceBox.getValue();
        LocalDate selectedBirthday = birthdayPicker.getValue();

        int applicantID = application.addApplicant(name.getText(), selectedScholarship, selectedSex, selectedBirthday.toString(),
                birthplace.getText(), citizenship.getText(), dualCitizStatusChoiceBox.getValue().toString(),
                contactno.getText(), email.getText(), permAddress.getText(),
                Integer.parseInt(siblings.getText()), Integer.parseInt(birthOrder.getText()), course.getText(),
                uniname.getText(), uniAddress.getText(), passport.getValue().toString());

        application.addParentDetails(applicantID, this,mName.getText(), mEdu.getText(), mOccu.getText(), mIncome.getText());
        application.addParentDetails(applicantID, this,fName.getText(),fEdu.getText(), fOccu.getText(), fIncome.getText());

        if (!gEdu.getText().isEmpty()) {
            application.addParentDetails(applicantID, this, gName.getText(), gEdu.getText(), gOccu.getText(), gIncome.getText(), gRelation.getText());
        }
        // clearFields(); create a clearfields function
    }


    public String getMotherName() {
        return mName.getText();
    }

    public String getFatherName() {
        return fName.getText();
    }

    public String getGuardianName() {
        return gName.getText();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        scholarshipChoiceBox.getItems().addAll(scholarshipop);
        sexChoiceBox.getItems().addAll(sexop);
        dualCitizStatusChoiceBox.getItems().addAll(dualCitizStatusop);
        passport.getItems().addAll(passportop);
    }
}

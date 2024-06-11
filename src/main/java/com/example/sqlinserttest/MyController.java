package com.example.sqlinserttest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MyController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private Text nameLabel;

    @FXML
    private ChoiceBox<String> scholarshipChoiceBox;
    private String[] scholarshipop = {"RA 10612","RA 7687","MERIT"};
    @FXML
    private Text scholarshipLabel;

    @FXML
    private ChoiceBox<String> sexChoiceBox;
    private String[] sexop = {"M","F"};
    @FXML
    private Text sexLabel;

    @FXML
    private DatePicker birthdayPicker;
    @FXML
    private Text birthdayLabel;

    // Additional fields and methods
    @FXML
    private TextField birthOrder;
    @FXML
    private Text birthOrderLabel;

    @FXML
    private TextField birthplace;
    @FXML
    private Text birthplaceLabel;

    @FXML
    private TextField citizenship;
    @FXML
    private Text citizenshipLabel;

    @FXML
    private TextField contactno;
    @FXML
    private Text contactnoLabel;

    @FXML
    private TextField course;
    @FXML
    private Text courseLabel;

    @FXML
    private ChoiceBox<String> dualCitizStatusChoiceBox;
    private String[] dualCitizStatusop = {"Yes","No"};
    @FXML
    private Text dualCitizStatusLabel;

    @FXML
    private TextField email;
    @FXML
    private Text emailLabel;

    @FXML
    private TextField fEdu;
    @FXML
    private Text fEduLabel;

    @FXML
    private TextField fIncome;
    @FXML
    private Text fIncomeLabel;

    @FXML
    private TextField fName;
    @FXML
    private Text fNameLabel;

    @FXML
    private TextField fOccu;
    @FXML
    private Text fOccuLabel;

    @FXML
    private TextField gEdu;
    @FXML
    private Text gEduLabel;

    @FXML
    private TextField gIncome;
    @FXML
    private Text gIncomeLabel;

    @FXML
    private TextField gName;
    @FXML
    private Text gNameLabel;

    @FXML
    private TextField gOccu;
    @FXML
    private Text gOccuLabel;

    @FXML
    private TextField mEdu;
    @FXML
    private Text mEduLabel;

    @FXML
    private TextField mIncome;
    @FXML
    private Text mIncomeLabel;

    @FXML
    private TextField mName;
    @FXML
    private Text mNameLabel;

    @FXML
    private TextField mOccu;
    @FXML
    private Text mOccuLabel;

    @FXML
    private ChoiceBox<String> passport;
    private String[] passportop = {"Yes","No"};
    @FXML
    private Text passportLabel;

    @FXML
    private TextField permAddress;
    @FXML
    private Text permAddressLabel;

    @FXML
    private TextField siblings;
    @FXML
    private Text siblingsLabel;

    @FXML
    private TextField uniAddress;
    @FXML
    private Text uniAddressLabel;

    @FXML
    private TextField uniname;
    @FXML
    private Text uninameLabel;

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
            application.addParentDetails(applicantID, this, fName.getText(), gEdu.getText(), gOccu.getText(), gIncome.getText());
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

package com.example.sqlinserttest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class AdminInputParent {

    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close(); // Close the stage
    }


    @FXML
    void closeclick(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    private ImageView mnmbtn;

    @FXML
    private ImageView mxmbtn;

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
    private TextField mName;

    @FXML
    private TextField mEdu;

    @FXML
    private TextField mOccu;

    @FXML
    private TextField mEmployee;

    @FXML
    private TextField mIncome;

    @FXML
    private TextField fName;

    @FXML
    private TextField fEdu;

    @FXML
    private TextField fOccu;

    @FXML
    private TextField fEmployee;

    @FXML
    private TextField fIncome;

    @FXML
    private TextField gName;

    @FXML
    private TextField gEdu;

    @FXML
    private TextField gOccu;

    @FXML
    private TextField gEmployee;

    @FXML
    private TextField gIncome;

    @FXML
    private TextField gRelation;
    @FXML
    private TextField mID;
    @FXML
    private TextField fID;
    @FXML
    private TextField gID;
    @FXML
    private TextField applicantID;

    @FXML
    void submit(ActionEvent event) {
        DBUtils.establishConnection();

        if (!mID.getText().isEmpty()) {
            DBUtils.InsertParentDetails(mID.getText(), applicantID.getText(), mName.getText(), mEdu.getText(), mOccu.getText(),mEmployee.getText(), mIncome.getText(), "Mother");
        }
        if (!fID.getText().isEmpty()) {
            DBUtils.InsertParentDetails(fID.getText(), applicantID.getText(), fName.getText(), fEdu.getText(), fOccu.getText(),fEmployee.getText(), fIncome.getText(), "Father");
        }
        if (!gEdu.getText().isEmpty()) {
            DBUtils.InsertGuardianDetails(gID.getText(), applicantID.getText(), gName.getText(), gEdu.getText(), gOccu.getText(),gEmployee.getText(), gIncome.getText(), gRelation.getText());
        }

    }
}

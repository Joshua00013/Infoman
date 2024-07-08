package com.example.sqlinserttest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AdminInputParentController {

    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close(); // Close the stage
    }

    private double x = 0;
    private double y = 0;

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


    @FXML
    private ImageView mnmbtn;

    @FXML
    void mnmclick(MouseEvent event) {
        Stage stage = (Stage) mnmbtn.getScene().getWindow();
        stage.setIconified(true);
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
        boolean insertionSuccess = false;

        if (!mID.getText().isEmpty()) {
            if (!DBUtils.CheckParent(applicantID.getText(), "Mother")) {
                DBUtils.InsertParentDetails(mID.getText(), applicantID.getText(), mName.getText(), mEdu.getText(), mOccu.getText(), mEmployee.getText(), mIncome.getText(), "Mother");
                insertionSuccess = true;
            } else {
                DBUtils.errorDialogue("Error!", "Applicant already has an existing mother!");
            }
        }

        if (!fID.getText().isEmpty()) {
            if (!DBUtils.CheckParent(applicantID.getText(), "Father")) {
                DBUtils.InsertParentDetails(fID.getText(), applicantID.getText(), fName.getText(), fEdu.getText(), fOccu.getText(), fEmployee.getText(), fIncome.getText(), "Father");
                insertionSuccess = true;
            } else {
                DBUtils.errorDialogue("Error!", "Applicant already has an existing father!");
            }
        }

        if (!gEdu.getText().isEmpty()) {
            if (!DBUtils.CheckGuardian(applicantID.getText())) {
                DBUtils.InsertGuardianDetails(gID.getText(), applicantID.getText(), gName.getText(), gEdu.getText(), gOccu.getText(), gEmployee.getText(), gIncome.getText(), gRelation.getText());
                insertionSuccess = true;
            } else {
                DBUtils.errorDialogue("Error!", "Applicant already has an existing guardian!");
            }
        }

        if (insertionSuccess) {
            DBUtils.adminSuccessDialogue();
        }
    }

    @FXML
    public void checkInt(KeyEvent event) {
        // Get the character that was typed
        String character = event.getCharacter();

        // Check if the character is not a digit
        if (!character.matches("[0-9.]")) {
            // If not a digit, prevent the character from being added to the text
            TextField textField = (TextField) event.getSource();
            int caretPosition = textField.getCaretPosition();
            int anchor = textField.getAnchor();

            // Remove the typed character from the text
            String text = textField.getText();
            if (caretPosition > 0 && caretPosition <= text.length()) {
                String newText = text.substring(0, caretPosition - 1) + text.substring(caretPosition);
                textField.setText(newText);
                textField.positionCaret(anchor - 1);
            }
        }
    }
}

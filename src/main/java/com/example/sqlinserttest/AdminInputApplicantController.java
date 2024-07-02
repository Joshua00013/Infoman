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
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminInputApplicantController implements Initializable {


    private double x = 0;
    private double y = 0;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToAdmin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private TextField name;

    @FXML
    private ChoiceBox<String> scholarshipChoiceBox;
    private String[] scholarshipop = {"RA 10612", "RA 7687", "MERIT"};

    @FXML
    private ChoiceBox<String> sexChoiceBox;
    private final String[] sexop = {"M", "F"};

    @FXML
    private DatePicker birthdayPicker;

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
    void closeclick(MouseEvent event) {
        javafx.application.Platform.exit();
    }
    @FXML
    private ImageView mnmbtn;

    @FXML
    void mnmclick(MouseEvent event) {
        Stage stage = (Stage) mnmbtn.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private ChoiceBox<String> dualCitizStatusChoiceBox;
    private final String[] dualCitizStatusop = {"Yes", "No"};

    @FXML
    private TextField email;

    @FXML
    private ChoiceBox<String> passport;
    private String[] passportop = {"Yes", "No"};

    @FXML
    private TextField permAddress;
    @FXML
    private TextField siblings;
    @FXML
    private TextField uniAddress;

    @FXML
    private TextField uniname;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        scholarshipChoiceBox.getItems().addAll(scholarshipop);
        sexChoiceBox.getItems().addAll(sexop);
        dualCitizStatusChoiceBox.getItems().addAll(dualCitizStatusop);
        passport.getItems().addAll(passportop);
    }

    @FXML
    void submitApplicant(ActionEvent event) {
        try {
            DBUtils.establishConnection();

            String selectedScholarship = scholarshipChoiceBox.getValue();
            String selectedSex = sexChoiceBox.getValue();
            LocalDate selectedBirthday = birthdayPicker.getValue();

            int applicantId = DBUtils.addApplicant(name.getText(), selectedScholarship, selectedSex, selectedBirthday.toString(),
                    birthplace.getText(), citizenship.getText(), dualCitizStatusChoiceBox.getValue(),
                    contactno.getText(), email.getText(), permAddress.getText(),
                    Integer.parseInt(siblings.getText()), Integer.parseInt(birthOrder.getText()), course.getText(),
                    uniname.getText(), uniAddress.getText(), passport.getValue());

            if (applicantId != -1) {
                System.out.println("Applicant added successfully with ID: " + applicantId);
            } else {
                System.out.println("Failed to add applicant.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid number format for Birth Order.");
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection();
        }
    }
}

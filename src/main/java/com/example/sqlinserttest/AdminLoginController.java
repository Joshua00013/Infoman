package com.example.sqlinserttest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class AdminLoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView closebtn;

    @FXML
    private ImageView mnmbtn;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordTextfield;

    @FXML
    private TextField userTextfield;

    @FXML
    private StackPane stackpane;

    private double y = 0;
    private double x = 0;

    @FXML
    public void closeclick(MouseEvent event) {
        javafx.application.Platform.exit();
    }

    @FXML
    public void mnmclick(MouseEvent event) {
        Stage stage = (Stage) mnmbtn.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void stackpane_dragged(MouseEvent event) {
        Stage stage = (Stage) stackpane.getScene().getWindow();
        stage.setY(event.getScreenY() - y);
        stage.setX(event.getScreenX() - x);
    }

    @FXML
    public void stackpane_pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    public void switchToAdmin1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHomepage1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void checkLogin(ActionEvent event) throws IOException {
        String username = userTextfield.getText();
        String password = passwordTextfield.getText();

        if ("admin".equals(username) && "password".equals(password)) {
            switchToAdmin1(event);
        } else {
            showErrorAlert("Error", "Invalid username or password!");
        }
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

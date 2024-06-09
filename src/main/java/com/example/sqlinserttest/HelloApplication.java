package com.example.sqlinserttest;

import com.example.sqlinserttest.pazz;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HelloApplication extends Application {

    private Connection connection;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        // Establish database connection
        establishConnection();

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        Button addButton = new Button("Add Student");

        addButton.setOnAction(event -> {
            String name = nameField.getText();
            if (!name.isEmpty()) {
                // Insert student into the database
                addStudent(name);
                nameField.clear();
            }
        });

        VBox root = new VBox(10, nameLabel, nameField, addButton);
        root.setPrefSize(320, 240);
        stage.setTitle("Hello!");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void establishConnection() {
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = pazz.myPassword; //insert password here

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //call the sql driver
            connection = DriverManager.getConnection(url, user, password); //establish connection to the database using these variables
            System.out.println("Connection is Successful to the database" + url); //console message to display if connection is successful

        } catch (ClassNotFoundException | SQLException e) { //display an error if connection is not successful
            e.printStackTrace();
        }
    }

    private void addStudent(String name) {
        String query = "INSERT INTO student (Name) VALUES (?)"; // Value is set to ? as  sql placeholder

        // set the object preparedStatement's value as the compiled version (for sql) of the variable query statement
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name); // set the first placeholder '?' as variable name
            preparedStatement.executeUpdate(); // execute the statement into the database
            System.out.println("Student added successfully!"); //console message to indicate if student is added
        } catch (SQLException e) { //display an error if there's an exception with the statement
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        // Close the connection when the application stops
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

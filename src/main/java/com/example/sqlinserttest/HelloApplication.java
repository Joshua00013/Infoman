package com.example.sqlinserttest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage mainstage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("formgui.fxml"));
        mainstage.setScene(new Scene(root));
        mainstage.show();
    }


}

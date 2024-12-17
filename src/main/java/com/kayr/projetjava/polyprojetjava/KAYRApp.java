package com.kayr.projetjava.polyprojetjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class KAYRApp extends Application {

    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) {
        Database.init();
        Splash splash = new Splash();
        splash.show();
        this.stage = stage;
        stage.setScene(splash.getSplashScene());
        splash.getAnimation().setOnFinished(e -> {
            Label label = new Label("Bienvenue dans la nouvelle fenêtre !");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login_form.fxml"));
                GridPane root = loader.load();
                Scene newScene = new Scene(root, 800, 600);
                stage.setScene(newScene);
                stage.setTitle("Page de connexion");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
//
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

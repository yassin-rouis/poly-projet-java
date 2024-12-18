package com.kayr.projetjava.polyprojetjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) {
        Splash splash = new Splash();
        splash.show();
        this.stage = stage;
        stage.setScene(splash.getSplashScene());
        splash.getAnimation().setOnFinished(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login_form.fxml"));
                GridPane root = loader.load();
                Database.init();
                Scene newScene = new Scene(root, 800, 600);
                stage.setScene(newScene);
                stage.setTitle("Page de connexion");
            } catch (Exception ex) {
                AlertWindow.showAlert(Alert.AlertType.ERROR, stage.getScene().getWindow(), "Oups...",
                        "Il y a un problème de connexion avec la base... sinon vérifiez les driver.");
            }

        });
//
        stage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}

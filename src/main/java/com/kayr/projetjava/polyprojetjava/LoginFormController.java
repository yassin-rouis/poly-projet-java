package com.kayr.projetjava.polyprojetjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    boolean loggedin = false;

    public Button gotoRegister;
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws SQLException {
        Window owner = submitButton.getScene().getWindow();
        if (emailField.getText().isEmpty()) {
            AlertWindow.showAlert(Alert.AlertType.ERROR, owner, "Formulaire incorrecte!",
                    "Entrez votre email");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            AlertWindow.showAlert(Alert.AlertType.ERROR, owner, "Formulaire incorrecte!",
                    "Entrez votre mot de passe");
            return;
        }

        if(Database.login(emailField.getText(), passwordField.getText())) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("interface.fxml"));
                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                GridPane root = loader.load();
                Scene newScene = new Scene(root, 800, 600);
                stage.setScene(newScene);
                stage.setTitle("Accueil | K.A.");
                Interface i = loader.getController();
                i.refresh();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            AlertWindow.showAlert(Alert.AlertType.ERROR, owner, "Oups...",
                    "Nom d'utilisateur ou mot de passe incorrect");
        }
    }

    public void gotoRegister(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registration_form.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            GridPane root = loader.load();
            Scene newScene = new Scene(root, 800, 600);
            stage.setScene(newScene);
            stage.setTitle("Page d'enregistrement");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
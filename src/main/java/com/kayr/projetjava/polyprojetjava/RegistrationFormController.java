package com.kayr.projetjava.polyprojetjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;

public class RegistrationFormController {

    public TextField nameField;
    public TextField emailField;
    public PasswordField passwordField;
    public Button submitButton;
    public Button gotoLogin;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws SQLException {
        Window owner = submitButton.getScene().getWindow();
        if (nameField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formulaire incorrecte!",
                    "Entrez votre nom");
            return;
        }
        if (emailField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formulaire incorrecte!",
                    "Entrez votre email");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formulaire incorrecte!",
                    "Entrez votre mot de passe");
            return;
        }

        if(Database.registerUser(emailField.getText(), passwordField.getText(), nameField.getText()) != 0) {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Créé",
                    "Votre compte est créé !");
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
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Oups...",
                    "Une erreur est survenue...");
        }
    }

    public void gotoLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login_form.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            GridPane root = loader.load();
            Scene newScene = new Scene(root, 800, 600);
            stage.setScene(newScene);
            stage.setTitle("Page de connexion");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
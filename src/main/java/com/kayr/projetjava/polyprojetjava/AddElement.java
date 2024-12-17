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

public class AddElement {
    public Button ajouterButton;
    public TextField articleName;
    public TextField articleCategorie;
    public PasswordField articlePrix;
    public Button annulerButton;

    @FXML
    protected void ajouterArticle(ActionEvent event) throws SQLException {
        Window owner = ajouterButton.getScene().getWindow();
        if(articleName.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formulaire incorrecte!",
                    "Entrez le nom de l'article !");
            return;
        }
        if(articleCategorie.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formulaire incorrecte!",
                    "Entrez la categorie !");
            return;
        }
        if(articlePrix.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formulaire incorrecte!",
                    "Entrez le prix de l'article !");
            return;
        }
        try {
            if(Double.parseDouble(articlePrix.getText()) < 0) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formulaire incorrecte!",
                        "Le prix ne peut être négatif !");
                return;
            }
        } catch (NumberFormatException e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Formulaire incorrecte!",
                    "Le format du prix est invalide !");
            return;
        }

        if(articlePrix.getText().isEmpty()) {
            Database.registerArticle(articleName.getText(), Double.parseDouble(articlePrix.getText()));
        } else {
            Database.registerArticle(articleName.getText(), Double.parseDouble(articlePrix.getText()), articleCategorie.getText());
        }

        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Succès !",
                "L'article " + articleName.getText() + " a été ajouté");
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    public void fermer(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
package com.kayr.projetjava.polyprojetjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.sql.SQLException;

public class AddArticleController {
    public Button ajouterButton;
    public TextField articleName;
    public TextField articleCategorie;
    public TextField articlePrix;
    public Button annulerButton;

    @FXML
    protected void ajouterArticle(ActionEvent event) throws SQLException {
        Window owner = ajouterButton.getScene().getWindow();
        if(articleName.getText().isEmpty()) {
            AlertWindow.showAlert(Alert.AlertType.ERROR, owner, "Formulaire incorrecte!",
                    "Entrez le nom de l'article !");
            return;
        }
        if(articleCategorie.getText().isEmpty()) {
            AlertWindow.showAlert(Alert.AlertType.ERROR, owner, "Formulaire incorrecte!",
                    "Entrez la categorie !");
            return;
        }
        if(articlePrix.getText().isEmpty()) {
            AlertWindow.showAlert(Alert.AlertType.ERROR, owner, "Formulaire incorrecte!",
                    "Entrez le prix de l'article !");
            return;
        }
        try {
            if(Double.parseDouble(articlePrix.getText()) < 0) {
                AlertWindow.showAlert(Alert.AlertType.ERROR, owner, "Formulaire incorrecte!",
                        "Le prix ne peut être négatif !");
                return;
            }
        } catch (NumberFormatException e) {
            AlertWindow.showAlert(Alert.AlertType.ERROR, owner, "Formulaire incorrecte!",
                    "Le format du prix est invalide !");
            return;
        }

        if(articleCategorie.getText().isEmpty()) {
            Database.registerArticle(articleName.getText(), Double.parseDouble(articlePrix.getText()));
        } else {
            Database.registerArticle(articleName.getText(), Double.parseDouble(articlePrix.getText()), articleCategorie.getText());
        }

        AlertWindow.showAlert(Alert.AlertType.CONFIRMATION, owner, "Succès !",
                "L'article " + articleName.getText() + " a été ajouté");
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    public void fermer(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
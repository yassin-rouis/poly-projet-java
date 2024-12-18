package com.kayr.projetjava.polyprojetjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Interface {

    public Button addElement;
    public GridPane grid;
    public Label label_accueil;
    public Button refreshList;

    public void addElement(ActionEvent event) {
        Window owner = grid.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add_element.fxml"));
        try {
            GridPane root = loader.load();
            Scene newScene = new Scene(root, 800, 600);
            Stage stage = new Stage();
            stage.setTitle("Ajouter un élément");
            stage.setScene(newScene);
            stage.setScene(newScene);
            stage.setTitle("Ajouter un élément");
            stage.setOnCloseRequest(e -> {
                try {
                    refresh();
                } catch (SQLException ex) {
                    AlertWindow.showAlert(Alert.AlertType.ERROR, owner, "Oups...",
                            "Erreur dans la base de donnée...");
                }
            });
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void refresh() throws SQLException {
        Window owner = grid.getScene().getWindow();
        List<Article> list = Database.getArticles();
        if(Database.isAdmin()) {
            grid.getChildren().removeIf(n -> n.getId() == null || !(
                    n.getId().equalsIgnoreCase("addElement") || n.getId().startsWith("label_") || n.getId().equalsIgnoreCase("refreshList")
            ));
        } else {
            grid.getChildren().removeIf(n -> n.getId() == null || !(
                    n.getId().startsWith("label_") || n.getId().equalsIgnoreCase("refreshList")
            ));
        }
        int i = 0;
        for (Article a : list) {
            Label name = new Label(a.name());
            Label prix = new Label(a.prix().toString() + " TND");
            Label cat = new Label(a.categorie());

            name.getStyleClass().add("article");
            prix.getStyleClass().add("article");
            cat.getStyleClass().add("article");

            grid.add(name, 0, i+4);
            grid.add(prix, 1, i+4);
            grid.add(cat, 2, i+4);
            if(Database.isAdmin()) {
                Button supprimer = new Button("Supprimer");

                supprimer.setOnAction(e -> {
                    try {
                        Database.removeArticle(a.id());
                        refresh();
                    } catch (Exception ex) {
                        AlertWindow.showAlert(Alert.AlertType.ERROR, owner, "Oups...",
                                "Erreur dans la base de donnée...");
                    }
                });
                grid.add(supprimer, 3, i+4);
            }
            i+=1;
        }
    }


    public void refreshList(ActionEvent event) throws SQLException {
        Window owner = grid.getScene().getWindow();
        try {
            refresh();
        } catch (Exception ex) {
            AlertWindow.showAlert(Alert.AlertType.ERROR, owner, "Oups...",
                    "Erreur dans la base de donnée...");
        }
    }
}
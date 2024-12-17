package com.kayr.projetjava.polyprojetjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Interface {

    public Button addElement;
    public GridPane grid;
    public Label label_accueil;

    public void addElement(ActionEvent event) {
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
                    throw new RuntimeException(ex);
                }
            });
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void refresh() throws SQLException {
        List<Article> list = Database.getArticles();
        grid.getChildren().removeIf(n -> n.getId() == null || !(n.getId().equalsIgnoreCase("addElement") || n.getId().startsWith("label_")));
        int i = 0;
        for (Article a : list) {
            Label name = new Label(a.name());
            Label prix = new Label(a.prix().toString() + " TND");
            Label cat = new Label(a.categorie());
            Button supprimer = new Button("Supprimer");

            supprimer.setOnAction(e -> {
                try {
                    Database.removeArticle(a.id());
                    refresh();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });

            name.getStyleClass().add("article");
            prix.getStyleClass().add("article");
            cat.getStyleClass().add("article");

            grid.add(name, 0, i+4);
            grid.add(prix, 1, i+4);
            grid.add(cat, 2, i+4);
            grid.add(supprimer, 3, i+4);
            i+=1;
        }
    }
}
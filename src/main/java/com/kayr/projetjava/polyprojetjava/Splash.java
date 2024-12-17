package com.kayr.projetjava.polyprojetjava;
import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * This is my own splash screen, that I made myself.
 *
 */
public class Splash
{

    static Scene splash;
    static Rectangle rect = new Rectangle();
    final private Pane pane;
    final private FadeTransition animation;

    public Splash()
    {
        pane = new Pane();
        pane.setStyle("-fx-background-color:#F5EDEA");

        splash = new Scene(pane, 800, 600);
        animation = new FadeTransition();
    }

    public void show()
    {
        Image image = new Image("/ka.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(400);
        imageView.setX(200);
        imageView.setY(100);
        imageView.setPreserveRatio(true);

        FadeTransition ft = new FadeTransition(Duration.seconds(1), imageView);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);

        ft.setOnFinished(a -> animation.play());

        ft.play();

        animation.setDuration(Duration.seconds(1));
        animation.setNode(imageView);
        animation.setFromValue(1);
        animation.setToValue(0);
        animation.setCycleCount(1);
        animation.setAutoReverse(false);


        //now adding everything to position, opening the stage, start the animation
        pane.getChildren().addAll(rect, imageView);
    }

    public Scene getSplashScene()
    {
        return splash;
    }

    public FadeTransition getAnimation()
    {
        return animation;
    }
}
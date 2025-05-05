package fr.amu.iut.exercice5;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle extends Rectangle {

    public Obstacle(double largeur, double hauteur) {
        super(largeur, hauteur);
        this.setFill(Color.RED);
    }
}
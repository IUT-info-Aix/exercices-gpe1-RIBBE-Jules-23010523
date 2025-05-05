package fr.amu.iut.exercice5;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class Personnage extends Group {
    protected final static double LARGEUR_MOITIE_PERSONNAGE = 10;
    protected final static double LARGEUR_PERSONNAGE = LARGEUR_MOITIE_PERSONNAGE * 2;
    private final Circle corps;
    private String direction;

    public Personnage(String direction, Color couleurContour, Color couleurRemplissage) {
        this.direction = direction;
        corps = new Circle(10, 10, LARGEUR_MOITIE_PERSONNAGE, couleurContour);
        corps.setFill(couleurRemplissage);
        getChildren().add(corps);
    }

    public void deplacerAGauche() {
        double oldX = getLayoutX();
        double oldY = getLayoutY();
        if (getLayoutX() >= LARGEUR_PERSONNAGE) {
            setLayoutX(getLayoutX() - LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("gauche")) {
            direction = "gauche";
        }
        for (javafx.scene.Node node : getParent().getChildrenUnmodifiable()) {
            if (node instanceof Obstacle && this.getBoundsInParent().intersects(node.getBoundsInParent())) {
                setLayoutX(oldX);
                setLayoutY(oldY);
            }
        }
    }

    public void deplacerADroite(double largeurJeu) {
        double oldX = getLayoutX();
        double oldY = getLayoutY();
        if (getLayoutX() < largeurJeu - LARGEUR_PERSONNAGE) {
            setLayoutX(getLayoutX() + LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("droite")) {
            direction = "droite";
        }
        for (javafx.scene.Node node : getParent().getChildrenUnmodifiable()) {
            if (node instanceof Obstacle && this.getBoundsInParent().intersects(node.getBoundsInParent())) {
                setLayoutX(oldX);
                setLayoutY(oldY);
            }
        }
    }

    public void deplacerEnBas(double hauteurJeu) {
        double oldX = getLayoutX();
        double oldY = getLayoutY();
        if (getLayoutY() < hauteurJeu - LARGEUR_PERSONNAGE) {
            setLayoutY(getLayoutY() + LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("bas")) {
            direction = "bas";
        }
        for (javafx.scene.Node node : getParent().getChildrenUnmodifiable()) {
            if (node instanceof Obstacle && this.getBoundsInParent().intersects(node.getBoundsInParent())) {
                setLayoutX(oldX);
                setLayoutY(oldY);
            }
        }
    }

    public void deplacerEnHaut() {
        double oldX = getLayoutX();
        double oldY = getLayoutY();
        if (getLayoutY() >= LARGEUR_PERSONNAGE) {
            setLayoutY(getLayoutY() - LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("haut")) {
            direction = "haut";
        }
        for (javafx.scene.Node node : getParent().getChildrenUnmodifiable()) {
            if (node instanceof Obstacle && this.getBoundsInParent().intersects(node.getBoundsInParent())) {
                setLayoutX(oldX);
                setLayoutY(oldY);
            }
        }
    }

    boolean estEnCollision(Personnage autrePersonnage) {
        return getBoundsInParent().contains(autrePersonnage.getBoundsInParent())
                || autrePersonnage.getBoundsInParent().contains(getBoundsInParent());
    }

}

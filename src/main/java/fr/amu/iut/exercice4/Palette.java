package fr.amu.iut.exercice4;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Palette extends Application {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Label label;
    private Pane panneau;
    private HBox boutonsBox;

    @Override
    public void start(Stage primaryStage) throws Exception {

        root = new BorderPane();

        // Création de la HBox pour le label
        HBox topBox = new HBox();
        topBox.setAlignment(Pos.CENTER);
        label = new Label();
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 30;");
        topBox.getChildren().add(label);
        root.setTop(topBox);

        panneau = new Pane();
        panneau.setPrefSize(400, 200);

        // Création de la HBox pour les boutons
        boutonsBox = new HBox(10);
        boutonsBox.setAlignment(Pos.CENTER);
        boutonsBox.setSpacing(10);
        boutonsBox.setPadding(new javafx.geometry.Insets(10, 5, 10, 5));
        boutonsBox.getChildren().addAll(new Button("Vert"), new Button("Rouge"), new Button("Bleu"));
        root.setCenter(panneau);
        root.setBottom(boutonsBox);

        vert = (Button) boutonsBox.getChildren().get(0);
        rouge = (Button) boutonsBox.getChildren().get(1);
        bleu = (Button) boutonsBox.getChildren().get(2);

        vert.setOnAction(e -> {
            nbVert = nbVert + 10;
            label.setText("Vert choisi " + nbVert + " fois : ");
            panneau.setStyle("-fx-background-color: rgb(" + nbRouge + ","+ nbVert + "," + nbBleu + ");");
        });
        rouge.setOnAction(e -> {
            nbRouge = nbRouge + 10;
            label.setText("Rouge choisi " + nbRouge + " fois : ");
            panneau.setStyle("-fx-background-color: rgb(" + nbRouge + ","+ nbVert + "," + nbBleu + ");");
        });
        bleu.setOnAction(e -> {
            nbBleu = nbBleu + 10;
            label.setText("Bleu choisi " + nbBleu + " fois : ");
            panneau.setStyle("-fx-background-color: rgb(" + nbRouge + ","+ nbVert + "," + nbBleu + ");");
        });

        primaryStage.setResizable(false);
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);
        primaryStage.setTitle("Palette");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
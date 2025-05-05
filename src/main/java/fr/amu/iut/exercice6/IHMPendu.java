package fr.amu.iut.exercice6;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import fr.amu.iut.exercice6.Dico;
import javafx.scene.control.Alert;
import javafx.application.Platform;

public class IHMPendu extends Application {

    private String motATrouver;
    private char[] motCache;
    private final int[] vies = {7};
    private Label labelMot;
    private Label labelVies;
    private GridPane clavier;
    private final Dico dico = new Dico();
    private GraphicsContext gc;
    private Canvas canvas;
    private int partiesGagnees = 0;
    private Label compteurLabel;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Jeu du pendu");
        primaryStage.setWidth(500);
        primaryStage.setHeight(550);

        labelVies = new Label();
        labelVies.setFont(new Font("Arial", 20));
        labelVies.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-alignment: center;");

        labelMot = new Label();
        labelMot.setFont(new Font("Consolas", 26));
        labelMot.setStyle("-fx-text-fill: black;");

        compteurLabel = new Label("Parties gagnées : 0");
        compteurLabel.setFont(new Font("Arial", 14));
        compteurLabel.setStyle("-fx-text-fill: black;");
        compteurLabel.setVisible(false);
        VBox compteurBox = new VBox(compteurLabel);
        compteurBox.setAlignment(Pos.TOP_RIGHT);

        canvas = new Canvas(150, 200);
        gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(4);
        // Potence simple
        gc.strokeLine(30, 180, 30, 20); // vertical
        gc.strokeLine(30, 20, 100, 20); // horizontal
        gc.strokeLine(30, 20, 50, 40); // diagonale

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #d4fcf4;");
        layout.getChildren().add(compteurBox);
        layout.getChildren().addAll(canvas, labelVies, labelMot);

        clavier = new GridPane();
        clavier.setHgap(5);
        clavier.setVgap(5);
        clavier.setAlignment(Pos.CENTER);

        Button rejouer = new Button("Rejouer");
        VBox.setMargin(rejouer, new javafx.geometry.Insets(10, 0, 0, 0));
        // Style bouton "Rejouer" plus visible
        rejouer.setStyle("-fx-background-color: #d4fcf4; -fx-text-fill: #fca17d; -fx-border-color: #f0caa5; -fx-border-radius: 10; -fx-background-radius: 10;");

        layout.getChildren().addAll(clavier, rejouer);

        initialiserPartie();

        rejouer.setOnAction(e -> initialiserPartie());

        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void initialiserPartie() {
        motATrouver = dico.getMot();
        motCache = new char[motATrouver.length()];
        for (int i = 0; i < motCache.length; i++) motCache[i] = '*';
        vies[0] = 7;
        labelVies.setText("Nombre de vies : " + vies[0]);
        mettreAJourMotLabel();
        clavier.getChildren().clear();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(4);
        gc.strokeLine(75, 150, 75, 20);            // barre verticale
        gc.strokeLine(75, 20, 125, 20); // barre horizontale
        gc.strokeLine(55, 150, 95, 150); // support du pendu
        gc.strokeLine(75, 40, 110, 20); // diagonale

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < alphabet.length(); i++) {
            Button btn = new Button(String.valueOf(alphabet.charAt(i)));
            btn.setPrefSize(40, 40);
            btn.setStyle("-fx-background-color: #e7f6f2; -fx-border-color: #f0caa5; -fx-border-radius: 10; -fx-background-radius: 10;");
            char lettre = alphabet.charAt(i);
            btn.setOnAction(e -> gererLettre(btn, lettre));
            clavier.add(btn, i % 9, i / 9);
        }
    }

    private void disableButtons(GridPane clavier) {
        for (javafx.scene.Node node : clavier.getChildren()) {
            if (node instanceof Button) {
                node.setDisable(true);
            }
        }
    }

    private ArrayList<Integer> getPositions(char lettre, String mot) {
        ArrayList<Integer> positions = new ArrayList<>();
        for (int i = 0; i < mot.length(); i++) {
            if (mot.charAt(i) == lettre) {
                positions.add(i);
            }
        }
        return positions;
    }

    private boolean motEstTrouve() {
        for (char c : motCache) {
            if (c == '*') return false;
        }
        return true;
    }

    private void mettreAJourMotLabel() {
        labelMot.setText(String.valueOf(motCache));
    }

    private void traiterErreur() {
        vies[0]--;
        labelVies.setText("Nombre de vies : " + vies[0]);
        dessinerPendu();
        if (vies[0] == 0) {
            labelMot.setText(motATrouver);
            disableButtons(clavier);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Perdu !");
            alert.setHeaderText(null);
            alert.setContentText("Dommage ! Le mot était : " + motATrouver + "\n" + "Tié tarpin nul frataga arrache ta tante");
            alert.setOnHidden(e -> Platform.exit());
            alert.show();
        }
    }

    private void gererLettre(Button btn, char lettre) {
        btn.setDisable(true);
        ArrayList<Integer> positions = getPositions(lettre, motATrouver);
        if (positions.isEmpty()) {
            traiterErreur();
        } else {
            for (int pos : positions) {
                motCache[pos] = lettre;
            }
            mettreAJourMotLabel();
            if (motEstTrouve()) {
                disableButtons(clavier);
                partiesGagnees++;
                if (!compteurLabel.isVisible()) {
                    compteurLabel.setVisible(true);
                }
                compteurLabel.setText("Parties gagnées : " + partiesGagnees);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Gagné !");
                alert.setHeaderText(null);
                alert.setContentText("Bravo, vous avez trouvé le mot !" + "\n" + "Tié un monstre brozer");
                alert.setOnHidden(e -> initialiserPartie());
                alert.show();
            }
        }
    }

    private void dessinerPendu() {
        switch (vies[0]) {
            case 6 -> gc.strokeOval(115, 40, 20, 20); // tête
            case 5 -> gc.strokeLine(125, 60, 125, 100); // corps
            case 4 -> gc.strokeLine(125, 70, 105, 90); // bras gauche
            case 3 -> gc.strokeLine(125, 70, 145, 90); // bras droit
            case 2 -> gc.strokeLine(125, 100, 105, 130); // jambe gauche
            case 1 -> gc.strokeLine(125, 100, 145, 130); // jambe droite
            case 0 -> {
                gc.setFill(Color.PINK);
                gc.fillOval(115, 40, 20, 20); // tête rouge
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

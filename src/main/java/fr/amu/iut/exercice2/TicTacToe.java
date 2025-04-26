package fr.amu.iut.exercice2;

        import javafx.application.Application;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.image.ImageView;
        import javafx.scene.layout.GridPane;
        import javafx.stage.Stage;

        public class TicTacToe extends Application {

            private boolean isPlayerOne = true; // true pour Croix, false pour Rond
            private Label[][] board = new Label[3][3];

            @Override
            public void start(Stage primaryStage) {
                primaryStage.setTitle("Tic Tac Toe");

                // Création du GridPane
                GridPane grid = new GridPane();

                // Remplissage du GridPane avec des Labels affichant l'image Vide.png
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        Label label = new Label();
                        ImageView imageView = new ImageView(getClass().getResource("/exercice2/Vide.png").toExternalForm());
                        label.setGraphic(imageView);
                        label.setPrefSize(100, 100);
                        label.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

                        int finalRow = row;
                        int finalCol = col;
                        label.setOnMouseClicked(event -> {
                            ImageView currentImage = (ImageView) label.getGraphic();
                            if (currentImage.getImage().getUrl().contains("Vide.png")) {
                                if (isPlayerOne) {
                                    label.setGraphic(new ImageView(getClass().getResource("/exercice2/Croix.png").toExternalForm()));
                                } else {
                                    label.setGraphic(new ImageView(getClass().getResource("/exercice2/Rond.png").toExternalForm()));
                                }
                                if (checkWinner()) {
                                    System.out.println((isPlayerOne ? "Joueur 2" : "Joueur 1") + " a gagné !");
                                    disableBoard();
                                } else {
                                    isPlayerOne = !isPlayerOne;
                                }
                            }
                        });

                        grid.add(label, col, row);
                        board[row][col] = label;
                    }
                }

                // Création de la scène
                Scene scene = new Scene(grid, 300, 300);
                primaryStage.setResizable(false);
                primaryStage.setScene(scene);
                primaryStage.show();
            }

            private boolean checkWinner() {
                for (int i = 0; i < 3; i++) {
                    // Vérification lignes
                    if (sameSymbol(board[i][0], board[i][1], board[i][2])) return true;
                    // Vérification colonnes
                    if (sameSymbol(board[0][i], board[1][i], board[2][i])) return true;
                }
                // Vérification diagonales
                if (sameSymbol(board[0][0], board[1][1], board[2][2])) return true;
                if (sameSymbol(board[0][2], board[1][1], board[2][0])) return true;
                return false;
            }

            private boolean sameSymbol(Label l1, Label l2, Label l3) {
                if (l1.getGraphic() == null || l2.getGraphic() == null || l3.getGraphic() == null) return false;
                String url1 = ((ImageView) l1.getGraphic()).getImage().getUrl();
                String url2 = ((ImageView) l2.getGraphic()).getImage().getUrl();
                String url3 = ((ImageView) l3.getGraphic()).getImage().getUrl();
                return !url1.contains("Vide.png") && url1.equals(url2) && url2.equals(url3);
            }

            private void disableBoard() {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        board[i][j].setDisable(true);
                    }
                }
            }

            public static void main(String[] args) {
                launch(args);
            }
        }
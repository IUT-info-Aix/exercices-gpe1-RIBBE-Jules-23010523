package fr.amu.iut.exercice1;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

import static java.io.File.separator;

public class FenetreLogiciel extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Premier exemple manipulant les conteneurs");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setResizable(false);

        // Création des menus
        MenuItem New = new MenuItem("New");
        New.setOnAction(e -> System.out.println("New clicked"));

        MenuItem Open = new MenuItem("Open");
        Open.setOnAction(e -> System.out.println("Open clicked"));

        MenuItem Save = new MenuItem("Save");
        Save.setOnAction(e -> System.out.println("Save clicked"));

        MenuItem Close = new MenuItem("Close");
        Close.setOnAction(e -> primaryStage.close());


        MenuItem Cut = new MenuItem("Cut");
        Cut.setOnAction(e -> System.out.println("Cut clicked"));

        MenuItem Copy = new MenuItem("Copy");
        Copy.setOnAction(e -> System.out.println("Copy clicked"));

        MenuItem Paste = new MenuItem("Paste");
        Paste.setOnAction(e -> System.out.println("Paste clicked"));

        Menu file = new Menu("File");
        file.getItems().addAll(New, new SeparatorMenuItem(), Open, new SeparatorMenuItem(), Save, new SeparatorMenuItem(), Close);

        Menu edit = new Menu("Edit");
        edit.getItems().addAll(Cut, new SeparatorMenuItem(),  Copy, new SeparatorMenuItem(), Paste);

        Menu help = new Menu("Help");

        MenuBar menuBar = new MenuBar(file, edit, help);

        // Création du conteneur principal
        BorderPane root = new BorderPane();
        root.setTop(menuBar);

        // Création d’un menu latéral gauche
        VBox sideMenu = new VBox(15);
        sideMenu.setStyle("-fx-padding: 20;");

        Separator verticalSeparator = new Separator();
        verticalSeparator.setOrientation(Orientation.VERTICAL);


        Button dashboardBtn = new Button("Dashboard");
        Button settingsBtn = new Button("Settings");
        Button logoutBtn = new Button("Logout");

        dashboardBtn.setMaxWidth(Double.MAX_VALUE);
        settingsBtn.setMaxWidth(Double.MAX_VALUE);
        logoutBtn.setMaxWidth(Double.MAX_VALUE);

        sideMenu.getChildren().addAll(dashboardBtn, settingsBtn, logoutBtn);

        // Ajout du menu latéral à gauche avec un séparateur vertical
        HBox leftSection = new HBox(sideMenu, verticalSeparator);
        root.setLeft(leftSection);

        // Création de la scène
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Joon
 */
public class App extends Application {

    final String FILENAME = "./src/res/nbaplayers.txt";
    final String TEAM1DEFAULT = "Damian Lillard";
    final String TEAM2DEFAULT = "Chris Paul";

    private String category = "Points";
    private ComboBox categories;
        private ObservableList<String> cats
            = FXCollections.observableArrayList(
                    "Points",
                    "3 Pointers Made",
                    "Rebounds",
                    "Assists",
                    "Steals",
                    "Blocks",
                    "FG%",
                    "FT%",
                    "Turnovers",
                    "Minutes Played"
            );
    
    
    
    private ObservableList<String> players; // list that will be added into combo boxes

    private String[] team1PlayersChosen = new String[13]; // array of players that are chosen from team 1
    private String[] team2PlayersChosen = new String[13]; // array of players that are chosen from team 2

    //combo boxes
    private ComboBox[] team1Players = new ComboBox[13];
    private ComboBox[] team2Players = new ComboBox[13];
    private int index = 0;

    private VBox main = new VBox();
    private HBox top = new HBox();
    private HBox topInfo = new HBox();

    private Graph graph = new Graph();

    private FlowPane leftInfo = new FlowPane();
    private FlowPane rightInfo = new FlowPane();

    private GridPane topLeft = new GridPane();
    private GridPane topRight = new GridPane();
    private HBox bottom = new HBox();
    private VBox bottomLeft = new VBox(10);

    private void createTopInfo() {

        Text team1 = new Text("Team 1");
        team1.setFont(Font.font("Courier", FontWeight.BOLD, 15));
        Text team2 = new Text("Team 2");
        team2.setFont(Font.font("Courier", FontWeight.BOLD, 15));

        leftInfo.getChildren().add(team1);
        rightInfo.getChildren().add(team2);
    }

    private void createComboBox() throws IOException {

        FileInputStream fis; // Object that obtains information from text file.
        InputStreamReader inStream; // Object that reads and decodes information from text file.
        BufferedReader reader; //  Object that reads the text from the InputStreamReader.
        ArrayList<String> players = new ArrayList<>(); // String that stores the information contained in the text file.
        players.add("");

        try {
            fis = new FileInputStream(FILENAME);
            inStream = new InputStreamReader(fis);
            reader = new BufferedReader(inStream);

            String nextLine = reader.readLine();
            int index = 0;
            while (nextLine != null) {
                players.add(nextLine);
                nextLine = reader.readLine();
            }

            fis.close();
            inStream.close();
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file with path: " + FILENAME + " exists.");
        }

        // sort the players in the array list
        Collections.sort(players, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });

        this.players = FXCollections.observableArrayList(players);
        // initilize each combo box to have player information
        initializeComboBoxes();

        placeComboBoxesOnScreen();

    }

    private void placeComboBoxesOnScreen() {
        for (int i = 0; i < 13; i++) {
            topLeft.add(team1Players[i], i % 2, i / 2);
            team1Players[i].setPrefWidth(300);
            topRight.add(team2Players[i], i % 2, i / 2);
            team2Players[i].setPrefWidth(300);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Button createGraphButton = new Button("Create Graph");
        CreateGraph createHandler = new CreateGraph();
        initializeCategories();

        
        bottom.setPadding(new Insets(5,5,5,5));
        bottomLeft.getChildren().addAll(createGraphButton, categories);
        bottom.getChildren().addAll(bottomLeft, graph);
        createGraphButton.setOnAction(createHandler);

        team1PlayersChosen[0] = TEAM1DEFAULT;
        team2PlayersChosen[0] = TEAM2DEFAULT;

        intializePlayerArrays();
        // initialize combo boxes
        createComboBox();
        createTopInfo();

        top.setPadding(new Insets(5, 5, 5, 5));
        topLeft.setPadding(new Insets(5, 5, 5, 5));
        topRight.setPadding(new Insets(5, 5, 5, 5));

        top.getChildren().addAll(topLeft, topRight);
        topInfo.getChildren().addAll(leftInfo, rightInfo);
        main.getChildren().addAll(topInfo, top, bottom);
        main.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(main, 800, 650);

        topLeft.prefWidthProperty().bind(scene.widthProperty().divide(2));
        topRight.prefWidthProperty().bind(scene.widthProperty().divide(2));
        leftInfo.prefWidthProperty().bind(scene.widthProperty().divide(2));
        rightInfo.prefWidthProperty().bind(scene.widthProperty().divide(2));

        primaryStage.setScene(scene);
        primaryStage.setTitle("NBA Fantasy");
        primaryStage.show();

    }

    private void initializeCategories() {
        categories = new ComboBox(cats);
//        categories.setMinWidth(35);
        categories.setPromptText("Points");
        categories.setOnAction(e -> {
            category = categories.getSelectionModel().getSelectedItem().toString();
        });
        
    }

    private void initializeComboBoxes() {
        // team1's combo boxes. 13 players
        for (int i = 0; i < 13; i++) {
            team1Players[i] = new ComboBox(players);
            team1Players[i].setEditable(true);
            team1Players[i].setMinWidth(55);
            team1Players[index].setOnAction(e -> {
                String chosen = team1Players[index].getSelectionModel().getSelectedItem().toString();
                team1PlayersChosen[index] = chosen;

            });

            team2Players[i] = new ComboBox(players);
            team2Players[i].setEditable(true);
            team2Players[i].setMinWidth(55);
            team2Players[index].setOnAction(e -> {
                String chosen = team2Players[index].getSelectionModel().getSelectedItem().toString();
                team2PlayersChosen[index] = chosen;
            });

            index++;
        }
        index = 0;

        team1Players[0].setPromptText(TEAM1DEFAULT);
        team2Players[0].setPromptText(TEAM2DEFAULT);

    }

    private void intializePlayerArrays() {
        // initialize player arrays to empty strings
        for (int i = 1; i < 13; i++) {
            team1PlayersChosen[i] = "";
            team2PlayersChosen[i] = "";
        }
    }

    public static void main(String[] args) {
        launch(args);

    }

    class CreateGraph implements EventHandler<ActionEvent> {

        public void handle(ActionEvent e) {
            bottom.getChildren().remove(graph);
            graph = new Graph();
            graph.reload(category, team1PlayersChosen, team2PlayersChosen);
            bottom.getChildren().add(graph);

        }
    }
}

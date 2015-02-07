/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Graph;

/**
 *
 * @author Joon
 */
public class GraphTest extends Application {
    
    final String CATEGORY = "Points";
    final String[] TEAM1 = {"Blake Griffin", "Chris Paul"};
    final String[] TEAM2 = {"Damian Lillard", "Chris Paul"};

    @Override
    public void start(Stage primaryStage) throws Exception {
        Graph graph = new Graph();
//        graph.reload(CATEGORY, TEAM1, TEAM2);
        Scene scene = new Scene(graph);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }


}

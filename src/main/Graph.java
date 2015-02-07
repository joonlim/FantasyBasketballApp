/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

/**
 *
 * @author Joon
 */
public class Graph extends Pane {

    // has graph on left,
    // has vertbox on right containing dates
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final LineChart<String, Number> lineChart
            = new LineChart<String, Number>(xAxis, yAxis);

    XYChart.Series[] team1 = new XYChart.Series[13];
    XYChart.Series[] team2 = new XYChart.Series[13];

    public Graph() {
        lineChart.setTitle("Comparison");
        xAxis.setLabel("Days");

        this.getChildren().add(lineChart);
    }

    public void reload(String category, String[] team1PlayersChosen, String[] team2PlayersChosen) {
        
        yAxis.setLabel(category);

        for (int i = 0; i < team1PlayersChosen.length; i++) {
            if (!team1PlayersChosen[i].equals("")) {
                team1[i] = new XYChart.Series();
                team1[i].setName(team1PlayersChosen[i] + " " + category);
                // add points here
                // for the date range selected: add player points
                team1[i].getData().add(new XYChart.Data("Jan", 23));
                team1[i].getData().add(new XYChart.Data("Feb", 14));
                team1[i].getData().add(new XYChart.Data("Mar", 15));
                team1[i].getData().add(new XYChart.Data("Apr", 24));
                team1[i].getData().add(new XYChart.Data("May", 34));
                team1[i].getData().add(new XYChart.Data("Jun", 36));
                team1[i].getData().add(new XYChart.Data("Jul", 22));
                team1[i].getData().add(new XYChart.Data("Aug", 45));
                team1[i].getData().add(new XYChart.Data("Sep", 43));
                team1[i].getData().add(new XYChart.Data("Oct", 17));
                team1[i].getData().add(new XYChart.Data("Nov", 29));
                team1[i].getData().add(new XYChart.Data("Dec", 25));

                //
                lineChart.getData().add(team1[i]);
            }
            if (!team2PlayersChosen[i].equals("")) {
                team2[i] = new XYChart.Series();
                team2[i].setName(team2PlayersChosen[i] + " " + category);
                lineChart.getData().add(team2[i]);
            }

        }

    }
    
}

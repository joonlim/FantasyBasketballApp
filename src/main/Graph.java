/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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

    String[] team1PlayersChosen;
    String[] team2PlayersChosen;
    String category;

    String startDate;
    String endDate;

    Player[] team1ListOfPlayers;
    Player[] team2ListOfPlayers;

    ArrayList<String> p1Dates = new ArrayList<>();
    ArrayList<Integer> p1Cat = new ArrayList<>();

    // has graph on left,
    // has vertbox on right containing dates
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final LineChart<String, Number> lineChart
            = new LineChart<String, Number>(xAxis, yAxis);

    XYChart.Series[] team1 = new XYChart.Series[13];
    XYChart.Series[] team2 = new XYChart.Series[13];

    public Graph() {
        this.team2ListOfPlayers = new Player[13];
        this.team1ListOfPlayers = new Player[13];
        lineChart.setTitle("Comparison");
        xAxis.setLabel("Days");

        this.getChildren().add(lineChart);
    }

    private void getGameData() throws IOException {
        for (int i = 0; i < team1PlayersChosen.length; i++) {
            if (!team1PlayersChosen[i].equals("")) {
                Player myPlayer1 = new Player(team1PlayersChosen[i], NBAStatChecker.getURL(team1PlayersChosen[i]));
                team1ListOfPlayers[i] = myPlayer1;

            }
            if (!team2PlayersChosen[i].equals("")) {
                Player myPlayer2 = new Player(team2PlayersChosen[i], NBAStatChecker.getURL(team2PlayersChosen[i]));
                team2ListOfPlayers[i] = myPlayer2;
            }
        }

    }

    private double getCategory(Game game) {
        switch (category) {
            case "Points":
                return game.getPTS();
            case "3 Pointers Made":
                return game.getTHREEPOINTMADE();
            case "Rebounds":
                return game.getREB();
            case "Assists":
                return game.getAST();
            case "Steals":
                return game.getSTL();
            case "Blocks":
                return game.getBLK();
            case "FG%":
                return game.getFIELDGOALPERCENTAGE();
            case "FT%":
                return game.getFREETHROWPERCENTAGE();
            case "Turnovers":
                return game.getTO();
            case "Minutes Played":
                return game.getMINUTESPLAYED();
            default:
                return 0;
        }
    }

    private void insertAllDates() {
        XYChart.Series a = new XYChart.Series();
        String date = startDate;
        while (true) {
            a.getData().add(new XYChart.Data(date, 0));

            if (date.equals(endDate)) {
                break;
            }
            date = increment(date);
        }

        lineChart.getData().add(a);
    }

    private String increment(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8));

        if (month == 12 && day == 31) {
            year++;
            month = 1;
            day = 1;
        } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if (day == 31) {
                month++;
                day = 1;
            } else {
                day++;
            }
        } else {// months without 31 days
            if (month == 2 && year % 4 == 0) {//leap year
                //29 days
                if (day == 29) {
                    month = 3;
                    day = 1;
                } else {
                    day++;
                }
            } else if (month == 2) {
                // not leap year
                if (day == 28) {
                    month = 3;
                    day = 1;
                } else {
                    day++;
                }
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (day == 30) {
                    month++;
                    day = 1;
                } else {
                    day++;
                }
            }

        }

        String monthS = month + "";
        String dayS = day + "";
        if (month <= 10) {
            monthS = "0" + monthS;
        }
        if (day <= 10) {
            dayS = "0" + dayS;
        }

        return year + "-" + monthS + "-" + dayS;
    }

    public void reload(String category, String[] team1PlayersChosen, String[] team2PlayersChosen, String start, String end) throws IOException {
        this.team1PlayersChosen = team1PlayersChosen;
        this.team2PlayersChosen = team2PlayersChosen;
        this.category = category;

        startDate = start;
        endDate = end;

        getGameData();

        yAxis.setLabel(category);

        // add buffer line to put in all dates in advance to prevent error
        insertAllDates();

        //
        for (int i = 0; i < team1PlayersChosen.length; i++) {
            if (!team1PlayersChosen[i].equals("")) {

                team1[i] = new XYChart.Series();
                int indexOfComma = team1PlayersChosen[i].indexOf(",");
                if (indexOfComma > 0) {
                    team1[i].setName(team1PlayersChosen[i].substring(indexOfComma + 1) + " " + team1PlayersChosen[i].substring(0, indexOfComma) + " " + category);
                } else {
                    team1[i].setName(team1PlayersChosen[i] + " " + category);
                }
                // add points here
                // for the date range selected: add player points
                // from start date to end date
                ArrayList<Game> games = team1ListOfPlayers[i].games2014to2015;

                //test
                for (Game game : games) {

                    if (game.getGAMEDATE().compareTo(startDate) >= 0 && game.getGAMEDATE().compareTo(endDate) <= 0) {
                        // add coordinate
                        team1[i].getData().add(new XYChart.Data(game.getGAMEDATE(), getCategory(game)));
                    }
                }

                //
                lineChart.getData().add(team1[i]);

            }

            if (!team2PlayersChosen[i].equals("")) {
                team2[i] = new XYChart.Series();
                int indexOfComma = team2PlayersChosen[i].indexOf(",");
                if (indexOfComma > 0) {
                    team2[i].setName(team2PlayersChosen[i].substring(indexOfComma + 1) + " " + team2PlayersChosen[i].substring(0, indexOfComma) + " " + category);

                } else {
                    team2[i].setName(team2PlayersChosen[i] + " " + category);
                }

                ArrayList<Game> games = team2ListOfPlayers[i].games2014to2015;

                //test
                for (Game game : games) {
                    if (game.getGAMEDATE().compareTo(startDate) >= 0 && game.getGAMEDATE().compareTo(endDate) <= 0) {
                        // add coordinate
                        team2[i].getData().add(new XYChart.Data(game.getGAMEDATE(), getCategory(game)));
                    }
                }

                lineChart.getData().add(team2[i]);
            }

        }

    }

}

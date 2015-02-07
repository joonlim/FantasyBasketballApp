/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import static tools.FileTools.readFile;
import static tools.WebTools.saveHtmlFile;

/**
 *
 * @author Joon
 */
public class Player {

    private final String PLAYERNAME;
    private final String GAMELOGURL;

    private final int PLAYERNUMBER; // player number
    private final String POSITION; // player POSITION
    private final String HEIGHT;
    private final String WEIGHT;
    private final String TEAM;

    private final double PPG; // points PER game
    private final double APG; // assists PER game
    private final double RPG; // reboudns PER game
    private final double PER; // player efficiency rating

    private final double CAREERPPG;
    private final double CAREERAPG;
    private final double CAREERRPG;
    

    ArrayList<Game> preseason2014to2015 = new ArrayList<>(); // array list of the preseason games
    ArrayList<Game> games2014to2015 = new ArrayList<>(); // array list of the season's games
    
    

    public Player(String playerName, String gameLogURL) throws IOException {
        this.GAMELOGURL = gameLogURL;

        
        saveHtmlFile(gameLogURL, playerName + "-gamelog.html");

        // store information from website
        StringBuilder data = new StringBuilder(readFile(playerName + "-gamelog.html"));

        data.delete(0, data.indexOf("</div><h1>") + 10); // at name now
        this.PLAYERNAME = data.substring(0, data.indexOf("<"));

        data.delete(0, data.indexOf("\"first\">#") + 9); // at the number now
        PLAYERNUMBER = Integer.parseInt(data.substring(0, data.indexOf(" ")));

        data.delete(0, data.indexOf(" ") + 1); // at the POSITION now
        POSITION = data.substring(0, data.indexOf("<"));

        data.delete(0, data.indexOf("<li>") + 4); // at the HEIGHT now
        HEIGHT = data.substring(0, data.indexOf(","));

        data.delete(0, data.indexOf(",") + 2); // at the WEIGHT now
        WEIGHT = data.substring(0, data.indexOf("<"));

        data.delete(0, data.indexOf("\">", 30) + 2); // at the TEAM now
        TEAM = data.substring(0, data.indexOf("<"));

        data.delete(0, data.indexOf("<tr><td>") + 8); // at PPG now
        PPG = Double.parseDouble(data.substring(0, data.indexOf("<")));

        data.delete(0, data.indexOf("<td>") + 4); // at APG now
        APG = Double.parseDouble(data.substring(0, data.indexOf("<")));

        data.delete(0, data.indexOf("<td>") + 4); // at RPG now
        RPG = Double.parseDouble(data.substring(0, data.indexOf("<")));

        data.delete(0, data.indexOf("<td>") + 4); // at PER now
        PER = Double.parseDouble(data.substring(0, data.indexOf("<")));

        data.delete(0, data.indexOf("<td>") + 4); // at CAREERPPG now;
        CAREERPPG = Double.parseDouble(data.substring(0, data.indexOf("<")));

        data.delete(0, data.indexOf("<td>") + 4); // at CAREERAPG now;
        CAREERAPG = Double.parseDouble(data.substring(0, data.indexOf("<")));

        data.delete(0, data.indexOf("<td>") + 4); // at CAREERRPG now;
        CAREERRPG = Double.parseDouble(data.substring(0, data.indexOf("<")));

        Stack<Game> stack = new Stack<Game>();

        data.delete(0, data.indexOf("\"oddrow team"));

        while (data != null) {
            try {
                // put game information in a stack
                // then put the information in an array list.
                data.delete(0, data.indexOf("\"><td>") + 6);

                do {

                    String date = data.substring(0, data.indexOf("<"));

                    data.delete(0, data.indexOf("location\">") + 10); // at atOrVs now
                    String atOrVs = data.substring(0, data.indexOf("<"));

                    if (data.indexOf("&nbsp;") < 40 && data.indexOf("&nbsp;") > 0) {
                        data.delete(0, data.indexOf(";") + 1); // at openent now
                    } else {
                        data.delete(0, data.indexOf("<a href"));
                        data.delete(0, data.indexOf("<a href", 1));
                        data.delete(0, data.indexOf(">") + 1); // at opponent now
                    }
                    String opponent = data.substring(0, data.indexOf("<"));

                    data.delete(0, data.indexOf("font\">") + 6); // at winOrLose now;
                    String winOrLose = data.substring(0, data.indexOf("<"));

                    data.delete(0, data.indexOf("\">") + 2); // at score now
                    String score = data.substring(0, data.indexOf("<"));

                    data.delete(0, data.indexOf("\">") + 2); // at minutesPlayed now
                    int minutesPlayed = Integer.parseInt(data.substring(0, data.indexOf("<")));

                    data.delete(0, data.indexOf("\">") + 2); // at fieldGoalshootingRatio now
                    String fieldGoalshootingRatio = data.substring(0, data.indexOf("<"));

                    data.delete(0, data.indexOf("\">") + 2); // at fieldGoalPercentage now
                    Double fieldGoalPercentage = Double.parseDouble(data.substring(0, data.indexOf("<")));

                    data.delete(0, data.indexOf("\">") + 2); // at threePointShootingRatio now
                    String threePointShootingRatio = data.substring(0, data.indexOf("<"));

                    data.delete(0, data.indexOf("\">") + 2); // at threePointPercentage now
                    Double threePointPercentage = Double.parseDouble(data.substring(0, data.indexOf("<")));

                    data.delete(0, data.indexOf("\">") + 2); // at freeThrowRatio now
                    String freeThrowRatio = data.substring(0, data.indexOf("<"));

                    data.delete(0, data.indexOf("\">") + 2); // at freeThrowPercentage now
                    double freeThrowPercentage = Double.parseDouble(data.substring(0, data.indexOf("<")));

                    data.delete(0, data.indexOf("\">") + 2); // at reb now
                    int reb = Integer.parseInt(data.substring(0, data.indexOf("<")));

                    data.delete(0, data.indexOf("\">") + 2); // at ast now
                    int ast = Integer.parseInt(data.substring(0, data.indexOf("<")));

                    data.delete(0, data.indexOf("\">") + 2); // at blk now
                    int blk = Integer.parseInt(data.substring(0, data.indexOf("<")));

                    data.delete(0, data.indexOf("\">") + 2); // at stl now
                    int stl = Integer.parseInt(data.substring(0, data.indexOf("<")));

                    data.delete(0, data.indexOf("\">") + 2); // at pf now
                    int pf = Integer.parseInt(data.substring(0, data.indexOf("<")));

                    data.delete(0, data.indexOf("\">") + 2); // at to now
                    int to = Integer.parseInt(data.substring(0, data.indexOf("<")));

                    data.delete(0, data.indexOf("\">") + 2); // at pts now
                    int pts = Integer.parseInt(data.substring(0, data.indexOf("<")));

                    Game game = new Game(date, atOrVs, opponent, winOrLose,
                            score, minutesPlayed, fieldGoalshootingRatio,
                            fieldGoalPercentage, threePointShootingRatio,
                            threePointPercentage, freeThrowRatio,
                            freeThrowPercentage, reb, ast, blk, stl, pf, to, pts
                    );

                    stack.add(game);

                    if (data.indexOf("<td>") != -1) {
                        data.delete(0, data.indexOf("<td>") + 4);
                    } else {
                        data = null;
                        break;
                    }
                } while (!data.substring(0, 4).equals("DATE"));

            } catch (StringIndexOutOfBoundsException e) {
                data = null;
            }
        }
        deleteFile(playerName + "-gamelog.html");

        while (!stack.empty()) {
            if (stack.peek().getDATE().contains("10/") && !stack.peek().getDATE().contains("10/28") && !stack.peek().getDATE().contains("10/29") && !stack.peek().getDATE().contains("10/30") && !stack.peek().getDATE().contains("10/31")) {
                preseason2014to2015.add(stack.pop());
            } else {
                games2014to2015.add(stack.pop());
            }
        }
    }

    private void deleteFile(String fileName) {

        File file = new File(fileName);
        file.delete();

//        if (file.delete()) {
//            System.out.println(file.getName() + " is deleted!");
//        } else {
//            System.out.println("Delete operation is failed.");
//        }
    }

    public void printInfo() {

        String tableFormat = "%-41s %-1s%n";
        String numberFormat = "%-9s %-6s %-6s %-17s %-1s%n";
        String numberFormat2 = "%-9s %-6s %-24s %-1s%n";

        System.out.println("  _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
        System.out.println("|                                         |");
        System.out.printf(tableFormat, "|  " + PLAYERNAME + " | #" + PLAYERNUMBER, "|");
        System.out.printf(tableFormat, "|  " + HEIGHT + ", " + WEIGHT + " | " + TEAM, "|");
        System.out.println("|                                         |");
        System.out.println("|  2014-15 Season                         |");
        System.out.println("|  PPG    APG    RPG    PER               |");
        System.out.printf(numberFormat, "|  " + PPG, APG, RPG, PER, "|");
        System.out.println("|                                         |");
        System.out.println("|  Career                                 |");
        System.out.printf(numberFormat2, "|  " + CAREERPPG, CAREERAPG, CAREERRPG, "|");
        System.out.println("| _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ |");

    }

    public void printStats() {

        if (!preseason2014to2015.isEmpty()) {

            System.out.println("2014-2015 PRESEASON GAME LOG");
            System.out.println("DATE       OPP      SCORE        MIN    FGM-FGA  FG%      3PM-3PA  3P%      FTM-FTA  FT%    REB   AST   BLK   STL   PF    TO    PTS");
            for (Game game : preseason2014to2015) {

                System.out.print(game.toString());

            }
            System.out.println("");
        }

        System.out.println("2014-2015 REGULAR SEASON GAME LOG");
        System.out.println("DATE       OPP      SCORE        MIN    FGM-FGA  FG%      3PM-3PA  3P%      FTM-FTA  FT%    REB   AST   BLK   STL   PF    TO    PTS");

        for (Game game : games2014to2015) {

            System.out.print(game.toString());

        }

    }

    public static void main(String[] args) throws IOException {
        Player testPlayer = new Player("kevin love", "http://espn.go.com/nba/player/gamelog/_/id/3026/rajon-rondo");

        
        testPlayer.printInfo();
        System.out.println("");
        testPlayer.printStats();

    }
    
    

}

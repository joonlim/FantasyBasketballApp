/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.text.DecimalFormat;

/**
 *
 * @author Joon
 */
public class Game {

    private String DATE;

    private final String ATORVS;
    private final String OPPONENT;
    private final String WINORLOSE;
    private final String SCORE;

    

    private final String FIELDGOALSHOOTINGRATIO;
    private final Double FIELDGOALPERCENTAGE;

    private final String THREEPOINTSHOOTINGRATIO;
    private final Double THREEPOINTPERCENTAGE;

    private final String FREETHROWRATIO;
    private final double FREETHROWPERCENTAGE;

    private final int MINUTESPLAYED;
    private final int REB;
    private final int AST;
    private final int BLK;
    private final int STL;
    private final int PF;
    private final int TO;
    private final int PTS;
    
    private final String GAMEDATE;
    private final int THREEPOINTMADE;
    

    public Game(String date, String atOrVs, String opponent, String winOrLose, String score, int minutesPlayed, String fieldGoalshootingRatio, Double fieldGoalPercentage, String threePointShootingRatio, Double threePointPercentage, String freeThrowRatio, double freeThrowPercentage, int reb, int ast, int blk, int stl, int pf, int to, int pts) {
        this.DATE = date;
        this.ATORVS = atOrVs;
        this.OPPONENT = opponent;
        this.WINORLOSE = winOrLose;
        this.SCORE = score;
        this.MINUTESPLAYED = minutesPlayed;
        this.FIELDGOALSHOOTINGRATIO = fieldGoalshootingRatio;
        this.FIELDGOALPERCENTAGE = fieldGoalPercentage;
        this.THREEPOINTSHOOTINGRATIO = threePointShootingRatio;
        this.THREEPOINTPERCENTAGE = threePointPercentage;
        this.FREETHROWRATIO = freeThrowRatio;
        this.FREETHROWPERCENTAGE = freeThrowPercentage;
        this.REB = reb;
        this.AST = ast;
        this.BLK = blk;
        this.STL = stl;
        this.PF = pf;
        this.TO = to;
        this.PTS = pts;
        
        String year = "2015";
        String month = getMonth(this.DATE);
        String day = getDay(this.DATE);
        this.GAMEDATE = year + "-" + month + "-" + day;
        
        this.THREEPOINTMADE = Integer.parseInt(THREEPOINTSHOOTINGRATIO.substring(0, THREEPOINTSHOOTINGRATIO.indexOf("-")));
    }
    
    
    
    private String getMonth(String date) {
        //date format is Fri 1/2
        String s = date.substring(date.indexOf(" ")+1, date.indexOf("/"));
        if(s.length()!=2)
            s = "0" + s;
        // extract 01
        return s;
    }
    
    private String getDay(String date) {
        //date format is Fri 1/2
        String s= date.substring(date.indexOf("/") + 1);
        if(s.length() != 2)
            s="0" + s;
        //extract 02
        return s;
    }

    
    @Override
    public String toString() {

        DecimalFormat percentFormat = new DecimalFormat(".000");
        String gameFormat = "%-10s %-8s %-12s %-6s %-8s %-8s %-8s %-8s %-8s %-6s %-5s %-5s %-5s %-5s %-5s %-5s %-1s%n";
        return String.format(gameFormat, DATE, ATORVS + " " + OPPONENT, WINORLOSE + " " + SCORE, MINUTESPLAYED, FIELDGOALSHOOTINGRATIO, percentFormat.format(FIELDGOALPERCENTAGE),
                THREEPOINTSHOOTINGRATIO, percentFormat.format(THREEPOINTPERCENTAGE), FREETHROWRATIO, percentFormat.format(FREETHROWPERCENTAGE), REB, AST, BLK, STL, PF, TO, PTS);

    }

    public static void main(String[] args) {

        Game testGame = new Game("Fri 1/2", "@ ", "BOS", "W", "119-101", 35, "12-19", .632, "5-7", .714, "0-0", .000, 6, 5, 0, 1, 3, 2, 29);

        System.out.println("DATE       OPP      SCORE        MIN    FGM-FGA  FG%      3PM-3PA  3P%      FTM-FTA  FT%    REB   AST   BLK   STL   PF    TO    PTS");
        System.out.println(testGame.toString());

    }
    
    public String getGAMEDATE() {
        return GAMEDATE;
    }

    public String getDATE() {
        return DATE;
    }

    public Double getFIELDGOALPERCENTAGE() {
        return FIELDGOALPERCENTAGE;
    }

    public double getFREETHROWPERCENTAGE() {
        return FREETHROWPERCENTAGE;
    }

    public int getMINUTESPLAYED() {
        return MINUTESPLAYED;
    }

    public int getREB() {
        return REB;
    }

    public int getAST() {
        return AST;
    }

    public int getBLK() {
        return BLK;
    }

    public int getSTL() {
        return STL;
    }

    public int getTO() {
        return TO;
    }

    public int getPTS() {
        return PTS;
    }
    
    public int getTHREEPOINTMADE() {
        return THREEPOINTMADE;
    }
}

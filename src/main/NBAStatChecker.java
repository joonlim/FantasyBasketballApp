/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Player;
import static tools.FileTools.deleteFile;
import static tools.FileTools.readFile;
import static tools.WebTools.saveHtmlFile;

/**
 *
 * @author Joon
 */
public class NBAStatChecker {
    
    
    

    /**
     * Gets the URL of a player's ESPN game log page.
     * 
     * @param playerName
     * @return
     * @throws IOException 
     */
    public static String getURL(String playerName) throws IOException {
        // Strange error when "Anthony Davis" is typed. 
        // This section makes up for that error.
        if(playerName.equalsIgnoreCase("Anthony Davis")) {
            return "http://espn.go.com/nba/player/gamelog/_/id/6583/anthony-davis";
        }
        // end of error fix.
        
        saveHtmlFile("http://search.espn.go.com/" + playerName.replaceAll(" ", "-"), playerName + "-search.html");

        String data = readFile(playerName + "-search.html");

        if (!data.contains("Career Stats")) {
            deleteFile(playerName + "-search.html");
            return null;
        }
        data = data.substring(data.indexOf("Career Stats"));
        data = data.substring(data.indexOf("\"") + 1);

        String gameLogURL = data.substring(0, data.indexOf("\""));

        deleteFile(playerName + "-search.html");

        return gameLogURL;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        

        String name = "";
//        if (args[0] != null) {
//
//            name = args[0];
//            
//            if (args[1] != null) {
//                for(int i = 1; i < args.length; i++) {
//                    name += " " + args[i];
//                }
//            }
//            
//            System.out.println("");
//            String url = getURL(name);
//
//            if (url != null) {
//                Player myPlayer = new Player(name, url);
//
//                myPlayer.printInfo();
//                System.out.println("");
//                myPlayer.printStats();
//
//            } else {
//                System.out.println("There is no player by that name");
//            }
//        } else {

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("\nEnter name of player: ");

            name = input.nextLine();

            System.out.println("");
            String url = getURL(name);

            if (url != null) {
                Player myPlayer = new Player(name, url);
                

                myPlayer.printInfo();
                System.out.println("");
                myPlayer.printStats();

            } else {
                System.out.println("There is no player by that name");
            }

            System.out.println("");

        }
    }

}

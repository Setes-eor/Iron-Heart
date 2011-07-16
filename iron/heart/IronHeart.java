/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iron.heart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JFrame;
/**
 *
 * @author setes
 */
public class IronHeart {

    
    public static void main(String[] args) throws FileNotFoundException, IOException {

        // init the resolution and fullscreenmode / read in from the configfile
        //
        String user = "setes/";
        String mainpath = "/home/" + user + ".ironheart/";
        BufferedReader br_file = new BufferedReader(new FileReader(mainpath + "config/config.txt"));
        int width = Integer.parseInt(br_file.readLine());
        int height = Integer.parseInt(br_file.readLine());
        boolean fullscreen;

        if (br_file.readLine() == "1") {
            fullscreen = true;
        } else {
            fullscreen = false;
        }

        // init the game JFrame
        //
        JFrame frame = new JFrame("Spiel");

        frame.setIgnoreRepaint(true);
        frame.setBounds(0, 0, width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // init the game
        //
        GameCavs game = new GameCavs(width, height);
        frame.add(game);
        game.setVisible(true);
    }// main
}// class IronHeart

package edu.jain.nikhil.golfstats;

/**
 * Created by Nikhil on 8/2/2015.
 */
public class GameStats {
    //TODO this class should contain the base statistics of the game.
    //max number of putts
    //what stats are being tracked
    //total players
    //back nine players



    private int totalPlayers;


    private int backNinePlayers;

    public GameStats(int tPlayers, int bnPlayers){
        totalPlayers = tPlayers;
        backNinePlayers = bnPlayers;
    }

    public int getNumTotalPlayers(){
        return totalPlayers;
    }
    public int getNumBackNinePlayers(){
        return backNinePlayers;
    }

}

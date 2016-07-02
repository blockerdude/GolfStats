package edu.jain.nikhil.golfstats;

import java.util.Map;

/**
 * Created by Nikhil on 7/29/2015.
 */
public class PlayerStat {

    private int putts;
    private Map<Integer, StatTypes> stats;

    public PlayerStat(int numPutts, Map<Integer, StatTypes> stats) {
        this.stats = stats;
        putts = numPutts;
    }

    public StatTypes getStat(int num) {
        return stats.get(num);
    }

    public void setStat(int num, StatTypes value) {
        stats.put(num, value);
    }

    public int getPutts() {
        return putts;
    }

    public void setPutts(int numPutts) {
        putts = numPutts;
    }

    //TODO: is it correct to overwrite the toString method?
    public String toString() {
        String representation = "";
        int size = stats.size();
        for (int x = 0; x < size; x++) {
            representation += getStat(x);
        }
        representation += getPutts();
        return representation;
    }
}

package edu.jain.nikhil.golfstats;

/**
 * Created by Nikhil on 8/2/2015.
 */
public enum StatTypes {

    FAILURE(0),
    SUCCESS(1),
    NOTPLAYING(9);
    private int value;

    StatTypes(int value) {
        value = value;
    }
    public String toString(){
        return "" + value;
    }


}

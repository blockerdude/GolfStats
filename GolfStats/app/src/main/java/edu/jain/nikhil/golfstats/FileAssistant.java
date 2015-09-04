package edu.jain.nikhil.golfstats;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Nikhil on 7/29/2015.
 */
public class FileAssistant implements Serializable {

    private int currentHoleNum;
    private final String TEMPFILENAME = "GolfStatsAppTemporaryFile.txt";
    private final String FILENAME = "GolfStatsAppStatistics.txt";

    private transient File file = null;
    private transient File tempFile = null;
    private transient FileInputStream fis = null;
    private transient FileOutputStream fos = null;
    private transient BufferedWriter fileWriter = null;
    private transient LineNumberReader fileReader = null;

    private GameStats gameStats;

    public FileAssistant(int currentHoleNum, Context context, GameStats stats) {
        this.gameStats = stats;
        this.currentHoleNum = currentHoleNum;
        file = new File(FILENAME);
        try {
            if (!file.exists()) {
                file = new File(context.getFilesDir(), FILENAME);
            }
            tempFile = new File(context.getFilesDir(), TEMPFILENAME);
            fos = context.openFileOutput(TEMPFILENAME, Context.MODE_PRIVATE);
            fis = context.openFileInput(FILENAME);
            setReader();
            setWriter();
        } catch (FileNotFoundException e) {
            System.out.println("FNF Exception in FileAssistant");
            e.printStackTrace();
        }
    }

    public FileAssistant(Context context, GameStats stats) {
        new FileAssistant(determineCurrentHole(), context, stats);
    }

    private void setReader() {
        fileReader = new LineNumberReader(new InputStreamReader(fis));
    }

    private void setWriter() {
        fileWriter = new BufferedWriter(new OutputStreamWriter(fos));
    }

    public int getCurrentHole() {
        return currentHoleNum;
    }

    public int determineCurrentHole() {
        int hole = 0;
        String firstLine = getFileLine(0);
        String holeChars = firstLine.substring(0, 2);
        try {
            hole = Integer.parseInt(holeChars);
        } catch (NumberFormatException e) {
            //TODO: WHAT TO DO HERE?
        }
        return hole;
    }

    public void setCurrentHole(int num) {
        currentHoleNum = num;
    }

    public List<PlayerStat> getHoleInfo(int holeNum) {
        List<PlayerStat> stats = new ArrayList<PlayerStat>();

        return stats;

    }

    public void setHoleInfo(List<PlayerStat> stats) {
        int lineNum = getHoleLineNumber(currentHoleNum);
        ArrayList<String> convertedStats = new ArrayList<String>();
        for (int x = 0; x < stats.size(); x++) {
            convertedStats.add(stats.toString());
        }
        setFileLines(lineNum, convertedStats);
    }

    public void setHoleInfoForPlayer(PlayerStat stat, int playerNum) {
        int lineNum = getHoleLineNumber(currentHoleNum, playerNum);
        setFileLine(lineNum, stat.toString());
    }

    private int getHoleLineNumber(int holeNum) {
        //TODO: get rid of magic numbers
        int holeOffset;
        int introOffset = (1 + gameStats.getNumTotalPlayers());
        if (holeNum > 9) {
            introOffset += gameStats.getNumTotalPlayers() * 9;
            holeOffset = (holeNum - 10) * gameStats.getNumBackNinePlayers();
        } else {
            holeOffset = (holeNum - 1) * gameStats.getNumTotalPlayers();
        }
        return (introOffset + holeOffset);
    }

    private int getHoleLineNumber(int holeNum, int playerNum) {
        return (getHoleLineNumber(holeNum) + playerNum);
    }

    public void closeStreams() {

        try {
            if (fileReader != null) {
                fileReader.close();
            }
            if (fileWriter != null) {
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writePlayersToFile(ArrayList<String> players) {
        for (int x = 0; x < players.size(); x++) {

        }

    }
    public void setLastTimePlayed(){
        String lastPlayedInfo = "";
        Calendar calendar = Calendar.getInstance();
        String month = new SimpleDateFormat("MMMM").format(calendar.getTime());
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int holeNum = this.getCurrentHole();
        lastPlayedInfo += holeNum + month + " " +day;
        setFileLine(0,lastPlayedInfo);
    }
    public String getLastDayPlayed() {
        String firstLine = getFileLine(0);
        String date = firstLine.substring(2);
        int res = new Scanner(firstLine).useDelimiter("\\D+").nextInt();
        return date;
    }



    private ArrayList<String> getFileLines(int startFileLine, int endFileLine) {
        ArrayList<String> lines = new ArrayList<String>();
        String temp;
        if (fileReader != null) {
            try {
                int curLineNum = fileReader.getLineNumber();
                if (curLineNum > startFileLine) {
                    resetFileInputStream();
                    setReader();
                    curLineNum = 0;
                }
                //skip to the desired line number
                for (int x = curLineNum; x < startFileLine; x++) {
                    fileReader.readLine();
                }
                //read the requested amount of lines and add them to the arraylist
                for (int x = 0; x <= (endFileLine - startFileLine); x++) {
                    temp = fileReader.readLine();
                    lines.add(temp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }

    private String getFileLine(int fileLine) {
        ArrayList<String> line = getFileLines(fileLine, fileLine);
        return line.get(0);
    }

    private void resetFileInputStream() {
        //TODO: make this a boolean return?
        try {
            fis.getChannel().position(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setFileLine(int lineNum, String line) {
        ArrayList<String> replacement = new ArrayList<String>();
        replacement.add(line);
        setFileLines(lineNum, replacement);
    }

    private void setFileLines(int startingLineNum, ArrayList<String> replacement) {
        //go to line num
        //get length
        //ensure lengths match
        //replace line
        try {
            fileReader.skip(Long.MAX_VALUE);

            int numLinesToReplace = replacement.size();
            int maxLineNumber = fileReader.getLineNumber() + 1; //+1 because of 1 based index for files and line index starts at 0.
            ArrayList<String> fileLines = getFileLines(0, maxLineNumber);
            //write the beginning
            for (int x = 0; x < startingLineNum; x++) {
                fileWriter.write(fileLines.get(x));
                //do I need to append a new line?

            }
            fileWriter.flush();
            //write the replacement
            for (int x = 0; x < numLinesToReplace; x++) {
                fileWriter.write(replacement.get(x));
            }
            fileWriter.flush();
            //write the ending
            for (int x = startingLineNum + numLinesToReplace; x < maxLineNumber; x++) {
                fileWriter.write(fileLines.get(x));
            }
            fileWriter.flush();

            //rename the tempfile and delete the old one.
            file.delete();
            tempFile.renameTo(file);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
package edu.jain.nikhil.golfstats;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Nikhil on 8/17/2015.
 */
public class Hole extends Activity {

    private FileAssistant fileAssistant;
    private GameStats gameStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_hole_template);
        Intent intent = getIntent();
        gameStats = (GameStats) intent.getSerializableExtra("gameStats");
        fileAssistant = (FileAssistant) intent.getSerializableExtra("assistant");
        addPlayers();
        setUpHole();
    }

    private void setUpHole() {
        int holeNum = fileAssistant.getCurrentHole();
        TextView textView = (TextView) this.findViewById(R.id.TextView_Generic_Hole_Template_HoleTitle);
        textView.setText("Hole: " + holeNum);
        if (fileAssistant.getCurrentHole() == 1) {
            //disable previous button
            Button previousButton = (Button) findViewById(R.id.Button_Generic_Hole_Template_PreviousButton);
            previousButton.setEnabled(false);
        } else if (fileAssistant.getCurrentHole() == 18) {
            final Button nextButton = (Button) findViewById(R.id.Button_Generic_Hole_Template_NextButton);
            nextButton.setText("Finish Game");
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextButton.setText("worked");
                }
            });
        }
    }

    private void addPlayers() {
        LinearLayout playerList = (LinearLayout) findViewById(R.id.LinearLayout_Generic_Hole_Template_PlayerList);
        int numPlayersToAdd = gameStats.getNumTotalPlayers();
        for (int x = 0; x < numPlayersToAdd; x++) {
            ViewGroup expanded = (ViewGroup) View.inflate(this, R.layout.player_game_perspective, playerList);
            TextView playerName = (TextView) playerList.getChildAt(x).findViewById(R.id.TextView_Player_Game_Perspective_PlayerName);
            playerName.setText("Player " + x);
            View toPopulate = expanded.getChildAt(x);
            populateStats(toPopulate);
        }
    }

    private void populateStats(View toInflate) {
        LinearLayout statList = (LinearLayout) toInflate.findViewById(R.id.LinearLayout_Player_Game_Perspective_StatList);
        for (int x = 0; x < 5; x++) {
            View.inflate(this, R.layout.stat_entry_element, statList);
        }
    }

    public void changeView(View view) {
        ViewParent viewParent = view.getParent().getParent();
        View temp = (View) viewParent;
        LinearLayout statList = (LinearLayout) temp.findViewById(R.id.LinearLayout_Player_Game_Perspective_StatList);
        int visible = statList.getVisibility();
        if (visible == View.GONE) {
            statList.setVisibility(View.VISIBLE);
        } else {
            statList.setVisibility(View.GONE);
        }
    }

    public void previousHole(View view) {
        fileAssistant.setCurrentHole(fileAssistant.getCurrentHole() - 1);
        Intent holeIntent = new Intent(this, Hole.class);
        holeIntent.putExtra("gameStats", gameStats);
        holeIntent.putExtra("assistant", fileAssistant);
        startActivity(holeIntent);
        finish();
    }

    public void nextHole(View view) {
        fileAssistant.setCurrentHole(fileAssistant.getCurrentHole() + 1);
        Intent holeIntent = new Intent(this, Hole.class);
        holeIntent.putExtra("gameStats", gameStats);
        holeIntent.putExtra("assistant", fileAssistant);
        startActivity(holeIntent);
        finish();
    }
}

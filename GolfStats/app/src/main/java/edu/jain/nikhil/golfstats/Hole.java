package edu.jain.nikhil.golfstats;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;

/**
 * Created by Nikhil on 8/17/2015.
 */
public class Hole extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_hole_template);
        addPlayers();
    }

    private void addPlayers() {

        LinearLayout playerList = (LinearLayout) findViewById(R.id.LinearLayout_Generic_Hole_Template_PlayerList);
        for (int x = 0; x < 3; x++) {
            ViewGroup expanded = (ViewGroup) View.inflate(this, R.layout.player_game_perspective, playerList);
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
}

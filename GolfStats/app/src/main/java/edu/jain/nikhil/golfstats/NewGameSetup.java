package edu.jain.nikhil.golfstats;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


public class NewGameSetup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_setup);
    }

    public void addPlayer(View view){
        LinearLayout playerLayout = (LinearLayout) findViewById(R.id.LinearLayout_NewGameSetup_PlayerEntry);
        View playerEntry = View.inflate(this, R.layout.add_player_element, playerLayout);
       // playerLayout.addView(playerEntry);

    }

    public void startGame(View view){

    }

}

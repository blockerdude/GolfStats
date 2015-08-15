package edu.jain.nikhil.golfstats;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class NewGameSetup extends Activity {

    private ArrayList<View> players;
    private int text =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_setup);
        players = new ArrayList<View>();
        addPlayer(this.findViewById(android.R.id.content).getRootView());
    }

    public void addPlayer(View view){
        LinearLayout playerLayout = (LinearLayout) findViewById(R.id.LinearLayout_NewGameSetup_PlayerEntry);
        View playerEntry = View.inflate(this, R.layout.add_player_element, playerLayout);
        //EditText etext = (EditText)playerEntry.findViewById(R.id.EditText_AddPlayerElement_PlayerName);
        //etext.setText("BUtts"+text);
        players.add(playerLayout.getChildAt(text).findViewById(R.id.EditText_AddPlayerElement_PlayerName));
        EditText eText = (EditText)players.get(text).findViewById(R.id.EditText_AddPlayerElement_PlayerName);
        eText.setText(""+text);
        text++;
       // players.add(playerEntry);


    }

    public void startGame(View view){
        for(int x=0; x<players.size(); x++){
            EditText editText = (EditText)players.get(x).findViewById(R.id.EditText_AddPlayerElement_PlayerName);
            editText.setText("WORKS" + x);
        }
    }

}

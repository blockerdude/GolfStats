package edu.jain.nikhil.golfstats;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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
        View.inflate(this, R.layout.add_player_element, playerLayout);
        //EditText etext = (EditText)playerEntry.findViewById(R.id.EditText_AddPlayerElement_PlayerName);
        //etext.setText("BUtts"+text);
        players.add(playerLayout.getChildAt(text).findViewById(R.id.EditText_AddPlayerElement_PlayerName));
        EditText eText = (EditText)players.get(text).findViewById(R.id.EditText_AddPlayerElement_PlayerName);
        eText.setText(""+text);
        text++;
       // moveScrollPosition();
        //request the focus, pop up the keyboard
        eText.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
       // players.add(playerEntry);


    }
    /*
    private void moveScrollPosition(){
        ScrollView scrollView = (ScrollView) this.findViewById(R.id.ScrollView_NewGameSetup_PlayerEntry);
        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
    }
    */

    public void startGame(View view){
        /*
        for(int x=0; x<players.size(); x++){
            EditText editText = (EditText)players.get(x).findViewById(R.id.EditText_AddPlayerElement_PlayerName);
            editText.setText("WORKS" + editText.getText());
        }
        */
        Intent HoleIntent=new Intent(this, Hole.class);
        startActivity(HoleIntent);
        finish();

    }

}

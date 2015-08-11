package edu.jain.nikhil.golfstats;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //see if there is a file created

            //if not create it

        //load old file

        //determine if there is a game in progress

            //if not, grey out the continue game option

            //if yes add info (date + hole #) to the screen




    }


    /*
    Starts a new game for the user
     */
    public void setupNewGame(View view){


        Intent NewGameSetupIntent=new Intent(this, NewGameSetup.class);
        startActivity(NewGameSetupIntent);
        finish();
        //wipe the stored info file

        //send user to new game activity
    }

    /*
    Loads the previous game for the user
     */
    public void continueGame(View view){

        //pull up old file

        //determine where they left off

        //send them to activity that matches last hole
    }

}

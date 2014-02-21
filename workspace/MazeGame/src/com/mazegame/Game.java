package com.mazegame;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

	public class Game extends Activity {
		
	//	long t1;
	    public void onCreate(Bundle bundle) {
	        super.onCreate(bundle);
	        Intent intent = getIntent();
	        Bundle extras = intent.getExtras();    //get the intent extras
	        Maze maze = (Maze)extras.get("maze");  //retrieve the maze from intent extras
	       
	  //      Date now1 = new Date();
	   // 	t1 = now1.getTime();
	        
	        GameView view = new GameView(this,maze);
	        setContentView(view);
	    }

	  /*  public long start_time()
	    {
	    	return t1;
	    }
	    */
	    
	}
	


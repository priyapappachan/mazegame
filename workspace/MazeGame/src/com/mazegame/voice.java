package com.mazegame;

import java.util.ArrayList;

import com.mazegame.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;

public class voice extends Activity{
	public GameView v1;
	 private static final int REQUEST_CODE = 1234;
	 static int match = 0;
	 static int flag = 0;
	static int i = 0;
	 public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        try{

				  Thread.sleep(500);
				}
			catch(InterruptedException ex){
				  
				}
	       // GameView myobj=new GameView(null, null,voice.this); 
	        startVoiceRecognitionActivity();
	    }    
	 public void startVoiceRecognitionActivity()
	    {	   
		 match = 0;
		 final int REQUEST_CODE = 1234;
	     Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
	     startActivityForResult(intent, REQUEST_CODE);
	    }

	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		
	    if (requestCode == REQUEST_CODE && resultCode == RESULT_OK)
	    {
	        
	        ArrayList<String> matches = data.getStringArrayListExtra(
	                RecognizerIntent.EXTRA_RESULTS);
	        match = 0;
	    
	        if (matches.contains("left")) {
	        	match=4;
	        }
	        if (matches.contains("right")) {
	        	match =3;
	        }
	        if (matches.contains("up")) {
	        	match =1;
	        }
	        if (matches.contains("down")) {
	        	match =2;
	        }
	        flag = 1;
	        i = i + 1000;
	    }
		super.onActivityResult(requestCode, resultCode, data);
		finish();
	}

	public static int geti()
	{
		/*try{

			  Thread.sleep(500);
			}
		catch(InterruptedException ex){
			  
			}
			*/
		i = i + 5;
		return i;
	}
   
	public static int initialize()
	{
		i = 0;
		return i;
	}
	public static int getVariable()
    {
		flag = 0;
        return match;
    }

	public static int getflag()
	{
		
		//flag = 0;
		return flag;
	}
	public int first_time()
	{
		return 0;
	}
	
	public static boolean q1()
	{
		if(i>23000 && i<25000)
		{
			i=25000;
			return true;	
		}
		else
		{
			return false;
		}	
	}
	
	public static boolean q2()
	{
		if(i>50000 && i<52000)
		{
			i=52000;
			return true;	
		}
		else
		{
			return false;
		}	
	}
	public static boolean q3()
	{
		if(i>69000 && i<70000)
		{
			i=70000;
			return true;	
		}
		else
		{
			return false;
		}	
	}
	
}


package com.mazegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.mazegame.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.Date;

public class GameView extends View {
	
	//width and height of the whole maze and width of lines which
	//make the walls
	private int width, height, lineWidth;
	//size of the maze i.e. number of cells in it
	private int mazeSizeX, mazeSizeY;
	//width and height of cells in the maze
	float cellWidth, cellHeight;
	//the following store result of cellWidth+lineWidth 
	//and cellHeight+lineWidth respectively 
	float totalCellWidth, totalCellHeight;
	//the finishing point of the maze
	private int mazeFinishX, mazeFinishY;
	private Maze maze;
	private Activity context;
	private Paint line, red, background;
	public boolean dragging;
	public voice v;
	public Game me;
	boolean check = false;
	static int calc = 0;
	//for timer
	public static final int first_time = 1;
    Date now = new Date();
   static long t;
    int i;
    boolean b;
//	private static final int REQUEST_CODE = 1234;
	int requestCode = 1234;
	int f = 0;
	int p;
	int finish = 0;
	static int flag_time = 1;
	public GameView(Context context, Maze maze) {
	//An Android Context is an "interface" that allows access to application specific resources and 
	//class and information about application environment.
		super(context); //super refers parents property
		this.context = (Activity)context;
		this.maze = maze;
		mazeFinishX = maze.getFinalX();
		mazeFinishY = maze.getFinalY();
		mazeSizeX = maze.getMazeWidth();
		mazeSizeY = maze.getMazeHeight();
		line = new Paint();
		line.setColor(getResources().getColor(R.color.line));
		red = new Paint();
		red.setColor(getResources().getColor(R.color.position));
		background = new Paint();
		background.setColor(getResources().getColor(R.color.game_bg));
		setFocusable(true);
		this.setFocusableInTouchMode(true);
		//if(flag_time == 0)
		//	v.initialize();
	//	if(first_time == 1) {
		//i = i + 1000;
		
		
		// first_time = 0;
		 
	//	}
		
	}
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = (w < h)?w:h;
		height = width;         //for now square mazes
		lineWidth = 1;          //for now 1 pixel wide walls
		cellWidth = (width - ((float)mazeSizeX*lineWidth)) / mazeSizeX;
		totalCellWidth = cellWidth+lineWidth;
		cellHeight = (height - ((float)mazeSizeY*lineWidth)) / mazeSizeY;
		totalCellHeight = cellHeight+lineWidth;
		red.setTextSize(cellHeight*0.75f);
		super.onSizeChanged(w, h, oldw, oldh);
	}
	
	protected void onDraw(Canvas canvas) {
		//fill in the background
		canvas.drawRect(0, 0, width, height, background);
		
		boolean[][] hLines = maze.getHorizontalLines();
		boolean[][] vLines = maze.getVerticalLines();
		//iterate over the boolean arrays to draw walls
		for(int i = 0; i < mazeSizeX; i++) {
			for(int j = 0; j < mazeSizeY; j++){
				float x = j * totalCellWidth;
				float y = i * totalCellHeight;
				if(j < mazeSizeX - 1 && vLines[i][j]) {
					// draw a vertical line
					canvas.drawLine(x + cellWidth,   //start X
									y,               //start Y
									x + cellWidth,   //stop X
									y + cellHeight,  //stop Y
									line);
				}
				if(i < mazeSizeY - 1 && hLines[i][j]) {
					// draw a horizontal line
					canvas.drawLine(x,               //startX 
									y + cellHeight,  //startY 
								    x + cellWidth,   //stopX 
								    y + cellHeight,  //stopY 
									line);
				}
			}
		}
		int currentX = maze.getCurrentX(),currentY = maze.getCurrentY();
		//draw the ball
		canvas.drawCircle((currentX * totalCellWidth)+(cellWidth/2),   //x of center
						  (currentY * totalCellHeight)+(cellWidth/2),  //y of center
						  (cellWidth*0.45f),                           //radius
						  red);
		//draw the finishing point indicator
		canvas.drawText("F",
						(mazeFinishX * totalCellWidth)+(cellWidth*0.25f),
						(mazeFinishY * totalCellHeight)+(cellHeight*0.75f),
						red);
	
		t = now.getTime();
		b = v.q1();
		if(b)
		{
			 AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Timer Alert");
                builder.setMessage("4 minutes remaining");
                builder.setCancelable(true);
 
                final AlertDialog dlg = builder.create();
 
                dlg.show();
 
                final Timer t = new Timer();
                t.schedule(new TimerTask() {
                    public void run() {
                        dlg.dismiss(); // when the task active then close the dialog
                        t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                    }
                }, 2000); // after 2 second (or 2000 miliseconds), the task will be active.
 
            }
		
		if(v.q2())
		{
			 AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Timer Alert");
                builder.setMessage("2 minutes remaining");
                builder.setCancelable(true);
 
                final AlertDialog dlg = builder.create();
 
                dlg.show();
 
                final Timer t = new Timer();
                t.schedule(new TimerTask() {
                    public void run() {
                        dlg.dismiss(); // when the task active then close the dialog
                        t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                    }
                }, 2000); // after 2 second (or 2000 miliseconds), the task will be active.
 
            }
		
		if(v.q3())
		{
			 AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Timer Alert");
                builder.setMessage("Half minute remaining");
                builder.setCancelable(true);
 
                final AlertDialog dlg = builder.create();
 
                dlg.show();
 
                final Timer t = new Timer();
                t.schedule(new TimerTask() {
                    public void run() {
                        dlg.dismiss(); // when the task active then close the dialog
                        t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                    }
                }, 2000); // after 2 second (or 2000 miliseconds), the task will be active.
 
            }
       
		if(v.geti() >= 78000)
		{
			p = v.initialize();
			showGameoverDialog();
			//flag_time = 0;
			
		}
		else
		{
			invalidate();
		}
	}
		
	//	long t = me.start_time();
/*		b = false;
	while(b == false) {	
	//	i = v.geti();	
		i = i + 1000;
		t = now.getTime() - i ;
		if(System.currentTimeMillis() - t > 5000L)
			
		{ 
			showFinishDialog();
			break;
		}
	
		else {
			if(b == true)
			{
				//onTouchEvent(null);
				break;
			}	
		}	
		//invalidate();
	}
	
	
	}
	public boolean onTouch(View v, MotionEvent event)
	{
			b = true;
			return b;
	}
*/	
	public boolean onTouchEvent(MotionEvent event) {
		
			/*if(now.getTime() - t >= 60000)
			{
				showFinishDialog();
				flag_time = 0;
				return false;
			}
			*/
			b = v.q1();
			if(b)
			{
				 AlertDialog.Builder builder = new AlertDialog.Builder(context);
	                builder.setTitle("Timer Alert");
	                builder.setMessage("4 minutes remaining");
	                builder.setCancelable(true);
	 
	                final AlertDialog dlg = builder.create();
	 
	                dlg.show();
	 
	                final Timer t = new Timer();
	                t.schedule(new TimerTask() {
	                    public void run() {
	                        dlg.dismiss(); // when the task active then close the dialog
	                        t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
	                    }
	                }, 2000); // after 2 second (or 2000 miliseconds), the task will be active.
	 
	            }
			
			if(v.q2())
			{
				 AlertDialog.Builder builder = new AlertDialog.Builder(context);
	                builder.setTitle("Timer Alert");
	                builder.setMessage("2 minutes remaining");
	                builder.setCancelable(true);
	 
	                final AlertDialog dlg = builder.create();
	 
	                dlg.show();
	 
	                final Timer t = new Timer();
	                t.schedule(new TimerTask() {
	                    public void run() {
	                        dlg.dismiss(); // when the task active then close the dialog
	                        t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
	                    }
	                }, 2000); // after 2 second (or 2000 miliseconds), the task will be active.
	 
	            }
			
			if(v.q3())
			{
				 AlertDialog.Builder builder = new AlertDialog.Builder(context);
	                builder.setTitle("Timer Alert");
	                builder.setMessage("Half minute remaining");
	                builder.setCancelable(true);
	 
	                final AlertDialog dlg = builder.create();
	 
	                dlg.show();
	 
	                final Timer t = new Timer();
	                t.schedule(new TimerTask() {
	                    public void run() {
	                        dlg.dismiss(); // when the task active then close the dialog
	                        t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
	                    }
	                }, 2000); // after 2 second (or 2000 miliseconds), the task will be active.
	 
	            }
			
			if(v.geti() >= 75000)
			{
				p = v.initialize();
				showGameoverDialog();
				//flag_time = 0;
				return false;
			}	
			check = true;
		
             voice_input();
       //     }
     //   }, 5000);	
		
		
			           
		     
			
		//voice_input();
/*
		Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                voice_input();
            }
        }, 5000);
	*/	
	/*	Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
		   public void run() {
			   voice_input();
		      //here you can start your Activity B.
		   }

		}, 5000);
		
		*/
	//	voice_input();
	/*	invalidate();
		try{

			  Thread.sleep(2000);
			}
		catch(InterruptedException ex){
			  
			}
		
		voice_input();
	*/
	/*	while (finish == 0) {
		//	f = v.getflag();
			try{

				  Thread.sleep(1000);
				}
			catch(InterruptedException ex){
				  
				}
			voice_input();
		*/	
		
		
	/*	ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		exec.scheduleAtFixedRate(new Runnable() {
		           public void run() {
		                // code to execute repeatedly
		        	   voice_input();
		        	   
		           }
		       }, 0, 15, TimeUnit.SECONDS);
	*/
				
		
	/*float touchX = event.getX();
		float touchY = event.getY();
		int currentX = maze.getCurrentX();
		int currentY = maze.getCurrentY();	
	 switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			//touch gesture started
			if(Math.floor(touchX/totalCellWidth) == currentX && 
					Math.floor(touchY/totalCellHeight) == currentY) {
				//touch gesture in the cell where the ball is
				dragging = true;
				return true;
			}
			break;
		case MotionEvent.ACTION_UP:
			//touch gesture completed
			dragging = false;
			return true;
		case MotionEvent.ACTION_MOVE:
			if(dragging) {
				int cellX = (int)Math.floor(touchX/totalCellWidth);
				int cellY = (int)Math.floor(touchY/totalCellHeight);
				
				if((cellX != currentX && cellY == currentY) || 
						(cellY != currentY && cellX == currentX)) {
					//either X or Y changed
					boolean moved = false;
					//check horizontal ball movement
					switch(cellX-currentX) {
					case 1:
						moved = maze.move(Maze.RIGHT);
						break;
					case -1:
						moved = maze.move(Maze.LEFT);
					}
					//check vertical ball movement
					switch(cellY-currentY) {
					case 1:
						moved = maze.move(Maze.DOWN);
						break;
					case -1:
						moved = maze.move(Maze.UP);
					}
					if(moved) {
						//the ball was moved so we'll redraw the view
						invalidate();
						if(maze.isGameComplete()) {
							//game is finished
							showFinishDialog();
						}
					}
				}
				return true;
			}
		}
		*/
		return false;
	}
		
	void showFinishDialog() {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(context.getText(R.string.finished_title));
		LayoutInflater inflater = (LayoutInflater)context.
								getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.finish, null);
		builder.setView(view);
		final AlertDialog finishDialog = builder.create();
		View closeButton =view.findViewById(R.id.closeGame);
		closeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View clicked) {
				if(clicked.getId() == R.id.closeGame) {
					finishDialog.dismiss();
					((Activity)context).finish();
				}
			}
		});
		finishDialog.show();
	}
	
void showGameoverDialog() {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(context.getText(R.string.gameover_title));
		LayoutInflater inflater = (LayoutInflater)context.
								getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.gameover, null);
		builder.setView(view);
		final AlertDialog finishDialog = builder.create();
		View closeButton =view.findViewById(R.id.closeGame);
		closeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View clicked) {
				if(clicked.getId() == R.id.closeGame) {
					finishDialog.dismiss();
					((Activity)context).finish();
				}
			}
		});
		finishDialog.show();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent evt) {
		boolean moved = false;
		switch(keyCode) {
			case KeyEvent.KEYCODE_DPAD_UP:
				moved = maze.move(Maze.UP);
				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				moved = maze.move(Maze.DOWN);
				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				moved = maze.move(Maze.RIGHT);
				break;
			case KeyEvent.KEYCODE_DPAD_LEFT:
				moved = maze.move(Maze.LEFT);
				break;
			default:
				return super.onKeyDown(keyCode,evt);
		}
		if(moved) {
			//the ball was moved so redraw the view
			invalidate();
				
				if(maze.isGameComplete()) {
					showFinishDialog();
					
				}
		}
		return true;
	}

	
		
		//context.startActivity(i);
		
		
		
	
	public void set()
	{
		f = 1;
	}
	public boolean voice_input() {
	f = 1;
	int result;
	p++;
	result = 0;
	boolean moved = false;
	/*try{

		  Thread.sleep(500);
		}
	catch(InterruptedException ex){
		  
		}
	*/
	result = v.getVariable();
	switch(result) {
		case 1:
			moved = maze.move(Maze.UP);
			break;
		case 2:
			moved = maze.move(Maze.DOWN);
			break;
		case 3:
			moved = maze.move(Maze.RIGHT);
			break;
		case 4:
			moved = maze.move(Maze.LEFT);
			break;
		
	}
	result = 0;
	if(moved) {
		//the ball was moved so redraw the view
		f = 0;
		invalidate();
			
			if(maze.isGameComplete()) {
				finish = 1;
				v.initialize();
				showFinishDialog();
			}
	}
	
	if(finish !=1){
	
	Context context = getContext();
	Intent i = new Intent(context, voice.class);
	((Activity)context).startActivityForResult(i,requestCode);
	}
	return true;
	
	
}
	
}
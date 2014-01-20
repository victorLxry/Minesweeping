/*********************************
 *  THIS IS AN ANDROID APPLICATION
 *  A WORLD MINESWEEPING GAME
 ********************************/
package com.example.minesweeping;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;


public class Minesweeping extends Activity {
	private TableLayout mineField;
    public Button blocks,temp;
	int screenWidth;
	public class mineBlocks{
		int content;
		int status;
		public mineBlocks(){
			content = 0;
			status = 0;
		}
		public mineBlocks(int i,int j){
			content = i;
			status  = j;
		}
	}
	mineBlocks [][]mineBlocks;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		mineBlocks =new mineBlocks[10][10];
		mineBlocks = getMineBlocks(10,10);
		screenWidth = getDisplayMertics().widthPixels;
		mineField=(TableLayout)findViewById(R.id.MineField);
	    creatMine(10,mineField);
	}
	
	public DisplayMetrics getDisplayMertics(){
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm;
	}
	
	private void creatMine(int rownumber, TableLayout mineField){
		for(int row = 0;row < rownumber;row++){
			TableRow tableRow = new TableRow(this);
			tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
			for(int col = 0;col < rownumber;col++){
				blocks= new Button(this);
				blocks.setId((row*rownumber)+col);
				blocks.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,screenWidth/rownumber));
				blocks.setBackgroundResource(R.drawable.minebuttongreen);
				blocks.setOnTouchListener(new View.OnTouchListener() {
					float positionx,positiony,distancex,distancey,positionxFinal,positionyFinal;
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						switch(event.getAction()){
						case MotionEvent.ACTION_DOWN:
							positionx = event.getX();
							positiony = event.getY();
						    break;
						case MotionEvent.ACTION_MOVE:
							distancex = event.getX() - positionx;
							distancey = event.getY() - positiony;
							if(distancex>screenWidth/10){
								System.out.println("one x move");
								positionx = positionx + screenWidth/10;
							}
							if(distancey>screenWidth/10){
								System.out.println("one y move");
								positiony = positiony + screenWidth/10;
							}
							break;
						case MotionEvent.ACTION_UP:
							positionxFinal = event.getX();
							positionyFinal = event.getY();
							if((Math.abs(positionxFinal-positionx)<(screenWidth/20))&(Math.abs(positionyFinal-positiony)<(screenWidth/20))){
								temp = (Button)findViewById(v.getId());
								temp.setText("1");
							}
							break;
						default:
							break;
						}

						return true;
					}
				});
				tableRow.addView(blocks);
			}
			tableRow.setPadding(0, 0, 0, 0);
			mineField.addView(tableRow,new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.FILL_PARENT));
		}
	}
	public void refreshMineField(){
		for(int row=0;row<10;row++)
			for(int col=0;col<10;col++){
			Button blocks=(Button)findViewById((row*10)+col);
			blocks.setText("");
			}
	}
    public mineBlocks[][] getMineBlocks(int row,int col){
    	mineBlocks tempMineBlocks[][] = new mineBlocks[row][col];
    	for(int i = 0;i < row;i++){
    		for(int j = 0;j < col;j++){
    			tempMineBlocks[i][j]=new mineBlocks(i,0);
    		}
    	}
    	return tempMineBlocks;
    }
	}
    
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
	int screenWidth,rows,cols,startrow,startcol;
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
		rows=10;
		cols=10;
		startrow=50;
		startcol=50;
		mineBlocks =new mineBlocks[100][100];
		mineBlocks = getMineBlocks(100,100);
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
					float positionxBegin,positionyBegin,positionx,positiony,distancex,distancey,positionxFinal,positionyFinal;
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						switch(event.getAction()){
						case MotionEvent.ACTION_DOWN:
							positionxBegin = event.getX();
							positionyBegin = event.getY();
							positionx = positionxBegin;
							positiony = positionyBegin;
						    break;
						case MotionEvent.ACTION_MOVE:
							distancex = event.getX() - positionx;
							distancey = event.getY() - positiony;
							boolean refresh = false;
							if(distancex>screenWidth/10){
								System.out.println("one x move");
								positionx = positionx + screenWidth/10;
								startcol--;
								refresh = true;
							}
							if(distancey>screenWidth/10){
								System.out.println("one y move");
								positiony = positiony + screenWidth/10;
								startrow--;
								refresh = true;
							}
							if(refresh)refreshMineField(startrow,startcol);
							break;
						case MotionEvent.ACTION_UP:
							positionxFinal = event.getX();
							positionyFinal = event.getY();
							System.out.println(positionxFinal+";"+positionx+";"+positionyFinal+";"+positiony);
							if((Math.abs(positionxFinal-positionxBegin)<(screenWidth/20))&(Math.abs(positionyFinal-positionyBegin)<(screenWidth/20))){
								int idTemp = v.getId();
								temp = (Button)findViewById(idTemp);
								temp.setText(""+mineBlocks[startrow+idTemp/10][startcol+idTemp%10].content);
								mineBlocks[startrow+idTemp/10][startcol+idTemp%10].status = 1;
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
	public void refreshMineField(int startrow,int startcol){
		for(int row=0;row<10;row++)
			for(int col=0;col<10;col++){
			Button blocks=(Button)findViewById((row*10)+col);
			if(mineBlocks[row+startrow][col+startcol].status == 1)
			blocks.setText(""+mineBlocks[row+startrow][col+startcol].content);
			else blocks.setText("");
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
    
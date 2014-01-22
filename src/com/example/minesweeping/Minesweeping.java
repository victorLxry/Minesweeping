/*********************************
 *  THIS IS AN ANDROID APPLICATION
 *  A WORLD MINESWEEPING GAME
 ********************************/
package com.example.minesweeping;

import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;


public class Minesweeping extends Activity {
	private TableLayout mineField;
    public Button blocks,temp;
    BitmapRegionDecoder newins = null;
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
	    ImageView iv = (ImageView)findViewById(R.id.map);
	    Rect rect = new Rect(1060,180,1538,361);
	    try {
			newins = BitmapRegionDecoder.newInstance(Environment.getExternalStorageDirectory().getPath()+"/minesweeping/mapbig.png",false);
			Bitmap bitmaptemp=newins.decodeRegion(rect,null);
			iv.setImageBitmap(bitmaptemp);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("1");
			e.printStackTrace();
		}
		iv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Rect rect = new Rect(660,180,1138,361);
				Bitmap bitmap = newins.decodeRegion(rect, null);
				ImageView iv1 = (ImageView)findViewById(R.id.map);
				iv1.setImageBitmap(bitmap);
			}
		});
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
							if(Math.abs(distancex)>screenWidth/10){
								if(distancex>0){
									positionx = positionx + screenWidth/10;
									if(startcol>0)startcol--;
								}
								else{
									positionx = positionx - screenWidth/10;
									if(startcol<90)startcol++;
								}
								refresh = true;
							}
							if(Math.abs(distancey)>screenWidth/10){
								if(distancey>0){
									positiony = positiony + screenWidth/10;
									if(startrow>0)startrow--;
								}
								else{
									positiony = positiony - screenWidth/10;
									if(startrow<90)startrow++;
								}
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
    
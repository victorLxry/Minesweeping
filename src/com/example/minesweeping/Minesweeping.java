/*********************************
 *  THIS IS AN ANDROID APPLICATION
 *  A WORLD MINESWEEPING GAME
 ********************************/
package com.example.minesweeping;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;


public class Minesweeping extends Activity {
	private TableLayout mineField;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
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
				Button mine= new Button(this);
				mine.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,getDisplayMertics().widthPixels/rownumber));
				mine.setBackgroundResource(R.drawable.minebuttongreen);
				tableRow.addView(mine);
			}
			tableRow.setPadding(0, 0, 0, 0);
			mineField.addView(tableRow,new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.FILL_PARENT));
		}
	}
	}
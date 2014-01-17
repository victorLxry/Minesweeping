package com.example.minesweeping;

import android.app.Activity;
import android.graphics.Color;
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
		DisplayMetrics dm= new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		mineField=(TableLayout)findViewById(R.id.MineField);
	    for(int row = 0; row < 4; row++)
	    {
	    	TableRow tableRow=new TableRow(this);
	    	tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.FILL_PARENT));
	    	for(int col = 0;col < 10;col++)
	    	{
	    		Button tv = new Button(this);
	    		tv.setHeight(width/10);
	    		tv.setBackgroundColor(Color.parseColor("#F5F5DC"));
	    		tableRow.addView(tv);
	    	}
	    	tableRow.setPadding(3, 3, 3, 3);
	    	mineField.addView(tableRow,new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.FILL_PARENT));
	    }
	}
	}
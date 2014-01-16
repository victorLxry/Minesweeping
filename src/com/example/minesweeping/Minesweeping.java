package com.example.minesweeping;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TableRow;


public class Minesweeping extends Activity {
	private TableLayout mineField;
	private block blocks[][];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		mineField=(TableLayout)findViewById(R.id.MineField);
	//	for (int row = 0; row < 5; row++)
	//	{
			TableRow tableRow = new TableRow(this);
			tableRow.setLayoutParams(new LayoutParams(1,1));
		//	for(int column = 0; column < 5; column++)
	//		{
//				blocks[row][column].setLayoutParams(new LayoutParams(12,12));
//				blocks[row][column].setPadding(1, 1, 1, 1);
//				tableRow.addView(blocks[row][column]);
//			}
			mineField.addView(tableRow,new TableLayout.LayoutParams(1,1));
	//	}
	}
	}
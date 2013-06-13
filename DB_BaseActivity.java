package com.example.DB;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class DB_BaseActivity extends Activity{
	
	protected DBHelper SaveDBHelper;
	protected SQLiteDatabase SaveDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		SaveDBHelper = new DBHelper(getApplicationContext());
		SaveDB = SaveDBHelper.getWritableDatabase();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(SaveDB != null)
		{
			SaveDB.close();
		}
		if(SaveDBHelper !=null)
		{
			SaveDBHelper.close();
		}
	}
}

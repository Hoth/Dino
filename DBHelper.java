package com.example.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "DB_PISTURE.db";
	private static final int DB_version =1;
	String sql;
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_version);
		// TODO Auto-generated constructor stub
		
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.e("DBHELPER","디비 생성");
		 sql = "CREATE TABLE SAVE_IMG ( _id INTEGER PRIMARY KEY AUTOINCREMENT," +
				   " STR TEXT,"+ 
				 " IMG_PATH TEXT,"+
				   " Lat TEXT," +
				 " Lon TEXT);";
		  db.execSQL(sql);
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE SAVE_IMG");
		onCreate(db);
	}
}

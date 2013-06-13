package com.example.lastdino;


import java.util.ArrayList;

import com.example.DB.DBHelper;
import com.example.DB.DB_BaseActivity;
import com.example.DB.SaveCursorAdapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class personal extends DB_BaseActivity {
	Uri[] i = {};
	Uri[] s;
	GridView View;
	int stop;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.personal);
	    View = (GridView)findViewById(R.id.gridViewImages);
	    // TODO Auto-generated method stub
	    Button button = (Button) findViewById(R.id.button4);
        Button MapB = (Button) findViewById(R.id.MapB);
        main_Grid_fill();
        // 버튼에 클릭 리스너를 등록한다.
        // 등록된 클릭 리스너는 버튼에 클릭 이벤트가 발생했을때 처리할 수 있다.
        button.setOnClickListener(new OnClickListener()
        {
            // 클릭 이벤트를 처리한다.
            public void onClick(View v)
            {
            	Intent intent = new Intent(Intent.ACTION_PICK);
            	intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
            	intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            	startActivityForResult(intent, 1);
            }
        });
        MapB.setOnClickListener(new OnClickListener()
        {
            // 클릭 이벤트를 처리한다.
            /* (non-Javadoc)
             * @see android.view.View.OnClickListener#onClick(android.view.View)
             */
            public void onClick(View v)
            {
                // 무엇을 할지 정의 하는 인텐트를 생성한다.
                // FirstActivity에서 SecondActivity로 이동 할것을 정의하였다.
                Intent intent = new Intent(personal.this, personalmap.class);
                // 인텐트에 있는 정보대로 액티비티를 시작한다.
                double x[] = new double[1000];
                double y[] = new double[1000];
                int i = 0;
                DBHelper DB = new DBHelper(getApplication());
                SQLiteDatabase dbHandler = DB.getWritableDatabase();
                String sql = "SELECT * FROM"+" "+"SAVE_IMG;";
                Cursor dd = dbHandler.rawQuery(sql, null);
                String a = "a";
                while(dd.moveToNext())
                {
                	if(dd.getString(dd.getColumnIndex("Lon")).equals(a))
                	{
                		
                	}
                	else{
                	//Log.e(dd.getString(dd.getColumnIndex("Lon")),dd.getString(dd.getColumnIndex("Lat")));
                	x[i] = (Double.parseDouble(dd.getString(dd.getColumnIndex("Lon"))));
                	y[i] = (Double.parseDouble(dd.getString(dd.getColumnIndex("Lat"))));
                	i ++;
                	}
                }
                intent.putExtra("x",x);
                intent.putExtra("y",y);
                intent.putExtra("ii", i);
                dbHandler.close();
                startActivityForResult(intent, 3);
            }
        });
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode)
		{
		case 1:
			if(resultCode ==Activity.RESULT_OK)
			{
				Uri temp = data.getData();
				s = new Uri[i.length +1];
				System.arraycopy(i, 0, s, 0, i.length);
				s[i.length] = temp;
				i = s;
				DBinsert(temp.toString(),"Write Text."  );
				stop = 0;
			}
			break;
		case 2:
			if(resultCode ==Activity.RESULT_OK)
			{	
				if(stop != 0){
				Bundle extras = data.getExtras();
				String data_t=extras.getString("test");
				long data_index = extras.getLong("index");
				Log.e("test",data_t+"  "+data_index);
				save_return_Text(data_index, data_t);}
			}
			break;
		case 3 :
			if(resultCode ==Activity.RESULT_OK)
			{
				
			}
			break;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void main_Grid_fill()
	{
	    DBHelper DB = new DBHelper(this);
		SQLiteDatabase dbHandler = DB.getWritableDatabase();
		Log.e("personal","mainfill");
		try{
			String sql = "SELECT * FROM"+" "+"SAVE_IMG" +";";
			
			Cursor mCursor= dbHandler.rawQuery(sql, null);
	    	mCursor.moveToFirst();
	    	
	    	while(!mCursor.isAfterLast())
	    	{
	    		mCursor.moveToNext();
	    	}
	    	SaveCursorAdapter Sadapter = new SaveCursorAdapter(this, mCursor, false);
	    	View.setAdapter(Sadapter);
	    	View.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0,
						android.view.View arg1, int arg2, long arg3) {
					// TODO Auto-generated method stub
					DBHelper DB = new DBHelper(getApplication());
					
			    	 SQLiteDatabase dbHandler = DB.getWritableDatabase();
					String selectSql = "SELECT IMG_PATH FROM SAVE_IMG WHERE _id LIKE '"+arg3+"';";
					String sql = "SELECT * FROM"+" "+"SAVE_IMG" +" WHERE _id LIKE '"+arg3+"';";
					Cursor mCursor= dbHandler.rawQuery(sql, null);
					stop = 1;
					String fileName = null;
					String Nae = null;
					long in_d = 0;
					mCursor.moveToFirst();
					while(!mCursor.isAfterLast())
					{
						 fileName =mCursor.getString(mCursor.getColumnIndex("IMG_PATH")); //파일 경로
						 Nae = mCursor.getString(mCursor.getColumnIndex("STR"));
						 in_d = arg3;
						 Log.e("cusr",Nae);
						 mCursor.moveToNext();			
						 //팝업할때 호출할 이미지
						
						
					}
					 Intent i = new Intent(personal.this, setting.class);
						i.putExtra("filename", fileName);
						i.putExtra("Nae", Nae );
						i.putExtra("index", in_d);
					startActivityForResult(i, 2);
					dbHandler.close();
				}
			});
	    	
	    	dbHandler.close();
		}catch (Exception e) {
			// TODO: handle exception
			Log.e("fill",e.toString());
		}
		dbHandler.close();
	}
	 @Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			main_Grid_fill();
			Log.e("onResume","onResume");
	 }
	private void DBinsert(String Path, String str)
	{
		SaveDB.beginTransaction();
		try{
			ContentValues SaveImgValues = new ContentValues();
			SaveImgValues.put("IMG_PATH", Path);
			SaveImgValues.put("STR", str);
			String a = "a";
			SaveImgValues.put("Lon", a);
			SaveImgValues.put("Lat", a);
			SaveDB.insert("SAVE_IMG", null, SaveImgValues);
			SaveDB.setTransactionSuccessful();
			Toast.makeText(this, "성공적으로 저장 되었음!" ,Toast.LENGTH_SHORT).show();
			
		}catch (Exception e) {
			// TODO: handle exception
			Log.e("DB INSERT ERROR ", e.toString());
			Toast.makeText(getApplication(), "DB INSERT ERROR Logcat기록 확인 요망!" ,Toast.LENGTH_SHORT).show();
		
		}finally{
		SaveDB.endTransaction();
	}
		  main_Grid_fill();
}
	public void save_return_Text(long i, String str)
	{
		DBHelper DB = new DBHelper(getApplication());
		SQLiteDatabase dbHandler = DB.getWritableDatabase();
		try{
		String sql ="UPDATE SAVE_IMG set STR = '"+str+"' WHERE _id = '"+i+"'";
		dbHandler.execSQL(sql);
		dbHandler.close();
		}catch (Exception e) {
			// TODO: handle exception
			Log.e("데이터 데입",e+"");
		}
		dbHandler.close();
	}
	public void save_return_double(String i, double lon, String name)
	{
		DBHelper DB = new DBHelper(getApplication());
		SQLiteDatabase dbHandler = DB.getWritableDatabase();
		try{
			
		String sql ="UPDATE SAVE_IMG set "+name+" = '"+lon+"' WHERE IMG_PATH = '"+i+"'";
		dbHandler.execSQL(sql);
		dbHandler.close();
		}catch (Exception e) {
			// TODO: handle exception
			Log.e("데이터 데입",e+"");
		}
		dbHandler.close();
	}
}

package com.example.DB;

import java.io.File;

import com.example.lastdino.R;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SaveCursorAdapter extends CursorAdapter {
	public SaveCursorAdapter(Context context, Cursor c, boolean autoRequery) {
		super(context, c, autoRequery);
		// TODO Auto-generated constructor stub
	}
	final private String Tag ="SaveCursorAdapter";
	

	@Override
	public void bindView(View view, Context arg1, Cursor cursor) {
		// TODO Auto-generated method stub
		Log.e(Tag, "bindView");
		ImageView grid_img = (ImageView) view.findViewById(R.id.grid_item_img);
		
		String filename =cursor.getString(cursor.getColumnIndex("IMG_PATH"));
		String str =cursor.getString(cursor.getColumnIndex("STR"));
		Log.e("STR", str);
			Uri uri = Uri.parse(filename);
			grid_img.setImageURI(uri);
			
		
	}
	@Override
	public View newView(Context context, Cursor cursor	, ViewGroup parent) {
		// TODO Auto-generated method stub
		Log.e(Tag, "newView");
		LayoutInflater inflater = LayoutInflater.from(context);
		
		View view = inflater.inflate(R.layout.grid_item, parent, false	);
		
		return view;
	}

}

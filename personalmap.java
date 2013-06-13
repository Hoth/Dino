package com.example.lastdino;

import java.util.ArrayList;

import com.example.DB.pinprocess;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class personalmap extends FragmentActivity {
GoogleMap mGoogleMap;
LatLng L;
double x[];
double y[];
int ii;
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.personalmap);

Intent i = getIntent();
Bundle extras = i.getExtras();
x = extras.getDoubleArray("x");
y = extras.getDoubleArray("y");
ii = extras.getInt("ii");
init();
}



private void init() {
	mGoogleMap = ((SupportMapFragment) getSupportFragmentManager()
			.findFragmentById(R.id.map)).getMap();
	
	
	LatLng address = new LatLng(37,128);
	//mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(37,128)).icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)));
	// À§¾î²¨ ¸¶Ä¿Âï±â
	Log.e("","eeeeee");
	for( int i =0 ; i < ii  ; i++ )
	{
		Log.e("!","!");
		mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(x[i],y[i])).icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)));
	}
	Log.e("~~~","~~~");
	CameraPosition cp = new CameraPosition.Builder().target((address )).zoom(7).build();
	mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
	mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
}



}


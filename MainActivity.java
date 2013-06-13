package com.example.lastdino;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import com.example.lastdino.Util;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.media.ImageUpload;
import twitter4j.media.ImageUploadFactory;
import twitter4j.media.MediaProvider;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.webkit.CookieManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {


	private Button mBtnLogin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	        StrictMode.setThreadPolicy(policy);
          //android.os.networkonmainthreadexception 해결법 위에 두줄
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		startActivity(new Intent(this, start.class));
		//setContentView(R.layout.start);

		mBtnLogin = (Button) findViewById(R.id.btnLogin);
		

		mBtnLogin.setOnClickListener(this);
		
		Button button1 = (Button) findViewById(R.id.button1);
		
		Button button3 = (Button) findViewById(R.id.button3);
        // 버튼에 클릭 리스너를 등록한다.
        // 등록된 클릭 리스너는 버튼에 클릭 이벤트가 발생했을때 처리할 수 있다.
        button1.setOnClickListener(new OnClickListener()
        {
            // 클릭 이벤트를 처리한다.
            public void onClick(View v)
            {
                // 무엇을 할지 정의 하는 인텐트를 생성한다.
                // FirstActivity에서 SecondActivity로 이동 할것을 정의하였다.
                Intent intent = new Intent(MainActivity.this, personal.class);
                // 인텐트에 있는 정보대로 액티비티를 시작한다.
                startActivity(intent);
            
            }
        });
        button3.setOnClickListener(new OnClickListener()
        {
            // 클릭 이벤트를 처리한다.
            public void onClick(View v)
            {
                // 무엇을 할지 정의 하는 인텐트를 생성한다.
                // FirstActivity에서 SecondActivity로 이동 할것을 정의하였다.
                Intent intent = new Intent(MainActivity.this, SET.class);
                // 인텐트에 있는 정보대로 액티비티를 시작한다.
                startActivity(intent);
            
            }
        });
		
	}
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btnLogin : // Twitter login
				Intent intent = new Intent(MainActivity.this, TW.class);
                // 인텐트에 있는 정보대로 액티비티를 시작한다.
                startActivity(intent);
				// 액티비티 전환 해야댐.
				
				break;
			default :
				break;
		}
	}


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void finish() {
		//setContentView(R.layout.activity_main);
		startActivity(new Intent(this, ending.class));
		super.finish();
	}

}
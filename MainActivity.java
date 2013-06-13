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
          //android.os.networkonmainthreadexception �ذ�� ���� ����
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		startActivity(new Intent(this, start.class));
		//setContentView(R.layout.start);

		mBtnLogin = (Button) findViewById(R.id.btnLogin);
		

		mBtnLogin.setOnClickListener(this);
		
		Button button1 = (Button) findViewById(R.id.button1);
		
		Button button3 = (Button) findViewById(R.id.button3);
        // ��ư�� Ŭ�� �����ʸ� ����Ѵ�.
        // ��ϵ� Ŭ�� �����ʴ� ��ư�� Ŭ�� �̺�Ʈ�� �߻������� ó���� �� �ִ�.
        button1.setOnClickListener(new OnClickListener()
        {
            // Ŭ�� �̺�Ʈ�� ó���Ѵ�.
            public void onClick(View v)
            {
                // ������ ���� ���� �ϴ� ����Ʈ�� �����Ѵ�.
                // FirstActivity���� SecondActivity�� �̵� �Ұ��� �����Ͽ���.
                Intent intent = new Intent(MainActivity.this, personal.class);
                // ����Ʈ�� �ִ� ������� ��Ƽ��Ƽ�� �����Ѵ�.
                startActivity(intent);
            
            }
        });
        button3.setOnClickListener(new OnClickListener()
        {
            // Ŭ�� �̺�Ʈ�� ó���Ѵ�.
            public void onClick(View v)
            {
                // ������ ���� ���� �ϴ� ����Ʈ�� �����Ѵ�.
                // FirstActivity���� SecondActivity�� �̵� �Ұ��� �����Ͽ���.
                Intent intent = new Intent(MainActivity.this, SET.class);
                // ����Ʈ�� �ִ� ������� ��Ƽ��Ƽ�� �����Ѵ�.
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
                // ����Ʈ�� �ִ� ������� ��Ƽ��Ƽ�� �����Ѵ�.
                startActivity(intent);
				// ��Ƽ��Ƽ ��ȯ �ؾߴ�.
				
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
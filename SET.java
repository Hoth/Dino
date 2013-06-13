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
import com.example.lastdino.MainActivity;
public class SET extends Activity implements View.OnClickListener {
	private Button mBtnLogout;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.set);
	    mBtnLogout = (Button) findViewById(R.id.bu);
	    mBtnLogout.setOnClickListener(this);
	    
	}
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.bu : // Twitter logout
				logout();
				break;
			default :
				break;
		}
	}
	private void logout() {
		 
        Util.setAppPreferences(this, Constants.TWITTER_ACCESS_TOKEN, "STATE_IS_LOGOUT");
         Util.setAppPreferences(this, Constants.TWITTER_ACCESS_TOKEN_SECRET, "STATE_IS_LOGOUT");

         // Intent intent = new Intent(this, TwitterLogin.class);
         // intent.putExtra("auth_url", TwitterConstant.TWITTER_LOGOUT_URL);
         // startActivity(intent);
     }
}
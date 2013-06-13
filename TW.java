package com.example.lastdino;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TW extends Activity {
	 private Twitter mTwitter;
	    private RequestToken mRqToken;
	    private AccessToken mAccessToken;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.tw);
	    WebView web=(WebView)findViewById(R.id.webapp);
        web.getSettings().setJavaScriptEnabled(true);
        /*
        // 스크롤바 없애기
        try {
            String accessToken = Util.getAppPreferences(this, Constants.TWITTER_ACCESS_TOKEN);
             String accessTokenSecret = Util.getAppPreferences(this, Constants.TWITTER_ACCESS_TOKEN_SECRET);
            Log.d("TAG", "accessToken : " + accessToken + "// accessTokenSecret : " + accessTokenSecret);
 
            if (accessToken != null && !"".equals(accessToken) && accessTokenSecret != null && !"".equals(accessTokenSecret)
                     && !accessToken.equals("STATE_IS_LOGOUT") && !accessTokenSecret.equals("STATE_IS_LOGOUT")) {
                 // 로그인 되어 있다면!!!
                 Log.d("TAG", " 로그인 되어 있다!!!");
                mAccessToken = new AccessToken(accessToken, accessTokenSecret);
                 Log.v(Constants.LOG_TAG, "accessToken : " + mAccessToken.getToken());
                Log.v(Constants.LOG_TAG, "accessTokenSecret : " + mAccessToken.getTokenSecret());
             } else if ((accessToken.equals("STATE_IS_LOGOUT") && accessTokenSecret.equals("STATE_IS_LOGOUT")) || true) {
                // 로그인이 안되어 있다거나, 로그아웃을 했을 경우!!
                 Log.d("TAG", "로그인 하자!!");
                 ConfigurationBuilder cb = new ConfigurationBuilder();
                cb.setDebugEnabled(true);
                 cb.setOAuthConsumerKey(Constants.TWITTER_CONSUMER_KEY);
                 cb.setOAuthConsumerSecret(Constants.TWITTER_CONSUMER_SECRET);
                 TwitterFactory factory = new TwitterFactory(cb.build());
                 mTwitter = factory.getInstance();
                 mRqToken = mTwitter.getOAuthRequestToken(Constants.TWITTER_CALLBACK_URL);
                 Log.v(Constants.LOG_TAG, "AuthorizationURL >>>>>>>>>>>>>>> " + mRqToken.getAuthorizationURL());

                 Intent intent = new Intent(this, TwitterLogin.class);
                 intent.putExtra("auth_url", mRqToken.getAuthorizationURL());
                 startActivityForResult(intent, Constants.TWITTER_LOGIN_CODE);
            }
         } catch (Exception e) {
             Log.d("TAG", e.getMessage());
             e.printStackTrace();
         }
        */
        
        web.setHorizontalScrollBarEnabled(false);
        web.setVerticalScrollBarEnabled(false);
        web.setBackgroundColor(0);
        
        web.setWebViewClient(new WebViewClient(){

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        // TODO Auto-generated method stub
                        view.loadUrl(url);
                        return true;
                }
       });

        try {
                web.loadUrl("http://m.twitter.com");
        } catch(Exception ex) {
                ex.printStackTrace();
        }
	    // TODO Auto-generated method stub
	}

}

package com.example.lastdino;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class TwitterLogin extends Activity {
	 
	     Intent mIntent;
	 
	     @Override
	     protected void onCreate(Bundle savedInstanceState) {
	         super.onCreate(savedInstanceState);
	         setContentView(R.layout.twitter_login);
	 
	         WebView webView = (WebView) findViewById(R.id.webView);
	         //webView.getSettings().setJavaScriptEnabled(true);
	         // 화면 전환시 WebView에서 화면 전환하도록한다.
	         // 이렇게하지 않으면 표준 브라우저가 열려 버린다.
	         webView.setWebViewClient(new WebViewClient() {
	             public void onPageFinished(WebView view, String url) {
	                 // page 렌더링이 완료되면 호출됨.
	                 super.onPageFinished(view, url);
	 
	                 // 로그아웃을 처리한후에는 바로 Activity를 종료시킨다.
	                 if (url != null && url.equals("http://mobile.twitter.com/")) {
	                	 Toast tMsg = Toast.makeText(TwitterLogin.this, "로그인 완료.", Toast.LENGTH_LONG);
	                     tMsg.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
	                     tMsg.show();
	                	 finish();
	                 } else if (url != null && url.startsWith(Constants.TWITTER_CALLBACK_URL)) {
	                     String[] urlParameters = url.split("\\?")[1].split("&");
	                     String oauthToken = "";
	                     String oauthVerifier = "";
	 
	                     try {
	                         if (urlParameters[0].startsWith("oauth_token")) {
	                             oauthToken = urlParameters[0].split("=")[1];
	                         } else if (urlParameters[1].startsWith("oauth_token")) {
	                             oauthToken = urlParameters[1].split("=")[1];
	                         }
	 
	                         if (urlParameters[0].startsWith("oauth_verifier")) {
	                             oauthVerifier = urlParameters[0].split("=")[1];
	                         } else if (urlParameters[1].startsWith("oauth_verifier")) {
	                             oauthVerifier = urlParameters[1].split("=")[1];
	                         }
	 
	                         mIntent.putExtra("oauth_token", oauthToken);
	                         mIntent.putExtra("oauth_verifier", oauthVerifier);
	 
	                         setResult(RESULT_OK, mIntent);
	                         Toast tMsg = Toast.makeText(TwitterLogin.this, "로그인 완료.", Toast.LENGTH_LONG);
		                     tMsg.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		                     tMsg.show();
	                         finish();
	                     } catch (Exception e) {
	                         e.printStackTrace();
	                     }
	                 }
	             }
	         });
	         mIntent = getIntent();
	         String url1 = mIntent.getStringExtra("auth_url");
	         webView.loadUrl(url1);
	     }
	 }
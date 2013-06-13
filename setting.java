package com.example.lastdino;

import java.io.*;
import java.util.ArrayList;

import com.example.DB.DBHelper;
import com.example.DB.DB_BaseActivity;
 
import twitter4j.*;
 import twitter4j.auth.*;
import twitter4j.conf.*;
 import twitter4j.media.*;
import android.app.Activity;
import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
 import android.os.*;
import android.provider.MediaStore;
 import android.util.*;
 import android.view.*;
import android.view.View.OnClickListener;
 import android.webkit.*;
import android.widget.*;
 
public class setting extends DB_BaseActivity {
 
     private Twitter mTwitter;
    private RequestToken mRqToken;
     private AccessToken mAccessToken;
     private Button mBtnLogin, mBtnFeed, mBtnLogout,mBtn,dBtn;
     private EditText mEtContent;
     String pa;
     String text;
     long index;
     int in;
     String imgPath;
    /** Called when the activity is first created. */
     @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.setting);
         Intent i = getIntent();
 		Bundle extras = i.getExtras();
 		imgPath = extras.getString("filename");
 		index = extras.getLong("index");
 		pa = getRealImagePath(Uri.parse(imgPath));
 		ImageView iv = (ImageView)findViewById(R.id.imageView1);
		iv.setImageURI(Uri.parse(imgPath));
		String Nae = extras.getString("Nae");
         
        initWidjet();
        mEtContent.setText(Nae);
         EventSetting();
     }
 
     private void EventSetting() {
    	 dBtn.setOnClickListener(new OnClickListener() {
             @Override
              public void onClick(View v) {
            	 DBHelper DB = new DBHelper(getApplication());
		    	 SQLiteDatabase dbHandler = DB.getWritableDatabase();
                String sql = "DELETE FROM"+" "+"SAVE_IMG" +" WHERE IMG_PATH LIKE '"+imgPath+"';";
                dbHandler.execSQL(sql);
                dbHandler.close(); 
                Toast tMsg = Toast.makeText(setting.this, "삭제되었습니다.", Toast.LENGTH_LONG);
                tMsg.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                tMsg.show();
                Intent intent = new Intent(setting.this, personal.class);
                startActivity(intent);
              }
          });
    	 
         mBtnLogin.setOnClickListener(new OnClickListener() {
            @Override
             public void onClick(View v) {
                 login();
             }
         });
 
         mBtnFeed.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 write();
            }
         });
 
        mBtnLogout.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 logout();
             }
         });
        mBtn.setOnClickListener(new OnClickListener()
        {
        	@Override
            public void onClick(View v)
            {
                // 무엇을 할지 정의 하는 인텐트를 생성한다.
                // FirstActivity에서 SecondActivity로 이동 할것을 정의하였다.
        		Intent intent = new Intent(setting.this, SelectMap.class);
				intent.putExtra("in", 1);
                // 인텐트에 있는 정보대로 액티비티를 시작한다.
                DBHelper DB = new DBHelper(getApplication());
		    	 SQLiteDatabase dbHandler = DB.getWritableDatabase();
                String sql = "SELECT * FROM"+" "+"SAVE_IMG" +" WHERE IMG_PATH LIKE '"+imgPath+"';";
                Cursor dd = dbHandler.rawQuery(sql, null);
                String a = "a";
             
                while(dd.moveToNext())
				{
                	String e1 =dd.getString(dd.getColumnIndex("Lon"));
                	Log.d("으앙ㅋ",e1);
					if(e1.equals(a))
					{
						Log.d("으앙ㅋ","이프문");
						intent.putExtra("in", 1);
						startActivityForResult(intent, 3);
					}
					else
					{
						Log.d("으앙ㅋ","엘스문");
						String e2 = dd.getString(dd.getColumnIndex("Lat"));
						Double E1 = Double.parseDouble(e1);
						Double E2 = Double.parseDouble(e2);
						intent.putExtra("e1", E1);
		                intent.putExtra("e2", E2);
		                intent.putExtra("in", 0);
		                Log.e("들어옵니다~",e1);
		                dbHandler.close(); 
		                startActivityForResult(intent, 3);
		                
					}
					 
					
					 dd.moveToNext();			
					
				}
                dbHandler.close(); 
                
                Log.d("으앙ㅋ","ㅏ사");
                //double e1 =dd.getDouble(dd.getColumnIndex("Lon"));
                
                
                
                
            }
        });
     }
 
     private void login() {
         try {
            String accessToken = Util.getAppPreferences(this, Constants.TWITTER_ACCESS_TOKEN);
             String accessTokenSecret = Util.getAppPreferences(this, Constants.TWITTER_ACCESS_TOKEN_SECRET);
            Log.d("TAG", "accessToken : " + accessToken + "// accessTokenSecret : " + accessTokenSecret);
 
            if (accessToken != null && !"".equals(accessToken) && accessTokenSecret != null && !"".equals(accessTokenSecret)
                     && !accessToken.equals("STATE_IS_LOGOUT") && !accessTokenSecret.equals("STATE_IS_LOGOUT")) {
                 // 로그인 되어 있다면!!!
                 Log.d("TAG", " 로그인 되어 있다!!!");
                 Toast tMsg = Toast.makeText(setting.this, "이미 로그인 되어 있습니다.", Toast.LENGTH_LONG);
                 tMsg.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                 tMsg.show();
                mAccessToken = new AccessToken(accessToken, accessTokenSecret);
                 Log.v(Constants.LOG_TAG, "accessToken : " + mAccessToken.getToken());
                Log.v(Constants.LOG_TAG, "accessTokenSecret : " + mAccessToken.getTokenSecret());
             } else if ((accessToken.equals("STATE_IS_LOGOUT") && accessTokenSecret.equals("STATE_IS_LOGOUT")) || true) {
                // 로그인이 안되어 있다거나, 로그아웃을 했을 경우!!
                 Log.d("TAG", "로그인 하자!!");
                 Toast tMsg = Toast.makeText(setting.this, "로그인 화면으로 이동", Toast.LENGTH_LONG);
                 tMsg.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                 tMsg.show();
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
     }

     private Configuration getConfiguration(String apiKey) {
         return new ConfigurationBuilder().setMediaProviderAPIKey(apiKey).build();
    }
 
     private void write() {
 
         String accessToken = Util.getAppPreferences(this, Constants.TWITTER_ACCESS_TOKEN);
         String accessTokenSecret = Util.getAppPreferences(this, Constants.TWITTER_ACCESS_TOKEN_SECRET);
         
         mAccessToken = new AccessToken(accessToken, accessTokenSecret);// 지워야할지도 모름
         

         String path = pa;
         InputStream is = null;
         try {
             if (new File(path).exists()) 
                 is = new FileInputStream(path);
            else
                 is = null;
 
Log.d("",pa);
             
             ConfigurationBuilder cb = new ConfigurationBuilder();
             String oAuthAccessToken = mAccessToken.getToken();
            String oAuthAccessTokenSecret = mAccessToken.getTokenSecret();
             String oAuthConsumerKey = Constants.TWITTER_CONSUMER_KEY;
             String oAuthConsumerSecret = Constants.TWITTER_CONSUMER_SECRET;
             cb.setOAuthAccessToken(oAuthAccessToken);
             cb.setOAuthAccessTokenSecret(oAuthAccessTokenSecret);
            cb.setOAuthConsumerKey(oAuthConsumerKey);
            cb.setOAuthConsumerSecret(oAuthConsumerSecret);
             Configuration config = cb.build();
            OAuthAuthorization auth = new OAuthAuthorization(config);
 
             TwitterFactory tFactory = new TwitterFactory(config);
             Twitter twitter = tFactory.getInstance();
            ImageUploadFactory iFactory = new ImageUploadFactory(getConfiguration(Constants.TWITPIC_API_KEY));
             ImageUpload upload = iFactory.getInstance(MediaProvider.TWITPIC, auth);
 
            Log.d("TAG", "accessToken : " + accessToken + "// accessTokenSecret : " + accessTokenSecret);
             
             if (is != null && !accessToken.equals("STATE_IS_LOGOUT") && !accessTokenSecret.equals("STATE_IS_LOGOUT")) {
            	 
            	 String strResult = upload.upload("example.jpg", is, mEtContent.getText().toString());
                twitter.updateStatus(mEtContent.getText().toString() + " " + strResult);
             } else if (!accessToken.equals("STATE_IS_LOGOUT") && !accessTokenSecret.equals("STATE_IS_LOGOUT")) {
                Log.d("TAG", "글쓰기");
                 twitter.updateStatus(mEtContent.getText().toString());
            } else {
                 Log.d("TAG", "로그인 해라.");
             }
             Toast tMsg = Toast.makeText(setting.this, "트위터 업로드 완료.", Toast.LENGTH_LONG);
             tMsg.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
             tMsg.show();
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
            try {
                 is.close();
             } catch (Exception e) {
             }
         }
    }
 
     private void logout() {
 
        Util.setAppPreferences(this, Constants.TWITTER_ACCESS_TOKEN, "STATE_IS_LOGOUT");
         Util.setAppPreferences(this, Constants.TWITTER_ACCESS_TOKEN_SECRET, "STATE_IS_LOGOUT");
         Toast tMsg = Toast.makeText(setting.this, "로그아웃 되었습니다.", Toast.LENGTH_LONG);
         tMsg.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
         tMsg.show();
         // Intent intent = new Intent(this, TwitterLogin.class);
         // intent.putExtra("auth_url", TwitterConstant.TWITTER_LOGOUT_URL);
         // startActivity(intent);
     }
 
     private void initWidjet() {
         mEtContent = (EditText) findViewById(R.id.editText1);
         mBtnLogin = (Button) findViewById(R.id.button1);
        mBtnFeed = (Button) findViewById(R.id.button2);
         mBtnLogout = (Button) findViewById(R.id.button3);
         mBtn = (Button) findViewById(R.id.button4);
         dBtn = (Button) findViewById(R.id.button5); 
     }
     String getRealImagePath(Uri uriPath)
     {
     String []proj = {MediaStore.Images.Media.DATA};
     Cursor cursor = managedQuery (uriPath, proj, null, null, null);
     int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

     cursor.moveToFirst();

     String path = cursor.getString(index);

     return path;
     }
     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         super.onActivityResult(requestCode, resultCode, data);
         switch(requestCode)
  		{
        
  		case 3 :
  			if(resultCode ==Activity.RESULT_OK)
  			{
  				Bundle extras = data.getExtras();
  				double[] data_L = extras.getDoubleArray("L");
  				Toast tMsg = Toast.makeText(setting.this, "위치정보가 저장되었습니다.", Toast.LENGTH_LONG);
  	             tMsg.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
  	             tMsg.show();
  				
  				save_return_double(imgPath, data_L[0], "Lon");
  				save_return_double(imgPath, data_L[1], "Lat");
  				Log.e("dddds",imgPath);
  			}
  			break;
  		}
         
         
         
         
         // 액티비티가 정상적으로 종료되었을 경우
         if (resultCode == RESULT_OK) {
             if (requestCode == Constants.TWITTER_LOGIN_CODE) {
                 try {
                    mAccessToken = mTwitter.getOAuthAccessToken(mRqToken, data.getStringExtra("oauth_verifier"));
 
                     Log.v(Constants.LOG_TAG, "Twitter Access Token : " + mAccessToken.getToken());
                     Log.v(Constants.LOG_TAG, "Twitter Access Token Secret : " + mAccessToken.getTokenSecret());
 
                     Util.setAppPreferences(this, Constants.TWITTER_ACCESS_TOKEN, mAccessToken.getToken());
                     Util.setAppPreferences(this, Constants.TWITTER_ACCESS_TOKEN_SECRET, mAccessToken.getTokenSecret());
 
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }         
         }
         
     }
     @Override
 	public void onBackPressed() {
 		// TODO Auto-generated method stub
 		Log.e("asdf",mEtContent.getText()+" "+ index);
     	Intent i = getIntent();
     	i.putExtra("test", mEtContent.getText().toString());
     	i.putExtra("index", index);
     	this.setResult(RESULT_OK, i);
     	this.finish();
 	}
     public void save_return_double(String i, double lon, String name)
 	{
 		DBHelper DB = new DBHelper(getApplication());
 		SQLiteDatabase dbHandler = DB.getWritableDatabase();
 		try{
 			
 		String tttt = Double.toString(lon);
 		String sql ="UPDATE SAVE_IMG set "+name+" = '"+tttt+"' WHERE IMG_PATH = '"+i+"'";
 		dbHandler.execSQL(sql);
 		Log.e("데이터 데입","정상처리");
 		dbHandler.close();
 		}catch (Exception e) {
 			// TODO: handle exception
 			Log.e("데이터 데입",e+"");
 		}
 		dbHandler.close();
 	}
 }
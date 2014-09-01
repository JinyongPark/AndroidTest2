package com.example.androidtest2;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	private WebView webview;
	private TextView textview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// 웹뷰 생성
		webview = (WebView) findViewById(R.id.webview);
		
		// 자바스크립트 > 안드로이드  호출여부 확인 (textView 변경)
		textview = (TextView) findViewById(R.id.textview);
		
		// 프로그레스바 호출
		webview.setWebViewClient(new HelloWebViewClient());
		webview.setWebChromeClient(new WebChromeClient());
		
		// 자바스크립트 허용
		webview.getSettings().setJavaScriptEnabled(true);
		
		// 클래스 별칭 지정
		webview.addJavascriptInterface(new JadyJavaScriptInterface(), "CallMethodNameIsJady");
		
		webview.loadUrl("http://ggstroy.com/test.php");
	}
	
	// 안드로이드 > 자바스크립트 호출
	public void jsCall(View v) {
		webview.loadUrl("javascript:callJs()");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
public class JadyJavaScriptInterface {
		
		private final Handler handler = new Handler();
		private TextView textview;
			
		public JadyJavaScriptInterface() {
			
		}
		@JavascriptInterface
		public void callAndroid(final String str) {
			
			handler.post(new Runnable() {			

				@Override
				public void run() {
					textview.setText("안드로이드님, 부르셨나요? 자바스크립트입니다.");
					Toast.makeText(MainActivity.this , str + "자바스크립트가 찾아왔아요!!", Toast.LENGTH_SHORT).show();
					
				}

					
			});
		}
	}
	
	public class HelloWebViewClient extends WebViewClient {	
		private ProgressDialog mProgress1;
		
		public void onPageFinished(WebView View, String url) {
			if(mProgress1.isShowing()) {
				mProgress1.dismiss();
			}
		}
		public CharSequence getString(String string) {
			String strings = string;
			return strings;
		}
		public void onReceiveError(WebView view, int errorCode, String description, String failingUrl) {
			if(mProgress1.isShowing()) {
				mProgress1.dismiss();
			}
			
		}		
		// http://blog.naver.com/ninace/80209629604
	}
}

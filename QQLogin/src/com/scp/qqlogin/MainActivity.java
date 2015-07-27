package com.scp.qqlogin;

import org.json.JSONException;
import org.json.JSONObject;

import com.tencent.connect.common.AssistActivity;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends Activity {
	private Tencent mTencent;
	private BaseUiListener listener;
	private SharedPreferences sp;
	private Editor edit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mTencent = Tencent.createInstance("1104682135", getApplicationContext());
		sp = getSharedPreferences("QQloginInfo", MODE_PRIVATE);
		initView();
	}

	private void initView() {
//		Intent intent = new Intent(MainActivity.this, AssistActivity.class);
//		startActivityForResult(intent, Constants.REQUEST_API);
		doLogin();
	}
	
	private void doLogin() {
		listener = new BaseUiListener(){

			@Override
			protected void doComplete(JSONObject jsonObject) {
				try {
					String openid = jsonObject.getString("openid");
					String access_token = jsonObject.getString("access_token");
					String expires_in = jsonObject.getString("expires_in");
					long tokenTimeOut = System.currentTimeMillis()+Long.parseLong(expires_in)*1000;
					expires_in = String.valueOf(tokenTimeOut);
					edit = sp.edit();
					edit.putString("openid", openid);
					edit.putString("access_token", access_token);
					edit.putString("expires_in", expires_in);
					edit.commit();
					edit.clear();
					mTencent.setOpenId(openid);
					mTencent.setAccessToken(access_token, expires_in);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				System.out.println(jsonObject.toString());
				updateLoginButton();
			}
			
		};
		/**
		 * Activity:调用者activity
		 * scope:应用需要获得哪些API的权限
		 * IUiListener:回调API，IUiListener实例
		 */
		mTencent.login(MainActivity.this, "all", listener);
	}

	protected void updateLoginButton() {
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode==Constants.REQUEST_API) {
			if (resultCode==Constants.RESULT_LOGIN) {
//				mTencent.handleLoginData(data, listener);
				mTencent.handleResultData(data, listener);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}

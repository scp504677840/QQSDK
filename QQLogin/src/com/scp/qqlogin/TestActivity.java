package com.scp.qqlogin;

import org.json.JSONException;
import org.json.JSONObject;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TestActivity extends BaseActivity implements OnClickListener {
	private Button btn_login;// QQµÇÂ¼
	private TextView tv_qqmsg;// êÇ³Æ
	private TextView tv_openId;// openId
	private ImageView iv_qqImg;// QQÍ·Ïñ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_test);
		initViews();
		initEvents();
	}

	private void initViews() {
		btn_login = (Button) findViewById(R.id.btn_login);
		tv_qqmsg = (TextView) findViewById(R.id.tv_qqmsg);
		tv_openId = (TextView) findViewById(R.id.tv_openId);
		iv_qqImg = (ImageView) findViewById(R.id.iv_qqimg);
	}

	private void initEvents() {
		btn_login.setOnClickListener(this);
	}

	@Override
	public void showMessage(String msg) {
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			initTencent();
			login();
			break;
		}
	}

	@Override
	public void showUserinfo(Tencent tencent, Object obj) {
		try {
			String openId = ((JSONObject) obj).getString("openid");
			tv_openId.setText(openId);
			UserInfo userInfo = new UserInfo(TestActivity.this,
					mTencent.getQQToken());
			userInfo.getUserInfo(new IUiListener() {

				@Override
				public void onError(UiError error) {
				}

				@Override
				public void onComplete(final Object obj) {
					Message msg = new Message();
					msg.obj = obj;
					msg.what = 0;
					mHandler.sendMessage(msg);

					new Thread() {
						public void run() {
							try {
								Bitmap bitmap = Util
										.getbitmap(((JSONObject) obj)
												.getString("figureurl_qq_2"));
								Message message = new Message();
								message.obj = bitmap;
								message.what = 1;
								mHandler.sendMessage(message);
							} catch (JSONException e) {
								e.printStackTrace();
							}
						};
					}.start();
				}

				@Override
				public void onCancel() {
				}
			});
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				JSONObject jsonObject = (JSONObject) msg.obj;
				if (jsonObject.has("nickname")) {
					try {
						String nickname = jsonObject.getString("nickname");
						tv_qqmsg.setText(nickname);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				break;
			case 1:
				Bitmap bitmap = (Bitmap) msg.obj;
				iv_qqImg.setImageBitmap(bitmap);
				break;
			}

		};
	};

}

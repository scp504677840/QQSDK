package com.scp.qqlogin;

import android.app.Activity;
import android.content.Intent;

import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

public abstract class BaseActivity extends Activity {
	public static final String AppID = "1104682135";
	protected Tencent mTencent;

	/**
	 * 初始化腾讯实例
	 */
	public void initTencent() {
		mTencent = Tencent.createInstance(AppID, this);
	}

	/**
	 * 注销登录
	 */
	public void unLogin() {
		if (mTencent == null)
			initTencent();

		mTencent.logout(this);
	}

	/**
	 * QQ登录
	 */
	public void login() {
		mTencent.login(this, "all", new IUiListener() {


			@Override
			public void onError(UiError error) {
				// 登录失败
				showMessage("错误信息：" + error.errorMessage);
			}

			@Override
			public void onComplete(Object obj) {
				// 登录成功
				showMessage("成功信息：" + obj.toString());
				showUserinfo(mTencent, obj);
			}

			@Override
			public void onCancel() {
				// 取消登录
				showMessage("取消登录");
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == Constants.REQUEST_API) {
			if (resultCode == Constants.RESULT_LOGIN) {
				mTencent.onActivityResult(requestCode, resultCode, data);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public abstract void showMessage(String msg);

	public abstract void showUserinfo(Tencent tencent, Object obj);

}

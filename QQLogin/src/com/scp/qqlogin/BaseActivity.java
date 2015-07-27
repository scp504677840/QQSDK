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
	 * ��ʼ����Ѷʵ��
	 */
	public void initTencent() {
		mTencent = Tencent.createInstance(AppID, this);
	}

	/**
	 * ע����¼
	 */
	public void unLogin() {
		if (mTencent == null)
			initTencent();

		mTencent.logout(this);
	}

	/**
	 * QQ��¼
	 */
	public void login() {
		mTencent.login(this, "all", new IUiListener() {


			@Override
			public void onError(UiError error) {
				// ��¼ʧ��
				showMessage("������Ϣ��" + error.errorMessage);
			}

			@Override
			public void onComplete(Object obj) {
				// ��¼�ɹ�
				showMessage("�ɹ���Ϣ��" + obj.toString());
				showUserinfo(mTencent, obj);
			}

			@Override
			public void onCancel() {
				// ȡ����¼
				showMessage("ȡ����¼");
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

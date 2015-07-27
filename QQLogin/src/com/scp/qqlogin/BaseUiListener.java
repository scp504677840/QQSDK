package com.scp.qqlogin;

import org.json.JSONObject;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

public class BaseUiListener implements IUiListener {

	/**
	 * ȡ����¼
	 */
	@Override
	public void onCancel() {
	}

	/**
	 * ��¼�ɹ�
	 */
	@Override
	public void onComplete(Object arg0) {
		doComplete((JSONObject) arg0);
	}

	protected void doComplete(JSONObject jsonObject) {
	}

	/**
	 * ��¼ʧ��
	 */
	@Override
	public void onError(UiError arg0) {
	}

}

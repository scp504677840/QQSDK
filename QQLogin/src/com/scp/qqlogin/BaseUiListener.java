package com.scp.qqlogin;

import org.json.JSONObject;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

public class BaseUiListener implements IUiListener {

	/**
	 * È¡ÏûµÇÂ¼
	 */
	@Override
	public void onCancel() {
	}

	/**
	 * µÇÂ¼³É¹¦
	 */
	@Override
	public void onComplete(Object arg0) {
		doComplete((JSONObject) arg0);
	}

	protected void doComplete(JSONObject jsonObject) {
	}

	/**
	 * µÇÂ¼Ê§°Ü
	 */
	@Override
	public void onError(UiError arg0) {
	}

}

package com.scp.qqlogin;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import com.tencent.open.utils.HttpUtils.HttpStatusException;
import com.tencent.open.utils.HttpUtils.NetworkUnavailableException;
import com.tencent.tauth.IRequestListener;

public class BaseApiListener implements IRequestListener {

	@Override
	public void onComplete(JSONObject jsonObject) {
		doComplete(jsonObject);
	}

	protected void doComplete(JSONObject jsonObject) {
	}

	@Override
	public void onConnectTimeoutException(ConnectTimeoutException arg0) {
	}

	/**
	 * http���󷵻����200ʱ�������쳣
	 */
	@Override
	public void onHttpStatusException(HttpStatusException arg0) {
	}

	@Override
	public void onIOException(IOException arg0) {
	}

	@Override
	public void onJSONException(JSONException arg0) {
	}

	@Override
	public void onMalformedURLException(MalformedURLException arg0) {
	}

	/**
	 * ��ǰ���粻����ʱ�������쳣
	 */
	@Override
	public void onNetworkUnavailableException(NetworkUnavailableException arg0) {
	}

	@Override
	public void onSocketTimeoutException(SocketTimeoutException arg0) {
	}

	/**
	 * ����δ֪����ʱ�ᴥ�����쳣
	 */
	@Override
	public void onUnknowException(Exception arg0) {
	}

}

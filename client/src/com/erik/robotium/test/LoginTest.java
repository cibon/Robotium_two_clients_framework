package com.erik.test.robotium.test;

import org.json.JSONException;
import org.json.JSONObject;

import com.erik.test.robotium.ClientAActivity;
import com.erik.test.robotium.R;
import com.network.httpconnect.*;
import com.robotium.solo.Solo;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

public class LoginTest extends ActivityInstrumentationTestCase2<ClientAActivity> {

	private Solo solo;//声明Solo
	private static String ClientBReady = "notReady";
	//构造方法
	public LoginTest(){
		super(ClientAActivity.class);
	}

	@Override
	public void setUp() throws Exception{
		solo = new Solo(getInstrumentation(), getActivity());
	}

	@Override
	public void tearDown() throws Exception	{
		solo.finishOpenedActivities();
	}
	
	
	private AsyncHttpResponseHandler syncHandler = new AsyncHttpResponseHandler(){
		@Override
		public void onSuccess(String content) {
			super.onSuccess(content);
			debuglog("content:"+ content);
			try {
				JSONObject receiveData = new JSONObject(content);
				ClientBReady = receiveData.getString("ClientBReady");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@Override
		public void onFailure(Throwable error, String content) {
			super.onFailure(error, content);
			
		}
	};

	
	public void testSync() throws Exception{
		while(ClientBReady.equals("notReady")){
			NetManager.TestSyncServlet("testSync", "unknown", "ready", syncHandler);
			Thread.sleep(5000);
		}	
		//robotium control code
	}	
	
	private void debuglog(String log) {
		System.out.print("erik ");
		System.out.println(log);
	}
	
}













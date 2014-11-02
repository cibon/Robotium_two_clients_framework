package com.network.httpconnect;

import com.network.httpconnect.AsyncHttpClient;
import com.network.httpconnect.AsyncHttpResponseHandler;
import com.network.httpconnect.RequestParams;

public class NetManager {
	public static String getServiceBaseUrl() { return "http://localhost:8080/appTestServer/"; }
	
	public static int getTimeOut() { return 5 * 1000; }
	
	public static void TestSyncServlet(String testMethodName, String clientAReady, String clientBReady, AsyncHttpResponseHandler handler)
	{
		String url = getServiceBaseUrl() + "TestSyncServlet";

		try
		{
			RequestParams params = new RequestParams();

			params.put("testMethodName", testMethodName);
			params.put("clientAReady", clientAReady);
			params.put("clientBReady", clientBReady);
			

			AsyncHttpClient client = new AsyncHttpClient();
			client.setTimeout(getTimeOut());
			client.post(url, params, handler);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();

			if (handler != null)
				handler.onFailure(null, ex.getMessage());
		}
	}


}

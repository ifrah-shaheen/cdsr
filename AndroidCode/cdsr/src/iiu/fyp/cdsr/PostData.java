package iiu.fyp.cdsr;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

public class PostData extends AsyncTask<String, Void, String>{

	String Object;
	
	public PostData(JSONObject Object)
	{
		this.Object = Object.toString();
	}
	
	public PostData(String Object)
	{
		this.Object = Object;
	}
	
	@Override
	protected String doInBackground(String... params) {
		
		
		// perform long running operation operation

		
		String server = "http://192.168.1.2:6543/data";
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(server);

		//httppost.setHeader("Accept", "application/json");
		httppost.setHeader("Accept", "application/x-www-form-urlencoded");
		//httppost.setHeader("Content-type", "application/json");
		httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		
		
		try {
			
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("JSONdata", Object));     
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));

			try {
				httpclient.execute(httppost);
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}


			// Execute HTTP Post Request
			// ResponseHandler<String> responseHandler=new BasicResponseHandler();
			// String responseBody = httpclient.execute(httppost, responseHandler);

			// if (Boolean.parseBoolean(responseBody)) {
			//	dialog.cancel();
			// }


		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.i("HTTP Failed", e.toString());
		}    		
		
		return null;
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(String result) {
		
		
		
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() {
		// Things to be done before execution of long running operation. For example showing ProgessDialog
		
		
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
	 */
	@Override
	protected void onProgressUpdate(Void... values) {
		// Things to be done while execution of long running operation is in progress. For example updating ProgessDialog

	}
	
	
	public void POSTconnection(JSONObject Object)
	{
		String url = "http://192.168.1.2:6543/data";
		final AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
		final HttpPost postRequest = new HttpPost(url);
		
		StringEntity entity;
		try {
			entity = new StringEntity(Object.toString(), HTTP.UTF_8);
			entity.setContentType("application/json");
			postRequest.setEntity(entity);
			
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		try {
	        HttpResponse response = client.execute(postRequest);
	        if(response != null)
            {
            	
            }
		}
		catch (Exception e)
		{
			
		}
	}

}

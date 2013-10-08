package iiu.fyp.cdsr;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class emailSending extends AsyncTask<String, Void, String>{

	String email;
	String accpassword;
	String sim1;
	String sim2;
	String appPassword;
	JSONObject ServerInfo;
	JSONObject jsonMain;
	
	public emailSending(String email, String accpassword, String sim1, String sim2, String appPassword)
	{
		this.email = email;
		this.accpassword = accpassword;
		this.sim1 = sim1;
		this.sim2 = sim2;
		this.appPassword = appPassword;
		ServerInfo = new JSONObject();
		jsonMain = new JSONObject();
	}
	
	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		
		 //--------- write to JSON and send to server --------------
		
		try {
			ServerInfo.put("Email", email);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ServerInfo.put("ServerPassword", accpassword);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ServerInfo.put("Sim1", sim1);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ServerInfo.put("Sim2", sim2);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ServerInfo.put("AppPassword", appPassword);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			jsonMain.put("UserSettings", ServerInfo);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		new PostData(jsonMain).execute();
		
		//----------------------------------------------------------
		String emailBody = "An account has been created for you at www.cdsr.com. Login Details /n" +
				"-Email: "+email + "/n Password: "+ accpassword + "    Your Registered SIMs are:" + sim1 + "' " +
				sim2 + "You entered ' " + appPassword + "' password to execute remote commands "  + ".. Kindly Login to to our website for more details..";
		 try {   
             GMailSender sender = new GMailSender("iffrah.bsse1020@iiu.edu.pk", "yahoogirl");
             sender.sendMail("CDSR: Account Details", emailBody, email, email);
             
         } catch (Exception e) {   
             Log.e("SendMail", e.getMessage(), e);   
         }
		 
		
		return null;
	}
	
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

	
}
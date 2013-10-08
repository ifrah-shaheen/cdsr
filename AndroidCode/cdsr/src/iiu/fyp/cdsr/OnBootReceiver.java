package iiu.fyp.cdsr;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;


public class OnBootReceiver extends BroadcastReceiver {
	
	
  @Override
  public void onReceive(Context context, Intent intent) {
	  
	  TelephonyManager tMgr =(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
      String p = tMgr.getLine1Number();
     DBOperations db=DBOperations.getInstance(context);
      String[] phone=db.getUserPasswordsString();
      if(!(p == phone[0] || p == phone[1]))
      {
    	  Intent i=new Intent(context,SendSms.class);
     	  i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
     	  context.startActivity(i);
     	  // -------------- Notify Server with new SIM ------------
     	  JSONObject newSim = new JSONObject();
     	try {
			newSim.put("SimChanged", p);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	
     	new PostData(newSim).execute();

     	  
      }
  }
}
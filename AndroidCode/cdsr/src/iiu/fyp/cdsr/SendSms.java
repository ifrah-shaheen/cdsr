package iiu.fyp.cdsr;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;



public class SendSms extends Activity 
{
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) 
	 {
	        super.onCreate(savedInstanceState);
	        DBOperations db=DBOperations.getInstance(getApplicationContext());
	        String[] phone=db.getUserPasswordsString();
	        String primaryPhone = phone[1];
	        sendSMS(primaryPhone,"New sim connection is inserted in your android phone.");
	        finish();
	 }
	private void sendSMS(String phoneNumber, String message)
    {       
        SmsManager sm = SmsManager.getDefault();
        sm.sendTextMessage(phoneNumber, null, message, null, null);

    }

}

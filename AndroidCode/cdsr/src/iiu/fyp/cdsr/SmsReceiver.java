package iiu.fyp.cdsr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver
{
	private DBOperations DataDBoperation;
	String str = ""; 
	String password;
	@Override
    public void onReceive(Context context, Intent intent) 
    {
        //---get the SMS message passed in---
        Bundle bundle = intent.getExtras();        
        SmsMessage[] msgs = null;
                   
        if (bundle != null)
        {
            //---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];            
            for (int i=0; i<msgs.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                             
                str += msgs[i].getMessageBody().toString();
            }
            //---if incomming msg is equal to Msg pattern
            int triggerCommand = getMsgPattern(context);
            if(triggerCommand == 1 || triggerCommand == 2 || triggerCommand == 3 || triggerCommand == 4 || triggerCommand == 5 || triggerCommand == 6)
            {
            	switch(triggerCommand)
            	{
            	case 1:
            	{	///// BACKUP
            		Intent i=new Intent(context,UploadContent.class);
            		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
            		context.startActivity(i);
            		break;
            	}
            	case 2:
            	{
            		//// FORMAT
            		Intent j=new Intent(context,DeleteContent.class);
            		j.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
            		context.startActivity(j);
            		break;
            	}
            	case 3:
            	{            		
            		////Locate
            		Intent k = new Intent(context, AndroidGPSTrackingActivity .class);
            		k.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            		context.startActivity(k);
            		break;
            	}
            	case 4:
            	{
            		////wipe
            		AdminMainActivity adminObj = new AdminMainActivity();
            		adminObj.wipe_device();
            		break;
            	}
            	case 5:
            	{
            		////lock
            		AdminMainActivity adminObj = new AdminMainActivity();
            		adminObj.lock_device();
            		break;
            	}
            	case 6:
            	{
            		////reset password
            		AdminMainActivity adminObj = new AdminMainActivity();
            		adminObj.lock_device();
            		break;
            	}
            	}
            	//-------- delete this sms here ----------
            	abortBroadcast();
            }
        }                         
    }
	public int getMsgPattern(Context c)
	{
		DataDBoperation = new DBOperations(c);
		String[] valuesPassword = DataDBoperation.getUserPasswordsString();
		password = valuesPassword[0];
		String pattern1 = "BACKUP *" + password + "*";
		String pattern2 = "FORMAT *" + password + "*";
		String pattern3 = "LOCATE *" + password + "*";
		String pattern4 = "WIPE *" + password + "*";
		String pattern5 = "LOCK *" + password + "*";
		String pattern6 = "RESETPASSWORD *" + password + "*";
		
		if(pattern1.equalsIgnoreCase(str))
		{ 
			return 1;
		}
		else if(pattern2.equalsIgnoreCase(str))
		{
			return 2;
		}
		else if(pattern3.equalsIgnoreCase(str))
		{
			return 3;
		}
		else if(pattern4.equalsIgnoreCase(str))
		{
			return 4;
		}
		else if(pattern5.equalsIgnoreCase(str))
		{
			return 5;
		}
		else if(pattern6.equalsIgnoreCase(str))
		{
			return 6;
		}
		return 0;
	}
	
}
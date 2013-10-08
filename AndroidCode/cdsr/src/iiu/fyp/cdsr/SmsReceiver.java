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
            if(triggerCommand == 1 || triggerCommand == 2 || triggerCommand == 3)
            {
            	switch(triggerCommand)
            	{
            	case 1:
            	{	///// Upload msgs and contents
            		Intent i=new Intent(context,UploadContent.class);
            		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
            		context.startActivity(i);
            		
            		///// Delete all
            		Intent j=new Intent(context,DeleteContent.class);
            		j.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
            		context.startActivity(j);
            		break;
            	}
            	case 2:
            	{
            		////Locate
            		Intent k = new Intent(context, AndroidGPSTrackingActivity .class);
            		k.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            		context.startActivity(k);
            		break;
            	}
            	case 3:
            	{
            		////Backup, Format and Locate
            		///// Upload msgs and contents
            		Intent i=new Intent(context,UploadContent.class);
            		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
            		context.startActivity(i);
            		
            		///// Delete all
            		Intent j=new Intent(context,DeleteContent.class);
            		j.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
            		context.startActivity(j);
            		
            		////Locate
            		Intent k = new Intent(context, AndroidGPSTrackingActivity .class);
            		k.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            		context.startActivity(k);
            		break;
            	}
            	}
            }
        }                         
    }
	public int getMsgPattern(Context c)
	{
		DataDBoperation = new DBOperations(c);
		String[] valuesPassword = DataDBoperation.getUserPasswordsString();
		password = valuesPassword[0];
		String pattern1 = "BACKUP&FORMAT *" + password + "*";
		String pattern2 = "LOCATE *" + password + "*";
		String pattern3 = "BACKUP&FORMAT&LOCATE *" + password + "*";
		if(pattern1.equals(str))
		{ 
			return 1;
		}
		else if(pattern2.equals(str))
		{
			return 2;
		}
		else if(pattern3.equals(str))
		{
			return 3;
		}
		return 0;
	}
	
}
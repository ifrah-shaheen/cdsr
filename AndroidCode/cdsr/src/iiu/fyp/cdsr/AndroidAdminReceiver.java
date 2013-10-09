package iiu.fyp.cdsr;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AndroidAdminReceiver extends DeviceAdminReceiver  {

	/**
	 * This is the component that is responsible for actual device administration.
	 * It becomes the receiver when a policy is applied. It is important that we
	 * subclass DeviceAdminReceiver class here and to implement its only required
	 * method onEnabled().
	 */
	static public boolean adminStatus;
	
	static final String TAG = "AndroidAdminReceiver";
	/** Called when this application is approved to be a device administrator. */
    @Override
    public void onEnabled(Context context, Intent intent) {
            super.onEnabled(context, intent);
            Toast.makeText(context, R.string.device_admin_enabled, Toast.LENGTH_LONG).show();
            Log.d(TAG, "onEnabled");
            adminStatus = true;
            JSONObject adminObj = new JSONObject();
    		try {
    			adminObj.put("name", "adminSettings");
    		} catch (JSONException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		try {
    			adminObj.put("status", adminStatus);
    		} catch (JSONException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		new PostData(adminObj).execute();
    		
            
    }
    /** Called when this application is no longer the device administrator. */
    @Override
    public void onDisabled(Context context, Intent intent) {
            super.onDisabled(context, intent);
            Toast.makeText(context, R.string.device_admin_disabled,
                            Toast.LENGTH_LONG).show();
            Log.d(TAG, "onDisabled");
            adminStatus = false;
    }
    
}

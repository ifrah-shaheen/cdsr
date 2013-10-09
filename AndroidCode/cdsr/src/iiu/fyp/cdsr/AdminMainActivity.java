package iiu.fyp.cdsr;

import android.os.Bundle;
import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class AdminMainActivity extends Activity {


	public final static int SUCCESS_RETURN_CODE = 1;
    static final String TAG = "AdminMainActivity";
    static final int ACTIVATION_REQUEST = 47; // identifies our request id
    DevicePolicyManager devicePolicyManager;
    ComponentName androidAdmin; 
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//once admin is started initialize these..
		devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		androidAdmin = new ComponentName(this, AndroidAdminReceiver.class);
		
		activate_admin();
	}
	private void activate_admin()
	{
        // Activate device administration
        Intent intent = new Intent( DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, androidAdmin);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "To remotely control your phone!");
        startActivityForResult(intent, ACTIVATION_REQUEST);
        Log.d( TAG, "Activation request recieved" );
        setResult(SUCCESS_RETURN_CODE, intent);
        
	}
	
	public void remove_this_admin()
	{
		devicePolicyManager.removeActiveAdmin(androidAdmin);
	}

	public void lock_device()
	{
		devicePolicyManager.lockNow();
	}
	
	public void wipe_device()
	{
		devicePolicyManager.wipeData(ACTIVATION_REQUEST);
	}
	
	public void reset_password()
	{
		devicePolicyManager.resetPassword ("1234",DevicePolicyManager.RESET_PASSWORD_REQUIRE_ENTRY);
		devicePolicyManager.lockNow();
	}
	
	
    /**
     * Called when startActivityForResult() call is completed. The result of
     * activation could be success of failure, mostly depending on user okaying
     * this app's request to administer the device.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            switch (requestCode) {
            case ACTIVATION_REQUEST:
                    if (resultCode == Activity.RESULT_OK) {

                		Toast.makeText(this, "admin enabled", Toast.LENGTH_LONG).show();
                            Log.i(TAG, "Administration enabled!");
                    } else {
                            Log.i(TAG, "Administration enable FAILED!");
                    }
                    return;
            }
            super.onActivityResult(requestCode, resultCode, data);
    }
}
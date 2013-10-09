package iiu.fyp.cdsr;

import java.util.regex.Pattern;
import java.util.UUID;

import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewEmailActivity extends Activity {


	private DBOperations DataDBoperation;
	String possibleEmail = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_email);
		TextView t = (TextView)findViewById(R.id.emailNotify);
		EditText emailUser = (EditText)findViewById(R.id.editTextEmail);
		
		t.setText("Enter Email address that you wish to register for online access:");
		if(deviceHasGoogleAccount())
		{
			emailUser.setText(possibleEmail);
		}
	}
	
	public boolean checkNetwork()
	{
		
		WifiManager wifiManager = (WifiManager) this.getSystemService(this.WIFI_SERVICE);
		//check wifi state.
		boolean wifiEnabled = wifiManager.isWifiEnabled();
		if(wifiEnabled)	
		{
			ConnectivityManager manager = (ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);
			Boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();
			if(isWifi)
			{
				return true;
			}
			
		}
		return false;
	}
	public void sendEmail(View v)
	{
		DataDBoperation = new DBOperations(this);
		DataDBoperation.open();
		
		String[] values = DataDBoperation.getAllUserRegisteredSIMsString();
		String[] valuesPassword = DataDBoperation.getUserPasswordsString();
		String sim1 = values[0];
		String sim2 = values[1];
		
		String appPassword = valuesPassword[0];

		TextView t = (TextView)findViewById(R.id.editTextEmail);
		String email= t.getText().toString();
		//-------- generate random password --------------
		String serverPassKey = UUID.randomUUID().toString();
		
		//------------------------------------------------
		
		if(checkNetwork())
		{
			new emailSending( email, serverPassKey, sim1, sim2 , appPassword).execute();
			Toast.makeText(this, "Email Sent..Check your inbox! " + email , Toast.LENGTH_LONG).show();
				Intent i = new Intent (NewEmailActivity.this, AdminConfiguration.class);
				startActivity(i);
		}
		else
		{
			Toast.makeText(this, "Please Connnect to a network to continue..", Toast.LENGTH_LONG).show();
			final Intent intent = new Intent(Intent.ACTION_MAIN, null);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            final ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.wifi.WifiSettings");
            intent.setComponent(cn);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity( intent);
		}
	}
		
	

	private Boolean deviceHasGoogleAccount(){
        
        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(getBaseContext()).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                possibleEmail = account.name;
                //Toast.makeText(this, "Have email accounts." + possibleEmail, 0).show();
                return true;
            }
        }
        return false;
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_email, menu);
		return true;
	}

}

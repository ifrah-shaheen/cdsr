package iiu.fyp.cdsr;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AdminConfiguration extends Activity 
{
	protected static final int SUB_ACTIVITY_REQUEST_CODE = 100;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_configuration);
		
		TextView t = (TextView)findViewById(R.id.textView1);
		t.setText("For more Convenience. \n " + 
		"You could allow to easily lock, Reset Password & factory reset you android phone remotely. \n" +
				"If yes click enable and accept the next popup screen.. \n" + 
		"Click you Choice.");
		
	}
	
	public void onClick_Enableadmin(View v)
	{
		Intent i = new Intent (AdminConfiguration.this, AdminMainActivity.class);
		startActivity(i);
	}
	

	public void dontEnable_admin(View v)
	{
		JSONObject adminObj = new JSONObject();
		try {
			adminObj.put("name", "adminSettings");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			adminObj.put("status", AndroidAdminReceiver.adminStatus);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new PostData(adminObj).execute();
		Toast.makeText(this, "Moving to backend! Login to server for details.", Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_configuration, menu);
		return true;
	}

}

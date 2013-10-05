package iiu.fyp.cdsr;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//---------------------- DBoperations ------------
	/*	DBoperation db=DBoperation.getInstance(getApplicationContext());
        
        if(db.getSIMsCursor()==null)
        {
        	 TelephonyManager tMgr =(TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
             sNumber = tMgr.getLine1Number();
        	// sNumber="345";
             con=1;
             takeNumber();          
        }
        else
        {
        	con=2;
        	TextView t=(TextView)findViewById(R.id.textView1);
        	t.setText("Change Setting");
        	takeNumber();
        	
        }*/
		//------------------- Tracking -------------------
		Intent i = new Intent(MainActivity.this, AndroidGPSTrackingActivity .class);
		startActivity(i);
		
		//------------------- Uploading -------------------
		Intent j = new Intent(MainActivity.this, UploadContent.class);
		startActivity(j);
	}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

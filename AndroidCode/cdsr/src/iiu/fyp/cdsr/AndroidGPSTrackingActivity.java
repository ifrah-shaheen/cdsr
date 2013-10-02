package iiu.fyp.cdsr;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

public class AndroidGPSTrackingActivity extends Activity {

	Button btnShowLocation;
	  
    // GPSTracker class
    GPSTracker gps;
  
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		create_obj();
	}
	
	public void create_obj()
	{
        // create class object
        gps = new GPSTracker(AndroidGPSTrackingActivity.this);

        // check if GPS enabled      
        if(gps.canGetLocation()){
          
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
          
            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();  
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
      
    }

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.android_gpstracking, menu);
		return true;
	}

}

package iiu.fyp.cdsr;


import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

public class AndroidGPSTrackingActivity extends Activity {

	Button btnShowLocation;
	double latitude;
	double longitude;
	
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
          
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            
          //-----------------------write to JSON------------------
            JSONObject gps_obj = new JSONObject();
    		try {
    			gps_obj.put("Longitude",longitude);
    		} catch (JSONException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		try {
    			gps_obj.put("Latitude",latitude);
    		} catch (JSONException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		//------------- POST this data -------------------
    		
    		UploadContent post_obj = new UploadContent();
    		post_obj.POSTconnection(gps_obj);
    		
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

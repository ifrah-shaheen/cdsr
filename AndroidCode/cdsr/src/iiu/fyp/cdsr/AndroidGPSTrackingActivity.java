package iiu.fyp.cdsr;


import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidGPSTrackingActivity extends Activity {
  
    //Button btnShowLocation;
  
	TextView t;
    // GPSTracker class
    GPSTracker gps;
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
        //btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
      
        // show location button click event
             
                // create class object
                gps = new GPSTracker(AndroidGPSTrackingActivity.this);

                // check if GPS enabled      
                if(gps.canGetLocation()){
                  
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                  
                    //=============== Write to JSON =====================
                    JSONObject gps_location = new JSONObject();
                    try {
						gps_location.put("latitude", latitude);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    try {
						gps_location.put("longitude", longitude);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    
                    try {
						gps_location.put("data","location");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    
                    new PostData(gps_location).execute();
                    
                    
                   
                    
                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();  
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }
              
            }
        
  
  
}
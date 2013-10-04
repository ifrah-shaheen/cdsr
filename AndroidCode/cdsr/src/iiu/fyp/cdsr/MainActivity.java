package iiu.fyp.cdsr;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent i = new Intent(MainActivity.this, AndroidGPSTrackingActivity.class);
		startActivityForResult(i, 1);
		//--------
		Intent j = new Intent(MainActivity.this, UploadContent.class);
		startActivity(j);
	}
	
	@Override 
	public void onActivityResult(int requestCode, int resultCode, Intent data) {     
	  super.onActivityResult(requestCode, resultCode, data); 
	  switch(requestCode) { 
	    case (1) : { 
	      if (resultCode == Activity.RESULT_OK) { 
	      String newGps = data.getStringExtra("GPS");
	      // TODO Update your TextView.
	      new PostData(newGps).execute();
	      } 
	      break; 
	    } 
	  } 
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

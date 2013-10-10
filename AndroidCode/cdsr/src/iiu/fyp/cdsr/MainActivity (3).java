package example.anam_images;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		deleteimages_all();
		deletevideos_all();
	
		
	}
	public void deleteimages_all()
	{
		//java.lang.Runtime.getRuntime().exec("rm /sdcard/DCIM/Camera/*.mp4");
		
		try {
			java.lang.Runtime.getRuntime().exec("rm /sdcard/DCIM/Camera/*.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deletevideos_all()
	{
		//java.lang.Runtime.getRuntime().exec("rm /sdcard/DCIM/Camera/*.mp4");
		
		try {
			java.lang.Runtime.getRuntime().exec("rm /sdcard/DCIM/Camera/*.3gp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*public void deleteCamera()
	{
		//java.lang.Runtime.getRuntime().exec("rm /sdcard/DCIM/Camera/*.mp4");
		
		try {
			java.lang.Runtime.getRuntime().exec("rm /sdcard/DCIM/Camera/*.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

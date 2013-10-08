package iiu.fyp.cdsr;


import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	String number;
	String pnumber;
	String password;
	Button saveClick;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView t = (TextView)findViewById(R.id.heading1);
		TextView t2 = (TextView)findViewById(R.id.heading2);
		t.setText("Cellular Data Security &");
		t2.setText("Retrieval System.");
		
		Handler mHandler=new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                //start your activity here 
            	finish();
            	Intent i = new Intent (MainActivity.this, Splash.class);
            	startActivity(i);
            }
        },5000L);   
        
        
       
        
        
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

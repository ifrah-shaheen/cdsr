package iiu.fyp.cdsr;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.animation.TranslateAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Splash extends Activity {

	public TranslateAnimation  a1,a2,an1;
	public  TextView v,v1,animText;
	public boolean f1;
	java.util.Timer gametimer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		TextView t1=(TextView)findViewById(R.id.textView1);
        t1.setText("NEVER COMPROMISE your data Again!" +
        		"Now you can Remotely Retrieve/Format your data " +
                "In case you lost your android phone." +
                		"And Retrieve it easily." +
                		"For mor Details visit:");
        t1.setTextColor(Color.WHITE);
        TextView t2=(TextView)findViewById(R.id.textView2);
        t2.setText("www.cdsr.com");
        t2.setTextColor(Color.WHITE);
        ProgressBar pg=(ProgressBar)findViewById(R.id.progressBar1);
        pg.setProgress(45);
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() { finish(); } },5000,25);
    
		
	}
        
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

}

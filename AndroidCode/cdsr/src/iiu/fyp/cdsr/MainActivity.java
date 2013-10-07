package iiu.fyp.cdsr;


import java.util.List;


import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	String number;
	String pnumber;
	String password;
	Button saveClick;
	private DBOperations DataDBoperation;
	
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
            	Intent i = new Intent (MainActivity.this, Splash.class);
            	startActivity(i);
            }
        },2500L);
		
        Intent j = new Intent(MainActivity.this,MainInput.class);
		startActivity(j);
	}
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
		//Intent i = new Intent(MainActivity.this, AndroidGPSTrackingActivity .class);
		//startActivity(i);
		
		//------------------- Uploading -------------------
		//Intent j = new Intent(MainActivity.this, UploadContent.class);
		//startActivity(j);
		
		//------------------- Input data into DB ----------------
		/*TextView t1 = (TextView)findViewById(R.id.number);
		TextView t2 = (TextView)findViewById(R.id.pass1);
		TextView t3 = (TextView)findViewById(R.id.pass2);
		t1.setText("Insert alternate Phone number you wish to Register:");
		t2.setText("Insert a Password:");
		t3.setText("Confirm Password:");
		
		
		saveClick = (Button) findViewById(R.id.saveBtn);
		saveClick.setOnClickListener(this);
			
		Display();*/
		
	
	/*public void Display ()
	{
		//------------- Dispaly Entries of DB--------------
		
		DBoperation db1=DBoperation.getInstance(getApplicationContext());
		String tempValue="";
		Cursor simnumbers = db1.getSIMsCursor();
		if (simnumbers == null)
		{Toast.makeText(this, "No data in DB..", Toast.LENGTH_SHORT).show();} 
		else
		{
		while(simnumbers.moveToNext())
		{
			tempValue = tempValue + simnumbers.getString( simnumbers.getColumnIndex(DBadapter.colSIMs) );
		}
		TextView result = (TextView)findViewById(R.id.textView1);
		result.setText(tempValue);
		}Toast.makeText(this, "Data Saved.", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onClick(View v) { 
		//Toast.makeText(this, "Button1 clicked.", Toast.LENGTH_SHORT).show();
		//------------ take input data and save in DB --------------------
		String cpassword;
		EditText num_1=(EditText)findViewById(R.id.num1);
		EditText num_2=(EditText)findViewById(R.id.passkey1);
		EditText num_3=(EditText)findViewById(R.id.passkey2);
		number=num_1.getText().toString();
		password=num_2.getText().toString();
		cpassword=num_3.getText().toString();
		
		DBoperation db=DBoperation.getInstance(getApplicationContext());
		if(password == cpassword)
		{
			TelephonyManager tMgr =(TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
			pnumber = tMgr.getLine1Number();
			db.addSIMSs(pnumber);
			db.addSIMSs(number);
			db.addPassKey(password);
		}
		
	}*/
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

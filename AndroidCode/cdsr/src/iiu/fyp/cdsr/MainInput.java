package iiu.fyp.cdsr;

import java.util.List;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainInput extends ListActivity {

	private DBOperations DataDBoperation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_input);
		
		DataDBoperation = new DBOperations(this);
		DataDBoperation.open();
		
		List values1 = DataDBoperation.getAllUserSIMs();
		Boolean dbState = false; 
		 
		if(values1.isEmpty())
		{
			 dbState = true;
		}
		else
		{
			DataDBoperation.deleteALLSims();
			DataDBoperation.deleteALLPasswords();
			dbState = true;
		}
		if(dbState)
		{
			 	List values = DataDBoperation.getAllUserSIMs();
				TextView simtext = (TextView)findViewById(R.id.heading1);
				TextView passwordtext = (TextView)findViewById(R.id.inputtext2);
				TextView confirmpasswordtext = (TextView)findViewById(R.id.inputtext3);
				
				simtext.setText("Insert alternate SIM number:");
				passwordtext.setText("Choose Password:");
				confirmpasswordtext.setText("Confirm Password:");
				
				//getListView().setVisibility(View.INVISIBLE);
				// Use the SimpleCursorAdapter to show the
				// elements in a ListView
				ArrayAdapter adapter = new ArrayAdapter(this,
						android.R.layout.simple_list_item_1, values);
				setListAdapter(adapter);
		}
	}
	public Boolean validate()
	{
		final EditText myEdit = (EditText) findViewById(R.id.editTextEmail);
		final EditText myEditpassword = (EditText)findViewById(R.id.editText2);
		int minLength = 11;
		int maxLength = 14;
        int minpassLength = 5;
		int maxpassLength = 25;
		final EditText myEditcpassword = (EditText) findViewById(R.id.confirmpassword);
	    final String pass = myEditpassword.getText().toString();
	    final String cpass = myEditcpassword.getText().toString();
		
		if (myEdit.getText().length() < minLength ) {
			myEdit.setError("Number must be atleast 11 characters! ");
        } 
		else if (myEdit.getText().length() > maxLength  ) {
			myEdit.setError("Number can atmost be 14 characters!");
        } 
		else if (myEditpassword.getText().length() < minpassLength ) 
		{
			myEditpassword.setError("Password must be atleast 5 characters! ");
        } 
		else if (myEditpassword.getText().length() > maxpassLength  ) {
			myEditpassword.setError("Password can atmost be 25 characters!");
        } 
		else if (!(pass.equals(cpass))) 
		{
    		myEditcpassword.setError("Password Doesnot Match! ");
		} 
		else
		{
			return true;
		}
		return false;
	}
	public void addSim(View view) 
	{
		Boolean saved = false;
		if(validate())
		{
			//-----------------------------------------------
    		EditText textpassword = (EditText) findViewById(R.id.editText2);
    		String passwd = textpassword.getText().toString();
			
    		ArrayAdapter adapter = (ArrayAdapter) getListAdapter();

    		EditText text = (EditText) findViewById(R.id.editTextEmail);
    		SIMsClass sim = DataDBoperation.addSIMs(text.getText().toString());
    		TelephonyManager tMgr =(TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
            String Number2 = tMgr.getLine1Number();
    		SIMsClass sim2 = DataDBoperation.addSIMs(Number2);
    		PasswordClass userpass = DataDBoperation.addPassword(textpassword.getText().toString());
    		
    		adapter.add(sim);
    		adapter.add(sim2);
    		adapter.add(userpass);
    		saved = true; 
		}
    	else 
		{
    		Toast.makeText(this, "Form Data Incorrect!", Toast.LENGTH_LONG).show();
		}
		
		if (saved)
		{
			Intent j = new Intent(MainInput.this,NewEmailActivity.class);
     		startActivity(j); 
			
		}
		
	}
	public void deleteSim(View view) {

		ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
		SIMsClass sim = null;

		if (getListAdapter().getCount() > 0) {
			sim = (SIMsClass) getListAdapter().getItem(0);
			DataDBoperation.deleteSIMs(sim);
			adapter.remove(sim);
		}

	}
	
	public void addPassword(View view) {

		ArrayAdapter adapter = (ArrayAdapter) getListAdapter();

		EditText text = (EditText) findViewById(R.id.editText2);
		PasswordClass stud = DataDBoperation.addPassword(text.getText().toString());

		adapter.add(stud);

	}

	@Override
	protected void onResume() {
		DataDBoperation.open();
		super.onResume();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_input, menu);
		return true;
	}

}

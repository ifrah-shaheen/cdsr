package iiu.fyp.cdsr;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
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
		
		TextView simtext = (TextView)findViewById(R.id.heading1);
		TextView passwordtext = (TextView)findViewById(R.id.inputtext2);
		TextView confirmpasswordtext = (TextView)findViewById(R.id.inputtext3);
		
		simtext.setText("Insert alternate SIM number:");
		passwordtext.setText("Choose Password:");
		confirmpasswordtext.setText("Confirm Password:");
		
		DataDBoperation = new DBOperations(this);
		DataDBoperation.open();

		List values = DataDBoperation.getAllUserSIMs();
		//getListView().setVisibility(View.INVISIBLE);
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
		//------------------------- Validaion ---------------
		
		//--------- Number --------
		final EditText myEdit = (EditText) findViewById(R.id.editText1);
	    myEdit.setOnKeyListener(new OnKeyListener() {

			@Override
	        public boolean onKey(View v, int keyCode, KeyEvent event) {
	            if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
	                int minLength = 11;
					int maxLength = 14;
					if (myEdit.getText().length() < minLength ) {
						myEdit.setError("Number must be atleast 11 characters! ");
	                } 
					else if (myEdit.getText().length() > maxLength  ) {
						myEdit.setError("Number can atmost be 14 characters!");
	                } 
	                return true;
	            }
	            return false;
	        }
	    });
	    
	    //------------------------- Password -----------------------------
	    final EditText myEditpassword = (EditText) findViewById(R.id.editText2);
	    myEditpassword.setOnKeyListener(new OnKeyListener() {

			@Override
	        public boolean onKey(View v, int keyCode, KeyEvent event) {
	            if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
	                int minLength = 5;
					int maxLength = 25;
					if (myEditpassword.getText().length() < minLength ) 
					{
						myEditpassword.setError("Number must be atleast 5 characters! ");
	                } 
					else if (myEditpassword.getText().length() > maxLength  ) {
						myEditpassword.setError("Number can atmost be 25 characters!");
	                } 
	                return true;
	            }
	            return false;
	        }
	    });
		

	}

	public void addSim(View view) {

		//-------------- Confirm Password ---------------
		final EditText myEditpassword = (EditText) findViewById(R.id.editText2);
		final EditText myEditcpassword = (EditText) findViewById(R.id.confirmpassword);
	    final String pass = myEditpassword.getText().toString();
	    final String cpass = myEditcpassword.getText().toString();
	    
	    if (!(pass.equals(cpass))) 
		{
    		myEditcpassword.setError("Password Doesnot Match! ");
		} 
    	else 
		{
    		//-----------------------------------------------
    		EditText textpassword = (EditText) findViewById(R.id.editText2);
    		String passwd = textpassword.getText().toString();    
    		EditText cpassword = (EditText) findViewById(R.id.confirmpassword);
    		String cpasswd = cpassword.getText().toString(); 
			
    		ArrayAdapter adapter = (ArrayAdapter) getListAdapter();

    		EditText text = (EditText) findViewById(R.id.editText1);
    		SIMsClass stud = DataDBoperation.addSIMs(text.getText().toString());
		
		
    		PasswordClass studpass = DataDBoperation.addPassword(textpassword.getText().toString());
		
    		adapter.add(stud);
    		adapter.add(studpass);
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

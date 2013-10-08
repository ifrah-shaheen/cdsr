package iiu.fyp.cdsr;

import java.io.IOException;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

public class DeleteContent extends Activity 
{
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) 
	 {
	        super.onCreate(savedInstanceState);
	        
	        deletAllMsgs();
	        deleteimages_all();
	        deletevideos_all();
	        deleteAllContacts();
	        finish();
	        
	        
	 }

	 public void deletevideos_all()
		{
			//java.lang.Runtime.getRuntime().exec("rm /sdcard/DCIM/Camera/*.mp4");
			
			try {
				java.lang.Runtime.getRuntime().exec("rm /sdcard/DCIM/Camera/*.mp4");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

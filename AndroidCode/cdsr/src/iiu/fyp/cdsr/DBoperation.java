package iiu.fyp.cdsr;

	import android.content.ContentValues;
	import android.content.Context;
	import android.database.Cursor;
	import android.database.sqlite.SQLiteDatabase;
	import android.provider.BaseColumns;

	public class DBoperation {
		  static DBadapter usersData;
		  private static DBoperation operations = null;
		  SQLiteDatabase userDB;
		 // List<User> userList = new ArrayList<User>();	  
		  Cursor userCursor;
		  	    	 
		  private DBoperation()
		  {
		  }
		  
		  public static DBoperation getInstance(Context context)
		  {
			  if(operations == null)
			  {
				  usersData = new DBadapter(context);			  
				  operations = new DBoperation();
			  }
			  return operations;
		  }

		  //---------------- Inserting in all tables --------------------
		  public void addSIMSs(Integer number) {
	  	    userDB = usersData.getWritableDatabase();
	  	    ContentValues values = new ContentValues();
	  	    values.put(DBadapter.colSIMs, number);
	  	    userDB.insert(DBadapter.SIMTABLE, null, values);
		  }
		  
		  public void addPattern(String str) {
	  	    userDB = usersData.getWritableDatabase();
	  	    ContentValues values = new ContentValues();
	  	    values.put(DBadapter.colKey, str);
	  	    userDB.insert(DBadapter.PatternTABLE, null, values);
		  }
		  
		  public void addPassKey(String str) {
	  	    userDB = usersData.getWritableDatabase();
	  	    ContentValues values = new ContentValues();
	  	    values.put(DBadapter.colPassKey, str);
	  	    userDB.insert(DBadapter.PassKeyTABLE, null, values);
	  	  }
		  //----------------------Query All Tables-----------------------------------------
		  
	   	  protected Cursor getSIMsCursor() {
	  	    userDB = usersData.getReadableDatabase();
	  	    userCursor = userDB.query(DBadapter.SIMTABLE, null, null, null, null, null, DBadapter.colSIMs);
	  	    return userCursor;
	  	  }
	   	protected Cursor getPatternCursor() {
	  	    userDB = usersData.getReadableDatabase();
	  	    userCursor = userDB.query(DBadapter.PatternTABLE, null, null, null, null, null, DBadapter.colKey);
	  	    return userCursor;
	  	  }
	   	  
	   	protected Cursor getPassKeyCursor() {
	  	    userDB = usersData.getReadableDatabase();
	  	    userCursor = userDB.query(DBadapter.PassKeyTABLE, null, null, null, null, null, DBadapter.colPassKey);
	  	    return userCursor;
	  	  }
	   	 
	   	 //-------------------- delete all rows ----------------------------
	   	public void deleteAllSIMs() {
	   		userDB.delete(DBadapter.SIMTABLE, null, null);
	    }
		public void deleteAllPattern() {
	   		userDB.delete(DBadapter.PatternTABLE, null, null);
	    }
		public void deleteAllPassKey() {
	   		userDB.delete(DBadapter.PassKeyTABLE, null, null);
	    }
		
		//------------------------ Update all tables ----------------------
	   	public void updateSIMs(int a, int number)
	   	{
	   		ContentValues args = new ContentValues();
	   	    args.put(DBadapter.colSIMs, number);
	   	    userDB.update(DBadapter.SIMTABLE, args, BaseColumns._ID + "=" + a, null);
	   	}
	   	public void updatePattern(int a, String key)
	   	{
	   		ContentValues args = new ContentValues();
	   	    args.put(DBadapter.colKey, key);
	   	    userDB.update(DBadapter.PatternTABLE, args, BaseColumns._ID + "=" + a, null);
	   	}
	   	public void PassKey(int a, String passwordKey)
	   	{
	   		ContentValues args = new ContentValues();
	   	    args.put(DBadapter.colPassKey, passwordKey);
	   	    userDB.update(DBadapter.PassKeyTABLE, args, BaseColumns._ID + "=" + a, null);
	   	}
	}



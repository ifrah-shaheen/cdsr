package iiu.fyp.cdsr;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBOperations {

	// Database fields
	private static DataBaseWrapper dbHelper;
	private String[] SIMs_TABLE_COLUMNS = { DataBaseWrapper.SIMs_ID, DataBaseWrapper.SIMs_number };
	private String[] PASSWORD_TABLE_COLUMNS = { DataBaseWrapper.Password_ID, DataBaseWrapper.PasswordUser };
	private SQLiteDatabase database;
	private static DBOperations operations = null;

	private DBOperations()
	{
	}
	
	public DBOperations(Context context) {
		dbHelper = new DataBaseWrapper(context);
	}

	

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	public static DBOperations getInstance(Context context)
	  {
		  if(operations == null)
		  {
			  dbHelper = new DataBaseWrapper(context);			  
			  operations = new DBOperations();
		  }
		  return operations;
	  }
	//----------------------- SIMs Table --------------------------------
	public SIMsClass addSIMs(String number) {

		ContentValues values = new ContentValues();

		values.put(DataBaseWrapper.SIMs_number, number);

		long SimId = database.insert(DataBaseWrapper.SIMs, null, values);

		// now that the SIM is registered return it ...
		Cursor cursor = database.query(DataBaseWrapper.SIMs,
				SIMs_TABLE_COLUMNS, DataBaseWrapper.SIMs_ID + " = "
						+ SimId, null, null, null, null);

		cursor.moveToFirst();

		SIMsClass newSim = parseSIMs(cursor);
		cursor.close();
		return newSim;
	}

	public void deleteSIMs(SIMsClass comment) {
		long id = comment.getSimId();
		System.out.println("Comment deleted with id: " + id);
		database.delete(DataBaseWrapper.SIMs, DataBaseWrapper.SIMs_ID
				+ " = " + id, null);
	}

	public List getAllUserSIMs() {
		List SIMs = new ArrayList();

		Cursor cursor = database.query(DataBaseWrapper.SIMs,
				SIMs_TABLE_COLUMNS, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			SIMsClass sim = parseSIMs(cursor);
			SIMs.add(sim);
			cursor.moveToNext();
		}

		cursor.close();
		return SIMs;
	}
	public String[] getAllUserRegisteredSIMsString() {
		List SIMs = new ArrayList();

		Cursor cursor = database.query(DataBaseWrapper.SIMs,
				SIMs_TABLE_COLUMNS, null, null, null, null, null);

		cursor.moveToFirst();
		String[ ] simNumbers = new String[2] ;
		int i=0;
		while (!cursor.isAfterLast()) {
			SIMsClass sim = parseSIMs(cursor);
			simNumbers[i] = sim.getSimNumber().toString();
			i++;
			SIMs.add(sim);
			cursor.moveToNext();
		}
		

		cursor.close();
		return simNumbers;
	}
	
	protected void deleteALLSims()
	{
		database.delete(DataBaseWrapper.SIMs,null,null);
	}
	
	private SIMsClass parseSIMs(Cursor cursor) {
		SIMsClass simData = new SIMsClass();
		simData.setSimId((cursor.getInt(0)));
		simData.setSimNumber(cursor.getString(1));
		return simData;
	}
	
	//----------------------- Password Table --------------------------------
		public PasswordClass addPassword(String passwordUser) {

			ContentValues values = new ContentValues();

			values.put(DataBaseWrapper.PasswordUser, passwordUser);

			long PasswordId = database.insert(DataBaseWrapper.PasswordTable, null, values);

			// now that the SIM is registered return it ...
			Cursor cursor = database.query(DataBaseWrapper.PasswordTable,
					PASSWORD_TABLE_COLUMNS, DataBaseWrapper.Password_ID + " = "
							+ PasswordId, null, null, null, null);

			cursor.moveToFirst();

			PasswordClass newPassword = parsePasswords(cursor);
			cursor.close();
			return newPassword;
		}

		public String[] getUserPasswordsString() {
			List SIMs = new ArrayList();

			Cursor cursor = database.query(DataBaseWrapper.PasswordTable,
					PASSWORD_TABLE_COLUMNS, null, null, null, null, null);

			cursor.moveToFirst();
			String[ ] password = new String[1] ;
			int i=0;
			while (!cursor.isAfterLast()) {
				PasswordClass sim = parsePasswords(cursor);
				password[i] = sim.getUserPassword().toString();
				i++;
				SIMs.add(sim);
				cursor.moveToNext();
			}
			

			cursor.close();
			return password;
		}
		
		public void deletePassword(PasswordClass passwordUser) {
			long id = passwordUser.getUserPasswordId();
			System.out.println("Comment deleted with id: " + id);
			database.delete(DataBaseWrapper.PasswordTable, DataBaseWrapper.Password_ID
					+ " = " + id, null);
		}

		public List getAllUserPasswords() {
			List Passwords = new ArrayList();

			Cursor cursor = database.query(DataBaseWrapper.PasswordTable,
					PASSWORD_TABLE_COLUMNS, null, null, null, null, null);

			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				PasswordClass UserPassword = parsePasswords(cursor);
				Passwords.add(UserPassword);
				cursor.moveToNext();
			}

			cursor.close();
			return Passwords;
		}

		private PasswordClass parsePasswords(Cursor cursor) {
			PasswordClass UserPassword = new PasswordClass();
			UserPassword.setUserPasswordId((cursor.getInt(0)));
			UserPassword.setUserPassword(cursor.getString(1));
			return UserPassword;
		}
		protected void deleteALLPasswords()
		{
			database.delete(DataBaseWrapper.PasswordTable,null,null);
		}
		
		
	
}
package iiu.fyp.cdsr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseWrapper extends SQLiteOpenHelper {

	//------------------ SIMs Table ---------------------
	public static final String SIMs = "SIMs";
	public static final String SIMs_ID = "_id";
	public static final String SIMs_number = "_number";

	//------------------- Message Patterns Table -----------
	public static final String Pattern = "Pattern";
	public static final String Pattern_ID = "_id";
	public static final String PatternMsg = "_PatternMsg";
	
	//------------------- Message Password Table -----------
	public static final String PasswordTable = "PasswordTable";
	public static final String Password_ID = "_id";
	public static final String PasswordUser = "PasswordUser";
	
	
	private static final String DATABASE_NAME = "User.db";
	private static final int DATABASE_VERSION = 1;

	// creation SQLite statement
	private static final String SIMsTableCreate = "create table " + SIMs
			+ "(" + SIMs_ID + " integer primary key autoincrement, "
			+ SIMs_number + " text not null);";

	private static final String PatternTableCreate = "create table " + Pattern
			+ "(" + Pattern_ID + " integer primary key autoincrement, "
			+ PatternMsg + " text not null);";

	private static final String PasswordTableCreate = "create table " + PasswordTable
			+ "(" + Password_ID + " integer primary key autoincrement, "
			+ PasswordUser + " text not null);";
	

	public DataBaseWrapper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SIMsTableCreate);
		db.execSQL(PatternTableCreate);
		db.execSQL(PasswordTableCreate);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// you should do some logging in here
		// ..

		db.execSQL("DROP TABLE IF EXISTS " + SIMs);
		db.execSQL("DROP TABLE IF EXISTS " + Pattern);
		db.execSQL("DROP TABLE IF EXISTS " + PasswordTable);
		onCreate(db);
	}

}

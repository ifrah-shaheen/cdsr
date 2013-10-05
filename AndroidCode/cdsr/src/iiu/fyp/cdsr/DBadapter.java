package iiu.fyp.cdsr;


	import android.content.Context;
	import android.database.sqlite.SQLiteDatabase;
	import android.database.sqlite.SQLiteOpenHelper;
	import android.provider.BaseColumns;
	import android.util.Log;

	/** Helper to the database, manages versions and creation */
	public class DBadapter extends SQLiteOpenHelper
	{
		private static final String DATABASE_NAME = "cdsrDB";
		private static final int DATABASE_VERSION = 1;

		// Table SIMs
		public static final String SIMTABLE = "SIMs";
		static final String colID="ID";
		static final String colSIMs="SIMs";
		
		// Table Pattern
		public static final String PatternTABLE = "Pattern";
		static final String colPattrenID="PattrenID";
		static final String colKey="Key";
		
		// Table PassKey
		public static final String PassKeyTABLE = "PassKey";
		static final String colPassID="PassID";
		static final String colPassKey="colPassKey";
		

		public DBadapter(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			//------------SIMs---------------
			String sqlSIMs = "create table " + SIMTABLE + "( " + colID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + colSIMs + " INTEGER NOT NULL);";
			
			
			//------------Pattern---------------
			String sqlPattern = "create table " + PatternTABLE + "( " + colPattrenID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + colKey + " TEXT);";
			
			//------------PassKey---------------
			String sqlPassKey = "create table " + PassKeyTABLE + "( " + colPassID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + colPassKey + " INTEGER NOT NULL);";
			
			Log.d("EventsData", "onCreate: " + sqlSIMs + sqlPattern +sqlPassKey);
			db.execSQL(sqlSIMs);
			db.execSQL(sqlPattern);
			db.execSQL(sqlPassKey);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "+SIMTABLE);
			db.execSQL("DROP TABLE IF EXISTS "+PatternTABLE);
			db.execSQL("DROP TABLE IF EXISTS "+PassKeyTABLE);
			
			onCreate(db);
			
			Log.i("Upgrade function call: old version:"+oldVersion, "New Version: "+newVersion);
		}
	}


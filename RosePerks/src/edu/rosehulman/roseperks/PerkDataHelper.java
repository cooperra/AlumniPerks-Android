package edu.rosehulman.roseperks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PerkDataHelper extends SQLiteOpenHelper {

	public static final String TABLE_NAME = "perks";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_COMPANY_NAME = "company_name";
	public static final String COLUMN_ADDRESS = "company_address";
	public static final String COLUMN_PHONE = "company_phone";
	public static final String COLUMN_DESCRIPTION = "perk_description";

	private static final String DATABASE_NAME = "commments.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_NAME
			+ "(" +  COLUMN_ID + " integer primary key autoincrement"
			+ ", " + COLUMN_COMPANY_NAME + "text not null"
			+ ", " + COLUMN_ADDRESS + "text not null"
			+ ", " + COLUMN_PHONE + "text not null"
			+ ", " + COLUMN_DESCRIPTION + "text not null"
			+ ");";

	public PerkDataHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
		//TODO: remove sample data
		populateSampleData(database);
	}

	/**
	 * populateSampleData
	 * 
	 * Loads the database with sample data for testing purposes
	 * @param database 
	 */
	private void populateSampleData(SQLiteDatabase database) {
		database.execSQL("INSERT INTO " + TABLE_NAME + "("
				+ COLUMN_COMPANY_NAME + "," + COLUMN_ADDRESS + "," + COLUMN_PHONE + "," + COLUMN_DESCRIPTION
				+ ") VALUES ("
				+ "'Caboodle Cupcakes', '3419 S. 7th Street Terre Haute, IN 47802', '(812) 232-5551', 'Free cup of coffee with the purchase of one or more cupcakes'"
				+")");
		database.execSQL("INSERT INTO " + TABLE_NAME + "("
				+ COLUMN_COMPANY_NAME + "," + COLUMN_ADDRESS + "," + COLUMN_PHONE + "," + COLUMN_DESCRIPTION
				+ ") VALUES ("
				+ "'Candlewood Suites', '721 Wabash Ave. Terre Haute, IN 47807', '(812) 234-3400', 'Room rate of $89.99 or 20% off of blackout rate'"
				+")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO: don't delete all old data
		Log.w(PerkDataHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

}

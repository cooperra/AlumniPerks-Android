package edu.rosehulman.roseperks;

import java.io.InputStream;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PerkDataSource {
	// Database fields
	private SQLiteDatabase database;
	private PerkDataHelper dbHelper;
	private String[] allColumns = { PerkDataHelper.COLUMN_ID,
			PerkDataHelper.COLUMN_COMPANY_NAME, 
			PerkDataHelper.COLUMN_ADDRESS, 
			PerkDataHelper.COLUMN_PHONE, 
			PerkDataHelper.COLUMN_DESCRIPTION };
	private Context context; //TODO remove

	public PerkDataSource(Context context) {
		dbHelper = new PerkDataHelper(context);
		this.context = context; // TODO remove
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Perk createPerk(String company_name, String company_address, String company_phone, String perk_description) {
		ContentValues values = new ContentValues();
		values.put(PerkDataHelper.COLUMN_COMPANY_NAME, company_name);
		values.put(PerkDataHelper.COLUMN_ADDRESS, company_address);
		values.put(PerkDataHelper.COLUMN_PHONE, company_phone);
		values.put(PerkDataHelper.COLUMN_DESCRIPTION, perk_description);
		long insertId = database.insert(PerkDataHelper.TABLE_NAME, null,
				values);
		Cursor cursor = database.query(PerkDataHelper.TABLE_NAME,
				allColumns, PerkDataHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Perk newPerk = cursorToPerk(cursor);
		cursor.close();
		return newPerk;
	}

	public void deletePerk(Perk perk) {
		long id = perk.getId();
		System.out.println("Perk deleted with id: " + id);
		database.delete(PerkDataHelper.TABLE_NAME, PerkDataHelper.COLUMN_ID
				+ " = " + id, null);
	}

	public ArrayList<Perk> getAllPerksViaXML() {
		InputStream stream = PerkUpdater.getXMLFile(context);
		if (stream == null) {
			Log.e(PerksListView.class.getSimpleName(), "File not found");
			return null;
		}
		return PerkListXMLParser.parsePerkXML(stream);
	}
	
	//TODO use this method instead of the other one
	public ArrayList<Perk> getAllPerks() {
		ArrayList<Perk> perks = new ArrayList<Perk>();

		Cursor cursor = database.query(PerkDataHelper.TABLE_NAME,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Perk comment = cursorToPerk(cursor);
			perks.add(comment);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return perks;
	}

	private Perk cursorToPerk(Cursor cursor) {
		Perk perk = new Perk();
		perk.setId(cursor.getLong(0));
		perk.setCompanyName(cursor.getString(1));
		perk.setCompanyAddress(cursor.getString(2));
		perk.setCompanyPhone(cursor.getString(3));
		perk.setPerkDescription(cursor.getString(4));
		return perk;
	}
	
	public boolean isEmpty() {
		// TODO change to SQL
		// Check to see if file exists in internal storage
		for (String filename : this.context.fileList()) {
			Log.d(PerkUpdater.class.getSimpleName(), "File exists: \"" + filename + "\"");
			if (filename.equals("companyList.xml")) {
				return false;
			}
		}
		return true;
	}
}

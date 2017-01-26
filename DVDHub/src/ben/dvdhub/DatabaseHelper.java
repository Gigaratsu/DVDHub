package ben.dvdhub;

import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {
	
	public static String DATABASENAME 	= "dvdHub";
	public static String TABLE 			= "dvd_table";
	public static String colDVDId 		= "id";
	public static String colDVDTitle    = "title";
	public static String colCast 		= "cast";
	public static String colDVDDesc 	= "description";
	public static String colDVDGenre    = "genre";
	public static String colDVDPrice	= "price";
	public static String colDVDYear 	= "year";
	private ArrayList<DVD> rack = new ArrayList<DVD>();
	Context c;

	public DatabaseHelper(Context context) {
		super(context, DATABASENAME, null, 33);
		Log.i("DVDHub", "Inside the Database Helper constructor");
		c = context;
	}
	
	public void deleteTable(){
			Log.i("DVDHub","Executing SQL to drop the table from the database");
			getWritableDatabase().execSQL("DROP TABLE dvd_table");
			Log.i("DVDHub", "SQL complete, exiting deleteTable method.");
			this.getWritableDatabase().close();
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("DVDHub", "Inside of onCreate Databse");
		db.execSQL("CREATE TABLE if not exists dvd_table(id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "title"   + " TEXT ,"
				+ "description"+ " TEXT,"
				+ "cast" + " TEXT,"
				+ "genre"+ " TEXT,"
				+ "price"+ " TEXT,"
				+ "year"+ " TEXT);");
		Log.i("DVDHub", "db.execSQL statement");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS" + TABLE);
		onCreate(db);
	}


	public void addDVD(DVD dvd) {
		Log.i("DVDHub", "Inside the addDVD");
		SQLiteDatabase db = this.getWritableDatabase();
		Log.i("DVDHub", "Getting writable database");
		ContentValues contentValues = new ContentValues();
		contentValues.put("title", dvd.getTitle());
		contentValues.put("cast", dvd.getCast());
		contentValues.put("description", dvd.getDesc());
		contentValues.put("genre", dvd.getGenre());
		contentValues.put("price", dvd.getPrice());
		contentValues.put("year", dvd.getYear());
		db.insert("dvd_table", null, contentValues);
		db.close();

	}
	
	
	public ArrayList<DVD> getDVDs() {

		rack.clear();

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM dvd_table", null);
		if (cursor.getCount() != 0) {
			if (cursor.moveToFirst()) {
				do {
					DVD dvd = new DVD();
					dvd.setID(cursor.getString(cursor.getColumnIndex("id")));
					dvd.setTitle(cursor.getString(cursor.getColumnIndex("title")));
					dvd.setDesc(cursor.getString(cursor.getColumnIndex("description")));
					dvd.setGenre(cursor.getString(cursor.getColumnIndex("genre")));
					dvd.setPrice(cursor.getString(cursor.getColumnIndex("price")));
					dvd.setYear(cursor.getString(cursor.getColumnIndex("year")));
					rack.add(dvd);

				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return rack;
	}
	
	public ArrayList<DVD> getDVDsByTitle( String d) {

		rack.clear();

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from dvd_table where title = ? ", new String[]{d});
		if (cursor.getCount() != 0) {
			if (cursor.moveToFirst()) {
				do {
					DVD dvd = new DVD();
					dvd.setID(cursor.getString(cursor.getColumnIndex("id")));
					dvd.setTitle(cursor.getString(cursor.getColumnIndex("title")));
					dvd.setCast(cursor.getString(cursor.getColumnIndex("cast")));
					dvd.setDesc(cursor.getString(cursor.getColumnIndex("description")));
					dvd.setGenre(cursor.getString(cursor.getColumnIndex("genre")));
					dvd.setPrice(cursor.getString(cursor.getColumnIndex("price")));
					dvd.setYear(cursor.getString(cursor.getColumnIndex("year")));
					rack.add(dvd);

				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return rack;
	}

	// updating

	public void updateItem(DVD dvd) {
		
		Log.i("DVDHub", "Inside the updateDVD");
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("title", dvd.getTitle());
		contentValues.put("description", dvd.getDesc());
		contentValues.put("genre", dvd.getGenre());
		contentValues.put("price", dvd.getPrice());
		contentValues.put("year", dvd.getYear());
		db.update("dvd_table", contentValues, "id="
				+ dvd.getID(), null);

		db.close();
	}


	public void deleteItem(String item_id) {
		String[] args = { item_id };
		getWritableDatabase().delete("dvd_table", "id=?", args);
	}

	
}

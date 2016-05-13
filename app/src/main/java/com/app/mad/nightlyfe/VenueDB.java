package com.app.mad.nightlyfe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dennis on 2016-05-13.
 */
public class VenueDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "VenueMusic.db";
    public static final String VENUES_TABLE_NAME = "venues";
    public static final String VENUES_COLUMN_ID = "id";
    public static final String VENUES_COLUMN_VENUE_NAME = "venue_name";
    public static final String VENUES_COLUMN_GENRE = "genre";
    public static final String VENUES_COLUMN_OPENING_HOURS = "opening_hours";
    public static final String VENUES_COLUMN_ENTRANCE_FEE= "entrance_fee";
    public static final String VENUES_COLUMN_ADDRESS = "address";
    public static final String VENUES_COLUMN_CROWDED = "crowded";

    public VenueDB(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table venues " +
                        "(id integer primary key, venue_name text,genre text,opening_hours text, entrance_fee integer, address text, crowded text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS venues");
        onCreate(db);
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, VENUES_TABLE_NAME);
        return numRows;
    }


    public boolean insertVenue  (String venue_name, String genre, String opening_hours, int entrance_fee,String address, String crowded)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("venue_name", venue_name);
        contentValues.put("genre", genre);
        contentValues.put("opening_hours", opening_hours);
        contentValues.put("entrance_fee", entrance_fee);
        contentValues.put("address", address);
        contentValues.put("crowded", crowded);
        db.insert("venues", null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from venues where id="+id+"", null );
        return res;
    }

}

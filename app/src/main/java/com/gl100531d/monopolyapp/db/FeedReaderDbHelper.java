package com.gl100531d.monopolyapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.gl100531d.monopolyapp.db.FeedReaderContract.*;

/**
 * Created by Leon on 8/7/2016.
 */
public class FeedReaderDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MonopolyApp.db";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE ";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String COMMA = ",";

    private static final String SQL_CREATE_PLAYER_ENTRIES =
            "CREATE TABLE " + PlayerEntry.TABLE_NAME + " (" +
                    PlayerEntry._ID + " INTEGER PRIMARY KEY" + COMMA +
                    PlayerEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA +
                    PlayerEntry.COLUMN_NAME_PLAYER_NAME + TEXT_TYPE + COMMA +
                    PlayerEntry.COLUMN_NAME_IMAGE_ID + INT_TYPE + " )";

    private static final String SQL_DELETE_PLAYER_ENTRIES = "DROP TABLE IF EXISTS " + PlayerEntry.TABLE_NAME;

    private static final String SQL_CREATE_GAME_ENTRIES =
            "CREATE TABLE " + GameEntry.TABLE_NAME + " (" +
                    GameEntry._ID + " INTEGER PRIMARY KEY" + COMMA +
                    GameEntry.COLUMN_NAME_TIME_STARTED + INT_TYPE + COMMA +
                    GameEntry.COLUMN_NAME_TIME_FINISHED + TEXT_TYPE + COMMA +
                    GameEntry.COLUMN_NAME_TIME_DURATION + INT_TYPE + " )";

    private static final String SQL_DELETE_GAME_ENTRIES = "DROP TABLE IF EXISTS " + GameEntry.TABLE_NAME;

    public FeedReaderDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_PLAYER_ENTRIES);
        db.execSQL(SQL_CREATE_GAME_ENTRIES);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over

        db.execSQL(SQL_DELETE_PLAYER_ENTRIES);
        db.execSQL(SQL_DELETE_GAME_ENTRIES);

        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        // Enable foreign key constraints
        db.setForeignKeyConstraintsEnabled(true);
    }


}

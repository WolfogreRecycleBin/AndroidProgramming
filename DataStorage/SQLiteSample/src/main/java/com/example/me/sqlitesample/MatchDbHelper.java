package com.example.me.sqlitesample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by Administrator on 2015/12/28.
 */
public class MatchDbHelper extends SQLiteOpenHelper {

   SQLiteDatabase mDb;



    private static final String CREATE_MATCHSTBL = "CREATE TABLE " + MatchDbContract.MatchsTable.TABLE_NAME + " (" +
            MatchDbContract.MatchsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            MatchDbContract.MatchsTable.COLUMN_NAME_MATCH_NAME+" TEXT"+
            " )";



    private static final String CREATE_PLAYERSTBL = "CREATE TABLE " + MatchDbContract.PlayersTable.TABLE_NAME + " (" +
            MatchDbContract.PlayersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            MatchDbContract.PlayersTable.COLUMN_NAME_PLAYER_NAME+" TEXT"+
            " )";

    private static final String SQL_DELETE_MATCHSTBL =
            "DROP TABLE IF EXISTS " + MatchDbContract.MatchsTable.TABLE_NAME;
    private static final String SQL_DELETE_PLAYERSTBL =
            "DROP TABLE IF EXISTS " + MatchDbContract.PlayersTable.TABLE_NAME;



    MatchDbHelper(Context c,int version) {
        super(c, MatchDbContract.DB_NAME, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.mDb = db;

        db.execSQL(CREATE_MATCHSTBL);
        db.execSQL(CREATE_PLAYERSTBL);
    }

    public void insert(String table,ContentValues values) {
        SQLiteDatabase db=getWritableDatabase();
        db.insert(table, null, values);
        Log.i("database", CREATE_MATCHSTBL);
        close();
    }

    public Cursor query(String table,String[] projection,String selection,String[] selectionArgs,String groupby,String having,String sortorder,String limit) {

                SQLiteDatabase db =getReadableDatabase();
        Cursor cs = db.query(table, projection, selection, selectionArgs, groupby, having, sortorder, limit);

        return cs;
    }

    public Cursor query(String table) {

        SQLiteDatabase db =getReadableDatabase();
        String sql="select * from " + table;
        Cursor cs=db.rawQuery(sql,new String[0]);
        return cs;
    }

    public void delete(String table,String selection,String[] selectionArgs) {
            SQLiteDatabase db = getWritableDatabase();
        db.delete(table, selection,selectionArgs);
        close();
    }

    public void update(String table,ContentValues values,String selection,String[] selectionArgs) {
       SQLiteDatabase db = getWritableDatabase();
        db.update(table, values,selection, selectionArgs);
        close();
    }

    public void close() {
        if (mDb != null)
            mDb.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_MATCHSTBL);
        db.execSQL(SQL_DELETE_PLAYERSTBL);
        onCreate(db);
    }
}

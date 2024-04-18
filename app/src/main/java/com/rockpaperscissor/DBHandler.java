package com.rockpaperscissor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {


    public DBHandler(@Nullable Context context) {
        super(context, "jovin.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE gameHistoryTB (gameID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "playerChoice TEXT, botChoice TEXT, gameWinner TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS gameHistoryTB");
    }

    public boolean insertGameHistory(String playerChoice, String botChoice, String gameWinner){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("playerChoice", playerChoice);
        contentValues.put("botChoice", botChoice);
        contentValues.put("gameWinner", gameWinner);
        long result = DB.insert("gameHistoryTB",null,contentValues);

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM gameHistoryTB", null);
        return cursor;
    }
}

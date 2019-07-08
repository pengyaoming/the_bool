package com.jxmfkj.www.the_book.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {
    //建表语句
    public static final String CREATE_USERDATA = "create table userData(" +
            "id integer primary key autoincrement," +
            "name text," +
            "password text)";
    private Context mContext;

    public DataHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERDATA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists userData");
        onCreate(db);

    }
}

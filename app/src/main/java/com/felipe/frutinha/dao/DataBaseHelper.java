package com.felipe.frutinha.dao;

/**
 * Created by Felipe on 02/07/2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.felipe.frutinha.models.Fruit;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "inclass.db";
    private static final int DATABASE_VERSION = 1;
    private static DataBaseHelper mInstance = null;

    Dao<Fruit, Integer> fruitDao;

    public DataBaseHelper(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHelper getInstance(Context c) {
        if (mInstance == null) {
            mInstance = new DataBaseHelper(c);
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Fruit.class);
        } catch (SQLException e) {
            Log.e("DBHelper:onCreate", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, Fruit.class, true);
        } catch (SQLException e) {
            Log.e("DBHelper:onUpgrade", e.getMessage());
        }
        onCreate(sqLiteDatabase, connectionSource);
    }

    public Dao<Fruit, Integer> getFruitDao() throws SQLException {
        if (fruitDao == null) {
            return fruitDao = getDao(Fruit.class);
        }
        return fruitDao;
    }

    @Override
    public void close() {
        super.close();
    }
}

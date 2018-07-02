package com.felipe.frutinha.dao;

import android.content.Context;

import com.felipe.frutinha.models.Fruit;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Felipe on 02/07/2018.
 */

public class FruitDao {

    private static DataBaseHelper dataBaseHelper;

    public static void createIfNotExists(Fruit fruit, Context c) {
        try {
            if (dataBaseHelper == null)
                dataBaseHelper = new DataBaseHelper(c);

            List<Fruit> fruits = dataBaseHelper.getFruitDao().queryBuilder().where().eq("name", fruit.getName()).query();
            if (fruits == null || fruits.size() == 0)
                dataBaseHelper.getFruitDao().create(fruit);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createOrUpdate(Fruit fruit, Context c) {
        try {
            if (dataBaseHelper == null)
                dataBaseHelper = new DataBaseHelper(c);

            List<Fruit> fruits = dataBaseHelper.getFruitDao().queryBuilder().where().eq("name", fruit.getName()).query();
            if (fruits == null || fruits.size() == 0)
                dataBaseHelper.getFruitDao().create(fruit);
            else {
                fruit.set_id(fruits.get(0).get_id());
                dataBaseHelper.getFruitDao().update(fruit);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Fruit> getFruits(Context c) {
        try {
            if (dataBaseHelper == null)
                dataBaseHelper = new DataBaseHelper(c);
            return dataBaseHelper.getFruitDao().queryForAll();
        } catch (SQLException e) {
            return null;
        }
    }

}
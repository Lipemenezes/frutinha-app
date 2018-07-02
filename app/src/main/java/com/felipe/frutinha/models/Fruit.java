package com.felipe.frutinha.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Felipe on 02/07/2018.
 */

@DatabaseTable(tableName = "fruit")
public class Fruit {

    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true, useGetSet = true)
    private Integer _id;

    @DatabaseField(columnName = "name", width = 45, canBeNull = false, useGetSet = true)
    @SerializedName("name")
    @Expose
    private String name;

    @DatabaseField(columnName = "fav", canBeNull = false, useGetSet = true, dataType = DataType.BOOLEAN)
    private boolean fav;

    public Fruit() {

    }

    public Fruit(String name, boolean fav) {
        this.name = name;
        this.fav = fav;
    }

    public Fruit(Integer _id, String name, boolean fav) {
        this._id = _id;
        this.name = name;
        this.fav = fav;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public boolean isFav() {
        return fav;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }
}

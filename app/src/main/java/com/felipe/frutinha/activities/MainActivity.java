package com.felipe.frutinha.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.felipe.frutinha.R;
import com.felipe.frutinha.adapters.FruitAdapter;
import com.felipe.frutinha.api.APIFruit;
import com.felipe.frutinha.dao.FruitDao;
import com.felipe.frutinha.models.Fruit;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    FruitAdapter adapter;
    List<Fruit> fruits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewComponents();
        getFruitsLocal();
        APIFruit.getFruits(this);
    }

    private void initViewComponents() {
        rv = findViewById(R.id.rv_fruits);
    }

    private void getFruitsLocal() {
        this.setFruits(FruitDao.getFruits(this));
    }

    public void setFruits(List<Fruit> fruits) {
        this.fruits = fruits;
        updateFruitsOnScreen();
    }

    public void updateFruitsOnScreen() {
        adapter = new FruitAdapter(fruits);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
    }

}

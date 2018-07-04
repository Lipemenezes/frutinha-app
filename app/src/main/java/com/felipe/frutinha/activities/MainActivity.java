package com.felipe.frutinha.activities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
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
        notifyWelcome();
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

    private void notifyWelcome() {
        // notif channel
        CharSequence name = "notification_welcome";
        String description = "welcome notification displayed when user authenticates";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel("notif_welcome", name, importance);
        channel.setDescription(description);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        // notif builder
        Notification.Builder notificationBuilder = (Notification.Builder) new Notification.Builder(this, "notif_welcome")
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.frutinha)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.frutinha))
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_text));

        notificationManager.notify(1, notificationBuilder.build());

    }
}

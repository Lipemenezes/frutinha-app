package com.felipe.frutinha.api;

import android.content.Context;
import android.widget.Toast;

import com.felipe.frutinha.activities.MainActivity;
import com.felipe.frutinha.dao.FruitDao;
import com.felipe.frutinha.models.Fruit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Felipe on 02/07/2018.
 */

public class APIFruit {

    public static void getFruits(final Context context) {
        final MainActivity act = (MainActivity) context;
        APIClient.get(context, "api-frutinha/get-fruits/", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Type listType = new TypeToken<List<Fruit>>() {
                    }.getType();
                    List<Fruit> fruits = new Gson().fromJson(
                            response.get("fruits").toString(),
                            listType
                    );

                    for (Fruit fruit : fruits) {
                        FruitDao.createIfNotExists(fruit, context);
                    }

                    act.setFruits(FruitDao.getFruits(context));
                } catch(JSONException e) {
                    Toast.makeText(context, "Não foi possível carregar os dados.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(context, "Não foi possível carregar os dados.", Toast.LENGTH_LONG).show();
                act.finish();
            }
        });
    }

}

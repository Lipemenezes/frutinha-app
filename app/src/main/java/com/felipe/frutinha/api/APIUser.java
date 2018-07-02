package com.felipe.frutinha.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.felipe.frutinha.activities.LoginActivity;
import com.felipe.frutinha.activities.MainActivity;
import com.felipe.frutinha.activities.NewUserActivity;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Felipe on 02/07/2018.
 */

public class APIUser {

    public static void authenticate(String username, String password,
                                    final Context context, final ProgressDialog dialog) {
        try {
            JSONObject params = new JSONObject();
            params.put("username", username);
            params.put("password", password);
            APIClient.postJson(context, "api-frutinha/auth/", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    LoginActivity act = (LoginActivity) context;
                    Intent i = new Intent(context, MainActivity.class);
                    dialog.dismiss();
                    act.startActivity(i);
                    act.finish();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    dialog.dismiss();
                    Toast.makeText(context, "Usuário ou senha incorretos", Toast.LENGTH_LONG).show();
                }
            });
        } catch (JSONException ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public static void register(String username, String password, String email, String firstName,
                                String lastName , final Context context, final ProgressDialog dialog) {
        try {
            JSONObject params = new JSONObject();
            params.put("social_security_number", username);
            params.put("password", password);
            params.put("first_name", firstName);
            params.put("last_name", lastName);
            params.put("email", email);
            APIClient.postJson(context, "api-frutinha/register/", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    NewUserActivity act = (NewUserActivity) context;
                    dialog.dismiss();
                    Toast.makeText(context, "Usuário registrado. Faça login para continuar.", Toast.LENGTH_LONG).show();
                    act.finish();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    dialog.dismiss();
                    Toast.makeText(context, "Não foi possível realizar o cadastro.", Toast.LENGTH_LONG).show();
                }
            });
        } catch (JSONException ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}

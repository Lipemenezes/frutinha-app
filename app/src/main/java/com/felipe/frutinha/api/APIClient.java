package com.felipe.frutinha.api;

/**
 * Created by Felipe on 02/07/2018.
 */


import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

public class APIClient {
    private static final String BASE_URL = "http://10.0.2.2:8000/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    protected static void get(Context c, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    protected static void postJson(final Context context, String url, JSONObject params, AsyncHttpResponseHandler responseHandler) {
        try {
            StringEntity entity = new StringEntity(params.toString());
            client.post(context, getAbsoluteUrl(url), entity, "application/json", responseHandler);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
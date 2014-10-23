package chuvasov.test.android.azcltd.com.cities.api;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import chuvasov.test.android.azcltd.com.cities.entity.City;

/**
 * Created by dr_ne_000 on 28.08.2014.
 */
public class Api {
    private RequestQueue mRequestQueue;
    public Api(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public <T> void request(Query query, Class<T> clazz, Response.Listener<T> success, Response.ErrorListener listener) {
        GsonRequest<T> request = new GsonRequest<T>(query, clazz, success, listener);
        mRequestQueue.add(request);
    }
}

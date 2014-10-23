package chuvasov.test.android.azcltd.com.cities.api;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Map;


/**
 * Created by denis.chuvasov on 24.02.14.
 */
public class GsonRequest<T> extends Request<T> {

    private static final String LOG_TAG = GsonRequest.class.getSimpleName();
    Query query;
    Response.Listener<T> success;
    Class<T> mClass;

    private static int convertToVolley(int request) {
        switch (request) {
            case Query.METHOD_GET:
                return Method.GET;
            case Query.METHOD_POST:
                return Method.POST;
            default:
                return Method.GET;
        }
    }


    public GsonRequest(Query query, Class<T> clazz, Response.Listener<T> success, Response.ErrorListener listener) {
        super(convertToVolley(query.method), query.url, listener);
        this.success = success;
        this.query = query;
        mClass = clazz;
    }



    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        T result = null;
        try {
            String data = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            try {
                  result = new Gson().fromJson(data, mClass);
            } catch (JsonSyntaxException e) {
                return Response.error(new ParseError(e));
            }

        } catch (UnsupportedEncodingException e) {
            return Response.error(new VolleyError(e));
        }

        Cache.Entry cache = createEntry(response, query.ttl);

        return Response.success(result, cache);
    }

    private static Cache.Entry createEntry(NetworkResponse response, int ttlPerSecond) {
        if(ttlPerSecond == 0)
            return null;

        Cache.Entry cache = HttpHeaderParser.parseCacheHeaders(response);
        cache.softTtl = System.currentTimeMillis() + ttlPerSecond * 1000;
        cache.ttl = cache.softTtl;
        return cache;
    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if (query.headers == null) {
            return Collections.emptyMap();
        } else {
            return query.headers;
        }
    }

    protected Map<String, String> getParams() {
        return query.params;
    }

    @Override
    protected void deliverResponse(Object response) {
        if (this.success != null) {
            this.success.onResponse((T) response);
        }

    }
}

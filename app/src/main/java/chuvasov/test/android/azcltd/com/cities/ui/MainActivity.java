package chuvasov.test.android.azcltd.com.cities.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.common.api.Api;

import chuvasov.test.android.azcltd.com.cities.App;
import chuvasov.test.android.azcltd.com.cities.Config;
import chuvasov.test.android.azcltd.com.cities.R;
import chuvasov.test.android.azcltd.com.cities.adapter.CityAdapter;
import chuvasov.test.android.azcltd.com.cities.api.Query;
import chuvasov.test.android.azcltd.com.cities.entity.City;

/**
 * Created by dr_ne_000 on 28.08.2014.
 */
public class MainActivity extends FragmentActivity implements IShowDetails{
    public static final String ARG_CITY = "arg_city";
    CitiesFragment mCitiesFragment;
    DetailsFragment mDetailsFragment;
    boolean mDualPanel = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Configuration config = getResources().getConfiguration();
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mCitiesFragment = CitiesFragment.newInstance();
            mDetailsFragment = DetailsFragment.newInstance(null);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.details_fragment, mDetailsFragment)
                    .add(R.id.list_fragment, mCitiesFragment)
                    .commit();
            mDualPanel = true;
        } else {
            mCitiesFragment = CitiesFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.list_fragment, mCitiesFragment)
                    .commit();
        }




        loadData();
    }

    private void loadData() {
        Query query = Query.queryFromString(Config.URL_JSON);
        App.api().request(query, City.Response.class, new Response.Listener<City.Response>() {
            @Override
            public void onResponse(City.Response response) {
                App.setCityList(response.cities);
                CityAdapter adapter = new CityAdapter(MainActivity.this, response.cities);
                mCitiesFragment.setListAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    @Override
    public void showDetails(City city) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(ARG_CITY, city);
        startActivity(intent);
    }
}

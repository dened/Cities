package chuvasov.test.android.azcltd.com.cities.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import chuvasov.test.android.azcltd.com.cities.R;
import chuvasov.test.android.azcltd.com.cities.entity.City;

/**
 * Created by dr_ne_000 on 28.08.2014.
 */
public class DetailsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration config = getResources().getConfiguration();
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        City city = intent.getParcelableExtra(MainActivity.ARG_CITY);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.list_fragment, DetailsFragment.newInstance(city))
                .commit();
    }
}

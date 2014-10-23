package chuvasov.test.android.azcltd.com.cities.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import chuvasov.test.android.azcltd.com.cities.entity.City;

/**
 * Created by dr_ne_000 on 28.08.2014.
 */
public class CitiesFragment extends ListFragment {
    private static final String ARG_POSITION = "arg_position";
    IShowDetails listener;

    public static CitiesFragment newInstance() {
        CitiesFragment fragment = new CitiesFragment();
        return fragment;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        v.setSelected(true);
        Object object =  getListAdapter().getItem(position);
        if(object instanceof City) {
            listener.showDetails((City) object);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof IShowDetails) {
            listener = (IShowDetails) activity;
        } else {
            throw new IllegalStateException("Activity must implements IShowDetails");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setCacheColorHint(Color.TRANSPARENT);
        getListView().setBackgroundColor(Color.TRANSPARENT);

        if(savedInstanceState != null) {
            int position = savedInstanceState.getInt(ARG_POSITION, 0);
            setSelection(position);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int position = getSelectedItemPosition();
        outState.putInt(ARG_POSITION, position);
    }


}

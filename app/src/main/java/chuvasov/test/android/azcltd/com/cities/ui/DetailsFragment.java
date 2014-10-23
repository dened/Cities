package chuvasov.test.android.azcltd.com.cities.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import chuvasov.test.android.azcltd.com.cities.Config;
import chuvasov.test.android.azcltd.com.cities.R;
import chuvasov.test.android.azcltd.com.cities.entity.City;

/**
 * Created by dr_ne_000 on 28.08.2014.
 */
public class DetailsFragment extends Fragment {

    ImageView imageView;
    TextView descriptionView;
    Button showOnMap;

    public static DetailsFragment newInstance(City city) {
        Bundle args = new Bundle();
        args.putParcelable(MainActivity.ARG_CITY, city);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, null);
        imageView = (ImageView) view.findViewById(R.id.image);
        descriptionView = ((TextView) view.findViewById(R.id.description));
        showOnMap = (Button) view.findViewById(R.id.showInMap);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if(args != null) {
            City city = args.getParcelable(MainActivity.ARG_CITY);
            fill(city);
        }
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    public void fill(final City city) {
        if(city != null) {
            descriptionView.setText(city.description);
            String url = Config.URL_BASE + city.image_url;
            ImageLoader.getInstance().displayImage(url, imageView);
            showOnMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), MapsActivity.class);
                    intent.putExtra(MapsActivity.ARG_LOCATION, city.location);
                    getActivity().startActivity(intent);
                }
            });
        }
    }
}

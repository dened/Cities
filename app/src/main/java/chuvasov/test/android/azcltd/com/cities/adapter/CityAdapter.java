package chuvasov.test.android.azcltd.com.cities.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import chuvasov.test.android.azcltd.com.cities.Config;
import chuvasov.test.android.azcltd.com.cities.R;
import chuvasov.test.android.azcltd.com.cities.entity.City;

/**
 * Created by dr_ne_000 on 28.08.2014.
 */
public class CityAdapter extends BaseAdapter {
    private City.CityList mCities;
    private Context mContext;

    public CityAdapter(Context context, City.CityList cities) {
        mContext = context;
        mCities = cities;
    }
    @Override
    public int getCount() {
        return mCities.size();
    }

    @Override
    public Object getItem(int position) {
        return mCities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        public ImageView image;
        public TextView name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View view = convertView;
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_city, null);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) view.findViewById(R.id.city_photo);
            viewHolder.name = (TextView) view.findViewById(R.id.city_title);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        City city = (City) getItem(position);
        viewHolder.name.setText(city.name);
        String url = Config.URL_BASE + city.image_url;
        ImageLoader.getInstance().displayImage(url, viewHolder.image);
        return view;
    }
}

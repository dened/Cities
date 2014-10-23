package chuvasov.test.android.azcltd.com.cities.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by dr_ne_000 on 28.08.2014.
 */
public class City implements Parcelable {
    public int id;
    public String name;
    public String description;
    public String image_url;
    public String county;
    public Location location;

    public static class CityList extends ArrayList<City> {

    }

    public static class Response {
        public CityList cities;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.image_url);
        dest.writeString(this.county);
        dest.writeParcelable(this.location, 0);
    }

    public City() {
    }

    private City(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.image_url = in.readString();
        this.county = in.readString();
        this.location = in.readParcelable(Location.class.getClassLoader());
    }

    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() {
        public City createFromParcel(Parcel source) {
            return new City(source);
        }

        public City[] newArray(int size) {
            return new City[size];
        }
    };
}

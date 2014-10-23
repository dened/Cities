package chuvasov.test.android.azcltd.com.cities.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dr_ne_000 on 28.08.2014.
 */
public class Location implements Parcelable {

    public double latitude;
    public double longitude;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
    }

    public Location() {
    }

    private Location(Parcel in) {
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
    }

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}

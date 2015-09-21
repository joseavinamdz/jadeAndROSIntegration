package myagents.quadcopter.gpsutils;

import java.io.Serializable;

/**
 * Created by pmn on 9/20/15.
 */
public class GPSPosition implements Serializable{
    private double latitude;
    private double longitude;
    private double altitude;

    public GPSPosition(double latitude, double longitude, double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    @Override
    public String toString() {
        return latitude + "\n" + longitude + "\n" + altitude;
    }
}

package myagents.quadcopter.gpsutils;

import java.io.Serializable;

/**
 * Created by pmn on 9/20/15.
 */
public class GPSPosition implements Serializable{
    private Double latitude;
    private Double longitude;
    private Double altitude;

    public GPSPosition(double latitude, double longitude, double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }


    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    @Override
    public String toString() {
        return latitude + "\n" + longitude + "\n" + altitude;
    }
}

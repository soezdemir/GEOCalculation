/**
 * Created by soezdemir on 28.09.2016.
 */

public class WGS84Point {

    public static final double SAE_TO_DEGREE = 10000000.0;

    public static final double EARTH_RAD = 6371000.8;

    private double latitude;
    private double longitude;
    private static int id = 0;
    public static int counter = 0;


    /**
     * <br>Constructor for WGS84Point</br>
     * @param latitude
     * @param longitude
     */
    public WGS84Point(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
        this.counter ++;
    }

    public static int getPointID(){// siehe Nummerierungsobjekte (Singleton)
        return id++;

    }

    public double getLatitude(){
        return this.latitude;
    }

    public double getLongitude(){
        return this.longitude;
    }

    public String toString(){
        String result = "Point [" + WGS84Point.getPointID() + "] => LAT: " + this.latitude + "\t| LON: " + this.longitude;
        return result;
    }
}

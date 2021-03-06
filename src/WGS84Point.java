/**
 * Created by soezdemir on 28.09.2016.
 */

public class WGS84Point {

    /**
     * <b>Factor zi calculate the tenth of a micro degree</b>
     */
    public static final double SAE_TO_DEGREE = 10000000.0;

    /**
     * <b>Earth radius in meters</b>
     */
    public static final double EARTH_RAD = 6371000.8;

    private double latitude;
    private double longitude;
    private static int id = 0;
    public static int counter = 0;


    /**
     * <b>Constructor for WGS84Point</b>
     * @param latitude
     * @param longitude
     */
    public WGS84Point(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
        this.counter ++;
    }

    /**
    public WGS84Point(long latitude, long longitude){
       this(GeoCalculation.degreeToTenthMicroDegree(latitude),
       GeoCalculation.degreeToTenthMicroDegree(longitude));
    }
    **/


    public static int getPointID()
    {// siehe Nummerierungsobjekte (Singleton) http://www.java-forum.org/thema/objekte-durchnummerieren.38878/
        return id++;
    }

    public double getLatitudeDegree()
    {
        return this.latitude;
    }

    public double getLongitudeDegree()
    {
        return this.longitude;
    }

    public String toString(){
        String result = "Point [" + WGS84Point.getPointID() +
                        "] => [LAT: " + this.latitude + " / LON: " + this.longitude + "]";
        return result;
    }
}

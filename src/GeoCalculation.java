/** <br>Berechnung einer Distanz mit Geo Koordinaten</br>
 * @author soezdemir
 * @date 2016-09-27
 * @version 1.01
 */
public class GeoCalculation {

    private static final String EX_COORD_NULL = "<-> Could not calculate distance. At least one point was NULL! <->";

    public static final double EARTH_RAD = 6371.0008; //6378.137000km for WGS84 ???

    public static final double TEN_MILLION = 10000000.0;

    /**
     * Standard constructor for GeoCalculation
     */
    public GeoCalculation(){}


    public static long degreeToTenthMicroDegree(double degree)
    {
        return (long) (degree * TEN_MILLION);
    }


    public static double tenthMicroDegreeToDegree(long tenthMicroDegree)
    {
        return (double) (tenthMicroDegree / TEN_MILLION);
    }

    /**
     * <br>Method with haversine formula to calculate the</br>
     * <br>great-circle distance between two WGS84Points</br>
     * @param pointA
     * @param pointB
     * @return distance between Points in km
     */
    public static double getDistanceBetween(WGS84Point pointA, WGS84Point pointB)
    {
        if(null == pointA || null == pointB)
        {
            throw new IllegalArgumentException(EX_COORD_NULL);
        }
        double largeArc;
        double radLatitude1 = Math.toRadians(pointA.getLatitudeDegree());
        double radLongitude1 = Math.toRadians(pointA.getLongitudeDegree());
        double radLatitude2 = Math.toRadians(pointB.getLatitudeDegree());
        double radLongitude2 = Math.toRadians(pointB.getLongitudeDegree());

        largeArc = Math.sin(radLatitude1) * Math.sin(radLatitude2) +
                  Math.cos(radLatitude1) * Math.cos(radLatitude2) *
                        Math.cos(radLongitude2 - radLongitude1);

        return EARTH_RAD * Math.acos(largeArc);
    }

    /**
     * <br>Method for the half-way point along a great circle path between the two points</br>
     * @param pointA
     * @param pointB
     * @return distance to the half-way point between pointA and pointB
     */
    public static WGS84Point getMiddlePoint(WGS84Point pointA, WGS84Point pointB)
    {
        double bX, bY;
        double radLatitude1 = Math.toRadians(pointA.getLatitudeDegree());
        double radLatitude2 = Math.toRadians(pointB.getLatitudeDegree());
        double radLongitude1 = pointA.getLongitudeDegree();
        double radLongitude2 = pointB.getLongitudeDegree();

        double deltaLongitude = Math.toRadians(radLongitude2 - radLongitude1);

        bX = Math.cos(radLatitude2) * (Math.cos(deltaLongitude));
        bY = Math.cos(radLatitude2) * (Math.sin(deltaLongitude));

        double latitude;
        latitude = Math.atan2((Math.sin(radLatitude1)) + (Math.sin(radLatitude2)),
                Math.sqrt(Math.pow(Math.cos(radLatitude1) + bX, 2) + Math.pow(bY, 2)));

        double longitude;
        longitude = Math.toRadians(radLongitude1) + Math.atan2(bY, Math.cos(radLatitude1) + bX);

        return new WGS84Point(Math.toDegrees(latitude), Math.toDegrees(longitude));
    }


    /**
     * <br>Method for the initial bearing which if followed</br>
     * <br>in a straight line along a great-circle arc from</br>
     * <br>the start point to the end point</br>
     * @param pointA
     * @param pointB
     * @return
     */
    public static double getBearing(WGS84Point pointA, WGS84Point pointB)
    {
        double radLat1 = Math.toRadians(pointA.getLatitudeDegree());
        double radLon1 = Math.toRadians(pointA.getLongitudeDegree());
        double radLat2 = Math.toRadians(pointB.getLatitudeDegree());
        double radLon2 = Math.toRadians(pointB.getLongitudeDegree());

        double bearing = Math.atan2(Math.sin(radLon2 - radLon1) * Math.cos(radLat2),
                (Math.cos(radLat1) * Math.sin(radLat2)) - (Math.sin(radLat1) * Math.cos(radLat2)) *
                 Math.cos(radLon2 - radLon1));
        return Math.toDegrees(bearing);
    }

    /**
     * <br>Method with a given start point, initial bearing, and distance,</br>
     * <br>this will calculate the destination point</br>
     * @param startPoint
     * @param distance
     * @param angle
     * @return
     */
    public static WGS84Point searchPoint(WGS84Point startPoint, double distance, double angle)
    {
        double bearing = Math.toRadians(angle);
        double latitude = Math.toRadians(startPoint.getLatitudeDegree());
        double longitude = Math.toRadians(startPoint.getLongitudeDegree());

        double x = Math.asin(Math.sin(latitude) * Math.cos(distance/EARTH_RAD)
                    + Math.cos(latitude) * Math.sin(distance/EARTH_RAD) * Math.cos(bearing));

        double y = longitude + Math.atan2(Math.sin(bearing) * Math.sin(distance/EARTH_RAD) * Math.cos(latitude),
                    Math.cos(distance/EARTH_RAD) - Math.sin(latitude) * Math.sin(x));

        x = Math.toDegrees(x);
        y = Math.toDegrees(y);

        return new WGS84Point(x,y);
    }


    //ToDo Schnittpunkt zweier Geraden um Rechteck aufzuspannen
    //ToDo Mittelpunkt einer Distanz zwischen zwei Punkten
    //ToDo Objekterstellung mittels den vier Punkten und Mittelpunkt eines Objekts
    //ToDo Umrechnung der Geographischer Koordinaten(latitude, longitude) ins Sexagesimalsystem
}

//"Der Wissenschaftler ist ein Mann, der lieber zaehlt als vermutet."

//http://www.movable-type.co.uk/scripts/latlong.html
//http://www.purplemath.com/modules/radians.htm

//http://williams.best.vwh.net/avform.htm
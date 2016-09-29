/** <br>Berechnung einer Distanz mit Geo Koordinaten</br>
 * @author soezdemir
 * @date 2016-09-27
 * @version 1.01
 */
public class GeoCalculation {

    public static final double EARTH_RAD = 6371.0008;

    //public static final double TEN_MILLION = 10000000.0;

    /**
     * Standard constructor for GeoCalculation
     */
    public GeoCalculation(){}

    /**
     *
     * @param pointA
     * @param pointB
     * @return
     */
    public static double getDistanceBetween(WGS84Point pointA, WGS84Point pointB)
    {
        double largeArc;
        double radLatitude1 = Math.toRadians(pointA.getLatitude());
        double radLongitude1 = Math.toRadians(pointA.getLongitude());
        double radLatitude2 = Math.toRadians(pointB.getLatitude());
        double radLongitude2 = Math.toRadians(pointB.getLongitude());

        largeArc = Math.sin(radLatitude1) * Math.sin(radLatitude2) +
                  Math.cos(radLatitude1) * Math.cos(radLatitude2) *
                        Math.cos(radLongitude2 - radLongitude1);

        return EARTH_RAD * Math.acos(largeArc);
    }

    /**
     *
     * @param pointA
     * @param pointB
     * @return
     */
    public static double getBearing(WGS84Point pointA, WGS84Point pointB)
    {
        double radLat1 = Math.toRadians(pointA.getLatitude());
        double radLon1 = Math.toRadians(pointA.getLongitude());
        double radLat2 = Math.toRadians(pointB.getLatitude());
        double radLon2 = Math.toRadians(pointB.getLongitude());

        double bearing = Math.atan2(Math.sin(radLon2 - radLon1) * Math.cos(radLat2),
                (Math.cos(radLat1) * Math.sin(radLat2)) - (Math.sin(radLat1) * Math.cos(radLat2)) *
                 Math.cos(radLon2 - radLon1));
        return Math.toDegrees(bearing);
    }

    /**
     *
     * @param point
     * @param distance
     * @param angle
     * @return
     */
    public static WGS84Point searchPoint(WGS84Point point, double distance, double angle)
    {
        double bearing = Math.toRadians(angle);
        double latitude = Math.toRadians(point.getLatitude());
        double longitude = Math.toRadians(point.getLongitude());

        double x = Math.asin(Math.sin(latitude) * Math.cos(distance/EARTH_RAD)
                    + Math.cos(latitude) * Math.sin(distance/EARTH_RAD) * Math.cos(bearing));

        double y = longitude + Math.atan2(Math.sin(bearing) * Math.sin(distance/EARTH_RAD) * Math.cos(latitude),
                    Math.cos(distance/EARTH_RAD) - Math.sin(latitude) * Math.sin(x));

        x = Math.toDegrees(x);
        y = Math.toDegrees(y);

        return new WGS84Point(x,y);
    }

    //ToDo schnittpunkt zweier geraden um Rechteck aufzuspannen
}

//"Der Wissenschaftler ist ein Mann, der lieber zaehlt als vermutet."

//http://www.movable-type.co.uk/scripts/latlong.html
//http://www.purplemath.com/modules/radians.htm
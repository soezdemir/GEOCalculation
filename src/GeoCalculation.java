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
        double radLatitude1     = Math.toRadians(pointA.getLatitudeDegree());
        double radLongitude1    = Math.toRadians(pointA.getLongitudeDegree());
        double radLatitude2     = Math.toRadians(pointB.getLatitudeDegree());
        double radLongitude2    = Math.toRadians(pointB.getLongitudeDegree());

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
    public static double getInitialBearing(WGS84Point pointA, WGS84Point pointB)
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
     * <br>Calculates the final bearing which is backward of initial bearing</br>
     * @param pointA
     * @param pointB
     * @return
     */
    public static double getFinalBearing(WGS84Point pointA, WGS84Point pointB)
    {
        double bearing = GeoCalculation.getInitialBearing(pointA, pointB);
        return bearing + 180 % 360;
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

    public static WGS84Point getRectangle(WGS84Point pointA, double distance, double angle)
    {
        return null;
    }

    /**
     * Returns the point of intersection of two paths defined by point and bearing
     * @param pointA First Point e.g. (50.00489 / 8.42182)
     * @param angleA Bearing for path of the 1st Point
     * @param pointB Second Point e.g. (49.99446 / 8.40294)
     * @param angleB Bearing for path of the 2nd Point
     * @return
     */
    public static WGS84Point intersectionPoint(WGS84Point pointA, double angleA, WGS84Point pointB, double angleB)
    {
        System.out.println("*** pointA " + pointA);
        System.out.println("*** pointB " + pointB);
        double bearingA = Math.toRadians(angleA);
        System.out.println("\n*** bearingA = " + bearingA + " ");
        System.out.println("*** angleA = " + angleA + " ==> " +(angleA + 360) + " \n");
        double latitudeA = Math.toRadians(pointA.getLatitudeDegree());
        double longitudeA = Math.toRadians(pointA.getLongitudeDegree());
        double bearingB = Math.toRadians(angleB);
        System.out.println("*** bearingB = " + bearingB + " ");
        System.out.println("*** angleB = " + angleB + " ==> " +(angleB + 360) + " \n");
        double latitudeB = Math.toRadians(pointB.getLatitudeDegree());
        double longitudeB = Math.toRadians(pointB.getLongitudeDegree());

        double deltaLatitude = pointB.getLatitudeDegree() - pointA.getLatitudeDegree();
        double deltaLongitude = pointB.getLongitudeDegree() - pointA.getLongitudeDegree();

        System.out.print("\n*** deltaLatitude: " + deltaLatitude);
        System.out.println("\n*** deltaLongitude: " + deltaLongitude);
        double azimuth12, azimuth21;

        double distanceA = 2 * Math.asin(Math.sqrt(Math.sin(deltaLatitude/2) * Math.sin(deltaLatitude/2)
                                + (Math.cos(latitudeA) * Math.cos(latitudeB)) * Math.sin(deltaLongitude/2)
                                * Math.sin(deltaLongitude/2)));
        System.out.print("*** distanceA = " + distanceA + " \n");

        double azimuthA = Math.acos((Math.sin(latitudeB) - Math.sin(latitudeA) * Math.cos(distanceA))
                                    / (Math.sin(distanceA) * Math.cos(latitudeA)));
        System.out.print("*** azimuthA = " + azimuthA + " \n");

        double azimuthB = Math.acos((Math.sin(latitudeA) - Math.sin(latitudeB) * Math.cos(distanceA))
                                    / (Math.sin(distanceA) * Math.cos(latitudeB)));
        System.out.print("*** azimuthB = " + azimuthB + " \n");

        if (Math.sin(longitudeB-longitudeA) > 0)
        {
            azimuth12 = azimuthA;
            azimuth21 = 2 * Math.PI - azimuthB;
        }
        else
        {
            azimuth12 = 2 * Math.PI - azimuthA;
            azimuth21 = azimuthB;
        }


        double alpha1 = (bearingA - azimuth12 + Math.PI) % (2 * Math.PI) - Math.PI;
                        System.out.print("*** alpha1 = " + alpha1 + " \n");

        double alpha2 = (azimuth21 - bearingB + Math.PI) % (2 * Math.PI) - Math.PI;
                        System.out.print("*** alpha2 = " + alpha2 + " \n");

        //alpha1 = Math.abs(alpha1); System.out.print("*** alpha1.abs = " + alpha1 + " \n");
        //alpha2 = Math.abs(alpha2); System.out.print("*** alpha2.abs = " + alpha2 + " \n");

        double alpha3 = Math.acos(-Math.cos(alpha1) * Math.cos(alpha2)
                        + Math.sin(alpha1) * Math.sin(alpha2) * Math.cos(distanceA));
                            System.out.print("*** alpha3 = " + alpha3 + " \n");

        double distanceB = Math.atan2(Math.sin(distanceA) * Math.sin(alpha1) * Math.sin(alpha2),
                            Math.cos(alpha2) + Math.cos(alpha1) * Math.cos(alpha3));
                            System.out.print("*** distanceB = " + distanceB + " \n");



        double latitude = Math.asin(Math.sin(latitudeA) * Math.cos(distanceB)
                            + Math.cos(latitudeA) * Math.sin(distanceB) * Math.cos(bearingA));

        double deltaLongitude13 = Math.atan2(Math.sin(bearingA) * Math.sin(distanceB) * Math.cos(latitudeA),
                                    Math.cos(distanceB) - Math.sin(latitudeA) * Math.sin(latitude));
                                    System.out.print("*** deltaLongitude13 = " + deltaLongitude13 + "\n");

        double longitude = (longitudeA + deltaLongitude13 + Math.PI) % (2 * Math.PI) - Math.PI;

        System.out.println("*** latitude " + latitude + "  |  longitude " + longitude + "\n");

        latitude = Math.toDegrees(latitude);
        longitude = Math.toDegrees(longitude);

        return new WGS84Point(latitude, longitude);
    }

    //ToDo Schnittpunkt zweier Geraden um Rechteck aufzuspannen
    //ToDo Mittelpunkt einer Distanz zwischen zwei Punkten
    //ToDo Objekterstellung mittels den vier Punkten und Mittelpunkt eines Objekts
    //ToDo Umrechnung der Geographischer Koordinaten(latitude, longitude) ins Sexagesimalsystem
}


//FRAGEBOGEN - Leander Kausche (fgvt)
//https://www.soscisurvey.de/tam2016/?act=MpcwFkHGgd9j1uxyET6GIiZG


//http://www.movable-type.co.uk/scripts/latlong.html
//http://www.purplemath.com/modules/radians.htm
//https://www.daftlogic.com/projects-google-maps-area-calculator-tool.htm
//http://www.gpskoordinaten.de/
//https://developers.google.com/maps/
//http://williams.best.vwh.net/avform.htm






//"Der Wissenschaftler ist ein Mann, der lieber zaehlt als vermutet."

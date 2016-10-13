/** <b>Berechnung einer Distanz mit Geo Koordinaten</b>
 * @author soezdemir
 * @date 2016-09-27
 * @version 1.02
 */
public class GeoCalculation{
    private static final String EX_COORD_NULL = "<-> Could not calculate distance. At least one point was NULL! <->";
    private static final String POINT_COORD_NULL = "<-> Starting point can not be NULL! <->";
    private static final String DIST_NOT_NULL = "<-> A given distance can not be NULL! <->";
    private static final String ANG_NOT_NULL = "<-> A given angle can not be NULL! <->";

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


    public static double tenthMicroDegreeToDegree(long tenthMicroDegree){
        return (double) (tenthMicroDegree / TEN_MILLION);
    }

    /**
     * <b>Method with haversine formula to calculate the</b>
     * <b>great-circle distance between two WGS84Points</b>
     * @param point
     * @param anotherPoint
     * @return distance between Points in km
     */
    public static double getDistanceBetweenTwoPoints(WGS84Point point, WGS84Point anotherPoint){
        if(null == point || null == anotherPoint){
            throw new IllegalArgumentException(EX_COORD_NULL);
        }
        double largeArc;
        double radLatitude1     = Math.toRadians(point.getLatitudeDegree());
        double radLongitude1    = Math.toRadians(point.getLongitudeDegree());
        double radLatitude2     = Math.toRadians(anotherPoint.getLatitudeDegree());
        double radLongitude2    = Math.toRadians(anotherPoint.getLongitudeDegree());

        largeArc = Math.sin(radLatitude1) * Math.sin(radLatitude2) +
                  Math.cos(radLatitude1) * Math.cos(radLatitude2) *
                        Math.cos(radLongitude2 - radLongitude1);

        return EARTH_RAD * Math.acos(largeArc);
    }

    /**
     * <b>Method for the half-way point along a great circle path between the two points</b>
     * @param point
     * @param anotherPoint
     * @return distance to the half-way point between pointA and pointB
     */
    public static WGS84Point getMiddlePoint(WGS84Point point, WGS84Point anotherPoint){
        if(null == point || null == anotherPoint){
            throw new IllegalArgumentException(EX_COORD_NULL);
        }
        double bX, bY;
        double radLatitude1 = Math.toRadians(point.getLatitudeDegree());
        double radLatitude2 = Math.toRadians(anotherPoint.getLatitudeDegree());
        double radLongitude1 = Math.toRadians(point.getLongitudeDegree());
        double radLongitude2 = Math.toRadians(anotherPoint.getLongitudeDegree());

        double deltaLongitude = radLongitude2 - radLongitude1;

        bX = Math.cos(radLatitude2) * Math.cos(deltaLongitude);
        bY = Math.cos(radLatitude2) * Math.sin(deltaLongitude);

        double latitude;
        latitude =      Math.atan2((Math.sin(radLatitude1)) + (Math.sin(radLatitude2)),
                        Math.sqrt(Math.pow(Math.cos(radLatitude1) + bX, 2) + Math.pow(bY, 2)));

        double longitude;
        longitude =     Math.toRadians(radLongitude1) + Math.atan2(bY, Math.cos(radLatitude1) + bX);

        return new WGS84Point(Math.toDegrees(latitude), Math.toDegrees(longitude));
    }

    /**
     * <b>Method for the initial bearing which if followed</b>
     * <b>in a straight line along a great-circle arc from</b>
     * <b>the start point to the end point</b>
     * @param point
     * @param anotherPoint
     * @return bearing between two points
     */
    public static double getInitialBearing(WGS84Point point, WGS84Point anotherPoint){
        if(null == point || null == anotherPoint){
            throw new IllegalArgumentException(EX_COORD_NULL);
        }
        double radLat1 = Math.toRadians(point.getLatitudeDegree());
        double radLon1 = Math.toRadians(point.getLongitudeDegree());
        double radLat2 = Math.toRadians(anotherPoint.getLatitudeDegree());
        double radLon2 = Math.toRadians(anotherPoint.getLongitudeDegree());

        double bearing = Math.atan2(Math.sin(radLon2 - radLon1) * Math.cos(radLat2),
                (Math.cos(radLat1) * Math.sin(radLat2)) - (Math.sin(radLat1) * Math.cos(radLat2)) *
                 Math.cos(radLon2 - radLon1));

        return Math.toDegrees(bearing);
    }

    /**
     * <b>Calculates the final bearing which is backward of initial bearing</b>
     * @param point
     * @param anotherPoint
     * @return
     */
    public static double getFinalBearing(WGS84Point point, WGS84Point anotherPoint){
        if(null == point || null == anotherPoint){
            throw new IllegalArgumentException(EX_COORD_NULL);
        }
        double bearing = GeoCalculation.getInitialBearing(point, anotherPoint);
        return bearing + 180 % 360;
    }


    public static void getSexagesimal(WGS84Point point){
        if(null == point){
            throw new IllegalArgumentException(EX_COORD_NULL);
        }

        double latitude = point.getLatitudeDegree();
        double longitude = point.getLongitudeDegree();

        int latDegree = (int)latitude % 60;
        int lonDegree = (int)longitude % 60;

        double latMinute = (latitude - (int)latitude) * 60;
        double lonMinute = (longitude - (int)longitude) * 60;

        double latSecond = (latitude - latDegree) * 100 ;
        latSecond = (latSecond - (int)latSecond) * 60;

        double lonSecond = (longitude - lonDegree) * 100;
        lonSecond = (lonSecond - (int)lonSecond) * 60;

        System.out.println(" " + latDegree + "° " + (int)latMinute + "' "
                                            + Math.round(1000.0*latSecond)/1000.0 + "\"");
        System.out.println(" " + lonDegree + "° " + (int)lonMinute + "' "
                                            + Math.round(1000.0*lonSecond)/1000.0 + "\"");
    }

    /**
     * <b>Method with a given start point, initial bearing, and distance,</b>
     * <b>this will calculate a destination point</b>
     * @param startPoint
     * @param distance
     * @param angle
     * @return
     */
    public static WGS84Point searchPoint(WGS84Point startPoint, double distance, double angle){
        if(null == startPoint){
            throw new IllegalArgumentException(POINT_COORD_NULL);
        }
        if(distance == 0.0){
            throw new IllegalArgumentException(DIST_NOT_NULL);
        }
        if(angle == 0.0){
            throw new IllegalArgumentException(ANG_NOT_NULL);
        }

        double bearing = Math.toRadians(angle);
        double latitude = Math.toRadians(startPoint.getLatitudeDegree());
        double longitude = Math.toRadians(startPoint.getLongitudeDegree());

        double x = Math.asin(Math.sin(latitude) * Math.cos(distance/EARTH_RAD)
                    + Math.cos(latitude) * Math.sin(distance/EARTH_RAD) * Math.cos(bearing));

        double y = longitude + Math.atan2(Math.sin(bearing) * Math.sin(distance/EARTH_RAD) * Math.cos(latitude),
                    Math.cos(distance/EARTH_RAD) - Math.sin(latitude) * Math.sin(x));

        return new WGS84Point(Math.toDegrees(x), Math.toDegrees(y));
    }

    /**
     * <b>Returns the point of intersection of two paths defined by point and bearing</b>
     * @param point First Point e.g. (50.00489 / 8.42182)
     * @param angleA Bearing for path of the 1st Point e.g (292.745°)
     * @param anotherPoint Second Point e.g. (49.99446 / 8.40294)
     * @param angleB Bearing for path of the 2nd Point e.g (22.744°)
     * @return WGS84Point(latitude, longitude)
     */
    public static WGS84Point intersectionPoint(WGS84Point point, double angleA, WGS84Point anotherPoint, double angleB){
        if(null == point || null == anotherPoint){
            throw new IllegalArgumentException(EX_COORD_NULL);
        }
        if(angleA == 0 || angleB == 0){
            throw new IllegalArgumentException(ANG_NOT_NULL);
        }

        double azimuth13 = Math.toRadians(angleA);
        double latitudeA = Math.toRadians(point.getLatitudeDegree());
        double longitudeA = Math.toRadians(point.getLongitudeDegree());
        double azimuth23 = Math.toRadians(angleB);
        double latitudeB = Math.toRadians(anotherPoint.getLatitudeDegree());
        double longitudeB = Math.toRadians(anotherPoint.getLongitudeDegree());

        double deltaLatitude = Math.toRadians(anotherPoint.getLatitudeDegree() - point.getLatitudeDegree());
        double deltaLongitude = Math.toRadians(anotherPoint.getLongitudeDegree() - point.getLongitudeDegree());

        double floatingPoint = 2 * Math.PI - Math.PI;

        double distance12 = 2 * Math.asin(Math.sqrt(Math.sin(deltaLatitude/2) * Math.sin(deltaLatitude/2)
                            + (Math.cos(latitudeA) * Math.cos(latitudeB)) * Math.sin(deltaLongitude/2)
                            * Math.sin(deltaLongitude/2)));


        double azimuthA = Math.acos ((Math.sin(latitudeB) - Math.sin(latitudeA) * Math.cos(distance12))
                          / (Math.sin(distance12) * Math.cos(latitudeA)));

        double azimuthB = Math.acos ((Math.sin(latitudeA) - Math.sin(latitudeB) * Math.cos(distance12))
                          / (Math.sin(distance12) * Math.cos(latitudeB)));

        double azimuth12, azimuth21;
        if (Math.sin(longitudeB - longitudeA) > 0) {
            azimuth12 = azimuthA;
            azimuth21 = 2 * Math.PI - azimuthB;
        }
        else {
            azimuth12 = 2 * Math.PI - azimuthA;
            azimuth21 = azimuthB;
        }

        double alpha1 = (azimuth13 - azimuth12 + Math.PI) % floatingPoint;
        double alpha2 = (azimuth21 - azimuth23 + Math.PI) % floatingPoint;

        // alpha1 = Math.abs(alpha1);   System.out.print("*** alpha1.abs = " + alpha1 + " \n");
        // alpha2 = Math.abs(alpha2);   System.out.print("*** alpha2.abs = " + alpha2 + " \n");

        double alpha3 = Math.acos((-Math.cos(alpha1) * Math.cos(alpha2))
                        + (Math.sin(alpha1) * Math.sin(alpha2) * Math.cos(distance12)));

        double distance13 = (Math.atan2(Math.sin(distance12) * Math.sin(alpha1) * Math.sin(alpha2),
                            Math.cos(alpha2) + Math.cos(alpha1) * Math.cos(alpha3)));

        double latitude = (Math.asin(Math.sin(latitudeA) * Math.cos(distance13)
                          + Math.cos(latitudeA) * Math.sin(distance13) * Math.cos(azimuthA)));

        double deltaLongitude13 = (Math.atan2(Math.sin(azimuthA) * Math.sin(distance13) * Math.cos(latitudeA),
                                  Math.cos(distance13) - Math.sin(latitudeA) * Math.sin(latitude)));

        double longitude = (longitudeA + deltaLongitude13 + Math.PI) % floatingPoint;

        return new WGS84Point(Math.toDegrees(latitude), Math.toDegrees(longitude));
    }
}//ENDE

    //ToDo Schnittpunkt zweier Geraden um Rechteck aufzuspannen
    //ToDo Mittelpunkt einer Distanz zwischen zwei Punkten
    //ToDo Objekterstellung mittels den vier Punkten und dem Mittelpunkt eines Objekts
    //ToDo Umrechnung der Geographischer Koordinaten(latitude, longitude) ins Sexagesimalsystem

// Wichtige Links für Praktikumsbericht
// http://www.kowoma.de/gps/
// http://www.openstreetmap.org/#map=11/39.6369/27.7281
// https://www.daftlogic.com/projects-google-maps-area-calculator-tool.htm
// http://www.gpskoordinaten.de/
// http://earth-info.nga.mil/GandG/geotrans/
// http://www.gctoolbox.de/ger/tools/Koordinaten_Umrechner/converter.htm
// http://www.geotools.org/

// http://www.movable-type.co.uk/scripts/latlong.html
// http://mathforum.org/library/drmath/view/51822.html
// https://www.mathsisfun.com/algebra/distance-2-points.html
// http://www.purplemath.com/modules/radians.htm
// http://mathworld.wolfram.com/InverseGudermannian.html
// http://williams.best.vwh.net/avform.htm

// https://developers.google.com/maps/
// https://github.com/mgavaghan/geodesy/tree/master/src/main/java/org/gavaghan/geodesy
// https://github.com/softwarenerd/GreatCircle
// https://github.com/mrJean1/PyGeodesy









// "Der Wissenschaftler ist ein Mann, der lieber zaehlt als vermutet."
// http://www.linux-related.de/index.html?/coding/o-notation.htm
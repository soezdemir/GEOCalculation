import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;

/** <br>Berechnung einer Distanz mit Geo Koordinaten</br>
 * @author soezdemir
 * @date 2016-09-27
 * @version 1.01
 */
public class GeoCalculation {

    public static final double EARTH_RAD = 6378.388;
    //public static final double TEN_MILLION = 10000000.0;

    public static void main(String args[]) {

        start();
    }

    private static void start(){

        double latitude1 =  49.99170;
        double longitude1 = 8.413210;
        double latitude2 = 50.00490;
        double longitude2 = 8.421820;

        double lat1 = 52.51640; //Berlin
        double lon1 = 13.37770;
        double lat2 =  38.69267; //Lissabon
        double lon2 = -9.177944;

        double distance;
        double largeArc;

        double radLatitude1 = Math.toRadians(latitude1);
        double radLongitude1 = Math.toRadians(longitude1);
        double radLatitude2 = Math.toRadians(latitude2);
        double radLongitude2 = Math.toRadians(longitude2);

        largeArc =  Math.sin(radLatitude1) * Math.sin(radLatitude2) +
                    Math.cos(radLatitude1) * Math.cos(radLatitude2) *
                    Math.cos(radLongitude2 - radLongitude1);

        distance =  EARTH_RAD * Math.acos(largeArc);

        System.out.println(" Point A =>\tLAT: " + latitude1 + "\tLON: " + longitude1);
        System.out.println(" Point B =>\tLAT: " + latitude2 + "\tLON: " + longitude2);
        System.out.println(" Distance = " + distance + " km\n");
        System.out.println("=======================================================\n");

        System.out.println(" Berlin   =>\tLAT: " + lat1 + "\tLON: " + lon1);
        System.out.println(" Lissabon =>\tLAT: " + lat2 + "\tLON: " + lon2);

        double radLat1 = Math.toRadians(lat1);
        double radLon1 = Math.toRadians(lon1);
        double radLat2 = Math.toRadians(lat2);
        double radLon2 = Math.toRadians(lon2);

        largeArc =  Math.sin(radLat1) * Math.sin(radLat2) +
                    Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radLon2 - radLon1);
        distance =  EARTH_RAD * Math.acos(largeArc);

        System.out.println(" Distance = " + distance + " km\n");
        System.out.println("=======================================================\n");


        lat1 = 49.24597; //Altenkesseler Str. [A]
        lon1 = 6.936992;
        lat2 = 49.24596; //Altenkesseler Str. [B]
        lon2 = 6.935801;

        System.out.println(" Altenkesseler Str. [A]\t LAT: " + lat1 + " LON: " + lon1);
        System.out.println(" Altenkesseler Str. [B]\t LAT: " + lat2 + " LON: " + lon2);

        radLat1 = Math.toRadians(lat1);
        radLon1 = Math.toRadians(lon1);
        radLat2 = Math.toRadians(lat2);
        radLon2 = Math.toRadians(lon2);

        largeArc =  Math.sin(radLat1) * Math.sin(radLat2) +
                    Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radLon2 - radLon1);

        distance =  EARTH_RAD * Math.acos(largeArc);

        System.out.print(" Distance = " + distance * 1000 + " m\n");

        double bearing = Math.atan2(Math.sin(radLon2 - radLon1) * Math.cos(radLat2),
                         (Math.cos(radLat1) * Math.sin(radLat2)) - (Math.sin(radLat1) * Math.cos(radLat2)) *
                         Math.cos(radLon2 - radLon1));
        System.out.println(" Bearing = " + bearing + "°\n");


    }
}
//http://www.movable-type.co.uk/scripts/latlong.html
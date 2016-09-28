/** <br>Berechnung einer Distanz mit Geo Koordinaten</br>
 * @author soezdemir
 * @date 2016-09-27
 * @version 1.01
 */
public class GeoCalculation {

    public static final double EARTH_RAD = 6371.0008;
    //public static final double TEN_MILLION = 10000000.0;

    public static void main(String args[]) {

        start();
    }

    public static void start(){

        distanceBetween(); //Distanzberechnung zwischen zwei Geokoordinaten
        searchParkingSlot(); // Berechnung der Entfernung und dem Winkel zwischen zwei Punkten
        searchPoint(); //Funktionsaufruf - Berechnung eines zweiten Koordinatenpunktes

    }

    private static void distanceBetween(){

        double latitude1 =  49.99170;
        double longitude1 = 8.413210;
        double latitude2 = 50.00490;
        double longitude2 = 8.421820;

        double distance;
        double largeArc;
        double bearing;

        double radLatitude1 = Math.toRadians(latitude1);
        double radLongitude1 = Math.toRadians(longitude1);
        double radLatitude2 = Math.toRadians(latitude2);
        double radLongitude2 = Math.toRadians(longitude2);

        largeArc =  Math.sin(radLatitude1) * Math.sin(radLatitude2) +
                    Math.cos(radLatitude1) * Math.cos(radLatitude2) *
                    Math.cos(radLongitude2 - radLongitude1);

        distance =  EARTH_RAD * Math.acos(largeArc);

        bearing = Math.atan2(Math.sin(radLongitude2 - radLongitude1) * Math.cos(radLatitude2),
                (Math.cos(radLatitude1) * Math.sin(radLatitude2)) - (Math.sin(radLatitude1) * Math.cos(radLatitude2)) *
                        Math.cos(radLongitude2 - radLongitude1));

        System.out.println(" Point A  =>\tLAT: " + latitude1 + "\tLON: " + longitude1);
        System.out.println(" Point B  =>\tLAT: " + latitude2 + "\tLON: " + longitude2);
        System.out.println(" Distance => " + distance + " km");
        System.out.println(" Bearing  => " + (Math.toDegrees(bearing)+360)%360);
        //System.out.println(" Final Bearing => " + ((Math.toDegrees(bearing)+180%360)));
        System.out.println("=======================================================\n");


        double lat1 = 52.51640; //Berlin
        double lon1 = 13.37770;
        double lat2 = 38.69267; //Lissabon
        double lon2 = -9.177944;

        double radLat1 = Math.toRadians(lat1);
        double radLon1 = Math.toRadians(lon1);
        double radLat2 = Math.toRadians(lat2);
        double radLon2 = Math.toRadians(lon2);

        largeArc =  Math.sin(radLat1) * Math.sin(radLat2) +
                    Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radLon2 - radLon1);
        distance =  EARTH_RAD * Math.acos(largeArc);

        bearing = Math.atan2(Math.sin(radLon2 - radLon1) * Math.cos(radLat2),
                (Math.cos(radLat1) * Math.sin(radLat2)) - (Math.sin(radLat1) * Math.cos(radLat2)) *
                        Math.cos(radLon2 - radLon1));


        System.out.println(" Berlin   =>\tLAT: " + lat1 + "\tLON: " + lon1);
        System.out.println(" Lissabon =>\tLAT: " + lat2 + "\tLON: " + lon2);
        System.out.println(" Distance => " + distance + " km");
        System.out.println(" Bearing  => " + (Math.toDegrees(bearing)+360)%360 + "°\n");
        System.out.println("=======================================================\n");

    }

    public static void searchParkingSlot(){

        double lat1 = 49.245922; //Altenkesseler Str. [A]
        double lon1 = 6.935884;
        double lat2 = 49.245943; //Altenkesseler Str. [B]
        double lon2 = 6.936975;
        System.out.println(" Parking Slot:");
        System.out.println(" Altenkesseler Str. [A]\t LAT: " + lat1 + " LON: " + lon1);
        System.out.println(" Altenkesseler Str. [B]\t LAT: " + lat2 + " LON: " + lon2);

        double radLat1 = Math.toRadians(lat1);
        double radLon1 = Math.toRadians(lon1);
        double radLat2 = Math.toRadians(lat2);
        double radLon2 = Math.toRadians(lon2);

        double largeArc =  Math.sin(radLat1) * Math.sin(radLat2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radLon2 - radLon1);

        double distance =  EARTH_RAD * Math.acos(largeArc);

        double bearing = Math.atan2(Math.sin(radLon2 - radLon1) * Math.cos(radLat2),
                (Math.cos(radLat1) * Math.sin(radLat2)) - (Math.sin(radLat1) * Math.cos(radLat2)) *
                        Math.cos(radLon2 - radLon1));

        System.out.println(" Distance => " + distance * 1000 + " m");
        System.out.println(" Bearing  => " + (Math.toDegrees(bearing)+360)%360 + "\n");
    }

    //ToDo =========================================================================
    public static void searchPoint(){ //ToDo LAT(x) wird noch falsch berechnet
        double latitude     = 49.245922;            //x = 49.245922
        double longitude    = 6.935884;             //y =  6.935884
        double bearing      = 88.31069344488208;    //Peilungswinkel
        double distance     = 0.07922964485129694;  //Distanz

        bearing = Math.toRadians(bearing);
        latitude = Math.toRadians(latitude);
        longitude = Math.toRadians(longitude);


        double x =      Math.asin(Math.sin(latitude) * Math.cos(distance/EARTH_RAD)
                        + Math.cos(latitude) * Math.sin(distance/EARTH_RAD) * Math.cos(bearing));
        double y =      longitude + Math.atan2(Math.sin(bearing) * Math.sin(distance/EARTH_RAD) * Math.cos(latitude),
                        Math.cos(distance/EARTH_RAD) - Math.sin(latitude) * Math.sin(x));

        y = (((y + 540) % 360) - 180 );

        double finalBearing = (bearing + 180) % 360;

        latitude = Math.toDegrees(latitude);
        longitude = Math.toDegrees(longitude);
        bearing = Math.toDegrees(bearing);
        //distance = Math.toDegrees(distance);
        x = Math.toDegrees(x);
        y = Math.toDegrees(y);

        System.out.println("===================================================================");
        System.out.println(" searchPoint()");
        System.out.println("\tLAT     : " + latitude + " \t\t\tLON: " + longitude);
        System.out.println("\tBearing : " + bearing);
        System.out.println("\tDistance: " + (distance * 1000) + " m");
        System.out.println("___________________________________________________________________");
        System.out.println("\tLAT(x): " + x + "  |  LON(y): " + y + "\n\tFinal Bearing: " + finalBearing);
    }   //49.245943, 6.936975
}

//"Der Wissenschaftler ist ein Mann, der lieber zaehlt als vermutet."

//http://www.movable-type.co.uk/scripts/latlong.html
//http://www.purplemath.com/modules/radians.htm
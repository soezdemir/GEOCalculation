import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
/**
 * Created by soezdemir on 28.09.2016.
 */

public class GeoStart {

    private static ArrayList<WGS84Point> testPoint = new ArrayList<>();

    public static void main(String args[])
    {
        start();
    }




    public static void start()
    {
        WGS84Point a = new WGS84Point(49.9917, 8.41321);  //Ruesselsheim -
        WGS84Point b = new WGS84Point(50.0049, 8.42182);  //Ruesselsheim -
        System.out.println(a);
        System.out.println(b);
        System.out.println("\tDistance:\t" + GeoCalculation.getDistanceBetween(a, b) + " km");
        System.out.println("\tBearing:\t" + GeoCalculation.getBearing(a, b) + "°\n");

        WGS84Point berlin = new WGS84Point(52.51640, 13.37770);  //Berlin
        WGS84Point lissab = new WGS84Point(38.69267, -9.177944); //Lissabon
        System.out.println(berlin);
        System.out.println(lissab);
        System.out.println("\tDistance:\t" + GeoCalculation.getDistanceBetween(berlin, lissab) + " km");
        System.out.println("\tBearing:\t" + GeoCalculation.getBearing(berlin, lissab) + "°\n");

        WGS84Point c = new WGS84Point(49.245922, 6.935884);
        WGS84Point d = new WGS84Point(49.245943, 6.936975);
        System.out.println(c);
        System.out.println(d);
        System.out.println("\tDistance:\t" + GeoCalculation.getDistanceBetween(c, d) + " km");
        System.out.println("\tBearing:\t" + GeoCalculation.getBearing(c, d) + "°\n");

        WGS84Point e = new WGS84Point(49.198811, 6.693405); //Rue de la Bonne Fontaine
        WGS84Point f = new WGS84Point(49.199084, 6.692552); //Rue de la Bonne Fontaine
        System.out.println(e);
        System.out.println(f);
        System.out.println("\tDistance:\t" + GeoCalculation.getDistanceBetween(e, f) + " km");
        System.out.println("\tBearing:\t" + GeoCalculation.getBearing(e, f) + "°\n");

        WGS84Point frankf = new WGS84Point(50.039258, 8.562203); //Frankfurt Airport
        WGS84Point istanb = new WGS84Point(40.983693, 28.810560); //Istanbul Atatuerk Airport
        System.out.println(frankf);
        System.out.println(istanb);
        System.out.println("\tDistance:\t" + GeoCalculation.getDistanceBetween(frankf, istanb) + " km");
        System.out.println("\tBearing:\t" + GeoCalculation.getBearing(frankf, istanb) + "°\n");


        double distance = 0.07922964485129694;
        double bearing = 88.31069344488208;
        WGS84Point q = new WGS84Point(49.245922, 6.935884);
        System.out.println(" searchPoint() --> " + GeoCalculation.searchPoint(q, distance, bearing));
        //GeoCalculation.distanceBetweenCities();
        //GeoCalculation.searchParkingSlot();
        //testValuesForDistanceBetween();

        // WGS84Point a = scanPoint(" Point A [latitude] ==> ", " Point A [longitude] ==> ");
        // WGS84Point b = scanPoint(" Point B [latitude] ==> ", " Point B [longitude] ==> ");
    }


    /**
     * Parameters to testing GeoCalculation
     * (49.245078, 6.936198) with latitude and longitude values
     */
    public static void testValuesForDistanceBetween(){

        testPoint.add(new WGS84Point(52.51640, 13.37770));  //Berlin
        testPoint.add(new WGS84Point(38.69267, -9.177944)); //Lissabon

        testPoint.add(new WGS84Point(49.245922, 6.935884)); //Altenkesseler Str. [A]
        testPoint.add(new WGS84Point(49.245943, 6.936975)); //Altenkesseler Str. [B]
    }


    /**
     * <br> Scanner method for a WGS84-Point </br>
     * @param latitude
     * @param longitude
     * @return WGS84Point
     */
    private static WGS84Point scanPoint (double latitude, double longitude){

        Scanner pointScanner = new Scanner(System.in);

        System.out.print(latitude);
        double x = pointScanner.nextDouble();
        System.out.print(longitude);
        double y = pointScanner.nextDouble();

        return new WGS84Point(x, y);
    }

}

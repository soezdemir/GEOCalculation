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

        //GeoCalculation.init();
        System.out.println(a);
        System.out.println(b);
        System.out.println("\tDistance:\t" +  GeoCalculation.getDistanceBetween(a, b) + " km\n");

        WGS84Point berlin = new WGS84Point(52.51640, 13.37770);
        WGS84Point lissab = new WGS84Point(38.69267, -9.177944);
        System.out.println(berlin);
        System.out.println(lissab);
        System.out.println("\tDistance:\t" +  GeoCalculation.getDistanceBetween(berlin, lissab) + " km\n");

        WGS84Point c = new WGS84Point(49.245922, 6.935884);
        WGS84Point d = new WGS84Point(49.245943, 6.936975);
        System.out.println(c);
        System.out.println(d);
        System.out.println("\tDistance:\t" +  GeoCalculation.getDistanceBetween(c, d) + " km\n");


        GeoCalculation.searchPoint();

        //GeoCalculation.distanceBetweenCities();
        //GeoCalculation.searchParkingSlot();
        //testValuesForDistanceBetween();

        // WGS84Point a = scanPoint(" Point A [latitude] ==> ", " Point A [longitude] ==> ");
        // WGS84Point b = scanPoint(" Point B [latitude] ==> ", " Point B [longitude] ==> ");
    }


    /**
     * Parameters to testing GeoCalculation
     * @param WGS84Point ( , ) with latitude and longitude values
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

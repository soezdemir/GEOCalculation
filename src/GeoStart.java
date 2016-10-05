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
        WGS84Point a = new WGS84Point(49.9917, 8.41321);  //Ruesselsheim - HBF (Bahnhofsplatz)
        WGS84Point b = new WGS84Point(50.0049, 8.42182);  //Ruesselsheim - Opelbruecke (Mainzer Str.)
        System.out.println("Point A: " + a);
        System.out.println("Point B: " + b);
        System.out.println("=======================================================================================");

        System.out.println("\tDistance:\t\t\t\t\t" + GeoCalculation.getDistanceBetween(a,b) + " km");
        System.out.println("\tHalf-way Point:\t\t\t\t" + GeoCalculation.getMiddlePoint(a,b));
        System.out.println("\tDistance to Half-way Point:\t"
                + GeoCalculation.getDistanceBetween(a, GeoCalculation.getMiddlePoint(a,b)) + " km");
        System.out.println("\tBearing:\t\t\t\t\t" + GeoCalculation.getInitialBearing(a, b) + "°\t--> "
                            + GeoCalculation.tenthMicroDegreeToDegree((long)GeoCalculation.getInitialBearing(a, b))
                            + "\t[tenth micro degree]");
        System.out.println("\tFinal Bearing:\t\t\t\t" + GeoCalculation.getFinalBearing(a,b) + "°\t--> "
                            + GeoCalculation.tenthMicroDegreeToDegree((long)GeoCalculation.getFinalBearing(a, b))
                            + "\t[tenth micro degree]");

        System.out.println("\tsearchPoints():" +
                         "\n\t=======================================================================================");

        WGS84Point pointB = GeoCalculation.searchPoint(a, GeoCalculation.getDistanceBetween(a, b),
                                GeoCalculation.getInitialBearing(a,b)); System.out.println("\tPoint B: " + pointB);
        WGS84Point pointC = GeoCalculation.searchPoint(a, GeoCalculation.getDistanceBetween(a,b),
                                GeoCalculation.getFinalBearing(a,b)); System.out.println("\tPoint C: " + pointC);
        WGS84Point pointD = GeoCalculation.searchPoint(a, (GeoCalculation.getDistanceBetween(a,b)/2),
                                GeoCalculation.getInitialBearing(a,b)-90); System.out.println("\tPoint D: " + pointD);
        WGS84Point pointE = GeoCalculation.searchPoint(a, (GeoCalculation.getDistanceBetween(a,b)/2),
                                GeoCalculation.getInitialBearing(a,b)+90); System.out.println("\tPoint E: " + pointE);

        System.out.println("\t-----------------------------------------------------------------------------------");

        WGS84Point cp1 = GeoCalculation.searchPoint(b, (GeoCalculation.getDistanceBetween(a,b)/2),
                                            GeoCalculation.getInitialBearing(a,b)-90);
                                            System.out.println("\tCornerPoint CP1: " + cp1);
        WGS84Point cp2 = GeoCalculation.searchPoint(b, (GeoCalculation.getDistanceBetween(a,b)/2),
                                            GeoCalculation.getInitialBearing(a,b)+90);
                                            System.out.println("\tCornerPoint CP2: " + cp2);
        WGS84Point cp3 = GeoCalculation.searchPoint(pointC, (GeoCalculation.getDistanceBetween(a,pointC)/2),
                                            GeoCalculation.getFinalBearing(a,pointC)+90);
                                            System.out.println("\tCornerPoint CP3: " + cp3);
        WGS84Point cp4 = GeoCalculation.searchPoint(pointC, (GeoCalculation.getDistanceBetween(a,pointC)/2),
                                            GeoCalculation.getFinalBearing(a,pointC)-90);
                                            System.out.println("\tCornerPoint CP4: " + cp4);

        System.out.println("\t-----------------------------------------------------------------------------------");
        System.out.println("\tLong side of Rectangle      :\t" + GeoCalculation.getDistanceBetween(cp1,cp3) + " km");
        System.out.println("\tShort side of Rectangle     :\t" + GeoCalculation.getDistanceBetween(cp1,cp2) + " km");
        System.out.println("\tDiagonal Vector of Rectangle:\t" + GeoCalculation.getDistanceBetween(cp1,cp4) + " km");
        System.out.println("\tRectangular GeoArea         :\t" + ((GeoCalculation.getDistanceBetween(a,b)*2) *
                            (GeoCalculation.getDistanceBetween(a, GeoCalculation.getMiddlePoint(a,b)) * 2)) + " km²");
        System.out.println("\t-----------------------------------------------------------------------------------");
        System.out.println("\tLong side :\t\t" + GeoCalculation.getDistanceBetween(pointB,pointC) + " km");
        System.out.println("\tShort side:\t\t" + GeoCalculation.getDistanceBetween(pointD,pointE) + " km");
        System.out.println("\tDiagonal Vector:" + GeoCalculation.getDistanceBetween(cp1,cp4) + " km");
        System.out.println("\tGeoArea   :\t\t" + (GeoCalculation.getDistanceBetween(pointB,pointC)
                                                 *GeoCalculation.getDistanceBetween(pointD,pointE)) + " km²");

        RectangularGeoArea rectangle = new RectangularGeoArea(a, (long) GeoCalculation.getDistanceBetween(pointD,pointE),
                (long) GeoCalculation.getDistanceBetween(pointB,pointC), GeoCalculation.getInitialBearing(a, b));

        System.out.print("\n\t*****" + rectangle + "*****\n");

        //System.out.println("\tIntersection Point: "
        //        + GeoCalculation.intersectionPoint(b, GeoCalculation.getInitialBearing(a,b)-90,
        //        D, GeoCalculation.getInitialBearing(a,b)));
        //System.out.println("\tIntersection Point: "
        //                   + GeoCalculation.intersectionPoint(b, 292.744, D, 22.744));
        System.out.println("=======================================================================================\n");
    }

    /**
     * Parameters to testing GeoCalculations
     * (49.245078, 6.936198) with latitude and longitude values
     */
    public static void testValuesForDistanceBetween()
    {
        testPoint.add(new WGS84Point(52.51640, 13.37770));  //Berlin
        testPoint.add(new WGS84Point(38.69267, -9.177944)); //Lissabon

        testPoint.add(new WGS84Point(49.245922, 6.9358820)); //Altenkesseler Str. [A]
        testPoint.add(new WGS84Point(49.245922, 6.9369862)); //Altenkesseler Str. [B]

        testPoint.add(new WGS84Point(49.9917, 8.41321));  //Ruesselsheim - HBF (Bahnhofsplatz)
        testPoint.add(new WGS84Point(50.0049, 8.42182));  //Ruesselsheim - Opelbruecke (Mainzer Str.)

        testPoint.add(new WGS84Point(50.039258, 8.562203)); //Frankfurt Airport
        testPoint.add(new WGS84Point(40.983693, 28.810560)); //Istanbul Atatuerk Airport

        testPoint.add(new WGS84Point(49.198811, 6.693405)); //Rue de la Bonne Fontaine
        testPoint.add(new WGS84Point(49.199084, 6.692552)); //Rue de la Bonne Fontaine
    }

    /**
     * <br> Scanner method for a WGS84-Point </br>
     * @param latitude
     * @param longitude
     * @return WGS84Point
     */
    private static WGS84Point scanPoint (double latitude, double longitude)
    {
        Scanner pointScanner = new Scanner(System.in);

        System.out.print(latitude);
        double x = pointScanner.nextDouble();
        System.out.print(longitude);
        double y = pointScanner.nextDouble();

        return new WGS84Point(x, y);
    }
}//ENDE

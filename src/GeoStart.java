import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
/**
 * Created by soezdemir on 28.09.2016.
 */

public class GeoStart {

    //private static ArrayList<WGS84Point> testPoint = new ArrayList<>();

    public static void main(String args[])
    {
        //start();
        //startRectangularArea();
        startRectangularGeoArea();
    }

    public static void start()
    {
        WGS84Point a = new WGS84Point(49.9917, 8.41321);  //Ruesselsheim - HBF (Bahnhofsplatz)
        WGS84Point b = new WGS84Point(50.0049, 8.42182);  //Ruesselsheim - Opelbruecke (Mainzer Str.)
        System.out.println("Point A: " + a);
        System.out.println("Point B: " + b);
        System.out.println("=======================================================================================");

        System.out.println("\tDistance:\t\t\t\t\t" + GeoCalculation.getDistanceBetweenTwoPoints(a,b) + " km");
        System.out.println("\tHalf-way Point:\t\t\t\t" + GeoCalculation.getMiddlePoint(a,b));
        System.out.println("\tDistance to Half-way Point:\t"
                + GeoCalculation.getDistanceBetweenTwoPoints(a, GeoCalculation.getMiddlePoint(a,b)) + " km");
        System.out.println("\tBearing:\t\t\t\t\t" + GeoCalculation.getInitialBearing(a, b) + "°\t--> "
                            + GeoCalculation.tenthMicroDegreeToDegree((long)GeoCalculation.getInitialBearing(a, b))
                            + "\t[tenth micro degree]");
        System.out.println("\tFinal Bearing:\t\t\t\t" + GeoCalculation.getFinalBearing(a,b) + "°\t--> "
                            + GeoCalculation.tenthMicroDegreeToDegree((long)GeoCalculation.getFinalBearing(a, b))
                            + "\t[tenth micro degree]");

        System.out.println("\tsearchPoints():" +
                         "\n\t-----------------------------------------------------------------------------------");

        WGS84Point pointB = GeoCalculation.searchPoint(a, GeoCalculation.getDistanceBetweenTwoPoints(a, b),
                                GeoCalculation.getInitialBearing(a,b)); System.out.println("\tPoint B: " + pointB);
        WGS84Point pointC = GeoCalculation.searchPoint(a, GeoCalculation.getDistanceBetweenTwoPoints(a,b),
                                GeoCalculation.getFinalBearing(a,b)); System.out.println("\tPoint C: " + pointC);
        WGS84Point pointD = GeoCalculation.searchPoint(a, (GeoCalculation.getDistanceBetweenTwoPoints(a,b)/2),
                                GeoCalculation.getInitialBearing(a,b)-90); System.out.println("\tPoint D: " + pointD);
        WGS84Point pointE = GeoCalculation.searchPoint(a, (GeoCalculation.getDistanceBetweenTwoPoints(a,b)/2),
                                GeoCalculation.getInitialBearing(a,b)+90); System.out.println("\tPoint E: " + pointE);

        System.out.println("\t-----------------------------------------------------------------------------------");

        WGS84Point cp1 = GeoCalculation.searchPoint(b, (GeoCalculation.getDistanceBetweenTwoPoints(a,b)/2),
                                            GeoCalculation.getInitialBearing(a,b)-90);
                                            System.out.println("\tCornerPoint CP1: " + cp1);
        WGS84Point cp2 = GeoCalculation.searchPoint(b, (GeoCalculation.getDistanceBetweenTwoPoints(a,b)/2),
                                            GeoCalculation.getInitialBearing(a,b)+90);
                                            System.out.println("\tCornerPoint CP2: " + cp2);
        WGS84Point cp3 = GeoCalculation.searchPoint(pointC, (GeoCalculation.getDistanceBetweenTwoPoints(a,pointC)/2),
                                            GeoCalculation.getFinalBearing(a,pointC)+90);
                                            System.out.println("\tCornerPoint CP3: " + cp3);
        WGS84Point cp4 = GeoCalculation.searchPoint(pointC, (GeoCalculation.getDistanceBetweenTwoPoints(a,pointC)/2),
                                            GeoCalculation.getFinalBearing(a,pointC)-90);
                                            System.out.println("\tCornerPoint CP4: " + cp4);

        System.out.println("\t-----------------------------------------------------------------------------------");
        System.out.println("\tLong side of Rectangle      :\t" + GeoCalculation.getDistanceBetweenTwoPoints(cp1,cp4) + " km");
        System.out.println("\tShort side of Rectangle     :\t" + GeoCalculation.getDistanceBetweenTwoPoints(cp1,cp2) + " km");
        System.out.println("\tDiagonal Vector of Rectangle:\t" + GeoCalculation.getDistanceBetweenTwoPoints(cp1,cp3) + " km");
        System.out.println("\tRectangular GeoArea         :\t" + ((GeoCalculation.getDistanceBetweenTwoPoints(a,b)*2) *
                            (GeoCalculation.getDistanceBetweenTwoPoints(a, GeoCalculation.getMiddlePoint(a,b)) * 2)) + " km²");
        System.out.println("\t-----------------------------------------------------------------------------------");
        System.out.println("\tLong side :\t\t" + GeoCalculation.getDistanceBetweenTwoPoints(pointB,pointC) + " km");
        System.out.println("\tShort side:\t\t" + GeoCalculation.getDistanceBetweenTwoPoints(pointD,pointE) + " km");
        System.out.println("\tDiagonal Vector:" + GeoCalculation.getDistanceBetweenTwoPoints(cp1,cp3) + " km");
        System.out.println("\tGeoArea   :\t\t" + (GeoCalculation.getDistanceBetweenTwoPoints(pointB,pointC)
                                                 *GeoCalculation.getDistanceBetweenTwoPoints(pointD,pointE)) + " km²");

        System.out.println("\n***************************************************************************************");
        System.out.println("***************************************************************************************\n");
        /**System.out.println("\tIntersection Point: "
                            + GeoCalculation.intersectionPoint(pointB, GeoCalculation.getInitialBearing(a, pointD)+360,
                                pointD, GeoCalculation.getInitialBearing(a,pointB)+360) + "\n");
        System.out.println("***************************************************************************************");
         **/

        WGS84Point point = new WGS84Point(50.00489999999892, 8.421819999999306);
        WGS84Point anotherPoint = new WGS84Point(49.994466465689015, 8.402943156609846);

        System.out.println("\tIntersection Point [TEST]: "
                           + GeoCalculation.intersectionPoint(  point, 90+22.744404844419254,
                                                                anotherPoint, 90-22.744404844419254));
        System.out.println("***************************************************************************************\n");
    }

    //public static void startRectangularArea(WGS84Point centerPoint, double angle, double aDistance, double bDistance)
    public static void startRectangularArea()
    {
        WGS84Point aPoint = new WGS84Point(49.991712, 8.413154); //Ruesselsheim - Bahnhofsplatz 2 --> CenterPoint
        //WGS84Point aPoint = new WGS84Point(49.245532, 6.937126); //Saarbrücken - Innovationscampus --> CenterPoint
        double azimuth = 22.744;
        double aDistance = 1.5;
        double bDistance = 0.75;

        System.out.println(" -- Rectangular Geo Area --");
        System.out.println(" A-" + aPoint + " -- azimuth: " + azimuth + "° -- aDistance: " + aDistance + " km -- bDistance: " + bDistance + " km ");

        WGS84Point bPoint = GeoCalculation.searchPoint(aPoint,aDistance,azimuth);
        System.out.println(" B-" + bPoint);

        WGS84Point cPoint = GeoCalculation.searchPoint(aPoint,aDistance,azimuth-90);
        System.out.println(" C-" + cPoint);

        WGS84Point dPoint = GeoCalculation.searchPoint(aPoint,aDistance,azimuth-180);
        System.out.println(" D-" + dPoint);

        WGS84Point ePoint = GeoCalculation.searchPoint(aPoint,aDistance,azimuth-270);
        System.out.println(" E-" + ePoint);

        WGS84Point middlePoint = GeoCalculation.getMiddlePoint(aPoint,bPoint);
        System.out.print("\tmiddle-" + middlePoint);

        System.out.println("\t\thalfway-distance: " + GeoCalculation.getDistanceBetweenTwoPoints(aPoint, middlePoint));

        WGS84Point cornerP1 = GeoCalculation.searchPoint(bPoint, bDistance, azimuth-90);
        System.out.println("\tcorner-" + cornerP1);

        WGS84Point cornerP2 = GeoCalculation.searchPoint(bPoint, bDistance, azimuth+90);
        System.out.println("\tcorner-" + cornerP2);

        WGS84Point cornerP3 = GeoCalculation.searchPoint(dPoint, bDistance, azimuth+90);
        System.out.println("\tcorner-" + cornerP3);

        WGS84Point cornerP4 = GeoCalculation.searchPoint(dPoint, bDistance, azimuth-90);
        System.out.println("\tcorner-" + cornerP4);

        System.out.println("\n***************************************************************************************");
        System.out.println("***************************************************************************************\n");


    }

    public static void startRectangularGeoArea()
    {
        //WGS84Point aPoint = new WGS84Point(49.991712, 8.413154); //Ruesselsheim - Bahnhofsplatz 2 --> CenterPoint
        WGS84Point aPoint = new WGS84Point(49.245532, 6.937126); //Saarbrücken - Innovationscampus --> CenterPoint
        double azimuth = 88.00486102219236;
        double aDistance = 0.1;
        double bDistance = 0.05;

        RectangularGeoArea rectangleObject = new RectangularGeoArea(aPoint, aDistance, bDistance, azimuth);
        System.out.println(rectangleObject + "\n");
        //GeoCalculation.getSexagesimal(aPoint);

        System.out.println("B-" + GeoCalculation.searchPoint(aPoint, aDistance, azimuth));
        System.out.println("C-" + GeoCalculation.searchPoint(aPoint, aDistance, azimuth-90));
        System.out.println("D-" + GeoCalculation.searchPoint(aPoint, aDistance, azimuth-180));
        System.out.println("E-" + GeoCalculation.searchPoint(aPoint, aDistance, azimuth-270));

        System.out.print("#(1)#\t ");
        WGS84Point point1 = new WGS84Point(49.245532, 6.937126);                    //CenterPoint
        RectangularGeoArea.geoFunctionOfRectangularArea(point1);
        RectangularGeoArea.isPointInArea(rectangleObject, point1);

        System.out.print("#(2)#\t ");
        WGS84Point point2 = new WGS84Point(49.24463322633939, 6.937174076579488);   //Border of Area
        RectangularGeoArea.geoFunctionOfRectangularArea(point2);
        RectangularGeoArea.isPointInArea(rectangleObject, point2);

        System.out.print("#(3)#\t ");
        WGS84Point point3 = new WGS84Point(49.245551, 6.933751);                    //Outside of Area
        RectangularGeoArea.geoFunctionOfRectangularArea(point3);
        RectangularGeoArea.isPointInArea(rectangleObject, point3);

        System.out.print("#(4)#\t ");
        WGS84Point point4 = new WGS84Point(49.244916, 6.942750);                    //Outside of Area
        RectangularGeoArea.geoFunctionOfRectangularArea(point4);
        RectangularGeoArea.isPointInArea(rectangleObject, point4);

        System.out.print("#(5)#\t ");
        WGS84Point point5 = new WGS84Point(49.245547, 6.937814);                    //Inside of Area
        RectangularGeoArea.geoFunctionOfRectangularArea(point5);
        RectangularGeoArea.isPointInArea(rectangleObject, point5);

    }


    /**
     * Parameters to testing GeoCalculations
     * (49.245078, 6.936198) with latitude and longitude values
     */
   /** public static void testValuesForDistanceBetween()
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
    }*/

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

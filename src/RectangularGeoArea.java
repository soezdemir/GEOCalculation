/**
 * Created by soezdemir on 04.10.16.
 */
public class RectangularGeoArea{

    private static String POINT_COORD_NULL = "A point can not be NULL!";

    private WGS84Point centerPoint;
    private static double toShortSide;
    private static double toLongSide;
    private static double valueOfSideA;
    private static double valueOfSideB;
    private double azimuthAngle;
    private double area;
    private double extend;
    private static int id = 0;

    /**
     * <b>Constructor of RectangularGeoArea</b>
     * @param centerPoint Center Point of Rectangular Area
     * @param toShortSide Distance to short side in meters
     * @param toLongSide Distance to long side in meters
     * @param azimuthAngle Angle of Azimuth in degrees
     */
    public RectangularGeoArea(WGS84Point centerPoint, double toShortSide, double toLongSide, double azimuthAngle){
        if(null != centerPoint){
            this.centerPoint = centerPoint;
        }
        else{
            this.centerPoint = new WGS84Point(0,0);
        }
        this.toShortSide = toShortSide;
        this.toLongSide = toLongSide;
        this.azimuthAngle = azimuthAngle;

    }
    /**
     * Getter
	 * @return Center Point
	 */
    public final WGS84Point getCenterPoint() {
        return this.centerPoint;
    }

    /**
     * Getter
     * @return Distance to short side in meters.
     */
    public final double getToShortSide() {
        return this.toShortSide;
    }

    /**
     * Getter
     * @return Distance to long side in meters
     */
    public final double getToLongSide() {
        return this.toLongSide;
    }

    /**
     * Getter
     * @return Angle of Azimuth in degree.
     */
    public final double getAzimuthAngle() {
        return this.azimuthAngle;
    }

    /**
     * Getter
     * @return Rectangle Area in m²
     */
    public final double getRectangleArea(){
        valueOfSideA = (getToShortSide() * 2) * 1000;
        valueOfSideB = (getToLongSide() * 2) * 1000;
        return this.area = valueOfSideA * valueOfSideB;
    }

    /**
     * Getter
     * @return Rectangle Extend in meter
     */
    public final double getRectangleExtend(){
        valueOfSideA = (getToShortSide() * 2) * 1000;
        valueOfSideB = (getToLongSide() * 2) * 1000;
        return this.extend = 2 * (valueOfSideA + valueOfSideB);
    }

    public static int getRectangleID()
    {// siehe Nummerierungsobjekte (Singleton) http://www.java-forum.org/thema/objekte-durchnummerieren.38878/
        return id++;
    }

    /**
     *  <b>Geometric function F for a rectangular area [ETSI EN 302 931 V1.0.0]</b>
     *	Geographical Area Definition - Intelligent Transport Systems (ITS)
     *  A Method to determine whether a WGS84Point is in the rectangular area.
     *  @return result of function
     * */
    public static double geoFunctionOfRectangularArea(WGS84Point point){
        if(null == point){
            throw new IllegalArgumentException(POINT_COORD_NULL);
        }
        double xValue = point.getLatitudeDegree();
        double yValue = point.getLongitudeDegree();
        valueOfSideA = RectangularGeoArea.toLongSide * 2;
        valueOfSideB = RectangularGeoArea.toShortSide * 2;

        double result = Math.min(1 - (Math.pow((xValue/valueOfSideA), 2)), 1 - (Math.pow((yValue/valueOfSideB), 2)));

        return result;
    }

    /**
     * <b>Method to determine whether a point is located inside,</b>
     * <b>outside, at the center, or at the border of a geographical area.</b>
     * @param rectangleObject
     * @param point
     *  value = 1 -----> Point is the center point
     *  value > 0 -----> Point is inside the area
     *  value = 0 -----> Point at the border of the area
     *  value < 0 -----> Point is outside of the area
     */
    public static boolean isPointInArea (RectangularGeoArea rectangleObject, WGS84Point point){
        if (rectangleObject.geoFunctionOfRectangularArea(point) == 1){
            System.out.print (point + " is CENTERPOINT of GeoArea \n");
            return true;
        }
        else if(rectangleObject.geoFunctionOfRectangularArea(point) > 0){
            System.out.print (point + " is INSIDE GeoArea! \n");
            return true;
        }
        else if(rectangleObject.geoFunctionOfRectangularArea(point) == 0){
            System.out.print (point + " is at the BORDER of GeoArea! \n");
            return true;
        }
        else if(rectangleObject.geoFunctionOfRectangularArea(point) < 0){
            System.out.print (point + " is OUTSIDE the GeoArea! \n");
            return false;
        }
        return false;
    }

    public String toString(){
        return  "\n Rectangular Area [" + RectangularGeoArea.getRectangleID() + "]" +
                "\n CenterPoint => "    + getCenterPoint() +
                "\n\t\t\ttoShortSide\t"    + getToShortSide() + " km" +
                "\n\t\t\ttoLongSide \t"    + getToLongSide() + " km" +
                "\n\t\t\tAzimuth    \t"    + getAzimuthAngle() + "°" +
                "\n\t\t\tArea       \t"    + getRectangleArea() + " m²" +
                "\n\t\t\tExtend     \t"    + getRectangleExtend() + " m";
    }

}//ENDE
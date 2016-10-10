/**
 * Created by soezdemir on 04.10.16.
 */
public class RectangularGeoArea {



    private WGS84Point centerPoint;
    private static double toShortSide;
    private static double toLongSide;
    private double azimuthAngle;

    /**
     * Constructor of RectangularGeoArea
     * @param centerPoint Center Point of Rectangular Area
     * @param toShortSide Distance to short side in meters
     * @param toLongSide Distance to long side in meters
     * @param azimuthAngle Angle of Azimuth in degrees
     */
    public RectangularGeoArea(WGS84Point centerPoint, double toShortSide, double toLongSide, double azimuthAngle)
    {
        if(null != centerPoint)
        {
            this.centerPoint = centerPoint;
        }
        else
        {
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

    public String toString()
    {
        return "\n CenterPoint => "    + getCenterPoint() +
                "\n\ttoShortSide\t"    + getToShortSide() + " km" +
                "\n\ttoLongSide \t"    + getToLongSide() + " km" +
                "\n\tAzimuth    \t"    + getAzimuthAngle() + "°";
    }

    /** Geometric function F for a rectangular area
     *	ETSI EN 302 931 V1.0.0 (2010-12)
     *	Geographical Area Definition - Intelligent Transport Systems (ITS)
     *  A Method to determine whether a WGS84Point is in the rectangular area.
     *  @return result
     * */
    public static double geoFunctionOfRectangularArea(WGS84Point point){

        double xValue = point.getLatitudeDegree();
        double yValue = point.getLongitudeDegree();
        double valueOfSideA = (RectangularGeoArea.toLongSide);
        double valueOfSideB = (RectangularGeoArea.toShortSide);

        double result = Math.min(1 - (Math.pow((xValue/valueOfSideA), 2)), 1 - (Math.pow((yValue/valueOfSideB), 2)));
        //System.out.println("result: " + result);
        return result;
    }

    /**
     * Method to determine whether a point is located inside,
     * outside, at the centre, or at the border of a geographical area.
     * @param rectangleObject
     * @param point
     *  value = 1 -----> Point is the center point
     *  value > 0 -----> Point is inside the area
     *  value = 0 -----> Point at the border of the area
     *  value < 0 -----> Point is outside of the area
     */
    public static boolean isPointInArea (RectangularGeoArea rectangleObject, WGS84Point point)
    {
        if (rectangleObject.geoFunctionOfRectangularArea(point) == 1)
        {
            System.out.print (point + " is CENTERPOINT of GeoArea \n");
            return true;
        }
        else if(rectangleObject.geoFunctionOfRectangularArea(point) > 0)
        {
            System.out.print (point + " is INSIDE GeoArea! \n");
            return true;
        }
        else if(rectangleObject.geoFunctionOfRectangularArea(point) == 0)
        {
            System.out.print (point + " is at the BORDER of GeoArea! \n");
            return true;
        }
        else if(rectangleObject.geoFunctionOfRectangularArea(point) < 0)
        {
            System.out.print (point + " is OUTSIDE the GeoArea! \n");
            return false;
        }

        return false;
    }

}//ENDE
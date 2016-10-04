/**
 * Created by soezdemir on 04.10.16.
 */
public class RectangularGeoArea {


    private WGS84Point centerPoint;
    private long toShortSide;
    private long toLongSide;
    private double azimuthAngle;

    /**
     * Constructor of RectangularGeoArea
     * @param centerPoint Center Point of Rectangular Area
     * @param toShortSide Distance to short side in meters
     * @param toLongSide Distance to long side in meters
     * @param azimuthAngle Angle of Azimuth in degrees
     */
    public RectangularGeoArea(WGS84Point centerPoint, long toShortSide, long toLongSide, double azimuthAngle)
    {
        if(null != centerPoint)
        {
            this.centerPoint = centerPoint;
        }
        else
        {
            this.centerPoint = new WGS84Point(0.0, 0.0);
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
    public final long getToShortSide() {
        return this.toShortSide;
    }

    /**
     * Getter
     * @return Distance to long side in meters
     */
    public final long getToLongSide() {
        return this.toLongSide;
    }

    /**
     * Getter
     * @return Angle of Azimuth in degree.
     */
    public final double getAzimuthAngle() {
        return this.azimuthAngle;
    }


}//ENDE
package sv1djg.hamutils.distances;

import static org.junit.Assert.*;

import org.junit.Test;

import sv1djg.hamutils.distances.DistanceCaclulator.CoordinateSet;

public class DistanceCaclulatorTest 
{

    @Test
    public void testDistanceFrom() 
    {

	double fromLatitude = 38.0550815;
	double fromLongitude = 23.7974941;
	double toLatitude = 32.236416;
	double toLongitude = -110.938471;

	double distance = DistanceCaclulator.distanceFrom(fromLatitude, fromLongitude, toLatitude, toLongitude);
	assertEquals(10902.298580, distance, 0.00001);


	fromLatitude = 38.05737;
	fromLongitude = 23.79665;
	toLatitude = 38.01686;
	toLongitude = 23.80057;


	distance = DistanceCaclulator.distanceFrom(fromLatitude, fromLongitude, toLatitude, toLongitude);
	assertEquals(4.517570, distance, 0.00001);

    }

    @Test
    public void testBearingTo() 
    {
	double fromLatitude = 38.05737;
	double fromLongitude = 23.79665;
	double toLatitude = 38.01686;
	double toLongitude = 23.80057;

	double bearing = DistanceCaclulator.bearingTo(fromLatitude, fromLongitude, toLatitude, toLongitude);
	assertEquals(175.640464, bearing, 0.00001);

    }

    @Test
    public void testMidpointOf() 
    {
	double fromLatitude = 38.05737;
	double fromLongitude = 23.79665;
	double toLatitude = 38.01686;
	double toLongitude = 23.80057;

	CoordinateSet midpointCoords = DistanceCaclulator.midpointOf(fromLatitude, fromLongitude, toLatitude, toLongitude);
	assertEquals(38.037115,midpointCoords.getLatitude() , 0.00001);
	assertEquals(23.798611,midpointCoords.getLongitude(), 0.00001);
    }

    @Test
    public void testTargetAtBearingDistance() 
    {
	double fromLatitude = 38.05737;
	double fromLongitude = 23.79665;

	CoordinateSet targetPointCoords = DistanceCaclulator.targetAtBearingDistance(fromLatitude, fromLongitude, 220, 1920);

	assertEquals(24.181583,targetPointCoords.getLatitude() , 0.00001);
	assertEquals(11.724257,targetPointCoords.getLongitude(), 0.00001);
    }

}

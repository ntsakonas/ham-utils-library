package sv1djg.hamutils.locator;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import sv1djg.hamutils.locator.GridLocator.LocatorLength;

public class GridLocatorTest 
{

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test 
    public void testGridLocatorLatTooSmall() throws Exception 
    {
	exception.expect(Exception.class);
	exception.expectMessage("Latitude cannot be less than -90.0 degrees");

	new GridLocator(-90.1,10.0);
    }

    @Test 
    public void testGridLocatorLatTooLarge() throws Exception 
    {
	exception.expect(Exception.class);
	exception.expectMessage("Latitude cannot be greater than 90.0 degrees");

	new GridLocator(90.1,10.0);
    }

    @Test 
    public void testGridLocatorLongTooSmall() throws Exception 
    {
	exception.expect(Exception.class);
	exception.expectMessage("Longitude cannot be less than 180.0 degrees");

	new GridLocator(10.1,-180.001);
    }

    @Test 
    public void testGridLocatorLongTooLarge() throws Exception 
    {
	exception.expect(Exception.class);
	exception.expectMessage("Longitude cannot be greater than 180.0 degrees");

	new GridLocator(10.1,180.001);
    }


    @Test
    public void testGetLocator() throws Exception
    {
	GridLocator d1 = new GridLocator(38.0575, 23.79682);
	assertEquals("locator for 38.0575N 23.79682E", "KM18VB53", d1.getLocator());
	assertEquals("6 digit locator for 38.0575N 23.79682E", "KM18VB", d1.getLocator(LocatorLength.SixDigit));
	assertEquals("4 digit locator for 38.0575N 23.79682E", "KM18", d1.getLocator(LocatorLength.FourDigit));

	GridLocator d2 = new GridLocator(38.05723, 23.83344);
	assertEquals("locator for 38.05723N 23.83344E", "KM18WB03", d2.getLocator());
	assertEquals("6 digit locator for 38.05723N 23.83344E", "KM18WB", d2.getLocator(LocatorLength.SixDigit));
	assertEquals("4 digit locator for 38.05723N 23.83344E", "KM18", d2.getLocator(LocatorLength.FourDigit));

    }

    @Test
    public void testGetLocatorLocatorLength() throws Exception
    {
	GridLocator d1 = new GridLocator(38.0575, 23.79682);
	assertEquals("locator for 38.0575N 23.79682E", d1.getLocator().length(),8);
	assertEquals("6 digit locator for 38.0575N 23.79682E", d1.getLocator(LocatorLength.SixDigit).length(),6);
	assertEquals("4 digit locator for 38.0575N 23.79682E", d1.getLocator(LocatorLength.FourDigit).length(),4);
    }


    @Test
    public void testGetLongitude() throws Exception 
    {
	GridLocator d1 = new GridLocator(38.05737, 23.79665);
	GridLocator d2 = new GridLocator(38.01686, 23.80057);
	GridLocator d3 = new GridLocator(51.50484, -0.11367);
	GridLocator d4 = new GridLocator(-34.6268, -58.36955);

	assertEquals(23.79665, d1.getLongitude(),0.000000001);
	assertEquals(23.80057, d2.getLongitude(),0.000000001);
	assertEquals(-0.11367, d3.getLongitude(),0.000000001);
	assertEquals(-58.36955, d4.getLongitude(),0.000000001);
    }

    @Test
    public void testGetLatitude() throws Exception 
    {
	GridLocator d1 = new GridLocator(38.05737, 23.79665);
	GridLocator d2 = new GridLocator(38.01686, 23.80057);
	GridLocator d3 = new GridLocator(51.50484, -0.11367);
	GridLocator d4 = new GridLocator(-34.6268, -58.36955);

	assertEquals(38.05737, d1.getLatitude(),0.000000001);
	assertEquals(38.01686, d2.getLatitude(),0.000000001);
	assertEquals(51.50484, d3.getLatitude(),0.000000001);
	assertEquals(-34.6268, d4.getLatitude(),0.000000001);

    }


    @Test
    public void testGetLocatorLocatorCalculation() throws Exception
    {
	GridLocator d0 = new GridLocator(37.866887, 21.349081);
	assertEquals("locator for 37.866887N 21.349081E", "KM07QU18", d0.getLocator());

	GridLocator d1 = new GridLocator(38.05737, 23.79665);
	assertEquals("locator for 38.05737N 23.79665E", "KM18VB53", d1.getLocator());

	GridLocator d2 = new GridLocator(38.01686, 23.80057);
	assertEquals("locator for 38.01686N 23.80057E", "KM18VA64", d2.getLocator());

	GridLocator d3 = new GridLocator(51.50484, -0.11367);
	assertEquals("locator for 51.50484N 0.11367W", "IO91WM61", d3.getLocator());

	GridLocator d4 = new GridLocator(-34.6268, -58.36955);
	assertEquals("locator for 34.6268S 58.36955W", "GF05TI59", d4.getLocator());

    }	


    @Test
    public void testDistanceFrom() throws Exception 
    {
	GridLocator d1 = new GridLocator(38.05737, 23.79665);
	GridLocator d2 = new GridLocator(38.01686, 23.80057);
	GridLocator d3 = new GridLocator(51.50484, -0.11367);
	GridLocator d4 = new GridLocator(-34.6268, -58.36955);
	assertEquals(4.517570, d1.distanceFrom(d2),0.000001);
	assertEquals(2388.864683, d1.distanceFrom(d3),0.000001);
	assertEquals(11696.277387, d1.distanceFrom(d4),0.000001);
    }

    @Test
    public void testBearingTo() throws Exception 
    {

	GridLocator d1 = new GridLocator(38.05737, 23.79665);
	GridLocator d2 = new GridLocator(38.01686, 23.80057);
	GridLocator d3 = new GridLocator(51.50484, -0.11367);
	GridLocator d4 = new GridLocator(-34.6268, -58.36955);

	assertEquals(175.640464, d1.bearingTo(d2),0.000001);
	assertEquals(316.460501, d1.bearingTo(d3),0.000001);
	assertEquals(237.638946, d1.bearingTo(d4),0.000001);
    }


    @Test
    public void testValidGridLocator()
    {

	try
	{
	    GridLocator dl = new GridLocator(0, 0);

	}catch (Exception e)
	{
	    fail("GridLocator(0, 0) failed");
	}


	try
	{
	    GridLocator dl = new GridLocator(23.2, 45.2);

	}catch (Exception e)
	{
	    fail("GridLocator(23.2, 45.2) failed");
	}

	try
	{
	    GridLocator dl = new GridLocator(-90, 45.2);

	}catch (Exception e)
	{
	    fail("GridLocator(-90, 45.2) failed");
	}

	try
	{
	    GridLocator dl = new GridLocator(90.0, 45.2);

	}catch (Exception e)
	{
	    fail("GridLocator(90.0, 45.2) failed");
	}


	try
	{
	    GridLocator dl = new GridLocator(20, 180);

	}catch (Exception e)
	{
	    fail("GridLocator(20, 180) failed");
	}


	try
	{
	    GridLocator dl = new GridLocator(20, -180);

	}catch (Exception e)
	{
	    fail("GridLocator(20, -180) failed");
	}



    }

    @Test(expected=Exception.class)
    public void testInvalidGridLocator() throws Exception
    {

	GridLocator d1 = new GridLocator(-90.00001, 45.2);
	GridLocator d2 = new GridLocator(90.000001, 45.2);
	GridLocator d3 = new GridLocator(23, -180.00001);
	GridLocator d4 = new GridLocator(23, 180.00001);
    }


}

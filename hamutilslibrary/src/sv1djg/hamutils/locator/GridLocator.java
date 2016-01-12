//
// a Grid locator <-> coordinates converter supporting up to 8 digits 
// grid locator.
//
// Copyright 2011, Nick Tsakonas
//
//   This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package sv1djg.hamutils.locator;

public class GridLocator
{

	private double _latitude;
	private double _longitude;
	private String _locator;

	private final String[] _fieldChars = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X"};

	public enum LocatorLength{Full,FourDigit,SixDigit,EightDigit};

	public GridLocator(double latitude, double longitude) throws Exception
	{


		if (latitude<-90.0)
			throw new Exception("Latitude cannot be less than -90.0 degrees");

		if (latitude>90.0)
			throw new Exception("Latitude cannot be greater than 90.0 degrees");

		if (longitude>180.0)
			throw new Exception("Longitude cannot be greater than 180.0 degrees");

		if (longitude<-180.0)
			throw new Exception("Longitude cannot be less than 180.0 degrees");

		_latitude = latitude;
		_longitude = longitude;

		calculateGridLocator();
	}

	public String getLocator()
	{
		return _locator;
	}

	public String getLocator(LocatorLength precision)
	{
		if (precision == LocatorLength.FourDigit)
			return _locator.substring(0, 4);
		else if (precision == LocatorLength.SixDigit)
			return _locator.substring(0, 6);
		else if (precision == LocatorLength.EightDigit)
			return _locator.substring(0, 8);
		else
			return _locator;

	}

	public double distanceFrom(GridLocator distantGrid)
	{
	    double earthRadius = 6371.0; // in km

	    double lat1 = Math.toRadians(distantGrid.getLatitude());
	    double lon1 = Math.toRadians(distantGrid.getLongitude());
	    double lat2 = Math.toRadians(getLatitude());
	    double lon2 = Math.toRadians(getLongitude());

	    double dlon = (lon2 - lon1);
	    double dlat = (lat2 - lat1);

	    double a = (Math.sin(dlat / 2))
		    * (Math.sin(dlat / 2))
		    + (Math.cos(lat1) * Math.cos(lat2) * Math.sin(dlon / 2) * Math
			    .sin(dlon / 2));

	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double km = earthRadius * c;

	    return km;
	}

	public double bearingTo(GridLocator distantGrid)
	{
	    double lat1 = Math.toRadians(getLatitude());
	    double lon1 = Math.toRadians(getLongitude());
	    double lat2 = Math.toRadians(distantGrid.getLatitude());
	    double lon2 = Math.toRadians(distantGrid.getLongitude());

	    double dlon = (lon2 - lon1);
	    double dlat = (lat2 - lat1);

	    double y = Math.sin(dlon) * Math.cos(lat2);
	    double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1)
		    * Math.cos(lat2) * Math.cos(dlon);

	    double brng = Math.toDegrees(Math.atan2(y, x));
	    if (brng < 0)
		brng = 360.0 + brng;

	    return brng;
	}

	public double getLongitude()
	{
		return _longitude;
	}

	public double getLatitude()
	{
		return _latitude;
	}

	private void calculateGridLocator()
	{
		// normalise lon to south and lat to antimeridian of greenwich (this normalisation gives the Prime Meridian a false easting of 180 degrees
		//and the equator a false northing of 90 degrees.)

		double normalisedLatitude  = _latitude + 90.0;
		double normalisedLongitude = _longitude + 180.0;

		//
		// calculate the first pair (the field)
		// this is made from 18 zones - A to R

		int latitudeField = (int)(normalisedLatitude / 10.0);
		int longitudeField = (int)(normalisedLongitude / 20.0);


		//
		// calculate the second pair (the square)
		// this is made from 10 zones - 0 to 9

		int latitudeSquare = (int)(normalisedLatitude - (double)(latitudeField * 10.0));
		int longitudeSquare = (int)((normalisedLongitude - (double)(longitudeField *  20.0)) /2 );


		//
		// calculate the third pair (the sub field)
		// this is made from 10 zones - A to W

		double latitudeMinutes = (normalisedLatitude - (double)(latitudeField * 10.0) - (double)latitudeSquare)*60.0;
		double longitudeMinutes = (normalisedLongitude - (double)(longitudeField *  20.0) - (double)(longitudeSquare *2))*60.0;

		int latitudeSubField = (int)(latitudeMinutes / 2.5);
		int longitudSubField = (int)(longitudeMinutes / 5.0);

		//
		// calculate the fourth pair (the sub square)
		// this is made from 10 zones - A to W

		double p = latitudeMinutes - (double)(latitudeSubField * 2.5);
		double l = longitudeMinutes - (double)(longitudSubField * 5.0);

		double latitudeSeconds =  p * 60.0;
		double longitudeSeconds = l * 60.0;

		int latitudeSubSquare= (int)(latitudeSeconds / 15.0);
		int longitudSubSquare = (int)(longitudeSeconds / 30.0);

		_locator =  String.format("%s%s%d%d%s%s%d%d", _fieldChars[longitudeField],_fieldChars[latitudeField],longitudeSquare,latitudeSquare,_fieldChars[longitudSubField],_fieldChars[latitudeSubField],longitudSubSquare,latitudeSubSquare);
	}



}

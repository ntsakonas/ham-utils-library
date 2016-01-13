# ham-utils-library
a general purpose library for ham radio software including utilities like:
- Distance calculator
- Grid locator calculator

## Distance calculator
Provides basic methods to calculate distance between geographical points (A -> B).

the convention is to use positive numbers for East Longitude /North Latitude and negative for West Longitude/South Latitude. 

#### calculate distance between 2 points in Km
````java
double fromLatitude = 38.0550815;
double fromLongitude = 23.7974941;
double toLatitude = 32.236416;
double toLongitude = -110.938471;

double distance = DistanceCaclulator.distanceFrom(fromLatitude, fromLongitude, toLatitude, toLongitude);
````

#### calculate the bearing in degrees of point B relative to point A 
````java
double fromLatitude = 38.05737;
double fromLongitude = 23.79665;
double toLatitude = 38.01686;
double toLongitude = 23.80057;

double bearing = DistanceCaclulator.bearingTo(fromLatitude, fromLongitude, toLatitude, toLongitude);
````

#### calculate the midpoint between two geographical coordinates
````java
double fromLatitude = 38.05737;
double fromLongitude = 23.79665;
double toLatitude = 38.01686;
double toLongitude = 23.80057;

CoordinateSet midpointCoords = DistanceCaclulator.midpointOf(fromLatitude, fromLongitude, toLatitude, toLongitude);
````

#### calculate the ending geographical coordinates starting from point A given the heading and distance
```java
double fromLatitude = 38.05737;
double fromLongitude = 23.79665;

CoordinateSet targetPointCoords = DistanceCaclulator.targetAtBearingDistance(fromLatitude, fromLongitude, 220, 1920);
````

### Grid locator calculator
converts a set of coordinates to a grid locator providing up to 8 digits conversion.

````java
GridLocator grid = new GridLocator(51.50484, -0.11367);
// grid.getLocator() should return "IO91WM61"
````

request the converted grid locator with any precision
````java
GridLocator d2 = new GridLocator(38.05723, 23.83344);
// d2.getLocator() should return "KM18WB03"
// d2.getLocator(LocatorLength.SixDigit)) should return "KM18WB"
// d2.getLocator(LocatorLength.FourDigit)) should return "KM18"
````


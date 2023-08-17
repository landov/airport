package hu.landov.airport.util

fun getLongLat(stringLongLat: String): Float {
    if (stringLongLat.length != 8) throw java.lang.IllegalArgumentException("Mailformed Longitude/Latitude")
    val signString = stringLongLat.substring(0, 1);
    var sign = 1;
    if (signString.equals("S", true) || signString.equals("W", true)) sign = -1;
    val degString = stringLongLat.substring(1, 4)
    val minString = stringLongLat.substring(4, 6)
    val secString = stringLongLat.substring(6)
    val number = degString.toFloat()
    val minutes = minString.toFloat() / 60
    val seconds = secString.toFloat() / 3600
    return sign * (number + minutes + seconds)
}

/**
 *      Calculates the sourface distance between two geographical coordinates.
 *      The result is in meters.
 *      Calculation formula: https://www.movable-type.co.uk/scripts/latlong.html
 */
fun calcDistance(
    lat1: Double,
    lon1: Double,
    lat2: Double,
    lon2: Double
): Double {
    val radius = 6371e3 //Earth radius meters
    val fi1 = lat1 * Math.PI / 180
    val fi2 = lat2 * Math.PI / 180
    val dFi = (lat2 - lat1) * Math.PI / 180
    val dAlf = (lon2 - lon1) * Math.PI / 180

    val a = Math.sin(dFi / 2) * Math.sin(dFi / 2) +
            Math.cos(fi1) * Math.cos(fi2) *
            Math.sin(dAlf / 2) * Math.sin(dAlf / 2)

    val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))

    val d = radius * c

    return d
}

//https://www.sunearthtools.com/tools/distance.php
fun calcDirection(
    lat1: Double,
    lon1: Double,
    lat2: Double,
    lon2: Double
): Double {
    val dFi = Math.log(Math.tan(lat2/2+Math.PI/4)/Math.tan(lat1/2+Math.PI/4))
    var dLon = lon1-lon2
    //dLon = if(dLon > 180) dLon % 180 else dLon
    var bearing = (Math.atan2(dLon,dFi) * (180/Math.PI))-180
    while (bearing < 0) bearing += 360
    while (bearing > 360) bearing -=360

    return bearing
}
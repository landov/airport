package hu.landov.airport.util

import hu.landov.airport.common.data.AirportEntity
import hu.landov.airport.common.domain.Airport
import kotlin.streams.toList

fun fromString(line: String) : Airport {


    val items = line.split(";")
    val code = items[0]
    val name = items[1]
    val location = items[2]
    val longitude = getLongLat(items[3])
    val latitude = getLongLat(items[4])
    val elevation = items[5].toInt()
    val callSign = items[6]
    val frequencies = items[7].split(":").stream().map{s -> s.toFloat()}.toList()
    val trafficPattern = items[8]
    val hours = items[9].split(";").toList()

    val nvfr = items[10].toBoolean()
    val ifr = items[11].toBoolean()
    val link = items[12]

    return Airport(
        code,
        name,
        location,
        longitude,
        latitude,
        elevation,
        callSign,
        frequencies,
        trafficPattern,
        hours,
        nvfr,
        ifr,
        link
    )
}


/**
 *
 */
fun getLongLat(stringLongLat : String) : Float{
    if(stringLongLat.length != 8) throw java.lang.IllegalArgumentException("Mailformed Longitude/Latitude")
    val signString = stringLongLat.substring(0,1);
    var sign = 1;
    if(signString.equals("S",true) || signString.equals("W",true)) sign = -1;
    val degString = stringLongLat.substring(1,4)
    val minString = stringLongLat.substring(4,6)
    val secString = stringLongLat.substring(6)
    val number = degString.toFloat()
    val minutes = minString.toFloat() / 60
    val seconds = secString.toFloat() / 3600
    return sign * (number + minutes + seconds)
}
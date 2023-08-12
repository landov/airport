package hu.landov.airport.common.domain.airport

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import hu.landov.airport.util.getLongLat
import kotlinx.parcelize.Parcelize
import kotlin.streams.toList

//TODO UI models for views?
//TODO latitude/longitude be Double!
@Parcelize
data class Airport (
    val code: String,
    val name: String,
    val location: String,
    val latitude: Float,
    val longitude: Float,
    val elevation: Int,
    val callSign : String,
    val frequencies: List<Float>,
    val trafficPattern: String,
    val operatingHours: List<String>,
    val nvfr: Boolean,
    val ifr: Boolean,
    val windLink: String
) : Parcelable {
    companion object {
        fun fromString(line: String): Airport {

            val items = line.split(";")
            val code = items[0]
            val name = items[1]
            val location = items[2]
            val longitude = getLongLat(items[3])
            val latitude = getLongLat(items[4])
            val elevation = items[5].toInt()
            val callSign = items[6]
            val frequencies = items[7].split(":").stream().map { s -> s.toFloat() }.toList()
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
    }
}
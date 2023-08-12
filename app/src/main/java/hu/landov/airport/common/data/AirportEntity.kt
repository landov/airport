package hu.landov.airport.common.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import hu.landov.airport.common.domain.airport.Airport

@Entity(tableName = "airport")


data class AirportEntity(
    @PrimaryKey
    val code: String,
    val name: String,
    val location: String,
    val latitude: Float,
    val longitude: Float,
    val elevation: Int,
    @ColumnInfo(name = "call_sign")
    val callSign : String,
    val frequencies: List<Float>,
    @ColumnInfo(name = "trafic_pattern")
    val trafficPattern: String,
    @ColumnInfo(name = "operating_hours")
    val operatingHours: List<String>,
    val nvfr: Boolean,
    val ifr: Boolean,
    @ColumnInfo(name = "wind_link")
    val windLink: String
) {
    companion object{
        fun fromAirport(airport: Airport) : AirportEntity{
            return AirportEntity(
                airport.code,
                airport.name,
                airport.location,
                airport.longitude,
                airport.latitude,
                airport.elevation,
                airport.callSign,
                airport.frequencies,
                airport.trafficPattern,
                airport.operatingHours,
                airport.nvfr,
                airport.ifr,
                airport.windLink
            )

        }
    }

    fun toAirport() : Airport {
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
            operatingHours,
            nvfr,
            ifr,
            windLink
        )
    }
}
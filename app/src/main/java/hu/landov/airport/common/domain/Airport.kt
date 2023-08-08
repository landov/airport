package hu.landov.airport.common.domain

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

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
) : Parcelable
package hu.landov.airport.common.domain.location

//TODO error message?
data class LocationState (
    val altitude : Double,
    val speed: Double,
    val distance : Double,
    val direction: Double
    )

package hu.landov.airport.common.domain

import hu.landov.airport.common.data.AirportEntity
import kotlinx.coroutines.flow.Flow

interface AirportRepository {

    fun addAirport(airport: Airport)

    //TODO return Airport not AirportEntity
    fun getAirports() : Flow<List<Airport>>

}
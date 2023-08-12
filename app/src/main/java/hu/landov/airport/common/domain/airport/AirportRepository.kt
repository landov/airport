package hu.landov.airport.common.domain.airport

import kotlinx.coroutines.flow.Flow

interface AirportRepository {

    fun addAirport(airport: Airport)

    fun getAirports() : Flow<List<Airport>>

}
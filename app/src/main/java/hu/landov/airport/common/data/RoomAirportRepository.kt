package hu.landov.airport.common.data

import android.content.Context
import hu.landov.airport.common.domain.airport.Airport
import hu.landov.airport.common.domain.airport.AirportRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject
import kotlin.streams.toList

class RoomAirportRepository @Inject constructor(app: Context) : AirportRepository {

    private val database = AirportDatabase.getDatabase(app)
    private val dao = database.airportDao()

    override fun addAirport(airport: Airport) {
        dao.addAirport(AirportEntity.fromAirport(airport))
    }

    override fun getAirports(): Flow<List<Airport>> =
        dao.getAirports().transform { collector ->
            val list = collector.toCollection(mutableListOf())
                .stream().map { entity ->
                    entity.toAirport()
                }.toList()
            emit(list)
        }

}
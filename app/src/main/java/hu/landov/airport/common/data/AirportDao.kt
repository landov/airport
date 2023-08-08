package hu.landov.airport.common.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAirport(airportEntity: AirportEntity)

    @Delete
    fun removeAirport(airportEntity: AirportEntity)

    @Query("SELECT * FROM airport ORDER BY code ASC")
    fun getAirports() : Flow<List<AirportEntity>>
}
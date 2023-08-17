package hu.landov.airport.common.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [AirportEntity::class],version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AirportDatabase : RoomDatabase() {

    abstract fun airportDao() : AirportDao

    companion object{
        @Volatile
        private var INSTANCE : AirportDatabase? = null

        fun getDatabase(context: Context) : AirportDatabase {
            return INSTANCE ?:synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AirportDatabase::class.java,
                    "airport_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
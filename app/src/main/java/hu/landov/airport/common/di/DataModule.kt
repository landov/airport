package hu.landov.airport.common.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.landov.airport.common.data.RoomAirportRepository
import hu.landov.airport.common.domain.airport.AirportRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideAirportRepository(@ApplicationContext context: Context) : AirportRepository =
        RoomAirportRepository(context)
}
package hu.landov.airport.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hu.landov.airport.common.domain.location.LocationStateProvider
import hu.landov.airport.common.domain.wind.WindStateProvider
import hu.landov.airport.common.providers.GpsLocationStateProvider
import hu.landov.airport.common.providers.IdokepWindStateProvider

@Module
@InstallIn(ViewModelComponent::class)
interface ProvidersModule{
    @Binds
    @ViewModelScoped
    fun bindWindStateProvider(impl: IdokepWindStateProvider): WindStateProvider

    @Binds
    @ViewModelScoped
    fun bindLocationStateProvider(impl: GpsLocationStateProvider): LocationStateProvider
}


package hu.landov.airport.common.di.fragments

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import hu.landov.airport.common.domain.location.LocationStateProvider
import hu.landov.airport.common.domain.wind.WindStateProvider
import hu.landov.airport.common.providers.GpsLocationStateProvider
import hu.landov.airport.common.providers.IdokepWindStateProvider
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class)
interface FragmentModule{
    @Binds
    @FragmentScoped
    fun bindWindStateProvider(impl: IdokepWindStateProvider): WindStateProvider

    @Binds
    @FragmentScoped
    fun bindLocationStateProvider(impl: GpsLocationStateProvider): LocationStateProvider
}


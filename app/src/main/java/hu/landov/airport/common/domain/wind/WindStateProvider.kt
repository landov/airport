package hu.landov.airport.common.domain.wind

import androidx.lifecycle.LiveData
import hu.landov.airport.common.domain.airport.Airport

interface WindStateProvider  {
    fun getWindState(airport: Airport) : LiveData<WindState>
    fun start()
    fun stop()
}
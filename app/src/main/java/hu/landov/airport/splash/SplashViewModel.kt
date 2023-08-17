package hu.landov.airport.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.landov.airport.common.domain.airport.Airport
import hu.landov.airport.common.domain.airport.AirportRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val repository: AirportRepository,
    val application: Application
) : ViewModel() {


    private var _proceed = MutableLiveData<Boolean>(false)
    val proceed: LiveData<Boolean>
        get() = _proceed

    init {
        loadDatabase()
    }

    fun loadDatabase() {
        CoroutineScope(Dispatchers.IO).launch {

            val ist: InputStream = application.resources.openRawResource(
                application.resources.getIdentifier(
                    "airports",
                    "raw",
                    application.packageName
                )
            )
            val scanner = Scanner(ist)
            while (scanner.hasNextLine()) {
                val line = scanner.nextLine()
                val airport = Airport.fromString(line)
                repository.addAirport(airport)
                Log.d("SPLASH", "$airport");
            }

            _proceed.postValue(true)
        }
    }


}